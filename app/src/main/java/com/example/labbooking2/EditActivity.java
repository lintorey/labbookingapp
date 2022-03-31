package com.example.labbooking2;

import androidx.appcompat.app.AppCompatActivity;

import com.example.labbooking2.API.APIRequestData;
import com.example.labbooking2.API.RetroServer;
import com.example.labbooking2.Model.DataModel;
import com.example.labbooking2.Model.ResponseModel;
import com.example.viewpager2tabs.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigInteger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditActivity extends AppCompatActivity {

    private String xName,xEmail,xNum,xAdd,xCat;
    private EditText etName,etEmail,etNum,etAdd,etCat;
    private Button btnEdit;
    private String yName,yEmail,yNum,yAdd,yCat;

    DataModel dm = new DataModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent receive = getIntent();
        xName = receive.getStringExtra("xName");
        xEmail = receive.getStringExtra("xEmail");
        xNum = receive.getStringExtra("xNum");
        xAdd = receive.getStringExtra("xAdd");
        xCat = receive.getStringExtra("xCat");

        etName = findViewById(R.id.editname);
        etEmail = findViewById(R.id.editmail);
        //etNum = findViewById(R.id.editcontactnumber);
        etAdd = findViewById(R.id.editaddress);
        etCat = findViewById(R.id.editcategory);
        btnEdit = findViewById(R.id.btn_change);

        etName.setText(xName);
        etEmail.setText(xEmail);
        //etNum.setText(xNum);
        etAdd.setText(xAdd);
        etCat.setText(xCat);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yName = etName.getText().toString();
                yEmail= etEmail.getText().toString();
                //yNum= etNum.getText().toString();
                yAdd= etAdd.getText().toString();
                yCat= etCat.getText().toString();

                updateData();
            }
        });
    }

    private void updateData(){
        APIRequestData ardData = RetroServer.connectRetrofit().create(APIRequestData.class);
        Call<ResponseModel> updateData = ardData.ardUpdateData(yName,yEmail,yAdd,xNum,yCat);

        updateData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int code = response.body().getCode();
                String message = response.body().getMessage();

                Toast.makeText(EditActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(EditActivity.this, "Fail to Change ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
