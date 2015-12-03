package com.cc.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.cc.bandian.R;
import com.cc.component.bar.CircleBar;
import com.cc.tool.util.DateUtils;

public class AnimalActivity extends Activity {

    private CircleBar circleBar;
    private TextView mTextview;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_animal, menu);
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_animal);
        circleBar = (CircleBar) findViewById(R.id.circle);
        circleBar.setSweepAngle(360);
        circleBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                circleBar.startCustomAnimation();
            }
        });
        new Handler().postDelayed(new Runnable() {
            public void run() {
                circleBar.setText("100");
            }
        }, 500);
//        setContentView(new MySurfaceView(getApplicationContext())); // 别忘了开始的时候载入我们加工好的的SurfaceView
        mTextview=(TextView)findViewById(R.id.data_tv);

        mTextview.setText(DateUtils.getStringToDate(DateUtils.getCurrentDate())+"");


        String str="130185198704250011";
        int sts= TextUtils.lastIndexOf(str,'1');
        int ii=str.lastIndexOf("11");
        String ing=str.substring(sts);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
