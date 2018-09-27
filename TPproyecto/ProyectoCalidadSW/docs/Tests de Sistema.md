# Grupo Hello World
### Trabajo Final - Reproductor Mp3
### Casos de Prueba de Sistema
___
|Autores                               |
|------------------------------------|
|Castagno Gustavo - 34582890           |
|Gonzalez Somerstein Gustavo -7721064  |
|Maero Facundo - 38479441              |
### Historial de Revisiones

| Versión | Fecha    | Resumen de cambios |
|:-------:|----------|--------------------|
|  1.0.0  | 20/06/16 | Primera versión    |

## Tests de Sistema

### 1. Descripción del Test: Reproducir canción

**Ejecucion (pasos):**  
1.Abrir el programa  
2.Apretar el botón para añadir canción(+)  
3.Elegir un archivo con extension .mp3  
4.Hacer click en el boton play  

**Resultado esperado:** La canción elegida debería empezar a reproducirse y la barra de progreso empezar a incrementarse.

### 2. Descripción del Test: Pausar

**Ejecución (pasos):**  
1.Abrir el programa  
2.Apretar el botón para añadir canción(+)  
3.Elegir un archivo con extension .mp3  
4.Hacer click en el boton play(>)  
5.Hacer click en el boton pausa  

**Resultado esperado:** La canción elegida debería pausarse y la barra de progreso detenerse.

### 3. Descripción del Test: Reanudar

**Ejecución (pasos):**  
1.Abrir el programa  
2.Apretar el botón para añadir canción(+)  
3.Elegir un archivo con extension .mp3  
4.Hacer click en el boton play(>)  
5.Esperar 20 segundos  
6.Hacer click en el boton pausa  
7.Hacer click en play nuevamente  

**Resultado esperado**: La canción elegida debería reanudarse desde el instante que fue pausada, ademas la barra de progreso debería volver a incrementarse.

### 4. Descripción del Test: Detener

**Ejecución (pasos):**  
1.Abrir el programa  
2.Apretar el botón para añadir canción(+)  
3.Elegir un archivo con extension .mp3  
4.Hacer click en el boton play(>)  
5.Hacer click en el boton stop  

**Resultado esperado:** La canción elegida debería detenerse, ademas la barra de progreso debería ponerse a 0.

### 5. Descripción del Test: Play luego de un stop

**Ejecución (pasos):**  
1.Abrir el programa  
2.Apretar el botón para añadir canción(+)  
3.Elegir un archivo con extension .mp3  
4.Hacer click en el boton play(>)  
5.Esperar 20 segundos  
6.Hacer click en el boton stop  
7.Hacer click en play nuevamente  

**Resultado esperado:** La canción debería empezar a reproducirse desde el inicio(a diferencia de pausa donde era posible reanudar)


### 6. Descripción del Test: Siguiente Cancion

**Ejecución (pasos):**  
1.Abrir el programa  
2.Apretar el botón para añadir canción(+)  
3.Elegir un archivo con extension .mp3  
4.Añadir otra canción  
5.Hacer click en el botón siguiente(>>)  

**Resultado esperado:** La segunda canción añadida debería empezar a reproducirse

### 7. Descripción del Test: Borrar canción

**Ejecucion (pasos):**  
1.Abrir el programa  
2.Apretar el botón para añadir canción  
3.Elegir un archivo con extension .mp3  
4.Añadir una nueva canción.  
5.Seleccionar de la playlist una canción.  
6.Hacer click en el botón para borrar canción. 

**Resultado esperado:** La canción seleccionada debería desaparecer de la playlist y no debería poder reproducirse.

### 8. Descripción del Test: Ver información de la canción

**Ejecucion (pasos):**  
1.Abrir el programa  
2.Apretar el botón para añadir canción  
3.Elegir un archivo con extension .mp3  
4.Hacer click en el boton Mostrar Info  

**Resultado esperado:** Debería abrirse una ventana emergente con información de la canción(Artista,Titulo,Album,Genero)

### 9. Descripción del Test: Ver portada del album

**Ejecucion (pasos):**  
1.Abrir el programa  
2.Apretar el botón para añadir canción  
3.Elegir un archivo con extension .mp3  
4.Hacer click en el boton Mostrar Portada  

**Resultado esperado:** Debería abrirse una ventana emergente con la imagen de la portada del album elegido.

### 10. Descripción del Test: Barra de volumen

**Ejecución (pasos):**  
1.Abrir el programa  
2.Apretar el botón para añadir canción(+)   
3.Elegir un archivo con extension .mp3  
4.Hacer click en el boton play(>)  
5.Arrastrar la barra de volumen al máximo  

**Resultado esperado:** El volumen debería cambiar progresivamente hasta llegar al máximo.

### 11. Descripción del Test: Botón Mute

**Ejecución (pasos):**  
1.Abrir el programa  
2.Apretar el botón para añadir canción(+)  
3.Elegir un archivo con extension .mp3  
4.Hacer click en el boton play(>)  
5.Hacer click en el boton mute  

**Resultado esperado:** Inmediatamente se debería dejar de escuchar la canción que se estaba reproduciendo y la barra de volumen se debería poner automáticamente al mínimo.


----------


## Casos Extremos

### 12. Descripción del Test: Añadir un archivo con extension distinta a mp3

**Ejecución (pasos):**  
1.Abrir el programa  
2.Apretar el botón para añadir canción(+)  
3.Elegir un archivo con extension distinta a mp3  

**Resultado esperado:** El sistema no debería sufrir ningún cambio, debería ignorar el archivo que se intento añadir.

### 13. Descripción del Test: Tratar de reproducir cuando no hay canciones

**Ejecución (pasos):**  
1.Abrir el programa  
2.Hacer click en el boton play  

**Resultado esperado:** No debería haber ningún cambio en la UI

### 14. Descripción del Test: Borrar la canción que se esta reproduciendo

**Ejecución (pasos):**  
1.Abrir el programa  
2.Apretar el botón para añadir canción(+)  
3.Elegir un archivo con extension .mp3  
4.Hacer click en el boton play  
5.Seleccionar la canción en la playlist  
6.Hacer click en el icono de borrar canción  

**Resultado esperado:** La reproduccion debería detenerse y debería habilitarse nuevamente el botón de play(). La canción removida ya no debe aparecer en la playlist.
