package com.arnau478.lox;

class AstPrinter implements Expr.Visitor<String> {
	String print(Expr expr) {
		return expr.accept(this);
	}
	
	@Override
	public String visitBinaryExpr(Expr.Binary expr) {
		return parenthise(expr.operator.lexeme, expr.left, expr.right);
	}
	
	@Override
	public String visitGroupingExpr(Expr.Grouping expr) {
		return parenthise("group", expr.expression);
	}
	
	@Override
	public String visitLiteralExpr(Expr.Literal expr) {
		if(expr.value == null) return "nil";
		return expr.value.toString();
	}
	
	@Override
	public String visitUnaryExpr(Expr.Unary expr) {
		return parenthise(expr.operator.lexeme, expr.right);
	}
	
	private String parenthise(String name, Expr... exprs) {
		StringBuilder builder = new StringBuilder();
		
		builder.append("(").append(name);
		for(Expr expr : exprs) {
			builder.append(" ");
			builder.append(expr.accept(this));
		}
		builder.append(")");
		
		return builder.toString();
	}
}
