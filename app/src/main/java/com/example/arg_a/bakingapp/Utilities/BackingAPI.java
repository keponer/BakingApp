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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BackingAPI {

    private static final String BAKED_ID        = "id";
    private static final String BAKED_NAME      = "name";
    private static final String BAKED_SERVINGS  = "servings";
    private static final String BAKED_IMAGE     = "image";

    /**
     * VolleyCallback Interface
     */
    public interface VolleyCallback{
        void onSuccess(ArrayList<Baked> arrayList);
        void onError();
    }

    public static synchronized void getBaked (Context context, String url, final VolleyCallback callback){

        final ArrayList<Baked> bakeds = new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(context);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //Parse and add movies to the arrayList
                for(int i = 0;i<response.length();i++){

                    try {
                        Log.d("parse", parseJSONtoBaked(response.getJSONObject(i)).toString());
                        bakeds.add(parseJSONtoBaked(response.getJSONObject(i)));
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


    private static Baked parseJSONtoBaked(JSONObject jsonBaked) throws JSONException {
        Baked baked;

        int id          = jsonBaked.getInt(BAKED_ID);
        String name     = jsonBaked.getString(BAKED_NAME);
        int servings    = jsonBaked.getInt(BAKED_SERVINGS);
        String image    = jsonBaked.getString(BAKED_IMAGE);

        baked = new Baked();

        baked.setId(id);
        baked.setName(name);
        baked.setServings(servings);
        baked.setImage(image);

        return baked;
    }
}
