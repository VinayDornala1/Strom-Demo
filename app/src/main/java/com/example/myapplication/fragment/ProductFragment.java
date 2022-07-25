package com.example.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.VideoCounselling;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductFragment#} factory method to
 * create an instance of this fragment.
 */
public class ProductFragment extends Fragment {
Button button;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup=(ViewGroup) inflater.inflate(R.layout.fragment_product, container, false);
        button=viewGroup.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), VideoCounselling.class);
                startActivity(intent);
            }
        });
        return viewGroup;
    }
}