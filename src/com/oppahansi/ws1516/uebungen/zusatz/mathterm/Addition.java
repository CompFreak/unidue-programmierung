/**
 * Created by:
 * Institut für Informatik und Wirtschaftsinformatik, Universität Duisburg-Essen
 *
 * For learning purpose only.
 *
 * Solved/Edited by Martin. Possible solution - there are other ways to
 * solve these tasks.
 *
 * Lange Nacht der Programmierung
 */

package com.oppahansi.ws1516.uebungen.zusatz.mathterm;

public class Addition extends BinaryOperation {

	public Addition(Expression leftExpression, Expression rightExpression) {
		super(leftExpression, rightExpression);
	}

	@Override
	public int evaluate() {
		return leftExpression.evaluate() + rightExpression.evaluate();
	}

}
