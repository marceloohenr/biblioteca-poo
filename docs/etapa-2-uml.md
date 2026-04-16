# Etapa 2 - Modelagem UML

## Descricao geral

A modelagem do Biblioteca Facil foi feita pensando em um sistema simples de biblioteca. As classes representam os principais elementos do sistema: pessoas, usuarios, bibliotecario, livros, emprestimos e a propria biblioteca.

O diagrama tambem foi usado como base para a implementacao do prototipo em Java.

## Classes do sistema

### Pessoa

Classe abstrata criada para guardar informacoes comuns de pessoas do sistema.

Atributos:

- `id`
- `nome`
- `email`

Metodo principal:

- `exibirDados()`

### Usuario

Representa a pessoa que pode pegar livros emprestados.

Atributo especifico:

- `matricula`

Relacionamento:

- Herda de `Pessoa`.

### Bibliotecario

Representa o funcionario responsavel pela biblioteca.

Atributo especifico:

- `cargo`

Relacionamento:

- Herda de `Pessoa`.

### Livro

Representa um livro cadastrado no sistema.

Atributos:

- `id`
- `titulo`
- `autor`
- `anoPublicacao`
- `disponivel`

Metodos principais:

- `emprestar()`
- `devolver()`
- `exibirDados()`

### Emprestimo

Representa o emprestimo de um livro para um usuario.

Atributos:

- `id`
- `usuario`
- `livro`
- `dataEmprestimo`
- `dataDevolucao`
- `ativo`

Metodos principais:

- `finalizarEmprestimo()`
- `exibirDados()`

### Biblioteca

Classe responsavel por controlar as listas e operacoes principais do sistema.

Atributos:

- `livros`
- `usuarios`
- `emprestimos`

Metodos principais:

- cadastrar livro
- cadastrar usuario
- listar livros
- listar usuarios
- realizar emprestimo
- realizar devolucao
- listar emprestimos ativos

### Main

Classe responsavel por iniciar o sistema e exibir o menu no terminal.

## Relacionamentos

- `Usuario` herda de `Pessoa`.
- `Bibliotecario` herda de `Pessoa`.
- `Emprestimo` se relaciona com `Usuario` e `Livro`.
- `Biblioteca` possui listas de `Livro`, `Usuario` e `Emprestimo`.

## Observacao

Essa modelagem pode ser ajustada nas proximas etapas, caso o sistema precise evoluir. Mesmo assim, qualquer alteracao deve manter coerencia com a proposta inicial.

