package br.com.alura.screenmatch.LambdaEx;

public class LambdaEx3 {
    public static void main(String[] args) {
        ToUpper toUpper = s -> s.toUpperCase();
        System.out.println(toUpper.transform("boneca"));
    }
}
