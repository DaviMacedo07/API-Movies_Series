package br.com.alura.screenmatch.ExStreams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main3 {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(10,20,30,40,50, 51);
        List<String> palavaras = Arrays.asList("java", "maca", "pera", "bestie", "lamda");

        Optional<Integer> max = numeros.stream()
                .max(Integer::compare);
        max.ifPresent(System.out::println);

        Map<Integer,List<String>> agrp = palavaras.stream()
                .collect(Collectors.groupingBy(String::length));

        System.out.println(agrp);

        }
}
