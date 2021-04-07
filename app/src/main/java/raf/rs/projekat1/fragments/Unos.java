package raf.rs.projekat1.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import raf.rs.projekat1.R;

public class Unos extends Fragment  implements AdapterView.OnItemSelectedListener {

    Spinner aSpinner;
    private EditText naslov;
    private EditText kolicina;
    private EditText opis;
    private Button dodaj;
    private CheckBox checkBox;

    public Unos() {
        super(R.layout.unos);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
        initView(view);
        initListener();
        aSpinner = view.findViewById(R.id.aSpinner);
        naslov = view.findViewById(R.id.naslov);
        kolicina = view.findViewById(R.id.kolicina);
        opis = view.findViewById(R.id.opis);
        checkBox = view.findViewById(R.id.audioCheck);
        dodaj = view.findViewById(R.id.dodajUListuBtn);
    }

    private void initView(View view) {
        aSpinner = view.findViewById(R.id.aSpinner);
        naslov = view.findViewById(R.id.naslov);
        kolicina = view.findViewById(R.id.kolicina);
        opis = view.findViewById(R.id.opis);
        checkBox = view.findViewById(R.id.audioCheck);
        dodaj = view.findViewById(R.id.dodajUListuBtn);
    }

    private void initListener() {
        aSpinner.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
