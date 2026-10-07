/*
 * Created by Cristian Gonzalez on 27/01/24 22:59
 *  Copyright (c) NetFree Mexico 2024 . All rights reserved.
 */

package com.socksfast.sockshttp.util;

import android.app.Activity;
import android.os.Bundle;

/*
 * To combat background service being stopped/swiped
 */
public class DummyActivity extends Activity {
	@Override
	public void onCreate( Bundle icicle ) {
		super.onCreate( icicle );
		finish();
	}
}