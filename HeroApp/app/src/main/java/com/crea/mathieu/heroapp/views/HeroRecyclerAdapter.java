package com.crea.mathieu.heroapp.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.crea.mathieu.heroapp.R;
import com.crea.mathieu.heroapp.activities.MainActivity;
import com.crea.mathieu.heroapp.fragments.HeroProfilFragment;
import com.crea.mathieu.heroapp.fragments.ListHerosFragment;
import com.crea.mathieu.heroapp.fragments.UtilNavigationFragment;
import com.crea.mathieu.heroapp.model.Hero;
import com.crea.mathieu.heroapp.utils.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HeroRecyclerAdapter extends RecyclerView.Adapter<HeroRecyclerAdapter.CellViewHolder> {


    private ArrayList<Hero> heros;
    private FragmentActivity context;

    public HeroRecyclerAdapter(ArrayList<Hero> heros, FragmentActivity context) {
        this.heros = heros;
        this.context = context;
    }

    public static class CellViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        LinearLayout linearLayout;

        public CellViewHolder(View v) {
            super(v);

           imageView = v.findViewById(R.id.cellViewImageView);
           textView = v.findViewById(R.id.cellViewHeroName);
           linearLayout = v.findViewById(R.id.ListHeroLinearLayout);
        }

    }
    @NonNull
    @Override
    public CellViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(MainActivity.MAIN_CONTEXT).inflate(R.layout.cell_view, parent, false);

        CellViewHolder viewHolder = new CellViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CellViewHolder holder, int position) {
        final Hero hero = this.heros.get(position);

        holder.textView.setText(hero.getName());

        Picasso.get().load(hero.getImage()).into(holder.imageView);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHeroInfoFragmentById(hero);
            }
        });


    }

    @Override
    public int getItemCount() {
        return this.heros.size();
    }


    private void goToHeroInfoFragmentById(Hero hero){
        HeroProfilFragment heroProfilFragment = new HeroProfilFragment(hero);
        UtilNavigationFragment.nextFragment(heroProfilFragment, this.context);
    }

}
