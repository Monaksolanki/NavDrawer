package com.example.monika.navdrawer;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    SessionManager session;
    HashMap<String,String> user;
    JSONObject aad;
    JSONArray details;
    EditText FirstName, LastName, EmailId, Password, No, Eno,BG,Address,PinCode;
    AlertDialogManager alert = new AlertDialogManager();

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



        FirstName = (EditText) view.findViewById(R.id.firstname);
        LastName = (EditText) view.findViewById(R.id.lastname);
        EmailId = (EditText) view.findViewById(R.id.emailid);
        Password = (EditText) view.findViewById(R.id.password);
        No = (EditText) view.findViewById(R.id.mobile_no);
        Eno = (EditText) view.findViewById(R.id.emergency_no);
        BG = (EditText) view.findViewById(R.id.bloodgroup);
        Address = (EditText) view.findViewById(R.id.address);
        PinCode = (EditText) view.findViewById(R.id.pincode);

        session = new SessionManager(getContext());
        session.checkLogin();
        user=session.getUserDetails();

        final Button Edit, Save;
        Edit = (Button) view.findViewById(R.id.edit);
        Save = (Button) view.findViewById(R.id.save);
        
        populateDetails();
        Save.setVisibility(View.INVISIBLE);

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirstName.setEnabled(true);
                LastName.setEnabled(true);
                EmailId.setEnabled(true);
                Password.setEnabled(true);
                No.setEnabled(true);
                Eno.setEnabled(true);
                BG.setEnabled(true);
                Address.setEnabled(true);
                PinCode.setEnabled(true);
                Save.setVisibility(View.VISIBLE);
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
                Eno.setEnabled(false);
                Address.setEnabled(false);
                PinCode.setEnabled(false);
                BG.setEnabled(false);
                updateProfile();
            }
        });



        return view;
    }

    private void updateProfile() {
        final RequestQueue adddata;
        adddata = Volley.newRequestQueue(getContext());
        final String aadhar = user.get(SessionManager.KEY_AADHAR);

        String url = "http://eitraproject.ga/telehealth/patient_update.php?" +
                "aadhar_id=" + aadhar +
                "&first_name=" + FirstName.getText().toString() +
                "&last_name=" + LastName.getText().toString() +
                "&email_id=" + EmailId.getText().toString() +
                "&mobile_no=" + No.getText().toString() +
                "&emergency_ph_no=" + Eno.getText().toString() +
                "&blood_group=" + BG.getText().toString() +
                "&address=" + Address.getText().toString() +
                "&pincode=" + PinCode.getText().toString();


        final StringRequest pushdata = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            public void onResponse(String response) {

                {

                    if (response.equals("0")) {

                        Toast.makeText(getContext(),"Uh oh...Error Occured. Please try again.",Toast.LENGTH_LONG).show();

                    } else if(response.equals("1")) {

                        Toast.makeText(getContext(),"Profile Details Updated Successfully",Toast.LENGTH_LONG).show();

                    }
                }

            }


        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                alert.showAlertDialog(getContext(), "Conection to server failed", "Oops...the server seems to be down. Please try " +
                        "again later", false);
            }
        }) {

        };

        adddata.add(pushdata);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onDestroyView(){

        super.onDestroyView();
    }
    private void populateDetails() {


        final RequestQueue adddata;
        adddata = Volley.newRequestQueue(getContext());
        final String aadhar = user.get(SessionManager.KEY_AADHAR);

        String url = "http://eitraproject.ga/telehealth/patient_profile.php?" +
                "aadhar_id=" + aadhar;

        final StringRequest pushdata = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            public void onResponse(String response) {
                //  Toast.makeText(LoginActivity.this,response,Toast.LENGTH_LONG).show();
                {
                    // For testing puspose aadhar, password is checked with sample data
                    // aadhar = test
                    // password = test
                    if (response.equals("0")) {


                        // aadhar / password doesn't match




                    } else {

                        try {
                            aad = new JSONObject(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                        try {
                            details = aad.getJSONArray(aadhar);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        Log.d("Response: ", response);

                        try {
                            Toast.makeText(getContext(),details.getString(0).toString(),Toast.LENGTH_SHORT);
                            Log.d("Name: ", details.getString(0).toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            FirstName.setText(details.getString(0).toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            LastName.setText(details.getString(1).toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            EmailId.setText(details.getString(2).toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            No.setText(details.getString(3).toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            Eno.setText(details.getString(4).toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            Address.setText(details.getString(5).toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            PinCode.setText(details.getString(6).toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            BG.setText(details.getString(7).toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } {
                    // user didn't entered aadhar or password
                    // Show alert asking him to enter the details

                }


            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                alert.showAlertDialog(getContext(), "Conection to server failed", "Oops...the server seems to be down. Please try " +
                        "again later", false);
            }
        }) {

        };

        adddata.add(pushdata);
    }
}




