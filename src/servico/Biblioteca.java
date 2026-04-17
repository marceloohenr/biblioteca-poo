package servico;

import java.util.ArrayList;

import modelo.Emprestimo;
import modelo.Livro;
import modelo.Usuario;

public class Biblioteca {
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

    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
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

    public boolean realizarDevolucao(int idEmprestimo) {
        Emprestimo emprestimo = buscarEmprestimoAtivoPorId(idEmprestimo);

        if (emprestimo == null) {
            return false;
        }

        emprestimo.finalizarEmprestimo();
        return true;
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

    private Emprestimo buscarEmprestimoAtivoPorId(int id) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getId() == id && emprestimo.isAtivo()) {
                return emprestimo;
            }
        }

        return null;
    }
}
