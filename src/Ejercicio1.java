
import java.util.Enumeration;
import java.util.Hashtable;

public class Ejercicio1 {

	public static void main(String[] args) {
		
		// introduzco variables,
		// guardo notas de cada alumno en un Hashtable con llaves,
		// de nombres de asignaturas,
		Hashtable<String, Double> notasFred = new Hashtable<String, Double>();
		notasFred.put("Sistemas", 9.2);
		notasFred.put("BBDD", 8.0);
		notasFred.put("Java", 10.0);
		notasFred.put("HTML", 9.7);
		notasFred.put("Javascript", 8.9);
		notasFred.put("PHP", 9.9);
		notasFred.put("Disenyo", 10.0);
		
		//muestro notas de cada alumno,
		System.out.println("Notas de Fred Aster: ");
		showNotas(notasFred);
		System.out.println("");
		
		Hashtable<String, Double> notasAndy = new Hashtable<String, Double>();
		notasAndy.put("Sistemas", 5.0);
		notasAndy.put("BBDD", 10.0);
		notasAndy.put("Java", 9.3);
		notasAndy.put("HTML", 6.6);
		notasAndy.put("Javascript", 8.1);
		notasAndy.put("PHP", 7.2);
		notasAndy.put("Disenyo", 4.0);
		
		System.out.println("Notas de Andy Warhol: ");
		showNotas(notasAndy);
		System.out.println("");
		
		Hashtable<String, Double> notasWoody = new Hashtable<String, Double>();
		notasWoody.put("Sistemas", 7.9);
		notasWoody.put("BBDD", 5.6);
		notasWoody.put("Java", 8.7);
		notasWoody.put("HTML", 5.9);
		notasWoody.put("Javascript", 6.0);
		notasWoody.put("PHP", 7.2);
		notasWoody.put("Disenyo", 10.0);
		
		System.out.println("Notas de Woody Allen: ");
		showNotas(notasWoody);
		System.out.println("");
		
		//diccionario de datos que almacene la nota media de cada alumno,
		Hashtable<String, Double> notasMediaAlumnos = new Hashtable<String, Double>();
		notasMediaAlumnos.put("Fred Aster", notaMedia(notasFred));
		notasMediaAlumnos.put("Andy Warhol", notaMedia(notasAndy));
		notasMediaAlumnos.put("Woody Allen", notaMedia(notasWoody));
		
		//mostramos notas media de todos alumnos,
		System.out.println("Notas media: ");
		showNotas(notasMediaAlumnos);
	}

	//método para calcular la nota media a partir de datos guardados, 
	//en un Hashtable,
	public static double notaMedia(Hashtable<String, Double> notasAlumno) {
		
		Enumeration<Double> notas = notasAlumno.elements();
		double sumNotas = 0;
		
		while (notas.hasMoreElements()) {
			sumNotas += (Double)notas.nextElement();			
		}
				
		return sumNotas / notasAlumno.size();
	}
	
	
	//método para mostrar notas media de cada alumno,
	public static void showNotas(Hashtable<String, Double> notasMediaAlumnos) {
		Enumeration<String> alumnos = notasMediaAlumnos.keys();
		Enumeration<Double> notas = notasMediaAlumnos.elements();
		
		while (alumnos.hasMoreElements()) {
			
			System.out.print(alumnos.nextElement() + ": "); 
			System.out.printf("%.2f", notas.nextElement()); 
			System.out.println("");
		}
	}
}
