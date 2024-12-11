package com.example.bugsmasher;

import android.graphics.Bitmap;
import android.media.SoundPool;
import android.media.AudioManager;
import android.media.MediaPlayer;

public class Assets {
    static Bitmap background;
    static Bitmap foodbar;
    static Bitmap roach1;
    static Bitmap roach2;
    static Bitmap roach3;

    static Bitmap life;


            // States of the Game Screen
    enum GameState {
        GettingReady,	// play "get ready" sound and start timer, goto next state
        Starting,		// when 3 seconds have elapsed, goto next state
        Running, 		// play the game, when livesLeft == 0 goto next state
        GameEnding,	    // show game over message
        GameOver,		// game is over, wait for any Touch and go back to title activity screen
    };
    static GameState state;		// current state of the game
    static float gameTimer;	    // in seconds
    static int livesLeft;		// 0-3

    static SoundPool soundPool;
    static int sound_getready;
    static int sound_squish1,sound_squish2,sound_squish3;
    static int sound_thump;
    static int sound_victory;
    static int sound_eating;

    static int sound_game_over;

    static int current_score;

    static int high_score;

    public static MediaPlayer mp;


    static Bug[] bug; // try using an array of bugs instead of only 1 bug (so you can have more than 1 on screen at a time)
}
