# Biblioteca Facil

Projeto desenvolvido para a disciplina de Programacao Orientada a Objetos.

## Descricao

O **Biblioteca Facil** e um sistema simples para ajudar no controle de uma biblioteca. A ideia principal e permitir o cadastro de livros e usuarios, alem de controlar emprestimos e devolucoes de forma basica pelo terminal.

## Problema

Em muitas bibliotecas pequenas, o controle de livros ainda pode ser feito manualmente, usando cadernos ou planilhas. Isso pode causar confusao na hora de saber quais livros estao disponiveis, quem esta com cada livro emprestado e quais emprestimos ainda estao ativos.

## Objetivo

Criar um sistema em Java que organize as principais informacoes de uma biblioteca, usando conceitos de Programacao Orientada a Objetos.

## Funcionalidades propostas

- Cadastrar livros
- Listar livros cadastrados
- Cadastrar usuarios
- Listar usuarios cadastrados
- Realizar emprestimo de livro
- Realizar devolucao de livro
- Consultar emprestimos ativos

## Integrantes

- Marcelo Henrique Malagueta
- Kilber Fernando Guimaraes

## Situacao do projeto

- Etapa 1: proposta do projeto concluida
- Etapa 2: modelagem UML concluida
- Etapa 3: prototipo funcional em Java em desenvolvimento

## Modelagem das classes

O sistema foi pensado com classes simples, separando as informacoes principais da biblioteca.

- `Pessoa`: classe base para representar dados comuns de pessoas do sistema.
- `Usuario`: representa o usuario que pode pegar livros emprestados.
- `Bibliotecario`: representa o funcionario responsavel pela biblioteca.
- `Livro`: representa um livro cadastrado e controla se ele esta disponivel.
- `Emprestimo`: representa o emprestimo de um livro para um usuario.
- `Biblioteca`: concentra as listas de livros, usuarios e emprestimos, alem das operacoes principais.
- `Main`: classe usada para executar o sistema pelo terminal.

O diagrama de classes esta na pasta `docs`.
