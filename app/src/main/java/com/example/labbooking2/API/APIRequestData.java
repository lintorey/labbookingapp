package com.example.labbooking2.API;

import com.example.labbooking2.Model.DataModel;
import com.example.labbooking2.Model.ResponseModel;

import java.math.BigInteger;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIRequestData {

    @GET("retrieve_user.php")
    Call<ResponseModel> ardRetrieveData();

    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponseModel> ardDeleteData(
            @Field("Teachername") String Teachername
    );

    @FormUrlEncoded
    @POST("get.php")
    Call<ResponseModel> ardGetData(
            @Field("Teachername") String Teachername
    );

    @GET("getcontacts.php")
    Call<List<DataModel>> ardGetContact(
            @Query("item_type") String item_type,
            @Query("key") String keyword
    );

    @FormUrlEncoded
    @POST("create.php")
    Call<DataModel> ardCreateData(

            @Field("TeacherID") String TeacherID,
            @Field("Teachername") String Teachername,
            @Field("email") String email,
            @Field("Address") String Address,
            @Field("ContactNumber") String ContactNumber,
            @Field("category") String category

    );

    @FormUrlEncoded
    @POST("update.php")
    Call<ResponseModel> ardUpdateData(

            @Field("Teachername") String Teachername,
            @Field("email") String email,
            @Field("Address") String Address,
            @Field("ContactNumber") String ContactNumber,
            @Field("category") String category

    );

    /*@GET("retrieve_user.php")
    Call<ResponseModel> ardRetrieveData();

    @GET("retrieve_books.php")
    Call<MyModel2> ardRetrieveDataBook();

    @FormUrlEncoded
    @POST("create.php")
    Call<MyModel2> ardCreateData(

            @Field("Title") String Title,
            @Field("Publisher") String Publisher,
            @Field("Year") String Year,
            @Field("Availability") String Availability

    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponseModel> ardDeleteData(
            @Field("RollNo") String RollNo
    );

    @FormUrlEncoded
    @POST("get.php")
    Call<ResponseModel> ardGetData(
            @Field("RollNo") String RollNo
    );

    @FormUrlEncoded
    @POST("update.php")
    Call<ResponseModel> ardUpdateData(

            @Field("RollNo") String RollNo,
            @Field("Name") String Name,
            @Field("EmailId") String EmailId,
            @Field("MobNo") int MobNo

    );

    @GET("getcontacts.php")
    Call<List<DataModel>> ardGetContact(
                    @Query("item_type") String item_type,
                    @Query("key") String keyword
            );*/
}
