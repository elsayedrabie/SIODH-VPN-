/*
 * Created by Cristian Gonzalez on 27/01/24 22:59
 *  Copyright (c) NetFree Mexico 2024 . All rights reserved.
 */

package org.spongycastle.crypto;

public interface ExtendedDigest 
    extends Digest
{
    /**
     * Return the size in bytes of the internal buffer the digest applies it's compression
     * function to.
     * 
     * @return byte length of the digests internal buffer.
     */
    public int getByteLength();
}
