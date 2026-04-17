package modelo;

import java.time.LocalDate;

public class Emprestimo {
    private int id;
    private Usuario usuario;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private boolean ativo;

    public Emprestimo(int id, Usuario usuario, Livro livro) {
        this.id = id;
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = LocalDate.now();
        this.ativo = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public void finalizarEmprestimo() {
        ativo = false;
        dataDevolucao = LocalDate.now();
        livro.devolver();
    }

    public String exibirDados() {
        String situacao = ativo ? "Ativo" : "Finalizado";
        String devolucao = dataDevolucao == null ? "Nao devolvido" : dataDevolucao.toString();

        return "ID: " + id
                + " | Usuario: " + usuario.getNome()
                + " | Livro: " + livro.getTitulo()
                + " | Emprestimo: " + dataEmprestimo
                + " | Devolucao: " + devolucao
                + " | Situacao: " + situacao;
    }
}

