/*
 * Created by Cristian Gonzalez on 27/01/24 22:59
 *  Copyright (c) NetFree Mexico 2024 . All rights reserved.
 */

package com.socksfast.sockshttp.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.socksfast.sockshttp.preference.SettingsPreference;
import com.socksfast.ultrasshservice.config.Settings;
import androidx.appcompat.app.AppCompatDelegate;
import android.content.Context;
import com.socksfast.sockshttp.preference.LocaleHelper;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import static android.content.pm.PackageManager.GET_META_DATA;
import org.json.JSONObject;
import com.socksfast.sockshttp.util.Utils;
import java.io.File;
import com.socksfast.sockshttp.util.AESCrypt;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by Pankaj on 03-11-2017.
 */
public abstract class BaseActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setModoNoturnoLocal();	
		resetTitles();
	}
	
	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(LocaleHelper.setLocale(base));
	}
	
	public void setModoNoturnoLocal() {
		boolean is = new Settings(this).getModoNoturno().equals("on");
		int night_mode = is ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO;
		getDelegate().setLocalNightMode(night_mode);
	}
	
	protected void resetTitles() {
		try {
			ActivityInfo info = getPackageManager().getActivityInfo(getComponentName(), GET_META_DATA);
			if (info.labelRes != 0) {
				setTitle(info.labelRes);
			}
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
	}
}
