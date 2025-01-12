package br.com.alura.screenmatch.ExStreams;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> lista = Arrays.asList(1, 2, 3, 4, 5, 6);

        lista.stream()
                .filter(n -> n % 2 != 0)
                .map(n -> n * 2)
                .forEach(System.out::println);

        System.out.println("*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_");

        List<String> palavras = Arrays.asList("banana", "apple", "apple", "orange", "orange", "banana");

        List<String> unicas = palavras.stream()
                .distinct()
                .collect(Collectors.toList());

        System.out.println(unicas);

        System.out.println("*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_");

        List<List<Integer>> numbers = Arrays.asList(
                Arrays.asList(1,2,3,4),
                Arrays.asList(5,6,7,8),
                Arrays.asList(9,10,11,12)
        );

        List<Integer> primos = numbers.stream()
                .flatMap(List::stream)
                .filter(Main::ehPrimo)
                .sorted()
                .collect(Collectors.toList());
        System.out.println(primos);



    }

    private static boolean ehPrimo(int numero) {
        if (numero < 2) return false;
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }
}