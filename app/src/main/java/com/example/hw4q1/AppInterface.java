package com.example.hw4q1;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AppInterface extends RelativeLayout
{

    //the size of the board
    private final int BOARD_SIZE = 3;

    //initial board text view
    private TextView[][] initialBoard;
    //grid for the initial board
    private GridLayout initialGrid;

    private int boardWidth;


    //constructor
    public AppInterface(Context context)
    {
        super(context);

        //for dimensions
        final int DP = (int)(getResources().getDisplayMetrics().density);

        //create initial board
        //get initial board from generator
        Generator generator = new Generator();
        char[][] initBoard = generator.generateInitialBoard();

        //create a grid layout inside relative layout
        initialGrid = new GridLayout(context);
        initialGrid.setId(GridLayout.generateViewId());
        initialGrid.setRowCount(BOARD_SIZE);
        initialGrid.setColumnCount(BOARD_SIZE);

        //setting layouts for the grid
        LayoutParams initialGridParams = new LayoutParams
                (
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
        initialGridParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        initialGrid.setLayoutParams(initialGridParams);

        //creating a goal board
        initialBoard = new TextView[BOARD_SIZE][BOARD_SIZE];

        for(int x = 0 ;  x < BOARD_SIZE ; x++)
        {
            for(int y =0  ; y < BOARD_SIZE ; y++)
            {
                initialBoard[x][y] = new TextView(context);
                initialBoard[x][y].setBackgroundColor(Color.parseColor("#87CEEB"));
                initialBoard[x][y].setText(" "); //Here
                initialBoard[x][y].setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                initialBoard[x][y].setGravity(Gravity.CENTER);
                initialBoard[x][y].setTextSize(TypedValue.COMPLEX_UNIT_SP,35 );
                initialBoard[x][y].setTextColor(Color.parseColor("#FF000000"));
                GridLayout.LayoutParams params = new GridLayout.LayoutParams( );
                params.width = 130*DP;
                params.height  = 130*DP;
                params.rowSpec = GridLayout.spec(x, 1);
                params.columnSpec = GridLayout.spec(y, 1);
                params.topMargin = params.bottomMargin = 1;
                params.leftMargin = params.rightMargin = 1;


                if (x == 0) params.topMargin = 400;
                initialBoard[x][y].setLayoutParams(params);
                initialGrid.addView(initialBoard[x][y]);

            }
        }





        //end initial board
        addView(initialGrid);

        //setting the background screen color
        setBackgroundColor(Color.parseColor("#DDDDDD"));

    } // end of constructor



    //method to display the current board to the screen
    public void drawCurrentBoard(char[][] currentBoard)
    {
        for(int x = 0 ;  x < BOARD_SIZE ; x++)
        {
            for (int y = 0; y < BOARD_SIZE; y++)
            {
                initialBoard[x][y].setText(""+currentBoard[x][y]);
            }
        }
    }

    //method that turns all the blocks to the red color , only if the board is completed
    public void endBoard(char[][] currentBoard)
    {
        for(int x = 0 ;  x < BOARD_SIZE ; x++)
        {
            for (int y = 0; y < BOARD_SIZE; y++)
            {
                initialBoard[x][y].setText(""+currentBoard[x][y]);
                initialBoard[x][y].setBackgroundColor(Color.parseColor("#FF0000"));
            }
        }
    }


}
