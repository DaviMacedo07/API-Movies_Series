package br.com.alura.screenmatch.LambdaEx;

public class LambdaEx4 {
    public static void main(String[] args) {
        Palindromo palindromo = str -> str.equals(new StringBuilder(str).reverse().toString());
        System.out.println(palindromo.verificaPalindromo("radar"));
        System.out.println(palindromo.verificaPalindromo("java"));
        System.out.println(palindromo.verificaPalindromo("mirim"));
    }
}
