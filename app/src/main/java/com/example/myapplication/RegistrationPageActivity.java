package com.example.myapplication;
import static android.R.layout.simple_spinner_item;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
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
import com.example.myapplication.model.GoodModels;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
public class RegistrationPageActivity extends AppCompatActivity {
    TextView textView;
    EditText editText,editText1,editText2;
    private String URLstring = "https://app.stormoverseas.com/API/StudentsAPI/GetCountryCodes";
    private String URLstrings = "https://app.stormoverseas.com/API/StudentsAPI/Add";
    private ArrayList<GoodModels> goodModelArrayList;
    Button button;
    private ArrayList<String> names = new ArrayList<String>();
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        textView=findViewById(R.id.text7);
        spinner = findViewById(R.id.spinner);
        editText=findViewById(R.id.edit);
        editText1=findViewById(R.id.editTextPhone2);
        editText2=findViewById(R.id.edit2);
        button=findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=editText.getText().toString();
                String number=editText1.getText().toString();
                String email=editText2.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                getRegistration(name,number,email,emailPattern);
                if (name.isEmpty()&& number.isEmpty()&&email.isEmpty()){
                    editText.setError("please enter name");
                    editText1.setError("please enter number");
                    editText2.setError("please enter email");
                }else if (number.length()<10&&!email.matches(emailPattern)){
                    editText2.setError("please enter email");
                    editText1.setError("please enter number");
                }else if (name.isEmpty()){
                    editText.setError("please name");
                }else if (number.isEmpty()){
                    editText1.setError("please number");
                }else if (number.trim().length() <10){
                    editText1.setError("please enter number");
                }else if (email.isEmpty()){
                    editText2.setError("please enter email");
                }else if (!email.matches(emailPattern)){
                    editText2.setError("please enter email pattern");
                }else {
                }
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
        textView.setPaintFlags(textView.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        retrieveJSON();
    }
    private void getRegistration(String name, String number, String email,String emailPattern) {
        JSONObject jsonObject=new JSONObject();
        String url=null;
        url=URLstrings;
        try {
            jsonObject.put("FullName",name);
            jsonObject.put("Email",email);
            jsonObject.put("MobileNo",number);
            jsonObject.put("Password",123456);
            jsonObject.put("APPType","androidweb");
            jsonObject.put("CourseLeadsTo","");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("jsonObject", "jsonObject-->" + jsonObject);
        Log.e("url", "url-->" + url);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject jsonObject = null;
                        Log.e("response", "response===> " + response);
                        try {
                            JSONObject obj = new JSONObject(response.toString());
                            obj.getString("msg");
                            obj.getString("str");
                            obj.getString("FullName");
                            obj.getString("Name");
                            obj.getString("Email");
                            obj.getString("Citizeship");
                            obj.getString("StudentId");
                            Intent intent;
                            intent = new Intent(RegistrationPageActivity.this, LoginPageActivity.class);
                            startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
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
                                    GoodModels playerModel=new GoodModels();
                                    JSONObject dataobj=dataArray.getJSONObject(i);
                                    playerModel.setName(dataobj.getString("Code"));
                                    goodModelArrayList.add(playerModel);
                                }
                                for (int i=0;i<goodModelArrayList.size();i++){
                                    names.add(goodModelArrayList.get(i).getName().toString());
                                }
                                ArrayAdapter<String> spinnerArrayAdapter=new ArrayAdapter<String>(RegistrationPageActivity.this,simple_spinner_item,names);
                                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinner.setAdapter(spinnerArrayAdapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}