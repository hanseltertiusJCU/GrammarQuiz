package com.example.hansel.grammarquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.media.AudioManager;
import android.media.SoundPool;
import android.widget.Toast;

public class ActivitySettings extends AppCompatActivity{

    private SoundManager sounds;
    private int menuPop;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        sounds = new SoundManager(this);
        menuPop = sounds.addSound(R.raw.pop);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    public void accelarometer (View view){
        sounds.play(menuPop);
        Intent intent = new Intent(this, Accelarometer.class);
        startActivity(intent);
    }

    public void mainMenu (View view){
        sounds.play(menuPop);
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_mainMenu) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if (id == R.id.action_highScores) {
            Intent intent = new Intent(getApplicationContext(), ScoresActivity.class);
            intent.putExtra("SCORE", score);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void about (View view){
        Context context = getApplicationContext();
        CharSequence text = "This quiz is about honing the grammar skills"
                + System.getProperty("line.separator") + "This exercise is used for students who undergo the year 7-9"
                + System.getProperty("line.separator") + "If you want to know how to play just click How to Play button";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void tutorial (View view){
        Context context = getApplicationContext();
        CharSequence text = "Take time to read the questions"
                + System.getProperty("line.separator") + "Correct answers gain 1 point each"
                + System.getProperty("line.separator") + "If you want to know what the quiz is about just click About button";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

}
