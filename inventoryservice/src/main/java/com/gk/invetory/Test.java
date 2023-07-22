package com.gk.invetory;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Integer> list=List.of(1,2,3,4,5);
        list.stream().filter(i->i%2!=0).reduce(Integer::sum).ifPresent(System.out::println);
    }
}
