package com.example.sb.atos_iaf_app;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sb.atos_iaf_app.Sql.DataBaseHelper1;

import java.util.HashMap;

public class InterviewDetails extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Context context;
    DataBaseHelper1 dbhelper;
    Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview_details);
        session = new Session(getApplicationContext());
        context = this;
        dbhelper = new DataBaseHelper1(context);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        TextView lblName = (TextView) findViewById(R.id.email);
        TextView lblPassword = (TextView) findViewById(R.id.password);

        // Button logout
        // btnLogout = (Button) findViewById(R.id.logout);

        //Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
        /**
         * Call this function whenever you want to check user login
         * This will redirect user to LoginActivity is he is not
         * logged in
         * */
        session.checkLogin();

        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        String name = user.get(Session.KEY_NAME);

        // email
        Log.d("user", name);
        String password = user.get(Session.KEY_PASSWORD);
        System.out.print(name);

        // displaying user data
        // lblName.setText(Html.fromHtml("Name: <b>" + name + "</b>"));
        //lblPassword.setText(Html.fromHtml("Passwo: <b>" + password + "</b>"));


        /**
         * Logout button click event
         * */
        /*btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Clear the session data
                // This will clear all session data and
                // redirect user to LoginActivity
                session.logoutUser();
            }
        });*/
        String selected  = Homepage.job_id_clicked.substring(0,Homepage.job_id_clicked.indexOf(" "));
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        // Start the transaction.
        db.beginTransaction();
        String status1 = "NO";
        String status2 = "Accepted";
        String status3="Completed";
        String selstatus;
        String[] columns = {"JobID", "JobDescription", "AppID","Status"};
        String whereclause ;//= "JobID = " + selected ;


        Cursor cursor1=db.query("jobdetails",columns,"JobID=?",new String[]{selected},null,null,null);
        Log.d("DD", "ss1");
        String JID;
        String JobDes = null;
        String ApID;
        if (cursor1.getCount() > 0) {
            Log.d("DD", "ss");
            while (cursor1.moveToNext()) {
                // Read columns data
                 JID = cursor1.getString(cursor1.getColumnIndex("JobID"));
                 JobDes = cursor1.getString(cursor1.getColumnIndex("JobDescription"));
                 ApID = cursor1.getString(cursor1.getColumnIndex("AppID"));
                 selstatus = cursor1.getString(cursor1.getColumnIndex("Status"));
                //Homepage.AcceptedInterviewsArray.add(JID+"  "+JobDes+"  "+ApID);
            }
        }
        TextView textView1 = (TextView) findViewById(R.id.jobDes);
        textView1.setText(JobDes);
        Button  schedule  = (Button) findViewById(R.id.schedule);
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onScheduleClick();
            }
        });
        db.endTransaction();
        dbhelper.close();

    }
    private void onScheduleClick()
    {
        ContentValues cv=new ContentValues();
        String  sel=Homepage.job_id_clicked;
        cv.put("status","Accepted");
        dbhelper=new DataBaseHelper1(context);
        SQLiteDatabase my=dbhelper.getReadableDatabase();
        my.beginTransaction();
        my.update("jobdetails",cv,"jobid=?",new String[]{sel});
        Toast.makeText(context, "Scheduled your Interview", Toast.LENGTH_LONG).show();
        my.endTransaction();
        dbhelper.close();

    }

    @Override
    public void onBackPressed() {
            DrawerLayout drawer1 = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer1.isDrawerOpen(GravityCompat.START)) {
                drawer1.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.third, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Homepage) {
            Intent intent = new Intent(this, Homepage.class);
            startActivity(intent);
            return true;


        } else if (id == R.id.nav_Feedback) {
            //Intent fed=new Intent(this,Feedback.class);
            //startActivity(fed);
            return true;

        } else if (id == R.id.nav_Logout) {
            session.logoutUser();
            // Intent a=new Intent(this,LoginScreen.class);
            // startActivity(a);
            return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}