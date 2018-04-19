package pds.ufrn.com.br.agendadoaluno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        logo = findViewById(R.id.logomarca);
        Animation plimplim = AnimationUtils.loadAnimation(this,R.anim.animation);
        logo.startAnimation(plimplim);
        final Intent login = new Intent(this,Login.class);
        Thread timer = new Thread(){
            public void run () {
                try {
                    sleep(1500);

                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(login);
                    finish();
                }
                }
        };
        timer.start();

    }
    @Override
    protected void onPause() {
        super.onPause();
        logo.clearAnimation();
    }

}
