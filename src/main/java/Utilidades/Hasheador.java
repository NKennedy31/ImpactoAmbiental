package Utilidades;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import Clave.HashException;
import impacto_ambiental.Usuario;

public class Hasheador {

	public static String procesar(String clave) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(clave.getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for (byte aByte : bytes) {
				sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new HashException(e.getMessage());
		}
	}
	
}
