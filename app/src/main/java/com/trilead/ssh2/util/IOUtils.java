/*
 * Created by Cristian Gonzalez on 27/01/24 22:59
 *  Copyright (c) NetFree Mexico 2024 . All rights reserved.
 */

package com.trilead.ssh2.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author Kohsuke Kawaguchi
 */
public class IOUtils {
    public static void closeQuietly(Closeable c) {
        try {
            c.close();
        } catch (IOException e) {
            // ignore error
        }
    }
}
