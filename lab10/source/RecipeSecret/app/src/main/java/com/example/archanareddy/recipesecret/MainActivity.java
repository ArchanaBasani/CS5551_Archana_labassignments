package com.example.archanareddy.recipesecret;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends WearableActivity {

    private TextView WearTextView;
    private TextView WearTextView1;
    private EditText key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WearTextView = (TextView) findViewById(R.id.textView_display);
        WearTextView1 = (TextView) findViewById(R.id.textView_display1);
        Button button = (Button) findViewById(R.id.button2);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void getNutrition(View v) {
        key = (EditText) findViewById(R.id.recipe_name);
        String s = key.getText().toString();
        String z = s.replace(" ", "_");
        String getURL = "https://api.nutritionix.com/v1_1/search/"+ z +"?fields=item_name%2Citem_id%2Cbrand_name%2Cnf_calories%2Cnf_total_fat&appId=4a24b224&appKey=e5041838f815b92b081f3417ff72c39a";
        String response = null;
        BufferedReader bfr = null;
        try {
            URL url = new URL(getURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            bfr = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;


            while ((line = bfr.readLine()) != null) {
                sb.append(line + " ");
            }
            response = sb.toString();

        } catch (Exception ex) {
            String Error = ex.getMessage();
        } finally {
            try {
                bfr.close();
            } catch (Exception ex) {

            }
        }

        try {
            JSONObject f = new JSONObject(response);

            JSONArray array = f.getJSONArray("hits");
            JSONObject array1 = array.getJSONObject(0);
            JSONObject fields = array1.getJSONObject("fields");
            String fat = fields.getString("nf_total_fat");
            String cal = fields.getString("nf_calories");

            if (cal == null) {
                Toast.makeText(this, "Sorry unable to fetch data....!", Toast.LENGTH_SHORT).show();
            }

            WearTextView.setText( cal.toString() + "\n");
            WearTextView1.setText( fat.toString());
        } catch (Exception e) {
            String Error = e.getMessage();
        }
    }
}
