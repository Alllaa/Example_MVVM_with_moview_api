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

import com.example.moviemvvm.R;
import com.example.moviemvvm.adapter.CustomAdapter;
import com.example.moviemvvm.adapter.CustomAdapter2;
import com.example.moviemvvm.model.MovieRated;
import com.example.moviemvvm.viewmodel.TopRatedViewModel;

import java.util.ArrayList;
import java.util.List;

public class TopRated extends Fragment {
    private RecyclerView recyclerView;
    private GridLayoutManager mGridLayoutManager;
    private TopRatedViewModel topRatedViewModel;
    private View view;
    private List<MovieRated> list = new ArrayList<>();
    private CustomAdapter2 customAdapter;
    public static int num = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_top_rated, container, false);
        recyclerView = view.findViewById(R.id.recycler_rated);
        mGridLayoutManager = new GridLayoutManager(getContext(), 2);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull final RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    Toast.makeText(getActivity(), "continue", Toast.LENGTH_SHORT).show();
                    num++;

                    topRatedViewModel.getMoivesRated(num);
                    Log.d("NumberPLya", String.valueOf(num));

                }
            }
        });
        customAdapter = new CustomAdapter2(getContext(),list);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(mGridLayoutManager);

        topRatedViewModel = ViewModelProviders.of(this).get(TopRatedViewModel.class);
        topRatedViewModel.intit();

        topRatedViewModel.getMoivesRated(num).observe(this, new Observer<List<MovieRated>>() {
            @Override
            public void onChanged(List<MovieRated> movieRateds) {
                list.addAll(movieRateds);
                customAdapter.notifyDataSetChanged();
            }
        });
        return view;
    }


}
