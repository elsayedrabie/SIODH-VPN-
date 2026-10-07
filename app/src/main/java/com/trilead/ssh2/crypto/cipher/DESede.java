
/*
 * Created by Cristian Gonzalez on 27/01/24 22:59
 *  Copyright (c) NetFree Mexico 2024 . All rights reserved.
 */

package com.trilead.ssh2.crypto.cipher;

/**
 * DESede.
 * 
 * @author See comments in the source file
 * @version $Id: DESede.java,v 1.1 2007/10/15 12:49:55 cplattne Exp $
 * 
 */
public class DESede extends DES
{
	private int[] key1 = null;
	private int[] key2 = null;
	private int[] key3 = null;

	private boolean encrypt;

	/**
	 * standard constructor.
	 */
	public DESede()
	{
	}

	/**
	 * initialise a DES cipher.
	 * 
	 * @param encrypting
	 *            whether or not we are for encryption.
	 * @param key
	 *            the parameters required to set up the cipher.
	 * @exception IllegalArgumentException
	 *                if the params argument is inappropriate.
	 */
	public void init(boolean encrypting, byte[] key)
	{
		key1 = generateWorkingKey(encrypting, key, 0);
		key2 = generateWorkingKey(!encrypting, key, 8);
		key3 = generateWorkingKey(encrypting, key, 16);

		encrypt = encrypting;
	}

	public String getAlgorithmName()
	{
		return "DESede";
	}

	public int getBlockSize()
	{
		return 8;
	}

	public void transformBlock(byte[] in, int inOff, byte[] out, int outOff)
	{
		if (key1 == null)
		{
			throw new IllegalStateException("DESede engine not initialised!");
		}

		if (encrypt)
		{
			desFunc(key1, in, inOff, out, outOff);
			desFunc(key2, out, outOff, out, outOff);
			desFunc(key3, out, outOff, out, outOff);
		}
		else
		{
			desFunc(key3, in, inOff, out, outOff);
			desFunc(key2, out, outOff, out, outOff);
			desFunc(key1, out, outOff, out, outOff);
		}
	}

	public void reset()
	{
	}
}
