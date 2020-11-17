package qtx.test;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import qtx.emision.EmisorTarea;

public class TestEmision {

	public static void main(String[] args) {
		
		JsonObjectBuilder builderTarea = Json.createObjectBuilder();
		builderTarea.add("baja", "persona")
		            .add("idPersona", "000750")
		            .add("fechaBaja", Json.createObjectBuilder()
		            		              .add("aa", 2020)
		            		              .add("mm", 2)
		            		              .add("dd",21));
		JsonObject tareaJson = builderTarea.build();
		
		EmisorTarea emisor = new EmisorTarea();
		emisor.generarNuevaTarea(tareaJson.toString());
		for(int i=1000; i<1050; i++) {
			JsonObjectBuilder builderTareaN = Json.createObjectBuilder(tareaJson);
			builderTareaN.add("id",i);
			emisor.generarNuevaTarea(builderTareaN.build().toString());
		}
	}

}
