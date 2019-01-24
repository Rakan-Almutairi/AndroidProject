package com.mobility.rakan.androidproject.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.mobility.rakan.androidproject.R;
import com.mobility.rakan.androidproject.activities.MainActivity;
import com.mobility.rakan.androidproject.activities.ViewNews;
import com.mobility.rakan.androidproject.adapters.NewsAdapter;
import com.mobility.rakan.androidproject.models.Constants;
import com.mobility.rakan.androidproject.models.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ListFragment extends Fragment {

    View rootView;
    ListView listNews;
    ArrayList<News> news;
    ListView tvFList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.list_fragment, container, false);

        tvFList = rootView.findViewById(R.id.list_news);
        news = new ArrayList<>();
        news = readJsonFile("news.json");
        NewsAdapter mNewsAdapter;
        mNewsAdapter = new NewsAdapter(getActivity(), news);
        listNews.setAdapter(mNewsAdapter);
//        listNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                // onclick in list with send the user to the link of the news
//                Intent myIntent = new Intent(getActivity(), ViewNews.class);
//                Bundle b = new Bundle();
//                b.putString(Constants.Link, news.get(i).getLink());
//                myIntent.putExtras(b);
//                startActivity(myIntent);
//            }
//        });
        return rootView;
    }

    public ArrayList readJsonFile(String json_file) {

        //----------------------------------------------
        BufferedReader reader = null;
        String mLine, title, description, link;
        JSONObject obj;
        JSONArray ary;
        news = new ArrayList<>();
        //----------------------------------------------

        try {
            InputStream is = getActivity().getAssets().open(json_file);
            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            mLine = new String(buffer, "UTF-8");

            obj = new JSONObject(mLine);
            ary = obj.getJSONArray("news");

            for (int i = 0; i < ary.length(); i++) {
                News News = new News();
                obj = ary.getJSONObject(i);
                //----------------------------------------------
                title = obj.getString("title");
                description = obj.getString("description");
                link = obj.getString("link");
                //----------------------------------------------
                News.setTitle((i + 1) + "-" + title);
                News.setDescription(description);
                News.setLink(link);
                news.add(News);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
        return news;
    }
}
