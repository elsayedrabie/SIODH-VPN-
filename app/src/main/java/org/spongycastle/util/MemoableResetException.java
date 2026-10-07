/*
 * Created by Cristian Gonzalez on 27/01/24 22:59
 *  Copyright (c) NetFree Mexico 2024 . All rights reserved.
 */

package org.spongycastle.util;

/**
 * Exception to be thrown on a failure to reset an object implementing Memoable.
 * <p>
 * The exception extends ClassCastException to enable users to have a single handling case,
 * only introducing specific handling of this one if required.
 * </p>
 */
public class MemoableResetException
    extends ClassCastException
{
    /**
     * Basic Constructor.
     *
     * @param msg message to be associated with this exception.
     */
    public MemoableResetException(String msg)
    {
        super(msg);
    }
}
