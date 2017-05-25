package com.example.hansel.grammarquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.media.AudioManager;
import android.media.SoundPool;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class MainActivity extends AppCompatActivity {

    private static final int AUTHENTICATE = 1;
    private TextView textView;
    private int score = 0;
    private int menuPop;
    private SoundManager sounds;
    Twitter twitter = TwitterFactory.getSingleton();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text_view);
        textView.setMovementMethod(new ScrollingMovementMethod());
        sounds = new SoundManager(this);
        menuPop = sounds.addSound(R.raw.pop);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void authorise (View view) {
        sounds.play(menuPop);
        Intent intent = new Intent(this, Authenticate.class);
        startActivityForResult(intent, AUTHENTICATE);
    }

    public void playScreen (View view) {
        sounds.play(menuPop);
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }

    public void settingScreen (View view){
        sounds.play(menuPop);
        Intent intent = new Intent(this, ActivitySettings.class);
        startActivity(intent);
    }

    public void highScoreScreen (View view){
        sounds.play(menuPop);
        Intent intent = new Intent(getApplicationContext(), ScoresActivity.class);
        intent.putExtra("SCORE", score);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if (requestCode == AUTHENTICATE && resultCode == RESULT_OK) {
            Background.run(new Runnable() {
                @Override
                public void run() {
                    String token = data.getStringExtra("access token");
                    String secret = data.getStringExtra("access token secret");
                    AccessToken accessToken = new AccessToken(token, secret);
                    twitter.setOAuthAccessToken(accessToken);

                    Query query = new Query("@twitterapi");
                    QueryResult result;
                    try {
                        twitter.updateStatus("Download the GrammarQuiz app today!");
                        result = twitter.search(query);
                    } catch (final Exception e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });
                        return;
                    }

                    // convert tweets into text
                    final StringBuilder builder = new StringBuilder();
                    for (Status status : result.getTweets()) {
                        builder.append(status.getUser().getScreenName())
                                .append(" said ")
                                .append(status.getText())
                                .append("\n");
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(builder.toString().trim());
                        }
                    });
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, ActivitySettings.class);
            startActivity(intent);
        }
        if (id == R.id.action_highScores) {
            Intent intent = new Intent(getApplicationContext(), ScoresActivity.class);
            intent.putExtra("SCORE", score);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}