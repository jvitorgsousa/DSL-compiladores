// Generated from src/cartlang/grammar/CartLang.g4 by ANTLR 4.13.1
package cartlang.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CartLangParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CartLangVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CartLangParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(CartLangParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link CartLangParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(CartLangParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CartLangParser#varDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(CartLangParser.VarDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link CartLangParser#clienteDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClienteDecl(CartLangParser.ClienteDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link CartLangParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(CartLangParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CartLangParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(CartLangParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link CartLangParser#itemStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitItemStmt(CartLangParser.ItemStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link CartLangParser#resumoStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResumoStmt(CartLangParser.ResumoStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link CartLangParser#ifStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmt(CartLangParser.IfStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link CartLangParser#whileStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStmt(CartLangParser.WhileStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link CartLangParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(CartLangParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringExpr}
	 * labeled alternative in {@link CartLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringExpr(CartLangParser.StringExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FloatExpr}
	 * labeled alternative in {@link CartLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatExpr(CartLangParser.FloatExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VarExpr}
	 * labeled alternative in {@link CartLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarExpr(CartLangParser.VarExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParensExpr}
	 * labeled alternative in {@link CartLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParensExpr(CartLangParser.ParensExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IntExpr}
	 * labeled alternative in {@link CartLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntExpr(CartLangParser.IntExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OpExpr}
	 * labeled alternative in {@link CartLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpExpr(CartLangParser.OpExprContext ctx);
}