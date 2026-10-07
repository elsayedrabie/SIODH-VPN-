/*
 * Created by Cristian Gonzalez on 27/01/24 22:59
 *  Copyright (c) NetFree Mexico 2024 . All rights reserved.
 */

package com.socksfast.ultrasshservice.util.securepreferences.crypto;

public interface CipherService {

    int getIVSize();
    byte[] encrypt(byte[] key, byte[] iv, byte[] data);
    byte[] decrypt(byte[] key, byte[] iv, byte[] data);

}
