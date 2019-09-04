package com.example.moviemvvm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviemvvm.R;
import com.example.moviemvvm.model.Favourite;

import java.util.List;

import static com.example.moviemvvm.model.rest.GetMovies.IMAGE_BASE_URL2;

public class CustomAdapter3 extends RecyclerView.Adapter<CustomAdapter3.MyViewHolder> {
    private LayoutInflater inflater;
    List<Favourite> list2;

    private OnDeleteClickListener lis;

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }

    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        lis = onDeleteClickListener;
    }

    public CustomAdapter3(Context ctx, List<Favourite> objects) {
        inflater = LayoutInflater.from(ctx);
        list2 = objects;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_fav_layout, parent, false);
       MyViewHolder holder = new MyViewHolder(view, lis);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(list2.get(position).getTitle());
        String urlPoster = IMAGE_BASE_URL2 + list2.get(position).getPoster_path();
        Glide.with(holder.imageView.getContext()).load(urlPoster).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list2.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;
        private ImageButton imageButton;

        private MyViewHolder(@NonNull View itemView, final OnDeleteClickListener listener) {
            super(itemView);
            textView = itemView.findViewById(R.id.txt);
            imageView = itemView.findViewById(R.id.img);
            imageButton = itemView.findViewById(R.id.img_btn);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        listener.onDeleteClick(position);
                        notifyItemChanged(position);
                    }
                }
            });


        }
    }
}
