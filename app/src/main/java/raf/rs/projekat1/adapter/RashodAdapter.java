package raf.rs.projekat1.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
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
import raf.rs.projekat1.models.Rashod;
import raf.rs.projekat1.viewmodels.RashodViewModel;

public class RashodAdapter extends ListAdapter<Rashod, RashodAdapter.ViewHolder> {


    private final Function<Rashod, Void> onUserClicked;
    private final Function<Rashod, Void> onViewClicked;
    private final Function<Rashod, Void> onEditClicked;
    private RashodViewModel recyclerViewModel;
    public int pozicija;

    public RashodAdapter(@NonNull DiffUtil.ItemCallback<Rashod> diffCallback, Function<Rashod, Void> onCarClicked, Function<Rashod, Void> onViewClicked,
                         Function<Rashod, Void> onEditClicked) {
        super(diffCallback);
        this.onUserClicked = onCarClicked;
        this.onEditClicked = onEditClicked;
        this.onViewClicked = onViewClicked;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rashod_list_item,parent,false);
        return new ViewHolder(view,parent.getContext(), position -> {
            Rashod rashod = getItem(position);
            onUserClicked.apply(rashod);
            return null;
        },position -> {
            Rashod rashod = getItem(position);
            onViewClicked.apply(rashod);
            return null;
        },position -> {
            Rashod rashod = getItem(position);
            onEditClicked.apply(rashod);
            return null;
        });
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Rashod rashod = getItem(position);
        holder.bind(rashod);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Context context;

        public ViewHolder(@NonNull View itemView, Context context, Function<Integer, Void> onItemClicked, Function<Integer, Void> onEditClicked,
                          Function<Integer, Void> onViewClicked) {
            super(itemView);
            this.context = context;
            // TODO
            itemView.findViewById(R.id.deleteRashod).setOnClickListener(user -> {
                if(getAdapterPosition() != RecyclerView.NO_POSITION) {
                    onItemClicked.apply(getAdapterPosition());
                }
            });
            itemView.findViewById(R.id.rashodLista).setOnClickListener(user -> {
                if(getAdapterPosition() != RecyclerView.NO_POSITION) {
                    onViewClicked.apply(getAdapterPosition());
                }
            });
            itemView.findViewById(R.id.editRashod).setOnClickListener(user -> {
                if(getAdapterPosition() != RecyclerView.NO_POSITION) {
                    onEditClicked.apply(getAdapterPosition());
                }
            });
        }

        public void bind(Rashod rashod) {
            ImageView imageView = itemView.findViewById(R.id.dolarPic);
            imageView.setColorFilter(Color.RED);
            ((TextView)itemView.findViewById(R.id.naslovRashod)).setText(rashod.getNaslov());
            ((TextView)itemView.findViewById(R.id.kolicinaRashod)).setText(Integer.toString(rashod.getKolicina()));
            ImageView imageDelete = itemView.findViewById(R.id.deleteRashod);
            ImageView imageEdit = itemView.findViewById(R.id.editRashod);

        }
    }
}
