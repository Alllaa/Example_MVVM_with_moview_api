package com.example.moviemvvm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviemvvm.R;
import com.example.moviemvvm.model.MovieRated;

import java.util.List;

import static com.example.moviemvvm.model.rest.GetMovies.IMAGE_BASE_URL2;

public class CustomAdapter2 extends RecyclerView.Adapter<CustomAdapter2.MyViewHolder> {
    private LayoutInflater inflater;
    List<MovieRated> list2;

    public CustomAdapter2(Context ctx, List<MovieRated> objects)
    {
        inflater = LayoutInflater.from(ctx);
        list2 = objects;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_layout, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
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

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txt);
            imageView = itemView.findViewById(R.id.img);
        }
    }
}
