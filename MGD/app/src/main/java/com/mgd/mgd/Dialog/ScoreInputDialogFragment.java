package com.mgd.mgd.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;

import com.mgd.mgd.Common.Constants;
import com.mgd.mgd.Common.Player;
import com.mgd.mgd.Components.ScoreSystem;
import com.mgd.mgd.GamePage;
import com.mgd.mgd.MainMenu;
import com.mgd.mgd.PostGameScreen;
import com.mgd.mgd.R;
import com.mgd.mgd.SampleGame;

public class ScoreInputDialogFragment extends DialogFragment{
    public static boolean IsShown = false;
    public AlertDialog.Builder builder = null;
    public EditText inputText = null;
    public Button continueButton = null;
    ConstraintLayout constraintLayout = null;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //duplicate guard
        //IsShown = true;

        builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Enter Your Name");

        if (builder == null)
        Log.i("builder", "null");
        else
            Log.i("builder", "created");

//        builder.setMessage("Exit to Main Menu?")
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        //return to mainmenu
//                        Intent intent = new Intent();
//                        intent.setClass(GamePage.Instance, MainMenu.class);
//                        startActivity(intent);
//                        GamePage.Instance.finish();
//                    }
//                })
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // do nothing
//                        IsShown = false;
//                    }
//                });
        builder.setView(constraintLayout);
        Dialog alertDialog = builder.create();


        return alertDialog;
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater,
//                      ViewGroup container,
//                      Bundle savedInstanceState)
//    {
//        super.onCreateView(inflater, container, savedInstanceState);
//
//        return
//    }

    @Override
    public void onAttach (final Context context)
    {
        super.onAttach(context);

        inputText = new EditText(context);
        inputText.setId(R.id.inputText);
        continueButton = new Button(context);
        continueButton.setId(R.id.continueButton);
        constraintLayout = new ConstraintLayout(context);
        constraintLayout.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT));

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputName = inputText.getText().toString();
                Log.i("onClick InputName", inputName);

                SampleGame.Instance.SaveScore((ScoreSystem) Player.Instance.GetComponent("score"), inputName);



                Intent intent = new Intent();
                // transition to postgame
                // commented out cause crash, temporary transition to mainmenu
                //intent.setClass(GamePage.Instance, PostGameScreen.class);
                intent.setClass(GamePage.Instance,MainMenu.class);
                GamePage.Instance.startActivity(intent);
                GamePage.Instance.finish();
            }
        });

        Log.i("OnAttach", "OnAttach In");

        constraintLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int width = constraintLayout.getWidth();
                int height = constraintLayout.getHeight();

                Log.i("scoreLayoutWidth", String.valueOf(width));
                Log.i("scoreLayoutHeight", String.valueOf(height));

                inputText.setLayoutParams(new ConstraintLayout.LayoutParams((int)(width * 8.0/10.0), ConstraintLayout.LayoutParams.MATCH_PARENT));
                continueButton.setLayoutParams(new ConstraintLayout.LayoutParams((int)(width * 2.0/10.0), ConstraintLayout.LayoutParams.MATCH_PARENT));

                ConstraintSet constraintSet = new ConstraintSet();

                constraintSet.constrainHeight(continueButton.getId(),
                        ConstraintSet.WRAP_CONTENT);
                constraintSet.constrainWidth(continueButton.getId(),
                        ConstraintSet.WRAP_CONTENT);

                constraintSet.connect(continueButton.getId(), ConstraintSet.LEFT, inputText.getId(), ConstraintSet.RIGHT, 0);
                constraintSet.applyTo(constraintLayout);

                continueButton.setText("Continue");
                continueButton.setTextSize(10);
                //continueButton.con
            }
        });



        constraintLayout.addView(inputText);
        constraintLayout.addView(continueButton);
    }

    public void InitDialogTextBox(Context context)
    {
        inputText = new EditText(context);
        //inputText.setId(1000+1);
        //builder.setView(inputText);
    }

    @Override
    public void onCancel(DialogInterface dialog) { IsShown = false;}

    @Override
    public void onDismiss(DialogInterface dialog) {IsShown = false;}


}
