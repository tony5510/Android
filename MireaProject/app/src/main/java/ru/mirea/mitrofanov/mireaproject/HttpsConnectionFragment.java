package ru.mirea.mitrofanov.mireaproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


import ru.mirea.mitrofanov.mireaproject.databinding.FragmentHttpsConnectionBinding;


public class HttpsConnectionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HttpsConnectionFragment() {
        // Required empty public constructor
    }

    private FragmentHttpsConnectionBinding binding;

    String[] locs = new String[0];
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HttpsConnectionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HttpsConnectionFragment newInstance(String param1, String param2) {
        HttpsConnectionFragment fragment = new HttpsConnectionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentHttpsConnectionBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        // Inflate the layout for this fragment
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkinfo = null;
                if (connectivityManager != null) {
                    networkinfo = connectivityManager.getActiveNetworkInfo();
                }
                if (networkinfo != null && networkinfo.isConnected()) {

                    new DownloadPageTask().execute("https://ipinfo.io/json"); // запуск нового потока

                } else {
                    binding.textView.setText("Нет интернета");
                }
            }
        });

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connectivityManager =
                        (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkinfo = null;
                if (connectivityManager != null) {
                    networkinfo = connectivityManager.getActiveNetworkInfo();
                }
                if (networkinfo != null && networkinfo.isConnected()) {
                    if (locs.length == 0) {
                        new DownloadPageWeather().execute("https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&current_weather=true"); // запуск нового потока
                    } else{
                        String path = "https://api.open-meteo.com/v1/forecast?latitude=" + locs[0] + "&longitude=" + locs[1] + "&current_weather=true";
                        new DownloadPageWeather().execute(path);
                    }
                } else {
                    binding.textView.setText("Нет интернета");
                }
            }
        });


        return view;
    }

    private class DownloadPageTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            binding.textView.setText("Загружаем...");
            binding.textView2.setText("Загружаем...");
            binding.textView3.setText("Загружаем...");
            binding.textView4.setText("Загружаем...");
            binding.textView5.setText("Загружаем...");
            binding.textView6.setText("Загружаем...");
            binding.textView7.setText("Загружаем...");
            binding.textView8.setText("Загружаем...");
        }
        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadIpInfo(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        }
        @Override
        protected void onPostExecute(String result) {
            Log.d(MainActivity.class.getSimpleName(), result);
            try {
                JSONObject responseJson = new JSONObject(result);
                Log.d(MainActivity.class.getSimpleName(), "Response: " + responseJson);
                String ip = responseJson.getString("ip");
                Log.d(MainActivity.class.getSimpleName(), "IP: " + ip);
                binding.textView.setText("Ip: " + responseJson.getString("ip"));
                binding.textView2.setText("Город: " + responseJson.getString("city"));
                binding.textView3.setText("Регион: " +responseJson.getString("region"));
                binding.textView4.setText("Страна: " + responseJson.getString("country"));
                binding.textView5.setText("Локация: " + responseJson.getString("loc"));
                binding.textView6.setText("Организация: " + responseJson.getString("org"));
                binding.textView7.setText("Почтовый код: " +responseJson.getString("postal"));
                binding.textView8.setText("Временная зона: " + responseJson.getString("timezone"));
                String str = responseJson.getString("loc");
                locs = str.split(",");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(result);
        }
    }
    private class DownloadPageWeather extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            binding.textView9.setText("Загружаем...");
            binding.textView10.setText("Загружаем...");
            binding.textView11.setText("Загружаем...");
            binding.textView12.setText("Загружаем...");
            binding.textView13.setText("Загружаем...");
        }
        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadIpInfo(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        }
        @Override
        protected void onPostExecute(String result) {
            Log.d(MainActivity.class.getSimpleName(), result);
            try {
                JSONObject responseJson = new JSONObject(result);
                Log.d(MainActivity.class.getSimpleName(), "Response: " + responseJson);
                JSONObject weatherJSON = responseJson.getJSONObject("current_weather");
                binding.textView9.setText("Температура: " + weatherJSON.getString("temperature"));
                binding.textView10.setText("Скорость ветра: " + weatherJSON.getString("windspeed"));
                binding.textView11.setText("Направление ветра: " + weatherJSON.getString("winddirection"));
                if (Integer.parseInt (weatherJSON.getString("is_day")) == 1){
                    binding.textView12.setText("Сейчас день.");
                } else{
                    binding.textView12.setText("Сейчас ночь.");
                }
                binding.textView13.setText("Время: " + weatherJSON.getString("time"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(result);
        }
    }
    private String downloadIpInfo(String address) throws IOException {
        InputStream inputStream = null;
        String data = "";
        try {
            URL url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(100000);
            connection.setConnectTimeout(100000);
            connection.setRequestMethod("GET");
            connection.setInstanceFollowRedirects(true);
            connection.setUseCaches(false);
            connection.setDoInput(true);
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 200 OK
                inputStream = connection.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int read = 0;
                while ((read = inputStream.read()) != -1) {
                    bos.write(read); }
                bos.close();
                data = bos.toString();
            } else {
                data = connection.getResponseMessage()+". Error Code: " + responseCode;
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return data;
    }
}