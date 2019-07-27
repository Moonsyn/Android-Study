package com.example.test190726;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Constraints;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // 이 부분을 주석처리해도 default 뷰가 나타난다.

        LinearLayout linearLayout = findViewById(R.id.rootLayout);

        Button button = new Button(this);
        //button.setLayoutParams(linearLayout.getLayoutParams());
        button.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        button.setText(R.string.btn);
        button.setTextSize(20.0f);
        button.setTextColor(Color.parseColor("#000000"));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
                finish();
            }
        });
        TextView tv = new TextView(this);
        tv.setText(R.string.tv);
        tv.setTextSize(25.0f);
        tv.setTextColor(Color.parseColor("#000000"));

        ImageView img = new ImageView(this);
        //img.setLayoutParams(linearLayout.getLayoutParams());
        img.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        img.setImageDrawable(getDrawable(R.drawable.good));

        Switch swc = new Switch(this);
        swc.setLayoutParams(linearLayout.getLayoutParams());
        swc.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        linearLayout.addView(button);
        linearLayout.addView(tv);
        linearLayout.addView(img);
        linearLayout.addView(swc);
    }
}
