import java.util.Enumeration;
import java.util.Hashtable;
import javax.swing.JOptionPane;


public class Ejercicio3 {

	public static void main(String[] args) {

		// introduzco variables,
		String option;
		Hashtable<String, Double> productsAmount = createAmountProducts();
		Hashtable<String, Double> productsPrice = createPriceProducts();

		// inicializo el menu,
		option = showMenu();

		// while
		try {

			// mientras usuario ne elige botón 5 continuo
			// ejecutar el programa,
			while (!option.equalsIgnoreCase("5")) {
				runMenu(option, productsAmount, productsPrice);
				option = showMenu();
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "El programa ha sido acabada");
		}
	}

	// para mostrar menu,
	public static String showMenu() {

		return JOptionPane.showInputDialog(
				"Elige una de las opciones: " + "\n1. Lista productos/precio" + "\n2. Lista productos/cantidad"
						+ "\n3. Añadir un producto" + "\n4. Buscar un producto" + "\n5. Acadar el programa");
	}

	// para ejecutar programa,
	public static void runMenu(String option, Hashtable<String, Double> productsAmount,
			Hashtable<String, Double> productsPrice) {

		// según la opción elegida ejecutamos el método en cuestión,
		switch (option) {

		// mostrar lista de precios,
		case "1":

			showProducts(productsPrice, "precio");

			break;

		// mostrar lista de cantidades,
		case "2":
			showProducts(productsAmount, "cantidad");

			break;

		// añadir producto a las listas,
		// o actualizar uno existente,
		case "3":
			String productName = JOptionPane.showInputDialog("Indica el nombre de un producto");
			double productAmount = Double.parseDouble(JOptionPane.showInputDialog("Indica la cantidad del producto"));
			double productPrice = Double.parseDouble(JOptionPane.showInputDialog("Indica el precio del producto"));

			productsAmount = addProductAmount(productsAmount, productName, productAmount);
			productsPrice = addProductPrice(productsPrice, productName, productPrice);

			break;

		// buscar producto por el nombre,
		case "4":
			String product = JOptionPane.showInputDialog("Introduzca el nombre de un producto a consultar");

			showProduct(productsPrice, product, "precio");
			showProduct(productsAmount, product, "cantidad");

			break;
		// para el caso de botón Cancel,
		case "":
		default:
			break;
		}
	}

	// método para crear un diccionario de datos,
	// articulo:precio
	public static Hashtable<String, Double> createPriceProducts() {

		Hashtable<String, Double> products = new Hashtable<String, Double>();

		products.put("potato", 2.99);
		products.put("milk", 1.0);
		products.put("bread", 0.55);
		products.put("butter", 1.25);
		products.put("beer", 0.75);
		products.put("apple", 1.52);
		products.put("salmon", 7.85);
		products.put("water", 0.60);
		products.put("oatmeal", 0.83);
		products.put("vine", 6.40);

		return products;
	}

	// método para crear un diccionario de datos,
	// articulo:cantidad,
	public static Hashtable<String, Double> createAmountProducts() {

		Hashtable<String, Double> products = new Hashtable<String, Double>();

		products.put("potato", 1.0);
		products.put("milk", 10.0);
		products.put("bread", 5.0);
		products.put("butter", 100.);
		products.put("beer", 50.0);
		products.put("apple", 20.0);
		products.put("salmon", 7.0);
		products.put("water", 200.0);
		products.put("oatmeal", 6.0);
		products.put("vine", 10.0);

		return products;
	}

	// método que muestra los datos de una lista,
	public static void showProducts(Hashtable<String, Double> products, String type) {
		Enumeration<String> article = products.keys();
		Enumeration<Double> value = products.elements();

		while (article.hasMoreElements()) {

			System.out.print(article.nextElement() + ". " + type + ": ");
			System.out.printf("%.2f", value.nextElement());
			System.out.println(" ");
		}
		System.out.println(" ");
	}

	// método que muestra datos de un producto concreto,
	public static void showProduct(Hashtable<String, Double> products, String article, String type) {

		if (products.containsKey(article)) {
			System.out.println(article + ": " + products.get(article));
			JOptionPane.showMessageDialog(null, article + " " + type + ": " + products.get(article));
		} else {
			System.out.println("El producto no aparece en la lista de " + type);
		}
	}

	// para añadir un producto a la lista de las candidades,
	public static Hashtable<String, Double> addProductAmount(Hashtable<String, Double> productsAmount, String name,
			double amount) {

		if (productsAmount.containsKey(name)) {

			productsAmount.put(name, (productsAmount.get(name) + amount));

			JOptionPane.showMessageDialog(null, "La cantidad de " + name + " ha sido actualizada");
		} else {
			productsAmount.put(name, amount);

			JOptionPane.showMessageDialog(null, "El producto " + name + " ha sido añadido a la lista de cantidad");
		}

		return productsAmount;
	}

	// para añadir un producto a la lista de los precios,
	public static Hashtable<String, Double> addProductPrice(Hashtable<String, Double> productsPrice, String name,
			double price) {

		if (productsPrice.containsKey(name)) {

			JOptionPane.showMessageDialog(null, "El precio " + name + " ha sido actualizado");

		} else {
			JOptionPane.showMessageDialog(null,
					"El precio del producto " + name + " ha sido añadido a la lista de precios");
		}

		productsPrice.put(name, price);

		return productsPrice;
	}
}
