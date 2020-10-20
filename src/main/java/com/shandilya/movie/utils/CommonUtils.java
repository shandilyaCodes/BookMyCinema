package com.shandilya.movie.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonUtils {

    public static List<String> csvToString(String csv) {
        return new ArrayList<>(Arrays.asList(csv.split(",")));
    }

    public static List<String> intersection(List<String> listOne, List<String> listTwo) {
        return listOne.stream()
                .filter(listTwo::contains)
                .collect(Collectors.toList());
    }

    public static List<String> union(List<String> listOne, List<String> listTwo) {
        return Stream.concat(listOne.stream(), listTwo.stream())
                .distinct()
                .collect(Collectors.toList());
    }

    public static String listToCsv(List<String> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : list) {
            stringBuilder.append(s).append(",");
        }
        return stringBuilder.toString();
    }
}