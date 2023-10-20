package com.flipkart.JDK8;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamAPI {
    public static void main(String arr[])
    {
        //concarancy
        String input="shashipratapsingh";

        Map<String, Long> collect = Arrays.stream(input.split(""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        //System.out.println(collect);
    }
}
