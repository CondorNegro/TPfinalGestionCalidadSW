# Grupo Hello World
### Trabajo Final - Reproductor Mp3
### System Requirements Specification
___
|Autores                               |
|------------------------------------|
|Castagno Gustavo - 34582890           |
|Gonzalez Somerstein Gustavo -7721064  |
|Maero Facundo - 38479441              |
### Historial de Revisiones

| Versión | Fecha    | Resumen de cambios |
|:-------:|----------|--------------------|
|  1.0.0  | 17/06/16 | Primera versión    |
|  1.1.0  | 18/06/16 | Agrego Diagramas de Secuencia|
|  1.2.0  | 18/07/16 | Agrego Diagrama de Actividades|
|  1.3.0  | 19/07/16 | Agrego Matriz de Trazabilidad y Diagrama de Arquitectura|


Tabla de Contenidos
===================

    1. Introducción
    2. Glosario
    3. Definición de requerimientos de usuario
        3.1. Requerimientos funcionales
        3.2. Requerimientos no funcionales
    4. Arquitectura del sistema
    5. Especificación de Requerimientos de Sistema
    6. Matriz de Trazabilidad

## 1. Introducción
---
Se describe a continuación el documento de Especificación de Requerimientos para el sistema: Reproductor de Mp3.
Dicho sistema se utilizará para reproducir archivos de audio, permitiendo diversas interacciones con el usuario, como la modificación de los archivos que se quieren escuchar, la visualización de información sobre los mismos, entre otros.


## 2. Glosario
---
| Acrónimo | Descripción|
|:-------:|----------|
|  SRS | Especificación de Requerimientos de Software|
| UI| Interfaz de Usuario|

## 3. Definición de requerimientos de usuario
---
Se muestra a continuación un diagrama de casos de uso del sistema, donde se pueden observar las diferentes interacciones que soporta el mismo.

![Diagrama de Casos de Uso](http://s33.postimg.org/db4349orz/Use_Case_Diagram.jpg)


----------


**Diagramas de Secuencia - Casos de Uso:**

Se presentan diagramas de secuencia que explican los casos de uso más importantes del sistema.

1- Agregar una canción a la playlist:
 
![Agregar Canción a la Playlist](http://s33.postimg.org/9mk4p0gfj/Case_Add_Song.jpg)

2- Cambiar de canción en reproducción:

![Cambiar de canción](http://s33.postimg.org/cz5uye81b/Case_Change_Song.jpg)

3- Pausar / Iniciar canción:

![Play/Pausa](http://s33.postimg.org/5ojjdr6en/Case_Play_Pause.jpg)

4- Detener canción:

![Detener canción](http://s33.postimg.org/nm8uvauy7/Case_Stop.jpg)

5- Modificar volumen: 

![Modificar Volumen](http://s33.postimg.org/8ira4xxyn/Case_Volumen.jpg)


----------


**Diagrama de Actividades:**

![Diagrama de Actividades](http://s33.postimg.org/4s72l8gxb/Diagrama_de_Actividades.jpg)


----------


#### 3.1 Requerimientos funcionales
Se listan los requerimientos funcionales del sistema, seguido de gráficos explicativos de algunos de ellos:

1. **Ejecuciones simultáneas:** El sistema deberá mostrar 3 vistas DJView funcionando a la vez con los 3 modelos diferentes.
2. **Cambio de modelo:** El sistema deberá permitir cambiar el modelo utilizado en tiempo de ejecución.
3. **Nuevo HeartModel:** El sistema deberá permitir al usuario intentar crear un nuevo HeartModel mediante un botón.
4. **Agregar canciones:** El sistema deberá permitir al usuario seleccionar canciones para agregar a la lista de reproducción.
5. **Eliminar canciones:** El sistema deberá permitir borrar las canciones que estén en la playlist.
6. **Reproducir música:** El sistema deberá poder iniciar la reproducción de música.
7. **Pausar música:** El sistema deberá poder pausar la reproducción de música.
8. **Detener música:** El sistema deberá poder detener la reproducción de música.
9. **Reanudar reproducción:** El sistema deberá poder reanudar la reproducción de música.
10. **Saltear canciones:** El sistema deberá permitir reproducir la canción siguiente o anterior a la actual.
11. **Mostrar información:** El sistema debería mostrarle al usuario la información de la canción que se está reproduciendo en una ventana emergente.
12. **Mostrar portada:** El sistema debería mostrar la portada del álbum que se está reproduciendo.
13. **Modificar volumen:** El sistema debería permitir al usuario cambiar de volumen.

#### 3.2 Requerimientos no funcionales

1. **Facilidad de uso:** Un usuario promedio (que haya utilizado alguna vez un reproductor MP3) debería ser capaz de utilizar todas las funcionalidades del sistema en menos de  5 minutos simplemente interactuando con la UI.
2. **Mantenibilidad:** El sistema debería ser fácilmente mantenible, por lo que es deseable separar la lógica de la interfaz de usuario.
3. **Portabilidad:** El sistema deberá funcionar en las plataformas más populares, entre ellas Windows y Mac Os.

## 4. Arquitectura del sistema
Se muestra un gráfico de la arquitectura del sistema a alto nivel, que muestra las relaciones entre los casos de uso y la estructura del sistema: 

![Diagrama de Arquitectura de alto nivel](http://s33.postimg.org/fi39v2nqn/Arquitectura_para_requerimientos.jpg)

## 5. Especificación de Requerimientos de Sistema
#### 5.1 Requerimientos funcionales
- **1. Ejecuciones simultáneas:** El sistema deberá mostrar 3 vistas DJView funcionando a la vez con los 3 modelos diferentes.
    - 1.1. Se deberán mostrar tres interfaces de usuario DJView simultáneamente.
    - 1.2 Las UI deberán funcionar una con cada modelo provisto (BeatModel, HeartModel, MP3Model).


- **2. Cambio de modelo:** El sistema deberá permitir cambiar el modelo utilizado en tiempo de ejecución.
    - 2.1. Deberá ser posible cambiar de lógica a utilizar mediante un menu de tipo dropdown, en el que estarán las opciones a elegir.
    - 2.2  Al pulsar la opcion de cambiar de modelo tambien se debera cambiar de controlador.
    - 2.3 La funcionalidad de cada modelo funcionará hasta que se cambie el mismo, momento donde se detiene para dar lugar a la siguiente.


- **3. Nuevo HeartModel:** El sistema deberá permitir al usuario intentar crear un nuevo HeartModel mediante un botón.
    - 3.1 Se proveerá un botón que permitirá intentar crear una nueva instancia del modelo HeartModel.
    - 3.2 Los intentos de creación de un nuevo modelo serán ignorados por el sistema.
    - 3.3 Se debera mostrar en la BeatBar mediante un texto el numero de intentos de crear el modelo.


- **4. Agregar canciones:** El sistema deberá permitir al usuario seleccionar canciones para agregar a la lista de reproducción.
    - 4.1. El sistema debería permitir seleccionar únicamente archivos con extensión .mp3
    - 4.2. El sistema deberá permitir agregar canciones a la playlist de una a la vez.
    - 4.3 La selección de archivos deberá realizarse usando una ventana de selección de carpetas y archivos.
    - 4.4 Se debera actualizar la playlist automaticamente cada vez que se añada una cancion. 


- **5. Eliminar canciones:** El sistema deberá permitir borrar las canciones que estén en la playlist.
    - 5.1. El sistema deberá permitir eliminar de a una canción en la playlist, seleccionando la misma con el mouse y cliqueando en el boton eliminar.
    - 5.2 La canción eliminada deberá desaparecer de la lista de canciones a reproducir.


- **6. Reproducir música:** El sistema deberá poder iniciar la reproducción de música.
    - 6.1 Se podrá comenzar la reproducción de la música disponible en la lista.
    - 6.2 No se podrán reproducir archivos que no se hayan agregado a la playlist previamente.
    - 6.3 Al comenzar la reproducción, el sistema deberá habilitar la pulsación del botón de pausa.
    - 6.4 Al reproducir una canción, se deberá mostrar en la interfaz de usuario su título, y su duración.


- **7. Pausar música:** El sistema deberá poder pausar la reproducción de música.
    - 7.1 La reproducción de un archivo podrá ser pausada, guardando el instante en el que se realiza.
    - 7.2 Al pausar la reproduccion se debera habilitar nuevamente el boton de play


- **8. Detener música:** El sistema deberá poder detener la reproducción de música.
    - 8.1 Se podrá detener la reproducción de música, y no se guardará el instante de la canción en el que se realiza la acción.
    - 8.2 Al detener la reproduccion(stop) se debe habilitar el icono de play, independientemente del estado en el que estaba el reproductor previamente.


- **9. Reanudar reproducción:** El sistema deberá poder reanudar la reproducción de música.
    - 9.1 Si la canción actual estaba pausada, el sistema deberá reanudar la reproducción desde el momento de pausa guardado.
    - 9.2 Si la cancion actual estaba detenida, el sistema deberá reiniciar la reproducción desde el comienzo.


- **10. Saltear canciones:** El sistema deberá permitir reproducir la canción siguiente o anterior a la actual.
    - 10.1 Si la playlist contiene solo una canción, al intentar reproducir la siguiente o la anterior, el sistema deberá reiniciar la reproducción de la pista actual.
    - 10.2 Si se intenta reproducir la canción siguiente a la última, el sistema deberá comenzar con la primera de la lista.
    - 10.3 Si se intenta reproducir la canción anterior a la primera, el sistema deberá comenzar con la última de la lista.


- **11. Mostrar información:** El sistema debería mostrarle al usuario la información de la canción que se está reproduciendo en una ventana emergente.
    - 11.1 El sistema debería proveer con una interfaz para mostrar al usuario información relevante sobre la canción actual.
    - 11.2 Se debería poder mostrar el artista, álbum, año y género musical de la canción actualmente en reproducción.
    - 11.3 Al presionar más de una vez el botón para mostrar la información, se abrirá y cerrará la misma ventana respectivamente.


- **12. Mostrar portada:** El sistema debería mostrar la portada del álbum que se está reproduciendo.
    - 12.1 Se debería proveer una interfaz para mostrar la portada del álbum de la canción actual.
    - 12.2 Si se presiona más de una vez el botón para ver la portada, se abrirá y cerrará la misma ventana respectivamente.


- **13. Modificar volumen:** El sistema debería permitir al usuario cambiar de volumen.
    - 13.1 El sistema deberá proveer con la interfaz de una barra deslizable para seleccionar el volumen de reproducción.
    - 13.2 El nivel de volumen se podrá modificar arrastrando el selector de la barra con el mouse.
    - 13.3 El sistema deberá contar con un botón de mute o silencio, que baje el volumen al mínimo sin necesidad de interactuar con la barra selectora.

#### 5.1 Requerimientos no funcionales
Se detallan a continuación los requerimientos no funcionales que precisen un mayor desarrollo: 

- **1. Facilidad de uso:** Un usuario promedio (que haya utilizado alguna vez un reproductor MP3) debería ser capaz de utilizar todas las funcionalidades del sistema con un entrenamiento de 5 minutos.
    - 1.1 La UI deberá ser diseñado de manera simple, con menos de 10 botones para minimizar la confusión del usuario ante el sistema.
    - 1.2 Un usuario promedio, que ya conozca las funciones que brinda un reproductor de música, deberá ser capaz de utilizar el sistema en su totalidad luego de 5 minutos de interacción con el mismo.


- **2. Mantenibilidad:** El sistema debería ser fácilmente mantenible, por lo que se debería separar la lógica de la interfaz de usuario.
	- 2.1 La lógica del sistema deberá ser intercambiable.
	- 2.2 La UI deberá ser intercambiable.
	- 2.3 Modificaciones en los módulos del sistema deberían ser fácilmente aplicables.

- **3. Portabilidad:** El sistema deberá funcionar en las plataformas más populares, entre ellas Windows y Mac Os.
	- 3.1 El sistema deberá ser utilizable en el sistema operativo Windows 10.
	- 3.2 El sistema deberá ser utilizable en el sistema operativo OS X El Capitan.
	- 3.3 El sistema debería funcionar correctamente en distribuciones Linux. 

## 6. Matriz de Trazabilidad
A continuación se muestran las relaciones entre los casos de uso del sistema y los requerimientos enunciados. Es importante notar que se hizo foco en los casos de uso y requerimientos directamente relacionados con el modelo y la interfaz de usuario propios.


1. Matriz de Trazabilidad entre Casos de Uso y Requerimientos de Usuario.
![Matriz de Trazabilidad - Requerimientos de Usuario](http://s33.postimg.org/9dvz67kxb/Trazabilidad_req_de_usuario.jpg)


2. Matriz de Trazabilidad entre Casos de Uso y Requerimientos de Sistema.
![Matriz de Trazabilidad - Requerimientos de Sistema](http://s33.postimg.org/caj06hqr3/Trazabilidad_req_de_sistema.jpg)
