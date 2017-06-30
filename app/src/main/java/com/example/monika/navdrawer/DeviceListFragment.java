package com.example.monika.navdrawer;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DeviceListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DeviceListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeviceListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ListView mListView;
    private DeviceListAdapter mAdapter;

    private ArrayList mDeviceList;
    public static String EXTRA_ADDRESS = "device_address";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DeviceListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeviceListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DeviceListFragment newInstance(String param1, String param2) {
        DeviceListFragment fragment = new DeviceListFragment();
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
        View RootView = inflater.inflate(R.layout.fragment_device_list, container, false);

        Bundle bundle = getArguments();
        mDeviceList = bundle.getStringArrayList("device.list");
       // mDeviceList		= getIntent().getExtras().getParcelableArrayList("device.list");

        mListView = (ListView) RootView.findViewById(R.id.lv_paired);


        final ArrayAdapter adapter = new ArrayAdapter(getContext() ,android.R.layout.simple_list_item_1);
//        mAdapter		= new DeviceListAdapter(getContext());
        adapter.addAll(mDeviceList);
//        mAdapter.setData(mDeviceList);


//
//		mAdapter.setListener(new DeviceListAdapter.OnPairButtonClickListener() {
//			@Override
//			public void onPairButtonClick(int position) {
//				BluetoothDevice device = (BluetoothDevice) mDeviceList.get(position);
//                View view = null;
//                String info = ((TextView) view).getText().toString();
//                String address = info.substring(info.length() - 17);
//				if (device.getBondState() == BluetoothDevice.BOND_BONDED) {
//					unpairDevice(device);
//				} else {
//					showToast("Pairing...");
//
//					pairDevice(device);
//                    BluetoothFragment bluetoothFragment = new BluetoothFragment();
//                    FragmentManager manager=getActivity().getSupportFragmentManager();
//                    Bundle bundle = new Bundle();
//                    bundle.putString(EXTRA_ADDRESS,address);
//
//                    bluetoothFragment.setArguments(bundle);
//
//
//                    manager.beginTransaction().
//                            // setCustomAnimations(R.anim.zoom_in,R.anim.zoom_out).
//                                    replace(R.id.relativeLayout_for_fragment,bluetoothFragment,
//                                    "BluetoothFragment").commit();
//
//                }
//			}
//		});

        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("IN:","on click item listener");
                //Toast.makeText(getContext(), " ITEM CLICKED POSITION = "+String.valueOf(0), Toast.LENGTH_SHORT).show();
                String info = ((TextView) view).getText().toString();
                String address = info.substring(info.length() - 17);

                BluetoothDevice device = (BluetoothDevice) mDeviceList.get(position);



                BluetoothFragment bluetoothFragment = new BluetoothFragment();
                FragmentManager manager=getActivity().getSupportFragmentManager();
                Bundle bundle = new Bundle();
                bundle.putString(EXTRA_ADDRESS,address);

                bluetoothFragment.setArguments(bundle);


                manager.beginTransaction().
                        // setCustomAnimations(R.anim.zoom_in,R.anim.zoom_out).
                                replace(R.id.relativeLayout_for_fragment,bluetoothFragment,
                                bluetoothFragment.getTag()).commit();
                // Make an intent to start next activity.
                //Intent i = new Intent(DeviceListActivity.this, ledControl.class);

                //Change the activity.

                //i.putExtra(EXTRA_ADDRESS, address); //this will be received at ledControl (class) Activity
                //startActivity(i);
            }
        });


        getActivity().registerReceiver(mPairReceiver, new IntentFilter(BluetoothDevice.ACTION_BOND_STATE_CHANGED));

        return RootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    private AdapterView.OnItemClickListener myListClickListener = new AdapterView.OnItemClickListener()
    {
        public void onItemClick (AdapterView<?> av, View v, int arg2, long arg3)
        {
            // Get the device MAC address, the last 17 chars in the View

        }
    };

    @Override
    public void onDestroy() {
        getActivity().unregisterReceiver(mPairReceiver);

        super.onDestroy();
    }


    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void pairDevice(BluetoothDevice device) {
        try {
            Method method = device.getClass().getMethod("createBond", (Class[]) null);
            method.invoke(device, (Object[]) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void unpairDevice(BluetoothDevice device) {
        try {
            Method method = device.getClass().getMethod("removeBond", (Class[]) null);
            method.invoke(device, (Object[]) null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final BroadcastReceiver mPairReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)) {
                final int state 		= intent.getIntExtra(BluetoothDevice.EXTRA_BOND_STATE, BluetoothDevice.ERROR);
                final int prevState	= intent.getIntExtra(BluetoothDevice.EXTRA_PREVIOUS_BOND_STATE, BluetoothDevice.ERROR);

                if (state == BluetoothDevice.BOND_BONDED && prevState == BluetoothDevice.BOND_BONDING) {
                    showToast("Paired");
                } else if (state == BluetoothDevice.BOND_NONE && prevState == BluetoothDevice.BOND_BONDED){
                    showToast("Unpaired");
                }

                mAdapter.notifyDataSetChanged();
            }
        }
    };
}
