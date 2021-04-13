package raf.rs.projekat1.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import raf.rs.projekat1.EditPrihodaActivity;
import raf.rs.projekat1.PrikazPrihodaActivity;
import raf.rs.projekat1.R;
import raf.rs.projekat1.adapter.PrihodAdapter;
import raf.rs.projekat1.differ.PrihodDiffItemCallback;
import raf.rs.projekat1.models.Prihod;
import raf.rs.projekat1.viewmodels.PrihodViewModel;

public class Prihodi extends Fragment {

    private RecyclerView recyclerView;
    private PrihodViewModel prihodViewModel;
    private PrihodAdapter prihodAdapter;
    private static final int EDIT_KEY = 123;

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

        }, edit -> {
            Intent intent = new Intent(getActivity(), EditPrihodaActivity.class);
            intent.putExtra("prihodEdit", edit);
            startActivityForResult(intent, EDIT_KEY);
            return null;

        }, view -> {

            Intent intent = new Intent(getActivity(), PrikazPrihodaActivity.class);
            intent.putExtra("prihod", view);
            startActivity(intent);
            return null;
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(prihodAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_CANCELED){
            return;
        }
        if(requestCode == EDIT_KEY && resultCode == Activity.RESULT_OK){
            Prihod old = (Prihod) data.getSerializableExtra("OLD");
            Prihod newPrihod = (Prihod)data.getSerializableExtra("NEW");
//            prihodViewModel.removePrihod(old);
//            prihodViewModel.addPrihod(newPrihod);
            prihodViewModel.editPrihod(old,newPrihod);
            prihodAdapter.notifyDataSetChanged();
            return;
        }
    }


}
