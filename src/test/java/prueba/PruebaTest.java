package prueba;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PruebaTest {

	APITest apiTest = new APITest();

	@Test
	void test3() {

		List<Map<String, String>> listaCerveza = apiTest.getListCervezas("lagunitas");
		List<Map<String, String>> listaCevezaFiltrado = apiTest.getListCervezasName(listaCerveza,
				"Lagunitas Brewing Co");
		List<Map<String, String>> listaDetalle = apiTest.getListCervezasState(listaCevezaFiltrado, "California");
		String nombre = "Lagunitas Brewing Co";
		String calle = "1280 N McDowell Blvd";
		String telefono = "7077694495";
		apiTest.validar(listaDetalle, nombre, calle, telefono);

	}

}
