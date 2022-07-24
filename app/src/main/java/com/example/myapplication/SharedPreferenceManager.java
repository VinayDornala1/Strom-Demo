package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;

public class
SharedPreferenceManager {
    public static String shared_pref_name = "Jaganna Thodu";
    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    private static Context mcontext;
    public SharedPreferenceManager(Context  context) {
        this.mcontext = context;
        sharedPreferences=mcontext.getSharedPreferences(shared_pref_name,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public static final String Name ="Name";
    public static final String Mobile ="Mobile";
    public static final String Id ="Id";
    public static final String DISTRICT_NAME ="DISTRICT_NAME";
    public static final String DISTRICT_CODE ="DISTRICT_CODE";
    public static final String MANDAL_NAME ="MANDAL_NAME";
    public static final String SECRETARIAT_CODE ="SECRETARIAT_CODE";
    public static final String SECRETARIAT_NAME ="SECRETARIAT_NAME";
    public static final String VOLUNTEER_NAME ="VOLUNTEER_NAME";
    public static final String WORK_STATUS ="WORK_STATUS";
    public static final String WORK_MSG ="WORK_MSG";
    public static final String Latest_VersionCode ="Latest_VersionCode";
    public static final String Latest_VersionName ="Latest_VersionName";
    public static final String Firbase_Status ="Firbase_Status";
    public static final String CLUSTER_ID ="CLUSTER_ID";
    public static final String mandal_code ="mandal_code";
    public static final String VOLUNTEER_UIA ="VOLUNTEER_UIA";
    public String saveretaildata(String districtName,String mandalName,String mancode,String secrcode,String secrname,String volname,String mob,String discode,String status,String cid,String uia)
    {
        sharedPreferences=mcontext.getSharedPreferences(shared_pref_name, Context.MODE_PRIVATE);
        editor.putString(DISTRICT_NAME,districtName);
        editor.putString(MANDAL_NAME,mandalName);
        editor.putString(mandal_code,mancode);
        editor.putString(SECRETARIAT_CODE,secrcode);
        editor.putString(SECRETARIAT_NAME,secrname);
        editor.putString(VOLUNTEER_NAME,volname);
        editor.putString(Mobile,mob);
        editor.putString(DISTRICT_CODE,discode);
        editor.putString(Firbase_Status,status);
        editor.putString(CLUSTER_ID,cid);
        editor.putString(VOLUNTEER_UIA,uia);
        editor.commit();
        Log.e(SharedPreferenceManager.class.getName(),"DISTRICT_NAME-->"+districtName);
        return districtName;
    }
    public HashMap<String, String> getvolunteerdetails()
    {
        sharedPreferences=mcontext.getSharedPreferences(shared_pref_name, Context.MODE_PRIVATE);
        HashMap<String,String>Retailerdata = new HashMap<>();
        Retailerdata.put(DISTRICT_NAME,sharedPreferences.getString(DISTRICT_NAME,""));
        Retailerdata.put(MANDAL_NAME,sharedPreferences.getString(MANDAL_NAME,""));
        Retailerdata.put(mandal_code,sharedPreferences.getString(mandal_code,""));
        Retailerdata.put(SECRETARIAT_CODE,sharedPreferences.getString(SECRETARIAT_CODE,""));
        Retailerdata.put(SECRETARIAT_NAME,sharedPreferences.getString(SECRETARIAT_NAME,""));
        Retailerdata.put(VOLUNTEER_NAME,sharedPreferences.getString(VOLUNTEER_NAME,""));
        Retailerdata.put(Mobile,sharedPreferences.getString(Mobile,""));
        Retailerdata.put(DISTRICT_CODE,sharedPreferences.getString(DISTRICT_CODE,""));
        Retailerdata.put(Firbase_Status,sharedPreferences.getString(Firbase_Status,""));
        Retailerdata.put(CLUSTER_ID,sharedPreferences.getString(CLUSTER_ID,""));
        Retailerdata.put(VOLUNTEER_UIA,sharedPreferences.getString(VOLUNTEER_UIA,""));
        return Retailerdata;
    }
    public void setWorkstatus(int filePath, String msg) {
        editor.putInt(WORK_STATUS, filePath);
        editor.putString(WORK_MSG, msg);
        editor.commit();
    }public void setVersioncode(int vercode, int vername) {
        editor.putInt(Latest_VersionCode, vercode);
        editor.putInt(Latest_VersionName, vername);
        editor.commit();
        Log.e("spmvercode","vercode--->"+vercode);
    }
    public int getWorkstatus() {
        return sharedPreferences.getInt(WORK_STATUS, 0);
    }
    public int getVersioncode() {
        return sharedPreferences.getInt(Latest_VersionCode, 0);
    } public int getVersionname() {
        return sharedPreferences.getInt(Latest_VersionName, 0);
    }
    public String getWorkmsg() {
        return sharedPreferences.getString(WORK_MSG, null);
    }
}