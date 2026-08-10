package cartlang.main;

import cartlang.parser.*;
import cartlang.semantic.*;
import cartlang.lexer.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {
    public static void main(String[] args) {
        String caminhoArquivo = (args.length > 0) ? args[0] : "teste_valido.cart";

        System.out.println("Tentando processar o arquivo: " + caminhoArquivo);

        try {
            CharStream input = CharStreams.fromFileName(caminhoArquivo);
            CartLangLexer lexer = new CartLangLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            CartLangParser parser = new CartLangParser(tokens);

            // Tenta realizar o parse
            ParseTree tree = parser.program();

            if (parser.getNumberOfSyntaxErrors() > 0) {
                System.err.println("[ERRO SINTATICO]: O arquivo contém erros de sintaxe.");
                return;
            }

            // Executa a verificação semântica
            CartVisitor visitor = new CartVisitor();
            visitor.visit(tree);

            System.out.println("[SUCESSO]: Arquivo processado e executado com sucesso!");

        } catch (Exception e) {
            System.err.println("\n[EXCEÇÃO CAPTURADA]: " + e.getMessage());
            e.printStackTrace(); // Mostra a linha exata onde o código falhou
        }
    }
}