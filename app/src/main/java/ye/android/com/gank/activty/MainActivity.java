package ye.android.com.gank.activty;

import android.content.ContentValues;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ye.android.com.gank.R;
import ye.android.com.gank.adapter.ImgAdapter;
import ye.android.com.gank.api.Api;
import ye.android.com.gank.data.Results;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    RequestQueue mQueue;
    ListView listView;
    ImgAdapter adapter;
    Context context;
    SwipeRefreshLayout sf;
    int page=1;
    List<Results> data=new ArrayList<Results>();
    public final int Success=1;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Success:
                    adapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        context=getApplicationContext();
        mQueue= Volley.newRequestQueue(context);
        sf=(SwipeRefreshLayout) findViewById(R.id.sf);
        sf.setOnRefreshListener(this);
        listView=(ListView)findViewById(R.id.ls);
        adapter=new ImgAdapter(context,data);
        listView.setAdapter(adapter);
        String url= Api.getData("福利","10","1");
        getData(url);
    }
    public void getData(String url){


        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            boolean error=response.getBoolean("error");
                            if(!error){
                                JSONArray jsonArray = response.getJSONArray("results");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    String jsonObject = jsonArray.getString(i);
                                    Gson gson = new Gson();
                                    data.add(gson.fromJson(jsonObject, Results.class));
                                }
                                mHandler.sendEmptyMessage(Success);
                                for (Results results:data){
                                    Log.d("resul",results.toString());
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {

            }
        });
        mQueue.add(jsonObjectRequest);
    }

    @Override
    public void onRefresh() {
        sf.setRefreshing(false);
        page++;
        String url= Api.getData("福利","10",""+page);
        getData(url);

    }
}
