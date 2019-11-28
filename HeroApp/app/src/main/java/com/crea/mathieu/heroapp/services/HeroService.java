package com.crea.mathieu.heroapp.services;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.crea.mathieu.heroapp.activities.MainActivity;
import com.crea.mathieu.heroapp.interfaces.HeroServiceListener;
import com.crea.mathieu.heroapp.model.Hero;
import com.crea.mathieu.heroapp.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HeroService {

    private HeroServiceListener listener;

    public HeroService(HeroServiceListener listener) {
        this.listener = listener;
    }

    /*
     * Fetch API or Fetch DataBase
     * api: https://superheroapi.com/
     * do requests on https://superheroapi.com/api/10156029975936156
     * do requests on https://superheroapi.com/api/10156029975936156/search/name

     */


    // GetHeroByName()

    public ArrayList<Hero> getHeroByName(String name) {

        //Fetch API
        //Volley

        String url = "https://superheroapi.com/api/10156029975936156/search/" + name;
        loadFromAPINameOrPower(url);


        //Fetch Database

        return null;
    }

    private void loadFromAPINameOrPower(String url) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(MainActivity.MAIN_CONTEXT);


        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Util.logTag("HeroService1", response.toString());

                        // list de tout les super-hero qui on le meme nom, power level
                        ArrayList<Hero> heros = new ArrayList<Hero>();

                        try {

                                JSONArray results = response.getJSONArray("results");
                                // boucle sure le json
                                for(int i = 0; i< results.length(); i++){
                                    JSONObject jsonObject = results.getJSONObject(i);

                                    Long id = jsonObject.getLong("id");
                                    String name = jsonObject.getString("name");

                                    JSONObject powerstat = jsonObject.getJSONObject("powerstats");
                                    String power = powerstat.getString("power");

                                    String url = jsonObject.getJSONObject("image").getString("url");

                                    Hero hero = new Hero();
                                    hero.setId(id);
                                    hero.setName(name);
                                    hero.setPower(power);
                                    hero.setImage(url);

                                    heros.add(hero);
                                }

                                if(heros.size() > 0){
                                    HeroService.this.listener.responseWithSuccess(heros);
                                } else {
                                    HeroService.this.listener.responseWithError("Hero 'blank' does not exist");
                                }


                        } catch (JSONException jse) {
                            Util.logTag("HeroService2", jse.getMessage());
                            HeroService.this.listener.responseWithError(jse.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Util.logTag("HeroService3", error.getMessage());
                        HeroService.this.listener.responseWithError(error.getMessage());
                    }
                });

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
    }

    private void loadFromAPIId(String url) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(MainActivity.MAIN_CONTEXT);

        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject responseHero) {
                        Util.logTag("HeroService1", responseHero.toString());

                        // list de tout les super-hero qui on le meme nom, power level
                        ArrayList<Hero> heros = new ArrayList<Hero>();

                        try {

                            // JSONArray results = response.getJSONArray("results");
                            // boucle sure le json
                            //for(int i = 0; i< results.length(); i++){
                               // JSONObject jsonObject = results.getJSONObject(i);

                                Long id = responseHero.getLong("id");
                                String name = responseHero.getString("name");

                                JSONObject powerstat = responseHero.getJSONObject("powerstats");
                                String power = powerstat.getString("power");

                                String url = responseHero.getJSONObject("image").getString("url");

                                Hero hero = new Hero();
                                hero.setId(id);
                                hero.setName(name);
                                hero.setPower(power);
                                hero.setImage(url);

                                heros.add(hero);
                           // }

                            if(heros.size() > 0){
                                HeroService.this.listener.responseWithSuccess(heros);
                            } else {
                                HeroService.this.listener.responseWithError("Hero 'blank' does not exist");
                            }


                        } catch (JSONException jse) {
                            Util.logTag("HeroService2", jse.getMessage());
                            HeroService.this.listener.responseWithError(jse.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Util.logTag("HeroService3", error.getMessage());
                        HeroService.this.listener.responseWithError(error.getMessage());
                    }
                });

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
    }

    // GetHeroByID()
    public ArrayList<Hero> getHeroById(int id) {

        String url = "https://superheroapi.com/api/10156029975936156/" + id;
        loadFromAPIId(url);

        Util.logTag("getbyid", url);

        return null;
    }

    // GetHeroByUniverse()

    // GetHeroByPower()
    public ArrayList<Hero> GetHeroByPower(int power) {
        return null;
    }
}
