package com.crea.mathieu.heroapp.fragments;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crea.mathieu.heroapp.R;
import com.crea.mathieu.heroapp.interfaces.HeroServiceListener;
import com.crea.mathieu.heroapp.model.Hero;
import com.crea.mathieu.heroapp.services.HeroService;
import com.crea.mathieu.heroapp.utils.Util;
import com.crea.mathieu.heroapp.views.HeroRecyclerAdapter;

import java.util.ArrayList;

public class ListHerosFragment extends Fragment  {


    private String searchName;
    private static final String tag = "ListHerosFragment";

    public ListHerosFragment(String searchName){
        this.searchName = searchName;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_heros_fragment, container, false);

        Util.logTag(tag, searchName);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Load data from api, api:heroapi
        loadData();


    }

    public void loadData(){
    //TODO fetch API, with volley, with HeroService
        // implemententation de l'interface


        new HeroService(new HeroServiceListener() {
            @Override
            public void responseWithSuccess(ArrayList<Hero> heros) {
                Util.logTag(tag,heros.toString());
                showProgressBar(false);
                setupRecyclerView(heros);
            }

            @Override
            public void responseWithError(String errorMesssage) {
                Util.logTag(tag,errorMesssage);
                makeToast(errorMesssage);
                showProgressBar(false);
            }
        }).getHeroByName(this.searchName);

    }

    private void showProgressBar(boolean show) {
        ProgressBar progressBar = getView().findViewById(R.id.progressBarListHero);

        progressBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    private void makeToast(String errorMesssage) {
        Toast.makeText(this.getContext(), errorMesssage, Toast.LENGTH_LONG).show();
    }



    private void setupRecyclerView(ArrayList<Hero> heros){
        RecyclerView recyclerView = getView().findViewById(R.id.list_heros);
        recyclerView.setVisibility(View.VISIBLE);


        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)

        recyclerView.setAdapter(new HeroRecyclerAdapter(heros, this.getActivity()));
        recyclerView.getAdapter().notifyDataSetChanged();

    }


}
