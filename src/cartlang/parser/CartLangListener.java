// Generated from src/cartlang/grammar/CartLang.g4 by ANTLR 4.13.1
package cartlang.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CartLangParser}.
 */
public interface CartLangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CartLangParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(CartLangParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link CartLangParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(CartLangParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link CartLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(CartLangParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CartLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(CartLangParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CartLangParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(CartLangParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link CartLangParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(CartLangParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link CartLangParser#clienteDecl}.
	 * @param ctx the parse tree
	 */
	void enterClienteDecl(CartLangParser.ClienteDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link CartLangParser#clienteDecl}.
	 * @param ctx the parse tree
	 */
	void exitClienteDecl(CartLangParser.ClienteDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link CartLangParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(CartLangParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CartLangParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(CartLangParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CartLangParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(CartLangParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link CartLangParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(CartLangParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link CartLangParser#itemStmt}.
	 * @param ctx the parse tree
	 */
	void enterItemStmt(CartLangParser.ItemStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link CartLangParser#itemStmt}.
	 * @param ctx the parse tree
	 */
	void exitItemStmt(CartLangParser.ItemStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link CartLangParser#resumoStmt}.
	 * @param ctx the parse tree
	 */
	void enterResumoStmt(CartLangParser.ResumoStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link CartLangParser#resumoStmt}.
	 * @param ctx the parse tree
	 */
	void exitResumoStmt(CartLangParser.ResumoStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link CartLangParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(CartLangParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link CartLangParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(CartLangParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link CartLangParser#whileStmt}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(CartLangParser.WhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link CartLangParser#whileStmt}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(CartLangParser.WhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link CartLangParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(CartLangParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link CartLangParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(CartLangParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringExpr}
	 * labeled alternative in {@link CartLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterStringExpr(CartLangParser.StringExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringExpr}
	 * labeled alternative in {@link CartLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitStringExpr(CartLangParser.StringExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FloatExpr}
	 * labeled alternative in {@link CartLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFloatExpr(CartLangParser.FloatExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FloatExpr}
	 * labeled alternative in {@link CartLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFloatExpr(CartLangParser.FloatExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VarExpr}
	 * labeled alternative in {@link CartLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterVarExpr(CartLangParser.VarExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VarExpr}
	 * labeled alternative in {@link CartLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitVarExpr(CartLangParser.VarExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParensExpr}
	 * labeled alternative in {@link CartLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParensExpr(CartLangParser.ParensExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParensExpr}
	 * labeled alternative in {@link CartLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParensExpr(CartLangParser.ParensExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IntExpr}
	 * labeled alternative in {@link CartLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIntExpr(CartLangParser.IntExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IntExpr}
	 * labeled alternative in {@link CartLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIntExpr(CartLangParser.IntExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code OpExpr}
	 * labeled alternative in {@link CartLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterOpExpr(CartLangParser.OpExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code OpExpr}
	 * labeled alternative in {@link CartLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitOpExpr(CartLangParser.OpExprContext ctx);
}