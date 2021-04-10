package raf.rs.projekat1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import raf.rs.projekat1.fragments.Profil;

public class EditProfil extends AppCompatActivity {

    private EditText ime;
    private EditText prezime;
    private EditText banka;
    private Button izmeni;
    private boolean messageWritten = false;
    public static final int PREFERENCE_WRITE_REQUEST_CODE = 222;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profil);
        init();
    }

    private void init() {
        initView();
        initListener();
    }

    private void initView() {
        prezime = findViewById(R.id.prezimeEdit);
        ime = findViewById(R.id.imeEdit);
        banka = findViewById(R.id.bankaEdit);
        izmeni = findViewById(R.id.izmeniEdit);

    }

    private void initListener() {
        izmeni.setOnClickListener(v -> {
            if(ime.getText().toString().isEmpty() || prezime.getText().toString().isEmpty() || banka.getText().toString().isEmpty()){
                Toast.makeText(this, "Popuni sva polja!", Toast.LENGTH_SHORT).show();
            }
            else {
                izmeniUser();
            }
        });
    }

    private void izmeniUser() {
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        sharedPreferences
                .edit()
                .putString(LoginActivity.PREF_MESSAGE_KEY, ime.getText().toString())
                .putString(LoginActivity.PREF_MESSAGE_KEY2, prezime.getText().toString())
                .putString(LoginActivity.PREF_MESSAGE_KEY3, banka.getText().toString())
                .apply();
        setResult(Activity.RESULT_OK);
        finish();
//        messageWritten = true;
//        if (messageWritten){
//            Intent intent = new Intent(this, BottomNavigationActivity.class);
//            startActivityForResult(intent, PREFERENCE_WRITE_REQUEST_CODE);
//
//        }
    }
}