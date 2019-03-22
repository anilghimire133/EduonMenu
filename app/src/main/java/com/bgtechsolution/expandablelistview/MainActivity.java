package com.bgtechsolution.expandablelistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RequestQueue mRequestQueue;
    private MyExListAdapter mExampleAdapter;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    JSONArray subcatarray,jarray;
    JSONObject obj,subObj;
    int j;
    private ArrayList<ParentPojo> ListTerbaru =  null;
    private ArrayList<ChildPojo> ListChildTerbaru =  null;
    private ArrayList<ArrayList<ChildPojo>> ListChildXXXXXXXXX = null;


    ExpandableListView expandableListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expandableListView = findViewById(R.id.expandableListView);
        mRequestQueue = Volley.newRequestQueue(this);

        ListTerbaru = new ArrayList<ParentPojo>();
        ListChildTerbaru = new ArrayList<ChildPojo>();//ArrayList<ArrayList<ChildTerbaru>>>();
        ListChildXXXXXXXXX = new ArrayList<ArrayList<ChildPojo>>();
        jsonParse();
// Listview Group expanded listener
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });


    }


    public void jsonParse() {


     //   final  String url = ("http://ksodari.pythonanywhere.com/api/categories");
        final  String url = ("https://api.myjson.com/bins/7pxzm");




        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        Log.i("response", url + "response:" + response);

                        try {


                            ParentPojo LT = null;
                            ChildPojo CT = null;

                             obj = new JSONObject(response);
                               jarray= obj.getJSONArray("stds");
                            for (int i = 0; i < jarray.length(); i++) {

                                  obj = jarray.getJSONObject(i);
                                String creatorName = obj.getString("stopName"); // yo string paxi ko obj ko name mathi extra ma haleko xa
                                String item_id = obj.getString("stopNum");

                             //   ListTerbaru.add(new ParentPojo( creatorName,item_id,creatorName)); // Yo add vaneko yesle MainMenuPojo ko getId() ko thau ma yo string ko value chae add garne ho; tya UI ma dekhako items Adapter class le gareko ho; yo add le pojo class ma value add gareko ho.

                                LT = new ParentPojo(creatorName,item_id,creatorName);
                                ListTerbaru.add(LT);


                                subcatarray=subObj.getJSONArray("students");

                                for (int j = 0; j < subcatarray.length(); j++) {

                                  String studentName  = subObj.getString("studentName");
                                    String studentId  = subObj.getString("studentId");
                                    CT = new ChildPojo(studentName,studentId);
                                    ListChildTerbaru.add(CT);//get(i).get(i).add(CT);
                                }
                            }

                            ListChildXXXXXXXXX.add(ListChildTerbaru);
                            ExpandableListView list = (ExpandableListView) findViewById(R.id.expandableListView);

                            MyExListAdapter adapter = new MyExListAdapter(MainActivity.this, ListTerbaru, ListChildXXXXXXXXX);
                            list.setAdapter(adapter);

                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "Error Parsing Data", Toast.LENGTH_LONG);
                            e.printStackTrace();
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG);
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(MainActivity.this, "No items available", Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                String token = "2cfceef344c54a65b2405acee44bdb22a8a09487";
                String auth_token = "Token " + token;   // "Token e973419cf2d6da5660f8a42f0d6b73ca87f14780"
                params.put("Authorization", auth_token);
                return params;
            }

//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("api_key", "123456789");
//                return params;
//            }
        };

        mRequestQueue.add(request);
    }

}
