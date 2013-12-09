package com.kamosoft.hsevo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private ImageView mCard;

    private int mATQ;
    private int mDEF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCard = (ImageView) findViewById(R.id.card);
        mATQ = 0;
        mDEF = 1;
        showCard(mATQ, mDEF);
    }

    private boolean showCard(int ATQ, int DEF) {
        int id = getResources().getIdentifier("c" + ATQ + "v" + DEF, "drawable", getPackageName());
        if (id == 0) {
            Toast.makeText(this, "Pas encore là !", Toast.LENGTH_SHORT).show();
            return false;
        }
        mCard.setBackgroundResource(id);
        return true;
    }

    public void plusATQ(View v) {
        if (showCard(mATQ + 1, mDEF)) {
            mATQ++;
        }
    }

    public void plusDEF(View v) {
        if (showCard(mATQ, mDEF + 1)) {
            mDEF++;
        }
    }

    public void minusATQ(View v) {
        if (showCard(mATQ - 1, mDEF)) {
            mATQ--;
        }
    }

    public void minusDEF(View v) {
        if (showCard(mATQ, mDEF - 1)) {
            mDEF--;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
