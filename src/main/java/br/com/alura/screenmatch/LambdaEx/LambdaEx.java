package br.com.alura.screenmatch.LambdaEx;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaEx {
    public static void main(String[] args) {
//        Operacao multiplica = (a, b) -> a * b;
//        System.out.println(multiplica.executar(5,3));
        Operacao operacao = n -> {
            if (n <=1 ) return false;
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) return false;
            }
            return true;
        };
        System.out.println(operacao.primoVerifica(12));
        System.out.println(operacao.primoVerifica(7));
        System.out.println(operacao.primoVerifica(3));
        System.out.println(Math.sqrt(25));


        // teste


        List<Integer> numeros = Arrays.asList(1,2,3,4,5,6,7,8,9,12,11,424,345);

        List<Integer> pares = numeros.stream()
                .filter(n -> n % 2 == 0 )
                .collect(Collectors.toList());

        System.out.println(pares);
    }
}
