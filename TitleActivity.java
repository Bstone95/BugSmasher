package com.example.bugsmasher;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class TitleActivity extends Activity {
    Bitmap bmp;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
        bmp = null;
    }

    public class MyView extends View {
        boolean switchToGameScreen;
        Rect playGameButtonRect;
        Rect highScoreButtonRect;
        Paint buttonPaint;
        Paint textPaint;

        public MyView(Context context) {
            super(context);
            switchToGameScreen = false;
            buttonPaint = new Paint();
            buttonPaint.setColor(0xFFCCCCCC); // Light gray color
            textPaint = new Paint();
            textPaint.setColor(0xFF000000); // Black color
            textPaint.setTextSize(50); // Set the text size
            textPaint.setTextAlign(Paint.Align.CENTER);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            // Load image if needed
            if (bmp == null)
                bmp = BitmapFactory.decodeResource(getResources(), R.drawable.titlescreen);

            // Draw the title full screen
            Rect dstRect = new Rect();
            canvas.getClipBounds(dstRect);
            canvas.drawBitmap(bmp, null, dstRect, null);

            // Define button rectangles
            int buttonWidth = dstRect.width() / 2 - 40;
            int buttonHeight = 100;
            int buttonY = dstRect.height() - buttonHeight - 50;

            playGameButtonRect = new Rect(20, buttonY, 20 + buttonWidth, buttonY + buttonHeight);
            highScoreButtonRect = new Rect(dstRect.width() - buttonWidth - 20, buttonY, dstRect.width() - 20, buttonY + buttonHeight);

            // Draw buttons
            canvas.drawRect(playGameButtonRect, buttonPaint);
            canvas.drawRect(highScoreButtonRect, buttonPaint);

            // Draw button text
            canvas.drawText("Play Game", playGameButtonRect.centerX(), playGameButtonRect.centerY() + 15, textPaint);
            canvas.drawText("High Score", highScoreButtonRect.centerX(), highScoreButtonRect.centerY() + 15, textPaint);

            // On click switch to main (game) activity
            if (switchToGameScreen) {
                switchToGameScreen = false;
                startActivity(new Intent(TitleActivity.this, MainActivity.class));
                // Delete image (don't need it in memory if not using it)
                bmp = null;
            } else {
                invalidate();
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            // On click set flag to switch to main (game) activity
            if (event.getAction() == MotionEvent.ACTION_UP) {
                float x = event.getX();
                float y = event.getY();

                if (playGameButtonRect.contains((int) x, (int) y)) {
                    switchToGameScreen = true;
                } else if (highScoreButtonRect.contains((int) x, (int) y)) {
                    // Handle high score button click
                    startActivity(new Intent(TitleActivity.this, PrefsActivity.class));
                }
            }
            return true; // to indicate we have handled this event
        }
    }
}