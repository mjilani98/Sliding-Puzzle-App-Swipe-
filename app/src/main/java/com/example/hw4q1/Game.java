package com.example.hw4q1;

public class Game {
    private final char BLANK = ' ';
    private char[][] currentBoard;
    private char[][] goalBoard;
    private int x;
    private int y;

    //public constructor
    public Game() {
        //create a generator for the boards
        Generator generator = new Generator();

        //getting the goal and current board
        currentBoard = generator.generateInitialBoard();
        goalBoard = generator.generateGoalBoard();

        //find the location of the x , y
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                //if the current spot is BLANK, then it is the location for x , y
                if (currentBoard[i][j] == BLANK) {
                    x = i;
                    y = j;
                }
            }
        }

    }

    //up , moves the blank block up
    public void up()
    {
        if(x != 0 )
        {
            currentBoard[x][y] = currentBoard[x - 1][y];
            currentBoard[x - 1][y] = BLANK;
            x-=1;
        }
    }

    //down, moves the blank block down
    public void down()
    {
        if(x!=2 )
        {
            currentBoard[x][y] = currentBoard[x + 1][y];
            currentBoard[x + 1][y] = BLANK;
            x+=1;
        }
    }

    //left, moves the blank block left
    public void left()
    {
        if(y!=0 )
        {
            currentBoard[x][y] = currentBoard[x][y-1];
            currentBoard[x][y-1] = BLANK;
            y-=1;
        }
    }

    //right, moves the blank block right
    public void right()
    {
        if(y!=2 )
        {
            currentBoard[x][y] = currentBoard[x][y+1];
            currentBoard[x][y+1] = BLANK;
            y+=1;
        }
    }

    //method that checks if the current board and the goal board are the same
    public boolean checkWin()
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                //if theres one block in the current does not match the goal
                //theres no win yet
                if(currentBoard[i][j] != goalBoard[i][j])
                {
                    return false;
                }
            }
        }

        return true;
    }



    //method returns the current board
    public char[][] getCurrentBoard()
    {
        return currentBoard;
    }

    public char[][] getGoalBoard()
    {
        return goalBoard;
    }

}
