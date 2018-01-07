package com.mgd.mgd;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;


public class MediaManager
{
    public final static MediaManager Instance = new MediaManager();

    private Context contextHolder;

    //Storage
    class SoundHandler
    {
        public int res;
        SoundHandler(int res) {this.res = res;}
    }
    Map <String, SoundHandler > soundMap = new HashMap<>();

    //Logic
    Vector<MediaPlayer> sound = new Vector<>();
    Vector<MediaPlayer> music = new Vector<>();


    public void Init(Context context)
    {
        //store all the sounds here
        soundMap.put("yaruta", new SoundHandler(R.raw.yaruta));
        soundMap.put("blast", new SoundHandler(R.raw.blast));
        contextHolder = context;
    }

    public void Update(double dt)
    {
        Log.i("soundSize", Integer.toString(sound.size()));
        for (MediaPlayer s: sound) {
            if (s == null)
            {
                //because released?
                sound.remove(s);
            }
        }
    }

    //can add more params to edit the file
    public boolean PlaySound(String name)
    {
        if (!soundMap.containsKey(name))
            return false;

        MediaPlayer tempMediaPlayer = MediaPlayer.create(contextHolder, soundMap.get(name).res);
        tempMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp.release();
            }
        });
        tempMediaPlayer.start();
        sound.add(tempMediaPlayer);

        return true;
    }

    public boolean PlaySound(int resId)
    {
        for (Map.Entry<String, SoundHandler> sh: soundMap.entrySet()) {
            if (sh.getValue().res == resId)
            {
                MediaPlayer tempMediaPlayer = MediaPlayer.create(contextHolder, sh.getValue().res);
                tempMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.reset();
                        mp.release();
                    }
                });
                tempMediaPlayer.start();
                sound.add(tempMediaPlayer);

                return true;
            }
        }

        return false;
    }

    public boolean PlayBGMusic(int resId, boolean loop)
    {
        for (Map.Entry<String, SoundHandler> sh: soundMap.entrySet()) {
            if (sh.getValue().res == resId)
            {
                MediaPlayer tempMediaPlayer = MediaPlayer.create(contextHolder, sh.getValue().res);
                tempMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.reset();
                        mp.release();
                    }
                });
                tempMediaPlayer.setLooping(true);
                tempMediaPlayer.start();
                music.add(tempMediaPlayer);

                return true;
            }
        }

        return false;
    }

    public void ClearSound()
    {
        for (MediaPlayer s: sound) {
            s.reset();
            s.release();
        }
        sound.clear();

        for (MediaPlayer s: music) {
            s.reset();
            s.release();
        }
        music.clear();
    }

}
