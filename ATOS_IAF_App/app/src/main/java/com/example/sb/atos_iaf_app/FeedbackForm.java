package com.example.sb.atos_iaf_app;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import com.example.sb.atos_iaf_app.Sql.DataBaseHelper;
import com.example.sb.atos_iaf_app.Sql.DataBaseHelper1;

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
    private void onSubmitClick()
    {
      dbhelper=new DataBaseHelper1(this);
        SQLiteDatabase ds=dbhelper.getWritableDatabase();
        ds.beginTransaction();
        EditText txt=(EditText)findViewById(R.id.Cand_RequirementType);
        String typo=txt.getText().toString();
        RadioGroup rgp1=(RadioGroup)findViewById(R.id.PROG_radio);
        int selid1=rgp1.getCheckedRadioButtonId();
        RadioButton rbpro=(RadioButton)findViewById(selid1);
        String rbproval=(String) rbpro.getText();
        ContentValues cv=new ContentValues();
        cv.put("Name",Name1);
        cv.put("Contact",contact);
        cv.put("email",email);
        cv.put("typeofreq",rbproval);
        ds.insertOrThrow("Feedback","",cv);
        ContentValues cv1=new ContentValues();
        cv1.put("status","completed");
        ds.update("jobdetails",cv1,"jobid=?", new String[]{sel});
        DataBaseHelper1.databaseversion=ds.getVersion();
        ds.endTransaction();
        dbhelper.close();
        Toast.makeText(context, "Submitted your Feedback", Toast.LENGTH_LONG).show();


    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.feedback_form, menu);
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
