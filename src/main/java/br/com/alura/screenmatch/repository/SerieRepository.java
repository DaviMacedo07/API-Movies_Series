package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.model.Categoria;
import br.com.alura.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {

   Optional<Serie> findByTituloContainingIgnoreCase(String nomeSerie);

   List<Serie> findByAtoresContainingIgnoreCase(String nomeAtor);

   List<Serie> findTop5ByOrderByAvaliacaoDesc();

   List<Serie> findByGenero(Categoria categoria);

   @Query("SELECT s FROM Serie s WHERE s.totalTemporadas = :totalTemporadas AND s.avaliacao >= :avaliacao")
   public List<Serie> seriesPorTemporadaEAValiacao(@Param("totalTemporadas") int totalTemporadas, @Param("avaliacao") double avaliacao);

   @Query("SELECT s FROM Serie s " +
           "JOIN s.episodios e " +
           "GROUP BY s " +
           "ORDER BY MAX(e.dataLancamento) DESC")
   List<Serie> lancamentosMaisRecentes(Pageable pageable);

}
