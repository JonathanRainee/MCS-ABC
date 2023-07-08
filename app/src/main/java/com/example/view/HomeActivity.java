package com.example.view;

import androidx.appcompat.app.AppCompatActivity;
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
import com.example.adapter.ViewPagerAdapter;
import com.example.mcs_abc.R;
import com.example.model.Item;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    TabLayout tl;
    ViewPager2 vp;
    ViewPagerAdapter vpa;

    ArrayList<Item> items = new ArrayList<>();
    RequestQueue queue;

    int userId;
    int id;
    String title;
    String body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        vp = findViewById(R.id.vp2);
        tl = findViewById(R.id.tabLayout2);
        vpa = new ViewPagerAdapter(this);
        vp.setAdapter(vpa);


        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        vp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                tl.getTabAt(position).select();
            }
        });
        Log.d("hehe", "onCreate: ");
        jsonParse();
        fetchData();
        Log.d("haha", "onCreate: ");

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

                        Log.d("makan", userId+" "+id+" "+title+" "+body);

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