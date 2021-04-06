package raf.rs.projekat1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        String message = sharedPreferences.getString(LoginActivity.PREF_MESSAGE_KEY, null);
        if(message == null) {
            // Nista jos uvek nije upisano, pokreni PreferenceActivity
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(this, BottomNavigationActivity.class);
            startActivity(intent);
        }

//        startActivity(intent);
        finish();
    }
}