package com.crea.mathieu.heroapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.crea.mathieu.heroapp.R;
import com.crea.mathieu.heroapp.model.Hero;
import com.squareup.picasso.Picasso;

public class HeroProfilFragment extends Fragment {
    private Hero hero;

    public HeroProfilFragment(Hero hero){
        this.hero = hero;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //TODO create our view
        // inflate view with layout
        View view = inflater.inflate(R.layout.activity_hero, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView nomHero = view.findViewById(R.id.textNameView);
        nomHero.setText(hero.getName());

        TextView idHero = view.findViewById(R.id.textIdView);
        idHero.setText("Id hero: ");
        idHero.append(Long.toString(hero.getId()));

        TextView powerHero = view.findViewById(R.id.textPowerView);
        powerHero.setText("Power: ");
        powerHero.append(hero.getPower());

        ImageView imageHero = view.findViewById(R.id.imageHeroView);


        Picasso.get().load(hero.getImage()).into(imageHero);
    }
}
