package com.example.sb.atos_iaf_app;
/**
 * Created by zayan on 5/7/17.
 */
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements JobsListFragment.EmployeeListFragmentInterface{

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        JobsListFragment jobsListFragment = new JobsListFragment();

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content, jobsListFragment, "Load_JobsListFragment");
        ft.addToBackStack(null);
//        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
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


    @Override
    public void callEmployeeDetail(long employeeIndex) {
        Log.v(TAG, "In Main Activity got the following index "+employeeIndex);

        //Check if the FrameLayout is being used

        View frameLayoutView = findViewById(R.id.content);
        if(frameLayoutView != null){
            //Create the detail Fragment Object
            CandidateDetailFragment candidateDetailFragment = new CandidateDetailFragment();
            candidateDetailFragment.setEmployeeIndex(employeeIndex);

            //Start Fragment Transaction
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.content, candidateDetailFragment, "LOAD_CandidateDetailFragment");
            ft.addToBackStack(null);
//            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }else{
            //Call Detail Activity
        }

    }
}
