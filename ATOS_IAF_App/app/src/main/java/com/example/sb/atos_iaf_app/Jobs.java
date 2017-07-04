package com.example.sb.atos_iaf_app;
/**
 * Created by zayan on 5/7/17.
 */
public class Jobs {

    private String JobDes;
    private String JobID;

    public Jobs(String JobID, String JobDes) {
        this.JobID = JobID;
        this.JobDes = JobDes;
    }

    public static final Jobs[] candidates = {
            new Jobs("7340", "Developer"),
            new Jobs("7341", "Designer"),
            new Jobs("7342", "Sales")
    };

    public static String[] getEmployeeNames(){
        String [] employeeNames = new String[Jobs.candidates.length];
        for(int i = 0; i< Jobs.candidates.length; i++){
            employeeNames[i] = candidates[i].getJobDes();
        }
        return employeeNames;
    }

    @Override
    public String toString(){
        return getJobID()+" "+ getJobDes();
    }

    public String getJobDes() {
        return JobDes;
    }

    public String getJobID() {
        return JobID;
    }
}


