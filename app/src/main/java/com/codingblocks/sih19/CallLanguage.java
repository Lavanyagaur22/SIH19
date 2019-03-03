package com.codingblocks.sih19;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class CallLanguage {

    String text,result;
    Context context;

    public CallLanguage(String text, Context context){
       this.text=text;
       this.context=context;
    }

    public String getResult(){

        ChangeLanguage changeLanguage=new ChangeLanguage(text, context);

        try {
            result = changeLanguage.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            String[] jugaad = result.split("\"");


            return jugaad[7];
        }
        catch (Exception e) {
            return text;
        }
    }
}
