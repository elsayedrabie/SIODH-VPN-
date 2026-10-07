/*
 * Created by Cristian Gonzalez on 27/01/24 22:59
 *  Copyright (c) NetFree Mexico 2024 . All rights reserved.
 */

package com.socksfast.sockshttp;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.socksfast.vpn.R;
import com.socksfast.sockshttp.activities.BaseActivity;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import android.os.CountDownTimer;
import android.view.View;
import android.app.Application;
import android.content.Intent;
import android.view.WindowManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.annotation.TargetApi;
import com.airbnb.lottie.LottieAnimationView;

public class LauncherActivity extends BaseActivity
{
   private LottieAnimationView loading;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
        
        
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
                @Override
                public void run() {
					// inicia atividade principal
					Intent intent = new Intent(getApplicationContext(), MainActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
					startActivity(intent);

					// encerra o launcher
					finish();
                }
            }, 1000);
    }
}
