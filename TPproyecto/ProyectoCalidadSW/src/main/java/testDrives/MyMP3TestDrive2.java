package main.java.testDrives;

import main.java.controllers.MP3Controller2;
import main.java.models.MP3Model;
import main.java.views.MP3View;

public class MyMP3TestDrive2 {
	
	//Este Test Drive permite probar la vista nueva MP3View, con el modelo propio MP3Model
	//Se pueden agregar canciones manualmente con un dialogo de seleccion, usar todos los 
	//botones de control multimedia, y ver la informacion de la pista actual (datos de la misma
	//y portada del album)
	public static void main(String[] args) {
		MP3Model model = MP3Model.getInstance();
		MP3View view = new MP3View(model);
		MP3Controller2 controller = new MP3Controller2(model,view);
		view.setController(controller);
	}
}
