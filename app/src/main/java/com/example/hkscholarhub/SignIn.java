package com.example.hkscholarhub;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn extends AppCompatActivity {

    private EditText email, password;
    private APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        apiService = RetrofitClient.getInstance().create(APIService.class);
    }

    public void Signin(View view) {
        String userEmail = email.getText().toString().trim();
        String userPassword = password.getText().toString().trim();

        Log.d("SignIn", "Email: " + userEmail);
        Log.d("SignIn", "Password: " + userPassword);

        if (!userEmail.isEmpty() && !userPassword.isEmpty()) {
            Call<LoginResponse> call = apiService.login(userEmail, userPassword);
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        LoginResponse loginResponse = response.body();
                        boolean status = loginResponse.isStatus();
                        String message = loginResponse.getMessage();
                        String userType = loginResponse.getUserType();  // Retrieved from response

                        if (status) {
                            handleUserRedirection(userType);  // Redirect based on user type
                        } else {
                            Toast.makeText(SignIn.this, message, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.e("SignInError", "Response Error: " + response.code());
                        Toast.makeText(SignIn.this, "Invalid response from server", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                    Log.e("SignInError", "Server Error: " + t.getMessage(), t);
                    Toast.makeText(SignIn.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }

    // Handle redirection based on user type
    private void handleUserRedirection(String userType) {
        Intent intent;

        switch (userType) {
            case "student":
                intent = new Intent(SignIn.this, HomeActivity.class);  // Navigate to student profile
                break;

            case "faculty":
                intent = new Intent(SignIn.this, FacultyHomePage.class);  // Navigate to faculty home page
                break;

            case "admin":
                intent = new Intent(SignIn.this, Admin_HomePage.class);  // Navigate to admin home page
                break;

            default:
                Toast.makeText(this, "Unknown user type: " + userType, Toast.LENGTH_SHORT).show();
                return;
        }

        startActivity(intent);
        finish();
    }

    public void goToForgotPassword(View view) {
        Intent intent = new Intent(SignIn.this, Forgot_Password.class);
        startActivity(intent);
        finish();
    }
}

