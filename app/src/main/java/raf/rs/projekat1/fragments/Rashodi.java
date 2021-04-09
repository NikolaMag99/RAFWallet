package raf.rs.projekat1.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import raf.rs.projekat1.EditPrihodaActivity;
import raf.rs.projekat1.EditRashodaActivity;
import raf.rs.projekat1.PrikazPrihodaActivity;
import raf.rs.projekat1.PrikazRashodaActivity;
import raf.rs.projekat1.R;
import raf.rs.projekat1.adapter.RashodAdapter;
import raf.rs.projekat1.differ.RashodDiffItemCallback;
import raf.rs.projekat1.models.Rashod;
import raf.rs.projekat1.viewmodels.RashodViewModel;
import timber.log.Timber;

public class Rashodi extends Fragment {

    private RecyclerView recyclerView;
    private ImageView delete;
    private ImageView edit;
    private ImageView dolar;
    private TextView naslov;
    private TextView kolicina;
    private RashodViewModel rashodViewModel;
    private RashodAdapter rashodAdapter;
    private int kolicinaRashoda;
    private String naslovRashoda;

    public Rashodi() {
        super(R.layout.rashodi_recycler);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rashodViewModel = new ViewModelProvider(requireActivity()).get(RashodViewModel.class);
        init(view);
    }

    private void init(View view) {
        initView(view);
        initObservers();
        initListeners();
        initRecycler();
    }


    private void initView(View view) {
        recyclerView = view.findViewById(R.id.listRv);
    }
    private void initListeners() {
    }

    private void initObservers() {
        rashodViewModel.getRashodi().observe(getViewLifecycleOwner(), rashodi -> {
                rashodAdapter.submitList(rashodi);
        });

    }

    private void initRecycler() {
        rashodAdapter = new RashodAdapter(new RashodDiffItemCallback(), rashod -> {
            int pozicija = rashod.getVrednost();
            if(pozicija == 1) {
                rashodViewModel.removeRashod(rashod);
            } else if (pozicija == 2){
                Intent intent = new Intent(getActivity(), EditRashodaActivity.class);
                startActivity(intent);
            }
            else if (pozicija == 3){
                Intent intent = new Intent(getActivity(), PrikazRashodaActivity.class);
                intent.putExtra("rashod", rashod);
                startActivity(intent);
            }

            return null;
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(rashodAdapter);
    }
}
