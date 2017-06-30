package com.example.sb.atos_iaf_app;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.os.Handler;


public class FirstActivity extends Activity {



    private int splash_time=2000;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_first);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent=new Intent(FirstActivity.this,SecondActivity.class);
                FirstActivity.this.startActivity(mainIntent);
                FirstActivity.this.finish();
            }
        },splash_time);
    }

  /*  public void gotosecondpage(View view)
    {
        Intent secondPage=new Intent(FirstActivity.this,SecondActivity.class);
        startActivity(secondPage);
    }
    */

}

