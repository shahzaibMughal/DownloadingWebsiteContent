package com.example.shahzaib.downloadingwebsitecontent;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    class DownloadTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... urls) {

            Log.i("The URL: ",urls[0]);

            try
            {
                URL url = new URL(urls[0]);
                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                String result="";
                char currentCharacter;
                int data = 0;


                data = reader.read();
                while(data != -1)
                {
                    currentCharacter = (char)data;
                    result += currentCharacter;
                    data  = reader.read();
                }

                // now all the data is stored in string result variable one by one
                // So its time to print the result on Screen
                return result;

            }catch (Exception e)
            {
                e.printStackTrace();
            }
            return "nothing downloaded";
        }
    }










    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DownloadTask task = new DownloadTask();
        try
        {
            String  result = task.execute("https://www.ecowebhosting.co.uk/").get();
            Log.i("Downloaded data:",result);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
