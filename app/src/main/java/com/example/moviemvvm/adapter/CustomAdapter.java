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
import com.example.moviemvvm.model.MovieNow;

import java.util.List;

import static com.example.moviemvvm.model.rest.GetMovies.IMAGE_BASE_URL;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    List<MovieNow> list2;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onFavouriteClick(int position);
    }

    public void setOnFavouriteClickListener(OnItemClickListener onFavouriteClickListener) {
        mListener = onFavouriteClickListener;
    }

    public CustomAdapter()
    {

    }
    public CustomAdapter(Context ctx, List<MovieNow> objects) {
        inflater = LayoutInflater.from(ctx);
        list2 = objects;
        // Log.d("tag",list2.get(0).getTitle());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.custom_layout, parent, false);
            MyViewHolder holder = new MyViewHolder(view, mListener);
            return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(list2.get(position).getTitle());
        String urlPoster = IMAGE_BASE_URL + list2.get(position).getPoster_path();
        Glide.with(holder.imageView.getContext()).load(urlPoster).into(holder.imageView);
        if(list2.get(position).getState() == 1)
        {
            holder.imageButton.setImageResource(R.drawable.favorite_red);
        }else
        {
            holder.imageButton.setImageResource(R.drawable.favorite_black);

        }

    }

    @Override
    public int getItemCount() {
        return list2.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;
        private ImageButton imageButton;

        private MyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            textView = itemView.findViewById(R.id.txt);
            imageView = itemView.findViewById(R.id.img);
            imageButton = itemView.findViewById(R.id.img_btn);

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        listener.onFavouriteClick(position);
                        notifyItemChanged(position);
                    }
                }
            });

        }
    }
}
