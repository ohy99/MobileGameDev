package com.mgd.mgd.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.mgd.mgd.Buttons.MuteButton;
import com.mgd.mgd.Buttons.ResumeButton;
import com.mgd.mgd.PauseScreen;
import com.mgd.mgd.SampleGame;

/**
 * Created by 161832Q on 27/1/2018.
 */

public class PauseConfirmDialogFragment extends DialogFragment {
    public static boolean IsShown = false;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //duplicate guard
        IsShown = true;

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Confirm Pause?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // dun do anything
                        IsShown = false;
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //trigger pause
                        SampleGame.Instance.SetIsPaused(true);
                        IsShown = false;

                        // create the relevant buttons and pause screen
                        PauseScreen.Create();
                        ResumeButton.Create();
                        MuteButton.Create();
                    }
                });

                return builder.create();
    }
}
