package com.example.fetch;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private TextView jsonTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jsonTextView = findViewById(R.id.jsonTextView);

        // Read the JSON file from assets
        String json;
        StringBuilder jsonData = new StringBuilder();
        try {
            InputStream is = getAssets().open("hiring.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);

            // Parse the JSON
            List<JsonData> dataList = parseJsonData(json);
            Map<Integer, List<JsonData>> groupedData = groupJsonData(dataList);

            // Display JSON data in TextView
            displayJsonData(groupedData, jsonData);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    // Parse JSON data into a list of JsonData objects
    List<JsonData> parseJsonData(String json) throws JSONException {
        List<JsonData> dataList = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(json);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int id = jsonObject.getInt("id");
            int listID = jsonObject.getInt("listId");
            String name = jsonObject.optString("name", "");
            if (!name.trim().isEmpty() && !name.equals("null")) {
                JsonData data = new JsonData(id, listID, name);
                dataList.add(data);
            }
        }
        return dataList;
    }

    // Group JsonData objects by listId
    Map<Integer, List<JsonData>> groupJsonData(List<JsonData> dataList) {
        Map<Integer, List<JsonData>> groupedData = new HashMap<>();
        for (JsonData data : dataList) {
            if (!groupedData.containsKey(data.getListID())) {
                groupedData.put(data.getListID(), new ArrayList<>());
            }
            groupedData.get(data.getListID()).add(data);
        }
        return groupedData;
    }

    // Display grouped JSON data in TextView
    void displayJsonData(Map<Integer, List<JsonData>> groupedData, StringBuilder jsonData) {
        for (Map.Entry<Integer, List<JsonData>> entry : groupedData.entrySet()) {
            int listId = entry.getKey();
            List<JsonData> dataList = entry.getValue();
            Collections.sort(dataList, new Comparator<JsonData>() {
                @Override
                public int compare(JsonData o1, JsonData o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
            jsonData.append("List ID: ").append(listId).append("\n");
            for (JsonData data : dataList) {
                jsonData.append("ID: ").append(data.getID())
                        .append(", Name: ").append(data.getName()).append("\n");
            }
            jsonData.append("\n");
        }
        jsonTextView.setText(jsonData.toString());
    }

}
