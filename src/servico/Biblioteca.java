package servico;

import java.util.ArrayList;
import java.util.List;

import modelo.Emprestimo;
import modelo.Livro;
import modelo.Usuario;

public class Biblioteca {
    public static class ResultadoOperacao {
        private final boolean sucesso;
        private final String mensagem;

        public ResultadoOperacao(boolean sucesso, String mensagem) {
            this.sucesso = sucesso;
            this.mensagem = mensagem;
        }

        public boolean isSucesso() {
            return sucesso;
        }

        public String getMensagem() {
            return mensagem;
        }
    }

    public static class ResultadoBusca<T> {
        private final boolean sucesso;
        private final String mensagem;
        private final ArrayList<T> resultados;

        public ResultadoBusca(boolean sucesso, String mensagem, ArrayList<T> resultados) {
            this.sucesso = sucesso;
            this.mensagem = mensagem;
            this.resultados = resultados;
        }

        public boolean isSucesso() {
            return sucesso;
        }

        public String getMensagem() {
            return mensagem;
        }

        public ArrayList<T> getResultados() {
            return resultados;
        }
    }

    private ArrayList<Livro> livros;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Emprestimo> emprestimos;
    private int proximoIdLivro;
    private int proximoIdUsuario;
    private int proximoIdEmprestimo;

    public Biblioteca() {
        livros = new ArrayList<Livro>();
        usuarios = new ArrayList<Usuario>();
        emprestimos = new ArrayList<Emprestimo>();
        proximoIdLivro = 1;
        proximoIdUsuario = 1;
        proximoIdEmprestimo = 1;
    }

    public int gerarIdLivro() {
        return proximoIdLivro++;
    }

    public int gerarIdUsuario() {
        return proximoIdUsuario++;
    }

    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
    }

    public ResultadoOperacao editarLivro(int idLivro, String titulo, String autor, int anoPublicacao) {
        Livro livro = buscarLivroPorId(idLivro);
        if (livro == null) {
            return new ResultadoOperacao(false, "Livro nao encontrado.");
        }

        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setAnoPublicacao(anoPublicacao);
        return new ResultadoOperacao(true, "Livro atualizado com sucesso.");
    }

    public ResultadoOperacao removerLivro(int idLivro) {
        Livro livro = buscarLivroPorId(idLivro);
        if (livro == null) {
            return new ResultadoOperacao(false, "Livro nao encontrado.");
        }

        if (!livro.isDisponivel()) {
            return new ResultadoOperacao(false, "Livro emprestado nao pode ser removido.");
        }

        livros.remove(livro);
        return new ResultadoOperacao(true, "Livro removido com sucesso.");
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public ResultadoOperacao editarUsuario(int idUsuario, String nome, String email, String matricula) {
        Usuario usuario = buscarUsuarioPorId(idUsuario);
        if (usuario == null) {
            return new ResultadoOperacao(false, "Usuario nao encontrado.");
        }

        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setMatricula(matricula);
        return new ResultadoOperacao(true, "Usuario atualizado com sucesso.");
    }

    public ResultadoOperacao removerUsuario(int idUsuario) {
        Usuario usuario = buscarUsuarioPorId(idUsuario);
        if (usuario == null) {
            return new ResultadoOperacao(false, "Usuario nao encontrado.");
        }

        if (possuiEmprestimoAtivo(usuario)) {
            return new ResultadoOperacao(false, "Usuario com emprestimo ativo nao pode ser removido.");
        }

        usuarios.remove(usuario);
        return new ResultadoOperacao(true, "Usuario removido com sucesso.");
    }

    public void listarLivros() {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }

        for (Livro livro : livros) {
            System.out.println(livro.exibirDados());
        }
    }

    public void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuario cadastrado.");
            return;
        }

        for (Usuario usuario : usuarios) {
            System.out.println(usuario.exibirDados());
        }
    }

    public boolean realizarEmprestimo(int idLivro, int idUsuario) {
        Livro livro = buscarLivroPorId(idLivro);
        Usuario usuario = buscarUsuarioPorId(idUsuario);

        if (livro == null || usuario == null) {
            return false;
        }

        if (!livro.emprestar()) {
            return false;
        }

        Emprestimo emprestimo = new Emprestimo(proximoIdEmprestimo, usuario, livro);
        proximoIdEmprestimo++;
        emprestimos.add(emprestimo);
        return true;
    }

    public ResultadoOperacao realizarEmprestimoComRetorno(int idLivro, int idUsuario) {
        Livro livro = buscarLivroPorId(idLivro);
        if (livro == null) {
            return new ResultadoOperacao(false, "Livro nao encontrado.");
        }

        Usuario usuario = buscarUsuarioPorId(idUsuario);
        if (usuario == null) {
            return new ResultadoOperacao(false, "Usuario nao encontrado.");
        }

        if (!livro.emprestar()) {
            return new ResultadoOperacao(false, "Livro ja emprestado.");
        }

        Emprestimo emprestimo = new Emprestimo(proximoIdEmprestimo, usuario, livro);
        proximoIdEmprestimo++;
        emprestimos.add(emprestimo);
        return new ResultadoOperacao(true, "Emprestimo realizado com sucesso.");
    }

    public boolean realizarDevolucao(int idEmprestimo) {
        Emprestimo emprestimo = buscarEmprestimoAtivoPorId(idEmprestimo);

        if (emprestimo == null) {
            return false;
        }

        emprestimo.finalizarEmprestimo();
        return true;
    }

    public ResultadoOperacao realizarDevolucaoComRetorno(int idEmprestimo) {
        Emprestimo emprestimo = buscarEmprestimoPorId(idEmprestimo);
        if (emprestimo == null) {
            return new ResultadoOperacao(false, "Emprestimo nao encontrado.");
        }

        if (!emprestimo.isAtivo()) {
            return new ResultadoOperacao(false, "Emprestimo ja finalizado.");
        }

        emprestimo.finalizarEmprestimo();
        return new ResultadoOperacao(true, "Devolucao realizada com sucesso.");
    }

    public void listarEmprestimosAtivos() {
        boolean encontrou = false;

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.isAtivo()) {
                System.out.println(emprestimo.exibirDados());
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum emprestimo ativo.");
        }
    }

    public ResultadoBusca<Livro> buscarLivroPorTitulo(String trechoTitulo) {
        ArrayList<Livro> encontrados = new ArrayList<Livro>();
        String trechoNormalizado = trechoTitulo.toLowerCase();

        for (Livro livro : livros) {
            if (livro.getTitulo().toLowerCase().contains(trechoNormalizado)) {
                encontrados.add(livro);
            }
        }

        if (encontrados.isEmpty()) {
            return new ResultadoBusca<Livro>(false, "Nenhum livro encontrado.", encontrados);
        }

        return new ResultadoBusca<Livro>(true, "Livros encontrados: " + encontrados.size(), encontrados);
    }

    public ResultadoBusca<Usuario> buscarUsuarioPorNome(String trechoNome) {
        ArrayList<Usuario> encontrados = new ArrayList<Usuario>();
        String trechoNormalizado = trechoNome.toLowerCase();

        for (Usuario usuario : usuarios) {
            if (usuario.getNome().toLowerCase().contains(trechoNormalizado)) {
                encontrados.add(usuario);
            }
        }

        if (encontrados.isEmpty()) {
            return new ResultadoBusca<Usuario>(false, "Nenhum usuario encontrado.", encontrados);
        }

        return new ResultadoBusca<Usuario>(true, "Usuarios encontrados: " + encontrados.size(), encontrados);
    }

    public ResultadoBusca<Livro> buscarLivroPorIdComRetorno(int idLivro) {
        Livro livro = buscarLivroPorId(idLivro);
        ArrayList<Livro> resultados = new ArrayList<Livro>();
        if (livro == null) {
            return new ResultadoBusca<Livro>(false, "Livro nao encontrado.", resultados);
        }

        resultados.add(livro);
        return new ResultadoBusca<Livro>(true, "Livro encontrado.", resultados);
    }

    public ResultadoBusca<Usuario> buscarUsuarioPorIdComRetorno(int idUsuario) {
        Usuario usuario = buscarUsuarioPorId(idUsuario);
        ArrayList<Usuario> resultados = new ArrayList<Usuario>();
        if (usuario == null) {
            return new ResultadoBusca<Usuario>(false, "Usuario nao encontrado.", resultados);
        }

        resultados.add(usuario);
        return new ResultadoBusca<Usuario>(true, "Usuario encontrado.", resultados);
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    private Livro buscarLivroPorId(int id) {
        for (Livro livro : livros) {
            if (livro.getId() == id) {
                return livro;
            }
        }

        return null;
    }

    private Usuario buscarUsuarioPorId(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }

        return null;
    }

    private Emprestimo buscarEmprestimoPorId(int id) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getId() == id) {
                return emprestimo;
            }
        }

        return null;
    }

    private Emprestimo buscarEmprestimoAtivoPorId(int id) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getId() == id && emprestimo.isAtivo()) {
                return emprestimo;
            }
        }

        return null;
    }

    private boolean possuiEmprestimoAtivo(Usuario usuario) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.isAtivo() && emprestimo.getUsuario().getId() == usuario.getId()) {
                return true;
            }
        }
        return false;
    }
}
