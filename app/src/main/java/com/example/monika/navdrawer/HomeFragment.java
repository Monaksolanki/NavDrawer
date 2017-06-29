package com.example.monika.navdrawer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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


public class HomeFragment extends Fragment {

    JSONObject aad;
    JSONArray details;
    TextView ecg,temp,pulse,symp;

    SessionManager session;
    HashMap<String,String> user;
    AlertDialogManager alert = new AlertDialogManager();

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        session = new SessionManager(getContext());
        session.checkLogin();
        user=session.getUserDetails();

        ecg = (TextView) view.findViewById(R.id.ecg);
        temp = (TextView) view.findViewById(R.id.temp);
        pulse = (TextView) view.findViewById(R.id.pulse);
        symp = (TextView) view.findViewById(R.id.symp);

        populateDetails();

        return view;
    }


    private void populateDetails() {


        final RequestQueue adddata;
        adddata = Volley.newRequestQueue(getContext());
        final String aadhar = user.get(SessionManager.KEY_AADHAR);

        String url = "http://eitraproject.ga/telehealth/patient_latest.php?" +
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
                            Log.d("Name: ", details.getString(0).toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            temp.setText(details.getString(1).toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            ecg.setText(details.getString(2).toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            pulse.setText(details.getString(3).toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            symp.setText(details.getString(4).toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(getContext(),"Details Updated",Toast.LENGTH_SHORT).show();

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
