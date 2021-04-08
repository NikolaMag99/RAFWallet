package raf.rs.projekat1.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.function.Function;

import raf.rs.projekat1.R;
import raf.rs.projekat1.models.Prihod;
import raf.rs.projekat1.viewmodels.RashodViewModel;

public class PrihodAdapter extends ListAdapter<Prihod, PrihodAdapter.ViewHolder> {


    private final Function<Prihod, Void> onUserClicked;
    private RashodViewModel recyclerViewModel;

    public PrihodAdapter(@NonNull DiffUtil.ItemCallback<Prihod> diffCallback, Function<Prihod, Void> onCarClicked) {
        super(diffCallback);
        this.onUserClicked = onCarClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prihod_list_item,parent,false);
        return new ViewHolder(view,parent.getContext(), position -> {
            Prihod prihod = getItem(position);
//            recyclerViewModel.removeUser(user);
            onUserClicked.apply(prihod);
            return null;
        });
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Prihod prihod = getItem(position);
        holder.bind(prihod);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Context context;

        public ViewHolder(@NonNull View itemView, Context context, Function<Integer, Void> onItemClicked) {
            super(itemView);
            this.context = context;
            // TODO
            itemView.findViewById(R.id.deletePrihod).setOnClickListener(user -> {
                if(getAdapterPosition() != RecyclerView.NO_POSITION) {
                    onItemClicked.apply(getAdapterPosition());
                }
            });
        }

        public void bind(Prihod rashod) {
            ImageView imageView = itemView.findViewById(R.id.dolarPrihod);
            imageView.setColorFilter(Color.GREEN);
            ((TextView)itemView.findViewById(R.id.naslovPrihod)).setText(rashod.getNaslov());
            ((TextView)itemView.findViewById(R.id.kolicinaPrihod)).setText(Integer.toString(rashod.getKolicina()));
            ImageView imageDelete = itemView.findViewById(R.id.deletePrihod);
            imageDelete.setColorFilter(Color.BLACK);
            ImageView imageEdit = itemView.findViewById(R.id.editPrihod);

        }
    }
}
