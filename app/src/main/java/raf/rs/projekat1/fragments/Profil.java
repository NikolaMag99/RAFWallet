package raf.rs.projekat1.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import raf.rs.projekat1.LoginActivity;
import raf.rs.projekat1.EditProfil;
import raf.rs.projekat1.R;

public class Profil extends Fragment {


    private TextView ime;
    private TextView prezime;
    private TextView banka;
    private Button odjava;
    private Button promeni;
    private boolean messageWritten = false;
    public static final int PREFERENCE_WRITE_REQUEST_CODE = 222;

    public Profil() {
        super(R.layout.profil);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(this.getActivity().getPackageName(), Context.MODE_PRIVATE);
        String message1 = sharedPreferences.getString(LoginActivity.PREF_MESSAGE_KEY, null);
        String message2 = sharedPreferences.getString(LoginActivity.PREF_MESSAGE_KEY2, null);
        String message3 = sharedPreferences.getString(LoginActivity.PREF_MESSAGE_KEY3, null);

        ime =  view.findViewById(R.id.ImeSaLogin);
        prezime = view.findViewById(R.id.PrezimeSaLogin);
        banka = view.findViewById(R.id.BankaSaLogin);
        ime.setText(message1);
        prezime.setText(message2);
        banka.setText(message3);
        odjava = view.findViewById(R.id.odjavaBtn);
        promeni = view.findViewById(R.id.izmeniBtn);

        odjava.setOnClickListener(v -> {
           sharedPreferences
                   .edit()
                   .putString(LoginActivity.PREF_MESSAGE_KEY, null)
                   .putString(LoginActivity.PREF_MESSAGE_KEY2, null)
                   .putString(LoginActivity.PREF_MESSAGE_KEY3, null)
                   .apply();
            messageWritten = true;
            if (messageWritten) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        promeni.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), EditProfil.class);
            startActivityForResult(intent, PREFERENCE_WRITE_REQUEST_CODE);

        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PREFERENCE_WRITE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(this.getActivity().getPackageName(), Context.MODE_PRIVATE);
            String message1 = sharedPreferences.getString(LoginActivity.PREF_MESSAGE_KEY, null);
            String message2 = sharedPreferences.getString(LoginActivity.PREF_MESSAGE_KEY2, null);
            String message3 = sharedPreferences.getString(LoginActivity.PREF_MESSAGE_KEY3, null);
            ime.setText(message1);
            prezime.setText(message2);
            banka.setText(message3);
        }
    }
}
