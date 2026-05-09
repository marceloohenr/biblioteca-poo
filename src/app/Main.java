package app;

import java.util.Scanner;

import modelo.Bibliotecario;
import modelo.Livro;
import modelo.Pessoa;
import modelo.Usuario;
import servico.Biblioteca;
import servico.Biblioteca.ResultadoBusca;
import servico.Biblioteca.ResultadoOperacao;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Biblioteca biblioteca = new Biblioteca();

    public static void main(String[] args) {
        Pessoa responsavel = new Bibliotecario(1, "Responsavel da biblioteca", "biblioteca@email.com", "Bibliotecario");

        System.out.println("Biblioteca Facil");
        System.out.println("Sistema iniciado por: " + responsavel.exibirDados());

        int opcao;

        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opcao: ");
            executarOpcao(opcao);
        } while (opcao != 0);

        System.out.println("Sistema encerrado.");
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println();
        System.out.println("===== MENU =====");
        System.out.println("1 - Cadastrar livro");
        System.out.println("2 - Listar livros");
        System.out.println("3 - Cadastrar usuario");
        System.out.println("4 - Listar usuarios");
        System.out.println("5 - Realizar emprestimo");
        System.out.println("6 - Realizar devolucao");
        System.out.println("7 - Listar emprestimos ativos");
        System.out.println("8 - Editar livro");
        System.out.println("9 - Remover livro");
        System.out.println("10 - Editar usuario");
        System.out.println("11 - Remover usuario");
        System.out.println("12 - Buscar livro por ID");
        System.out.println("13 - Buscar livro por titulo");
        System.out.println("14 - Buscar usuario por ID");
        System.out.println("15 - Buscar usuario por nome");
        System.out.println("0 - Sair");
    }

    private static void executarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                cadastrarLivro();
                break;
            case 2:
                biblioteca.listarLivros();
                break;
            case 3:
                cadastrarUsuario();
                break;
            case 4:
                biblioteca.listarUsuarios();
                break;
            case 5:
                realizarEmprestimo();
                break;
            case 6:
                realizarDevolucao();
                break;
            case 7:
                biblioteca.listarEmprestimosAtivos();
                break;
            case 8:
                editarLivro();
                break;
            case 9:
                removerLivro();
                break;
            case 10:
                editarUsuario();
                break;
            case 11:
                removerUsuario();
                break;
            case 12:
                buscarLivroPorId();
                break;
            case 13:
                buscarLivroPorTitulo();
                break;
            case 14:
                buscarUsuarioPorId();
                break;
            case 15:
                buscarUsuarioPorNome();
                break;
            case 0:
                break;
            default:
                System.out.println("Opcao invalida.");
                break;
        }
    }

    private static void cadastrarLivro() {
        System.out.println();
        System.out.println("Cadastro de livro");

        String titulo = lerTexto("Titulo: ");
        String autor = lerTexto("Autor: ");
        int ano = lerInteiro("Ano de publicacao: ");

        Livro livro = new Livro(biblioteca.gerarIdLivro(), titulo, autor, ano);
        biblioteca.cadastrarLivro(livro);

        System.out.println("Livro cadastrado com sucesso.");
    }

    private static void cadastrarUsuario() {
        System.out.println();
        System.out.println("Cadastro de usuario");

        String nome = lerTexto("Nome: ");
        String email = lerTexto("Email: ");
        String matricula = lerTexto("Matricula: ");

        Usuario usuario = new Usuario(biblioteca.gerarIdUsuario(), nome, email, matricula);
        biblioteca.cadastrarUsuario(usuario);

        System.out.println("Usuario cadastrado com sucesso.");
    }

    private static void realizarEmprestimo() {
        System.out.println();
        System.out.println("Realizar emprestimo");

        int idLivro = lerInteiro("ID do livro: ");
        int idUsuario = lerInteiro("ID do usuario: ");

        if (!idValido(idLivro) || !idValido(idUsuario)) {
            System.out.println("IDs invalidos. Informe valores maiores que zero.");
            return;
        }

        ResultadoOperacao resultado = biblioteca.realizarEmprestimoComRetorno(idLivro, idUsuario);
        System.out.println(resultado.getMensagem());
    }

    private static void realizarDevolucao() {
        System.out.println();
        System.out.println("Realizar devolucao");

        int idEmprestimo = lerInteiro("ID do emprestimo: ");

        if (!idValido(idEmprestimo)) {
            System.out.println("ID invalido. Informe valor maior que zero.");
            return;
        }

        ResultadoOperacao resultado = biblioteca.realizarDevolucaoComRetorno(idEmprestimo);
        System.out.println(resultado.getMensagem());
    }

    private static void editarLivro() {
        System.out.println();
        System.out.println("Editar livro");

        int idLivro = lerInteiro("ID do livro: ");
        if (!idValido(idLivro)) {
            System.out.println("ID invalido. Informe valor maior que zero.");
            return;
        }

        String titulo = lerTextoNaoVazio("Novo titulo: ");
        String autor = lerTextoNaoVazio("Novo autor: ");
        int ano = lerInteiro("Novo ano de publicacao: ");

        ResultadoOperacao resultado = biblioteca.editarLivro(idLivro, titulo, autor, ano);
        System.out.println(resultado.getMensagem());
    }

    private static void removerLivro() {
        System.out.println();
        System.out.println("Remover livro");

        int idLivro = lerInteiro("ID do livro: ");
        if (!idValido(idLivro)) {
            System.out.println("ID invalido. Informe valor maior que zero.");
            return;
        }

        ResultadoOperacao resultado = biblioteca.removerLivro(idLivro);
        System.out.println(resultado.getMensagem());
    }

    private static void editarUsuario() {
        System.out.println();
        System.out.println("Editar usuario");

        int idUsuario = lerInteiro("ID do usuario: ");
        if (!idValido(idUsuario)) {
            System.out.println("ID invalido. Informe valor maior que zero.");
            return;
        }

        String nome = lerTextoNaoVazio("Novo nome: ");
        String email = lerTextoNaoVazio("Novo email: ");
        String matricula = lerTextoNaoVazio("Nova matricula: ");

        ResultadoOperacao resultado = biblioteca.editarUsuario(idUsuario, nome, email, matricula);
        System.out.println(resultado.getMensagem());
    }

    private static void removerUsuario() {
        System.out.println();
        System.out.println("Remover usuario");

        int idUsuario = lerInteiro("ID do usuario: ");
        if (!idValido(idUsuario)) {
            System.out.println("ID invalido. Informe valor maior que zero.");
            return;
        }

        ResultadoOperacao resultado = biblioteca.removerUsuario(idUsuario);
        System.out.println(resultado.getMensagem());
    }

    private static void buscarLivroPorId() {
        System.out.println();
        System.out.println("Buscar livro por ID");

        int idLivro = lerInteiro("ID do livro: ");
        if (!idValido(idLivro)) {
            System.out.println("ID invalido. Informe valor maior que zero.");
            return;
        }

        ResultadoBusca<Livro> resultado = biblioteca.buscarLivroPorIdComRetorno(idLivro);
        System.out.println(resultado.getMensagem());
        for (Livro livro : resultado.getResultados()) {
            System.out.println(livro.exibirDados());
        }
    }

    private static void buscarLivroPorTitulo() {
        System.out.println();
        System.out.println("Buscar livro por titulo");

        String trechoTitulo = lerTextoNaoVazio("Trecho do titulo: ");
        ResultadoBusca<Livro> resultado = biblioteca.buscarLivroPorTitulo(trechoTitulo);
        System.out.println(resultado.getMensagem());
        for (Livro livro : resultado.getResultados()) {
            System.out.println(livro.exibirDados());
        }
    }

    private static void buscarUsuarioPorId() {
        System.out.println();
        System.out.println("Buscar usuario por ID");

        int idUsuario = lerInteiro("ID do usuario: ");
        if (!idValido(idUsuario)) {
            System.out.println("ID invalido. Informe valor maior que zero.");
            return;
        }

        ResultadoBusca<Usuario> resultado = biblioteca.buscarUsuarioPorIdComRetorno(idUsuario);
        System.out.println(resultado.getMensagem());
        for (Usuario usuario : resultado.getResultados()) {
            System.out.println(usuario.exibirDados());
        }
    }

    private static void buscarUsuarioPorNome() {
        System.out.println();
        System.out.println("Buscar usuario por nome");

        String trechoNome = lerTextoNaoVazio("Trecho do nome: ");
        ResultadoBusca<Usuario> resultado = biblioteca.buscarUsuarioPorNome(trechoNome);
        System.out.println(resultado.getMensagem());
        for (Usuario usuario : resultado.getResultados()) {
            System.out.println(usuario.exibirDados());
        }
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    private static String lerTextoNaoVazio(String mensagem) {
        while (true) {
            String texto = lerTexto(mensagem).trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("Campo obrigatorio. Digite um valor valido.");
        }
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException erro) {
                System.out.println("Digite um numero valido.");
            }
        }
    }

    private static boolean idValido(int id) {
        return id > 0;
    }
}

