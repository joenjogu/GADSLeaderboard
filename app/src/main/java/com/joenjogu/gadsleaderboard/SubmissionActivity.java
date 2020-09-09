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

        et_firstName = findViewById(R.id.et_first_name);
        et_lastName = findViewById(R.id.et_last_name);
        et_email = findViewById(R.id.et_email);
        et_githubLink = findViewById(R.id.et_githublink);
        et_submit = findViewById(R.id.btn_submit);

        et_firstName_layout = findViewById(R.id.et_first_name_layout);
        et_lastName_layout = findViewById(R.id.et_last_name_layout);
        et_email_layout = findViewById(R.id.et_email_layout);
        et_githubLink_layout = findViewById(R.id.et_githublink_layout);

        et_firstName.addTextChangedListener(new ValidationWatcher(et_firstName_layout, et_firstName));
        et_lastName.addTextChangedListener(new ValidationWatcher(et_lastName_layout, et_lastName));
        et_email.addTextChangedListener(new ValidationWatcher(et_email_layout, et_email));
        et_githubLink.addTextChangedListener(new ValidationWatcher(et_githubLink_layout, et_githubLink));

        et_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = et_firstName.getText().toString().trim();
                String lastName = et_lastName.getText().toString().trim();
                String email = et_email.getText().toString().trim();
                String githubLink = et_githubLink.getText().toString().trim();

//                if (firstName.isEmpty()){
//                    et_firstName.setError("Please Enter your First Name!");
//                }
//                if (lastName.isEmpty()){
//                    et_lastName.setError("Please Enter your Last Name!");
//                }
//                if (email.isEmpty()){
//                    et_email.setError("Please Enter your Email Address!");
//                }
//                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//                    et_email.setError("Enter a valid Email Address!");
//                }
//                if (githubLink.isEmpty()){
//                    et_githubLink.setError("Please Enter your Github Link!");
//                }
//                if (!Patterns.WEB_URL.matcher(githubLink).matches()){
//                    et_githubLink.setError("Please Enter a valid Link!");
//                }
//
//                if (et_firstName.getError().length() > 0
//                        && et_lastName.getError().length() > 0
//                        && et_email.getError().length() > 0
//                        && et_githubLink.getError().length() > 0){
//
//
//                }
                if (TextUtils.isEmpty(et_firstName_layout.getError()) &&
                        TextUtils.isEmpty(et_lastName_layout.getError()) &&
                        TextUtils.isEmpty(et_email_layout.getError()) &&
                        TextUtils.isEmpty(et_githubLink_layout.getError())){

                    if(submissionConfirmation()){
                        Toast.makeText(getBaseContext(), "Submitting...", Toast.LENGTH_LONG).show();
//                        submitUserDetails(firstName, lastName, email, githubLink);
                    }
                }
            }
        });
    }

    private class ValidationWatcher implements TextWatcher{

        private TextInputLayout inputLayout;
        private TextInputEditText editText;
        public ValidationWatcher(TextInputLayout inputLayout, TextInputEditText editText) {
            this.inputLayout = inputLayout;
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (editText.getId()){
                case R.id.et_first_name:
                case R.id.et_last_name:
                    validateText(inputLayout, editText);
                    break;
                case R.id.et_email:
                    validateEmail(inputLayout, editText);
                    break;
                case R.id.et_githublink:
                    validateUrl(inputLayout, editText);
                    break;
            }

        }
    }

    private void requestFocus(View view){
        if (view.requestFocus()){
            getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE
            );
        }
    }

    private boolean validateText(TextInputLayout inputLayout, TextInputEditText editText){
        if (Objects.requireNonNull(editText.getText()).toString().trim().isEmpty()){
            inputLayout.setErrorEnabled(true);
            inputLayout.setError("This field cannot be empty");
            return false;
        }
        return true;
    }

    private boolean validateEmail(TextInputLayout inputLayout, TextInputEditText editText){
        if (Objects.requireNonNull(editText.getText()).toString().trim().isEmpty()){
            inputLayout.setErrorEnabled(false);
        } else {
            String email = editText.getText().toString();
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                inputLayout.setError("Enter a valid Email Address!");
                requestFocus(editText);
            } else {
                inputLayout.setErrorEnabled(false);
            }
            return false;
        }
        return true;
    }

    private boolean validateUrl(TextInputLayout inputLayout, TextInputEditText editText){
        if (Objects.requireNonNull(editText.getText()).toString().trim().isEmpty()){
            inputLayout.setErrorEnabled(false);
        } else {
            String url = editText.getText().toString();
            if (!Patterns.WEB_URL.matcher(url).matches()) {
                inputLayout.setError("Please Enter a valid Link!");
                requestFocus(editText);
            }else {
                inputLayout.setErrorEnabled(false);
            }
            return false;
        }
        return true;
    }

    private void submitUserDetails(String firstName, String lastName, String email, String githubLink) {
        // POST details
        Log.d(TAG, "submitUserDetails: submitDetails");
        repository.postUserDetails(firstName, lastName, email, githubLink).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    displaySuccessDialog();
                    Toast.makeText(getBaseContext(), "Submission Successful", Toast.LENGTH_LONG).show();
                } else{
                    displayErrorDialog();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                displayErrorDialog();
            }
        });
    }

    private boolean submissionConfirmation() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.submission_confirmation);
        dialog.show();

        ImageView cancelButton = dialog.findViewById(R.id.btn_dialog_cancel);
        Button yesButton = dialog.findViewById(R.id.btn_submit_yes);

        cancelButton.setOnClickListener(view -> {dialog.cancel();});
        final boolean[] responseArray = {false};
        yesButton.setOnClickListener(view -> {
            responseArray[0] = true;
            dialog.dismiss();
        });
        boolean response = responseArray[0];
        return response;
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