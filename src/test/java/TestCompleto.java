package dominio;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestCompleto {

	@Test
	@Order(1)
	@DisplayName("01 Basico Artista")
	void testBasicoArtirta() {
		try {
			//Datos: Julio Iglesias	23/09/43 (80 años)-Masculino
			GregorianCalendar f1 = new GregorianCalendar();
			f1.set(1943, 8, 23);
			Artista a = new Artista (" julio   iglesias",f1,Sexo.Masculino);
			
			assertEquals("Julio Iglesias", a.getNombre());
			assertEquals("23/09/43", a.getFechaCorta());
			
			//Datos: Celine M. C. Dion	30/03/68 (56 años)-Femenino
			GregorianCalendar f2 = new GregorianCalendar();
			f2.set(1968, 2, 30);
			Artista a2 = new Artista ("   Celine   m.    C.  DION    ",f2,Sexo.Femenino);
			
			assertEquals("Celine M. C. Dion", a2.getNombre());
			assertEquals("30/03/68", a2.getFechaCorta());
		}catch (ExceptionArtista e) {
			fail("Esta linea no deberia correrse");
		}
	}
	
	@Test
	@Order(2)
	@DisplayName("02 Edad y toString  Artista")
	void testEdadYToStirngAstirta() {
		try {
			//Datos: Julio Iglesias	23/09/43 (80 años)-Masculino
			GregorianCalendar f = new GregorianCalendar();
			f.set(1943, 8, 23);
			Artista a = new Artista (" julio    iglesias",f,Sexo.Masculino);
			
			assertEquals(80,a.getEdad());
			assertEquals ("El Artista: Julio Iglesias (23/09/43)", a.toString());
			
			//Datos: Celine M. C. Dion	30/03/68 (56 años)-Femenino
			f.set(1968, 2, 30);
			Artista a2 = new Artista ("   Celine   m.    C.  DION    ",f,Sexo.Femenino);
			
			assertEquals(56,a2.getEdad());
			assertEquals ("La Artista: Celine M. C. Dion (30/03/68)", a2.toString());
		}catch(ExceptionArtista e) {
			fail("Esta linea no deberia correrse");
		}
	}
	
	@Test
	@Order(3)
	@DisplayName("03 Gestor Artista Add")
	void testAddArtistaAGestor() {
		try {
//			Datos:
//			Julio Iglesias			23/09/43 (80 años)-Masculino
//			Celine M. C. Dion		30/03/68 (56 años)-Femenino
//			Madonna L. V. Ciccone	16/08/58 (63 años)-Femenino
			GregorianCalendar f1 = new GregorianCalendar();
			f1.set(1943, 8, 23);
			Artista a1 = new Artista (" julio    iglesias",f1,Sexo.Masculino);
			
			GregorianCalendar f2 = new GregorianCalendar();
			f2.set(1968, 2, 30);
			Artista a2 = new Artista ("   Celine   m.    C.  DION    ",f2,Sexo.Femenino);
			
			GregorianCalendar f3 = new GregorianCalendar();
			f3.set(1958, 7, 16);
			Artista a3 = new Artista ("Madonna l. v. Ciccone",f3,Sexo.Femenino);
			
			GregorianCalendar f4 = new GregorianCalendar();
			f4.set(1968, 2, 30);
			Artista a4 = new Artista ("   Celine   m.    C.  DION    ",f4,Sexo.Femenino);
			
			GregorianCalendar f5 = new GregorianCalendar();
			f5.set(1958, 7, 16);
			Artista a5 = new Artista ("Madonna l. v. Ciccone",f5,Sexo.Femenino);
			
			GestorArtista ga = GestorArtista.getInstancia();
			
			assertEquals(0, ga.cantidadDeArtistas());	//Antes de cargar No hay ningun artista
			assertEquals(true,ga.addArtista(a1));		//No hay problemas de carga
			assertEquals(true,ga.addArtista(a2));		//No hay problemas de carga
			assertEquals(true,ga.addArtista(a3));		//No hay problemas de carga
			assertEquals(3, ga.cantidadDeArtistas());	//Ya ban tres cargados
			
			assertEquals(false,ga.addArtista(a4));		//No carga duplicados
			assertEquals(false,ga.addArtista(a5));		//No carga duplicados
			assertEquals(3, ga.cantidadDeArtistas());	//Sigue habiendo tres
		}catch(ExceptionArtista e) {
			fail("Esta linea no deberia correrse");
		}
	}
	
	@Test
	@Order(4)
	@DisplayName("04 Buscar Artistas con lambdas parte Nombre Orden Nombre")
	void testBuscarArtistaConLambdas01() {
//		Datos:
//		Luis Paez	23/09/90 (33 años)-Masculino
//		Luis Miguel	30/03/90 (34 años)-Masculino
//		Laura		16/08/88 (35 años)-Femenino
//		Lucia		16/08/87 (36 años)-Femenino
		GestorArtista ga = GestorArtista.getInstancia();
		ga.blanquearArtistas();
		ga=CargarArtistasParaTest4y5(ga);
		
		ArrayList<Artista> aux = ga.getArtistasImplementarConLambda("Lu");
		
		assertEquals(3, aux.size());	
		assertEquals("Lucia", aux.get(0).getNombre());	
		assertEquals("Luis Miguel",aux.get(1).getNombre());		
		assertEquals("Luis Paez",aux.get(2).getNombre());
		
	}

	/**
	 * Este no es un test.
	 * Es un método complementario necesario para cargar los artistas para el test 4 y 5.
	 * 
	 */
	private GestorArtista CargarArtistasParaTest4y5(GestorArtista ga) {
		try {
			GregorianCalendar f1 = new GregorianCalendar();
			f1.set(1990, 8, 23);
			Artista a1 = new Artista ("luis Paez",f1,Sexo.Masculino);
			
			GregorianCalendar f2 = new GregorianCalendar();
			f2.set(1990, 2, 30);
			Artista a2 = new Artista ("luis miguel",f2,Sexo.Masculino);
			
			GregorianCalendar f3 = new GregorianCalendar();
			f3.set(1988, 7, 16);
			Artista a3 = new Artista ("Laura ",f3,Sexo.Femenino);
			
			GregorianCalendar f4 = new GregorianCalendar();
			f4.set(1987, 7, 16);
			Artista a4 = new Artista ("lucia",f4,Sexo.Femenino);
			
//			System.out.println(a1.getEdad());
//			System.out.println(a2.getEdad());
//			System.out.println(a3.getEdad());
//			System.out.println(a4.getEdad());
			
			ga.addArtista(a1);
			ga.addArtista(a2);
			ga.addArtista(a3);
			ga.addArtista(a4);
		}catch(ExceptionArtista e) {
			fail("Esta linea no deberia correrse");
		}
		return ga;
	}
	
	@Test
	@Order(5)
	@DisplayName("05 Buscar Artistas con lambdas edad Menor_A Orden Nombre")
	void testBuscarArtistaConLambdas02() {
//		Datos:
//		Luis Paez	23/09/90 (33 años)-Masculino
//		Luis Miguel	30/03/90 (34 años)-Masculino
//		Laura		16/08/88 (35 años)-Femenino
//		Lucia		16/08/87 (36 años)-Femenino
		GestorArtista ga = GestorArtista.getInstancia();
		ga.blanquearArtistas();
		ga=CargarArtistasParaTest4y5(ga);
		
		ArrayList<Artista> aux = ga.getArtistasImplementarConLambda(34);
		
		assertEquals(2, aux.size());	
		assertEquals("Luis Miguel",aux.get(0).getNombre());		
		assertEquals("Luis Paez",aux.get(1).getNombre());
		
	}
	
	@Test
	@Order(6)
	@DisplayName("06 Exception Artista")
	void testExceptionArtista() {
//		Datos: Luis Paez	23/09/90 (33 años)-Masculino
		GregorianCalendar f1 = new GregorianCalendar();
		f1.set(1990, 8, 23);
		try {
			Artista a1 = new Artista ("luis Paez",f1,Sexo.Masculino);
		} catch (ExceptionArtista e) {
			fail("Esta linea no deberia correrse");
		}
		
//		Datos: Luis Paez	01/07/06 (18 años)-Masculino
		GregorianCalendar f2 = new GregorianCalendar();
		f2.set(2006, 6, 1);
		try {
			Artista a2 = new Artista ("luis Paez",f2,Sexo.Masculino);
		} catch (ExceptionArtista e) {
			fail("Esta linea no deberia correrse");
		}
		
//		Datos: Luis Paez	10/07/06 (17 años)-Masculino
		GregorianCalendar f3 = new GregorianCalendar();
		f3.set(2006, 9, 10);
		try {
			Artista a3 = new Artista ("luis Paez",f3,Sexo.Masculino);
			fail("Esta linea no deberia correrse");
		} catch (ExceptionArtista e) {
			assertEquals("No se permiten artistas menores (17)", e.getMessage());
		}

//		Datos: Luis Paez	10/10/06 (17 años)-Masculino
		GregorianCalendar f4 = new GregorianCalendar();
		f4.set(2006, 9, 1);
		try {
			Artista a4 = new Artista ("luis Paez",f4,Sexo.Masculino);
			fail("Esta linea no deberia correrse");
		} catch (ExceptionArtista e) {
			assertEquals("No se permiten artistas menores (17)", e.getMessage());
		}
	}
	
	@Test
	@Order(7)
	@DisplayName("07 Buscar Artistas Orden Sexo, Edad, Nombre")
	void testBuscarArtista03() {
//		Datos:
//		Luis Paez		23/09/90 (33 años)-Masculino
//		Luis Abel		21/09/90 (33 años)-Masculino
//		Rocio			16/08/88 (35 años)-Femenino
//		Julieta			16/08/87 (36 años)-Femenino
//		Albaro Miguel	21/03/90 (34 años)-Masculino
		GestorArtista ga = GestorArtista.getInstancia();
		ga.blanquearArtistas();
		ga=CargarArtistasParaTest7y8(ga);
		
		ArrayList<Artista> aux = ga.getArtistas();
		
		assertEquals(5, aux.size());	
		assertEquals("Rocio",aux.get(0).getNombre());		
		assertEquals("Julieta",aux.get(1).getNombre());
		assertEquals("Luis Paez",aux.get(2).getNombre());		
		assertEquals("Luis Abel",aux.get(3).getNombre());
		assertEquals("Albaro Miguel",aux.get(4).getNombre());
	}

	/**
	 * Este no es un test.
	 * Es un método complementario necesario para cargar los artistas para el test 7 y 8.
	 * 
	 */
	private GestorArtista CargarArtistasParaTest7y8(GestorArtista ga) {
		try {
			GregorianCalendar f1 = new GregorianCalendar();
			f1.set(1990, 8, 23);
			Artista a1 = new Artista ("luis Paez",f1,Sexo.Masculino);
			
			GregorianCalendar f2 = new GregorianCalendar();
			f2.set(1990, 8, 21);
			Artista a2 = new Artista ("luis abel",f2,Sexo.Masculino);
			
			GregorianCalendar f3 = new GregorianCalendar();
			f3.set(1988, 7, 16);
			Artista a3 = new Artista ("Rocio ",f3,Sexo.Femenino);
			
			GregorianCalendar f4 = new GregorianCalendar();
			f4.set(1987, 7, 16);
			Artista a4 = new Artista ("Julieta",f4,Sexo.Femenino);
			
			GregorianCalendar f5 = new GregorianCalendar();
			f5.set(1990, 2, 21);
			Artista a5 = new Artista ("Albaro miguel",f5,Sexo.Masculino);
			
			
//			System.out.println(a1.getEdad());
//			System.out.println(a2.getEdad());
//			System.out.println(a3.getEdad());
//			System.out.println(a4.getEdad());
//			System.out.println(a5.getEdad());
			
			ga.addArtista(a1);
			ga.addArtista(a2);
			ga.addArtista(a3);
			ga.addArtista(a4);
			ga.addArtista(a5);
		}catch(ExceptionArtista e) {
			fail("Esta linea no deberia correrse");
		}
		return ga;
	}

	@Test
	@Order(8)
	@DisplayName("08 Primer Reporte: promedio de edad")
	void testGestorArtistaPromedioEdad() {
//		Datos:
//		Luis Paez		23/09/90 (33 años)-Masculino
//		Luis Abel		21/09/90 (33 años)-Masculino
//		Rocio			16/08/88 (35 años)-Femenino
//		Julieta			16/08/87 (36 años)-Femenino
//		Albaro Miguel	21/03/90 (34 años)-Masculino
		GestorArtista ga = GestorArtista.getInstancia();
		ga.blanquearArtistas();
		
		assertEquals("No hay artistas.", ga.getPromedioEdadArtistas());
		
		ga=CargarArtistasParaTest7y8(ga);
		
		assertEquals("Hay 5 artistas con un promedio de 34 años.", ga.getPromedioEdadArtistas());	
	}
	
	@Test
	@Order(9)
	@DisplayName("09 Segundo Reporte: promedio de edad por sexo")
	void testGestorArtistaPromedioEdadxSexo() {
		
		GestorArtista ga = GestorArtista.getInstancia();
		ga.blanquearArtistas();
		
		assertEquals("No hay artistas masculinos.", ga.getPromedioEdadArtistas(Sexo.Masculino));
		assertEquals("No hay artistas femeninos.", ga.getPromedioEdadArtistas(Sexo.Femenino));
		
		try {
//			Datos: Luis Paez	23/04/90 (34 años)-Masculino
			GregorianCalendar f1 = new GregorianCalendar();
			f1.set(1990, 3, 23);
			Artista a1 = new Artista ("luis Paez",f1,Sexo.Masculino);
			
			ga.addArtista(a1);
		}catch(ExceptionArtista e) {
			fail("Esta linea no deberia correrse");
		}
		
		assertEquals("Hay 1 artistas masculinos con un promedio de 34 años.", ga.getPromedioEdadArtistas(Sexo.Masculino));
		assertEquals("No hay artistas femeninos.", ga.getPromedioEdadArtistas(Sexo.Femenino));
		
		try {
//			Datos: Luis Abel	21/05/80 (42 años)-Masculino
			GregorianCalendar f2 = new GregorianCalendar();
			f2.set(1980, 4, 21);
			Artista a2 = new Artista ("luis abel",f2,Sexo.Masculino);
			
			ga.addArtista(a2);
		}catch(ExceptionArtista e) {
			fail("Esta linea no deberia correrse");
		}
		
		assertEquals("Hay 2 artistas masculinos con un promedio de 39 años.", ga.getPromedioEdadArtistas(Sexo.Masculino));
		assertEquals("No hay artistas femeninos.", ga.getPromedioEdadArtistas(Sexo.Femenino));
	}
	
	@Test
	@Order(10)
	@DisplayName("10 Nuevo Atributo Fecha de Muerte")
	void testFechaMuerte() {
		Artista a1=null;
		try {
//			Datos: Luis Paez	23/04/90 (34 años)-Masculino
			GregorianCalendar f1 = new GregorianCalendar();
			f1.set(1990, 3, 23);
			a1 = new Artista ("luis Paez",f1,Sexo.Masculino);
		}catch(ExceptionArtista e) {
			fail("Esta linea no deberia correrse");
		}
		
		assertNull(a1.getFechaMuerte());
		assertEquals("Still Alive!", a1.getFechaMuerteCorta());
		
		GregorianCalendar f2 = new GregorianCalendar();
		f2.set(2020, 7, 28);
		a1.setFechaMuerte(f2);
		
		assertEquals("28/08/20", a1.getFechaMuerteCorta());
		
	}
}
