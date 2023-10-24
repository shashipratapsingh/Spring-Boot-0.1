package com.flipkart.JDK8;
import java.util.*;
import java.util.function.Function;
import java.util.stream.*;
public class JdkInterview {

    //Given a list of integers, find out all the even numbers that exist in the list using Stream functions?
    /* public static void main(String args[]) {
            List<Integer> list = Arrays.asList(10,15,8,49,25,98,32);
            list.stream()
                    .filter(n -> n%2 == 0)
                    .forEach(System.out::println);

    }*/


   // Given a list of integers, find out all the numbers starting with 1 using Stream functions?
  /* public static void main(String args[]) {
       List<Integer> myList = Arrays.asList(10,15,8,29,25,98,32);
       myList.stream()
               .map(s -> s + "") // Convert integer to String
               .filter(s -> s.startsWith("2"))
               .forEach(System.out::println);
   }*/

    //How to find duplicate elements in a given integers list in java using Stream functions?
    /*public static void main(String args[]) {
        List<Integer> myList = Arrays.asList(10,15,8,49,25,98,98,32,15);
        Set<Integer> set = new HashSet();
        myList.stream()
                .filter(n -> !set.add(n))
                .forEach(System.out::println);
    }*/

    /*public static void main(String args[]){
        List<Integer> list=Arrays.asList(1,2,3,3,2,6,7);
        Set<Integer> set=new HashSet<>();
        list.stream()
               // .filter(n->set.add(n))
                //.count()
                .map(n -> n + "")
                .filter(n->n.startsWith("3"))
        .forEach(System.out::println);*/

    //Given the list of integers, find the first element of the list using Stream functions?

   /* public static void main(String args[]) {
        List<Integer> myList = Arrays.asList(10,15,8,49,25,98,98,32,15);
        myList.stream()
                .findFirst()
                .ifPresent(System.out::println);
    }*/

    //Given a list of integers, find the total number of elements present in the list using Stream functions
    /*public static void main(String args[]) {
        List<Integer> myList = Arrays.asList(10,15,8,49,25,98,98,32,15);
        long count =  myList.stream()
                .count();
        System.out.println(count);
    }*/

    //Given a list of integers, find the maximum value element present in it using Stream functions?

   /* public static void main(String args[]) {
        String input = "Java articles are Awesome";

        Character result = input.chars() // Stream of String
                .mapToObj(s -> Character.toLowerCase(Character.valueOf((char) s))) // First convert to Character object and then to lowercase
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting())) //Store the chars in map with count
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1L)
                .map(entry -> entry.getKey())
                .findFirst()
                .get();
                System.out.println(result);
    }*/

   // How to find only duplicate elements with its count from the String ArrayList in Java8?


}























