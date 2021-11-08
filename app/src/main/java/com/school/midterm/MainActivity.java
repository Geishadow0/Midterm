package com.school.midterm;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    EditText editTextUsername;
    EditText editTextPassword;
    TextInputLayout textInputLayoutUsername;
    TextInputLayout textInputLayoutPassword;
    Button buttonLogin;
    SqliteHelper sqliteHelper;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqliteHelper = new SqliteHelper(this);
        initCreateAccountTextView();
        initViews();


        buttonLogin.setOnClickListener(view -> {
            if (validateUsername()&&validatePassword()) {

                String Username = editTextUsername.getText().toString();
                String Password = editTextPassword.getText().toString();

                User currentUser = sqliteHelper.Authenticate(new User(null, Username, null, Password ));

                if (currentUser != null) {
                    Snackbar.make(buttonLogin, "Succesfully Logged in!", Snackbar.LENGTH_LONG).show();

                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra("name",Username);
                    startActivity(intent);
                    finish();
                }
                else {
                    Snackbar.make(buttonLogin, " Failed to log in, please try again", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initCreateAccountTextView() {
        TextView textViewCreateAccount = findViewById(R.id.textViewCreateAccount);
        textViewCreateAccount.setText(fromhtml());
        textViewCreateAccount.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Register.class);
            startActivity(intent);
        });
    }

    private void initViews() {
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        textInputLayoutUsername = findViewById(R.id.textInputLayoutUsername);
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private static Spanned fromhtml() {
        Spanned result;
        result = Html.fromHtml("<font color='#0c0099'> I don't have account yet, </font>" +
                "<font color='#0c0099'>create one</font", Html.FROM_HTML_MODE_LEGACY);
        return result;
    }

    private boolean validateUsername() {
        boolean validuser;

        String Username = editTextUsername.getText().toString();

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
    private boolean validatePassword(){
        boolean validpass;

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