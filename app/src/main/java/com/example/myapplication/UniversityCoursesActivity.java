package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.adapter.ListsAdapter;
import com.example.myapplication.model.DataModels;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
public class UniversityCoursesActivity extends AppCompatActivity {
    private String Urls = "https://app.stormoverseas.com/API/UniversityCourses/Index";
    private static ProgressDialog mProgressDialog;
    ArrayList<DataModels> dataModelArrayList;
    private ListsAdapter rvAdapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);
        recyclerView = findViewById(R.id.recycler);
        gtSelectedImages();
    }
    private void gtSelectedImages() {
        showSimpleProgessDialog(this, "Loading...", "Fetching Json", false);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Urls,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("strrrrr",">>"+response);
                        try {
                            removeSimpleProgressDialog();
                            JSONObject obj = new JSONObject(response);
                            if (obj.optString("msg").equals("Success")) {
                                dataModelArrayList = new ArrayList<>();
                                JSONArray dataArrays = obj.getJSONArray("CountriesList");
                                for (int i = 0; i < dataArrays.length(); i++) {
                                    DataModels playerModel = new DataModels();
                                    JSONObject dataobj = dataArrays.getJSONObject(i);
                                    playerModel.setImgURL(dataobj.getString("CountryImage"));
                                    playerModel.setName(dataobj.getString("CountryName"));
                                    playerModel.setSelected(false);
                                    dataModelArrayList.add(playerModel);
                                }
                                setupRecycler();
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
    private void setupRecycler() {
        rvAdapter = new ListsAdapter(this, dataModelArrayList);
        recyclerView.setAdapter(rvAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2, GridLayoutManager.VERTICAL, false));
    }
    private void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();

        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void showSimpleProgessDialog(Context context, String title, String msg, boolean isCancelable) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context, title, msg);
                mProgressDialog.setCancelable(isCancelable);
            }
            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}