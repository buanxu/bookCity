package com.bookcity.web;

import com.bookcity.entity.User;
import com.bookcity.service.UserService;
import com.bookcity.service.impl.UserServiceImpl;
import com.bookcity.utils.BeanUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserController extends BaseServlet {

    private UserService userService=new UserServiceImpl();

    /**
     * 通过ajax判断当前注册的用户名是否存在
     * @param req
     * @param resp
     * @throws IOException
     */
    public void ajaxExistUsername(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取注册的用户名
        String username=req.getParameter("username");
        //查询数据库里是否存在该用户名
        boolean isExistsUsername = userService.existsUsername(username);
        //把查询结果封装在map里
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("isExistsUsername",isExistsUsername);

        //把map对象转换成String类型的数据传回前台
        Gson gson=new Gson();
        String json=gson.toJson(map);
        resp.getWriter().print(json);

    }

    /**
     * kaptcha谷歌验证码，使用需要导依赖，
     * 使用方法：在web.xml中配置KaptchaServlet的请求路径，KaptchaServlet会自动生成验证码及验证码图片，并将验证码放在session域
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        //从session域中获取KaptchaServlet生成的验证码，KAPTCHA_SESSION_KEY即验证码的key
        String sessionCode = (String) req.getSession().getAttribute("KAPTCHA_SESSION_KEY");
        //删除session中的验证码，以阻止重复提交
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        User user=(User)BeanUtils.copyParamsToBean(new User(),req.getParameterMap());

        //检查验证码
        if(sessionCode!=null && sessionCode.equalsIgnoreCase(code)){//验证码正确
            //再检查用户名是否已存在
            if (userService.existsUsername(username)){//已存在，则跳转到注册页面
                System.out.println("用户名["+username+"]已存在");

                //返回给前台信息
                req.setAttribute("errorMsg", "该用户名已存在");
                req.setAttribute("username",username);
                req.setAttribute("email",email);

                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            }else{//不存在,则注册并跳转到登陆页面
                userService.register(user);
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        }else {//验证码错误
            System.out.println("验证码错误");

            //返回给前台信息
            req.setAttribute("errorMsg", "验证码错误");
            req.setAttribute("username",username);
            req.setAttribute("email",email);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }

    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User loginUser = userService.login(new User(null, username, password, null));
        if (loginUser==null){//登陆失败
            req.setAttribute("errorMsg", "用户名或密码错误！");
            req.setAttribute("username",username );
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req ,resp);
        }else {//登录成功
            //用户信息保存到session域
            req.getSession().setAttribute("user",loginUser);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req ,resp);
        }
    }

    public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //用户退出时销毁session(或销毁session中用户的信息)
        req.getSession().invalidate();

        //退出后重定向到index
        resp.sendRedirect(req.getContextPath());
    }


}
