package com.etheric.elleen.form;

import com.etheric.elleen.responseModel.SubmitFormResponseModel;

public interface IMainActivity {
    void onSuccessDetailsFromPresenterToActivity(SubmitFormResponseModel submitFormResponseModel);
    void onFailedDetailsFromPresenterToActivity(String message);
}
