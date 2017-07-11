package com.example.sb.atos_iaf_app;

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
import android.widget.TextView;

import com.example.sb.atos_iaf_app.Sql.DataBaseHelper1;

public class CompletedFeedbackForm extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public String sel1 =null;
    public static String name ="test";
    public static String phone ="test";
    public static String email ="test";
    public static String typeofreq ="test";
    public static String prog ="test";
    public static String anal ="test";
    public static String desn ="test";
    public static String test ="test";
    public static String dbds ="test";
    public static String ct ="test";
    public static String at ="test";
    public static String ia ="test";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBaseHelper1 dbhelper=new DataBaseHelper1(this);
        SQLiteDatabase ds=dbhelper.getReadableDatabase();
        sel1= Homepage.comp_string.substring(0, Homepage.comp_string.indexOf(" "));
        String[] columns = {"Name", "Contact", "Email","Typeofreq","prog","anal","desn","test","dbds","at","ct","it","jobid"};
        Cursor cursor=ds.query("feedback", columns, "jobid=?", new String[]{sel1}, null, null, null);
        Log.d("DD", "ss1");
        if (cursor.getCount() > 0) {
            Log.d("DD", "ss");
            while (cursor.moveToNext()) {
                // Read columns data
                name = cursor.getString(cursor.getColumnIndex("Name"));
                 phone = cursor.getString(cursor.getColumnIndex("phone"));
                 email = cursor.getString(cursor.getColumnIndex("email"));
                 typeofreq = cursor.getString(cursor.getColumnIndex("typeofreq"));
                 prog = cursor.getString(cursor.getColumnIndex("prog"));
                 anal = cursor.getString(cursor.getColumnIndex("anal"));
                 desn = cursor.getString(cursor.getColumnIndex("desn"));
                 test = cursor.getString(cursor.getColumnIndex("test"));
                 dbds = cursor.getString(cursor.getColumnIndex("dbds"));
                 at = cursor.getString(cursor.getColumnIndex("at"));
                 ct = cursor.getString(cursor.getColumnIndex("ct"));
                 ia = cursor.getString(cursor.getColumnIndex("ia"));

            }

        }
        TextView txtname = (TextView) findViewById(R.id.selename);
        txtname.setText("Name:        " + name);
        TextView txtpho = (TextView) findViewById(R.id.selepho);
        txtpho.setText("Contact:     " + phone);
        TextView txtemail = (TextView) findViewById(R.id.selmails);
        txtemail.setText("Email:         " + email);
        TextView txttypo = (TextView) findViewById(R.id.req);
        txttypo.setText("Name:        " + typeofreq);
        TextView txtname1 = (TextView) findViewById(R.id.create);
        txtname1.setText("Name:        " + prog);
        TextView txtname2 = (TextView) findViewById(R.id.busana);
        txtname2.setText("Name:        " + anal);
        TextView txtname3 = (TextView) findViewById(R.id.sysdes);
        txtname3.setText("Name:        " + desn);
        TextView txtname4 = (TextView) findViewById(R.id.testing);
        txtname4.setText("Name:        " + test);
        TextView txtname5 = (TextView) findViewById(R.id.dbdes);
        txtname5.setText("Name:        " + dbds);
        TextView txtname6 = (TextView) findViewById(R.id.anathink);
        txtname6.setText("Name:        " + at);
        TextView txtname7 = (TextView) findViewById(R.id.conthink);
        txtname7.setText("Name:        " + ct);
        TextView txtname8 = (TextView) findViewById(R.id.infoacq);
        txtname8.setText("Name:        " + ia);

        ds.close();



        setContentView(R.layout.activity_completed_feedback_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
            Intent a=new Intent(this,LoginScreen.class);
            startActivity(a);
            return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
