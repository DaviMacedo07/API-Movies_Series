package br.com.alura.screenmatch.model;


public enum Categoria {
    ACAO("Action", "Ação"),
    ROMANCE("Romance", "Romance"),
    COMEDIA("Comedy", "Comédia"),
    DRAMA("Drama", "Drama"),
    CRIME("Crime", "Crime"),
    AVENTURA("Adventure", "Aventura"),
    FICCAO_CIENTIFICA("Sci-Fi", "Ficção Científica"),
    TERROR("Horror", "Terror"),
    DOCUMENTARIO("Documentary", "Documentário"),
    THRILLER("Thriller", "Suspense"),
    BIOGRAFIA("Biography", "Biografia");

    private String categoriaOmdb;
    private String categoriaPortugues;
    Categoria(String categoriaOmdb, String categoriaPortugues) {
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaPortugues = categoriaPortugues;
    }

    public static Categoria fromString(String text) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Categoria não pode ser vazia");
        }

        // Split by comma in case there are multiple genres
        String[] genres = text.split(",");

        // Try to match the first genre
        String primaryGenre = genres[0].trim();

        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(primaryGenre)) {
                return categoria;
            }
        }

        // If no exact match is found, try partial matching
        for (Categoria categoria : Categoria.values()) {
            if (primaryGenre.toLowerCase().contains(categoria.categoriaOmdb.toLowerCase()) ||
                    categoria.categoriaOmdb.toLowerCase().contains(primaryGenre.toLowerCase())) {
                return categoria;
            }
        }

        throw new IllegalArgumentException("Nenhuma categoria encontrada para: " + text);
    }
    public static Categoria fromPortugues(String text) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Categoria não pode ser vazia");
        }
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaPortugues.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

}

