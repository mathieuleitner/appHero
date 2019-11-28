package com.crea.mathieu.heroapp.fragments;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.crea.mathieu.heroapp.R;
import com.crea.mathieu.heroapp.utils.Util;

public class SearchHeroFragment extends Fragment {

    private static final String tag = "SearchHeroFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //TODO create our view
        // inflate view with layout
        View view = inflater.inflate(R.layout.search_fragment_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText searchEditText = view.findViewById(R.id.searchHeroName);

        Button valider = view.findViewById(R.id.buttonValidateHeroName);

        // Onclick method
        valider.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // get text from editbox when button is clicked and log it's message
                String searchName = searchEditText.getText().toString();
                Util.logTag(tag, searchName);
                //go to ListHero Fragment with a "parameter" searchName
                goToListHerosFragmentWithName(searchName);

            }
        });
    }

    private void goToListHerosFragmentWithName(String searchName) {
        ListHerosFragment listHerosFragment = new ListHerosFragment(searchName);

        UtilNavigationFragment.nextFragment(listHerosFragment, this.getActivity());

    }
}
