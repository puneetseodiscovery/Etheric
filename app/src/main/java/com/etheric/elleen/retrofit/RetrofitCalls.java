package com.etheric.elleen.retrofit;

import android.os.Handler;
import android.os.Message;
import android.util.Log;


import com.etheric.elleen.responseModel.SubmitFormResponseModel;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitCalls {

    private APIInterface apiInterface;

    public RetrofitCalls() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    public void submitForm(String mobile, String city, String town, String industry, String rb1, String rb2, String rb3, String rb4, String rb5, String rb6, String rb7, MultipartBody.Part signature, MultipartBody.Part userImg,RequestBody signatureImgName,RequestBody userImgName,final Handler mHandler) {
        final Message message = new Message();
        RequestBody mobile1 = RequestBody.create(MediaType.parse("text/plain"), mobile);
        RequestBody city1 = RequestBody.create(MediaType.parse("text/plain"), city);
        RequestBody town1 = RequestBody.create(MediaType.parse("text/plain"), town);
        RequestBody industry1 = RequestBody.create(MediaType.parse("text/plain"), industry);
        RequestBody rb11 = RequestBody.create(MediaType.parse("text/plain"), rb1);
        RequestBody rb21 = RequestBody.create(MediaType.parse("text/plain"), rb2);
        RequestBody rb31= RequestBody.create(MediaType.parse("text/plain"), rb3);
        RequestBody rb41 = RequestBody.create(MediaType.parse("text/plain"), rb4);
        RequestBody rb51 = RequestBody.create(MediaType.parse("text/plain"), rb5);
        RequestBody rb61 = RequestBody.create(MediaType.parse("text/plain"), rb6);
        RequestBody rb71 = RequestBody.create(MediaType.parse("text/plain"), rb7);
        /*RequestBody rb81 = RequestBody.create(MediaType.parse("text/plain"), rb8);
        RequestBody rb91 = RequestBody.create(MediaType.parse("text/plain"), rb9);
        RequestBody rb101 = RequestBody.create(MediaType.parse("text/plain"), rb10);*/
        Call<SubmitFormResponseModel> call = apiInterface.submitForm(mobile1,city1,town1,industry1,rb11,rb21,rb31,rb41,rb51,rb61,rb71,signatureImgName,signature,userImgName,userImg);
       // Call<SubmitFormResponseModel> call = apiInterface.submitForm(mobile,city,town,industry,rb1,rb2,rb3,rb4,rb5,rb6,rb7,rb8,rb9,rb10,signatureImgName,signature,userImgName,userImg);
        call.enqueue(new Callback<SubmitFormResponseModel>() {
            @Override
            public void onResponse(Call<SubmitFormResponseModel> call, Response<SubmitFormResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.SUBMIT_DETAILS_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.SUBMIT_DETAILS_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<SubmitFormResponseModel> call, Throwable t) {
                Log.d("++++++", "++ response edit profile image ++" + t.getMessage());

                message.what = apiInterface.SUBMIT_DETAILS_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }

    /*public void loginUser(String email, String password,String device_token, final Handler mHandler) {
        final Message message = new Message();
        Call<LoginResponseModel> call = apiInterface.loginUser(email,password,device_token);
        call.enqueue(new Callback<LoginResponseModel>() {
            @Override
                public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                    if (response.body() != null) {
                        Log.e("Status().code","" + response.code());
                        if (response.body().getStatus().equalsIgnoreCase("200")) {
                            message.what = apiInterface.LOGIN_SUCCESS;
                            message.obj = response.body();
                            String token = response.body().getData().get(0).getRememberToken();
                            int id = response.body().getData().get(0).getId();
                            Log.d("+++++++++", "++ access token++" + token);
                            Log.d("+++++++++", "++ id++" + id);
                            new PreferenceHandler().writeString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_LOGIN_TOKEN, token);
                            new PreferenceHandler().writeInteger(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_LOGIN_USER_ID, id);
                            String mLoginToken = new PreferenceHandler().readString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_LOGIN_TOKEN, "");
                            int userId = new PreferenceHandler().readInteger(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_LOGIN_USER_ID, 0);
                            Log.d("+++++++++", "++ access token read++" + mLoginToken);
                            Log.d("+++++++++", "++ id read++" + userId);
                            mHandler.sendMessage(message);
                        } else if(response.body().getStatus().equalsIgnoreCase("401")){
                            message.what = apiInterface.OTP_NOT_VERIFIED;
                            message.obj = response.body();
                            mHandler.sendMessage(message);
                        }else {
                            message.what = apiInterface.LOGIN_FAILED;
                            message.obj = response.body().getMessage();
                            mHandler.sendMessage(message);
                        }
                    }

                }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                Log.e("Status().equals(200)","SUCCESS");
                message.what = apiInterface.LOGIN_FAILED;
                message.obj = t.getMessage();
                Log.e("Error msg","" + t.getMessage());
                mHandler.sendMessage(message);
            }
        });
    }*/

}
