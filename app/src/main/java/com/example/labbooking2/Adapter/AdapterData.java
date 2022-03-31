package com.example.labbooking2.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.labbooking2.API.APIRequestData;
import com.example.labbooking2.API.RetroServer;
//import com.example.labbooking2.EditActivity;
import com.example.labbooking2.EditActivity;
import com.example.labbooking2.FirstFragment;
import com.example.labbooking2.Model.DataModel;
import com.example.labbooking2.Model.ResponseModel;
import com.example.viewpager2tabs.R;
import com.google.android.material.transition.Hold;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.List;
import java.util.logging.LogRecord;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData>{

    private Context ctx;
    private List<DataModel> listEasyIlmu;
    private List<DataModel> listUser;
    private String Teachername;
    //FirstFragment GalleryFragment = new FirstFragment();

    public AdapterData(Context ctx, List<DataModel> listEasyIlmu) {
        this.ctx = ctx;
        this.listEasyIlmu = listEasyIlmu;
    }

    @NonNull
    @NotNull
    @Override
    public HolderData onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View Layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent,false);
        HolderData holder = new HolderData(Layout);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HolderData holder, int position) {
        DataModel dm = listEasyIlmu.get(position);


        holder.tvName.setText(dm.getTeachername());
        holder.tvAddress.setText("Address: "+dm.getAddress());
        holder.tvMobile.setText("Contact No. : "+dm.getContactNumber());
        holder.tvEmail.setText("Email: "+dm.getEmail());
        holder.tvCat.setText("Category: "+dm.getCategory());

    }



    @Override
    public int getItemCount() {
        return listEasyIlmu.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {

        TextView tvName,tvAddress,tvMobile,tvEmail,tvCat;
        ImageButton bttn;


        public HolderData(@NonNull @NotNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvAddress = itemView.findViewById(R.id.tv_address);
            tvMobile = itemView.findViewById(R.id.tv_mobile);
            tvEmail = itemView.findViewById(R.id.tv_email);
            tvCat = itemView.findViewById(R.id.tv_cat);

            bttn = itemView.findViewById((R.id.editbttn));


            bttn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Teachername = tvName.getText().toString();
                    //Intent sendData = new Intent(ctx, EditActivity.class);
                    //ctx.startActivity(sendData);
                    getData();
                }
            });


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder dialogMessage = new AlertDialog.Builder(ctx);
                    dialogMessage.setMessage("Are you sure want to delete?");
                    dialogMessage.setCancelable(true);

                    Teachername = tvName.getText().toString();

                    dialogMessage.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deleteData();
                            dialog.dismiss();
                        }
                    });/*dialogMessage.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getData();
                            dialog.dismiss();
                        }
                    });*/

                    dialogMessage.show();

                    return false;
                }
            });
        }

/*
        private void deleteData(){
            APIRequestData ardData = RetroServer.connectRetrofit().create(APIRequestData.class);
            Call<ResponseModel> deleteData = ardData.ardDeleteData(Teachername);

            deleteData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    int code = response.body().getCode();
                    String message = response.body().getMessage();

                    Toast.makeText(ctx, "Done!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(ctx, "Fail", Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void getData(){
            APIRequestData ardData = RetroServer.connectRetrofit().create(APIRequestData.class);
            Call<ResponseModel> grabData = ardData.ardGetData(Teachername);

            grabData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    int code = response.body().getCode();
                    String message = response.body().getMessage();
                    listUser = response.body().getData();

                    String varName = listUser.get(0).getTeachername();
                    String varAddress = listUser.get(0).getAddress();
                    String varMobile = listUser.get(0).getContactNumber();
                    String varEmail = listUser.get(0).getEmail();
                    String varCat = listUser.get(0).getCategory();

                    Intent sendData = new Intent(ctx, EditActivity.class);
                    sendData.putExtra("xName",varName);
                    sendData.putExtra("xAdd",varAddress);
                    sendData.putExtra("xNum",varMobile);
                    sendData.putExtra( "xEmail",varEmail);
                    sendData.putExtra( "xCat",varCat);

                    ctx.startActivity(sendData);

                    //Toast.makeText(ctx, "test: " +varEmail+varIdRollNo+varName+varMobNo, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(ctx, "Done", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(ctx, "Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }*/


        private void deleteData(){
            APIRequestData ardData = RetroServer.connectRetrofit().create(APIRequestData.class);
            Call<ResponseModel> deleteData = ardData.ardDeleteData(Teachername);

            deleteData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    int code = response.body().getCode();
                    String message = response.body().getMessage();

                    Toast.makeText(ctx, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(ctx, "Connect to server fail", Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void getData(){
            APIRequestData ardData = RetroServer.connectRetrofit().create(APIRequestData.class);
            Call<ResponseModel> grabData = ardData.ardGetData(Teachername);

            grabData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    int code = response.body().getCode();
                    String message = response.body().getMessage();
                    listUser = response.body().getData();

                    String varName = listUser.get(0).getTeachername();
                    String varAddress = listUser.get(0).getAddress();
                    String varMobile = listUser.get(0).getContactNumber();
                    String varEmail = listUser.get(0).getEmail();
                    String varCat = listUser.get(0).getCategory();
                    //BigInteger varMobNo = listUser.get(0).getMobNo();
                    //int varMobno = varMobNo.intValue();

                    Intent sendData = new Intent(ctx, EditActivity.class);
                    sendData.putExtra("xName",varName);
                    sendData.putExtra( "xEmail",varEmail);
                    sendData.putExtra("xNum",varMobile);
                    sendData.putExtra("xAdd",varAddress);
                    sendData.putExtra( "xCat",varCat);

                    ctx.startActivity(sendData);
                    //Toast.makeText(ctx, "test: " +varEmail+varIdRollNo+varName+varMobNo, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(ctx, "Done", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(ctx, "Connect to server fail", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
