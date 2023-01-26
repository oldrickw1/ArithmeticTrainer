package com.oldrickweenink;

import android.app.Application;

public class myApplication extends Application {

    public static ExpressionGenerator expressionGenerator;

    public static void setUpExpressionGenerator() {
        expressionGenerator = new ExpressionGenerator();
    }
}
