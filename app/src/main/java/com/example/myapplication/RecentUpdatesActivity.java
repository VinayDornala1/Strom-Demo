package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.example.myapplication.adapter.ListAdapters;
import com.example.myapplication.model.ListModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
public class RecentUpdatesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListAdapters listAdapters;
    private static ProgressDialog mProgressDialog;
    ArrayList<ListModel> listModelArrayList = new ArrayList<>();
    private String Urls = "https://app.stormoverseas.com/API/ArticlesAPI/RecentUpdates?Type=Upcoming";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = findViewById(R.id.textView);
        gtSelectedImages();
    }
    private void gtSelectedImages() {
        showSimpleProgessDialog(this, "Loading...", "Fetching Json", false);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Urls,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("strrrrr", ">>" + response);
                        try {
                            removeSimpleProgressDialog();
                            JSONObject obj = new JSONObject(response);
                            if (obj.optString("msg").equals("success")) {
                                listModelArrayList = new ArrayList<>();
                                JSONArray dataArray = obj.getJSONArray("RecentUpdatesList");
                                for (int i = 0; i < dataArray.length(); i++) {
                                    ListModel playerModel = new ListModel();
                                    Log.e("strrrrrr", ">>" + playerModel);
                                    JSONObject dataobj = dataArray.getJSONObject(i);
                                    playerModel.setImgURL(dataobj.getString("ImageUrl"));
                                    playerModel.setCountry(dataobj.getString("Title"));
                                    playerModel.setCity(dataobj.getString("shortdescription"));
                                    playerModel.setName(dataobj.getString("ArticleId"));
                                    listModelArrayList.add(playerModel);
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
        listAdapters = new ListAdapters(this, listModelArrayList);
        recyclerView.setAdapter(listAdapters);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
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