package io.github.fengzaiyao.plugin.common.util;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class BeanUtil {

    public static void copyBean(Object source, Object target) {
        if (source == null || target == null) {
            return;
        }
        try {
            BeanUtils.copyProperties(source, target);
        } catch (Exception ex) {
        }
    }

    public static <T> T copyBean(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        try {
            T target = targetClass.newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (Exception ex) {
            return null;
        }
    }

    public static <F, T> List<T> copyListBean(List<F> fromList, Class<T> tClass) {
        if (fromList == null || fromList.isEmpty()) {
            return null;
        }
        List<T> tList = new ArrayList<>();
        for (F f : fromList) {
            T t = copyBean(f, tClass);
            tList.add(t);
        }
        return tList;
    }
}
