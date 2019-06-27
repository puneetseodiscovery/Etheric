package com.etheric.elleen.form;

import android.os.Message;

import com.etheric.elleen.responseModel.SubmitFormResponseModel;
import com.etheric.elleen.retrofit.APIInterface;
import com.etheric.elleen.retrofit.RetrofitCalls;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MMainActivity implements IMMainActivity {

    IPMainActivity ipMainActivity;

    public MMainActivity(PMainActivity pMainActivity) {
        ipMainActivity = pMainActivity;
    }

    @Override
    public void submitForm(String mobile, String city, String town, String industry, String rb1, String rb2, String rb3, String rb4, String rb5, String rb6, String rb7, MultipartBody.Part signature, MultipartBody.Part userImg, RequestBody signatureImgName, RequestBody userImgName) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.submitForm(mobile,city,town,industry,rb1,rb2,rb3,rb4,rb5,rb6,rb7,signature,userImg,signatureImgName,userImgName,mHandler);
    }

    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.SUBMIT_DETAILS_SUCCESS:
                    SubmitFormResponseModel submitFormResponseModel = (SubmitFormResponseModel) msg.obj;
                    ipMainActivity.onSubmitResponseSuccessFromModel(submitFormResponseModel);
                    break;
                case APIInterface.SUBMIT_DETAILS_FAILED:
                    String message = (String) msg.obj;
                    ipMainActivity.onSubmitResponseFailureFromModel(message);
                    break;

            }
        }
    };
}
