package com.joenjogu.gadsleaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmissionActivity extends AppCompatActivity {
    static final String TAG = "SubmissionActivity";

    private NetworkRepository repository;
    TextInputEditText et_firstName, et_lastName, et_email, et_githubLink;
    TextInputLayout et_firstName_layout, et_lastName_layout, et_email_layout, et_githubLink_layout;
    Button et_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);

        repository = new NetworkRepository();

        et_firstName = findViewById(R.id.et_first_name);
        et_lastName = findViewById(R.id.et_last_name);
        et_email = findViewById(R.id.et_email);
        et_githubLink = findViewById(R.id.et_githublink);
        et_submit = findViewById(R.id.btn_submit);

        et_firstName_layout = findViewById(R.id.et_first_name_layout);
        et_lastName_layout = findViewById(R.id.et_last_name_layout);
        et_email_layout = findViewById(R.id.et_email_layout);
        et_githubLink_layout = findViewById(R.id.et_githublink_layout);

        et_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = et_firstName.getText().toString().trim();
                String lastName = et_lastName.getText().toString().trim();
                String email = et_email.getText().toString().trim();
                String githubLink = et_githubLink.getText().toString().trim();

                if (TextUtils.isEmpty(firstName)){
                    et_firstName.setError("Please Enter your First Name!");
                }
                if (TextUtils.isEmpty(lastName)){
                    et_lastName.setError("Please Enter your Last Name!");
                }
                if (TextUtils.isEmpty(email)){
                    et_email.setError("Please Enter your Email Address!");
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    et_email.setError("Enter a valid Email Address!");
                }
                if (TextUtils.isEmpty(githubLink)){
                    et_githubLink.setError("Please Enter your Github Link!");
                }
                if (!Patterns.WEB_URL.matcher(githubLink).matches()){
                    et_githubLink.setError("Please Enter a valid Link!");
                }

                if (TextUtils.isEmpty(et_firstName.getError()) &&
                        TextUtils.isEmpty(et_lastName.getError()) &&
                        TextUtils.isEmpty(et_email.getError()) &&
                        TextUtils.isEmpty(et_githubLink.getError())) {

                    submissionConfirmation(firstName, lastName, email, githubLink);
                }
            }
        });
    }

    private void submitUserDetails(String firstName, String lastName, String email, String githubLink) {
        // POST details
        Log.d(TAG, "submitUserDetails: submitDetails");
        repository.postUserDetails(firstName, lastName, email, githubLink).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "submitUserDetails: onResponse " + response.code());
                if (response.isSuccessful()){
                    Log.d(TAG, "submitUserDetails: successful");
                    displaySuccessDialog();
                } else{
                    displayErrorDialog();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG, "submitUserDetails: failure");
                t.printStackTrace();
                displayErrorDialog();
            }
        });
    }

    private void submissionConfirmation(String firstName, String lastName, String email, String githubLink) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.submission_confirmation);
        dialog.show();

        ImageView cancelButton = dialog.findViewById(R.id.btn_dialog_cancel);
        Button yesButton = dialog.findViewById(R.id.btn_submit_yes);

        cancelButton.setOnClickListener(view -> {dialog.cancel();});
        yesButton.setOnClickListener(view -> {
            submitUserDetails(firstName, lastName, email, githubLink);
            dialog.dismiss();
        });
    }

    private void displayErrorDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.failed_submission);
        dialog.show();
    }

    private void displaySuccessDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.successful_submission);
        dialog.show();
    }
}