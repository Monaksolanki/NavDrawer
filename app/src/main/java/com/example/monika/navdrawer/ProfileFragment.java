package com.example.monika.navdrawer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        EditText name;
        Button click;

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        name = (EditText) view.findViewById(R.id.name);
        click = (Button) view.findViewById(R.id.click);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileFragment.this.getActivity(), "Click", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}





