package com.oldrickweenink;

import java.util.Random;

public class ExpressionGenerator {

    public Expression getBeginnerExpression() {
        Random random = new Random();
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

//    public Expression getIntermediateExpression() {
//    }

//    public Expression getAdvancedExpression() {
//
//    }



}
