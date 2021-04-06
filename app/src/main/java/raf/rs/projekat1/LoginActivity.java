package raf.rs.projekat1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import raf.rs.projekat1.fragments.Profil;

public class LoginActivity extends AppCompatActivity {


    private ImageView imageView;
    private EditText ime;
    private EditText prezime;
    private EditText imeBanke;
    private EditText sifra;
    private String pin = "sifra";
    private Button loginBtn;

    public static final String PREF_MESSAGE_KEY = "prefMessageKey";
    public static final String PREF_MESSAGE_KEY2 = "prefMessageKey2";
    public static final String PREF_MESSAGE_KEY3 = "prefMessageKey3";
    public static final int PREFERENCE_WRITE_REQUEST_CODE = 222;
    private boolean messageWritten = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        initView();
        initListener();
    }

    private void initView() {
        imageView = findViewById(R.id.imageLogin);
        prezime = findViewById(R.id.prezimeLogin);
        imeBanke = findViewById(R.id.bankaLogin);
        sifra = findViewById(R.id.passwordLogin);
        ime = findViewById(R.id.imeLogin);
        loginBtn = findViewById(R.id.loginBtn);
    }

    private void initListener() {
        loginBtn.setOnClickListener(v -> {
            if(sifra.getText().toString().equals(pin)) {
                if(ime.getText().toString().isEmpty() || prezime.getText().toString().isEmpty() || imeBanke.getText().toString().isEmpty()){
                    Toast.makeText(this, "Popuni sva polja!", Toast.LENGTH_SHORT).show();
                }else{
                    cuvanje();
                }
            }
           else{
               if(sifra.getText().toString().isEmpty()){
                   Toast.makeText(this, "Niste uneli sifru!", Toast.LENGTH_SHORT).show();
               }else {
                   Toast.makeText(this, "Nije dobra sifra!", Toast.LENGTH_SHORT).show();
               }

            }

        });
    }

    private void cuvanje() {
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        sharedPreferences
                .edit()
                .putString(PREF_MESSAGE_KEY, ime.getText().toString())
                .putString(PREF_MESSAGE_KEY2, prezime.getText().toString())
                .putString(PREF_MESSAGE_KEY3, imeBanke.getText().toString())
                .apply();
        messageWritten = true;
        if (messageWritten){
            Intent intent = new Intent(this, BottomNavigationActivity.class);
//            Intent intent2 = new Intent(this, Profil.class);
//            intent2.putExtra(Profil.USER_KEY, new User(ime.getText().toString()));
            startActivityForResult(intent, PREFERENCE_WRITE_REQUEST_CODE);
            Toast.makeText(this, "Dobro ti meni doso " + ime.getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }


}