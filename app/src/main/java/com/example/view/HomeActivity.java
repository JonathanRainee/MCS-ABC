package com.example.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.adapter.ItemAdapter;
import com.example.adapter.ViewPagerAdapter;
import com.example.mcs_abc.R;
import com.example.model.Item;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    TabLayout tl;
    ViewPager2 vp;
    ViewPagerAdapter vpa;
    ItemAdapter ia;
    MainFragment mf = new MainFragment();
    BottomNavigationView btv;

    public static ArrayList<Item> items = new ArrayList<>();
    RequestQueue queue;

    int userId;
    int id;
    String title;
    String body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fetch();
        replaceFrag(new MainFragment());
        items.add(new Item(2,2,"asdfasdf", "asfasdfasdf"));
//        ia.setItems(items);
//        ia.notifyDataSetChanged();

        btv = findViewById(R.id.nav);
        ia = new ItemAdapter(this);

        btv.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.homeTtle:
                    replaceFrag(new MainFragment());
                    break;
                case R.id.notifTtle:
                    replaceFrag(new NotifFragment());
                    break;
            }

            return true;
        });

        Log.d("hehe", "onCreate: ");
//        jsonParse();
//        fetchData();
        Log.d("haha", "onCreate: ");
    }

    private void replaceFrag(Fragment frag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fl,frag);
        ft.commit();
    }

    void fetchData(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://jsonplaceholder.typicode.com/posts";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0; i<response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        int userId, id;
                        String title, body;

                        userId = jsonObject.getInt("userId");
                        id = jsonObject.getInt("id");
                        title = jsonObject.getString("title");
                        body = jsonObject.getString("body");

                        Log.i("tidur", "onResponse: ");
                        Log.d("makan", userId+" "+id+" "+title+" "+body);
                        Log.i("minum", "onResponse: "+title);
                        Log.i("bobo", "onResponse: ");
                        items.add(new Item(userId, id, title, body));
                        ia.setData(items);
//                        ia.setItems(items);
//                        ia.notifyDataSetChanged();
                    }catch(Exception E){

                    }
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    void fetch(){
        RequestQueue re = Volley.newRequestQueue(this);
        String url = "https://jsonplaceholder.typicode.com/posts";

        JsonArrayRequest jsonAR = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                int userId, id;
                                String title, body;
                                userId = obj.getInt("userId");
                                id = obj.getInt("id");
                                title = obj.getString("title");
                                body = obj.getString("body");
                                items.add(new Item(userId, id, title, body));
                                Log.d("hoho", "onResponse: ");
                                Log.d("size", "onResponse: "+items.size());
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }


    private void jsonParse(){
        queue = Volley.newRequestQueue(this);
        String url = "https://jsonplaceholder.typicode.com/posts";
        JsonArrayRequest req = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
               for (int i = 0 ; i < response.length(); i++){
                   try {
                       JSONObject obj = response.getJSONObject(i);
                       Log.d("object", obj.getString("title"));

                   }catch (Exception e){
                       Log.e("err line 83", "onResponse: ");
                   }
               }
            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("err line 91", "onErrorResponse: ");
            }
        });
        queue.add(req);

    }
}