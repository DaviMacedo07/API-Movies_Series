package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.*;
import br.com.alura.screenmatch.repository.SerieRepository;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=459f19";


    @Autowired
    private SerieRepository serieRepository;

    private List<Serie> series = new ArrayList<>();

    private List<DadosSerie> dadosSeries = new ArrayList<>();

    public void exibeMenu() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("""
                1 - Buscar séries
                2 - Buscar episódios
                3 - Listar séries buscadas                
                4 - Buscar série por título
                5 - Buscar série por ator(atriz)
                6 - Top 5 séries
                7 - Buscar série por categorias
                8 - Filtrar séries
                0 - Sair
                """);
            System.out.print("Escolha uma opção: ");

            try {
                opcao = leitura.nextInt();
                leitura.nextLine();

                switch (opcao) {
                    case 1 -> buscarSerieWeb();
                    case 2 -> buscarEpisodioPorSerie();
                    case 3 -> listarSeriesBuscadas();
                    case 4 -> buscarSeriePorTitulo();
                    case 5 -> buscarSeriePorAtor();
                    case 6 -> buscarTopSeries();
                    case 7 -> buscarSeriesPorCategoria();
                    case 8 -> filtrarSeriesPorTemporadaEAvaliacao();
                    case 0 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida, tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                leitura.nextLine();
            }
        }
    }

    private void filtrarSeriesPorTemporadaEAvaliacao() {
        System.out.println("Filtrar séries ate quantas temporadas? ");
        var totalTemp = leitura.nextInt();
        System.out.println("Qual pontuação de avaliação minima? ");
        var avaliacaoSerie = leitura.nextDouble();
        leitura.nextLine();
        List<Serie> filtroSeries = serieRepository.seriesPorTemporadaEAValiacao(totalTemp, avaliacaoSerie);
        System.out.println("--- SÉRIES FILTRADAS ---");
        filtroSeries.forEach(s -> System.out.println( s.getTitulo() +  "-- Total de Temporadas: " + s.getTotalTemporadas() + " -- Avaliação: " + s.getAvaliacao()));
    }

    private void buscarSeriesPorCategoria() {
        System.out.println("Qual categoria/genero de série você deseja? ");
        var nomeGenero = leitura.nextLine();
        Categoria categoria = Categoria.fromPortugues(nomeGenero);
        List<Serie> seriesPorCategoria = serieRepository.findByGenero(categoria);
        System.out.println("Séries da categoria: " + nomeGenero);
        seriesPorCategoria.forEach(System.out::println);
    }

    private void buscarTopSeries() {
        List<Serie> serieTop = serieRepository.findTop5ByOrderByAvaliacaoDesc();
        serieTop.forEach(s ->  System.out.println(s.getTitulo() + " avaliação: " + s.getAvaliacao()));
    }

    private void buscarSeriePorAtor() {
        System.out.println("Digite o nome do ator: ");
        var nomeAtor = leitura.nextLine();
        List<Serie> seriesEncontradas = serieRepository.findByAtoresContainingIgnoreCase(nomeAtor);
        System.out.println("Séries em que o(a): " + nomeAtor + " atuou: ");
        seriesEncontradas.forEach(s -> System.out.println(s.getTitulo() + " avaliação: " + s.getAvaliacao()));
    }


    private void buscarSerieWeb() {
        DadosSerie dados = getDadosSerie();
        Serie serie = new Serie(dados);
     //   dadosSeries.add(dados);
        serieRepository.save(serie);
        System.out.println(dados);
    }

    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        return dados;
    }

    private void buscarEpisodioPorSerie() {
        listarSeriesBuscadas();
        System.out.println("Escolha uma serie pelo nome");
        var nomeSerie = leitura.nextLine();

        Optional<Serie> serie = serieRepository.findByTituloContainingIgnoreCase(nomeSerie);

        if (serie.isPresent()) {

            var serieEncontrada = serie.get();
            List<DadosTemporada> temporadas = new ArrayList<>();

            for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
                var json = consumo.obterDados(ENDERECO + serieEncontrada.getTitulo().replace(" ", "+") + "&season=" + i + API_KEY);
                DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
                temporadas.add(dadosTemporada);
            }
            temporadas.forEach(System.out::println);

            List<Episodio> episodios = temporadas.stream()
                    .flatMap(d -> d.episodios().stream()
                            .map(e -> new Episodio(d.numero(), e)))
                    .collect(Collectors.toList());
            serieEncontrada.setEpisodios(episodios);
            serieRepository.save(serieEncontrada);
        } else {
            System.out.println("Série não encontrada! ");
        }
    }
    private void listarSeriesBuscadas() {
        series = serieRepository.findAll();
        if (series.isEmpty()) {
            System.out.println("Nenhuma série foi buscada ainda.");
        } else {
            series.forEach(serie -> {
                System.out.println("Título: " + serie.getTitulo());
                System.out.println("Gênero: " + serie.getGenero());
                System.out.println("Total Temporadas: " + serie.getTotalTemporadas());
                System.out.println("Avaliação: " + serie.getAvaliacao());
                System.out.println("------------------");
            });
        }
    }

    private void buscarSeriePorTitulo() {
        System.out.println("Escolha uma série pelo título: ");
        var nomeSerie = leitura.nextLine();
        Optional<Serie> serieBuscada = serieRepository.findByTituloContainingIgnoreCase(nomeSerie);

        if(serieBuscada.isPresent()) {
            System.out.println("Dados da Série: " + serieBuscada.get());
        } else {
            System.out.println("Série não encontrada!");
        }
    }
    }