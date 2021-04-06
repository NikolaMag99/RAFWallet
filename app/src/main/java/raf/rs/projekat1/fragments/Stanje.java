package raf.rs.projekat1.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import raf.rs.projekat1.R;
import raf.rs.projekat1.viewmodels.PrihodViewModel;

public class Stanje extends Fragment {


    private TextView prihod;
    private TextView rashod;
    private TextView razlika;
    private PrihodViewModel prihodViewModel;

    public Stanje() {
        super(R.layout.stanje);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }


    private void init(View view) {
        initView(view);
        initObservers();

    }

    private void initObservers() {

    }

    private void initView(View view) {
        prihod =  view.findViewById(R.id.prihodBroj);
        rashod = view.findViewById(R.id.rashodBroj);
        razlika = view.findViewById(R.id.razlikaBroj);

    }
}
