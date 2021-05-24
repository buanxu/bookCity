package com.bookcity.utils;

import com.bookcity.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class BeanUtils {

    /**
     * 把请求参数注入到bean中
     * @param bean
     * @param params
     * @param <T>
     * @return
     */
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

    /**
     * 把字符转类型的数字转换成int类型
     * @param value
     * @param defaultValue
     * @return
     */
    public static int parseInt(String value,int defaultValue){
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }
}
