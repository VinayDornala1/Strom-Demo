package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import static android.R.layout.simple_spinner_item;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.model.GoodModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
public class Login extends AppCompatActivity {
    private String URLstring = "https://app.stormoverseas.com/API/StudentsAPI/GetCountryCodes";
    private String URLstrings = "https://app.stormoverseas.com/API/StudentsAPI/MobileAvailability?MobileNo=";
    private static ProgressDialog mProgressDialog;
    TextView textView;
    EditText editText;
//    ProgressDialog progressDialog;
    Button button;
    private ArrayList<GoodModel> goodModelArrayList;
    private ArrayList<String> names = new ArrayList<String>();
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        spinner = findViewById(R.id.spinner);
        button=findViewById(R.id.btn);
        editText=findViewById(R.id.edit);
        textView=findViewById(R.id.text4);

        mProgressDialog=new ProgressDialog(Login.this);
        mProgressDialog.setMessage("Processing");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressDialog.show();
                String number=editText.getText().toString();
                getMobileNumber(number);
                if (number.length()==10){
                }else {
                    mProgressDialog.dismiss();
                    Toast.makeText(Login.this,"please enter number",Toast.LENGTH_SHORT).show();
                }
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), RegistrationPageActivity.class);
                startActivity(intent);
            }
        });
        textView.setPaintFlags(textView.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        retrieveJSON();
    }



    private void getMobileNumber(String number) {
        JSONObject jsonObject=new JSONObject();
        String url=null;
        url=URLstrings+number;
        try {
            jsonObject.put("MobileNo",number);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("jsonObject", "jsonObject-->" + jsonObject);
        Log.e("url", "url-->" + url);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("strrrrr", ">>" + response);
                        try {
                            JSONObject obj=new JSONObject(response.toString());
                            obj.getString("msg");
                            obj.getString("data");
                            obj.getString("MobileNo");
                            if (!number.equals(obj)){
                                mProgressDialog.dismiss();
                                Toast.makeText(Login.this, "Success", Toast.LENGTH_SHORT).show();
                                Intent intent;
                                intent = new Intent(Login.this, OtpPageActivity.class);
                                startActivity(intent);
                                finish();
                                setLoginState(true,1);
                            }else{
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            mProgressDialog.dismiss();
                            Toast.makeText(Login.this, "please Register", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        int socketTimeout = 1800000;//60 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjectRequest.setRetryPolicy(policy);
        requestQueue.add(jsonObjectRequest);
        Log.e("requestQueue", "requestQueue--> " + requestQueue);
    }
    private void retrieveJSON() {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("strrrrr", ">>" + response);
                        try {
                            JSONObject obj=new JSONObject(response);
                            if (obj.optString("msg").equals("Success")){
                                goodModelArrayList=new ArrayList<>();
                                JSONArray dataArray=obj.getJSONArray("lstCodes");
                                for (int i=0;i<dataArray.length();i++){
                                    GoodModel playerModel=new GoodModel();
                                    JSONObject dataobj=dataArray.getJSONObject(i);
                                    playerModel.setName(dataobj.getString("Code"));
                                    goodModelArrayList.add(playerModel);
                                }
                                for (int i=0;i<goodModelArrayList.size();i++){
                                    names.add(goodModelArrayList.get(i).getName().toString());
                                }
                                ArrayAdapter<String> spinnerArrayAdapter=new ArrayAdapter<String>(Login.this,simple_spinner_item,names);
                                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinner.setAdapter(spinnerArrayAdapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void setLoginState(boolean status,Integer userType) {
        SharedPreferences sp = getSharedPreferences("app",
                MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
//        ed.putString("number",getIntent().getStringExtra("number"));
        ed.putBoolean("isLoggedIn", status);
        ed.putInt("userType",userType);
        ed.commit();
    }
}