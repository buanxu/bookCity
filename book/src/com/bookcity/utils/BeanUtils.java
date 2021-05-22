package com.bookcity.utils;

import com.bookcity.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class BeanUtils {

    //把请求参数注入到bean中
    public static <T> T copyParamsToBean(T bean,Map params){
        try {
            org.apache.commons.beanutils.BeanUtils.populate(bean,params);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return bean;
    }
}
