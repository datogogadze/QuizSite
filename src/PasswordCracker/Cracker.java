package PasswordCracker;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cracker {
	/*
	 Given a byte[] array, produces a hex String,
	 such as "234a6f". with 2 chars for each byte in the array.
	 (provided code)
	*/
	public static String hexToString(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (int i=0; i<bytes.length; i++) {
			int val = bytes[i];
			val = val & 0xff;  // remove higher bits, sign
			if (val<16) buff.append('0'); // leading 0
			buff.append(Integer.toString(val, 16));
		}
		return buff.toString();
	}
	
	public static String generateHash(String password) {
		String result = null;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA");
			md.update(password.getBytes());
			result = hexToString(md.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}
	

}
