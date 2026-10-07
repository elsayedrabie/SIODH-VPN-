/*
 * Created by Cristian Gonzalez on 27/01/24 22:59
 *  Copyright (c) NetFree Mexico 2024 . All rights reserved.
 */

package com.socksfast.ultrasshservice.config;

import java.util.UUID;

/**
 * Created by arne on 15.12.16.
 */

public class PasswordCache {
    public static final int AUTHPASSWORD = 3;
    private static PasswordCache mInstance;
	private static UUID mDefaultUuid = UUID.randomUUID();
	
	final private UUID mUuid;
    private String mAuthPassword;

    private PasswordCache(UUID uuid) {
        mUuid = uuid;
    }

    public static PasswordCache getInstance(UUID uuid) {
        if (mInstance == null || !mInstance.mUuid.equals(uuid)) {
            mInstance = new PasswordCache(uuid);
        }
        return mInstance;
    }


    public static String getAuthPassword(UUID uuid, boolean resetPW) {
        if (uuid == null) uuid = mDefaultUuid;
		
		String pwcopy = getInstance(uuid).mAuthPassword;
        if (resetPW)
            getInstance(uuid).mAuthPassword = null;
        return pwcopy;
    }

    public static void setCachedPassword(String uuid, int type, String password) {
        if (uuid == null) uuid = mDefaultUuid.toString();
		
		PasswordCache instance = getInstance(UUID.fromString(uuid));
        switch (type) {
            case AUTHPASSWORD:
                instance.mAuthPassword = password;
                break;
        }
    }


}
