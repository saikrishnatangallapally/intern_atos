package com.example.sb.atos_iaf_app;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sb.atos_iaf_app.Sql.DataBaseHelper1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Homepage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public int count =0;
    private Context context;
    DataBaseHelper1 dbhelper;
    Session session;
    public static List<String> UpcomingInterviewsArray = new ArrayList<String>();
    public static List<String> AcceptedInterviewsArray = new ArrayList<String>();
    public static List<String> TodaysInterviewArray= new ArrayList<String>();
    public static List<String> CompletedInterview= new ArrayList<String>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
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

        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
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

        // create the TabHost that will contain the Tabs
        TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
        tabHost.setup();

        TabSpec tab1 = tabHost.newTabSpec("Upcoming Interviews");
        TabSpec tab2 = tabHost.newTabSpec("Accepted Interviews");
        TabSpec tab3 = tabHost.newTabSpec("Today's Interviews");
        TabSpec tab4 = tabHost.newTabSpec("Completed Interviews");

        // Set the Tab name and Activity
        // that will be opened when particular Tab will be selected
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("Upcoming Interviews");
        //tab1.setContent(new Intent(this,Tab1Activity.class));

        tab2.setContent(R.id.tab2);
        tab2.setIndicator("Accepted Interviews");
        //tab2.setContent(new Intent(this,Tab2Activity.class));

        tab3.setContent(R.id.tab3);
        tab3.setIndicator("Today's Interviews");
        //tab3.setContent(new Intent(this,Tab3Activity.class));

        tab4.setContent(R.id.tab4);
        tab4.setIndicator("Completed Interviews");
        //tab4.setContent(new Intent(this,Tab4Activity.class));

        /** Add the tabs  to the TabHost to display. */
        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);
        tabHost.addTab(tab4);

       /* for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)
        {
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(R.id.title);
            tv.setTextColor(0xffffffff);
        }*/
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        // Start the transaction.
        db.beginTransaction();

        try {

            // String selectQuery = " SELECT JobID,JobDescription,AppID FROM  jobdetails where Status = ' NO ' " ;
            String status1 = "NO";
            String status2 = "Accepted";
            String status3="Completed";
            String[] columns = {"JobID", "JobDescription", "AppID"};
           Cursor cursor = db.query("jobdetails", columns, "status=?", new String[]{status1}, null, null, null);

            Log.d("DD", "ss1");
            if (cursor.getCount() > 0) {
                Log.d("DD", "ss");
                while (cursor.moveToNext()) {
                    // Read columns data
                    String JID = cursor.getString(cursor.getColumnIndex("JobID"));
                    String JobDes = cursor.getString(cursor.getColumnIndex("JobDescription"));
                    String ApID = cursor.getString(cursor.getColumnIndex("AppID"));
                    UpcomingInterviewsArray.add(JID+"  "+JobDes+"  "+ApID);
                }

            }
            Cursor cursor1=db.query("jobdetails", columns, "status=?", new String[]{status2}, null, null, null);
            Log.d("DD", "ss1");
            if (cursor1.getCount() > 0) {
                Log.d("DD", "ss");
                while (cursor1.moveToNext()) {
                    // Read columns data
                    String JID = cursor1.getString(cursor1.getColumnIndex("JobID"));
                    String JobDes = cursor1.getString(cursor1.getColumnIndex("JobDescription"));
                    String ApID = cursor1.getString(cursor1.getColumnIndex("AppID"));
                    AcceptedInterviewsArray.add(JID+"  "+JobDes+"  "+ApID);
                }

            }
            Cursor cursor2=db.query("jobdetails", columns, "status=?", new String[]{status3}, null, null, null);
            Log.d("DD", "ss1");
            if (cursor1.getCount() > 0) {
                Log.d("DD", "ss");
                while (cursor2.moveToNext()) {
                    // Read columns data
                    String JID = cursor2.getString(cursor1.getColumnIndex("JobID"));
                    String JobDes = cursor2.getString(cursor1.getColumnIndex("JobDescription"));
                    String ApID = cursor2.getString(cursor1.getColumnIndex("AppID"));
                    CompletedInterview.add(JID+"  "+JobDes+"  "+ApID);
                }

            }
            db.setTransactionSuccessful();
        }
        catch (SQLiteException e)
        {
            e.printStackTrace();

        }
        finally
        {
            db.endTransaction();
                    // End the transaction.
            db.close();
                    // Close database
        }

            ListView listView1;
        listView1 = (ListView) findViewById(R.id.UpInterview);
            ArrayAdapter<String> adapter1;
        adapter1= new ArrayAdapter<>(this,
                R.layout.content_third_listup, R.id.listup, UpcomingInterviewsArray);
        listView1.setAdapter(adapter1);





            ListView listView2 = (ListView) findViewById(R.id.AccInterview);
            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                    R.layout.content_third_listacc,R.id.listacc, AcceptedInterviewsArray);
            listView2.setAdapter(adapter2);

         /*   ListView listView3 = (ListView) findViewById(R.id.TodInterview);
            ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,
                    R.layout.content_third_listtod,R.id.listtod, TodaysInterviewArray);
            listView3.setAdapter(adapter3);
*/
            ListView listView4 = (ListView) findViewById(R.id.ComInterview);
            ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this,
                    R.layout.content_third_listcom,R.id.listcom, CompletedInterview);
            listView4.setAdapter(adapter4);


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

    public static String job_id_clicked;
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Homepage) {
            count = count + 1;
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
    public void onListItemClick(View view)
    {
        job_id_clicked = ((TextView) view.findViewById(R.id.listup)).getText().toString();
        Intent intent = new Intent(this, InterviewDetails.class);
        startActivity(intent);
    }
    public void onAccItemClick(View view)
    {
        Intent intent = new Intent(this, FeedbackForm.class);
        startActivity(intent);
    }


    }



