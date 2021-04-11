package raf.rs.projekat1.differ;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;


import raf.rs.projekat1.models.Rashod;

public class RashodDiffItemCallback extends DiffUtil.ItemCallback<Rashod> {

    @Override
    public boolean areItemsTheSame(@NonNull Rashod oldItem, @NonNull Rashod newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Rashod oldItem, @NonNull Rashod newItem) {
        return oldItem.getKolicina() == (newItem.getKolicina()) &&
                oldItem.getNaslov().equals(newItem.getNaslov());
    }

}
