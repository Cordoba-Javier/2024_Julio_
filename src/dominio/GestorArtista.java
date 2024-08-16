package dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GestorArtista {
	
	private static GestorArtista ga;
	private ArrayList<Artista> misArtistas;
	
	private GestorArtista() {
		misArtistas=new ArrayList<Artista>();
	}
	
	public static GestorArtista getInstancia() {
		if(ga==null)
			ga=new GestorArtista();
		return ga;
	}
	
	public boolean addArtista(Artista a) {
		if(!artistaCargado(a.getNombre())) {
			misArtistas.add(a);
			return true;}
		return false;
	}
	
	public boolean artistaCargado(String a) {
		for (Artista artista : misArtistas) {
			if(artista.getNombre().equals(a))
				return true;
		}
		return false;
	}

	public int cantidadDeArtistas() {
		return misArtistas.size();
	}

	public void blanquearArtistas() {
		misArtistas=new ArrayList<Artista>();
	}

	public ArrayList<Artista> getArtistas(String parteNombre) {
		
		
	}
	
	public ArrayList<Artista> getArtistasImplementarConLambda(String parteNombre) {
		Predicate<Artista> p=s->s.getNombre().contains(parteNombre);
		Comparator<Artista> com = new Comparator<Artista>() {
			public int compare(Artista o,Artista p) {
				if(p.getNombre().compareTo(o.getNombre())!=0)
					return p.getEdad()-o.getEdad();
				return p.getFechaNacto().compareTo(o.getFechaNacto());
			}};
		return misArtistas.stream().filter(p).sorted(com).collect(Collectors.toCollection(ArrayList<Artista>::new));
	}
	
	public ArrayList<Artista> getArtistasImplementarConLambda(int edadLimiteSuperior) {
		Predicate<Artista> p=s->s.getEdad()<=edadLimiteSuperior;
		Comparator<Artista> com = new Comparator<Artista>() {
			public int compare(Artista o,Artista p) {
				if(p.getNombre().compareTo(o.getNombre())!=0)
					return p.getEdad()-o.getEdad();
				return p.getFechaNacto().compareTo(o.getFechaNacto());
			}};
		return misArtistas.stream().filter(p).sorted(com).collect(Collectors.toCollection(ArrayList<Artista>::new));
	}
	
	public ArrayList<Artista> getArtistas(int edadLimiteSuperior) {
	}
	
	public ArrayList<Artista> getArtistas() {
		Comparator<Artista> com = new Comparator<Artista>() {
			public int compare(Artista o,Artista p) {
				if(p.getSexo().compareTo(o.getSexo())==0)
					return 1;
				return -1;
			}};
		return misArtistas.stream().sorted(com).collect(Collectors.toCollection(ArrayList<Artista>::new));
	
	}
	
	public String getPromedioEdadArtistas(){
		double r=misArtistas.stream().mapToDouble(a->a.getEdad()).average().orElse(0);
		if(r==0)
			return "No hay artistas.";
		return "Hay "+misArtistas.size()+" artistas con un promedio de "+(int)r +" años.";
		
	}
	
	public String getPromedioEdadArtistas(Sexo s){
		Predicate<Artista> p=a->a.getSexo().equals(s);
		double r=misArtistas.stream().filter(p).mapToDouble(a->a.getEdad()).average().orElse(0);
		ArrayList<Artista>tmp= misArtistas.stream().filter(p).collect(Collectors.toCollection(ArrayList<Artista>::new));
		if(r==0) {
			if(s.equals(Sexo.Femenino))
				return "No hay artistas femeninos.";
			return "No hay artistas masculinos.";
		}
		
		if(s.equals(Sexo.Femenino))
			return "Hay "+tmp.size()+" artistas femenino con un promedio de "+(int)r+" años.";
		return "Hay "+tmp.size()+" artistas masculinos con un promedio de "+(int)r+" años.";
	
		
	}
	
}
