package com.school.midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class Register extends AppCompatActivity {
    //Declaration EditTexts
    EditText editTextUsername;
    EditText editTextEmail;
    EditText editTextPassword;

    //Declaration textInputLayout
    TextInputLayout textInputLayoutUsername;
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;

    //Declaration Button
    Button buttonRegister;

    //Declaration SqliteHelper
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sqliteHelper = new SqliteHelper(this);
        initTextViewLogin();
        initViews();

        buttonRegister.setOnClickListener(view -> {
            if (validateUsername()&&validateEmail()&&validatePassword()) {
                String UserName = editTextUsername.getText().toString();
                String Email = editTextEmail.getText().toString();
                String Password = editTextPassword.getText().toString();

                //Check in the database is there any user associated with this email
                if (!sqliteHelper.isEmailExists(Email)) {
                    //Email does not exist now add new user to database
                    sqliteHelper.addUser(new User(null, UserName, Email, Password));
                    Snackbar.make(buttonRegister, "User created Succesfully! Please Login", Snackbar.LENGTH_LONG).show();
                    new Handler(Looper.getMainLooper()).postDelayed(this::finish, Snackbar.LENGTH_LONG);
                } else {
                    //Email exist with email input provided so show error user already exist
                    Snackbar.make(buttonRegister, "User already exist with same email ", Snackbar.LENGTH_LONG).show();
                }
            }
            else{
                Snackbar.make(buttonRegister,"Gagal Register", Snackbar.LENGTH_LONG).show();
            }
        });
    }
    //This method used to set Login TextView click event
    private void initTextViewLogin(){
        TextView textViewLogin = findViewById(R.id.textViewLogin);
        textViewLogin.setOnClickListener(view -> finish());
    }

    //This method is used to connect XML views to its Objects
    private void initViews() {
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextUsername = findViewById(R.id.editTextUsername);
        textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);
        textInputLayoutUsername = findViewById(R.id.textInputLayoutUsername);
        buttonRegister = findViewById(R.id.buttonRegister);
    }

    //This method is used to validate input given by user
    private boolean validateUsername() {
        boolean validuser;

        //get values from Edittext fields
        String Username = editTextUsername.getText().toString();

        //Handling validation for UserName field
        if (Username.isEmpty()) {
            validuser = false;
            textInputLayoutUsername.setError("Please enter valid username!");
        }
        else {
            if (Username.length() >= 5) {
                validuser = true;
                textInputLayoutUsername.setError(null);
            } else {
                validuser = false;
                textInputLayoutUsername.setError("Username is to Short!");
            }
        }
        return validuser;
    }

    //This method is used to validate input email
    private boolean validateEmail() {
        boolean validemail;

        //get values from Edittext fields
        String Email = editTextEmail.getText().toString();

        //Handling validation for UserName field
        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            validemail = false;
            textInputLayoutEmail.setError("Please enter valid email");
        } else {
            validemail = true;
            textInputLayoutEmail.setError(null);
        }
        return validemail;
    }

    //Handling validation for password field
    private boolean validatePassword(){
        boolean validpass;

        //Get values from EditText fields
        String Password = editTextPassword.getText().toString();

        //Handling validation for Password field
        if (Password.isEmpty()) {
            validpass = false;
            textInputLayoutPassword.setError("Please enter valid password!");
        } else {
            if (Password.length() >= 5) {
                validpass = true;
                textInputLayoutPassword.setError(null);
            } else {
                validpass = false;
                textInputLayoutPassword.setError("Password is to short!");
            }
        }
        return validpass;
    }
}