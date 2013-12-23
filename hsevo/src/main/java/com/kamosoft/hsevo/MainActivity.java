package com.kamosoft.hsevo;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private FrameLayout mCard;

    private int mATQ;
    private int mDEF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCard = (FrameLayout) findViewById(R.id.card);

        if (savedInstanceState == null) {
            mATQ = 0;
            mDEF = 1;
            getFragmentManager().beginTransaction().
                    add(R.id.card, new CardFragment(getCardId(mATQ, mDEF))).
                    commit();
        }
    }

    private boolean showCard(int ATQ, int DEF) {
        int id = getCardId(ATQ, DEF);
        if (id == 0) {
            Toast.makeText(this, "Pas encore là !", Toast.LENGTH_SHORT).show();
            return false;
        }
        getFragmentManager().
                beginTransaction().
                setCustomAnimations(R.anim.card_flip_right_in, R.anim.card_flip_right_out,
                        R.anim.card_flip_left_in, R.anim.card_flip_left_out).
                replace(R.id.card, new CardFragment(id)).
                addToBackStack(null).
                commit();

        return true;
    }

    private int getCardId(int ATQ, int DEF) {
        return getResources().getIdentifier("c" + ATQ + "v" + DEF, "drawable", getPackageName());
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

    class CardFragment extends Fragment {

        int mResId;

        CardFragment(int resId) {
            mResId = resId;
        }

        CardFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(mResId);
            return imageView;
        }
    }
}
