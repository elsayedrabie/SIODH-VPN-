/*
 * Created by Cristian Gonzalez on 27/01/24 22:59
 *  Copyright (c) NetFree Mexico 2024 . All rights reserved.
 */

package com.socksfast.ultrasshservice.tunnel;

public interface UDPListener {
    void onConnecting();
    void onConnected();
    void onNetworkLost();
    void onAuthFailed();
    void onReconnecting();
    void onConnectionLost();
    void onError();
    void onDisconnected();
}
