package com.example.yiming.wifilocation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import static com.example.yiming.wifilocation.R.styleable.View;


public class MainActivity extends AppCompatActivity {
    RequestQueue Queue;
    String url = "http://192.168.0.103:8080/wifi/test.php";
    Button btn;
    EditText lon_edtext;
    EditText lat_edtext;
    TextView tv;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Queue = Volley.newRequestQueue(getApplicationContext());
        btn = (Button)findViewById(R.id.button1);
        lon_edtext = (EditText)findViewById(R.id.editText);
        lat_edtext = (EditText)findViewById(R.id.editText2);
        tv = (TextView)findViewById(R.id.textView3);

       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Test();
           }
       });
    }

     private  void Test()
     {
         StringRequest mStringRequest = new StringRequest(Request.Method.POST,
                 url, new Response.Listener<String>() {
             @Override
             public void onResponse(String response) {
                 // response
                 tv.setText(response);
                 Log.d("Response", response);
             }
         }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {
                 // error
                // Log.d("Error.Response", response);
             }
         }) {

             @Override
             protected Map<String, String> getParams() {
                 Map<String, String> params = new HashMap<String, String>();
                 params.put("lon", "123.456");
                 params.put("lat", "258.369");
                 params.put("MacAddress","00:0A:02:0B:03:0C");
                 params.put("AP1_RSSI","-10");
                 params.put("AP2_RSSI","-12");
                 params.put("AP3_RSSI","-14");

                 return params;
             }

         };

         Queue.add(mStringRequest);
     }

}
