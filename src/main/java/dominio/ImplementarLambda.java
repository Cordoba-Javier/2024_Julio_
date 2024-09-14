package dominio;

import Interfaz.ListaArtista;
import Interfaz.UserArtista;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class ImplementarLambda {
    private final ListaArtista listaArtista;
    public ImplementarLambda(ListaArtista listaArtista) {
        this.listaArtista = listaArtista;
    }

    public ArrayList<Artista> getArtistasImplementarConLambda(String parteNombre) {
        Predicate<Artista> p = s -> s.getNombre().contains(parteNombre);
        Comparator<Artista> com = new Comparator<Artista>() {
            public int compare(Artista o, Artista p) {
                if (p.getNombre().compareTo(o.getNombre()) != 0)
                    return p.getEdad() - o.getEdad();
                return p.getFechaNacto().compareTo(o.getFechaNacto());
            }
        };
        return listaArtista.getListaArtista().stream().filter(p).sorted(com).collect(Collectors.toCollection(ArrayList<Artista>::new));
    }

    public ArrayList<Artista> getArtistasImplementarConLambda(int edadLimiteSuperior) {
        Predicate<Artista> p = s -> s.getEdad() <= edadLimiteSuperior;
        Comparator<Artista> com = new Comparator<Artista>() {
            public int compare(Artista o, Artista p) {
                if (p.getNombre().compareTo(o.getNombre()) != 0)
                    return p.getEdad() - o.getEdad();
                return p.getFechaNacto().compareTo(o.getFechaNacto());
            }
        };
        return listaArtista.getListaArtista().stream().filter(p).sorted(com).collect(Collectors.toCollection(ArrayList<Artista>::new));
    }

    public ArrayList<Artista> getArtistas() {
        Comparator<Artista> com = new Comparator<Artista>() {
            public int compare(Artista o, Artista p) {
                if (p.getSexo().compareTo(o.getSexo()) == 0)
                    return 1;
                return -1;
            }
        };
        return listaArtista.getListaArtista().stream().sorted(com).collect(Collectors.toCollection(ArrayList<Artista>::new));

    }

    public String getPromedioEdadArtistas() {
        double r = listaArtista.getListaArtista().stream().mapToDouble(a -> a.getEdad()).average().orElse(0);
        if (r == 0)
            return "No hay artistas.";
        return "Hay " + listaArtista.getListaArtista().size() + " artistas con un promedio de " + (int) r + " años.";

    }

    public String getPromedioEdadArtistas(Sexo s) {
        Predicate<Artista> p = a -> a.getSexo().equals(s);
        double r = listaArtista.getListaArtista().stream().filter(p).mapToDouble(a -> a.getEdad()).average().orElse(0);
        ArrayList<Artista> tmp = listaArtista.getListaArtista().stream().filter(p).collect(Collectors.toCollection(ArrayList<Artista>::new));
        if (r == 0) {
            if (s.equals(Sexo.Femenino))
                return "No hay artistas femeninos.";
            return "No hay artistas masculinos.";
        }

        if (s.equals(Sexo.Femenino))
            return "Hay " + tmp.size() + " artistas femenino con un promedio de " + (int) r + " años.";
        return "Hay " + tmp.size() + " artistas masculinos con un promedio de " + (int) r + " años.";

    }

}
