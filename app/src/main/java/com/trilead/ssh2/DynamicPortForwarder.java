/*
 * Created by Cristian Gonzalez on 27/01/24 22:59
 *  Copyright (c) NetFree Mexico 2024 . All rights reserved.
 */

package com.trilead.ssh2;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.trilead.ssh2.channel.ChannelManager;
import com.trilead.ssh2.channel.DynamicAcceptThread;

/**
 * A <code>DynamicPortForwarder</code> forwards TCP/IP connections to a local
 * port via the secure tunnel to another host which is selected via the SOCKS
 * protocol. Checkout {@link Connection#createDynamicPortForwarder(int)} on how
 * to create one.
 * 
 * @author Kenny Root
 * @version $Id: $
 */
public class DynamicPortForwarder {
	ChannelManager cm;

	DynamicAcceptThread dat;

	DynamicPortForwarder(ChannelManager cm, InetSocketAddress addr, int maxThreads)
			throws IOException {
		this.cm = cm;

		dat = new DynamicAcceptThread(cm, addr, maxThreads);
		dat.setDaemon(true);
		dat.start();
	}

	DynamicPortForwarder(ChannelManager cm, int local_port, int maxThreads) throws IOException {
		this.cm = cm;

		dat = new DynamicAcceptThread(cm, local_port, maxThreads);
		dat.setDaemon(true);
		dat.start();
	}

	/**
	 * Stop TCP/IP forwarding of newly arriving connections.
	 * 
	 * @throws IOException
	 */
	public void close() throws IOException {
		dat.stopWorking();
	}
}
