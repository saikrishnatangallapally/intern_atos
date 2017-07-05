package com.example.sb.atos_iaf_app;

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
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.content.Intent;
import java.util.HashMap;


public class Homepage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Session session;
   // Button btnLogout;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        session = new Session(getApplicationContext());

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
        Log.d("user",name);
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
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
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
        getMenuInflater().inflate(R.menu.third, menu);
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
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    }



