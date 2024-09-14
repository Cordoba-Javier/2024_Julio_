package dominio;

import Interfaz.ListaArtista;

import java.util.ArrayList;

public class GestorArtista implements ListaArtista {

	private static GestorArtista ga;
	public static ArrayList<Artista> misArtistas;
	private GestorArtista() {
		misArtistas = new ArrayList<Artista>();
	}


	public static GestorArtista getInstancia() {
		if (ga == null)
			ga = new GestorArtista();
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

	@Override
	public ArrayList<Artista> getListaArtista() {
		return misArtistas;
	}
}





	

	

