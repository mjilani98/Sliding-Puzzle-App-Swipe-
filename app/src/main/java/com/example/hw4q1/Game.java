package com.example.hw4q1;

public class Game {
    private final char BLANK = ' ';
    private char[][] currentBoard;
    private char[][] goalBoard;
    private int x;
    private int y;

    //public constructor
    public Game()
    {
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


    //method returns the current board
    public char[][] getCurrentBoard()
    {
        return currentBoard;
    }

    public char[][] getGoalBoard()
    {
        return goalBoard;
    }


    public boolean checkCompleted()
    {
        for(int x = 0 ; x<currentBoard.length ; x++)
        {
            for(int y=0 ; y<currentBoard.length; y++)
            {
                if(currentBoard[x][y] != goalBoard[x][y])
                    return false;
            }
        }

        return true;
    }


    //method swaps two blocks together
    public void swap(int startX , int endX , int startY, int endY)
    {
        if (check(startX,endX,startY,endY))
        {
            char block1 = currentBoard[startX-1][startY-1] ;
            char block2 = currentBoard[endX-1][endY-1] ;

            if( Character.isDigit(block1))
            {
                char temp = currentBoard[startX - 1][startY - 1];
                currentBoard[startX - 1][startY - 1] = currentBoard[endX - 1][endY - 1];
                currentBoard[endX - 1][endY - 1] = temp;
            }
        }
    }


    //method that checks if the swap is valid - should be private
    private boolean check(int startX , int endX , int startY, int endY)
    {
        // check locations u,v and x,y are swappable - has to be true
        if(isSwappable(startX,endX,startY,endY))
        {
            // if u,v and x,y both have numbers then no -  has to be false
            if(!bothNumbers(startX,endX,startY,endY))
            {
                // if u,v and x,y are horizontal neighbors then yes
                //if u,v and x,y are vertical neighbors then yes
                if( isHorizontalNeighbors(startX,endX,startY,endY) || isVerticalNeighbors(startX,endX,startY,endY) )
                {
                    return true;
                }

            }

        }
        else
            return false;

        return false;
    }

    //method returns weather is the swap is valid positions or not
    //the difference between swaps has to in range of -1 and 1
    private boolean isSwappable(int startX , int endX , int startY, int endY)
    {
        int differenceX = startX - endX;
        int differenceY = startY - endY;

        if( (differenceX < -1 || differenceX > 1) || (differenceY < -1 || differenceY > 1) )
            return false;
        else
            return  true;
    }

    //method checks if the swaps both have numbers
    //returns true if they have both numbers
    private boolean bothNumbers(int startX , int endX , int startY, int endY)
    {
        //get the values from the board based on the swap values
        char block1 = currentBoard[startX-1][startY-1]; // subtract one , values are passed as 1, 2, 3
        char block2 = currentBoard[endX-1][endY-1]; // subtract one , values are passed as 1, 2, 3

        //check if both blocks have numbers
        if(Character.isDigit(block1) && Character.isDigit(block2))
            return true;
        else
            return false;

    }

    //method checks if swaps is within horizontal neighbors
    //the difference between the start row and end row should be zero
    //since they are in the same row
    private boolean isHorizontalNeighbors(int startX , int endX , int startY, int endY)
    {
        return (startX == endX) && (Math.abs(startY - endY) == 1);
    }

    //method check is swap is within vertical neighbors
    private boolean isVerticalNeighbors(int startX , int endX , int startY, int endY)
    {
      return (startY == endY) && (Math.abs(startX - endX )==1);
    }


}
