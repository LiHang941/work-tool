package com.github.lihang941.tool.common.bean;


import org.springframework.beans.BeanUtils;

/**
 * @author : lihang1329@gmail.com
 */
public class BeanConvert {

    public static <T> T copy(Object source, T target) {
        if (source == null) {
            return null;
        }
        BeanUtils.copyProperties(source, target);
        return target;
    }

    public static <T> T copy(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        T t;
        try {
            t = targetClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        BeanUtils.copyProperties(source, t);
        return t;
    }


}
