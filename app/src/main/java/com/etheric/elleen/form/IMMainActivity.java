package com.etheric.elleen.form;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface IMMainActivity {
    void submitForm(String mobile, String city, String town, String industry, String rb1, String rb2, String rb3, String rb4, String rb5, String rb6, String rb7,MultipartBody.Part signature, MultipartBody.Part userImg, RequestBody signatureImgName, RequestBody userImgName);
}
