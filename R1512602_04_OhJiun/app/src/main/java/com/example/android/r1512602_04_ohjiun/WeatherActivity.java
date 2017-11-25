package com.example.android.r1512602_04_ohjiun;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class WeatherActivity extends AppCompatActivity {
    TextView tvWeather, tvDistrict;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        Intent intent = getIntent();
        String zone = intent.getStringExtra("zone");
        String name = intent.getStringExtra("name");

        tvDistrict = findViewById(R.id.district);
        tvDistrict.setText("날씨: "+name);

        tvWeather = findViewById(R.id.weather);

        String url = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone="+zone;

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

                //시간(hour),온도(temp), 날씨(wfKor), 강수확률(ps), 바람방향(wdKor)을 출력한다.
                String headerCd = "";
                String temp = "";
                String wfKor = "";
                String ps = "";
                String wdKor = "";

                boolean isPs = false;
                boolean isTemp = false;
                boolean isWfKor = false;
                boolean isWdKor = false;
                boolean isHeaderCd = false;

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_DOCUMENT) {
                        ;
                    }
                    else if (eventType == XmlPullParser.START_TAG) {
                        String tagName = xpp.getName();
//                        if (tagName.equals("headerCd"))
 //                           isHeaderCd = true;
                        if (tagName.equals("temp"))
                            isTemp = true;
                        if (tagName.equals("wfKor"))
                            isWfKor = true;
                        if (tagName.equals("ps"))
                            isPs = true;
                    }
                    else if (eventType == XmlPullParser.TEXT) {
                        if (isHeaderCd) {
                            headerCd = xpp.getText();
                            isHeaderCd = false;
                        }
//                        if (headerCd.equals("0")) {
                            if (isTemp) {
                                temp = xpp.getText();
                                longitude[count] = Double.parseDouble(temp);
//                                tvBusLongitude.append(count+" 경도: "+gpsX+"\n");
                                isTemp = false;
                            }
                            if (isWfKor) {
                                wfKor = xpp.getText();
                                latitude[count] = Double.parseDouble(wfKor);
//                                tvBusLatitude.append(count+" 위도: "+gpsY+"\n");
                                isWfKor = false;
                            }
                            if (isPs) {
                                ps = xpp.getText();
                                busNum[count] = ps;
                                //                               tvBusNumber.append(count+" 버스번호: "+plainNo+"\n");
                                isPs = false;
                                count++;
                            }
//                        }
                    }
                    else if (eventType == XmlPullParser.END_TAG) {
                        ;
                    }
                    eventType = xpp.next();
                } //while
            }
            catch (Exception e) {
                tvWeather.setText(e.getMessage());

            }

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
