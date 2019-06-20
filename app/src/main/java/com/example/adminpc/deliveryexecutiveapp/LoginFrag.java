package com.example.adminpc.deliveryexecutiveapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;


///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link LoginFrag.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link LoginFrag#newInstance} factory method to
// * create an instance of this fragment.
// */
public class LoginFrag extends Fragment {

    private final String DELIVERY_SIGNIN = Apis.BASE_URL + Apis.DELIVERY_SIGNIN;

    //  Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    //  Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    //    private OnFragmentInteractionListener mListener;
//
    public LoginFrag() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment LoginFrag.
//     */
    //  Rename and change types and number of parameters
//    public static LoginFrag newInstance(String param1, String param2) {
//        LoginFrag fragment = new LoginFrag();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        final EditText et_username = (TextInputEditText) view.findViewById(R.id.et_useraname);
        final EditText et_password = (TextInputEditText) view.findViewById(R.id.et_password);
        Button btn_login = view.findViewById(R.id.btn_login);

        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Alert");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener(

        ) {
            @Override
            public void onClick(View view) {
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                if (username.length() == 0 && password.length() == 0) {
                    alertDialog.setMessage("Please enter username and password!");
                    alertDialog.show();
                } else if (username.length() == 0) {
                    alertDialog.setMessage("Please enter username!");
                    alertDialog.show();
                } else if (password.length() == 0) {
                    alertDialog.setMessage("Please enter password!");
                    alertDialog.show();
                } else {
                    //TODO: Send the userid to the next Available activity after validating.
                    // RESTAURANT_API_ENDPOINT = http://localhost:8081/qeats/v1
                    // /delivery/signIn/username=”{user}”&password=”{pass}”
                    JsonLoadAndProcess jsonLoad = new JsonLoadAndProcess(getActivity());
                    String data = null;

                    try {
                       data = jsonLoad.new JsonTaskGET().execute(DELIVERY_SIGNIN + "username=" + username + "&password=" + password).get();
                       //data = jsonLoad.returnData();
                       //data = "test";
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //String data = jsonLoad.returnData();
                    if (data == null) {
                        Toast.makeText(getActivity(), "Error while making the connection!", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getActivity(), data, Toast.LENGTH_LONG).show();

                        try {
                            // TODO: Testing with file but should be placed a link.
//                            InputStream inputStream = getResources().getAssets().open("json-validate");
//                            int size = inputStream.available();
//                            byte[] buffer = new byte[size];
//                            inputStream.read(buffer);
//                            inputStream.close();
//                            data = new String(buffer, "UTF-8");

                            JSONObject jsonObject = new JSONObject(data);
                            String value = jsonObject.getString("validated");
                            if(value.equals("true")){
                                Intent i = new Intent(getActivity(), AvailableActivity.class);
                                // Toast.makeText(getActivity(), value, Toast.LENGTH_SHORT).show();
                                i.putExtra("username", username);
                                startActivity(i);
                            }else{
                                alertDialog.setMessage("Invalid credentials");
                                alertDialog.show();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Error while Parsing!", Toast.LENGTH_LONG).show();
                        }

                    }

                }
            }
        });

        return view;
    }

    //  Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        //  Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
