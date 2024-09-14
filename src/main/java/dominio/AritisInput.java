package dominio;

import Interfaz.UserArtista;

import java.util.GregorianCalendar;

public class AritisInput {
    private final UserArtista user;
    public AritisInput(UserArtista user) {
        this.user = user;

    }
    public Artista Entrada(String nombre,GregorianCalendar fechaNacto,Sexo sexo){
        return user.CrearArtiista(nombre,fechaNacto,sexo);
    }
}
