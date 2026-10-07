/*
 * Created by Cristian Gonzalez on 27/01/24 22:59
 *  Copyright (c) NetFree Mexico 2024 . All rights reserved.
 */

package com.trilead.ssh2.auth;

public interface AgentIdentity {
    public String getAlgName();
    public byte[] getPublicKeyBlob();
    public byte[] sign(byte[] data);
}
