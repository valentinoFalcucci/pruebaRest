package prueba;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import io.restassured.RestAssured;

public class APITest {
	
	

	public List<Map<String, String>> getListCervezas(String parametro) {

		List<Map<String, String>> listaCerveza = new ArrayList<Map<String, String>>();
		Map<String, String> cerveza = new HashMap<String, String>();

		List<Object> lista = RestAssured.get("https://api.openbrewerydb.org/breweries/autocomplete?query=" + parametro)
				.as(List.class);

		for (int i = 0; i < lista.size(); i++) {
			cerveza = (Map<String, String>) lista.get(i);
			listaCerveza.add(cerveza);
		}

		return listaCerveza;
	}
	
	

	public List<Map<String, String>> getListCervezasName(List<Map<String, String>> listado, String parametro) {
		List<Map<String, String>> listaCevezaNueva = new ArrayList<Map<String, String>>();
		for (int i = 0; i < listado.size(); i++) {
			if (listado.get(i).get("name").contains("Lagunitas Brewing Co")) {
				listaCevezaNueva.add(listado.get(i));
			}

		}

		return listaCevezaNueva;
	}
	
	

	public List<Map<String, String>> getListCervezasState(List<Map<String, String>> listado, String parametro) {
		HashMap<String, String> objetoDetalle = new HashMap<String, String>();
		List<Map<String, String>> listaDetalle = new ArrayList<Map<String, String>>();
		for (int i = 0; i < listado.size(); i++) {
			objetoDetalle = RestAssured.get("https://api.openbrewerydb.org/breweries/" + listado.get(i).get("id"))
					.as(HashMap.class);

			if (objetoDetalle.get("state").contains(parametro)) {
				listaDetalle.add(objetoDetalle);
			}

		}

		return listaDetalle;
	}

	public void validar(List<Map<String, String>> listado, String name, String street, String phone) {
		HashMap<String, String> objetoDetalle = new HashMap<String, String>();
		List<Map<String, String>> listaDetalle = new ArrayList<Map<String, String>>();
		for (int i = 0; i < listado.size(); i++) {
			objetoDetalle = RestAssured.get("https://api.openbrewerydb.org/breweries/" + listado.get(i).get("id"))
					.as(HashMap.class);
			listaDetalle.add(objetoDetalle);

		}

		for (int i = 0; i < listaDetalle.size(); i++) {
			Assert.assertTrue(listaDetalle.get(i).get("name").equals(name), "El nombre debe ser : Brewing Co");
			Assert.assertTrue(listaDetalle.get(i).get("street").equals(street),
					"La calle debe ser 1280 N McDowell Blvd");
			Assert.assertTrue(listaDetalle.get(i).get("phone").equals(phone), "EL telefono debe ser : 7077694495");

		}

	}

}
