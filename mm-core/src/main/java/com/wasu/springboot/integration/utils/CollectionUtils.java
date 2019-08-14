package com.wasu.springboot.integration.utils;

import java.util.Collection;

/**
 * Created by viruser on 2019/8/9.
 */
public class CollectionUtils {
    /**
     * 集合是否为空
     *
     * @param coll
     * @return
     * @author 王越
     * @date 10:58
     */
    public static boolean isEmpty(Collection coll) {
        return coll == null || coll.isEmpty();
    }

    /**
     * 集合是否不为空
     *
     * @param coll
     * @return
     * @author 王越
     * @date 10:58
     */
    public static boolean isNotEmpty(Collection coll) {
        return !CollectionUtils.isEmpty(coll);
    }
}
