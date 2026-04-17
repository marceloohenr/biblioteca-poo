package app;

import java.util.Scanner;

import modelo.Bibliotecario;
import modelo.Livro;
import modelo.Pessoa;
import modelo.Usuario;
import servico.Biblioteca;

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

        if (biblioteca.realizarEmprestimo(idLivro, idUsuario)) {
            System.out.println("Emprestimo realizado com sucesso.");
        } else {
            System.out.println("Nao foi possivel realizar o emprestimo.");
        }
    }

    private static void realizarDevolucao() {
        System.out.println();
        System.out.println("Realizar devolucao");

        int idEmprestimo = lerInteiro("ID do emprestimo: ");

        if (biblioteca.realizarDevolucao(idEmprestimo)) {
            System.out.println("Devolucao realizada com sucesso.");
        } else {
            System.out.println("Emprestimo ativo nao encontrado.");
        }
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
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
}

