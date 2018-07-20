package com.djk.utils;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Predicate;

/**
 * 增加一些Java8中不支持的集合操作方法
 * Created by dujinkai on 17/6/15.
 */
public class IteratorUtils {


    /**
     * 2个集合进行合并
     *
     * @param coll    要合并的对象集合
     * @param subColl 被合并的对象集合
     * @param matcher 两个对象匹配函数
     * @param merger  两个对象的合并函数，Consumer类型，无需返回值
     * @param <F>     要合并的对象类型
     * @param <V>     合并后的对象类型
     * @return 合并后的对象集合
     */
    public static <F, V> List<F> zip(Collection<F> coll, Collection<V> subColl, BiFunction<F, V, Boolean> matcher,
                                     BiConsumer<F, V> merger) {
        coll.stream().forEach(item -> CollectionUtils.select(subColl, object -> matcher.apply(item, object)).stream()
                .forEach(subItem -> merger.accept(item, subItem)));
        return coll instanceof List ? (List) coll : new ArrayList<>(coll);
    }

    /**
     * 从一个集合中选择出一个满足匹配条件的实体
     *
     * @param lists     集合
     * @param predicate 匹配条件
     * @param <T>       返回对象
     * @return 返回匹配到的对象
     */
    public static <T> Optional<T> filterMatch(List<T> lists, Predicate<T> predicate) {
        return lists.stream().filter(predicate).findFirst();
    }
}
