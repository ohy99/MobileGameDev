package com.mgd.mgd.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.mgd.mgd.GamePage;
import com.mgd.mgd.MainMenu;

/**
 * Created by 161832Q on 27/1/2018.
 */

public class ExitToMainMenuConfirmDialogFragment extends DialogFragment {
    public static boolean IsShown = false;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //duplicate guard
        IsShown = true;

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Exit to Main Menu?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //return to mainmenu
                        Intent intent = new Intent();
                        intent.setClass(GamePage.Instance, MainMenu.class);
                        GamePage.Instance.finish();
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                        IsShown = false;
                    }
                });

        return builder.create();
    }

    @Override
    public void onCancel(DialogInterface dialog) { IsShown = false;}

    @Override
    public void onDismiss(DialogInterface dialog) {IsShown = false;}
}

