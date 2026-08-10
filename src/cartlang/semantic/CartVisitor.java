package cartlang.semantic;
import java.util.ArrayList;
import java.util.List;

import cartlang.parser.CartLangBaseVisitor;
import cartlang.parser.CartLangParser;

public class CartVisitor extends CartLangBaseVisitor<Object> {

    private final SymbolTable symbolTable = new SymbolTable();

    // Estrutura para os comandos de domínio
    private static class ItemCompra {
        String nome;
        double preco;
        ItemCompra(String nome, double preco) {
            this.nome = nome;
            this.preco = preco;
        }
    }

    private final List<ItemCompra> carrinho = new ArrayList<>();

    // --- Declaração e Atribuição ---

    @Override
    public Object visitVarDecl(CartLangParser.VarDeclContext ctx) {
        String name = ctx.ID().getText();
        String typeStr = ctx.type().getText();
        SymbolTable.Type type = parseType(typeStr);

        int line = ctx.getStart().getLine();

        if (!symbolTable.declare(name, type)) {
            throw new RuntimeException("Erro Semântico na linha " + line + ": Variável '" + name + "' já foi declarada.");
        }

        if (ctx.expr() != null) {
            Object val = visit(ctx.expr());
            checkTypeMatch(type, val, line);
            symbolTable.get(name).value = val;
        }
        return null;
    }

    @Override
    public Object visitAssignment(CartLangParser.AssignmentContext ctx) {
        String name = ctx.ID().getText();
        int line = ctx.getStart().getLine();

        if (!symbolTable.isDeclared(name)) {
            throw new RuntimeException("Erro Semântico na linha " + line + ": Variável '" + name + "' não foi declarada.");
        }

        SymbolTable.Symbol sym = symbolTable.get(name);
        Object val = visit(ctx.expr());
        checkTypeMatch(sym.type, val, line);
        sym.value = val;
        return null;
    }

    // --- Comandos do Domínio (Lista de Compras) ---

    @Override
    public Object visitItemStmt(CartLangParser.ItemStmtContext ctx) {
        Object nomeObj = visit(ctx.expr(0));
        Object precoObj = visit(ctx.expr(1));
        int line = ctx.getStart().getLine();

        if (!(nomeObj instanceof String)) {
            throw new RuntimeException("Erro Semântico na linha " + line + ": O primeiro argumento do comando 'item' deve ser uma String.");
        }
        if (!(precoObj instanceof Number)) {
            throw new RuntimeException("Erro Semântico na linha " + line + ": O segundo argumento do comando 'item' deve ser int ou float.");
        }

        String nome = (String) nomeObj;
        double preco = ((Number) precoObj).doubleValue();

        carrinho.add(new ItemCompra(nome, preco));
        System.out.println("[DSL Log] Adicionado ao carrinho: " + nome + " -> R$ " + String.format("%.2f", preco));
        return null;
    }

    @Override
    public Object visitResumoStmt(CartLangParser.ResumoStmtContext ctx) {
        System.out.println("\n==========================================");
        System.out.println("         RESUMO DA LISTA DE COMPRAS       ");
        System.out.println("==========================================");
        double total = 0;
        for (ItemCompra item : carrinho) {
            System.out.printf("- %-25s R$ %7.2f\n", item.nome, item.preco);
            total += item.preco;
        }
        System.out.println("------------------------------------------");
        System.out.printf("TOTAL ACUMULADO:              R$ %7.2f\n", total);
        System.out.println("==========================================\n");
        return null;
    }

    // --- Estruturas de Controle ---

    @Override
    public Object visitIfStmt(CartLangParser.IfStmtContext ctx) {
        Object condVal = visit(ctx.expr());
        if (isTrue(condVal)) {
            visit(ctx.statement(0));
        } else if (ctx.statement().size() > 1) {
            visit(ctx.statement(1));
        }
        return null;
    }

    @Override
    public Object visitWhileStmt(CartLangParser.WhileStmtContext ctx) {
        while (isTrue(visit(ctx.expr()))) {
            visit(ctx.statement());
        }
        return null;
    }

    // --- Expressões Literais e Aritméticas ---

    @Override
    public Object visitOpExpr(CartLangParser.OpExprContext ctx) {
        Object left = visit(ctx.expr(0));
        Object right = visit(ctx.expr(1));
        String op = ctx.op.getText();
        int line = ctx.getStart().getLine();

        if (left instanceof Number && right instanceof Number) {
            double l = ((Number) left).doubleValue();
            double r = ((Number) right).doubleValue();

            switch (op) {
                case "+": return l + r;
                case "-": return l - r;
                case "*": return l * r;
                case "/": return l / r;
                case ">": return l > r;
                case "<": return l < r;
                case "==": return l == r;
            }
        }

        // Validação semântica: impede somas inválidas (ex: String + Int)
        throw new RuntimeException("Erro Semântico na linha " + line + ": Operação '" + op + "' não suportada entre " 
            + left.getClass().getSimpleName() + " e " + right.getClass().getSimpleName());
    }

    @Override
    public Object visitVarExpr(CartLangParser.VarExprContext ctx) {
        String name = ctx.ID().getText();
        int line = ctx.getStart().getLine();

        if (!symbolTable.isDeclared(name)) {
            throw new RuntimeException("Erro Semântico na linha " + line + ": Variável '" + name + "' não declarada.");
        }
        return symbolTable.get(name).value;
    }

    @Override
    public Object visitIntExpr(CartLangParser.IntExprContext ctx) {
        return Integer.parseInt(ctx.INT().getText());
    }

    @Override
    public Object visitFloatExpr(CartLangParser.FloatExprContext ctx) {
        return Double.parseDouble(ctx.FLOAT().getText());
    }

    @Override
    public Object visitStringExpr(CartLangParser.StringExprContext ctx) {
        String text = ctx.STRING().getText();
        return text.substring(1, text.length() - 1); // remove as aspas
    }

    // --- Utilitários Semânticos ---

    private SymbolTable.Type parseType(String typeStr) {
        switch (typeStr) {
            case "int": return SymbolTable.Type.INT;
            case "float": return SymbolTable.Type.FLOAT;
            case "string": return SymbolTable.Type.STRING;
            default: return SymbolTable.Type.UNKNOWN;
        }
    }

    private void checkTypeMatch(SymbolTable.Type expected, Object value, int line) {
        if (value == null) return;
        boolean valid = false;
        if (expected == SymbolTable.Type.INT && value instanceof Integer) valid = true;
        if (expected == SymbolTable.Type.FLOAT && (value instanceof Double || value instanceof Float)) valid = true;
        if (expected == SymbolTable.Type.STRING && value instanceof String) valid = true;

        if (!valid) {
            throw new RuntimeException("Erro Semântico na linha " + line + ": Atribuição incompatível para o tipo " + expected);
        }
    }

    private boolean isTrue(Object val) {
        if (val instanceof Boolean) return (Boolean) val;
        if (val instanceof Number) return ((Number) val).doubleValue() != 0;
        return val != null;
    }
}