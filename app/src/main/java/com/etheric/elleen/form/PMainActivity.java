package com.etheric.elleen.form;

import com.etheric.elleen.responseModel.SubmitFormResponseModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PMainActivity implements IPMainActivity {

    IMainActivity iMainActivity;
    IMMainActivity imMainActivity;

    public PMainActivity(MainActivity mainActivity) {

        iMainActivity = mainActivity;
        imMainActivity = new MMainActivity(this);

    }

    @Override
    public void submitForm(String mobile, String city, String town, String industry, String rb1, String rb2, String rb3, String rb4, String rb5, String rb6, String rb7, MultipartBody.Part signature, MultipartBody.Part userImg, RequestBody signatureImgName,RequestBody userImgName) {
        imMainActivity.submitForm(mobile,city,town,industry,rb1,rb2,rb3,rb4,rb5,rb6,rb7,signature,userImg,signatureImgName,userImgName);
    }

    @Override
    public void onSubmitResponseSuccessFromModel(SubmitFormResponseModel submitFormResponseModel) {
        iMainActivity.onSuccessDetailsFromPresenterToActivity(submitFormResponseModel);
    }

    @Override
    public void onSubmitResponseFailureFromModel(String message) {
        iMainActivity.onFailedDetailsFromPresenterToActivity(message);
    }
}
