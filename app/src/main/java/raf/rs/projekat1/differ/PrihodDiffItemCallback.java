package raf.rs.projekat1.differ;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import raf.rs.projekat1.models.Prihod;
import raf.rs.projekat1.models.Rashod;

public class PrihodDiffItemCallback extends DiffUtil.ItemCallback<Prihod> {

    @Override
    public boolean areItemsTheSame(@NonNull Prihod oldItem, @NonNull Prihod newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Prihod oldItem, @NonNull Prihod newItem) {
        return oldItem.getKolicina() == (newItem.getKolicina()) &&
                oldItem.getNaslov().equals(newItem.getNaslov());
    }

}
