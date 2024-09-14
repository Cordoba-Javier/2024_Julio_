package dominio;

import Interfaz.UserArtista;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Objects;

public class Artista{

	private String nombre;
	private GregorianCalendar fechaNacto;
	private GregorianCalendar fechaMuerte;
	private Sexo sexo;

	private Artista(String nombre, GregorianCalendar fechaNacto, Sexo sexo) {
		nombre.trim().toLowerCase();
		nombre=getNombre();
		this.nombre = nombre;
		this.fechaNacto = fechaNacto;
		this.sexo = sexo;
		this.fechaMuerte = null;
	}

	public static Artista InstanciaArtista(String nombre, GregorianCalendar fechaNacto, Sexo sexo) {
		if nombre==null
				return throw ExceptionArtista("Nombre Null");
		if fechaNacto == null
				return throw ExceptionArtista("Fecha Null");
		if sexo==null
				return throw ExceptionArtista("Sexo Null");

		return new Artista(nombre, fechaNacto, sexo);
	}

	public String getNombre() {
		String[] tmp = nombre.split(" ");
		String aux = "";
		for (String s : tmp) {
			if (s.length() != 0)
				aux += s.substring(0, 1).toUpperCase() + s.substring(1) + " ";
		}
		return aux.trim();
	}


	public String getFechaCorta() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");

		return sdf.format(fechaNacto.getTime());
	}

	public int getEdad() {
		Calendar cal = GregorianCalendar.getInstance();

		if (fechaNacto.get(Calendar.MONTH) > cal.get(Calendar.MONTH))
			return cal.getWeekYear() - fechaNacto.getWeekYear() - 1;
		return cal.getWeekYear() - fechaNacto.getWeekYear();
	}

	public String getFechaMuerteCorta() {
		if (fechaMuerte == null)
			return "Still Alive!";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		return sdf.format(fechaMuerte.getTime());
	}

	public String getArtis() {
		if (sexo == Sexo.Masculino)
			return "El Artista: ";
		return "La Artista: ";
	}
}





