package com.example.arg_a.bakingapp.Utilities;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.arg_a.bakingapp.data.Baked;
import com.example.arg_a.bakingapp.data.Ingredient;
import com.example.arg_a.bakingapp.data.Step;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BackingAPI {

    private static final String BAKED_ID            = "id";
    private static final String BAKED_NAME          = "name";
    private static final String BAKED_SERVINGS      = "servings";
    private static final String BAKED_IMAGE         = "image";
    private static final String BAKED_INGREDIENTS   = "ingredients";
    private static final String BAKED_STEPS         = "steps";

    private static final String INGREDIENT_QUANTITY     = "quantity";
    private static final String INGREDIENT_MEASURE      = "measure";
    private static final String INGREDIENT_INGREDIENT   = "ingredient";

    private static final String STEPS_ID                = "id";
    private static final String STEPS_SHORTDESCRIPTION  = "shortDescription";
    private static final String STEPS_DESCRIPTION       = "description";
    private static final String STEPS_VIDEOURL          = "videoURL";
    private static final String STEPS_THUMBNAILURL      = "thumbnailURL";

    /**
     * VolleyCallback Interface
     */
    public interface VolleyCallback{
        void onSuccess(ArrayList<Baked> arrayList);
        void onError();
    }

    /**
     * With a callback return a List of Baked, including ingredients and steps
     * @param context APP Context
     * @param url URL to get JSONArray
     * @param callback Callback
     */
    public static synchronized void getBaked(Context context, String url, final VolleyCallback callback){

        final ArrayList<Baked> bakeds = new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(context);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //Parse and add movies to the arrayList
                for(int i = 0;i<response.length();i++){

                    try {
                        bakeds.add(parseJSONtoBaked(response.getJSONObject(i)));
                        bakeds.get(i).setIngredients(getIngredients(response.getJSONObject(i)));
                        bakeds.get(i).setSteps(getSteps(response.getJSONObject(i)));
                        Log.d("parse", bakeds.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }
        }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

        queue.add(jsonArrayRequest);
    }

    /**
     * Given a JSONObject creates a baked object with it
     * @param jsonBaked JSONObject to parse
     * @return Baked object
     * @throws JSONException
     */
    private static Baked parseJSONtoBaked(JSONObject jsonBaked) throws JSONException {
        Baked baked = new Baked();

        int id          = jsonBaked.getInt(BAKED_ID);
        String name     = jsonBaked.getString(BAKED_NAME);
        int servings    = jsonBaked.getInt(BAKED_SERVINGS);
        String image    = jsonBaked.getString(BAKED_IMAGE);

        baked.setId(id);
        baked.setName(name);
        baked.setServings(servings);
        baked.setImage(image);

        return baked;
    }

    /**
     * Given a JSONObject creates a List of Ingredient
     * @param jsonBaked JSONObject contains a baked
     * @return List of Ingredients
     * @throws JSONException
     */
    private static List<Ingredient> getIngredients(JSONObject jsonBaked) throws JSONException {

        List<Ingredient> ingredientList = new ArrayList<>();
        Ingredient ingredient = new Ingredient();

        JSONArray jsonIngredients = jsonBaked.getJSONArray(BAKED_INGREDIENTS);

        for(int i = 0;i<jsonIngredients.length();i++){

            JSONObject jsonObjectIngredients = jsonIngredients.getJSONObject(i);
            Double quantity         = jsonObjectIngredients.getDouble(INGREDIENT_QUANTITY);
            String measure          = jsonObjectIngredients.getString(INGREDIENT_MEASURE);
            String nameIngredient   = jsonObjectIngredients.getString(INGREDIENT_INGREDIENT);

            ingredient.setQuantity(quantity);
            ingredient.setMeasure(measure);
            ingredient.setIngredient(nameIngredient);

            ingredientList.add(ingredient);
        }

        return ingredientList;

    }

    /**
     * Given a JSONObject baked return a list of steps
     * @param jsonBaked JSONObject to parse
     * @return List of steps
     * @throws JSONException
     */
    private static List<Step> getSteps(JSONObject jsonBaked) throws JSONException {

        List<Step> stepList = new ArrayList<>();
        Step step = new Step();

        JSONArray jsonSteps = jsonBaked.getJSONArray(BAKED_STEPS);

        for (int i = 0; i < jsonSteps.length(); i++) {

            JSONObject jsonObjectSteps = jsonSteps.getJSONObject(i);

            int id = jsonObjectSteps.getInt(STEPS_ID);
            String shortDescription = jsonObjectSteps.getString(STEPS_SHORTDESCRIPTION);
            String description = jsonObjectSteps.getString(STEPS_DESCRIPTION);
            String videoURL = jsonObjectSteps.getString(STEPS_VIDEOURL);
            String thumbnailURL = jsonObjectSteps.getString(STEPS_THUMBNAILURL);

            step.setId(id);
            step.setShortDescription(shortDescription);
            step.setDescription(description);
            step.setVideoURL(videoURL);
            step.setThumbnailURL(thumbnailURL);
        }

        return stepList;

    }
}
