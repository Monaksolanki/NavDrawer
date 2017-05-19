package com.example.monika.navdrawer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TableRow;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;

public class RegisterActivity extends AppCompatActivity
{


    EditText obj_name,obj_lname,obj_email,obj_pass,obj_add,obj_num,obj_adhar,obj_dob,
            obj_pin,obj_enum,obj_age;

    TextView obj_text_email;

    RadioButton m, f;
    View date;
    String gender;
    Spinner obj_bg,doc1,doc2,doc3,doc4,doc5;

    ArrayAdapter<String> spinnerAdapterdoc1;
    ArrayAdapter<String> spinnerAdapterdoc2;
    ArrayAdapter<String> spinnerAdapterdoc3;
    ArrayAdapter<String> spinnerAdapterdoc4;
    ArrayAdapter<String> spinnerAdapterdoc5;

    JSONObject name,aadhar;
    JSONArray json_name,json_aadhar;

    Button obj_submit,obj_login;
    Resources res;
    String[] doc_names;
    /*for validating Birthdate*/
    private Pattern pattern;
    private Matcher matcher_bday;
    private static final String DATE_PATTERN =
            "((19|20)\\d\\d)[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])";//yyyy/mm/dd
    //"(0[1-9]|1[012])[/.-](0[1-9]|[12][0-9]|3[01])[/.-]((19|20)\\d\\d)";
    //"^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d{2}$"; //mm/dd/yyyy vallid pattern


    /*for validating email id*/

    private Matcher matcher_email;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    //"[a-zA-Z0-9._-]+@[a-z]+\\\\.+[a-z]+";
    AlertDialogManager alert = new AlertDialogManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/



        obj_name= (EditText) findViewById(R.id.firstname);
        obj_lname= (EditText) findViewById(R.id.lastname);
        obj_email= (EditText) findViewById(R.id.emailid);
        obj_text_email= (TextView) findViewById(R.id.email);
        obj_pass= (EditText) findViewById(R.id.password);
        obj_add= (EditText) findViewById(R.id.address);
        obj_pin= (EditText) findViewById(R.id.pincode);
        obj_num= (EditText) findViewById(R.id.mobile_no);
        obj_enum= (EditText) findViewById(R.id.emergency_no);
        obj_adhar= (EditText) findViewById(R.id.adhar_id);
        obj_dob=(EditText) findViewById(R.id.dob);
        obj_age= (EditText) findViewById(R.id.age);
        obj_bg= (Spinner) findViewById(R.id.bloodgroup);
        res = getResources();
        doc_names = res.getStringArray(R.array.doctor_list);




        doc1 = (Spinner)findViewById(R.id.doctor1);
        doc2 = (Spinner)findViewById(R.id.doctor2);
        doc3 = (Spinner)findViewById(R.id.doctor3);
        doc4 = (Spinner)findViewById(R.id.doctor4);
        doc5 = (Spinner)findViewById(R.id.doctor5);


        spinnerAdapterdoc1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerAdapterdoc2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerAdapterdoc3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerAdapterdoc4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerAdapterdoc5 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);

        spinnerAdapterdoc1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAdapterdoc2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAdapterdoc3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAdapterdoc4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAdapterdoc5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        doc1.setAdapter(spinnerAdapterdoc1);
        spinnerAdapterdoc1.add("Select Any One");

        doc2.setAdapter(spinnerAdapterdoc1);
        spinnerAdapterdoc2.add("Select Any One");

        doc3.setAdapter(spinnerAdapterdoc1);
        spinnerAdapterdoc3.add("Select Any One");

        doc4.setAdapter(spinnerAdapterdoc1);
        spinnerAdapterdoc4.add("Select Any One");

        doc5.setAdapter(spinnerAdapterdoc1);
        spinnerAdapterdoc5.add("Select Any One");

        getDocInfo();


        m= (RadioButton) findViewById(R.id.male);
        f= (RadioButton) findViewById(R.id.female);

        obj_submit= (Button) findViewById(R.id.submit);
        obj_login= (Button) findViewById(R.id.log);

        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender="M";
            }
        });

        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender="F";
            }
        });





        obj_submit.setOnClickListener(new View.OnClickListener()
                                      {


                                          @Override
                                          public void onClick(View v) {

                                              matcher_bday = Pattern.compile(DATE_PATTERN).matcher(obj_dob.getText().toString());//for bday validation
                                              matcher_email= Pattern.compile(EMAIL_PATTERN).matcher(obj_email.getText().toString());//for email validation
                                              //if(obj_name.equals(""))
                                              //   Toast.makeText(MainActivity.this,"Enter First Name",Toast.LENGTH_SHORT).show();

                                              if(obj_name.getText().toString().length()==0){
                                                  obj_name.setError("First name not entered");
                                                  obj_name.requestFocus();
                                              }
                                              else if(obj_lname.getText().toString().length()==0){
                                                  obj_lname.setError("Last name not entered");
                                                  obj_lname.requestFocus();
                                              }

                                              else if(obj_email.getText().toString().length()==0){
                                                  obj_email.setError("Email not entered");
                                                  obj_email.requestFocus();
                                              }

                                              else if (!matcher_email.matches()) {

                                                  Toast.makeText(RegisterActivity.this, "Invalid email id", Toast.LENGTH_SHORT).show();
                                              }
                                              else if(obj_pass.getText().toString().length()==0){
                                                  obj_pass.setError("Password not entered");
                                                  obj_pass.requestFocus();
                                              }

                                              else if(obj_add.getText().toString().length()==0){
                                                  obj_add.setError("address not entered");
                                                  obj_add.requestFocus();
                                              }

                                              else if(obj_pin.getText().toString().length()==0){
                                                  obj_pin.setError("pincode not entered");
                                                  obj_pin.requestFocus();
                                              }
                                              else if(obj_num.getText().toString().length()==0){
                                                  obj_num.setError("Mobile number is not entered");
                                                  obj_num.requestFocus();
                                              }
                                              else if(obj_num.getText().toString().length()!=10){
                                                  //Toast.makeText(MainActivity.this,"Mobile number should be of 10 digits",Toast.LENGTH_SHORT).show();
                                                  obj_num.setError("Mobile number should be of 10 digits");
                                                  obj_num.requestFocus();
                                              }


                                              else if(obj_enum.getText().toString().length()==0){
                                                  obj_enum.setError("emergency number is not entered");
                                                  obj_enum.requestFocus();
                                              }

                                              else if(obj_enum.getText().toString().length()!=10){
                                                  // Toast.makeText(MainActivity.this,"Emergency number should be of 10 digits",Toast.LENGTH_SHORT).show();
                                                  obj_enum.setError("emergency number should be of 10 digits");
                                                  obj_enum.requestFocus();
                                              }

                                              else if(obj_adhar.getText().toString().length()==0){
                                                  obj_adhar.setError("Aadhar number not entered");
                                                  obj_adhar.requestFocus();
                                              }

                                              else if(obj_adhar.getText().toString().length()!=12){
                                                  //Toast.makeText(MainActivity.this,"Adhar number should be of 12 digits",Toast.LENGTH_SHORT).show();
                                                  obj_adhar.setError("Aadhar number should be of 12 digits");
                                                  obj_adhar.requestFocus();
                                              }
                                              else if(obj_dob.getText().toString().length()==0){
                                                  obj_dob.setError("Date of Birth not entered");
                                                  obj_dob.requestFocus();
                                              }


                                              else if (!matcher_bday.matches()) {

                                                  // Toast.makeText(MainActivity.this, "Birthday format should be yyyy/mm/dd!", Toast.LENGTH_SHORT).show();
                                                  obj_dob.setError("Birthday format should be yyyy-mm-dd!");
                                                  obj_dob.requestFocus();
                                              }

                                              else if(!(m.isChecked()) && !(f.isChecked()))
                                              {
                                                  Toast.makeText(RegisterActivity.this,"Select Gender",Toast.LENGTH_LONG).show();
                                              }

                                              else if (obj_bg.getSelectedItem().toString().trim().equals("Select one")) {
                                                  Toast.makeText(RegisterActivity.this, "Enter blood group", Toast.LENGTH_SHORT).show();

                                              }

                                              else if(obj_age.getText().toString().length()==0){
                                                  obj_age.setError("age not entered");
                                                  obj_age.requestFocus();
                                              }


                                              else {

                                                  publishData();
                                                  //Intent i = new Intent(MainActivity.this, ConfirmActivity.class);
                                                  //startActivity(i);
                                              }

                                          }


                                      }


        );

        obj_login.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {

                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getDocInfo() {
        final RequestQueue adddata;
        adddata= Volley.newRequestQueue(this);

        String url="http://eitraproject.ga/telehealth/doctor_list1.php";


        final StringRequest pushdata=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    name = new JSONObject(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    aadhar = new JSONObject(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    json_name = name.getJSONArray("name");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    json_aadhar = aadhar.getJSONArray("aadhar_id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
                String name1 = null;
                try {
                    name1 = json_name.getString(0).toString().trim();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // Toast.makeText(MainActivity.this,name1,Toast.LENGTH_LONG).show();

                //  String[] separated = response.split(",");
                int i;
                for(i=0; i < json_name.length() ; i++) {
                    try {
                        spinnerAdapterdoc1.add(json_name.getString(i).toString().trim());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        spinnerAdapterdoc2.add(json_name.getString(i).toString().trim());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        spinnerAdapterdoc3.add(json_name.getString(i).toString().trim());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        spinnerAdapterdoc4.add(json_name.getString(i).toString().trim());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        spinnerAdapterdoc5.add(json_name.getString(i).toString().trim());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                spinnerAdapterdoc1.notifyDataSetChanged();
                spinnerAdapterdoc2.notifyDataSetChanged();
                spinnerAdapterdoc3.notifyDataSetChanged();
                spinnerAdapterdoc4.notifyDataSetChanged();
                spinnerAdapterdoc5.notifyDataSetChanged();

                Toast.makeText(RegisterActivity.this,"Doctor List Updated",Toast.LENGTH_LONG).show();




            }
        }, new Response.ErrorListener(){


            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

        };
        adddata.add(pushdata);
    }


    public void publishData() {

        final RequestQueue adddata;
        adddata= Volley.newRequestQueue(this);
        String aadharidofdoc1 = null;
        String aadharidofdoc2 = null;
        String aadharidofdoc3 = null;
        String aadharidofdoc4 = null;
        String aadharidofdoc5 = null;
        try {
            aadharidofdoc1 = json_aadhar.getString(doc1.getSelectedItemPosition() - 1).toString();
            Log.d("Doc 1 Position:",aadharidofdoc1);
            //Log.d("",doc1.getSelectedItemId())
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            aadharidofdoc2 = json_aadhar.getString(doc2.getSelectedItemPosition() - 1).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            aadharidofdoc3 = json_aadhar.getString(doc3.getSelectedItemPosition() - 1).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            aadharidofdoc4 = json_aadhar.getString(doc4.getSelectedItemPosition() - 1).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            aadharidofdoc5 = json_aadhar.getString(doc5.getSelectedItemPosition() - 1).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url="http://eitraproject.ga/telehealth/patient_reg.php?"+
                "aadhar_id="+obj_adhar.getText().toString()+
                "&password="+obj_pass.getText().toString()+
                "&first_name="+obj_name.getText().toString()+
                "&last_name="+obj_lname.getText().toString()+
                "&email_id="+obj_email.getText().toString()+
                "&mobile_no="+obj_num.getText().toString()+
                "&emergency_ph_no="+obj_enum.getText().toString()+
                "&address="+obj_add.getText().toString()+
                "&pincode="+obj_pin.getText().toString()+
                "&dob="+obj_dob.getText().toString()+
                "&age="+obj_age.getText().toString()+
                "&gender="+gender.toString()+
                "&blood_group="+obj_bg.getSelectedItem().toString()+
                "&doc1="+aadharidofdoc1+
                "&doc2="+aadharidofdoc2+
                "&doc3="+aadharidofdoc3+
                "&doc4="+aadharidofdoc4+
                "&doc5="+aadharidofdoc5;

        Toast.makeText(this,url,Toast.LENGTH_LONG).show();

        final StringRequest pushdata=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(RegisterActivity.this,response,Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener(){


            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

        };
        adddata.add(pushdata);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}