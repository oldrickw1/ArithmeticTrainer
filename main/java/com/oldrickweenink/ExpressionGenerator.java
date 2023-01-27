package com.oldrickweenink;

import java.util.Random;

public class ExpressionGenerator {
    Random random = new Random();

    public Expression getBeginnerExpression() {
        int a = random.nextInt(10);
        int b = random.nextInt(10);
        int ranNum = random.nextInt(2);
        switch (ranNum) {
            case 0:
                return new Expression(a + " + " + b, a+b);
            case 1:
                return new Expression(a + " - " + b, a-b);
        }
        return null;
    }

    public Expression getIntermediateExpression() {
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        int c = random.nextInt(21);
        int ranNum = random.nextInt(3);
        switch (ranNum) {
            case 0:
                return new Expression(a + " + " + b, a+b);
            case 1:
                return new Expression(a + " - " + b, a-b);
            case 2:
                return new Expression(a + " + " + b + "-" + c, a+b-c);
        }
        return null;
    }

    public Expression getAdvancedExpression() {
        return null;
    }

//    public Expression getIntermediateExpression() {
//    }

//    public Expression getAdvancedExpression() {
//
//    }



}
