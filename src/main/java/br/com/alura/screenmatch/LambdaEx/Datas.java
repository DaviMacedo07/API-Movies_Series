package br.com.alura.screenmatch.LambdaEx;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Datas {
    public static void main(String[] args) {
        String pattern = "E, dd MMM yyyy HH:mm:ss z";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        System.out.println(date);
    }
}
