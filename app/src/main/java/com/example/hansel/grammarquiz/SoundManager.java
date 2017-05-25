package com.example.hansel.grammarquiz;

import android.content.Context;
import android.media.SoundPool;

public class SoundManager {
    private Context context;
    private SoundPool pool;
    public SoundManager(Context context){
        this.context = context;
        SoundPool.Builder builder = new SoundPool.Builder();
        builder.setMaxStreams(10);
        pool = builder.build();


    }

    public int addSound(int resourceID){
        return pool.load(context, resourceID,1);
    }

    public void play(int soundID){
        pool.play(soundID, 1, 1, 1, 0, 1);
    }
}