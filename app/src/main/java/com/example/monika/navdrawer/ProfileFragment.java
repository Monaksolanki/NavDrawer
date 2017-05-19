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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        EditText name;
        Button click;


        final EditText FirstName, LastName, EmailId, Password, No;
        FirstName = (EditText) view.findViewById(R.id.firstname);
        LastName = (EditText) view.findViewById(R.id.lastname);
        EmailId = (EditText) view.findViewById(R.id.emailid);
        Password = (EditText) view.findViewById(R.id.password);
        No = (EditText) view.findViewById(R.id.mobile_no);

        Button Edit, Save;
        Edit = (Button) view.findViewById(R.id.edit);
        Save = (Button) view.findViewById(R.id.save);

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirstName.setEnabled(true);
                LastName.setEnabled(true);
                EmailId.setEnabled(true);
                Password.setEnabled(true);
                No.setEnabled(true);
            }
        });

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirstName.setEnabled(false);
                LastName.setEnabled(false);
                EmailId.setEnabled(false);
                Password.setEnabled(false);
                No.setEnabled(false);
            }
        });


//        name = (EditText) view.findViewById(R.id.name);
//        click = (Button) view.findViewById(R.id.click);
//
//        click.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(ProfileFragment.this.getActivity(), "Click", Toast.LENGTH_SHORT).show();
//            }
//        });
        return view;
    }
}




