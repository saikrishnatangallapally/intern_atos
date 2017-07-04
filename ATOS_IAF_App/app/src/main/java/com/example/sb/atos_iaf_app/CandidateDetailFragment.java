package com.example.sb.atos_iaf_app;

/**
 * Created by zayan on 5/7/17.
 */
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class CandidateDetailFragment extends Fragment implements View.OnClickListener{

    long employeeIndex;

    public CandidateDetailFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(savedInstanceState != null){
            employeeIndex = savedInstanceState.getLong("employeeIndex");
        }

        View layout = inflater.inflate(R.layout.fragment_employee_detail, container, false);
        Button testButton = (Button) layout.findViewById(R.id.testButtonId);

        testButton.setOnClickListener(this);

        //Handle the Child Fragment. For demo purpose I haven't created a new Fragment
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        JobsListFragment elf = new JobsListFragment();
        ft.replace(R.id.employeeAddressFragmentId, elf);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
        //End

        return layout;
    }

    public void setEmployeeIndex(long employeeIndex) {
        this.employeeIndex = employeeIndex;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putLong("employeeIndex", employeeIndex);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.testButtonId:
                //Do something
                break;
        }
    }
}
