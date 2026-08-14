package cartlang.semantic;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cartlang.parser.*;

public class CartVisitor extends CartLangBaseVisitor<Object> {

    private final SymbolTable symbolTable = new SymbolTable();

    // Estrutura para os comandos de domínio
    private static class ItemCompra {

    }

    private static class ItemCarrinho {
        String nome;
        double valor;

        ItemCarrinho(String nome, double valor) {
            this.nome = nome;
            this.valor = valor;
        }
    }

    // Estrutura para os dados do cliente e seu carrinho individual
    private static class CarrinhoCliente {
        String nomeCliente;
        List<ItemCarrinho> itens = new ArrayList<>();

        CarrinhoCliente(String nomeCliente) {
            this.nomeCliente = nomeCliente;
        }
    }

    private final List<ItemCompra> carrinho = new ArrayList<>();
    private Map<String, CarrinhoCliente> clientes = new HashMap<>();


    // --- Declaração e Atribuição ---

    @Override
    public Object visitClienteDecl(CartLangParser.ClienteDeclContext ctx) {
        String idCliente = ctx.ID().getText();
        Object valExpr = visit(ctx.expr());
        
        String nomeFormatado = (valExpr != null) ? valExpr.toString().replace("\"", "") : idCliente;

        // Cria o carrinho vinculado ao ID
        clientes.put(idCliente, new CarrinhoCliente(nomeFormatado));
        System.out.println("[DSL Log] Cliente cadastrado: ID='" + idCliente + "', Nome='" + nomeFormatado + "'");
        return null;
    }
    

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
        String idCliente = ctx.ID().getText();

        if (!clientes.containsKey(idCliente)) {
            throw new RuntimeException("Erro Semântico: Cliente com ID '" + idCliente + "' não foi declarado.");
        }

        Object objNome = visit(ctx.expr(0));
        Object objValor = visit(ctx.expr(1));

        String nomeProd = (objNome != null) ? objNome.toString().replace("\"", "") : "Produto sem nome";
        double valorProd = Double.parseDouble(objValor.toString());

        // Adiciona o item na lista do cliente correspondente
        CarrinhoCliente carrinho = clientes.get(idCliente);
        carrinho.itens.add(new ItemCarrinho(nomeProd, valorProd));

        System.out.println("[DSL Log] Adicionado para (" + idCliente + "): " + nomeProd + " -> R$ " + String.format("%.2f", valorProd));
        return null;
    }

    @Override
    public Object visitResumoStmt(CartLangParser.ResumoStmtContext ctx) {
        // CASO 1: resumo de um cliente específico (ex: resumo c1;)
        if (ctx.ID() != null) {
            String idCliente = ctx.ID().getText();

            if (!clientes.containsKey(idCliente)) {
                throw new RuntimeException("Erro Semântico: Impossível gerar resumo. Cliente '" + idCliente + "' não existe.");
            }

            imprimirResumoCliente(idCliente, clientes.get(idCliente));
        } 
        // CASO 2: resumo geral de TODOS os clientes (ex: resumo;)
        else {
            if (clientes.isEmpty()) {
                System.out.println("\n[SISTEMA]: Nenhum cliente/carrinho cadastrado no sistema.");
                return null;
            }

            System.out.println("\n==========================================");
            System.out.println("   RESUMO GERAL DE TODOS OS CLIENTES      ");
            System.out.println("==========================================");

            double totalGeral = 0.0;

            for (Map.Entry<String, CarrinhoCliente> entry : clientes.entrySet()) {
                String id = entry.getKey();
                CarrinhoCliente carrinho = entry.getValue();

                System.out.println("\nCLIENTE: " + carrinho.nomeCliente + " (ID: " + id + ")");
                System.out.println("------------------------------------------");

                double subtotal = 0.0;
                if (carrinho.itens.isEmpty()) {
                    System.out.println("  (Carrinho vazio)");
                } else {
                    for (ItemCarrinho item : carrinho.itens) {
                        System.out.printf("- %-25s R$ %8.2f\n", item.nome, item.valor);
                        subtotal += item.valor;
                    }
                }
                System.out.printf("SUBTOTAL:                    R$ %8.2f\n", subtotal);
                totalGeral += subtotal;
            }

            System.out.println("==========================================");
            System.out.printf("TOTAL GERAL DE TODAS AS COMPRAS: R$ %8.2f\n", totalGeral);
            System.out.println("==========================================\n");
        }

        return null;
    }

     // Método auxiliar para imprimir o resumo de um único cliente
     private void imprimirResumoCliente(String idCliente, CarrinhoCliente carrinho) {
        double total = 0.0;

        System.out.println("\n==========================================");
        System.out.println("        RESUMO DO CARRINHO                ");
        System.out.println("==========================================");
        System.out.println("CLIENTE: " + carrinho.nomeCliente + " (ID: " + idCliente + ")");
        System.out.println("------------------------------------------");

        if (carrinho.itens.isEmpty()) {
            System.out.println("  (Carrinho vazio)");
        } else {
            for (ItemCarrinho item : carrinho.itens) {
                System.out.printf("- %-25s R$ %8.2f\n", item.nome, item.valor);
                total += item.valor;
            }
        }

            System.out.println("------------------------------------------");
            System.out.printf("TOTAL ACUMULADO:              R$ %8.2f\n", total);
            System.out.println("==========================================\n");
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