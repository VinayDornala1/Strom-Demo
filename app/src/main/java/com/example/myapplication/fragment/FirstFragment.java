package com.example.myapplication.fragment;
import static android.content.Context.MODE_PRIVATE;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.VideoCounselling;
import com.example.myapplication.adapter.MyAdapter;
import com.example.myapplication.model.Model;
import com.smarteist.autoimageslider.SliderView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
public class FirstFragment extends Fragment {
    private int currentSelected = 0;
    private String intro = "intro", product = "product", location = "location", login = "login";
    private LinearLayout llIntro, llProduct, llLocation, llLogin;
    private TextView textView, textView1, textView2, textView3;
    private ImageView imageView4,imageView1,imageView2,imageView3;
    DrawerLayout drawerLayout;
    ImageView imageView;
    SliderView recyclerView;
    LinearLayout linearLayout, linearLayout1,linearLayout2,linearLayout3,linearLayout4,share,counselling,training;
    private MyAdapter myAdapter;
    ThirdFragment thirdFragment=new ThirdFragment();
    Model mm;
    ArrayList<Model> images = new ArrayList<>();
    private String Urls = "https://app.stormoverseas.com/API/MobileBanners/List?Email=";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_first, container, false);
//        (MainActivity)getActivity().ClickItemSelected();
        imageView = viewGroup.findViewById(R.id.image);
        training=viewGroup.findViewById(R.id.training);
        linearLayout3=viewGroup.findViewById(R.id.application);
        counselling=viewGroup.findViewById(R.id.counselling);
        linearLayout = viewGroup.findViewById(R.id.drawer);
        linearLayout1=viewGroup.findViewById(R.id.click);
        share=viewGroup.findViewById(R.id.share);
        linearLayout4=viewGroup.findViewById(R.id.search);
        linearLayout2=viewGroup.findViewById(R.id.alumni);
        drawerLayout = viewGroup.findViewById(R.id.layout1);
        recyclerView = viewGroup.findViewById(R.id.slider);
        llIntro = viewGroup.findViewById(R.id.layout3);
        llProduct = viewGroup.findViewById(R.id.layout4);
        llLocation = viewGroup.findViewById(R.id.layout5);
        llLogin = viewGroup.findViewById(R.id.layout6);
        textView=viewGroup.findViewById(R.id.textView);
        textView1=viewGroup.findViewById(R.id.textView1);
        textView2=viewGroup.findViewById(R.id.textView2);
        textView3=viewGroup.findViewById(R.id.textView3);
        imageView1=viewGroup.findViewById(R.id.image1);
        imageView2=viewGroup.findViewById(R.id.image2);
        imageView3=viewGroup.findViewById(R.id.image3);
        imageView4=viewGroup.findViewById(R.id.image4);
        myAdapter = new MyAdapter(getActivity(), images);
        training.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickCounselling();
                drawerLayout.closeDrawer(Gravity.LEFT);
            }
        });
        counselling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), VideoCounselling.class);
                startActivity(intent);
                drawerLayout.closeDrawer(Gravity.LEFT);
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String Body="Download this App";
                String Sub="https://stormoverseas.com/app";
                intent.putExtra(Intent.EXTRA_TEXT,Body);
                intent.putExtra(Intent.EXTRA_TEXT,Sub);
                startActivity(Intent.createChooser(intent,"Share using"));
                drawerLayout.closeDrawer(Gravity.LEFT);
            }
        });
        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).ClickSelected();
            }
        });
        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).ClickItem();
                drawerLayout.closeDrawer(Gravity.LEFT);
            }
        });
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickAlumini();
                drawerLayout.closeDrawer(Gravity.LEFT);
            }
        });
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                bottomNavigationView.setOnNavigationItemSelectedListener(myNavigationItemListener);
                ((MainActivity)getActivity()).ClickItemSelected();
                drawerLayout.closeDrawer(Gravity.LEFT);
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment,thirdFragment).commit();
            }
        });
        SharedPreferences pref;
        SharedPreferences.Editor edit;
        pref = getActivity().getSharedPreferences("app", MODE_PRIVATE);
        edit = pref.edit();
        imageView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RtlHardcoded")
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        llIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickMostPopular();
            }
        });
        llProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickAlumini();
            }
        });
        llLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickCounselling();
            }
        });
        llLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickEvent();
            }
        });
        llIntro.setBackgroundResource(R.drawable.tabselect);
        textView.setTextColor(Color.parseColor("#0063ad"));
        imageView1.setColorFilter(Color.parseColor("#0063ad"));
        if (currentSelected == 1) {
        } else {
//            tvLogin.setTextColor(getResources().getColor(R.color.black));
//                    llLogin.setBackgroundDrawable(getResources().getDrawable(R.color.black));
            unselectElse(1);
            removeAllFragment(new IntroFragment(), false, intro);
        }
        getSelectedNames();
        return viewGroup;
    }
    public void onClickEvent(){
        imageView1.setColorFilter(Color.parseColor("#CCCACA"));
        imageView2.setColorFilter(Color.parseColor("#CCCACA"));
        imageView3.setColorFilter(Color.parseColor("#CCCACA"));
        imageView4.setColorFilter(Color.parseColor("#0063ad"));
        llIntro.setBackgroundResource(R.drawable.radiu);
        textView.setTextColor(Color.parseColor("#CCCACA"));
        llProduct.setBackgroundResource(R.drawable.radiu);
        textView1.setTextColor(Color.parseColor("#CCCACA"));
        llLocation.setBackgroundResource(R.drawable.radiu);
        textView2.setTextColor(Color.parseColor("#CCCACA"));
        llLogin.setBackgroundResource(R.drawable.tabselect);
        textView3.setTextColor(Color.parseColor("#0063ad"));
        if (currentSelected == 4) {
        } else {
            unselectElse(4);
            removeAllFragment(new LoginFragment(), false, login);
        }
    }

    public void onClickMostPopular(){
        llIntro.setBackgroundResource(R.drawable.tabselect);
        textView.setTextColor(Color.parseColor("#0063ad"));
        imageView1.setColorFilter(Color.parseColor("#0063ad"));
        imageView2.setColorFilter(Color.parseColor("#CCCACA"));
        imageView3.setColorFilter(Color.parseColor("#CCCACA"));
        imageView4.setColorFilter(Color.parseColor("#CCCACA"));
        llProduct.setBackgroundResource(R.drawable.radiu);
        textView1.setTextColor(Color.parseColor("#CCCACA"));
        llLocation.setBackgroundResource(R.drawable.radiu);
        textView2.setTextColor(Color.parseColor("#CCCACA"));
        llLogin.setBackgroundResource(R.drawable.radiu);
        textView3.setTextColor(Color.parseColor("#CCCACA"));
        if (currentSelected != 1) {
            unselectElse(1);
//                    (new IntroFragment(),R.id.container_body, false);
            removeAllFragment(new IntroFragment(), false, intro);
        }
    }
    public void onClickAlumini(){
        imageView1.setColorFilter(Color.parseColor("#CCCACA"));
        imageView2.setColorFilter(Color.parseColor("#0063ad"));
        imageView3.setColorFilter(Color.parseColor("#CCCACA"));
        imageView4.setColorFilter(Color.parseColor("#CCCACA"));
        llIntro.setBackgroundResource(R.drawable.radiu);
        textView.setTextColor(Color.parseColor("#CCCACA"));
        llProduct.setBackgroundResource(R.drawable.tabselect);
        textView1.setTextColor(Color.parseColor("#0063ad"));
        llLocation.setBackgroundResource(R.drawable.radiu);
        textView2.setTextColor(Color.parseColor("#CCCACA"));
        llLogin.setBackgroundResource(R.drawable.radiu);
        textView3.setTextColor(Color.parseColor("#CCCACA"));
        if (currentSelected == 2) {
        } else {
            unselectElse(2);
            removeAllFragment(new LocationFragment(), false, product);
        }
    }
    public void onClickCounselling(){
        imageView1.setColorFilter(Color.parseColor("#CCCACA"));
        imageView2.setColorFilter(Color.parseColor("#CCCACA"));
        imageView3.setColorFilter(Color.parseColor("#0063ad"));
        imageView4.setColorFilter(Color.parseColor("#CCCACA"));
        llIntro.setBackgroundResource(R.drawable.radiu);
        textView.setTextColor(Color.parseColor("#CCCACA"));
        llProduct.setBackgroundResource(R.drawable.radiu);
        textView1.setTextColor(Color.parseColor("#CCCACA"));
        llLocation.setBackgroundResource(R.drawable.tabselect);
        textView2.setTextColor(Color.parseColor("#0063ad"));
        llLogin.setBackgroundResource(R.drawable.radiu);
        textView3.setTextColor(Color.parseColor("#CCCACA"));
        if (currentSelected == 3) {
        } else {
            unselectElse(3);
            removeAllFragment(new ProductFragment(), false, location);
        }
    }

    private void unselectElse(int current) {
        switch (currentSelected) {
            case 1:
                currentSelected = current;
                break;
            case 2:
                currentSelected = current;
                break;
            case 3:
                currentSelected = current;
                break;
            case 4:

                currentSelected = current;
                break;
        }
        if (currentSelected == 0) {
            currentSelected = current;
        }
    }

    public void removeAllFragment(Fragment replaceFragment, boolean addToBackStack, String tag) {

        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
//        manager.popBackStackImmediate(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
        if (addToBackStack) {
            ft.addToBackStack(tag);
        }
        ft.replace(R.id.container_body, replaceFragment);
        ft.commitAllowingStateLoss();
    }

    private void getSelectedNames() {
        JSONObject jsonObject = new JSONObject();
        String url = null;
        url = Urls + "vinaybabu335@gmail.com";
        Log.e("Api", "Apis>>" + url);

        Log.e("jsonObject", "jsonObject-->" + jsonObject);
        Log.e("url", "url-->" + url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("strrrrr", ">>" + response);
                        try {
                            JSONObject obj = new JSONObject(response.toString());
                            if (obj.optString("msg").equals("success")) {
                                JSONArray dataArray = obj.getJSONArray("MobileBannersList");
                                for (int i = 0; i < dataArray.length(); i++) {
                                    JSONObject object = (JSONObject) dataArray.get(i);
                                    mm = new Model();
                                    mm.setImgURL(object.getString("BannerUrl"));
                                    images.add(mm);
                                }
                                recyclerView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
                                recyclerView.setSliderAdapter(myAdapter);
                                recyclerView.setScrollTimeInSec(3);
                                recyclerView.setAutoCycle(true);
                                recyclerView.startAutoCycle();
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
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        int socketTimeout = 1800000;//60 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjectRequest.setRetryPolicy(policy);
        requestQueue.add(jsonObjectRequest);
        Log.e("requestQueue", "requestQueue--> " + requestQueue);
    }
}

