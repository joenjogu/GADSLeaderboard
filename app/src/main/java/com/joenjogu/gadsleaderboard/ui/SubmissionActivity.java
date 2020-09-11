package com.joenjogu.gadsleaderboard.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.joenjogu.gadsleaderboard.R;
import com.joenjogu.gadsleaderboard.repository.NetworkRepository;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmissionActivity extends AppCompatActivity {
    static final String TAG = "SubmissionActivity";

    private NetworkRepository repository;
    EditText et_firstName, et_lastName, et_email, et_githubLink;
    Button et_submit;
    ImageView back;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);

        repository = new NetworkRepository();
        progressBar = findViewById(R.id.submission_progress_bar);
        back = findViewById(R.id.iv_back);
        back.setOnClickListener(view -> onBackPressed());

        et_firstName = findViewById(R.id.et_first_name);
        et_lastName = findViewById(R.id.et_last_name);
        et_email = findViewById(R.id.et_email);
        et_githubLink = findViewById(R.id.et_githublink);
        et_submit = findViewById(R.id.btn_submit);

        et_firstName.requestFocus();

        et_submit.setOnClickListener(view -> {
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

                submissionConfirmation(firstName.trim(), lastName.trim(), email.trim(), githubLink.trim());
            }
        });
    }

    private void submitUserDetails(String firstName, String lastName, String email, String githubLink) {
        progressBar.setVisibility(View.VISIBLE);
        // POST details
        Log.d(TAG, "submitUserDetails: submitDetails");
        repository.postUserDetails(firstName, lastName, email, githubLink).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NotNull Call<Void> call, @NotNull Response<Void> response) {
                Log.d(TAG, "submitUserDetails: onResponse " + response.code());
                if (response.isSuccessful()){
                    Log.d(TAG, "submitUserDetails: successful");
                    progressBar.setVisibility(View.GONE);
                    displaySuccessDialog();
                } else{
                    progressBar.setVisibility(View.GONE);
                    displayErrorDialog();
                }
            }

            @Override
            public void onFailure(@NotNull Call<Void> call, @NotNull Throwable t) {
                Log.d(TAG, "submitUserDetails: failure");
                progressBar.setVisibility(View.GONE);
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

        cancelButton.setOnClickListener(view -> dialog.cancel());
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