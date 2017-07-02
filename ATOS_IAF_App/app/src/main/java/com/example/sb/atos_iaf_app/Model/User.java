package com.example.sb.atos_iaf_app.Model;

/**
 * Created by Akshitha on 01-07-2017.
 */


public class User {
    String Name,DASID,Email,Password;
    long Contact;
    public void setName(String Name)
    {
        this.Name=Name;
    }
    public String getName()
    {
        return this.Name;
    }
    public void setDASID(String DASID)
    {
        this.DASID=DASID;
    }
    public String getDASID()
    {
        return this.DASID;
    }
    public void setEmail(String Email)
    {
        this.Email=Email;
    }
    public String getEmail()
    {
        return this.Email;
    }
    public void setPassword(String Password)
    {
        this.Password=Password;
    }
    public String getPassword()
    {
        return this.Password;
    }
    public void setContact(long Contact)
    {
        this.Contact=Contact;
    }
    public long getContact()
    {
        return this.Contact;
    }
}
