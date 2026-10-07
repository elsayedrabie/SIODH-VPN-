/*
 * Created by Cristian Gonzalez on 27/01/24 22:59
 *  Copyright (c) NetFree Mexico 2024 . All rights reserved.
 */

package com.socksfast.ultrasshservice.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import android.util.Log;
import android.widget.Toast;
import com.socksfast.vpn.BuildConfig;
import com.socksfast.vpn.R;
/**
 * @author Skank3r
 */
public class SkProtect {

	//private static final String TAG = SkProtect.class.getSimpleName();
    
    private static final String TAG = new Object() {
   int AntiSniff;
   public String toString() {
      byte[] buf = new byte[19];
      AntiSniff = 744656551;
      buf[0] = (byte) (AntiSniff >>> 5);
      AntiSniff = -2087953474;
      buf[1] = (byte) (AntiSniff >>> 6);
      AntiSniff = -892447074;
      buf[2] = (byte) (AntiSniff >>> 4);
      AntiSniff = 718284228;
      buf[3] = (byte) (AntiSniff >>> 2);
      AntiSniff = 711744868;
      buf[4] = (byte) (AntiSniff >>> 6);
      AntiSniff = -630877860;
      buf[5] = (byte) (AntiSniff >>> 16);
      AntiSniff = 1359493310;
      buf[6] = (byte) (AntiSniff >>> 14);
      AntiSniff = -1585079068;
      buf[7] = (byte) (AntiSniff >>> 6);
      AntiSniff = 467563579;
      buf[8] = (byte) (AntiSniff >>> 22);
      AntiSniff = -1145364575;
      buf[9] = (byte) (AntiSniff >>> 11);
      AntiSniff = 622247146;
      buf[10] = (byte) (AntiSniff >>> 12);
      AntiSniff = 1732046284;
      buf[11] = (byte) (AntiSniff >>> 20);
      AntiSniff = -1663270644;
      buf[12] = (byte) (AntiSniff >>> 10);
      AntiSniff = -1656630563;
      buf[13] = (byte) (AntiSniff >>> 10);
      AntiSniff = 2074608842;
      buf[14] = (byte) (AntiSniff >>> 19);
      AntiSniff = -722663203;
      buf[15] = (byte) (AntiSniff >>> 1);
      AntiSniff = 1356693864;
      buf[16] = (byte) (AntiSniff >>> 17);
      AntiSniff = 695031378;
      buf[17] = (byte) (AntiSniff >>> 4);
      AntiSniff = 1400292283;
      buf[18] = (byte) (AntiSniff >>> 12);
      return new String(buf);
   }
}.toString();
	
	private static final String APP_BASE = (new Object() {
   int MDevz;
   public String toString() {
      byte[] buf = new byte[17];
      MDevz = -864694629;
      buf[0] = (byte) (MDevz >>> 21);
      MDevz = 906196998;
      buf[1] = (byte) (MDevz >>> 11);
      MDevz = -1229011120;
      buf[2] = (byte) (MDevz >>> 23);
      MDevz = -1754911208;
      buf[3] = (byte) (MDevz >>> 8);
      MDevz = -1178528076;
      buf[4] = (byte) (MDevz >>> 23);
      MDevz = 1484373631;
      buf[5] = (byte) (MDevz >>> 10);
      MDevz = -497489858;
      buf[6] = (byte) (MDevz >>> 14);
      MDevz = -623265599;
      buf[7] = (byte) (MDevz >>> 22);
      MDevz = 997159913;
      buf[8] = (byte) (MDevz >>> 8);
      MDevz = 1575152434;
      buf[9] = (byte) (MDevz >>> 3);
      MDevz = -1001090289;
      buf[10] = (byte) (MDevz >>> 3);
      MDevz = 786924208;
      buf[11] = (byte) (MDevz >>> 17);
      MDevz = 36938261;
      buf[12] = (byte) (MDevz >>> 11);
      MDevz = 591778724;
      buf[13] = (byte) (MDevz >>> 13);
      MDevz = -1212294417;
      buf[14] = (byte) (MDevz >>> 10);
      MDevz = 654572010;
      buf[15] = (byte) (MDevz >>> 20);
      MDevz = 729157061;
      buf[16] = (byte) (MDevz >>> 5);
      return new String(buf);
   }
}.toString());


	private static final String x = (new Object() {
		int MDevz;
		public String toString() {
			byte[] buf = new byte[13];
			MDevz = -1283813939;
			buf[0] = (byte) (MDevz >>> 2);
			MDevz = -1225476227;
			buf[1] = (byte) (MDevz >>> 20);
			MDevz = 413142137;
			buf[2] = (byte) (MDevz >>> 5);
			MDevz = -1151811808;
			buf[3] = (byte) (MDevz >>> 19);
			MDevz = 1164448670;
			buf[4] = (byte) (MDevz >>> 3);
			MDevz = -1241995678;
			buf[5] = (byte) (MDevz >>> 4);
			MDevz = -123838017;
			buf[6] = (byte) (MDevz >>> 8);
			MDevz = -1176970336;
			buf[7] = (byte) (MDevz >>> 23);
			MDevz = -1531314015;
			buf[8] = (byte) (MDevz >>> 15);
			MDevz = 1779442844;
			buf[9] = (byte) (MDevz >>> 15);
			MDevz = 1628060510;
			buf[10] = (byte) (MDevz >>> 7);
			MDevz = 2046571968;
			buf[11] = (byte) (MDevz >>> 2);
			MDevz = -932267889;
			buf[12] = (byte) (MDevz >>> 16);
			return new String(buf);
		}
	}.toString());
	
	// Assinatura da Google Play
	//private static final String APP_SIGNATURE = "XbhYZ4Bz/9F4cWLIDMg0wl/+jl8=\n";

	private static SkProtect mInstance;

	private Context mContext;
	
	public static void init(Context context) {
		if (mInstance == null) {
			mInstance = new SkProtect(context);

			// This method will print your certificate signature to the logcat.
			//AndroidTamperingProtectionUtils.getCertificateSignature(context);
		}
	}

	private SkProtect(Context context) {
		mContext = context;
	}
	
	/*public void tamperProtect() {
		AndroidTamperingProtection androidTamperingProtection = new AndroidTamperingProtection.Builder(mContext, APP_SIGNATURE)
			.installOnlyFromPlayStore(false) // By default is set to false.
			.build();

		if (!androidTamperingProtection.validate()) {
			throw new RuntimeException();
		}
	}*/
	
	public void simpleProtect() {
		if (!APP_BASE.equals(mContext.getPackageName().toLowerCase()) ||
				!mContext.getString(R.string.app_name).toLowerCase().equals(x)) {
			throw new RuntimeException();
		}
	}

	public static void CharlieProtect() {
		if (mInstance == null) return;
			
		mInstance.simpleProtect();
		
		// ative apenas ao enviar pra PlayStore
		//mInstance.tamperProtect();
	}
}
