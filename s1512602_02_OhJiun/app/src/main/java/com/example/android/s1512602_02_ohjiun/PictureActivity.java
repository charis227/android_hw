package com.example.android.s1512602_02_ohjiun;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PictureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        TextView txt1 = (TextView) findViewById(R.id.txt1);
        TextView txt2 = (TextView) findViewById(R.id.txt2);
        TextView txt3 = (TextView) findViewById(R.id.txt3);

        ImageView pic1 = (ImageView) findViewById(R.id.pic1);
        ImageView pic2 = (ImageView) findViewById(R.id.pic2);
        ImageView pic3 = (ImageView) findViewById(R.id.pic3);

        Intent intent = getIntent();
        String tag = intent.getStringExtra("intent_tag");
        Resources res = getResources();

        int id_label = res.getIdentifier("place"+tag, "string", getPackageName());
        String label = res.getString(id_label);
        setTitle(label);

        int id_picstring1 = res.getIdentifier("pic"+tag+"01", "string", getPackageName());
        String picstring1 = res.getString(id_picstring1);
        txt1.setText(picstring1);

        int id_picstring2 = res.getIdentifier("pic"+tag+"02", "string", getPackageName());
        String picstring2 = res.getString(id_picstring2);
        txt2.setText(picstring2);

        int id_picstring3 = res.getIdentifier("pic"+tag+"03", "string", getPackageName());
        String picstring3 = res.getString(id_picstring3);
        txt3.setText(picstring3);

        int id_src1 = res.getIdentifier("src"+tag+"01", "string", getPackageName());
        String src1 = res.getString(id_src1);
        int id_drawable1 = res.getIdentifier(src1, "drawable", getPackageName());
        Drawable drawable = res.getDrawable(id_drawable1);
        pic1.setBackground(drawable);

        int id_src2 = res.getIdentifier("src"+tag+"02", "string", getPackageName());
        String src2 = res.getString(id_src2);
        int id_drawable2 = res.getIdentifier(src2, "drawable", getPackageName());
        drawable = res.getDrawable(id_drawable2);
        pic2.setBackground(drawable);

        int id_src3 = res.getIdentifier("src"+tag+"03", "string", getPackageName());
        String src3 = res.getString(id_src3);
        int id_drawable3 = res.getIdentifier(src1, "drawable", getPackageName());
        drawable = res.getDrawable(id_drawable3);
        pic3.setBackground(drawable);
    }

    public void closePicture(View v) {
        finish();
    }

}
