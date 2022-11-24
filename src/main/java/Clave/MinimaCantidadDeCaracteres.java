package Clave;

import Clave.ValidacionClave;

public class MinimaCantidadDeCaracteres implements ValidacionClave {

	private static Integer CANTIDAD_MINIMA_DE_CARACTERES = 8;

	public Boolean esValida(String clave) {
		return null != clave && "" != clave &&  clave.length() > CANTIDAD_MINIMA_DE_CARACTERES;
	}
}
