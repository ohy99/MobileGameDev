package com.mgd.mgd;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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

import com.mgd.mgd.Components.Render;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.UserDataHandler;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class ScorePage extends Activity implements View.OnClickListener {

    private ScrollView scrollView;
    private TextView indexTextView, nameTextView, scoreTextView;
    //@Override
    private int scrollViewWidth, scrollViewHeight;

    private Activity myself = this;
    private Button backButton;

    class ScoreInfo
    {
        String name;
        int score;
    }

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

                indexTextView.setLayoutParams(new TableRow.LayoutParams(indexWidth, TableRow.LayoutParams.MATCH_PARENT));
                indexTextView.setTextSize(20);
                indexTextView.setBackgroundColor(Color.GRAY);
                nameTextView.setLayoutParams(new TableRow.LayoutParams(nameWidth, TableRow.LayoutParams.MATCH_PARENT));
                nameTextView.setTextSize(20);
                nameTextView.setBackgroundColor(Color.GRAY);
                scoreTextView.setLayoutParams(new TableRow.LayoutParams(scoreWidth, TableRow.LayoutParams.MATCH_PARENT));
                scoreTextView.setTextSize(20);
                scoreTextView.setBackgroundColor(Color.GRAY);


                SharedPreferences sharedPreference = getSharedPreferences("score", MODE_PRIVATE);
                //SharedPreferences.Editor editor = sharedPreference.edit();
                int numOfSaves = sharedPreference.getInt("num", 0);
                Log.i("numOfSaves", String.valueOf(numOfSaves));


                Vector<ScoreInfo> scoreInfoVector = new Vector<>();
                for (int i = 0; i < numOfSaves; ++i)
                {
                    ScoreInfo scoreInfo = new ScoreInfo();
                    scoreInfo.name  = sharedPreference.getString("name" + String.valueOf(i), "nil");
                    scoreInfo.score = sharedPreference.getInt("score" + String.valueOf(i), 0);
                    scoreInfoVector.add( scoreInfo);
                }
                quick_sort(scoreInfoVector, 0, scoreInfoVector.size()- 1);

                for (int i = scoreInfoVector.size() - 1; i >= 0; --i)
                {
                    ScoreInfo scoreInfo = scoreInfoVector.get(i);
                    String name = scoreInfo.name;
                    int score = scoreInfo.score;

                    Log.i("i", String.valueOf(i));

                    TextView textView = new TextView(myself);
                    textView.setText(String.valueOf(scoreInfoVector.size() - i));
                    textView.setTextSize(20);
                    if ((scoreInfoVector.size() - i) % 2 == 1)
                    textView.setBackgroundColor(Color.LTGRAY);
                    else
                        textView.setBackgroundColor(Color.GRAY );
                    textView.setLayoutParams(new TableRow.LayoutParams(indexWidth, TableRow.LayoutParams.MATCH_PARENT));

                    TextView textView2 = new TextView(myself);
                    textView2.setText(name);
                    textView2.setTextSize(20);
                    if ((scoreInfoVector.size() - i) % 2 == 1)
                        textView2.setBackgroundColor(Color.LTGRAY);
                    else
                        textView2.setBackgroundColor(Color.GRAY );
                    textView2.setLayoutParams(new TableRow.LayoutParams(nameWidth, TableRow.LayoutParams.MATCH_PARENT));

                    TextView textView3 = new TextView(myself);
                    textView3.setText(String.valueOf(score));
                    textView3.setTextSize(20);
                    if ((scoreInfoVector.size() - i) % 2 == 1)
                        textView3.setBackgroundColor(Color.LTGRAY);
                    else
                        textView3.setBackgroundColor(Color.GRAY );
                    textView3.setLayoutParams(new TableRow.LayoutParams(scoreWidth, TableRow.LayoutParams.MATCH_PARENT));

                    TableRow tableRow = new TableRow(myself);
                    tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
                    tableRow.addView(textView);
                    tableRow.addView(textView2);
                    tableRow.addView(textView3);

                    TableLayout tableLayout = (TableLayout) findViewById(R.id.scoreboard);
                    tableLayout.addView(tableRow,  new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT));
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




    private void quick_sort(Vector<ScoreInfo> data_list, int first_index, int last_index){
        if (first_index < last_index)
        {
            int pivot_location = partition(data_list, first_index, last_index);
            quick_sort(data_list, first_index, pivot_location - 1);
            quick_sort(data_list, pivot_location + 1, last_index);
        }
    }
    private int partition(Vector<ScoreInfo>  data_list , int first_index, int last_index){
        int middle_index = (first_index + last_index) / 2;
        ScoreInfo pivot_value = data_list.get(middle_index);
        int small_index = first_index;


        swap(data_list, first_index, middle_index);
        for (int check_index = (first_index + 1); check_index <= last_index; check_index++)
        {
            if (data_list.get(check_index).score < pivot_value.score)
            {
                small_index++;
                swap(data_list, small_index, check_index);
            }
        }
        swap(data_list, first_index, small_index);

        return small_index;
    }

    private void swap(Vector<ScoreInfo> data_list, int first_index, int second_index)
    {
        ScoreInfo temp = data_list.get(first_index);
        data_list.setElementAt(data_list.get(second_index), first_index);
        data_list.setElementAt(temp, second_index);
//        data_list.get(first_index) = data_list.get(second_index);
//        data_list.get(second_index) = temp;
    }
}
