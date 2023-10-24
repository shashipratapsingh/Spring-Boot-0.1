package com.flipkart.JDK8;


import java.util.Comparator;
import java.util.stream.Stream;

public class StreamConversion {
    public static void main(String args[]){
      /*  String[] language= {"java", "c","c++"};
        Arrays.asList(language)
                .stream()
                .filter(n->!n.contains("java"))
                .forEach(System.out::println);*/


                 //String[] numbers= {"1","4","3","2","4"};
                 //Arrays.stream(numbers)
                // .filter(n->!n.contains("3"))
                // .sorted()
                // .collect(Collectors.toList())
                 //.forEach(System.out::println);


        Integer highest = Stream.of(1, 2, 3, 77, 6, 5)
                .max(Comparator.comparing(Integer::valueOf))
                .get();
        Integer lowest = Stream.of(1, 2, 3, 77, 6, 5)
                .min(Comparator.comparing(Integer::valueOf))
                .get();
        System.out.println("The highest number is: " + highest);
        System.out.println("The lowest number is: " + lowest);
    }
}
