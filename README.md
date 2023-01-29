# ArithmeticTrainer

Simple Android application that promts the user to solve arithmetic expressions with increasing difficulty. Includes a level-system, life-system and tracks highscore by storing the value as persistent data in Shared Preferences.

## MainActivity
Here is where the quizzing happens. 

## GameOver
Displays the "game over" screen, user can decide to try again here. 

## Expression
Simple data structure that stores an expression and the evaluation of said expression.

## ExpressionGenerator
Class with methods to semi-randomly generate expressions for different difficulty levels. 
This could be made more interesting by actually making random expressions instead of using static frames with placeholders.

