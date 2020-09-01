package com.joenjogu.gadsleaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PatternMatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SubmissionActivity extends AppCompatActivity {

    EditText et_firstName = findViewById(R.id.et_first_name);
    EditText et_lastName = findViewById(R.id.et_last_name);
    EditText et_email = findViewById(R.id.et_email);
    EditText et_githubLink = findViewById(R.id.et_githublink);
    Button et_submit = findViewById(R.id.btn_submit);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);

        et_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = et_firstName.getText().toString().trim();
                String lastName = et_lastName.getText().toString().trim();
                String email = et_email.getText().toString().trim();
                String githubLink = et_githubLink.getText().toString().trim();

                if (firstName.isEmpty()){
                    et_firstName.setError("Please Enter your First Name!");
                }
                if (lastName.isEmpty()){
                    et_lastName.setError("Please Enter your Last Name!");
                }
                if (email.isEmpty()){
                    et_email.setError("Please Enter your Email Address!");
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    et_email.setError("Enter a valid Email Address!");
                }
                if (githubLink.isEmpty()){
                    et_githubLink.setError("Please Enter your Github Link!");
                }
                if (!Patterns.WEB_URL.matcher(githubLink).matches()){
                    et_githubLink.setError("Please Enter a valid Link!");
                }

                if (et_firstName.getError().length() > 0
                        && et_lastName.getError().length() > 0
                        && et_email.getError().length() > 0
                        && et_githubLink.getError().length() > 0){

                    submitUserDetails(firstName, lastName, email, githubLink);
                }
            }
        });
    }

    private void submitUserDetails(String firstName, String lastName, String email, String githubLink) {
        // POST details
    }
}