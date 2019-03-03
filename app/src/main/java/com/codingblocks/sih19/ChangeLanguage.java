package com.codingblocks.sih19;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.security.cert.Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

public class ChangeLanguage extends AsyncTask<String,Void,String> {

    String text;
    Context context;
    String languageCode;
    String response;

    public ChangeLanguage(String text,Context context)
    {
        this.text=text;
        this.context=context;
        SharedPreferences sharedPreferences = context.getSharedPreferences("Language",Context.MODE_PRIVATE);
        languageCode=sharedPreferences.getString("lang","en");
    }

    @Override
    protected String doInBackground(String... strings) {


        try {

            String resultText = text.replace(" ", "%20");

            URL url = new URL("https://translation.googleapis.com/language/translate/v2/?q=" + resultText + "&source=en&target=" + languageCode + "&key=AIzaSyAxj94A5RpybypmEtgAV2CbhgVe9JVWmtA");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.flush();
            writer.close();
            os.close();

            int responseCode = conn.getResponseCode();

            Log.e("Response Code", responseCode + "");

            if (responseCode == HttpsURLConnection.HTTP_OK) {

                BufferedReader in=new BufferedReader(
                        new InputStreamReader(
                                conn.getInputStream()));

                String line="";

                while((line = in.readLine()) != null) {

                    response+= line;
                }

            }
            else {
                response="";
            }

        }
        catch (IOException e) {
            e.getMessage();
        }

        return response;

    }
}
