package com.example.sb.AtosInterviewManagementServices;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.os.Handler;


public class LogoStartScreen extends Activity {



    private int splash_time=2000;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.logostartscreen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent=new Intent(LogoStartScreen.this,LoginScreen.class);
                LogoStartScreen.this.startActivity(mainIntent);
                LogoStartScreen.this.finish();
            }
        },splash_time);
    }

  /*  public void gotosecondpage(View view)
    {
        Intent secondPage=new Intent(LogoStartScreen.this,LoginScreen.class);
        startActivity(secondPage);
    }
    */

}

