//package br.com.alura.screenmatch;
//
//import br.com.alura.screenmatch.principal.Principal;
//import br.com.alura.screenmatch.repository.SerieRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class ScreenmatchApplicationSemWeb implements CommandLineRunner {
//    private final SerieRepository serieRepository;
//    private final Principal principal;
//
//    public ScreenmatchApplicationSemWeb(SerieRepository serieRepository, Principal principal) {
//        this.serieRepository = serieRepository;
//        this.principal = principal;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        principal.exibeMenu();
//    }
//
//    public static void main(String[] args) {
//        SpringApplication.run(ScreenmatchApplicationSemWeb.class, args);
//    }
//}