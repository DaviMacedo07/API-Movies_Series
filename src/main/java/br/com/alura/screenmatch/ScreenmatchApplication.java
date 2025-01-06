package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.DadosFilme;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoAPI = new ConsumoAPI();
		var json = consumoAPI.obterDados(" http://www.omdbapi.com/?t=Speak-no-Evil&apikey=459f19");
		System.out.println(json);
		ConverteDados conversor = new ConverteDados();
		DadosFilme dados = conversor.obterDados(json, DadosFilme.class );
		System.out.println(dados);
	}
}
