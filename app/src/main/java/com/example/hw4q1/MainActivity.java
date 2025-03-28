package com.example.hw4q1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //Initialize an app interface
    private AppInterface appInterface;

    //Initialize a game
    private Game game;

    //Initialize gesture detector
    private GestureDetector gestureDetector;

    // the board coordinates on the screen , (manually calculated)
    private int startBoardX = 150;
    private int endBoardX = 930;
    //
    private int startBoardY = 400;
    private int endBoardY = 1185;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //create a new game
        game = new Game();

        //create app interface
        appInterface = new AppInterface(this );

        //display app interface
        setContentView(appInterface);

        //draw board on the screen
        appInterface.drawCurrentBoard(game.getCurrentBoard());

        //create a swipe detector
        SwipeDetecotr detector = new SwipeDetecotr();

        //create a gesture detector
        gestureDetector = new GestureDetector(this, detector);

    }

    public boolean onTouchEvent(MotionEvent event)
    {
        gestureDetector.onTouchEvent(event);

        return true;
    }

    //method returns screen height
    public int screenHeight()
    {
        int screenHeight = getWindowManager().getCurrentWindowMetrics().getBounds().height();

        return screenHeight;
    }

    //method returns screen width
    public int screenWidth()
    {
        int screenWidth = getWindowManager().getCurrentWindowMetrics().getBounds().width();

        return screenWidth;
    }

    //class that handles the swipes events
    private class SwipeDetecotr extends GestureDetector.SimpleOnGestureListener
    {


        //row and columns
        private int startRow , endRow, startColumn, endColumn;

        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY)
        {
            //check if the game is completed before enabling the fling
            if(game.checkCompleted())
                return false;

            //get x , y coordinates of where swipe started
            float startX = event1.getX();
            float startY = event1.getY();

            //get x , y coordinates of where the swipe ended
            float endX = event2.getX();
            float endY = event2.getY();

            //check if the event is inside the board
            if(inSideEvent((int)startX,(int)startY,(int)endX,(int)endY))
            {
                //get the start row
                startRow = getRow(event1);

                //get the end row
                endRow = getRow(event2);

                //get the start column
                startColumn = getColumn(event1);

                //get the end column
                endColumn = getColumn(event2);

                //swap
                game.swap(startRow,endRow,startColumn,endColumn);

                //display current board
                appInterface.drawCurrentBoard(game.getCurrentBoard());

                //check if the board is completed
                if(game.checkCompleted())
                {
                    //turn the board to red
                    appInterface.endBoard(game.getCurrentBoard());
                }

            }


            return true;
        }

        //method that checks if the event happened inside the range of the board;
        private boolean inSideEvent(int startx , int starty , int endx , int endy)
        {
            //if the event coordinates is withing the board coordimjinates , return true
            if( ( startx>= startBoardX && endx <= endBoardX) && (starty>=startBoardY && endy<=endBoardY) )
            {
                if(!(starty > endBoardY))
                    return true;
                else
                    return false;

            }

            return false;
        }

        //method that returns the start row and end row
        private int getRow(MotionEvent event)
        {
            int row = 0;

            if(event.getY() <= 655) // event is within row 1 range
                row = 1;
            else if (event.getY() <= 925) // event is within row 2 range
            {
                row = 2;
            }
            else if(event.getY() <= 1185) // event is within row 3 range
            {
                row = 3;
            }

            return row;
        }

        //method that return the start column and end column
        private int getColumn(MotionEvent event)
        {
            int column =0;

            if(event.getX() <= 405)  // event is within column 1 range
                column = 1;
            else if(event.getX() <= 670) // event is within column 2 range
            {
                column =2;
            }
            else if(event.getX() <= 930) // event is within column 3 range
            {
                column = 3;
            }

            return column;
        }



    }
}