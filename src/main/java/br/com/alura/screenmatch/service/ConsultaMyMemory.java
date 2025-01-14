package br.com.alura.screenmatch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ConsultaMyMemory {

    public static String obterTraducao(String text) {
        ObjectMapper mapper = new ObjectMapper();
        ConsumoAPI consumo = new ConsumoAPI();


        String texto = URLEncoder.encode(text, StandardCharsets.UTF_8);
        String langpair = URLEncoder.encode("en|pt-br", StandardCharsets.UTF_8);

        String url = "https://api.mymemory.translated.net/get?q=" + texto + "&langpair=" + langpair;

        String json = consumo.obterDados(url);

        try {
            DadosTraducao traducao = mapper.readValue(json, DadosTraducao.class);
            return traducao.dadosResposta().textoTraduzido();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao processar a resposta JSON: " + e.getMessage(), e);
        }
    }
}
