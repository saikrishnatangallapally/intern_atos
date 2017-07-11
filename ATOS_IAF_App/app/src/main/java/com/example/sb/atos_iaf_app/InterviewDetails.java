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
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
        String email = user.get(Session.KEY_NAME);

        // email
        Log.d("user", email);
        String password = user.get(Session.KEY_PASSWORD);
        System.out.print(email);

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
        String selected = Homepage.app_id_clicked.substring(0, Homepage.app_id_clicked.indexOf(" "));
        Toast.makeText(context, selected, Toast.LENGTH_LONG).show();

        SQLiteDatabase db = dbhelper.getReadableDatabase();
        // Start the transaction.
        db.beginTransaction();
        String status1 = "NO";
        String status2 = "Accepted";
        String status3 = "Completed";
        String selstatus;
        String[] columns = {"AppID", "Name", "JobID", "Phone", "Address"};
       // String whereclause;//= "JobID = " + selected ;


        Cursor cursor1 = db.query("Candidate_details", columns, "AppID=?", new String[]{selected}, null, null, null);
        String ApID =null, Name1 = null, Con = null, Add = null;
        String JobDes = null;
        String JID = null;
        if (cursor1.getCount() > 0) {
            while (cursor1.moveToNext()) {
                // Read columns data
                Name1 = cursor1.getString(cursor1.getColumnIndex("Name"));
                ApID = cursor1.getString(cursor1.getColumnIndex("AppID"));
                JID = cursor1.getString(cursor1.getColumnIndex("JobID"));
                Con = cursor1.getString(cursor1.getColumnIndex("Phone"));
                Add = cursor1.getString(cursor1.getColumnIndex("Address"));
                // Name.add(Name1);
                // ApplicantID.add(ApID);
                // JobID.add(ApID);
                // Contact.add(Con);
                // Address.add(Add);

                //Display these Strings


            }
        }
        TextView txtcandID = (TextView) findViewById(R.id.jobid);
        txtcandID.setText("Job ID:                   " + JID);
        TextView txtcandName = (TextView) findViewById(R.id.candName);
        txtcandName.setText("Name:                     " + Name1);
        TextView txtcontactNO = (TextView) findViewById(R.id.contactNO);
        txtcontactNO.setText("Contact:                  " + Con);
        TextView textView1 = (TextView) findViewById(R.id.candID);
        textView1.setText("Applicant ID:             " + ApID);
        TextView txtCon = (TextView) findViewById(R.id.add);
        txtCon.setText("Address:                  " + Add);
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
        RadioGroup rdgrp;
        RadioButton selectedRadioButton;
        String date3=null;
        date3=date1.getText().toString();
        String sel = Homepage.app_id_clicked.substring(0, Homepage.app_id_clicked.indexOf(" "));
        rdgrp=(RadioGroup)findViewById(R.id.radioGroup);
        int rad=rdgrp.getCheckedRadioButtonId();
        if(rad==-1||date3==null)
        {
            Toast.makeText(getApplicationContext(),"Please select the date and time", Toast.LENGTH_LONG).show();
        }
        else {
            RadioButton rbpro = (RadioButton) findViewById(rad);
            String radiovalue = (String) rbpro.getText();
            String slot = radiovalue.substring(0, radiovalue.indexOf(" "));
            dbhelper = new DataBaseHelper1(context);
            // dbhelper.openDataBase();
            SQLiteDatabase my = dbhelper.getWritableDatabase();
            String[] columns = {"Date", "Email"};
            HashMap<String, String> user = session.getUserDetails();
            String email = user.get(Session.KEY_NAME);
            Cursor cursor1 = my.query("slots", columns, "Date=? and Email=?", new String[]{date3, email}, null, null, null);
            String slot1 = null, slot2 = null, slot3 = null, checkslot = null;
            Log.d("Aaaaaa", cursor1.getCount() + " ");
            if (cursor1.getCount() > 0) {
                if (cursor1.moveToFirst()) {
                    // Read columns data
                    if (slot.equals("10")) {
                        checkslot = cursor1.getString(cursor1.getColumnIndex("Slot1"));
                        if (checkslot.equals("True")) {
                            Toast.makeText(getApplicationContext(), "Slot is busy.Go for other slot", Toast.LENGTH_LONG).show();
                        } else {
                            ContentValues cv1 = new ContentValues();
                            cv1.put("Slot1", "True");
                            int row = my.update("slots", cv1, "Date=? and Email=?", new String[]{date3, email});
                            Log.v("Row No", row + "");
                            Toast.makeText(context, "Slot1", Toast.LENGTH_LONG).show();
                        }
                    } else if (slot.equals("12")) {
                        checkslot = cursor1.getString(cursor1.getColumnIndex("Slot2"));
                        if (checkslot.equals("True")) {
                            Toast.makeText(getApplicationContext(), "Slot is busy.Go for other slot", Toast.LENGTH_LONG).show();
                        } else {
                            ContentValues cv2 = new ContentValues();
                            cv2.put("Slot2", "True");
                            int row = my.update("slots", cv2, "Date=? and Email=?", new String[]{date3, email});
                            Log.v("Row No", row + "");
                            Toast.makeText(context, "Slot2", Toast.LENGTH_LONG).show();

                        }
                    } else if (slot.equals("2")) {
                        checkslot = cursor1.getString(cursor1.getColumnIndex("Slot3"));
                        if (checkslot.equals("True")) {
                            Toast.makeText(getApplicationContext(), "Slot is busy.Go for other slot", Toast.LENGTH_LONG).show();
                        } else {
                            ContentValues cv3 = new ContentValues();
                            cv3.put("Slot3", "True");
                            int row = my.update("slots", cv3, "Date=? and Email=?", new String[]{date3, email});
                            Log.v("Row No", row + "");
                            Toast.makeText(context, "Slot3", Toast.LENGTH_LONG).show();
                        }
                    }


                }
            } else {
                // ContentValues contentValues = new ContentValues();

                //contentValues.put("Email",email);
                //contentValues.put("Date",date3);
                if (slot.equals("10")) {
                    // contentValues.put("Slot1","True");
                    //contentValues.put("Slot2","False");
                    //contentValues.put("Slot3","False");
                    slot1 = "True";
                    slot2 = "False";
                    slot3 = "False";

                } else if (slot.equals("12")) {
                    //contentValues.put("Slot1","False");
                    //contentValues.put("Slot2","True");
                    //contentValues.put("Slot3","False");
                    slot1 = "False";
                    slot2 = "True";
                    slot3 = "False";
                } else if (slot.equals("2")) {
                    //contentValues.put("Slot1","False");
                    //contentValues.put("Slot2","False");
                    //   contentValues.put("Slot3","True");
                    slot1 = "False";
                    slot2 = "False";
                    slot3 = "True";

                }
                //contentValues.put("AppID",sel);
                //my.insert("slots",null ,contentValues);
                String query = "INSERT INTO slots (Email,Date,Slot1,Slot2,Slot3,AppID) VALUES('" + email + "', '" + date3 + "', '" + slot1 + "', '" + slot2 + "', '" + slot3 + "', '" + sel + "');";
                my.execSQL(query);
                Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_LONG).show();
                ContentValues cv = new ContentValues();
                cv.put("status", "Accepted");
                int row = my.update("jobdetails", cv, "appid=?", new String[]{sel});
                Log.v("Row No", row + "");
                Toast.makeText(context, "Scheduled your Interview", Toast.LENGTH_LONG).show();
                dbhelper.close();
                Intent intent = new Intent(this, Homepage.class);
                startActivity(intent);
            }
       /* ContentValues cv = new ContentValues();
        cv.put("status", "Accepted");
        int row =  my.update("jobdetails", cv, "appid=?", new String[]{sel});
        Log.v("Row No", row + "");
        Toast.makeText(context, "Scheduled your Interview", Toast.LENGTH_LONG).show();
        dbhelper.close();*/
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer1 = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer1.isDrawerOpen(GravityCompat.START)) {
            drawer1.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(this, Homepage.class);
            startActivity(intent);
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

