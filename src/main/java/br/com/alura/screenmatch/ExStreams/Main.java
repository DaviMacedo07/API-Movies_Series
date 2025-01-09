package br.com.alura.screenmatch.ExStreams;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> lista = Arrays.asList(1, 2, 3, 4, 5, 6);

        lista.stream()
                .filter(n -> n % 2 == 0)
                .forEach(System.out::println);
    }
}