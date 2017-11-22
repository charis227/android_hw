package com.example.android.s1512602_08_ohjiun;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private TextView tvBusLatitude, tvBusLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvBusLatitude = (TextView) findViewById(R.id.bus_latitude);
        tvBusLongitude = (TextView) findViewById(R.id.bus_longitude);

        String serviceKey = "VZk4EhfOFiAaRhqvId46im%2BuOcPd%2FzIiLaquayzXt6xEWy2G8n4hojoJnJel4KFwlDV5b5988PmYZZTx9mXWQw%3D%3D";
        String url = "http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList?ServiceKey="+serviceKey+"&strSrch=2016";

        new DownloadWebpageTask().execute(url);
    }

    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            try {
                return (String)downloadUrl( (String)urls[0] );
            }
            catch (IOException e) {
                return "다운로드 실패";
            }
        }

        protected void onPostExecute(String result) {
            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(new StringReader(result));
                int eventType = xpp.getEventType();

                String headerCd = "";
                String busRouteId = "";
                String busRouteNm = "";

                boolean bSet_busRouteId = false;
                boolean bSet_headerCd = false;
                boolean bSet_busRouteNm = false;

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_DOCUMENT) {
                        ;
                    }
                    else if (eventType == XmlPullParser.START_TAG) {
                        String tagName = xpp.getName();
                        if (tagName.equals("headerCd"))
                            bSet_headerCd = true;
                        if (tagName.equals("busRouteId"))
                            bSet_busRouteId = true;
                        if (tagName.equals("busRouteNm"))
                            bSet_busRouteNm = true;
                    }
                    else if (eventType == XmlPullParser.TEXT) {
                        
                    }
                }
            }
        }

        private String downloadUrl(String myUrl) throws IOException {
            HttpURLConnection conn = null;
            try {
                URL url = new URL(myUrl);
                conn = (HttpURLConnection) url.openConnection();
                BufferedInputStream buffer = new BufferedInputStream(conn.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(buffer, "utf-8"));
                String line = null;
                String page = "";

                while ( (line = bufferedReader.readLine()) != null ) {
                    page += line;
                }
                return page;
            }
            finally {
                conn.disconnect();
            }
        }
    }

}
