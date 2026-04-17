# Biblioteca Fácil

Projeto desenvolvido para a disciplina de Programação Orientada a Objetos.

## Descrição

O **Biblioteca Fácil** é um sistema simples para ajudar no controle de uma biblioteca. A ideia principal é permitir o cadastro de livros e usuários, além de controlar empréstimos e devoluções de forma básica.

## Problema

Em muitas bibliotecas pequenas, o controle de livros ainda pode ser feito manualmente, usando cadernos ou planilhas. Isso pode causar confusão na hora de saber quais livros estão disponíveis, quem está com cada livro emprestado e quais empréstimos ainda estão ativos.

## Objetivo

Criar um sistema em Java que organize as principais informações de uma biblioteca, usando conceitos de Programação Orientada a Objetos.

## Funcionalidades propostas

- Cadastrar livros
- Listar livros cadastrados
- Cadastrar usuários
- Listar usuários cadastrados
- Realizar empréstimo de livro
- Realizar devolução de livro
- Consultar empréstimos ativos

## Funcionalidades implementadas

- Cadastro de livros pelo terminal
- Listagem de livros cadastrados
- Cadastro de usuários
- Listagem de usuários
- Registro de empréstimos
- Devolução de livros
- Consulta de empréstimos ativos

## Integrantes

- Marcelo Henrique Malagueta
- Kilber Fernando Guimarães

## Modelagem das classes

O sistema foi pensado com classes simples, separando as informações principais da biblioteca.

- `Pessoa`: classe base para representar dados comuns de pessoas do sistema.
- `Usuario`: representa o usuário que pode pegar livros emprestados.
- `Bibliotecario`: representa o funcionário responsável pela biblioteca.
- `Livro`: representa um livro cadastrado e controla se ele está disponível.
- `Emprestimo`: representa o empréstimo de um livro para um usuário.
- `Biblioteca`: concentra as listas de livros, usuários e empréstimos, além das operações principais.
- `Main`: classe usada para executar o sistema pelo terminal.

Os documentos de entrega estão na pasta `docs`: a proposta do projeto e o diagrama de classes UML.

## Estrutura do projeto

```text
src/
  app/
    Main.java
  modelo/
    Pessoa.java
    Usuario.java
    Bibliotecario.java
    Livro.java
    Emprestimo.java
  servico/
    Biblioteca.java
docs/
  etapa-1-proposta.pdf
  uml-biblioteca-facil.pdf
```

## Como executar

É necessário ter o JDK instalado, pois o projeto usa o compilador `javac`.

Para compilar:

```bash
javac -d bin src/app/Main.java src/modelo/*.java src/servico/*.java
```

Para executar:

```bash
java -cp bin app.Main
```
