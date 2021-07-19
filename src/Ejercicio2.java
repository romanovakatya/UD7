
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

public class Ejercicio2 {

	public static void main(String[] args) {

		// introduzco variables,
		//creamos compra 1,
		Hashtable<String, Double> compra1 = new Hashtable<String, Double>();
		compra1 = createBuy(1.21, 100, 3, 200);
		
		//creamos compra 2,
		Hashtable<String, Double> compra2 = new Hashtable<String, Double>();
		compra2 = createBuy(1.04, 20, 6, 50);

		//añadimos compras a una lista,
		ArrayList<Hashtable<String, Double>> listOfBuys = new ArrayList<Hashtable<String, Double>>();
		listOfBuys.add(compra1);
		listOfBuys.add(compra2);

		//mostramos todas compras,
		showBuys(listOfBuys);

	}

	//método para crear una compra que es un Hashtable con datos necesarios,
	//introducido por parametros,
	public static Hashtable<String, Double> createBuy(double vat, double grossPrice, double amount, double importe) {
		Hashtable<String, Double> compra = new Hashtable<String, Double>();

		compra.put("VAT", vat);
		compra.put("GrossPrice", grossPrice);
		compra.put("Price+VAT", (compra.get("VAT") * compra.get("GrossPrice")));
		compra.put("Amount", amount);
		compra.put("Importe", importe);
		compra.put("Change", (compra.get("Importe") - compra.get("Price+VAT")));

		return compra;
	}

	//método que muestra los datos de una compra,
	public static void showBuy(Hashtable<String, Double> buy) {
		Enumeration<String> name = buy.keys();
		Enumeration<Double> valor = buy.elements();

		while (name.hasMoreElements()) {

			System.out.print(name.nextElement() + ": ");
			System.out.printf("%.2f", valor.nextElement());
			System.out.println("");
		}
	}

	//método que muestra lista de compras con sus datos,
	public static void showBuys(ArrayList<Hashtable<String, Double>> listOfBuys) {

		Iterator<Hashtable<String, Double>> it = listOfBuys.iterator();
		//count para identificar cada compra,
		int count = 1;

		while (it.hasNext()) {
			System.out.println("Compra: " + count);
			showBuy(it.next());
			count++;
			System.out.println("");

		}

	}
}
