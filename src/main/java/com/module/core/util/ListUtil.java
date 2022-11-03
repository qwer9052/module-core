package com.module.core.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListUtil {

    /**
     * ex)
     * List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
     *    addToList(list, Stream.of(4, 5));
     *
     *    스트림 요소 합치기
     *
     * @param target
     * @param source
     * @param <T>
     */
    public static<T> void addToList(List<T> target, Stream<T> source) {
        source.collect(Collectors.toCollection(() -> target));
    }
}
