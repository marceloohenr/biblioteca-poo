package modelo;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private boolean disponivel;

    public Livro(int id, String titulo, String autor, int anoPublicacao) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.disponivel = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public boolean emprestar() {
        if (!disponivel) {
            return false;
        }

        disponivel = false;
        return true;
    }

    public void devolver() {
        disponivel = true;
    }

    public String exibirDados() {
        String situacao = disponivel ? "Disponivel" : "Emprestado";

        return "ID: " + id
                + " | Titulo: " + titulo
                + " | Autor: " + autor
                + " | Ano: " + anoPublicacao
                + " | Situacao: " + situacao;
    }
}

