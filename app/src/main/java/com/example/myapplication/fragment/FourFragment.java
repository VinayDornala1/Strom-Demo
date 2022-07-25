package com.example.myapplication.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;
import com.example.myapplication.StudyModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FourFragment#} factory method to
 * create an instance of this fragment.
 */
public class FourFragment extends Fragment {
RecyclerView recyclerView;
ArrayList<StudyModel> studyModels=new ArrayList<>();

StudyAdapter studyAdapter;
    private String Urls = "https://app.stormoverseas.com/API/StudentsAPI/GetSubjectsAPI";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup=(ViewGroup) inflater.inflate(R.layout.fragment_four, container, false);
        recyclerView=viewGroup.findViewById(R.id.recycler);
        getStudyArea();
        return viewGroup;
    }

    private void getStudyArea() {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, Urls,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("strrrrr", ">>" + response);
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (obj.optString("msg").equals("success")) {
                                JSONArray dataArray = obj.getJSONArray("Subjects");
                                for (int i = 0; i < dataArray.length(); i++) {
                                    StudyModel playerModel = new StudyModel();
                                    JSONObject dataobj = dataArray.getJSONObject(i);
                                    playerModel.setImage(dataobj.getString("Logo"));
                                    playerModel.setName(dataobj.getString("SubjectName"));
                                    studyModels.add(playerModel);
                                }
                                setupReycler();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    private void setupReycler() {
        studyAdapter = new StudyAdapter(getActivity(), studyModels);
        recyclerView.setAdapter(studyAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
    }
}