package com.example.myapplication.fragment;
import static android.content.Context.MODE_PRIVATE;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
public class SecondFragment extends Fragment {
//    private int currentSelected = 0;
//    RecyclerView recyclerView;
//    private String intro="intro", product = "product", location = "location", login = "login";
//    private MyAdapter myAdapter;
//    Button button;
//    FrameLayout frameLayout;
//    private LinearLayout llIntro, llProduct, llLocation, llLogin;
//    private TextView tvIntro, tvProduct, tvLocation, tvLogin;
//    private static ProgressDialog mProgressDialog;
//    private String Urls = "https://api.stormoverseas.com/API/MobileBanners/List?Type=TOP";
//    ArrayList<Model> images = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_second, container, false);
//        recyclerView = viewGroup.findViewById(R.id.recycler);
//        llIntro = viewGroup.findViewById(R.id.layout);
////        llProduct = viewGroup.findViewById(R.id.layout1);
////        llLocation = viewGroup.findViewById(R.id.layout2);
//        llLogin = viewGroup.findViewById(R.id.layout3);
////        tvIntro =  viewGroup.findViewById(R.id.most);
////        tvProduct =  viewGroup.findViewById(R.id.most1);
////        tvLocation =  viewGroup.findViewById(R.id.most2);
////        frameLayout = viewGroup.findViewById(R.id.container_body);
////        tvLogin =  viewGroup.findViewById(R.id.most3);
////        button=viewGroup.findViewById(R.id.button);
//        SharedPreferences pref;
//        SharedPreferences.Editor edit;
//        pref=getActivity().getSharedPreferences("app",MODE_PRIVATE);
//        edit=pref.edit();
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                edit.clear();
//                edit.apply();
//                getActivity().finish();
//            }
//        });
//        llIntro.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (currentSelected == 1) {
//                } else {
//                    tvIntro.setTextColor(getResources().getColor(R.color.black));
////                    llIntro.setBackgroundColor(getResources().getColor(R.color.black));
//                    unselectElse(1);
////                    (new IntroFragment(),R.id.container_body, false);
//                    removeAllFragment(new IntroFragment(), false, intro);
//                }
//            }
//        });
//        llProduct.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (currentSelected == 2) {
//                } else {
//                    tvProduct.setTextColor(getResources().getColor(R.color.black));
////                    llProduct.setBackgroundDrawable(getResources().getDrawable(R.color.black));
//                    unselectElse(2);
//                    removeAllFragment(new LocationFragment(), false, product);
//                }
//            }
//        });
//        llLocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (currentSelected == 3) {
//                } else {
//                    tvLocation.setTextColor(getResources().getColor(R.color.black));
////                    llLocation.setBackgroundColor(getResources().getColor(R.color.black));
//                    unselectElse(3);
//                    removeAllFragment(new ProductFragment(), false, intro);
//                }
//            }
//        });
//        llLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (currentSelected == 4) {
//                } else {
//                    tvLogin.setTextColor(getResources().getColor(R.color.black));
////                    llLogin.setBackgroundDrawable(getResources().getDrawable(R.color.black));
//                    unselectElse(4);
//                    removeAllFragment(new LoginFragment(), false, intro);
//                }
//            }
//        });
//        if (currentSelected == 1) {
//        } else {
//            tvLogin.setTextColor(getResources().getColor(R.color.black));
////                    llLogin.setBackgroundDrawable(getResources().getDrawable(R.color.black));
//            unselectElse(1);
//            removeAllFragment(new IntroFragment(), false, intro);
//        }
        return viewGroup;
//    }
//    public void setLoginState(boolean status,Integer userType) {
//        SharedPreferences sp = getActivity().getSharedPreferences("app",
//                MODE_PRIVATE);
////        number=sp.getString("number","number");
//        SharedPreferences.Editor ed = sp.edit();
////        ed.putString("number",getIntent().getStringExtra("number"));
//        ed.putBoolean("isLoggedIn", status);
//        ed.putInt("userType",userType);
//        ed.commit();
//    }
//
//    private void unselectElse(int current) {
//        switch (currentSelected) {
//            case 1:
//                tvIntro.setTextColor(getResources().getColor(R.color.purple_200));
////                llIntro.setBackgroundColor(getResources().getColor(R.color.purple_200));
//                currentSelected = current;
//                break;
//            case 2:
//                tvProduct.setTextColor(getResources().getColor(R.color.purple_200));
////                llIntro.setBackgroundColor(getResources().getColor(R.color.purple_200));
//                currentSelected = current;
//                break;
//            case 3:
//                tvLocation.setTextColor(getResources().getColor(R.color.purple_200));
////                llLocation.setBackgroundColor(getResources().getColor(R.color.purple_200));
//                currentSelected = current;
//                break;
//            case 4:
//                tvLogin.setTextColor(getResources().getColor(R.color.purple_200));
////                llLocation.setBackgroundColor(getResources().getColor(R.color.purple_200));
//                currentSelected = current;
//                break;
//        }
//        if (currentSelected==0){
//            currentSelected=current;
//        }
//    }
//    private void removeAllFragment(Fragment replaceFragment, boolean addToBackStack, String tag) {
//        FragmentManager manager = requireActivity().getSupportFragmentManager();
//        FragmentTransaction ft = manager.beginTransaction();
//        if (addToBackStack) {
//            ft.addToBackStack(tag);
//        }
//        ft.replace(R.id.container_body, replaceFragment);
//        ft.commitAllowingStateLoss();
//        getSelectedNames();
//}
//    private void getSelectedNames() {
//        showSimpleProgressDailog("Loading...", "fetching Json", false);
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, Urls,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.d("strrrrr", ">>" + response);
//                        try {
//                            removeSimpleProgressDailog();
//                            JSONObject obj = new JSONObject(response);
//                            if (obj.optString("msg").equals("success")) {
//                                JSONArray dataArray = obj.getJSONArray("mobileBannersList");
//                                for (int i = 0; i < dataArray.length(); i++) {
//                                    Model playerModel = new Model();
//                                    JSONObject dataobj = dataArray.getJSONObject(i);
//                                    playerModel.setImgURL(dataobj.getString("bannerUrl"));
//                                    images.add(playerModel);
//                                }
//                                setupReycler();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
//        requestQueue.add(stringRequest);
//    }
//    private void setupReycler() {
//        myAdapter = new MyAdapter(getContext(), images);
//        recyclerView.setAdapter(myAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
//    }
//    private static void removeSimpleProgressDailog() {
//        try {
//            if (mProgressDialog != null) {
//                if (mProgressDialog.isShowing()) {
//                    mProgressDialog.dismiss();
//                    mProgressDialog = null;
//                }
//            }
//        } catch (IllegalArgumentException ie) {
//            ie.printStackTrace();
//        } catch (RuntimeException re) {
//            re.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    private void showSimpleProgressDailog(String title, String msg, boolean isCanceled) {
//        try {
//            if (mProgressDialog == null) {
//                mProgressDialog = ProgressDialog.show(getContext(), title, msg);
//                mProgressDialog.setCancelable(isCanceled);
//            }
//            if (!mProgressDialog.isShowing()) {
//                mProgressDialog.show();
//            }
//        } catch (IllegalArgumentException ie) {
//            ie.printStackTrace();
//        } catch (RuntimeException re) {
//            re.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}