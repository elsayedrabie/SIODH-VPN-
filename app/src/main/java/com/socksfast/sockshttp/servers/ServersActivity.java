/*
 * Created by Cristian Gonzalez on 27/01/24 22:59
 *  Copyright (c) NetFree Mexico 2024 . All rights reserved.
 */

package com.socksfast.sockshttp.servers;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.netfreemexico.generador.NetFreeMXGen;
import com.socksfast.vpn.R;

import android.view.LayoutInflater;
import android.widget.TextView;
import com.socksfast.ultrasshservice.config.Settings;
import com.socksfast.sockshttp.MainActivity;
import com.socksfast.sockshttp.util.ConfigUtil;

import java.util.ArrayList;
import java.util.List;

import android.view.Gravity;
import androidx.appcompat.app.AlertDialog;

import android.graphics.drawable.ColorDrawable;

import org.json.JSONException;
import org.json.JSONObject;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

public class ServersActivity extends AppCompatActivity {
    private List<ServersModel> servidores;
    private ServersAdapter adaptador;
    private ConfigUtil config;
    private Settings mConfig;
    private int PICK_FILE;
	
	private Menu menu;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servers);
        Toolbar mToolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true); 
        servidores = new ArrayList<>();
        cargarservers();
        
        config = new ConfigUtil(this);
        mConfig = new Settings(this);
        RecyclerView recycler = findViewById(R.id.recycler_servers);
		adaptador = new ServersAdapter(servidores);
        adaptador.setOnItemClick(new ServersAdapter.onItemClickListener() {
				@Override
				public void onItemClick(int posicion) {
                    saveSpinner(posicion);
                    MainActivity.updateMainViews(getApplicationContext());
                    finish();     
					//loadServerData(posicion);
				}
			});

        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adaptador);
    }
		
	
    private void cargarservers() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < config.getServersArray().length(); i++) {
                        JSONObject servers = config.getServersArray().getJSONObject(i);
                        String name = servers.getString("Name");
                        String info = servers.getString("Info");
                        String flag = servers.getString("Flag");
                        ServersModel modelo = new ServersModel();
                        modelo.setServerName(name);
                        modelo.setServerFlag(flag);
                        modelo.setServerPosicion(i);
                        modelo.setServerInfo(getString(R.string.app_name));
                        servidores.add(modelo);
                    }
                    ServersActivity.this.runOnUiThread(new Runnable() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void run() {
                            adaptador.notifyDataSetChanged();
                        }
                    });
                } catch (JSONException e) {
                    ServersActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ServersActivity.this, "JSON Error Severs Activity: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });    
                    
                }
            }
        }).start();
    }
    
    private void saveSpinner(int position){
		SharedPreferences prefs = mConfig.getPrefsPrivate();
		SharedPreferences.Editor edit = prefs.edit();
		edit.putInt("LastSelectedServer", position);
		edit.apply();
	}
    
    
   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ser, menu);
        return true;
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {


		// Menu Itens
		switch (item.getItemId()) {

			case R.id.losjshd:
				Changelogs();
				break;

		}

		return super.onOptionsItemSelected(item);
	}
    
    
   public void Changelogs()
    {
		View inflate = LayoutInflater.from(this).inflate(R.layout.notif, null);
        MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(this);
		alertDialogBuilder.setView(inflate); 
		TextView title = inflate.findViewById(R.id.notiftext1);
		TextView ms = inflate.findViewById(R.id.confimsg);
		RelativeLayout ok = inflate.findViewById(R.id.appButton1);
		title.setText("Nota de la actualizacion");
		ms.setText(this.config.geNote());
        
        TextView configVer = inflate.findViewById(R.id.config_v);
		configVer.setText(this.config.getVersion());
        
	//	ok.setText("Hide");
		final AlertDialog alert = alertDialogBuilder.create(); 
		alert.setCanceledOnTouchOutside(false);
       alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		alert.getWindow().setGravity(Gravity.CENTER); 
        alert.getWindow().setLayout((int) (getResources().getDisplayMetrics().widthPixels * 0.8d), -2);
		ok.setOnClickListener(new View.OnClickListener() { 
				@Override
				public void onClick(View p1){
					alert.dismiss();
				}
			});
		alert.show();
	}
    
    
}
