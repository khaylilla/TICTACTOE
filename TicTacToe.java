package com.tictactoe;

import java.util.Scanner;

class Main {

    public static void main(String[] args) {

        int n = 3;

        char[][] board = new char[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                board[i][j] = '-';
            }
        }

//Create a Scanner for asking the players for their names
        Scanner in = new Scanner(System.in);
        System.out.println("Tic Tac Toe is ready for play!\n");
        System.out.print("What is your name?, player 1: ");
        String p1 = in.nextLine();
        System.out.print(" What is your name?, player 2:");
        String p2 = in.nextLine();

        boolean player1 = true;

        //Create a gameEnded boolean and use it as the condition in the while loop
        boolean gameEnded = false;
        while(!gameEnded) {

//Draw the board
            drawBoard(board);

//Print whose turn it is
            if(player1) {
                System.out.println(p1 + "'s Turn (x):");
            } else {
                System.out.println(p2 + "'s Turn (o):");
            }

            char c = '-';
            if(player1) {
                c = 'x';
            } else {
                c = 'o';
            }


            int row = 3;
            int col = 3;

            while(true) {

                System.out.print("Enter a row number: ");
                row = in.nextInt();
                System.out.print("Enter a column number: ");
                col = in.nextInt();

                if(row < 0 || col < 0 || row >= n || col >= n) {
                    System.out.println("This position is off the bounds of the board! Try again.");

                } else if(board[row][col] != '-') {
                    System.out.println("Someone has already made a move at this position! Try again.");

                } else {
                    break;
                }
            }

            board[row][col] = c;

//Check to see if either player has won
            if(playerHasWon(board) == 'x') {
                System.out.println(p1 + " has won!");
                gameEnded = true;
            } else if(playerHasWon(board) == 'o') {
                System.out.println(p2 + " has won!");
                gameEnded = true;
            } else {

//If neither player has won, check to see if there has been a tie (if the board is full)
                if(boardIsFull(board)) {
                    System.out.println("It's a tie!");
                    gameEnded = true;
                } else {

//If player1 is true, make it false, and vice versa; this way, the players alternate each turn
                    player1 = !player1;
                }
            }
        }

//Draw the board at the end of the game
        drawBoard(board);
    }

    //Make a function to draw the tic tac toe board
    public static void drawBoard(char[][] board) {
        System.out.println("Board:");

        for(int i = 0; i < board.length; i++) {

//The inner for loop prints out each row of the board
            for(int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
            }

//This print statement makes a new line so that each row is on a separate line
            System.out.println();
        }
    }

    //Make a function to see if someone has won and return the winning char
    public static char playerHasWon(char[][] board) {

        for(int i = 0; i < board.length; i++) {

            boolean inARow = true;

            char value = board[i][0];

            if(value == '-') {
                inARow = false;

            } else {
                for(int j = 1; j < board[i].length; j++) {
                    if(board[i][j] != value) {
                        inARow = false;
                        break;
                    }
                }
            }
            if(inARow) {
                return value;
            }
        }

// We can use the same construction to check each column
        for(int j = 0; j < board[0].length; j++) {
            boolean inACol = true;
            char value = board[0][j];

            if(value == '-') {
                inACol = false;
            } else {

                for(int i = 1; i < board.length; i++) {
                    if(board[i][j] != value) {
                        inACol = false;
                        break;
                    }
                }
            }

            if(inACol) {

                return value;
            }
        }

// We can use a similar construction to check the diagonals
// Check the diagonal going from upper left to bottom right: [0][0], [1][1], [2][2]...
        boolean inADiag1 = true;
        char value1 = board[0][0];
        if(value1 == '-') {
            inADiag1 = false;
        } else {
            for(int i = 1; i < board.length; i++) {
                if(board[i][i] != value1) {
                    inADiag1 = false;
                    break;
                }
            }
        }

        if(inADiag1) {
            return value1;
        }

// Check the diagonal going from upper right to bottom left: [0][n-1], [1][n-2], [2][n-3]...
        boolean inADiag2 = true;
        char value2 = board[0][board.length-1];

        if(value2 == '-') {
            inADiag2 = false;
        } else {
            for(int i = 1; i < board.length; i++) {
                if(board[i][board.length-1-i] != value2) {
                    inADiag2 = false;
                    break;
                }
            }
        }

        if(inADiag2) {
            return value2;
        }

//Otherwise nobody has not won yet
        return ' ';
    }

    //Make a function to check if all of the positions on the board have been filled
    public static boolean boardIsFull(char[][] board) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}


