package com.example.myapplication.fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.RecentUpdatesActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.Adapters;
import com.example.myapplication.adapter.ListAdapter;
import com.example.myapplication.adapter.PopularAdapter;
import com.example.myapplication.adapter.ReadyAdapter;
import com.example.myapplication.model.DataModel;
import com.example.myapplication.model.Models;
import com.example.myapplication.model.PopularModel;
import com.example.myapplication.model.ReadyModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
public class IntroFragment extends Fragment {
    private static ProgressDialog mProgressDialog;
    RecyclerView recyclerView;
    RecyclerView recyclerView2,recyclerView3,recyclerView4;
    private ListView listView;
    private ListAdapter listAdapter;
    private ReadyAdapter readyAdapter;
    private Adapters adapters;
    private PopularAdapter popularAdapter;
    ArrayList<DataModel> dataModelArrayList=new ArrayList<>();
    ArrayList<ReadyModel> dataModelArrayListS=new ArrayList<>();
    TextView view,view1;
    private String URLstring = "https://app.stormoverseas.com/API/MobileBanners/MostPopularAPI";
    private String Url = "https://app.stormoverseas.com/API/MobileBanners/MostPopularAPI";
    private String UrlS = "https://app.stormoverseas.com/API/MobileBanners/MostPopularAPI";
    private String Urls = "https://app.stormoverseas.com/API/MobileBanners/MostPopularAPI";
    ArrayList<Models> image = new ArrayList<>();
    ArrayList<PopularModel> images = new ArrayList<>();
    public IntroFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_intro, container, false);
        recyclerView2 = viewGroup.findViewById(R.id.recyclers);
        view1=viewGroup.findViewById(R.id.view2);
        view=viewGroup.findViewById(R.id.view);
        recyclerView = viewGroup.findViewById(R.id.lv);
        recyclerView3=viewGroup.findViewById(R.id.lv2);
        recyclerView4=viewGroup.findViewById(R.id.lv3);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), RecentUpdatesActivity.class);
                startActivity(intent);
            }
        });
        gtSelectedImages();
        retrieveJSON();
        retrieveJSONS();
        retrieveJSONs();
        return viewGroup;
    }

    private void retrieveJSONs() {
        showSimpleProgressDailog("Loading...", "fetching Json", false);
        JSONObject jsonObject=new JSONObject();
        String url=null;
        url=UrlS;
        try {
            jsonObject.put("atype", "android");
            jsonObject.put("Email", "chapanu@gmail.com");
            jsonObject.put("position", "top");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject jsonObject = null;
                        Log.e("response", "response===> " + response);
                        try {
                            removeSimpleProgressDailog();
                            JSONObject obi = new JSONObject(response.toString());
                            if (obi.optString("msg").equals("Success")){
                                JSONArray js2 = obi.getJSONArray("IsTopUniversities");
                                Log.e("responsed", "response===> " + js2);
                                for (int i2 = 0; i2 < js2.length(); i2++) {
                                    JSONObject contacting3 = js2.getJSONObject(i2);
                                    PopularModel dataModel = new PopularModel();
                                    dataModel.setImages(contacting3.getString("ImageUrl"));
                                    dataModel.setText(contacting3.getString("UniversityName"));
                                    dataModel.setText1(contacting3.getString("Location"));
                                    dataModel.setText2(contacting3.getString("CountryName"));
//                                    dataModel.setText3(contacting3.getString("CountryName"));
//                                    dataModel.setText4(contacting3.getString("ReadyToApply"));
                                    images.add(dataModel);
                                    Log.e("response", "response===> " + contacting3);
                                }
                                if (images.size() > 0) {
                                    popularAdapter = new PopularAdapter(getActivity(), images);
                                    recyclerView4.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                                    recyclerView4.setAdapter(popularAdapter);
                                    popularAdapter.notifyDataSetChanged();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("error", "error--->" + error.toString());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        int socketTimeout = 1800000;//60 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjectRequest.setRetryPolicy(policy);
        requestQueue.add(jsonObjectRequest);
        Log.e("requestQueue", "requestQueue--> " + requestQueue);
    }

    private void retrieveJSONS() {
        showSimpleProgressDailog("Loading...", "fetching Json", false);
        JSONObject jsonObject=new JSONObject();
        String url=null;
        url=Urls;
        try {
            jsonObject.put("atype", "android");
            jsonObject.put("Email", "chapanu@gmail.com");
            jsonObject.put("position", "top");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject jsonObject = null;
                        Log.e("response", "response===> " + response);
                        try {
                            removeSimpleProgressDailog();
                            JSONObject obi = new JSONObject(response.toString());
                            if (obi.optString("msg").equals("Success")){
                                JSONArray js2 = obi.getJSONArray("ReadytoApply");
                                Log.e("responsed", "response===> " + js2);
                                for (int i2 = 0; i2 < js2.length(); i2++) {
                                    JSONObject contacting3 = js2.getJSONObject(i2);
                                    ReadyModel dataModel = new ReadyModel();
                                    dataModel.setImages(contacting3.getString("ImageUrl"));
                                    dataModel.setText(contacting3.getString("CourseName"));
                                    dataModel.setText1(contacting3.getString("UniversityName"));
                                    dataModel.setText2(contacting3.getString("Location"));
                                    dataModel.setText3(contacting3.getString("CountryName"));
                                    dataModel.setText4(contacting3.getString("ReadyToApply"));
                                    dataModelArrayListS.add(dataModel);
                                    Log.e("response", "response===> " + contacting3);
                                }
                                if (dataModelArrayListS.size() > 0) {
                                    readyAdapter = new ReadyAdapter(getActivity(), dataModelArrayListS);
                                    recyclerView3.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                                    recyclerView3.setAdapter(readyAdapter);
                                    readyAdapter.notifyDataSetChanged();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("error", "error--->" + error.toString());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        int socketTimeout = 1800000;//60 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjectRequest.setRetryPolicy(policy);
        requestQueue.add(jsonObjectRequest);
        Log.e("requestQueue", "requestQueue--> " + requestQueue);
    }

    private void retrieveJSON() {
        showSimpleProgressDailog("Loading...", "fetching Json", false);
        JSONObject jsonObject=new JSONObject();
        String url=null;
        url=URLstring;
        try {
            jsonObject.put("atype", "android");
            jsonObject.put("Email", "chapanu@gmail.com");
            jsonObject.put("position", "top");
        }catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject jsonObject = null;
                        Log.e("response", "response===> " + response);
                        try {
                            removeSimpleProgressDailog();
                            JSONObject obi = new JSONObject(response.toString());
                            if (obi.optString("msg").equals("Success")) {
                                JSONArray js2 = obi.getJSONArray("RecentUpdates");
                                Log.e("responsed", "response===> " + js2);
                                for (int i2 = 0; i2 < js2.length(); i2++) {
                                    JSONObject contacting3 = js2.getJSONObject(i2);
                                    DataModel dataModel = new DataModel();
                                    dataModel.setImgUrl(contacting3.getString("ImageUrl"));
                                    dataModel.setCountry(contacting3.getString("Title"));
                                    dataModel.setCity(contacting3.getString("Description"));
                                    dataModelArrayList.add(dataModel);
                                    Log.e("response", "response===> " + contacting3);
                                }
                                if (dataModelArrayList.size() > 0) {
                                    listAdapter = new ListAdapter(getActivity(), dataModelArrayList);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                                    recyclerView.setAdapter(listAdapter);
                                    listAdapter.notifyDataSetChanged();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("error", "error--->" + error.toString());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        int socketTimeout = 1800000;//60 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjectRequest.setRetryPolicy(policy);
        requestQueue.add(jsonObjectRequest);
        Log.e("requestQueue", "requestQueue--> " + requestQueue);
    }
    private void gtSelectedImages() {
        showSimpleProgressDailog("Loading...", "fetching Json", false);
        JSONObject jsonObject = new JSONObject();
        String url = null;
        url = Url;
        try {
            jsonObject.put("atype", "android");
            jsonObject.put("Email", "chapanu@gmail.com");
            jsonObject.put("position", "top");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    //                    @SuppressLint("NotifyDataSetChanged")
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject jsonObject = null;
                        Log.e("response", "response===> " + response);
                        try {
                            removeSimpleProgressDailog();
                            JSONObject obj = new JSONObject(response.toString());
                            if (obj.optString("msg").equals("Success")) {
//                            NameList.clear();
                                JSONArray js = obj.getJSONArray("Labels");
                                for (int i = 0; i < js.length(); i++) {
                                    JSONObject contacting = js.getJSONObject(i);
                                    JSONArray js1 = contacting.getJSONArray("items");
                                    for (int i1 = 0; i1 < js1.length(); i1++) {
                                        JSONObject contacting2 = js1.getJSONObject(i1);
                                        Models mm = new Models();
                                        String imag = contacting2.getString("ImageUrl");
                                        String images = contacting2.getString("VideoThumbnail");
//                                        String imag = contacting2.getString("ImageUrl");
                                        mm.setImgUrl(imag);
                                        mm.setImgURLS(images);
//                                        mm.setImgUrl(imag);
                                        mm.setName(contacting2.getString("Title"));
                                        mm.setNames(contacting2.getString("SubTitle"));

                                            image.add(mm);

                                            Log.e("response", "response===> " + contacting2.getString("Title").toString());
                                        }

                                        if (image.size() > 0) {
                                            adapters = new Adapters(getActivity(), image);
                                            recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                                            recyclerView2.setAdapter(adapters);
                                            adapters.notifyDataSetChanged();
                                        }
                                    }
                                }
                            } catch(JSONException e){
                                e.printStackTrace();
                            }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("error", "error--->" + error.toString());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        int socketTimeout = 1800000;//60 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjectRequest.setRetryPolicy(policy);
        requestQueue.add(jsonObjectRequest);
        Log.e("requestQueue", "requestQueue--> " + requestQueue);
    }
    private static void removeSimpleProgressDailog() {
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
    private void showSimpleProgressDailog(String title, String msg, boolean isCanceled) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(getContext(), title, msg);
                mProgressDialog.setCancelable(isCanceled);
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