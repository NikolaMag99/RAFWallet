package raf.rs.projekat1.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import raf.rs.projekat1.R;
import raf.rs.projekat1.models.Prihod;
import raf.rs.projekat1.models.Rashod;
import raf.rs.projekat1.viewmodels.PrihodViewModel;
import raf.rs.projekat1.viewmodels.RashodViewModel;
import timber.log.Timber;

public class Stanje extends Fragment {


    private TextView prihod;
    private TextView rashod;
    private TextView razlika;
    private PrihodViewModel prihodViewModel;
    private RashodViewModel rashodViewModel;
    int zbir = 0;
    int zbirRashod = 0;

    public Stanje() {
        super(R.layout.stanje);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prihodViewModel = new ViewModelProvider(this).get(PrihodViewModel.class);
        rashodViewModel = new ViewModelProvider(this).get(RashodViewModel.class);
        init(view);
    }


    private void init(View view) {
        initView(view);
        initObservers();

    }

    private void initView(View view) {
        prihod =  view.findViewById(R.id.prihodBroj);
        rashod = view.findViewById(R.id.rashodBroj);
        razlika = view.findViewById(R.id.razlikaBroj);

    }

    private void initObservers() {
        prihodViewModel.getPrihodi().observe(getViewLifecycleOwner(), prihodi -> {
            for(Prihod p: prihodi){
                zbir += p.getKolicina();
            }
            prihod.setText(String.valueOf(zbir));
            razlika.setText(String.valueOf(zbir));
            Timber.e(String.valueOf(zbir));
        });

        rashodViewModel.getRashodi().observe(getViewLifecycleOwner(), rashodi -> {
            for(Rashod r: rashodi){
                zbirRashod += r.getKolicina();
            }
            rashod.setText(String.valueOf(zbirRashod));
            if (zbir-zbirRashod > 0){
                razlika.setTextColor(Color.GREEN);
                razlika.setText(String.valueOf(zbir-zbirRashod));
            }else{
                razlika.setTextColor(Color.RED);
                razlika.setText(String.valueOf(zbir-zbirRashod));
            }
            Timber.e(String.valueOf(zbirRashod));
        });

    }

}
