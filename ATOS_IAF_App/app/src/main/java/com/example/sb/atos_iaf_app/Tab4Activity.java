package com.example.sb.atos_iaf_app;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;


/**
 * Created by PC on 05/07/2017.
 */

public class Tab4Activity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        TextView  tv=new TextView(this);
        tv.setTextSize(25);
        tv.setGravity(Gravity.CENTER_VERTICAL);
        tv.setText("This Is Tab4 Activity");

        setContentView(tv);
    }
}
