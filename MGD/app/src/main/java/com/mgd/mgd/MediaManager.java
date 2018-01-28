package com.mgd.mgd;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;


public class MediaManager
{
    public final static MediaManager Instance = new MediaManager();

    private Context contextHolder;
    Vibrator vibrator;

    class VibratorInstance
    {
        boolean isActive = false;
        double vibrateElapsed = 0.0;
        double vibrateDuration = 0.5;
    }
    VibratorInstance vibratorInstance = new VibratorInstance();


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

    float defaultVolume = 0.1f;
    //float currentVolume = defaultVolume;
    float currentSoundVolume = defaultVolume;
    float currentMusicVolume = defaultVolume;

    public void Init(Context context)
    {
        //store all the sounds here
        soundMap.put("yaruta", new SoundHandler(R.raw.yaruta));
        soundMap.put("blast", new SoundHandler(R.raw.blast));
        contextHolder = context;

        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public void Update(double dt)
    {
        //Log.i("soundSize", Integer.toString(sound.size()));
/*        for (MediaPlayer s: sound) {
            if (!s.isPlaying())
            {
                //because released?
                sound.remove(s);
            }
        }*/

        if (vibratorInstance.isActive)
        {
            vibratorInstance.vibrateElapsed += dt;
            if (vibratorInstance.vibrateElapsed >= vibratorInstance.vibrateDuration)
            {
                vibratorInstance.vibrateElapsed = 0.0;
                StopVibrate();
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
                        MediaManager.Instance.RemoveSound(mp);
                    }
                });
                tempMediaPlayer.start();
                tempMediaPlayer.setVolume(currentSoundVolume,currentSoundVolume);
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
                tempMediaPlayer.setVolume(currentMusicVolume,currentMusicVolume);
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

    @Deprecated
    public void ToggleSound()
    {
        //if (currentVolume == 0.f)
        //    UnMuteSound();
        //else
        //    MuteSound(0);
    }

    public void MuteSound(int type)
    {
        if (type == 0)
        {
            //sound
            currentSoundVolume = 0.f;
            for (MediaPlayer s: sound) {
                s.setVolume(currentSoundVolume, currentSoundVolume);
            }
        }
        else
        {
            currentMusicVolume = 0.f;
            for (MediaPlayer s: music) {
                s.setVolume(currentMusicVolume, currentMusicVolume);
            }
        }

    }
    public void UnMuteSound(int type)
    {
        if (type == 0)
        {
            currentSoundVolume = defaultVolume;
            for (MediaPlayer s: sound) {
                s.setVolume(currentSoundVolume, currentSoundVolume);
            }
        }
        else
        {
            currentMusicVolume = defaultVolume;
            for (MediaPlayer s: music) {
                s.setVolume(currentMusicVolume, currentMusicVolume);
            }
        }
    }


    public void RemoveSound(MediaPlayer mp)
    {
        sound.remove(mp);
    }

    //param can add type
    public void RequestVibration()
    {
        long pattern[] = {0, 50, 0};

        if (Build.VERSION.SDK_INT >= 26)
        {
            vibrator.vibrate(VibrationEffect.createOneShot(150,10));
        }
        else
        {
            vibrator.vibrate(pattern, -1);
        }

        vibratorInstance.isActive = true;
        vibratorInstance.vibrateElapsed = 0.0;
    }

    void StopVibrate() {vibrator.cancel();}

    void RemoveAll() {
        StopVibrate();
        for (MediaPlayer s: sound) {
            s.stop();
            s.release();
        }
        for (MediaPlayer s: music) {
            s.stop();
            s.release();
        }
        sound.clear();
        music.clear();
    }
}
