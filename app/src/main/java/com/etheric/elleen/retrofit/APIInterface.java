package com.etheric.elleen.retrofit;


import com.etheric.elleen.responseModel.SubmitFormResponseModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface APIInterface {


    public static final int SUBMIT_DETAILS_SUCCESS= 1;
    public static final int SUBMIT_DETAILS_FAILED= 2;



    /*@Headers({"Accept: application/json"})
    @POST("/Cliknfixx/api/editProfile")
    Call<SaveUserProfileResponseModel> saveUserProfile(
            @Query("name") String name,
            @Query("phone") String phone,
            @Query("blood_group") String blood_group,
            @Query("age") String age,
            @Query("address") String address,
            //@Query("user_image") String user_image,
            @Header("token") String token);*/

    @Multipart
    @POST("form.php")
    Call<SubmitFormResponseModel> submitForm(
            @Part("mobile_number") RequestBody mobile,
            @Part("city") RequestBody city,
            @Part("town") RequestBody town,
            @Part("company_name") RequestBody industry,
            @Part("question_1") RequestBody rb1,
            @Part("question_2") RequestBody rb2,
            @Part("question_3") RequestBody rb3,
            @Part("question_4") RequestBody rb4,
            @Part("question_5") RequestBody rb5,
            @Part("question_6") RequestBody rb6,
            @Part("question_7") RequestBody rb7,
            /*@Part("question_8") RequestBody rb8,
            @Part("question_9") RequestBody rb9,
            @Part("question_10") RequestBody rb10,*/
            @Part("signature") RequestBody signatureImgName, @Part MultipartBody.Part signature,
            @Part("image") RequestBody userImgName, @Part MultipartBody.Part userImg
    );

//    @Multipart
//    @POST("form.php")
//    Call<SubmitFormResponseModel> submitForm(
//            @Query("mobile_number") String mobile,
//            @Query("city") String city,
//            @Query("town") String town,
//            @Query("company_name") String industry,
//            @Query("question_1") String rb1,
//            @Query("question_2") String rb2,
//            @Query("question_3") String rb3,
//            @Query("question_4") String rb4,
//            @Query("question_5") String rb5,
//            @Query("question_6") String rb6,
//            @Query("question_7") String rb7,
//            @Query("question_8") String rb8,
//            @Query("question_9") String rb9,
//            @Query("question_10") String rb10,
//            @Part("signature") RequestBody signatureImgName, @Part MultipartBody.Part signature,
//            @Part("image") RequestBody userImgName, @Part MultipartBody.Part userImg
//    );

    /*@Multipart
    @POST("/Cliknfixx/api/editProfile")
    Call<SaveUserProfileResponseModel> saveUserProfile(
            @Part("name") String name,
            @Part("phone") String phone,
            @Part("blood_group") String blood_group,
            @Part("age") String age,
            @Part("address") String address,
            @Part MultipartBody.Part user_image,
            @Header("token") String token);*/

}
