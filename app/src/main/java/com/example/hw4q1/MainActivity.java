package com.example.hw4q1;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

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

    //class that handles the swipes events
    private class SwipeDetecotr extends GestureDetector.SimpleOnGestureListener
    {
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY)
        {
            //get x , y coordinates of where swipe started
            float startX = event1.getX();
            float startY = event1.getY();

            //get x , y coordinates of where the swipe ended
            float endX = event2.getX();
            float endY = event2.getY();

            System.out.println("X started at  : " +(int)startX);
            System.out.println("X ended at : " +(int)endX);

            System.out.println("Y started at  : " + (int)startY);
            System.out.println("Y ended at : " +(int)endY);

            return true;
        }
    }
}