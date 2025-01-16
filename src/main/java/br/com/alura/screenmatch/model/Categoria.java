package br.com.alura.screenmatch.model;


public enum Categoria {
    ACAO("Action"),
    ROMANCE("Romance"),
    COMEDIA("Comedy"),
    DRAMA("Drama"),
    CRIME("Crime"),
    AVENTURA("Adventure"),
    FICCAO_CIENTIFICA("Sci-Fi"),
    TERROR("Horror"),
    DOCUMENTARIO("Documentary"),
    THRILLER("Thriller"),
    BIOGRAFIA("Biography");

    private String categoriaOmdb;
    Categoria(String categoriaOmdb) {
        this.categoriaOmdb = categoriaOmdb;
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

    }

