package raf.rs.projekat1.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import raf.rs.projekat1.R;
import raf.rs.projekat1.adapter.PrihodAdapter;
import raf.rs.projekat1.adapter.RashodAdapter;
import raf.rs.projekat1.differ.PrihodDiffItemCallback;
import raf.rs.projekat1.differ.RashodDiffItemCallback;
import raf.rs.projekat1.viewmodels.PrihodViewModel;
import raf.rs.projekat1.viewmodels.RashodViewModel;

public class Prihodi extends Fragment {

    private RecyclerView recyclerView;
    private PrihodViewModel prihodViewModel;
    private PrihodAdapter prihodAdapter;

    public Prihodi() {
        super(R.layout.prihodi_recycler);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prihodViewModel = new ViewModelProvider(requireActivity()).get(PrihodViewModel.class);
        init(view);
    }

    private void init(View view) {
        initView(view);
        initObservers();
        initListeners();
        initRecycler();
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.prihodRv);
    }
    private void initListeners() {
    }

    private void initObservers() {
        prihodViewModel.getPrihodi().observe(getViewLifecycleOwner(), prihodi -> {
            prihodAdapter.submitList(prihodi);
        });

    }

    private void initRecycler() {
        prihodAdapter = new PrihodAdapter(new PrihodDiffItemCallback(), prihod -> {
            prihodViewModel.removePrihod(prihod);
            return null;
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(prihodAdapter);
    }
}
