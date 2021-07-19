import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import javax.swing.JOptionPane;

public class Ejercicio4 {

	final static double VAT = 1.21;

	public static void main(String[] args) {

		// introduzco variables,
		String option;
		Hashtable<String, Double> listOfProductStock = createListOfProductStock();
		Hashtable<String, Double> productPriceList = createProductPriceList();
		Hashtable<String, Double> shoppingCart = new Hashtable<String, Double>();
		ArrayList<Hashtable<String, Double>> shoppingList = new ArrayList<Hashtable<String, Double>>();

		// inicializo el menu,
		option = showMenu();

		// while
		try {

			// mientras usuario ne elige botón 5 continuo
			// ejecutar el programa,
			while (!option.equalsIgnoreCase("0")) {
				runMenu(option, listOfProductStock, productPriceList, shoppingCart, shoppingList);
				option = showMenu();
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Las compras han sido acabadas");
		}
	}

	// para mostrar menu,
	public static String showMenu() {

		return JOptionPane.showInputDialog("Elige una de las opciones: " + "\n1. Lista productos/precio"
				+ "\n2. Lista productos/cantidad" + "\n3. Buscar un producto" + "\n4. Añadir un producto a la cesta"
				+ "\n5. Mostrar cesta " + "\n6. Acabar la compra" + "\n7. Mostrar compras"
				+ "\n8. Actualizar lista de productos" + "\n0. Acadar compras");
	}

	// para ejecutar el programa,
	public static void runMenu(String option, Hashtable<String, Double> listOfProductStock,
			Hashtable<String, Double> productPriceList, Hashtable<String, Double> shoppingCart,
			ArrayList<Hashtable<String, Double>> shoppingList) {

		// según la opción elegida ejecutamos el método en cuestión,
		switch (option) {

		// mostrar la lista de precios,
		case "1":

			// showProducts(productPriceList, "precio");
			System.out.println("Los precios: " + productPriceList.toString());

			break;

		// mostrar el stock de productos,
		case "2":
			// showProducts(listOfProductStock, "cantidad");
			System.out.println("El stock de los productos: " + listOfProductStock.toString());

			break;

		// buscar producto por el nombre,
		case "3":
			String product = JOptionPane.showInputDialog("Introduzca el nombre de un producto a consultar");

			showProduct(productPriceList, product, "precio");
			showProduct(listOfProductStock, product, "cantidad");

			break;

		// añadir productos al carrito,
		case "4":

			String productName = JOptionPane.showInputDialog("Indica el nombre de un producto");
			double productAmount = Double.parseDouble(JOptionPane.showInputDialog("Indica la cantidad del producto"));

			shoppingCart = addProductAmountToShoppingCart(shoppingCart, listOfProductStock, productName, productAmount);

			break;

		// mostrar el contenido de cesta,
		case "5":
			showProducts(shoppingCart, "cantidad");
			System.out.println("Precio final: " + calculatePriceTotal(productPriceList, shoppingCart)
					+ ". La cantidad de los productos: " + calculateAmountTotal(shoppingCart));

			break;
		// finalizar compra,
		case "6":
			// pedimos IVA,
			String iva = JOptionPane.showInputDialog("Indica la IVA: " + "\n1 - 21%" + "\n2 - 4%");
			double vat = VAT;

			if (iva.equalsIgnoreCase("2")) {
				vat = 1.04;
			}

			// pedimos el importe,
			double money = Double.parseDouble(JOptionPane.showInputDialog("Introduce el importe"));

			// finalizamos compra creando y mostrando el ticket,
			Hashtable<String, Double> compra = createBuy(vat, calculatePriceTotal(productPriceList, shoppingCart),
					calculateAmountTotal(shoppingCart), money);

			System.out.println("El resultado de compra: " + compra.toString());

			// añadimos compra a la lista de compras,
			shoppingList = createListOfBuys(shoppingList, compra);

			// vaciamos el carrito,
			shoppingCart.clear();
			break;

		// para mostrar lista de compras,
		case "7":
			if (shoppingList.size() > 0) {
				showBuys(shoppingList);
			}else {
				JOptionPane.showMessageDialog(null, "Lista de compras todavía está vacia");
			}
			break;

			// para actualizar lista de productos, su precio y cantidad,
		case "8":

			String button = JOptionPane.showInputDialog(null, "Actualizar producto:" + "\n1. Cantidad" + "\n2. Precio");
			String newProduct = JOptionPane.showInputDialog("Indica el nombre de un producto");

			//actualizamos la cantidad,
			if (button.equalsIgnoreCase("1")) {

				double amount = Double.parseDouble(JOptionPane.showInputDialog("Indica la cantidad del producto"));

				listOfProductStock = actualizeStock(listOfProductStock, newProduct, amount, "+");
				System.out.println(listOfProductStock.toString());
			}

			//actualizamos el precio,
			else if (button.equalsIgnoreCase("2")) {

				double price = Double.parseDouble(JOptionPane.showInputDialog("Indica el precio nuevo del producto"));

				productPriceList = addProductPrice(productPriceList, newProduct, price);
				
				System.out.println(productPriceList.toString());
			}
			break;

		default:
			break;
		}
	}

	// método para crear un diccionario de datos,
	// articulo:precio
	public static Hashtable<String, Double> createProductPriceList() {

		Hashtable<String, Double> products = new Hashtable<String, Double>();

		products.put("potato", 1.0);
		products.put("milk", 2.0);
		products.put("bread", 3.0);
		products.put("butter", 0.5);
		products.put("beer", 0.5);
		products.put("apple", 1.0);
		products.put("salmon", 7.0);
		products.put("water", 0.10);
		products.put("oatmeal", 0.5);
		products.put("vine", 6.0);

		return products;
	}

	// método para crear un diccionario de datos,
	// articulo:cantidad,
	public static Hashtable<String, Double> createListOfProductStock() {

		Hashtable<String, Double> products = new Hashtable<String, Double>();

		products.put("potato", 1.0);
		products.put("milk", 10.0);
		products.put("bread", 5.0);
		products.put("butter", 20.0);
		products.put("beer", 5.0);
		products.put("apple", 20.0);
		products.put("salmon", 7.0);
		products.put("water", 30.0);
		products.put("oatmeal", 1.0);
		products.put("vine", 2.0);

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
			System.out.println("El producto no está en la lista de " + type);
		}
	}

	// para añadir un producto a la lista de las compras,
	public static Hashtable<String, Double> addProductAmountToShoppingCart(Hashtable<String, Double> buysList,
			Hashtable<String, Double> listOfProductStock, String name, double amount) {

		if (listOfProductStock.get(name) >= amount) {

			// comprobación si el carrito ya contiene el producto,
			if (buysList.containsKey(name)) {

				buysList.put(name, (buysList.get(name) + amount));

				JOptionPane.showMessageDialog(null, "La cantidad de " + name + " ha sido actualizada");
			} else {
				buysList.put(name, amount);

				JOptionPane.showMessageDialog(null, "El producto " + name + " ha sido añadido al carrito de compras");
			}

			listOfProductStock = actualizeStock(listOfProductStock, name, amount, "-");
		}

		else {
			JOptionPane.showMessageDialog(null,
					"La cantidad disponible de " + name + " es " + listOfProductStock.get(name));
		}

		return buysList;
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

	// para actualizar el stock del producto o sea añadir o restar,
	public static Hashtable<String, Double> actualizeStock(Hashtable<String, Double> listOfProductStock, String name,
			double amount, String action) {

		//comprobamos si ya existe el producto en la lista,
		if (listOfProductStock.containsKey(name)) {
			
			double stock = listOfProductStock.get(name);
			
			if (action.equalsIgnoreCase("+")) {
				listOfProductStock.put(name, stock + amount);
			} else {
				listOfProductStock.put(name, stock - amount);
			}
		} 
		// si no existe lo creamos de nuevo,
		else {
			listOfProductStock.put(name, amount);
			
			JOptionPane.showMessageDialog(null, "El producto " + name + " ha sido añadido a la lista");
		}

		return listOfProductStock;
	}

	// para calcular el precio total de la lista de compras,
	public static double calculatePriceTotal(Hashtable<String, Double> productsPrice,
			Hashtable<String, Double> productsAmount) {
		double priceTotal = 0;

		Enumeration<String> article = productsAmount.keys();

		while (article.hasMoreElements()) {
			String element = article.nextElement();

			priceTotal = priceTotal + (productsAmount.get(element) * productsPrice.get(element));

		}

		return priceTotal;
	}

	// para calcular la cantitad de productos en la lista de compras,
	public static double calculateAmountTotal(Hashtable<String, Double> productsAmount) {
		double amountTotal = 0;

		Enumeration<Double> value = productsAmount.elements();

		while (value.hasMoreElements()) {
			Double element = value.nextElement();
			// System.out.println(element);
			amountTotal = amountTotal + element;
		}

		return amountTotal;
	}

	// método para crear una compra que es un Hashtable con datos necesarios,
	// introducido por parametros,
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

	// método para añadir una compra a la lista de compras,
	public static ArrayList<Hashtable<String, Double>> createListOfBuys(
			ArrayList<Hashtable<String, Double>> shoppingList, Hashtable<String, Double> buy) {
		shoppingList.add(buy);

		return shoppingList;
	}

	// método que muestra los datos de una compra,
	public static void showBuy(Hashtable<String, Double> buy) {
		Enumeration<String> name = buy.keys();
		Enumeration<Double> valor = buy.elements();

		while (name.hasMoreElements()) {

			System.out.print(name.nextElement() + ": ");
			System.out.printf("%.2f", valor.nextElement());
			System.out.println("");
		}
	}

	// método que muestra lista de compras con sus datos,
	public static void showBuys(ArrayList<Hashtable<String, Double>> listOfBuys) {

		Iterator<Hashtable<String, Double>> it = listOfBuys.iterator();
		// count para identificar cada compra,
		int count = 1;

		while (it.hasNext()) {
			System.out.println("Compra: " + count);
			showBuy(it.next());
			count++;
			System.out.println("");

		}

	}
}
