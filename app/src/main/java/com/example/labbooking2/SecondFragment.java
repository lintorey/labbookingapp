package com.example.labbooking2;
/*
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.viewpager2tabs.R;

public class FirstFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public FirstFragment() {
        // Required empty public constructor
    }


    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
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
        return inflater.inflate(R.layout.fragment_first, container, false);
    }
}*/

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.labbooking2.API.APIRequestData;
import com.example.labbooking2.API.RetroServer;
import com.example.labbooking2.Model.DataModel;
import com.example.viewpager2tabs.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondFragment extends Fragment {

    private EditText id,name,address,contact,mail,pass,cat;
    private Button submit;
    //private int sid;
    private String TeacherID,Teachername,Address,ContactNumber,email,password,category;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        id = (EditText) view.findViewById(R.id.tid);
        name = (EditText) view.findViewById(R.id.tname);
        address = (EditText)view.findViewById(R.id.taddress);
        contact = (EditText)view.findViewById(R.id.tcontnum);
        mail = (EditText)view.findViewById(R.id.tmymail);
        pass =(EditText) view.findViewById(R.id.tpass);
        cat =(EditText) view.findViewById(R.id.tcat);
        submit = (Button) view.findViewById(R.id.btn_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TeacherID = id.getText().toString();
                Teachername = name.getText().toString();
                Address = address.getText().toString();
                ContactNumber = contact.getText().toString();
                email = mail.getText().toString();
                password = pass.getText().toString();
                category = cat.getText().toString();


                if(TeacherID.trim().equals("")){
                    id.setError("Please enter id");
                }
                else if(Teachername.trim().equals("")){
                    name.setError("Please enter name");
                }
                else if(Address.trim().equals("")){
                    address.setError("Please enter address");
                }
                else if(ContactNumber.trim().equals("")){
                    contact.setError("Please enter contact");
                }
                else if(email.trim().equals("")){
                    mail.setError("Please enter mail");
                }
                else if(password.trim().equals("")){
                    pass.setError("Please enter pass");
                }
                else if(category.trim().equals("")){
                    cat.setError("Please enter category");
                }
                else{
                    createData();
                    id.getText().clear();
                    name.getText().clear();
                    address.getText().clear();
                    contact.getText().clear();
                    mail.getText().clear();
                    pass.getText().clear();
                    cat.getText().clear();
                }

            }
        });


        return view;
    }

    private void createData(){
        APIRequestData ardData = RetroServer.connectRetrofit().create(APIRequestData.class);
        Call<DataModel> storeData = ardData.ardCreateData(TeacherID,Teachername,email,Address,ContactNumber,category);

        storeData.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                //int code = response.body().getCode();
                //String message = response.body().getMessage();

                Toast.makeText(getActivity(), "New Data Added!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                Toast.makeText(getActivity(), "New Data Added!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}