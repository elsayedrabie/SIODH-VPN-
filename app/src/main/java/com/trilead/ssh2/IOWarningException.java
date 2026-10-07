/*
 * Created by Cristian Gonzalez on 27/01/24 22:59
 *  Copyright (c) NetFree Mexico 2024 . All rights reserved.
 */

package com.trilead.ssh2;

import java.io.IOException;

/**
 * @author Thomas Singer
 */
public final class IOWarningException extends IOException {

    private static final long serialVersionUID = 1L;
    
	// Setup ==================================================================

    public IOWarningException(String message) {
		super(message);
	}
}
