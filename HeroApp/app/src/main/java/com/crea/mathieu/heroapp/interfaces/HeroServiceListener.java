package com.crea.mathieu.heroapp.interfaces;

import com.crea.mathieu.heroapp.model.Hero;

import java.util.ArrayList;

public interface HeroServiceListener {

    public void responseWithSuccess(ArrayList<Hero> heros);
    public void responseWithError(String errorMesssage );
}
