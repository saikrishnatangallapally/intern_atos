package com.example.sb.AtosInterviewManagementServices;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sb.AtosInterviewManagementServices.Sql.DataBaseHelper1;

public class FeedbackForm extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Context context;
    DataBaseHelper1 dbhelper;
    Session session;
    public static String Name1;
    public static String contact;
    public static String email;
    public static String sel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_form);
        context=this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
      dbhelper=new DataBaseHelper1(context);
        SQLiteDatabase  db=dbhelper.getReadableDatabase();
        db.beginTransaction();
        sel= Homepage.acc_string.substring(0, Homepage.acc_string.indexOf(" "));
        String[] columns = {"Name", "Phone", "Email"};
        Cursor cursor1 = db.query("Candidate_details", columns, "JobID=?", new String[]{sel}, null, null, null);
        Name1 = null;
        contact = null;
        email = null;
        if (cursor1.getCount() > 0) {
            while (cursor1.moveToNext()) {
                // Read columns data
                Name1 = cursor1.getString(cursor1.getColumnIndex("Name"));
                contact = cursor1.getString(cursor1.getColumnIndex("Phone"));
                email = cursor1.getString(cursor1.getColumnIndex("Email"));

            }
        }
        TextView txtname = (TextView) findViewById(R.id.selname);
        txtname.setText("Name:        " + Name1);
        TextView txtpho = (TextView) findViewById(R.id.selpho);
        txtpho.setText("Contact:     " + contact);
        TextView txtemail = (TextView) findViewById(R.id.selemail);
        txtemail.setText("Email:         " + email);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        db.endTransaction();
        dbhelper.close();
        Button submit = (Button) findViewById(R.id.btnSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSubmitClick();
            }
        });
    }
    private void onSubmitClick() {
        dbhelper = new DataBaseHelper1(this);
        SQLiteDatabase ds = dbhelper.getWritableDatabase();

        EditText txt = (EditText) findViewById(R.id.Cand_RequirementType);
        String typo = txt.getText().toString();
        RadioGroup rgp1 = (RadioGroup) findViewById(R.id.PROG_radio);
        int selid1 = rgp1.getCheckedRadioButtonId();
        RadioGroup rgp2 = (RadioGroup) findViewById(R.id.ANAL_radio);
        int selid2 = rgp2.getCheckedRadioButtonId();
        RadioGroup rgp3 = (RadioGroup) findViewById(R.id.DESN_radio);
        int selid3 = rgp3.getCheckedRadioButtonId();
        RadioGroup rgp4 = (RadioGroup) findViewById(R.id.TEST_radio);
        int selid4 = rgp4.getCheckedRadioButtonId();
        RadioGroup rgp5 = (RadioGroup) findViewById(R.id.DBDS_radio);
        int selid5 = rgp5.getCheckedRadioButtonId();
        RadioGroup rgp6 = (RadioGroup) findViewById(R.id.AT_radio);
        int selid6 = rgp6.getCheckedRadioButtonId();
        RadioGroup rgp7 = (RadioGroup) findViewById(R.id.CT_radio);
        int selid7 = rgp7.getCheckedRadioButtonId();
        RadioGroup rgp8 = (RadioGroup) findViewById(R.id.AT_radio);
        int selid8 = rgp8.getCheckedRadioButtonId();


        if(Name1==null||contact==null||email==null||typo==null||selid1==-1||selid2==-1||selid3==-1||selid4==-1||selid5==-1||selid6==-1||selid7==-1||selid8==-1)
        {
            Toast.makeText(context, "Please enter  all the values", Toast.LENGTH_LONG).show();
        }
        else {
            RadioButton rbpro1 = (RadioButton) findViewById(selid1);
            String rbproval1 = (String) rbpro1.getText();
            RadioButton rbpro2 = (RadioButton) findViewById(selid2);
            String rbproval2 = (String) rbpro2.getText();
            RadioButton rbpro3 = (RadioButton) findViewById(selid3);
            String rbproval3 = (String) rbpro3.getText();
            RadioButton rbpro4 = (RadioButton) findViewById(selid4);
            String rbproval4 = (String) rbpro4.getText();
            RadioButton rbpro5 = (RadioButton) findViewById(selid5);
            String rbproval5 = (String) rbpro5.getText();
            RadioButton rbpro6 = (RadioButton) findViewById(selid6);
            String rbproval6 = (String) rbpro6.getText();
            RadioButton rbpro7 = (RadioButton) findViewById(selid7);
            String rbproval7 = (String) rbpro7.getText();
            RadioButton rbpro8 = (RadioButton) findViewById(selid8);
            String rbproval8 = (String) rbpro8.getText();

            ContentValues cv = new ContentValues();
            cv.put("Name", Name1);
            cv.put("Contact", contact);
            cv.put("email", email);
            cv.put("typeofreq", typo);
            cv.put("prog", rbproval1);
            cv.put("anal",rbproval2);
            cv.put("desn",rbproval3);
            cv.put("test",rbproval4);
            cv.put("dbds",rbproval5);
            cv.put("at",rbproval6);
            cv.put("ct",rbproval7);
            cv.put("it",rbproval8);
            cv.put("JobId",sel);

            ds.insertOrThrow("Feedback", "", cv);
            ContentValues cv1 = new ContentValues();
            cv1.put("status", "Completed");
            int row = ds.update("jobdetails", cv1, "jobid=?", new String[]{sel});
            DataBaseHelper1.databaseversion = ds.getVersion();
            ds.close();
            dbhelper.close();
            Toast.makeText(context, "Submitted your Feedback", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Homepage.class);
            startActivity(intent);
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(this, Homepage.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
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
