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
    private TextView tvBusLatitude, tvBusLongitude, tvBusNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvBusLatitude = (TextView) findViewById(R.id.bus_latitude);
        tvBusLongitude = (TextView) findViewById(R.id.bus_longitude);
        tvBusNumber = (TextView) findViewById(R.id.bus_number);

        tvBusNumber.setText("");
        tvBusLongitude.setText("");
        tvBusLatitude.setText("");

        String serviceKey = "VZk4EhfOFiAaRhqvId46im%2BuOcPd%2FzIiLaquayzXt6xEWy2G8n4hojoJnJel4KFwlDV5b5988PmYZZTx9mXWQw%3D%3D";
        String url = "http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList?ServiceKey="+serviceKey+"&strSrch=2016";
        String posUrl = "http://ws.bus.go.kr/api/rest/buspos/getBusPosByRtid?ServiceKey="+serviceKey+"&busRouteId=100100522";

        new DownloadWebpageTask().execute(posUrl);
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
            double latitude[] = new double[30];
            double longitude[] = new double[30];
            String busNum[] = new String[30];
            int count = 0;

            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(new StringReader(result));
                int eventType = xpp.getEventType();

                String headerCd = "";
                String gpsX = "";
                String gpsY = "";
                String plainNo = "";

                boolean isPlainNo = false;
                boolean isGpsX = false;
                boolean isGpsY = false;
                boolean isHeaderCd = false;

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_DOCUMENT) {
                        ;
                    }
                    else if (eventType == XmlPullParser.START_TAG) {
                        String tagName = xpp.getName();
                        if (tagName.equals("headerCd"))
                            isHeaderCd = true;
                        if (tagName.equals("gpsX"))
                            isGpsX = true;
                        if (tagName.equals("gpsY"))
                            isGpsY = true;
                        if (tagName.equals("plainNo"))
                            isPlainNo = true;
                    }
                    else if (eventType == XmlPullParser.TEXT) {
                        if (isHeaderCd) {
                            headerCd = xpp.getText();
                            isHeaderCd = false;
                        }
                        if (headerCd.equals("0")) {
                            if (isGpsX) {
                                gpsX = xpp.getText();
                                longitude[count] = Double.parseDouble(gpsX);
//                                tvBusLongitude.append(count+" 경도: "+gpsX+"\n");
                                isGpsX = false;
                            }
                            if (isGpsY) {
                                gpsY = xpp.getText();
                                latitude[count] = Double.parseDouble(gpsY);
//                                tvBusLatitude.append(count+" 위도: "+gpsY+"\n");
                                isGpsY = false;
                            }
                            if (isPlainNo) {
                                plainNo = xpp.getText();
                                busNum[count] = plainNo;
 //                               tvBusNumber.append(count+" 버스번호: "+plainNo+"\n");
                                isPlainNo = false;
                                count++;
                            }
                        }
                    }
                    else if (eventType == XmlPullParser.END_TAG) {
                        ;
                    }
                    eventType = xpp.next();
                } //while
            }
            catch (Exception e) {
                tvBusLatitude.setText(e.getMessage());

            }

            double lat = 37.5463899;
            double lng = 126.9625194;
            double distance, min=100.0;
            int minIndex=0;
            for (int i=0; i<count; i++) {
                distance = Math.sqrt( Math.pow(lat-latitude[i],2) + Math.pow(lng-longitude[i],2) );
                if (min>distance) {
                    min = distance;
                    minIndex = i;
                }
            }

            tvBusLatitude.setText("경도: "+ Double.toString(latitude[minIndex]));
            tvBusLongitude.setText("위도: "+ Double.toString(longitude[minIndex]));
            tvBusNumber.setText("버스번호: "+busNum[minIndex]);

        }//function

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
