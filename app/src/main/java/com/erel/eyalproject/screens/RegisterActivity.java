package com.erel.eyalproject.screens;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.erel.eyalproject.R;
import com.erel.eyalproject.model.User;
import com.erel.eyalproject.services.DatabaseService;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etfname, etlname, etemail, etphone, etpassword;
    Button btnrgn;
    private DatabaseService databaseService;




    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    private String email;
    private String password;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        databaseService = DatabaseService.getInstance();

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        btnrgn = findViewById(R.id.btnrgn);
        etfname = findViewById(R.id.etFname);
        etlname = findViewById(R.id.etLname);
        etemail = findViewById(R.id.etEmail);
        etphone = findViewById(R.id.etPhone);
        etpassword=findViewById(R.id.etPassword);

        btnrgn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String Fname = etfname.getText().toString();
        String Lname = etlname.getText().toString();
         email = etemail.getText().toString();
        password = etpassword.getText().toString();
        String Phone = etphone.getText().toString();
        String Password = etpassword.getText().toString();



        boolean valid = true;

        if (Fname.length() < 2) {
            valid = false;
            Toast.makeText(RegisterActivity.this, "Enter first name", Toast.LENGTH_SHORT).show();
        }
        if (Lname.length() < 2) {
            valid = false;
            Toast.makeText(this, "Enter last name", Toast.LENGTH_SHORT).show();
        }
        if (Phone.length() != 10) {
            valid = false;
            Toast.makeText(this, "Enter phone", Toast.LENGTH_SHORT).show();
        }
        if (!email.contains("@")) {
            valid = false;
            Toast.makeText(this, "Enter valid email", Toast.LENGTH_SHORT).show();
        }

      //  if (valid) {
            //   Intent gotoACD = new Intent(RegisterActivity.this, ActShowData.class);

//            gotoACD.putExtra("fname", Fname);
//            gotoACD.putExtra("lname", Lname);
//            gotoACD.putExtra("email", Email);
//            gotoACD.putExtra("phone", Phone);
//
//            startActivity(gotoACD);


            /// Register user
            registerUser(Fname, Lname, Phone, email, Password);
     //   }
    }


    /// Register the user
    private void registerUser(String fname, String lname, String phone, String email, String password) {
        Log.d("TAG", "registerUser: Registering user...");


        /// create a new user object
        User user = new User("jjj", fname, lname, phone, email, password);


        /// proceed to create the user
        createUserInDatabase(user);

    }


    private void createUserInDatabase(User user) {

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString("email", email);

        editor.putString("password", password);

        editor.commit();

        databaseService.createNewUser(user, new DatabaseService.DatabaseCallback<String>() {
            @Override
            public void onCompleted(String uid) {
                Log.d("TAG", "createUserInDatabase: User created successfully");
                /// save the user to shared preferences
                user.setId(uid);






                Log.d("TAG", "createUserInDatabase: Redirecting to MainActivity");
                /// Redirect to MainActivity and clear back stack to prevent user from going back to register screen
                Intent mainIntent = new Intent(RegisterActivity.this, MainActivity.class);
                /// clear the back stack (clear history) and start the MainActivity
                mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(mainIntent);
            }

            @Override
            public void onFailed(Exception e) {
                Log.e("TAG", "createUserInDatabase: Failed to create user", e);
                /// show error message to user
                Toast.makeText(RegisterActivity.this, "Failed to register user", Toast.LENGTH_SHORT).show();
                /// sign out the user if failed to register

            }
        });
    }
}
