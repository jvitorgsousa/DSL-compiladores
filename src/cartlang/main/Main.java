package cartlang.main;

import cartlang.parser.*;
import cartlang.semantic.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {
    public static void main(String[] args) {
        
        String caminhoArquivo = (args.length > 0) ? args[0] : "teste_valido.cart";

        System.out.println("Tentando processar o arquivo: " + caminhoArquivo);

        try {
            CharStream input = CharStreams.fromFileName(caminhoArquivo);
            
            // 1. ANÁLISE LÉXICA (Lexer)
            CartLangLexer lexer = new CartLangLexer(input);
            
            // Força o Lexer a lançar exceção no PRIMEIRO erro léxico encontrado
            lexer.removeErrorListeners();
            lexer.addErrorListener(new CustomErrorListener());

            CommonTokenStream tokens = new CommonTokenStream(lexer);
            
            // 2. ANÁLISE SINTÁTICA (Parser)
            CartLangParser parser = new CartLangParser(tokens);
            
            // Força o Parser a lançar exceção ao encontrar erro sintático
            parser.removeErrorListeners();
            parser.addErrorListener(new CustomErrorListener());

            // O parsing e a construção da AST acontecem aqui
            ParseTree tree = parser.program();

            // 3. ANÁLISE SEMÂNTICA E EXECUÇÃO (Visitor)
            CartVisitor visitor = new CartVisitor();
            visitor.visit(tree);

            System.out.println("\n[SUCESSO]: Arquivo processado e executado com sucesso!");

        } catch (Exception e) {
            System.err.println("\n[EXCEÇÃO CAPTURADA]: " + e.getMessage());
            e.printStackTrace(); 
        }
    }
}