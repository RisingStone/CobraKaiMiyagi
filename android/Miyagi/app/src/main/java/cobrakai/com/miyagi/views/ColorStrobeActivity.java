package cobrakai.com.miyagi.views;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import java.util.Timer;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cobrakai.com.miyagi.R;

public class ColorStrobeActivity extends AppCompatActivity {
    Handler handler = new Handler();

    int counter = 0;

    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {

           if(counter % 2 == 0){
                colorStrobeWrapper.setBackgroundColor(getResources().getColor(R.color.miyagi_flag));
           } else {
                colorStrobeWrapper.setBackgroundColor(getResources().getColor(R.color.holo_blue_dark));
           }
            counter++;

            handler.postDelayed(runnableCode, 500);
        }
    };

    @InjectView(R.id.color_strobe) FrameLayout colorStrobeWrapper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_strobe);

        ButterKnife.inject(this);

        handler.post(runnableCode);
    }
}
