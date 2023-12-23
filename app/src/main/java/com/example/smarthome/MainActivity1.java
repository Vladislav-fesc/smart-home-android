package com.example.smarthome;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity1 extends AppCompatActivity {
    Button zal, spal1, spal2,  kit;
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        zal = findViewById(R.id.button);
        resultTextView = findViewById(R.id.temp); // Replace with your actual TextView ID
        new WeatherTask().execute("https://api.openweathermap.org/data/2.5/weather?q=Томск&appid=de2e02d88e5bac5f6d25d130147f5d29&units=metric&lang=ru");
        zal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(MainActivity1.this, MainActivity2.class);
                startActivity(mainIntent);
                finish();
            }
        });
        kit = findViewById(R.id.button3);
        kit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent1 = new Intent(MainActivity1.this, MainActivity3.class);
                startActivity(mainIntent1);
                finish();
            }
        });

        spal1 = findViewById(R.id.button5);
        spal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent4 = new Intent(MainActivity1.this, MainActivity6.class);
                startActivity(mainIntent4);
                finish();
            }
        });
        spal2 = findViewById(R.id.button7);
        spal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent5 = new Intent(MainActivity1.this, MainActivity7.class);
                startActivity(mainIntent5);
                finish();
            }
        });


    }

    private class WeatherTask extends AsyncTask<String, String, String> {

        // Будет выполнено до отправки данных по URL
        protected void onPreExecute() {
            super.onPreExecute();
            resultTextView.setTextSize(20);
        }

        // Будет выполняться во время подключения по URL
        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                // Создаем URL подключение, а также HTTP подключение
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                // Создаем объекты для считывания данных из файла
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                // Генерируемая строка
                StringBuilder buffer = new StringBuilder();
                String line = "";

                // Считываем файл и записываем все в строку
                while ((line = reader.readLine()) != null)
                    buffer.append(line).append("\n");

                // Возвращаем строку
                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // Закрываем соединения
                if (connection != null)
                    connection.disconnect();

                try {
                    if (reader != null)
                        reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        // Выполняется после завершения получения данных
        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            // Конвертируем JSON формат и выводим данные в текстовом поле
            try {
                resultTextView.setTextSize(40);
                JSONObject jsonObject = new JSONObject(result);
                JSONObject weather1 = jsonObject.getJSONObject("main");
                double weatherMain0 = weather1.getDouble("temp");
                String vas4 = "4";
                char lastChar4 = vas4.charAt(vas4.length() - 1);
                String vas3 = "3";
                char lastChar3 = vas3.charAt(vas3.length() - 1);
                String vas2 = "2";
                char lastChar2 = vas2.charAt(vas2.length() - 1);
                String vas1 = "1";
                char lastChar1 = vas1.charAt(vas1.length() - 1);
                String vas0 = "0";
                char lastChar0 = vas0.charAt(vas0.length() - 1);
                if (weatherMain0 < 0) {
                    String s = String.valueOf(weatherMain0);
                    int f = s.indexOf(".") + 1;
                    char lastChar = s.charAt(f);
                    char lastCha = s.charAt(1);
                    if (lastChar == lastChar4 || lastChar == lastChar3 || lastChar == lastChar4 || lastChar == lastChar3 || lastChar == lastChar2 || lastChar == lastChar1 || lastChar == lastChar0) {
                        if (lastCha==lastChar0){
                            s = s.substring(1, f - 1);
                            resultTextView.setText(s + "");
                        }else {
                            s = s.substring(0, f - 1);
                            resultTextView.setText(s + "");
                        }


                    } else {
                        double g = weatherMain0 - 1;
                        String s1 = String.valueOf(g);
                        s1 = s1.substring(0, f - 1);
                        resultTextView.setText(s1 + "");

                    }

                } else {
                    String s = String.valueOf(weatherMain0);
                    int f = s.indexOf(".") + 1;
                    char lastChar = s.charAt(f);
                    if (lastChar == lastChar4 || lastChar == lastChar3 || lastChar == lastChar4 || lastChar == lastChar3 || lastChar == lastChar2 || lastChar == lastChar1 || lastChar == lastChar0) {
                        s = s.substring(0, f - 1);
                        resultTextView.setText(s + "");

                    } else {
                        double g = weatherMain0 + 1;
                        String s1 = String.valueOf(g);
                        s1 = s1.substring(0, f - 1);
                        resultTextView.setText(s1 + "");


                    }
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


}