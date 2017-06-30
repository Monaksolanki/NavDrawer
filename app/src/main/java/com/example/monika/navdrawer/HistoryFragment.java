package com.example.monika.navdrawer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment {
    private final String TAG=this.getClass().getSimpleName();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public HistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

      //  Log.d(TAG,mParam1);
        //Log.d(TAG,mParam2);


//        View view= inflater.inflate(R.layout.fragment_history, container, false);
//        ArrayList<Product> arrayList=new ArrayList<>();
//        Product p1=new Product("Coke",100,5);
//        Product p2=new Product("Coke",100,5);
//        Product p3=new Product("Coke",100,5);
//        Product p4=new Product("Coke",100,5);
//        Product p5=new Product("Coke",100,5);
//        Product p6=new Product("Coke",100,5);
//
//        arrayList.add(p1);
//        arrayList.add(p2);
//        arrayList.add(p3);
//        arrayList.add(p4);
//        arrayList.add(p5);
//        arrayList.add(p6);
//
//
//        ArrayAdapter<Product> adapter=new ArrayAdapter<Product>(HistoryFragment.this.getActivity(),
//                android.R.layout.simple_list_item_1,
//                arrayList);
//
//        ListView list= (ListView) view.findViewById(R.id.listView);
//        list.setAdapter(adapter);
        return inflater.inflate(R.layout.fragment_history, container, false);
    }
    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onDestroyView(){

        super.onDestroyView();
    }

}
