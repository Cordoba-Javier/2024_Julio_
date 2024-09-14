package Interfaz;

import dominio.Artista;
import dominio.Sexo;

import java.util.GregorianCalendar;

public interface UserArtista {
    Artista CrearArtiista(String nombre, GregorianCalendar fechaNacto, Sexo sexo);
}
