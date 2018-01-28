package com.mgd.mgd;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.UserDataHandler;

import java.util.HashSet;
import java.util.Set;

public class ScorePage extends Activity implements View.OnClickListener {

    private ScrollView scrollView;
    private TextView indexTextView, nameTextView, scoreTextView;
    //@Override
    private int scrollViewWidth, scrollViewHeight;

    private Activity myself = this;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // hide top bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_score_page);


        scrollView =  (ScrollView) findViewById(R.id.scrollView);

        //on create, adjust the size of the 3 header textview
        indexTextView = (TextView) findViewById(R.id.indexTV);
        nameTextView = (TextView) findViewById(R.id.nameTV);
        scoreTextView = (TextView) findViewById(R.id.scoreTV);

        TableRow headerTR = (TableRow) findViewById(R.id.headerTR);

        //main function is to store the width and height
        headerTR.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    scrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                else {
                    scrollView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }


                //prob is this thing came late
                scrollViewWidth = scrollView.getWidth();
                scrollViewHeight = scrollView.getHeight();
                Log.i("Width", String.valueOf(scrollViewWidth));
                Log.i("Height", String.valueOf(scrollViewHeight));




                int indexWidth = (int)(scrollViewWidth * 1.0/10.0);
                int nameWidth = (int)(scrollViewWidth * 7.0/10.0);
                int scoreWidth = (int)(scrollViewWidth * 2.0/10.0);
                Log.i("IndexWidth", String.valueOf(indexWidth));

                indexTextView.setLayoutParams(new TableRow.LayoutParams(indexWidth, TableRow.LayoutParams.WRAP_CONTENT));
                indexTextView.setTextSize(20);
                nameTextView.setLayoutParams(new TableRow.LayoutParams(nameWidth, TableRow.LayoutParams.WRAP_CONTENT));
                nameTextView.setTextSize(20);
                scoreTextView.setLayoutParams(new TableRow.LayoutParams(scoreWidth, TableRow.LayoutParams.WRAP_CONTENT));
                scoreTextView.setTextSize(20);


                SharedPreferences sharedPreference = getSharedPreferences("score", MODE_PRIVATE);
                //SharedPreferences.Editor editor = sharedPreference.edit();
                int numOfSaves = sharedPreference.getInt("num", 0);
                Log.i("numOfSaves", String.valueOf(numOfSaves));

                for (int i = 0; i < numOfSaves;++i){
                    String name = sharedPreference.getString("name" + String.valueOf(i), "nil");
                    int score = sharedPreference.getInt("score" + String.valueOf(i), 0);

                    Log.i("i", String.valueOf(i));
                    TextView textView = new TextView(myself);
                    textView.setText(String.valueOf(i + 1));
                    textView.setTextSize(20);
                    textView.setLayoutParams(new TableRow.LayoutParams(indexWidth, TableRow.LayoutParams.WRAP_CONTENT));

                    TextView textView2 = new TextView(myself);
                    textView2.setText(name);
                    textView2.setTextSize(20);
                    textView2.setLayoutParams(new TableRow.LayoutParams(nameWidth, TableRow.LayoutParams.WRAP_CONTENT));

                    TextView textView3 = new TextView(myself);
                    textView3.setText(String.valueOf(score));
                    textView3.setTextSize(20);
                    textView3.setLayoutParams(new TableRow.LayoutParams(scoreWidth, TableRow.LayoutParams.WRAP_CONTENT));

                    TableRow tableRow = new TableRow(myself);
                    tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                    tableRow.addView(textView);
                    tableRow.addView(textView2);
                    tableRow.addView(textView3);

                    TableLayout tableLayout = (TableLayout) findViewById(R.id.scoreboard);
                    tableLayout.addView(tableRow,  new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

                }


            }});



        backButton = (Button) findViewById(R.id.backbutton);
        backButton.setOnClickListener(this);

    }

    @Override
    //Invoke a callback on clicked event on a view
    public void onClick(View v)
    {
        //Intent intent = new Intent();
        if(v == backButton)
        {
            Intent intent = new Intent();
            intent.setClass(this, MainMenu.class);
            this.finish();
            startActivity(intent);
        }

//        if(v == btn_start) {
//            intent.setClass(MainMenu.this, GamePage.class);
//            startActivity(intent);
//            MainMenu.this.finish();
//        }
//
//        if(v == btn_facebook) {
//
//        }
//
//        if (v == btn_settings) {
//
//        }
//
//        if(v == btn_highscore) {
//
//        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
