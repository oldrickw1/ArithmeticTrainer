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
        int ranNum = random.nextInt(4);
        switch (ranNum) {
            case 0:
                return new Expression(a + " + " + b, a+b);
            case 1:
                return new Expression(a + " - " + b, a-b);
            case 2:
                return new Expression(a + " + " + b + " - " + c, a+b-c);
            case 3:
                return new Expression(a + " - " + b + " + " + c, a-b+c);
        }
        return null;
    }

    public Expression getAdvancedExpression() {
        int a = random.nextInt(100);
        int b = random.nextInt(100);
        int c = random.nextInt(100);
        int d = random.nextInt(100);
        int ranNum = random.nextInt(4);
        switch (ranNum) {
            case 0:
                return new Expression(a + " + " + b + " + " + c + " + " + d, a+b+c+d);
            case 1:
                return new Expression(a + " - " + b + " + " + c + " - " + d, a-b+c-d);
            case 2:
                return new Expression(a + " - " + b + " - " + c + " + " + d, a-b-c+d);
            case 3:
                return new Expression(a + " + (" + b + " x " + c + ")", a+(b*c));
        }
        return null;
    }
}
