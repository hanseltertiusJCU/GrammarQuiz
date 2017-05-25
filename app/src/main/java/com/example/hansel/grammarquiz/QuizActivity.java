package com.example.hansel.grammarquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;
import android.media.AudioManager;
import android.media.SoundPool;


public class QuizActivity extends Activity{

    private QuestionLibrary mQuestionLibrary = new QuestionLibrary();

    private TextView mScoreView;
    private TextView mQuestionView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private SoundManager sounds;
    private int sndCorrect;
    private int sndWrong;
    private int menuPop;


    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;

    MediaPlayer bkgrdmsc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mScoreView = (TextView)findViewById(R.id.score);
        mQuestionView = (TextView)findViewById(R.id.question);
        mButtonChoice1 = (Button) findViewById(R.id.choice1);
        mButtonChoice2 = (Button) findViewById(R.id.choice2);
        mButtonChoice3 = (Button) findViewById(R.id.choice3);
        sounds = new SoundManager(this);
        sndCorrect = sounds.addSound(R.raw.correct);
        sndWrong = sounds.addSound(R.raw.wrong);
        menuPop = sounds.addSound(R.raw.pop);

        bkgrdmsc = MediaPlayer.create(QuizActivity.this, R.raw.backgroundmusic);
        bkgrdmsc.setLooping(true);
        bkgrdmsc.start();

        updateQuestion();

        //Start of Button Listener for Button1
        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mButtonChoice1.getText() == mAnswer) {
                    mScore = mScore + 1;
                    updateScore(mScore);
                    sounds.play(sndCorrect);

                    if (mQuestionNumber == QuestionLibrary.mQuestions.length) {
                        Intent intent = new Intent(getApplicationContext(), ScoresActivity.class);
                        intent.putExtra("SCORE", mScore);
                        startActivity(intent);
                    } else {
                        Toast.makeText(QuizActivity.this, "correct", Toast.LENGTH_SHORT).show();
                        updateQuestion();
                    }
                }
                    // If the user's answer is wrong
                        else {
                            sounds.play(sndWrong);
                            if (mQuestionNumber == QuestionLibrary.mQuestions.length){
                                Intent intent = new Intent(getApplicationContext(), ScoresActivity.class);
                                intent.putExtra("SCORE", mScore);
                                startActivity(intent);
                            } else{
                                Toast.makeText(QuizActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                                updateQuestion();
                            }
                        }
                }

        });
        //End of Button Listener for Button1

        //Start of Button Listener for Button2
        mButtonChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mButtonChoice2.getText() == mAnswer) {
                    mScore = mScore + 1;
                    updateScore(mScore);
                    sounds.play(sndCorrect);

                    if (mQuestionNumber == QuestionLibrary.mQuestions.length) {
                        Intent intent = new Intent(getApplicationContext(), ScoresActivity.class);
                        intent.putExtra("SCORE", mScore);
                        startActivity(intent);
                    } else {
                        Toast.makeText(QuizActivity.this, "correct", Toast.LENGTH_SHORT).show();
                        updateQuestion();
                    }
                }
                // If the user's answer is wrong
                else {
                    sounds.play(sndWrong);
                    if (mQuestionNumber == QuestionLibrary.mQuestions.length){
                        Intent intent = new Intent(getApplicationContext(), ScoresActivity.class);
                        intent.putExtra("SCORE", mScore);
                        startActivity(intent);
                    } else{
                        Toast.makeText(QuizActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                        updateQuestion();
                    }
                }
            }

        });
        //End of Button Listener for Button2

        //Start of Button Listener for Button3
        mButtonChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mButtonChoice3.getText() == mAnswer) {
                    mScore = mScore + 1;
                    updateScore(mScore);
                    sounds.play(sndCorrect);

                    if (mQuestionNumber == QuestionLibrary.mQuestions.length) {
                        Intent intent = new Intent(getApplicationContext(), ScoresActivity.class);
                        intent.putExtra("SCORE", mScore);
                        startActivity(intent);
                    } else {
                        Toast.makeText(QuizActivity.this, "correct", Toast.LENGTH_SHORT).show();
                        updateQuestion();
                    }
                }
                // If the user's answer is wrong
                else {
                    sounds.play(sndWrong);
                    if (mQuestionNumber == QuestionLibrary.mQuestions.length){
                        Intent intent = new Intent(getApplicationContext(), ScoresActivity.class);
                        intent.putExtra("SCORE", mScore);
                        startActivity(intent);
                    } else{
                        Toast.makeText(QuizActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                        updateQuestion();
                    }
                }
            }

        });
        //End of Button Listener for Button3

    }

    @Override
    protected void onPause(){
        super.onPause();
        bkgrdmsc.release();
        finish();
    }

    private void updateQuestion(){
        mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
        mButtonChoice1.setText(mQuestionLibrary.getChoice1(mQuestionNumber));
        mButtonChoice2.setText(mQuestionLibrary.getChoice2(mQuestionNumber));
        mButtonChoice3.setText(mQuestionLibrary.getChoice3(mQuestionNumber));

        mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
        mQuestionNumber++;
    }

    private void updateScore(int point){
        mScoreView.setText("" + mScore);
    }


    public void quit (View view){
        sounds.play(menuPop);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
