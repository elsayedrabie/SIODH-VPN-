/*
 * Created by Cristian Gonzalez on 27/01/24 22:59
 *  Copyright (c) NetFree Mexico 2024 . All rights reserved.
 */

package com.socksfast.sockshttp.model;

import com.socksfast.sockshttp.MainActivity;
import androidx.fragment.app.Fragment;
import android.view.View;

public abstract class ViewFragment extends Fragment
implements OnUpdateLayout
{
	public void updateLayout()
	{
		updateLayout(null);
	}
}
