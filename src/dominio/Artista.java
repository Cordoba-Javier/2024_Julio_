package dominio;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Objects;

public class Artista {
	
	private String nombre;
	private GregorianCalendar fechaNacto;
	private GregorianCalendar fechaMuerte;
	private Sexo sexo;
	
	public Artista(String nombre, GregorianCalendar fechaNacto, Sexo sexo) throws ExceptionArtista {
		Calendar cal = GregorianCalendar.getInstance();
		if (fechaNacto.get(Calendar.MONTH)>cal.get(Calendar.MONTH)) {
		if((cal.getWeekYear()-fechaNacto.getWeekYear())<=18)
			throw new ExceptionArtista("No se permiten artistas menores (17)");
		}
		this.nombre=nombre.trim().toLowerCase();
		this.fechaNacto=fechaNacto;
		this.sexo=sexo;
		this.fechaMuerte=null;
	}
	
	
	
	public String getNombre() {
		String[] tmp=nombre.split(" ");
		String aux="";
		for (String s : tmp) {
			if(s.length()!=0)
				aux+=s.substring(0,1).toUpperCase()+s.substring(1)+" ";
		}
		return aux.trim();
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public GregorianCalendar getFechaNacto() {
		return fechaNacto;
	}


	public void setFechaNacto(GregorianCalendar fechaNacto) {
		this.fechaNacto = fechaNacto;
	}


	public Sexo getSexo() {
		return sexo;
	}


	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	

	public String getFechaCorta() {
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yy");
		
		return sdf.format(fechaNacto.getTime()); 
	}
	
	public int getEdad() {
		Calendar cal = GregorianCalendar.getInstance();
		
		if (fechaNacto.get(Calendar.MONTH)>cal.get(Calendar.MONTH))
			return cal.getWeekYear()-fechaNacto.getWeekYear()-1;
		return cal.getWeekYear()-fechaNacto.getWeekYear();
	}
	
	public void setFechaMuerte(GregorianCalendar fechaMuerte) {
		this.fechaMuerte=fechaMuerte;
	}
	
	public GregorianCalendar getFechaMuerte() {
		return fechaMuerte;
	}
	public String getFechaMuerteCorta() {
		if(fechaMuerte==null)
			return"Still Alive!";
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yy");
		return sdf.format(fechaMuerte.getTime());
	}


	@Override
	public int hashCode() {
		return Objects.hash(fechaNacto, nombre, sexo);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artista other = (Artista) obj;
		return Objects.equals(fechaNacto, other.fechaNacto) && Objects.equals(nombre, other.nombre)
				&& sexo == other.sexo;
	}
	public String getArtis() {
		if(sexo==Sexo.Masculino)
			return "El Artista: ";
		return"La Artista: ";
	}



	@Override
	public String toString() {
		return getArtis()+getNombre() +" ("+ getFechaCorta()+")";
	}
	
	
		
}

