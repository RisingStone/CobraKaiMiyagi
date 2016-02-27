package cobrakai.com.miyagi.utils;

import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import cobrakai.com.miyagi.R;
import cobrakai.com.miyagi.ui.TypefaceTextView;

/**
 * Created by Gareoke on 2/27/16.
 */
public class Helper {

    public static void setupActionBar(AppCompatActivity activity, String title){
        ActionBar ab = activity.getSupportActionBar();
        ab.setDisplayShowCustomEnabled(true);
        ab.setDisplayShowTitleEnabled(false);

        ab.setBackgroundDrawable(new ColorDrawable(activity.getResources().getColor(R.color.actionbar_background)));

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ab.setElevation(0);
        }

        ab.setCustomView(R.layout.custom_action_bar);
        ((TypefaceTextView)ab.getCustomView().findViewById(R.id.custom_action_bar_text)).setText(title);

        Typeface face=Typeface.createFromAsset(activity.getAssets(),
                "fonts/gyosho.ttf");

        ((TypefaceTextView)ab.getCustomView().findViewById(R.id.custom_action_bar_text)).setTypeface(face);
    }
}
