package com.example.moviemvvm.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviemvvm.AppClass;
import com.example.moviemvvm.R;
import com.example.moviemvvm.adapter.CustomAdapter;
import com.example.moviemvvm.model.MovieNow;
import com.example.moviemvvm.viewmodel.PLayNowViewModel;

import java.util.ArrayList;
import java.util.List;


public class PlayNow extends Fragment {
    private RecyclerView recyclerView;
    private GridLayoutManager mGridLayoutManager;
    private PLayNowViewModel pLayNowViewModel;
    private View view;
    private List<MovieNow> list = new ArrayList<>();
    // private List<MovieNow> copy = new ArrayList<>();
    private CustomAdapter customAdapter;
    private int num = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_play_now, container, false);
        recyclerView = view.findViewById(R.id.recycler_playnow);
        mGridLayoutManager = new GridLayoutManager(getContext(), 2);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull final RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    Toast.makeText(getActivity(), "continue", Toast.LENGTH_SHORT).show();
                    num++;

                    pLayNowViewModel. getMoives(num);

                    Log.d("NumberPLya", String.valueOf(num));

                }
            }
        });


        customAdapter = new CustomAdapter(getContext(), list);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(mGridLayoutManager);

        pLayNowViewModel = ViewModelProviders.of(this).get(PLayNowViewModel.class);

        pLayNowViewModel.getMoives(num).observe(this, new Observer<List<MovieNow>>() {
            @Override
            public void onChanged(List<MovieNow> movieNows) {
                list.addAll(movieNows);
                customAdapter.notifyDataSetChanged();
            }
        });

        customAdapter.setOnFavouriteClickListener(new CustomAdapter.OnItemClickListener() {
            @Override
            public void onFavouriteClick(int position) {
                if (list.get(position).getState() == 0) {
                    Toast.makeText(AppClass.getObject(), "Film number " + position + " add to favourite", Toast.LENGTH_SHORT).show();
                    // Log.d("lolinsert", list.get(position).getTitle() + " state = " + list.get(position).getState());

                    pLayNowViewModel.saveFavouriteFilm(list.get(position));

                    // Log.d("lolinset", list.get(position).getTitle() + " state = " + list.get(position).getState());
                } else {
                    Toast.makeText(AppClass.getObject(), "Film number " + position + " delete from favourite", Toast.LENGTH_SHORT).show();
                    // Log.d("loldelete", list.get(position).getTitle() + " state = " + list.get(position).getState());

                    pLayNowViewModel.saveFavouriteFilm(list.get(position));

                    //Log.d("loldelete", list.get(position).getTitle() + " state = " + list.get(position).getState());

                }
            }

        });
        customAdapter.notifyDataSetChanged();
//        OnLifecycleEvent onLifecycleEvent;

        return view;
    }

}
