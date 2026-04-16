package com.erel.eyalproject.screens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.erel.eyalproject.R;
import com.erel.eyalproject.model.User;
import com.erel.eyalproject.screens.MainActivity;
import com.erel.eyalproject.services.DatabaseService;


public class UserProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "UserProfileActivity";

    private EditText etUserFirstName, etUserLastName, etUserEmail, etUserPhone;
    private TextView tvUserDisplayName, tvUserDisplayEmail;
    private Button btnUpdateProfile, btnSignOut;

    String userId;
    User selectedUser;
    boolean isCurrentUser = false;

    DatabaseService databaseService;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


       databaseService=DatabaseService.getInstance();



//        if (!selectedUser.isAdmin()) {
//             If the user is not an admin and the selected user is not the current user
//             then finish the activity
//            Toast.makeText(this, "You are not authorized to view this profile", Toast.LENGTH_SHORT).show();
//            finish();
//            return;
//        }



        // Initialize the EditText fields
        etUserFirstName = findViewById(R.id.etUserFirstName);
        etUserLastName = findViewById(R.id.etUserLastName);
        etUserEmail = findViewById(R.id.etUserEmail);
        etUserPhone = findViewById(R.id.etUserPhone);
        tvUserDisplayName = findViewById(R.id.tvUserDisplayName);
        tvUserDisplayEmail = findViewById(R.id.tvUserDisplayEmail);
        btnUpdateProfile = findViewById(R.id.btnUpdateProfile);
        btnSignOut = findViewById(R.id.btnSignOut);
        btnUpdateProfile.setOnClickListener(this);
        btnSignOut.setOnClickListener(this);


        showUserProfile();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnUpdateProfile) {
            updateUserProfile();
            return;
        }
        if(v.getId() == R.id.btnSignOut) {
            signOut();
        }
    }

    private void showUserProfile() {
        // Get the user data from database
        databaseService.getUser( new DatabaseService.DatabaseCallback<User>() {
            @Override
            public void onCompleted(User user) {
                selectedUser = user;
                // Set the user data to the EditText fields
                etUserFirstName.setText(user.getFname());
                etUserLastName.setText(user.getLname());
                etUserEmail.setText(user.getEmail());
                etUserPhone.setText(user.getPhone());

                
                // Update display fields
                String displayName = user.getFname() + " " + user.getLname();
                tvUserDisplayName.setText(displayName);
                tvUserDisplayEmail.setText(user.getEmail());



            }

            @Override
            public void onFailed(Exception e) {
                Log.e(TAG, "Error getting user profile", e);
            }
        });

        // disable the EditText fields if the user is not the current user
        if (!isCurrentUser) {
            etUserEmail.setEnabled(false);

        } else {
            etUserEmail.setEnabled(true);

            btnUpdateProfile.setVisibility(View.VISIBLE);
        }
    }

    private void updateUserProfile() {
        if (selectedUser == null) {
            Log.e(TAG, "User not found");
            Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
            return;
        }
        // Get the updated user data from the EditText fields
        String firstName = etUserFirstName.getText().toString();
        String lastName = etUserLastName.getText().toString();
        String phone = etUserPhone.getText().toString();
        String email = etUserEmail.getText().toString();



        // Update the user object
        selectedUser.setFname(firstName);
        selectedUser.setLname(lastName);
        selectedUser.setPhone(phone);
        selectedUser.setEmail(email);


        // Update the user data in the authentication
        Log.d(TAG, "Updating user profile");
        Log.d(TAG, "Selected user UID: " + selectedUser.getId());
        Log.d(TAG, "Is current user: " + isCurrentUser);
        Log.d(TAG, "User email: " + selectedUser.getEmail());



        updateUserInDatabase(selectedUser);

    }

    private void updateUserInDatabase(User user) {
        Log.d(TAG, "Updating user in database: " + user.getId());
        databaseService.updateUser(user, new DatabaseService.DatabaseCallback<Void>() {
            @Override
            public void onCompleted(Void result) {
                Log.d(TAG, "User profile updated successfully");
                Toast.makeText(UserProfileActivity.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                showUserProfile(); // Refresh the profile view
            }

            @Override
            public void onFailed(Exception e) {
                Log.e(TAG, "Error updating user profile", e);
                Toast.makeText(UserProfileActivity.this, "Failed to update profile", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void signOut() {
        Log.d(TAG, "Sign out button clicked");


        Log.d(TAG, "User signed out, redirecting to LandingActivity");
        Intent landingIntent = new Intent(UserProfileActivity.this, MainActivity.class);
        landingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(landingIntent);
    }
}