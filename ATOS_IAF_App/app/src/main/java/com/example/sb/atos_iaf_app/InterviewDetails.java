package com.example.sb.atos_iaf_app;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sb.atos_iaf_app.Sql.DataBaseHelper1;

import java.util.Calendar;
import java.util.HashMap;

public class InterviewDetails extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Context context;
    DataBaseHelper1 dbhelper;
    Session session;
    Button date;
    EditText date1;
    private int day, mon, year;
    private DatePickerDialog.OnDateSetListener mdate;


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
        String selected = Homepage.job_id_clicked.substring(0, Homepage.job_id_clicked.indexOf(" "));
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        // Start the transaction.
        db.beginTransaction();
        String status1 = "NO";
        String status2 = "Accepted";
        String status3 = "Completed";
        String selstatus;
        String[] columns = {"AppID", "Name", "JobID", "Phone", "Address"};
        String whereclause;//= "JobID = " + selected ;


        Cursor cursor1 = db.query("Candidate_details", columns, "JobID=?", new String[]{selected}, null, null, null);
        String JID=null, Name1 = null, Con = null, Add = null;
        String JobDes = null;
        String ApID = null;
        if (cursor1.getCount() > 0) {
            while (cursor1.moveToNext()) {
                // Read columns data
                Name1 = cursor1.getString(cursor1.getColumnIndex("Name"));
                JID = cursor1.getString(cursor1.getColumnIndex("JobID"));
                ApID = cursor1.getString(cursor1.getColumnIndex("AppID"));
                Con = cursor1.getString(cursor1.getColumnIndex("Phone"));
                Add = cursor1.getString(cursor1.getColumnIndex("Address"));
                // Name.add(Name1);
                // ApplicantID.add(ApID);
                // JobID.add(JID);
                // Contact.add(Con);
                // Address.add(Add);

                //Display these Strings


            }
        }
        TextView txtcandID = (TextView) findViewById(R.id.candID);
        txtcandID.setText("ApplicantID:       " + ApID);
        TextView txtcandName = (TextView) findViewById(R.id.candName);
        txtcandName.setText("Name:              " + Name1);
        TextView txtcontactNO = (TextView) findViewById(R.id.contactNO);
        txtcontactNO.setText("Contact:           " + Con);
        TextView textView1 = (TextView) findViewById(R.id.jobid);
        textView1.setText("JobID:             " + JID);
        TextView txtCon = (TextView) findViewById(R.id.add);
        txtCon.setText("Address+           " + Add);
        Button schedule = (Button) findViewById(R.id.schedule);
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onScheduleClick();
            }
        });
        db.endTransaction();
        dbhelper.close();
        date = (Button) findViewById(R.id.date);
        date1 = (EditText) findViewById(R.id.date1);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                day = c.get(Calendar.DAY_OF_MONTH);
                Log.d("akshitha", "DSdds");
                mon = c.get(Calendar.MONTH);
                year = c.get(Calendar.YEAR);
               // Toast.makeText(context, "Scheduled your Interview1", Toast.LENGTH_LONG).show();
                DatePickerDialog Dialog;
                Dialog = new DatePickerDialog(InterviewDetails.this,android.R.style.Theme_Holo_Dialog_MinWidth, mdate, year, mon, day);
                Dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Dialog.show();
            }

        });
      mdate =new DatePickerDialog.OnDateSetListener(){
          @Override
          public void onDateSet(DatePicker datePicker, int year, int month, int day) {
              month=month+1;
              String date=day+"/"+month+"/"+year;
              date1.setText(date);


          }


      };
        String d = date1.getText().toString();
        Log.d("KKK", d);

    }

    private void onDateClick() {
        final Calendar c= Calendar.getInstance();
        day=c.get(Calendar.DAY_OF_MONTH);
        Log.d("akshitha","DSdds");
        mon=c.get(Calendar.MONTH);
        year=c.get(Calendar.YEAR);
       // Toast.makeText(context, "Scheduled your Interview1", Toast.LENGTH_LONG).show();
        DatePickerDialog dataPickerDialog=new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener()
        {
            public void onDateSet(DatePicker view, int year, int moy, int doy){
                date1.setText(doy+"/"+(moy+1)+"/"+year);
               // Toast.makeText(context, "Scheduled your Interview2", Toast.LENGTH_LONG).show();
            }
        },day,mon,year);
    }


    private void onScheduleClick() {
        ContentValues cv = new ContentValues();
        String sel = Homepage.job_id_clicked.substring(0, Homepage.job_id_clicked.indexOf(" "));
       /// String query="update jobdetails set status='Accepted' where jobid ='" +sel+ "';";

       cv.put("status", "Accepted");
        dbhelper = new DataBaseHelper1(context);
        SQLiteDatabase my = dbhelper.getWritableDatabase();
        my.beginTransaction();
        my.update("jobdetails", cv, "jobid=?", new String[]{sel});
        DataBaseHelper1.databaseversion=my.getVersion();
       // my.execSQL(query);
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

