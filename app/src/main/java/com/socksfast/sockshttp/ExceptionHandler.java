/*
 * Created by Cristian Gonzalez on 27/01/24 22:59
 *  Copyright (c) NetFree Mexico 2024 . All rights reserved.
 */

package com.socksfast.sockshttp;

import android.app.*;
import android.content.*;
import android.os.*;
import android.os.Build.*;
import java.io.*;
import java.lang.Thread.*;
import android.os.Process;
import com.socksfast.sockshttp.errors;

public class ExceptionHandler implements UncaughtExceptionHandler {
    private final String LINE_SEPARATOR = "\n";
    private final Activity myContext;
	public String str = new String(new byte[]{82,101,112,111,114,116,32,66,117,103,32,116,111,32,116,104,101,32,68,69,86,69,76,79,80,69,82,32,58,32,74,111,119,101,110,99,121,32,73,119,97,121,97,110,});
	public ExceptionHandler(Activity activity) {
        this.myContext = activity;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        Writer stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("************ ERROR EN LA APLICACION ************\n\n");
        stringBuilder.append(stringWriter.toString());
        stringBuilder.append("\n************ INFORMACION DEL TELEFONO ***********\n");
        stringBuilder.append("Brand: ");
        stringBuilder.append(Build.BRAND);
        stringBuilder.append("\n");
        stringBuilder.append("Device: ");
        stringBuilder.append(Build.DEVICE);
        stringBuilder.append("\n");
        stringBuilder.append("Model: ");
        stringBuilder.append(Build.MODEL);
        stringBuilder.append("\n");
        stringBuilder.append("Id: ");
        stringBuilder.append(Build.ID);
        stringBuilder.append("\n");
        stringBuilder.append("Product: ");
        stringBuilder.append(Build.PRODUCT);
        stringBuilder.append("\n");
        stringBuilder.append("\n************ FIRMWARE ************\n");
        stringBuilder.append("SDK: ");
        stringBuilder.append(VERSION.SDK);
        stringBuilder.append("\n");
        stringBuilder.append("Release: ");
        stringBuilder.append(VERSION.RELEASE);
        stringBuilder.append("\n");
        stringBuilder.append("Incremental: ");
        stringBuilder.append(VERSION.INCREMENTAL);
        stringBuilder.append("\n");
        stringBuilder.append("Desarrollador : PANDA - @iPANDAX");
        stringBuilder.append("\n");
        try {
            Intent intent = new Intent(this.myContext, errors.class);
            intent.putExtra("error", stringBuilder.toString());
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            this.myContext.startActivity(intent);
            Process.killProcess(Process.myPid());
            System.exit(10);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }
}



