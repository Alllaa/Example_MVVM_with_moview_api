package com.example.moviemvvm.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviemvvm.AppClass;
import com.example.moviemvvm.R;
import com.example.moviemvvm.adapter.CustomAdapter3;
import com.example.moviemvvm.model.Favourite;
import com.example.moviemvvm.viewmodel.FavouriteViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavouriteV extends Fragment {
    private RecyclerView recyclerView;
    private GridLayoutManager mGridLayoutManager;
    private FavouriteViewModel favouriteViewModel;
    private List<Favourite> list = new ArrayList<>();
    private CustomAdapter3 customAdapter;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_favourite, container, false);
        recyclerView = view.findViewById(R.id.recycler_favourite);
        mGridLayoutManager = new GridLayoutManager(getContext(), 2);
        customAdapter = new CustomAdapter3(getContext(), list);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(mGridLayoutManager);

        favouriteViewModel = ViewModelProviders.of(this).get(FavouriteViewModel.class);
        //favouriteViewModel.init();
        favouriteViewModel.getMovies().observe(this, new Observer<List<Favourite>>() {
            @Override
            public void onChanged(List<Favourite> favourites) {
                list.addAll(favourites);
                //customAdapter = new CustomAdapter3(getContext(), list);
            }
        });

        customAdapter.setOnDeleteClickListener(new CustomAdapter3.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(int position) {
                favouriteViewModel.delFavouritMovie(list.get(position));
                Toast.makeText(AppClass.getObject(), list.get(position).getTitle()+" is deleted", Toast.LENGTH_SHORT).show();
                list.remove(list.get(position));
                customAdapter.notifyDataSetChanged();
            }
        });


        return view;
    }

}
