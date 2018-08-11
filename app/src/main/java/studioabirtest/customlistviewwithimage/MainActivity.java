package studioabirtest.customlistviewwithimage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    ArrayList<WeModel> arrayList;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ///--------------initialize array list------------
        arrayList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.mylistview);
        fetchdata();


    }


    public void fetchdata()
    {
       String api_url="http://studioabir.com/cbiusmartapp/api/getimage.php";

        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(api_url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response)
            {
                //------------Initialize array-------------///
                final String[] image = new String[response.length()];
                final String[] name = new String[response.length()];
                final String[] date = new String[response.length()];

                for (int i = 0; i<response.length();i++)
                {
                    try
                    {
                        JSONObject jsonObject = response.getJSONObject(i);
                        //--------------save responce in array for intent data
                        image[i] = jsonObject.getString("photo");
                        name[i] = jsonObject.getString("name");
                        date[i] = jsonObject.getString("date");

                        ///-----------for load data from array list in list view----------///
                        arrayList.add(new WeModel(
                                jsonObject.getString("photo"),
                                jsonObject.getString("name"),
                                jsonObject.getString("date")

                        ));

                    }

                    catch (JSONException e)
                    {
                        Toast.makeText(getApplication(),"Warning: "+e,Toast.LENGTH_LONG).show();

                    }

                    MyAdapter myAdapter = new MyAdapter(getApplicationContext(),R.layout.layoutdemo,arrayList);
                    lv.setAdapter(myAdapter);
                    myAdapter.notifyDataSetChanged();

                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            Intent a = new Intent(MainActivity.this, Details.class);
                            a.putExtra("imagedata",image[i]);
                            a.putExtra("namedata",name[i]);
                            a.putExtra("datedata",date[i]);
                            startActivity(a);
                        }
                    });

                }



            }
        },
                new Response.ErrorListener()

                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(getApplicationContext(),"Warning "+error,Toast.LENGTH_LONG).show();
                    }
                });


        studioabirtest.customlistviewwithimage.AppController.getInstance().addToRequestQueue(jsonArrayRequest);


    }






}
