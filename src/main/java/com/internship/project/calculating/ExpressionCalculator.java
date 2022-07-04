package com.internship.project.calculating;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class ExpressionCalculator {

    public String apply(String mathExpression) {
        Expression expression = new ExpressionBuilder(mathExpression).build();
        double result = expression.evaluate();
        return String.valueOf(result);
    }
}
