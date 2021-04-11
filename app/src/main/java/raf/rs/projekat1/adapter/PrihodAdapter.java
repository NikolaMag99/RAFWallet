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
    private final Function<Prihod, Void> onViewClicked;
    private final Function<Prihod, Void> onEditClicked;
    private RashodViewModel recyclerViewModel;

    public PrihodAdapter(@NonNull DiffUtil.ItemCallback<Prihod> diffCallback, Function<Prihod, Void> onCarClicked, Function<Prihod, Void> onViewClicked,
                         Function<Prihod, Void> onEditClicked ) {
        super(diffCallback);
        this.onUserClicked = onCarClicked;
        this.onEditClicked = onEditClicked;
        this.onViewClicked = onViewClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prihod_list_item,parent,false);
        return new ViewHolder(view,parent.getContext(), position -> {
            Prihod prihod = getItem(position);
            onUserClicked.apply(prihod);
            return null;
        },position -> {
            Prihod prihod = getItem(position);
            onViewClicked.apply(prihod);
            return null;
        },position -> {
            Prihod prihod = getItem(position);
            onEditClicked.apply(prihod);
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

        public ViewHolder(@NonNull View itemView, Context context, Function<Integer, Void> onItemClicked, Function<Integer, Void> onEditClicked,
                          Function<Integer, Void> onViewClicked) {
            super(itemView);
            this.context = context;


            itemView.findViewById(R.id.deletePrihod).setOnClickListener(user -> {
                if(getAdapterPosition() != RecyclerView.NO_POSITION) {
                    onItemClicked.apply(getAdapterPosition());
                }
            });

            itemView.findViewById(R.id.prihodLista).setOnClickListener(user -> {
                if(getAdapterPosition() != RecyclerView.NO_POSITION) {
                    onViewClicked.apply(getAdapterPosition());
                }
            });

            itemView.findViewById(R.id.editPrihod).setOnClickListener(user -> {
                if(getAdapterPosition() != RecyclerView.NO_POSITION) {
                    onEditClicked.apply(getAdapterPosition());
                }
            });
        }

        public void bind(Prihod rashod) {
            ImageView imageView = itemView.findViewById(R.id.dolarPrihod);
            imageView.setColorFilter(Color.GREEN);
            ((TextView)itemView.findViewById(R.id.naslovPrihod)).setText(rashod.getNaslov());
            ((TextView)itemView.findViewById(R.id.kolicinaPrihod)).setText(Integer.toString(rashod.getKolicina()));
            ImageView imageDelete = itemView.findViewById(R.id.deletePrihod);
            ImageView imageEdit = itemView.findViewById(R.id.editPrihod);

        }
    }
}
