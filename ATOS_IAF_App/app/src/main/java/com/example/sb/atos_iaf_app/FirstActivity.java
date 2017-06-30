package com.example.sb.atos_iaf_app;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;


public class FirstActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_first);
    }

    public void gotosecondpage(View view)
    {
        Intent secondPage=new Intent(FirstActivity.this,SecondActivity.class);
        startActivity(secondPage);
    }

}
