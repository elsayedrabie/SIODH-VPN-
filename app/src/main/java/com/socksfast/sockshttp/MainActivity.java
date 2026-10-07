/*
 * Created by Cristian Gonzalez on 27/01/24 22:59
 *  Copyright (c) NetFree Mexico 2024 . All rights reserved.
 */

package com.socksfast.sockshttp;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;

import android.app.Activity;
import android.content.*;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.*;
import android.os.CountDownTimer;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.view.*;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.ads.*;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.netfreemexico.generador.NetFreeMXGen;
import com.socksfast.sockshttp.activities.ConfigGeralActivity;
import com.socksfast.sockshttp.adapter.LogsAdapter;
import com.socksfast.sockshttp.adapter.PageAdapter;
import com.socksfast.sockshttp.servers.ServersActivity;
import com.socksfast.sockshttp.activities.BaseActivity;
import com.socksfast.sockshttp.util.*;
import com.socksfast.sockshttp.util.AESCrypt;
import com.socksfast.sockshttp.util.ConfigUtil;
import com.socksfast.sockshttp.util.Utils;
import com.socksfast.ultrasshservice.LaunchVpn;
import com.socksfast.ultrasshservice.SocksHttpService;
import com.socksfast.ultrasshservice.config.Settings;
import com.socksfast.ultrasshservice.logger.ConnectionStatus;
import com.socksfast.ultrasshservice.logger.SkStatus;
import com.socksfast.ultrasshservice.tunnel.TunnelManagerHelper;
import com.socksfast.ultrasshservice.util.SkProtect;
import com.socksfast.vpn.R;

import java.io.*;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.json.*;
import org.json.JSONException;

@SuppressLint("SetTextI18n")
@SuppressWarnings("deprecation")
public class MainActivity extends BaseActivity implements SkStatus.StateListener,
        View.OnClickListener {
    private static final String UPDATE_VIEWS = "MainUpdate";
    private Settings mConfig;
    private Handler mHandler;
    private ImageView starterButton;
    private ConfigUtil config;
    private static final String NOTIFICATION_PERMISSION = "android.permission.POST_NOTIFICATIONS";
    private static final int PERMISSION_REQUEST_CODE = 1;
    private InterstitialAd interstitialAd;
    private RewardedAd rewardedAd;
    private CountDownTimer mCountDownTimer;
    private boolean xunin;
    private long mTimeLeftInMillis;
    private long xsa8jf;
    private boolean mTimerEnabled;
    private TextView statu, mTextViewCountDown;
    private TextView servername, serverinfo;
    private LinearLayout serverlayout;
    private ImageView serverimage, tiempodd;
    public static boolean mConnected;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler(Looper.getMainLooper());
        mConfig = new Settings(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        a();
        SkProtect.CharlieProtect();
        IntentFilter filter = new IntentFilter();
        filter.addAction(UPDATE_VIEWS);
        LocalBroadcastManager.getInstance(this).registerReceiver(mActivityReceiver, filter);
        setMainView();
        NetFreeMXGen.getInstance().init(this, mConfig.getPrefsPrivate(), false);
    }

    private void a() {
        setContentView(R.layout.activity_main_drawer);

        BottomNavigationView navigationView = findViewById(R.id.navigation);
        PageAdapter pageAdapter = new PageAdapter(this);
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(pageAdapter);
        viewPager.setOffscreenPageLimit(2);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Menu menu = navigationView.getMenu();
                if (position == 0) {
                    menu.findItem(R.id.homeMenu).setChecked(true);
                } else {
                    menu.findItem(R.id.logMenu).setChecked(true);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        navigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.homeMenu) {
                viewPager.setCurrentItem(0);
            } else {
                viewPager.setCurrentItem(1);
            }
            return true;
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        LogsAdapter logsAdapter = new LogsAdapter(layoutManager, this);
        RecyclerView recyclerView = findViewById(R.id.recyclerLog);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(logsAdapter);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && checkSelfPermission(NOTIFICATION_PERMISSION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{NOTIFICATION_PERMISSION}, PERMISSION_REQUEST_CODE);
        }
        mTextViewCountDown = findViewById(R.id.timerTextView);
        config = new ConfigUtil(this);
        starterButton = findViewById(R.id.activity_starterButtonMain);
        starterButton.setOnClickListener(this);
        updateConfig(true);
        tiempodd = findViewById(R.id.addTime);
        tiempodd.setOnClickListener(v -> tiempo());
        statu = findViewById(R.id.statu);
        serverlayout = findViewById(R.id.layout_mainservers);
        serverlayout.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ServersActivity.class)));
        serverimage = findViewById(R.id.imagemainlayout);
        servername = findViewById(R.id.nombremainlayout);
        serverinfo = findViewById(R.id.infomainlayout);
        vread();

        findViewById(R.id.clearLog).setOnClickListener(this);
        findViewById(R.id.imgUpdate).setOnClickListener(this);
        findViewById(R.id.imgSettings).setOnClickListener(this);

    }


    private void x90a() {
        if (xsa8jf == 0) {
            //	Toast.makeText(this, "tu tiempo es muy corto , agrega", Toast.LENGTH_LONG).show();
            long millisInput = 500 * 1000;
            tiemset(millisInput);
        }
        if (!xunin) {
            goGreenTimer();
        }
    }


    public void tiempo() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.renovar_acceso, null);
        MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(this);
        alertDialogBuilder.setView(inflate);
        RelativeLayout ok = inflate.findViewById(R.id.appButton1);
        LinearLayout bubu = inflate.findViewById(R.id.hadsButton);
        final AlertDialog alert = alertDialogBuilder.create();
        alert.setCanceledOnTouchOutside(false);
        Objects.requireNonNull(alert.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alert.getWindow().setGravity(Gravity.CENTER);
        alert.getWindow().setLayout((int) (getResources().getDisplayMetrics().widthPixels * 0.8d), -2);
        ok.setOnClickListener(p1 -> alert.dismiss());
        bubu.setOnClickListener(p -> {
            try {
                tiempodd.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Cargando anuncio", Toast.LENGTH_SHORT).show();
                loadRewardedAd();
                alert.dismiss();
            } catch (Exception l) {
            }
        });
        alert.show();
    }


    private void doUpdateLayout() {
        setStarterButton(starterButton);
    }

    private int mainposition() {
        SharedPreferences prefs = mConfig.getPrefsPrivate();
        return prefs.getInt("LastSelectedServer", 0);
    }

    public void setMainView() {
        try {
            JSONObject object = config.getServersArray().getJSONObject(mainposition());
            String nombre = object.getString("Name");
            String info = object.getString("Info");
            servername.setText(nombre);
            serverinfo.setText(info);

            setImagen(serverimage, object.getString("Flag"));

        } catch (Exception ignored) {

        }
    }

    public void setImagen(ImageView im, String nameo) throws Exception {
        InputStream inputStream = getAssets().open("flags/" + nameo + ".png");
        im.setImageDrawable(Drawable.createFromStream(inputStream, nameo + ".png"));
    }

    private void updateConfig(final boolean isOnCreate) {
        if (!isOnCreate) {
            Toast.makeText(this, "Buscando actualizacion", Toast.LENGTH_SHORT).show();
        }
        new ConfigUpdate(this, result -> {
            try {
                if (!result.contains("Error al obtener datos")) {
                    String json_data = AESCrypt.decrypt(ConfigUtil.PASSWORD, result);
                    if (isNewVersion(json_data)) {
                        try {
                            File file = new File(getFilesDir(), "Config.json");
                            OutputStream out = new FileOutputStream(file);
                            out.write(result.getBytes());
                            out.flush();
                            out.close();
                            to();


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        if (!isOnCreate) {

                            to4();
                        }
                    }
                } else if (result.contains("Error al obtener datos") && !isOnCreate) {
                    Toast.makeText(MainActivity.this, "No se pudo buscar la actualización", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception ignore) {

            }
        }).start(isOnCreate);
    }


    private boolean isNewVersion(String result) {
        try {
            String current = config.getVersion();
            String update = new JSONObject(result).getString("Version");
            return config.versionCompare(update, current);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void to() {
        Toast.makeText(MainActivity.this, "Servidores Actualizados Con Exito", Toast.LENGTH_LONG).show();
    }

    public void to4() {
        Toast.makeText(MainActivity.this, "Estas en la ultima version ", Toast.LENGTH_LONG).show();
    }

    public void startOrStopTunnel(Activity activity) {
        if (SkStatus.isTunnelActive()) {
            TunnelManagerHelper.stopSocksHttp(activity);
        } else {
            NetFreeMXGen.getInstance().loadServer(config.getServersArray());
            Intent intent = new Intent(activity, LaunchVpn.class);
            intent.setAction(Intent.ACTION_MAIN);
            activity.startActivity(intent);
        }
    }

    public void setStarterButton(ImageView starterButton) {
        String state = SkStatus.getLastState();
        if (starterButton != null) {
            if (SkStatus.SSH_INICIANDO.equals(state)) {
                fx900();//fx
                statu.setTextColor(Color.parseColor("#FF00FFD1"));
                serverlayout.setEnabled(false);
                statu.setText("Iniciando");
                starterButton.setBackgroundDrawable(getDrawable(R.drawable.power1));
            } else if (SkStatus.SSH_PARANDO.equals(state)) {
                fx900();
                statu.setText("Apagando");
                starterButton.setBackgroundDrawable(getDrawable(R.drawable.power1));
                statu.setTextColor(Color.parseColor("#FF00FFD1"));
                serverlayout.setEnabled(true);
            } else if (SkStatus.SSH_DESCONECTADO.equals(state)) {
                fx900();
                statu.setText("Desconectado");
                starterButton.setBackgroundDrawable(getDrawable(R.drawable.power1));
                statu.setTextColor(Color.parseColor("#FF0000"));
                serverlayout.setEnabled(true);

            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View p1) {

        switch (p1.getId()) {
            case R.id.activity_starterButtonMain:
                startOrStopTunnel(this);
                Vibrator vb_service = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vb_service.vibrate(10);
                break;
            case R.id.clearLog:
                SkStatus.clearLog();
                break;
            case R.id.imgSettings:
                startActivity(new Intent(this, ConfigGeralActivity.class));
                break;
            case R.id.imgUpdate:
                updateConfig(false);
                break;
        }
    }

    @Override
    public void updateState(final String state, String msg, int localizedResId, final ConnectionStatus level, Intent intent) {
        mHandler.post(() -> {
            doUpdateLayout();
            if (SkStatus.isTunnelActive()) {
                if (level.equals(ConnectionStatus.LEVEL_CONNECTED)) {
                    starterButton.setBackgroundDrawable(getDrawable(R.drawable.power3));
                    statu.setTextColor(Color.parseColor("#FF0061E8"));
                    statu.setText("Conectado");
                    serverlayout.setEnabled(false);
                    x90a();
                }
            }
        });
    }

    private void cou() {
        TimeUnit pos = TimeUnit.MILLISECONDS;
        long hours = pos.toHours(mTimeLeftInMillis) - TimeUnit.DAYS.toHours(pos.toDays(mTimeLeftInMillis));
        long minutes = pos.toMinutes(mTimeLeftInMillis) - TimeUnit.HOURS.toMinutes(pos.toHours(mTimeLeftInMillis));
        long seconds = pos.toSeconds(mTimeLeftInMillis) - TimeUnit.MINUTES.toSeconds(pos.toMinutes(mTimeLeftInMillis));
        String txx = (hours > 1 ? hours + "h:" : "") + (minutes > 1 ? minutes + "m:" : "") + (seconds + "s");
        mTextViewCountDown.setText(txx);
    }

    private final BroadcastReceiver mActivityReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null)
                return;
            if (action.equals(UPDATE_VIEWS)) {
                setMainView();
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        showInterstitial();
        if (!mTimerEnabled) {
            gameresu();
        }
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                });
            }
        }, 0, 1000);
        SkStatus.addStateListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SkStatus.removeStateListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this)
                .unregisterReceiver(mActivityReceiver);
    }

    public static void updateMainViews(Context context) {
        Intent updateView = new Intent(UPDATE_VIEWS);
        LocalBroadcastManager.getInstance(context)
                .sendBroadcast(updateView);
    }

    @Override
    public void onBackPressed() {
        showExitDialog();
    }

    private void showExitDialog() {
        MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(this);
        alertDialogBuilder.setMessage("Queres salir de la app ?");
        alertDialogBuilder.setNegativeButton("Minimizar", (arg0, arg1) -> {
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
        });
        alertDialogBuilder.setPositiveButton("Salir", (arg0, arg1) -> Utils.exitAll(MainActivity.this));
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    private void fx900() {
        if (xunin) {
            stopGreenTimer();
        }
    }

    private void stopGreenTimer() {
        mCountDownTimer.cancel();
        xunin = false;
    }

    private void tiadd(long time) {
        tiemset(time);
        if (xunin) {
            stopGreenTimer();
        }
        goGreenTimer();
    }

    private void tiemset(long milliseconds) {
        xsa8jf = mTimeLeftInMillis + milliseconds;
        mTimeLeftInMillis = xsa8jf;
        cou();
    }

    private void gamesave() {
        SharedPreferences saved_current_time = getSharedPreferences("BLEESD", Context.MODE_PRIVATE);
        SharedPreferences.Editor time_edit = saved_current_time.edit();
        time_edit.putLong("SE", mTimeLeftInMillis);
        time_edit.apply();
    }

    private void gameresu() {
        SharedPreferences time = getSharedPreferences("BLEESD", Context.MODE_PRIVATE);
        long saved_time = time.getLong("SE", 0);
        tiemset(saved_time);
        String state = SkStatus.getLastState();
        if (SkStatus.SSH_CONECTADO.equals(state)) {
            if (!xunin) {
                goGreenTimer();
            }
        }
        mTimerEnabled = true;
    }

    private void goGreenTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                gamesave();
                cou();
            }

            @Override
            public void onFinish() {
                xunin = false;
                stopGreenTimer();
                xsa8jf = 0;
                Intent stopVPN = new Intent(SocksHttpService.TUNNEL_SSH_STOP_SERVICE);
                LocalBroadcastManager.getInstance(MainActivity.this)
                        .sendBroadcast(stopVPN);
                Toast.makeText(MainActivity.this, "Tu tiempo expiro", Toast.LENGTH_LONG).show();
            }
        }.start();
        xunin = true;
        mConnected = true;
    }

    private void vread() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(
                this,
                "ca-app-pub-3940256099942544/1033173712",
                adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        MainActivity.this.interstitialAd = interstitialAd;
                        interstitialAd.setFullScreenContentCallback(
                                new FullScreenContentCallback() {
                                    @Override
                                    public void onAdDismissedFullScreenContent() {
                                        MainActivity.this.interstitialAd = null;
                                    }

                                    @Override
                                    public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                        MainActivity.this.interstitialAd = null;
                                    }

                                    @Override
                                    public void onAdShowedFullScreenContent() {
                                    }
                                });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        interstitialAd = null;
                    }
                });
    }

    private void showInterstitial() {
        if (interstitialAd != null) {
            interstitialAd.show(this);

        } else {
            vread();
        }
    }

    private void showRewardedVideo() {
        if (rewardedAd == null) {
            return;
        }
        rewardedAd.setFullScreenContentCallback(
                new FullScreenContentCallback() {
                    @Override
                    public void onAdShowedFullScreenContent() {
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                        rewardedAd = null;
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        rewardedAd = null;
                    }
                });
        Activity activityContext = MainActivity.this;
        rewardedAd.show(
                activityContext,
                rewardItem -> {
                    Toast.makeText(MainActivity.this, "Tiempo Renovado", Toast.LENGTH_SHORT).show();
                    tiadd((long) (2 * 3600 * 1000));
                    if (!SkStatus.isTunnelActive()) {
                        fx900();
                        gamesave();
                    }
                });
    }

    private void loadRewardedAd() {
        if (rewardedAd == null) {
            AdRequest adRequest = new AdRequest.Builder().build();
            RewardedAd.load(
                    this,
                    "ca-app-pub-3940256099942544/5224354917",
                    adRequest,
                    new RewardedAdLoadCallback() {
                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            rewardedAd = null;

                            tiempodd.setVisibility(View.VISIBLE);
                            Toast.makeText(MainActivity.this, "Fallo al cargar", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                            MainActivity.this.rewardedAd = rewardedAd;
                            tiempodd.setVisibility(View.VISIBLE);
                            showRewardedVideo();
                        }
                    });
        }
    }
}