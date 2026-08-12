# CartLang — Linguagem de Domínio Específico (DSL) para Carrinho de Compras

**CartLang** é uma DSL projetada para simular e gerenciar operações de uma lista de compras de forma simples e intuitiva. 
O projeto utiliza **ANTLR4** para análise léxica e sintática, e **Java** para a interpretação e checagem semântica.

Este projeto foi feito com o intuito de estudar e aprender sobre o funcionamento de DSLs.

Autores:
- João Vitor Gomes de Sousa
- Vitória Tavares Araújo
- Mônyca Monaliza Almeida Marques

---
## Estrutura de Comandos e Sintaxe

A linguagem suporta declaração de variáveis, adição de produtos ao carrinho, emissão de relatórios, controle de fluxo com condicionais e laços de repetição.

### 1. Declaração de Variáveis
Declarar variáveis explicitamente, com ou sem inicialização.

  ```
  tipo nomeVariavel;
  tipo nomeVariavel = valor;
  ```
* **Tipos suportados:** `int`, `float`, `string`

### 2.  Adição de Itens
Adiciona um produto com seu respectivo valor ou quantidade à lista de compras.

```
item "Nome do Produto", ValorOuQuantidade;
```
* **Sintaxe:** O primeiro parâmetro deve obrigatoriamente ser uma String, seguindo de seu valor que deve ser um float.

### 3.  Resumo de Compras
Imprime no console a lista atualizada com todos os itens adicionados ao carrinho e o total acumulado.

```
resumo;
```

### 4.  Estruturas de Controle (Condições e Repetições)
Cumpre a mesma função de sua base, executa blocos de código com base em condições lógica.

```
if (expressao) {
    // instruções
} else {
    // instruções
}

while (expressao) {
    // instruções
}
```

---
## Compilação e Execução

### 1. Geração dos arquivos ANTLR
```
java -jar lib/antlr-4.13.1-complete.jar -visitor -package cartlang.parser -o src/cartlang/parser src/cartlang/grammar/CartLang.g4
```

### 2. Compilação do projeto Java
```
javac -cp ".;lib/antlr-4.13.1-complete.jar" -d bin (Get-ChildItem -Recurse src/*.java)
```

### 3. Execução de arquivo 
```
javac -cp ".;lib/antlr-4.13.1-complete.jar" -d bin (Get-ChildItem -Recurse src/*.java)
```
