/*
 * Created by Cristian Gonzalez on 27/01/24 22:59
 *  Copyright (c) NetFree Mexico 2024 . All rights reserved.
 */

package com.socksfast.ultrasshservice.vpn;

import android.app.Service;

public interface V2Listener {
    boolean onProtect(final int socket);
    Service getService();
    void startService();
    void stopService();
    void onConnected();
    void onError();

}
