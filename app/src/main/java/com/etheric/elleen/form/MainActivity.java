package com.etheric.elleen.form;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.etheric.elleen.R;
import com.etheric.elleen.base.BaseClass;
import com.etheric.elleen.responseModel.SubmitFormResponseModel;
import com.etheric.elleen.util.Utility;
import com.kyanogen.signatureview.SignatureView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static okhttp3.MultipartBody.*;

public class MainActivity extends BaseClass implements IMainActivity, View.OnClickListener {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.et_city)
    TextView etCity;
    @BindView(R.id.et_town)
    TextView etTown;
    @BindView(R.id.et_industry)
    EditText etIndustry;
    @BindView(R.id.tv_ques1)
    TextView tvQues1;
    @BindView(R.id.tv_ques2)
    TextView tvQues2;
    @BindView(R.id.tv_ques3)
    TextView tvQues3;
    @BindView(R.id.tv_ques4)
    TextView tvQues4;
    @BindView(R.id.tv_ques5)
    TextView tvQues5;
    @BindView(R.id.tv_ques6)
    TextView tvQues6;
    @BindView(R.id.tv_ques7)
    TextView tvQues7;
    /*@BindView(R.id.tv_ques8)
    TextView tvQues8;
    @BindView(R.id.tv_ques9)
    TextView tvQues9;
    @BindView(R.id.tv_ques10)
    TextView tvQues10;*/
    @BindView(R.id.rg_ques1)
    RadioGroup rgQues1;
    @BindView(R.id.cb1_no)
    RadioButton rbQues1;
    @BindView(R.id.cb1_yes)
    RadioButton rbQues11;
    @BindView(R.id.rg_ques2)
    RadioGroup rgQues2;
    @BindView(R.id.cb2_no)
    RadioButton rbQues2;
    @BindView(R.id.cb2_yes)
    RadioButton rbQues22;
    @BindView(R.id.rg_ques3)
    RadioGroup rgQues3;
    @BindView(R.id.cb3_no)
    RadioButton rbQues3;
    @BindView(R.id.cb3_yes)
    RadioButton rbQues33;
    @BindView(R.id.rg_ques4)
    RadioGroup rgQues4;
    @BindView(R.id.cb4_no)
    RadioButton rbQues4;
    @BindView(R.id.cb4_yes)
    RadioButton rbQues44;
    @BindView(R.id.rg_ques5)
    RadioGroup rgQues5;
    @BindView(R.id.cb5_no)
    RadioButton rbQues5;
    @BindView(R.id.cb5_yes)
    RadioButton rbQues55;
    @BindView(R.id.rg_ques6)
    RadioGroup rgQues6;
    @BindView(R.id.cb6_no)
    RadioButton rbQues6;
    @BindView(R.id.cb6_yes)
    RadioButton rbQues66;
    @BindView(R.id.rg_ques7)
    RadioGroup rgQues7;
    @BindView(R.id.cb7_no)
    RadioButton rbQues7;
    @BindView(R.id.cb7_yes)
    RadioButton rbQues77;
    /*@BindView(R.id.rg_ques8)
    RadioGroup rgQues8;
    @BindView(R.id.cb8_no)
    RadioButton rbQues8;
    @BindView(R.id.cb8_yes)
    RadioButton rbQues88;
    @BindView(R.id.rg_ques9)
    RadioGroup rgQues9;
    @BindView(R.id.cb9_no)
    RadioButton rbQues9;
    @BindView(R.id.cb9_yes)
    RadioButton rbQues99;
    @BindView(R.id.rg_ques10)
    RadioGroup rgQues10;
    @BindView(R.id.cb10_no)
    RadioButton rbQues10;
    @BindView(R.id.cb10_yes)
    RadioButton rbQues00;*/
    @BindView(R.id.upload_img_text)
    TextView tvUploadImageText;
    @BindView(R.id.btn_upload_img)
    Button btnUploadImg;
    @BindView(R.id.signature_text)
    TextView tvSignatureText;
    @BindView(R.id.img_ques11)
    ImageView ivUserPic;
    @BindView(R.id.img_ques12)
    ImageView ivSignature;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.cb_terms_signup)
    CheckBox cbTerms;

    MultipartBody.Part userImg,signature;
    RequestBody userImgName,signatureImgName;
    String rb1,rb2,rb3,rb4,rb5,rb6,rb7;
    int selectedId;

    public static final int CAMERA_REQUEST_CODE = 1;
    public static final int GALLERY_REQUEST_CODE = 2;

    IPMainActivity ipMainActivity;

    ProgressDialog progressDialog;

    boolean doubleBackToExitPressedOnce = false;

    final CharSequence[] cityArray = {"Ambala", "Bhiwani", "Charkhidadri", "Faridabad", "Fatehabad", "Gurugram", "Hisar", "Jhajjar", "Jind"
            , "Kaithal", "Karnal", "Kurukshetra", "Mahendragarh", "Mewat", "Palwal", "Panchkula", "Panipat", "Rewari", "Rohtak", "Sirsa"
            , "Sonipat", "Yamunanagar"};

    CharSequence[] townArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ipMainActivity = new PMainActivity(this);
        init();
        etCity.setOnClickListener(this);
        etTown.setOnClickListener(this);
    }

    public void init() {
        tvTitle.setTypeface(Utility.typeFaceForBoldText(this));
        etMobile.setTypeface(Utility.typeFaceForText(this));
        etCity.setTypeface(Utility.typeFaceForText(this));
        etTown.setTypeface(Utility.typeFaceForText(this));
        etIndustry.setTypeface(Utility.typeFaceForText(this));
        tvQues1.setTypeface(Utility.typeFaceForText(this));
        tvQues2.setTypeface(Utility.typeFaceForText(this));
        tvQues3.setTypeface(Utility.typeFaceForText(this));
        tvQues4.setTypeface(Utility.typeFaceForText(this));
        tvQues5.setTypeface(Utility.typeFaceForText(this));
        tvQues6.setTypeface(Utility.typeFaceForText(this));
        tvQues7.setTypeface(Utility.typeFaceForText(this));
        /*tvQues8.setTypeface(Utility.typeFaceForText(this));
        tvQues9.setTypeface(Utility.typeFaceForText(this));
        tvQues10.setTypeface(Utility.typeFaceForText(this));*/
        rbQues1.setTypeface(Utility.typeFaceForText(this));
        rbQues11.setTypeface(Utility.typeFaceForText(this));
        rbQues2.setTypeface(Utility.typeFaceForText(this));
        rbQues22.setTypeface(Utility.typeFaceForText(this));
        rbQues3.setTypeface(Utility.typeFaceForText(this));
        rbQues33.setTypeface(Utility.typeFaceForText(this));
        rbQues4.setTypeface(Utility.typeFaceForText(this));
        rbQues44.setTypeface(Utility.typeFaceForText(this));
        rbQues5.setTypeface(Utility.typeFaceForText(this));
        rbQues55.setTypeface(Utility.typeFaceForText(this));
        rbQues6.setTypeface(Utility.typeFaceForText(this));
        rbQues66.setTypeface(Utility.typeFaceForText(this));
        rbQues7.setTypeface(Utility.typeFaceForText(this));
        rbQues77.setTypeface(Utility.typeFaceForText(this));
        /*rbQues8.setTypeface(Utility.typeFaceForText(this));
        rbQues88.setTypeface(Utility.typeFaceForText(this));
        rbQues9.setTypeface(Utility.typeFaceForText(this));
        rbQues99.setTypeface(Utility.typeFaceForText(this));
        rbQues10.setTypeface(Utility.typeFaceForText(this));
        rbQues00.setTypeface(Utility.typeFaceForText(this));*/
        tvUploadImageText.setTypeface(Utility.typeFaceForText(this));
        btnUploadImg.setTypeface(Utility.typeFaceForText(this));
        tvSignatureText.setTypeface(Utility.typeFaceForText(this));
        btnSubmit.setTypeface(Utility.typeFaceForText(this));
        cbTerms.setTypeface(Utility.typeFaceForText(this));

        etCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(etCity.getText().length()>0)
                    etCity.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etTown.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(etTown.getText().length()>0)
                    etTown.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    public void onUploadPhotoClicked(View view) {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, GALLERY_REQUEST_CODE);
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK)
        {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ivUserPic.setImageBitmap(photo);
            try {
                userImg = sendImageFileToserver(photo,"0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }  else if (requestCode == GALLERY_REQUEST_CODE) {
            if(data!= null) {
                Uri selectedImage = data.getData();
                ivUserPic.setImageURI(selectedImage);
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                    userImg = sendImageFileToserver(bitmap,"0");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public Part sendImageFileToserver(Bitmap bitMap,String signature) throws IOException {
        File filesDir = getFilesDir();
        File file = new File(filesDir, "image"+ System.currentTimeMillis() + ".png");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitMap.compress(Bitmap.CompressFormat.JPEG, 50, bos);
        byte[] bitmapdata = bos.toByteArray();

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bitmapdata);
        fos.flush();
        fos.close();

        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), reqFile);
        if(signature.equalsIgnoreCase("1"))
            signatureImgName = RequestBody.create(MediaType.parse("text/plain"), "image");
        else
            userImgName = RequestBody.create(MediaType.parse("text/plain"), "image");
        /*new PreferenceHandler().writeSaveImgString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_UPLOAD_IMG, String.valueOf(fileToUpload));
        new PreferenceHandler().writeSaveImgString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_IMG_NAME, String.valueOf(filename));*/

        return body;
    }

    public Part sendImageFileToserver1(Bitmap bitMap,String signature) throws IOException {
        File filesDir = getFilesDir();
        File file = new File(filesDir, "signature"+System.currentTimeMillis() + ".png");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitMap.compress(Bitmap.CompressFormat.JPEG, 50, bos);
        byte[] bitmapdata = bos.toByteArray();

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bitmapdata);
        fos.flush();
        fos.close();

        RequestBody reqFile = RequestBody.create(MediaType.parse("signature/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("signature", file.getName(), reqFile);
        if(signature.equalsIgnoreCase("1"))
            signatureImgName = RequestBody.create(MediaType.parse("text/plain"), "signature");
        else
            userImgName = RequestBody.create(MediaType.parse("text/plain"), "signature");

        return body;
    }


    public void onSubmitClicked(View view) {
        if (Utility.isNetworkConnected(this)) {
            if (etMobile.getText().toString().length() > 0 && etCity.getText().toString().length() > 0 && etTown.getText().toString().length() > 0
                    && etIndustry.getText().toString().length() > 0 && rgQues1.getCheckedRadioButtonId() != -1 && rgQues1.getCheckedRadioButtonId() != -1
                    && rgQues2.getCheckedRadioButtonId() != -1 && rgQues3.getCheckedRadioButtonId() != -1 && rgQues4.getCheckedRadioButtonId() != -1
                    && rgQues5.getCheckedRadioButtonId() != -1 && rgQues6.getCheckedRadioButtonId() != -1 && rgQues7.getCheckedRadioButtonId() != -1
                    //&& rgQues8.getCheckedRadioButtonId() != -1 && rgQues9.getCheckedRadioButtonId() != -1 && rgQues10.getCheckedRadioButtonId() != -1
                    && userImg != null && signature != null) {

                selectedId = rgQues1.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                rb1 = radioButton.getText().toString();

                selectedId = rgQues1.getCheckedRadioButtonId();
                RadioButton radioButton1 = (RadioButton) findViewById(selectedId);
                rb1 = radioButton1.getText().toString();

                selectedId = rgQues2.getCheckedRadioButtonId();
                RadioButton radioButton2 = (RadioButton) findViewById(selectedId);
                rb2 = radioButton2.getText().toString();

                selectedId = rgQues3.getCheckedRadioButtonId();
                RadioButton radioButton3 = (RadioButton) findViewById(selectedId);
                rb3 = radioButton3.getText().toString();

                selectedId = rgQues4.getCheckedRadioButtonId();
                RadioButton radioButton4 = (RadioButton) findViewById(selectedId);
                rb4 = radioButton4.getText().toString();

                selectedId = rgQues5.getCheckedRadioButtonId();
                RadioButton radioButton5 = (RadioButton) findViewById(selectedId);
                rb5 = radioButton5.getText().toString();

                selectedId = rgQues6.getCheckedRadioButtonId();
                RadioButton radioButton6 = (RadioButton) findViewById(selectedId);
                rb6 = radioButton6.getText().toString();

                selectedId = rgQues7.getCheckedRadioButtonId();
                RadioButton radioButton7 = (RadioButton) findViewById(selectedId);
                rb7 = radioButton7.getText().toString();

               /* selectedId = rgQues8.getCheckedRadioButtonId();
                RadioButton radioButton8 = (RadioButton) findViewById(selectedId);
                rb8 = radioButton8.getText().toString();

                selectedId = rgQues9.getCheckedRadioButtonId();
                RadioButton radioButton9 = (RadioButton) findViewById(selectedId);
                rb9 = radioButton9.getText().toString();

                selectedId = rgQues10.getCheckedRadioButtonId();
                RadioButton radioButton10 = (RadioButton) findViewById(selectedId);
                rb10 = radioButton10.getText().toString();*/

                if (cbTerms.isChecked()) {
                    progressDialog = Utility.showLoader(this);
                    ipMainActivity.submitForm(etMobile.getText().toString(), etCity.getText().toString(), etTown.getText().toString(), etIndustry.getText().toString(),
                            rb1, rb2, rb3, rb4, rb5, rb6, rb7, signature, userImg, signatureImgName, userImgName);
                } else
                    Toast.makeText(this, "Please accept the Terms and Conditions.", Toast.LENGTH_SHORT).show();

            } else {
                if (etMobile.getText().toString().length() == 0 && etCity.getText().toString().length() == 0 && etCity.getText().toString().length() == 0
                        && etIndustry.getText().toString().length() == 0 && rgQues1.getCheckedRadioButtonId() == -1 && rgQues2.getCheckedRadioButtonId() == -1
                        && rgQues3.getCheckedRadioButtonId() == -1 && rgQues4.getCheckedRadioButtonId() == -1 && rgQues5.getCheckedRadioButtonId() == -1
                        && rgQues6.getCheckedRadioButtonId() == -1 && rgQues7.getCheckedRadioButtonId() == -1
                       // && rgQues8.getCheckedRadioButtonId() == -1 && rgQues9.getCheckedRadioButtonId() == -1 && rgQues10.getCheckedRadioButtonId() == -1
                    ) {
                    etMobile.setError("Enter Mobile");
                    etCity.setError("Enter City");
                    etTown.setError("Enter Town");
                    etIndustry.setError("Enter Company Name");
                    etMobile.requestFocus();
                } else if (etMobile.getText().toString().length() == 0) {
                    etMobile.setError("Enter Mobile");
                    etMobile.requestFocus();
                } else if (etCity.getText().toString().length() == 0) {
                    etCity.setError("Enter City");
                    etCity.requestFocus();
                } else if (etTown.getText().toString().length() == 0) {
                    etTown.setError("Enter Town");
                    etTown.requestFocus();
                } else if (etIndustry.getText().toString().length() == 0) {
                    etIndustry.setError("Enter Company Name");
                    etIndustry.requestFocus();
                } else if (userImg == null || signature == null || rgQues1.getCheckedRadioButtonId() == -1 || rgQues2.getCheckedRadioButtonId() == -1
                        || rgQues3.getCheckedRadioButtonId() == -1 || rgQues4.getCheckedRadioButtonId() == -1 || rgQues5.getCheckedRadioButtonId() == -1
                        || rgQues6.getCheckedRadioButtonId() == -1 || rgQues7.getCheckedRadioButtonId() == -1
                        //|| rgQues8.getCheckedRadioButtonId() == -1 || rgQues9.getCheckedRadioButtonId() == -1 || rgQues10.getCheckedRadioButtonId() == -1
                ) {
                    String message = "";
                    if (signature == null) {
                        message = message + "Add Your Signature.";
                        //Toast.makeText(this, "Add Your Signature", Toast.LENGTH_SHORT).show();
                    }
                    if (userImg == null) {
                        message = message + "Add Your Picture.";
                        //Toast.makeText(this, "Add Your Picture.", Toast.LENGTH_SHORT).show();
                    }
                    if (
                            //rgQues10.getCheckedRadioButtonId() == -1 || rgQues9.getCheckedRadioButtonId() == -1 || rgQues8.getCheckedRadioButtonId() == -1 ||
                            rgQues7.getCheckedRadioButtonId() == -1 || rgQues6.getCheckedRadioButtonId() == -1 || rgQues5.getCheckedRadioButtonId() == -1
                            || rgQues4.getCheckedRadioButtonId() == -1 || rgQues3.getCheckedRadioButtonId() == -1 || rgQues2.getCheckedRadioButtonId() == -1
                            || rgQues1.getCheckedRadioButtonId() == -1) {
                        message = message + "Please answer all the questions.";
                        //Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                    }

                    if (message.length() > 0)
                        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            Toast.makeText(this, "Check your internet connection !!!", Toast.LENGTH_SHORT).show();
        }
    }

    public void loadCitySpinner(){
        final android.support.v7.app.AlertDialog.Builder alert = new android.support.v7.app.AlertDialog.Builder(this);
        alert.setTitle("Select City");
        alert.setSingleChoiceItems(cityArray, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (cityArray[which] == "Ambala") {
                    etCity.setText(cityArray[which]);
                    etTown.setText("");
                    dialog.cancel();
                    setTownArray("Ambala");
                } else if (cityArray[which] == "Bhiwani") {
                    etCity.setText(cityArray[which]);
                    etTown.setText("");
                    dialog.cancel();
                    setTownArray("Bhiwani");
                } else if (cityArray[which] == "Charkhidadri") {
                    etCity.setText(cityArray[which]);
                    etTown.setText("");
                    dialog.cancel();
                    setTownArray("Charkhidadri");
                } else if (cityArray[which] == "Faridabad") {
                    etCity.setText(cityArray[which]);
                    etTown.setText("");
                    dialog.cancel();
                    setTownArray("Faridabad");
                } else if (cityArray[which] == "Fatehabad") {
                    etCity.setText(cityArray[which]);
                    etTown.setText("");
                    dialog.cancel();
                    setTownArray("Fatehabad");
                } else if (cityArray[which] == "Gurugram") {
                    etCity.setText(cityArray[which]);
                    etTown.setText("");
                    dialog.cancel();
                    setTownArray("Gurugram");
                } else if (cityArray[which] == "Hisar") {
                    etCity.setText(cityArray[which]);
                    etTown.setText("");
                    dialog.cancel();
                    setTownArray("Hisar");
                } else if (cityArray[which] == "Jhajjar") {
                    etCity.setText(cityArray[which]);
                    etTown.setText("");
                    dialog.cancel();
                    setTownArray("Jhajjar");
                } else if (cityArray[which] == "Jind") {
                    etCity.setText(cityArray[which]);
                    etTown.setText("");
                    dialog.cancel();
                    setTownArray("Jind");
                } else if (cityArray[which] == "Kaithal") {
                    etCity.setText(cityArray[which]);
                    etTown.setText("");
                    dialog.cancel();
                    setTownArray("Kaithal");
                } else if (cityArray[which] == "Karnal") {
                    etCity.setText(cityArray[which]);
                    etTown.setText("");
                    dialog.cancel();
                    setTownArray("Karnal");
                } else if (cityArray[which] == "Kurukshetra") {
                    etCity.setText(cityArray[which]);
                    etTown.setText("");
                    dialog.cancel();
                    setTownArray("Kurukshetra");
                } else if (cityArray[which] == "Mahendragarh") {
                    etCity.setText(cityArray[which]);
                    etTown.setText("");
                    dialog.cancel();
                    setTownArray("Mahendragarh");
                } else if (cityArray[which] == "Mewat") {
                    etCity.setText(cityArray[which]);
                    etTown.setText("");
                    dialog.cancel();
                    setTownArray("Mewat");
                } else if (cityArray[which] == "Palwal") {
                    etCity.setText(cityArray[which]);
                    etTown.setText("");
                    dialog.cancel();
                    setTownArray("Palwal");
                } else if (cityArray[which] == "Panchkula") {
                    etCity.setText(cityArray[which]);
                    etTown.setText("");
                    dialog.cancel();
                    setTownArray("Panchkula");
                } else if (cityArray[which] == "Panipat") {
                    etCity.setText(cityArray[which]);
                    etTown.setText("");
                    dialog.cancel();
                    setTownArray("Panipat");
                } else if (cityArray[which] == "Rewari") {
                    etCity.setText(cityArray[which]);
                    etTown.setText("");
                    dialog.cancel();
                    setTownArray("Rewari");
                } else if (cityArray[which] == "Rohtak") {
                    etCity.setText(cityArray[which]);
                    etTown.setText("");
                    dialog.cancel();
                    setTownArray("Rohtak");
                } else if (cityArray[which] == "Sirsa") {
                    etCity.setText(cityArray[which]);
                    etTown.setText("");
                    dialog.cancel();
                    setTownArray("Sirsa");
                } else if (cityArray[which] == "Sonipat") {
                    etCity.setText(cityArray[which]);
                    etTown.setText("");
                    dialog.cancel();
                    setTownArray("Sonipat");
                } else if (cityArray[which] == "Yamunanagar") {
                    etCity.setText(cityArray[which]);
                    etTown.setText("");
                    dialog.cancel();
                    setTownArray("Yamunanagar");
                }
            }
        });
        alert.show();
    }

    public void setTownArray(String city) {
        if(city.equalsIgnoreCase("Ambala")){
            townArray = new CharSequence[]{"Ambala", "Barara", "Naraingarh"};
        } else if(city.equalsIgnoreCase("Bhiwani")){
            townArray = new CharSequence[]{"Bawani", "Bhiwani", "Loharu", "Siwani"};
        } else if(city.equalsIgnoreCase("Charkhidadri")){
            townArray = new CharSequence[]{"Charkhi Dadri"};
        } else if(city.equalsIgnoreCase("Faridabad")){
            townArray = new CharSequence[]{"Faridabad"};
        } else if(city.equalsIgnoreCase("Fatehabad")){
            townArray = new CharSequence[]{"Bhuna", "Fatehabad", "Ratia", "Tohana"};
        } else if(city.equalsIgnoreCase("Gurugram")){
            townArray = new CharSequence[]{"Farrukhnagar", "Gurugram", "Hailey Mandi", "Pataudi","Sohna"};
        } else if(city.equalsIgnoreCase("Hisar")){
            townArray = new CharSequence[]{"Barwala", "Hansi", "Hisar", "Narnaund","Uklana Mandi"};
        } else if(city.equalsIgnoreCase("Jhajjar")){
            townArray = new CharSequence[]{"Bahadurgarh", "Beri", "Jhajjar"};
        } else if(city.equalsIgnoreCase("Jind")){
            townArray = new CharSequence[]{"Jind", "Julana", "Narwana", "Safidon","Uchana"};
        } else if(city.equalsIgnoreCase("Kaithal")){
            townArray = new CharSequence[]{"Cheeka", "Kaithal", "Kalayat", "Pundri","Rajound"};
        } else if(city.equalsIgnoreCase("Karnal")){
            townArray = new CharSequence[]{"Assandh", "Gharaunda", "Indri", "Karnal","Nilokheri","Nissing","Taraori"};
        } else if(city.equalsIgnoreCase("Kurukshetra")){
            townArray = new CharSequence[]{"Ladwa", "Pehowa", "Shahbad", "Thanesar"};
        } else if(city.equalsIgnoreCase("Mahendragarh")){
            townArray = new CharSequence[]{"Ateli", "Kanina", "Mahendragarh", "Nagal Chaudhry","Narnaul"};
        } else if(city.equalsIgnoreCase("Mewat")){
            townArray = new CharSequence[]{"Ferozepur jhirka", "Nuh", "Punahana", "Taoru"};
        } else if(city.equalsIgnoreCase("Palwal")){
            townArray = new CharSequence[]{"Hathin", "Hodal", "Palwal"};
        } else if(city.equalsIgnoreCase("Panchkula")){
            townArray = new CharSequence[]{"Panchkula"};
        } else if(city.equalsIgnoreCase("Panipat")){
            townArray = new CharSequence[]{"Panipat", "Samalkha"};
        } else if(city.equalsIgnoreCase("Rewari")){
            townArray = new CharSequence[]{"Bawal", "Dharuhera", "Rewari"};
        } else if(city.equalsIgnoreCase("Rohtak")){
            townArray = new CharSequence[]{"Kalanaur", "Maham", "Rohtak", "Sampla"};
        } else if(city.equalsIgnoreCase("Sirsa")){
            townArray = new CharSequence[]{"Ellenabad", "Kalanwali", "Mandi Dabwali", "Rania","Sirsa"};
        } else if(city.equalsIgnoreCase("Sonipat")){
            townArray = new CharSequence[]{"Ganaur", "Gohana", "Kharkhoda", "Sonipat"};
        } else if(city.equalsIgnoreCase("Yamunanagar")){
            townArray = new CharSequence[]{"Radaur", "Yamunanagar"};
        }
    }

    public void loadTownSpinner(){
        final android.support.v7.app.AlertDialog.Builder alert = new android.support.v7.app.AlertDialog.Builder(this);
        alert.setTitle("Select Town");
        alert.setSingleChoiceItems(townArray, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (townArray[which] == "Ambala") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Barara") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Naraingarh") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Bawani") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Bhiwani") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Loharu") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Siwani") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Charkhi Dadri") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Faridabad") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Bhuna") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Fatehabad") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Ratia") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Tohana") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Farrukhnagar") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Gurugram") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Hailey Mandi") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Pataudi") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Sohna") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Barwala") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Hansi") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Hisar") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Narnaund") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Uklana Mandi") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Bahadurgarh") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Beri") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Jhajjar") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Jind") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Julana") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Narwana") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Safidon") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Uchana") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Cheeka") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Kaithal") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Kalayat") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Pundri") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Rajound") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Assandh") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Gharaunda") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Indri") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Karnal") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Nilokheri") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Nissing") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Taraori") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Ladwa") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Pehowa") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Shahbad") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Thanesar") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Ateli") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Kanina") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Mahendragarh") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Nagal Chaudhry") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Narnaul") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Ferozepur jhirka") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Nuh") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Punahana") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Taoru") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Hathin") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Hodal") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Palwal") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Panchkula") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Panipat") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Samalkha") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                }  else if (townArray[which] == "Bawal") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Dharuhera") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Rewari") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Kalanaur") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Maham") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Rohtak") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Sampla") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Ellenabad") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Kalanwali") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Mandi Dabwali") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Rania") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Sirsa") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Ganaur") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Gohana") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Kharkhoda") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Sonipat") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Radaur") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                } else if (townArray[which] == "Yamunanagar") {
                    etTown.setText(townArray[which]);
                    dialog.cancel();
                }
            }
        });
        alert.show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.et_city:
                loadCitySpinner();
                break;
            case R.id.et_town:
                if(etCity.getText().toString().length() == 0)
                    Toast.makeText(this, "Please Select City", Toast.LENGTH_SHORT).show();
                else
                    loadTownSpinner();
                break;
        }
    }

    @Override
    public void onSuccessDetailsFromPresenterToActivity(SubmitFormResponseModel submitFormResponseModel) {
        etMobile.setText("");
        etCity.setText("");
        etTown.setText("");
        etIndustry.setText("");
        rgQues1.clearCheck();
        rgQues2.clearCheck();
        rgQues3.clearCheck();
        rgQues4.clearCheck();
        rgQues5.clearCheck();
        rgQues6.clearCheck();
        rgQues7.clearCheck();
        /*rgQues8.clearCheck();
        rgQues9.clearCheck();
        rgQues10.clearCheck();*/
        ivUserPic.setImageResource(0);
        ivSignature.setImageResource(0);
        cbTerms.setChecked(false);
        progressDialog.dismiss();
        Toast.makeText(this, "" + submitFormResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailedDetailsFromPresenterToActivity(String message) {
        progressDialog.dismiss();
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }


    public void onSignatureClicked(View view) {
        LayoutInflater factory = LayoutInflater.from(this);
        final View deleteDialogView = factory.inflate(R.layout.custom_dialog, null);
        TextView tvNeedSign = (TextView)deleteDialogView.findViewById(R.id.tv_need_sign_text);
        Button btnSubmit = (Button)deleteDialogView.findViewById(R.id.btn_submit_dialog);
        tvNeedSign.setTypeface(Utility.typeFaceForText(MainActivity.this));
        btnSubmit.setTypeface(Utility.typeFaceForText(MainActivity.this));
        final AlertDialog deleteDialog = new AlertDialog.Builder(this).create();
        deleteDialog.setView(deleteDialogView);
        deleteDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        deleteDialogView.findViewById(R.id.btn_submit_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //your business logic
                deleteDialog.dismiss();
                SignatureView signatureView = deleteDialogView.findViewById(R.id.signature_view);
                ivSignature.setImageBitmap(signatureView.getSignatureBitmap());
                try {
                    signature = sendImageFileToserver1(signatureView.getSignatureBitmap(),"1");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        deleteDialog.show();

    }

    @Override
    public void onBackPressed() {
        int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        if (backStackEntryCount == 0) {
            if (doubleBackToExitPressedOnce) {
                finishAffinity();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, getResources().getString(R.string.exit), Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);

        } else {
            super.onBackPressed();
        }

    }
}
