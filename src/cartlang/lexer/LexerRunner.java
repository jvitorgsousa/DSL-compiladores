package cartlang.lexer;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import cartlang.parser.CartLangLexer;
import cartlang.semantic.CustomErrorListener;

import java.io.IOException;

public class LexerRunner {

    public static CommonTokenStream processarArquivo(String caminhoArquivo) throws IOException {
        // Leitura do arquivo de código
        CharStream input = CharStreams.fromFileName(caminhoArquivo);

        // Inicialização do Lexer gerado pelo ANTLR
        CartLangLexer lexer = new CartLangLexer(input);

        // Registro do tratador de erros customizado
        lexer.removeErrorListeners();
        lexer.addErrorListener(new CustomErrorListener());

        // Retorno de sequência de tokens gerada pela análise léxica
        return new CommonTokenStream(lexer);
    }
}