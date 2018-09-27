# Grupo Hello World
### Trabajo Final - Reproductor Mp3
### Configuration Management Plan
___
|Autores                               |
|------------------------------------|
|Castagno Gustavo - 34582890           |
|Gonzalez Somerstein Gustavo -7721064  |
|Maero Facundo - 38479441              |
### Historial de Revisiones

| Versión | Fecha    | Resumen de cambios |
|:-------:|----------|--------------------|
|  1.0.0  | 10/06/16 | Primera versión    |
|  1.1.0  | 12/06/16 | Pequeños cambios generales |
| 1.1.1   | 12/06/16 | Correcciones en las imagenes |
1.2.0    | 17/06/2016| Agrego ramas Test y correcciones

### Página de Aprobación

A continuación se listan las personas a las que se deberá solicitar su aprobación en cada cambio mayor del plan.  

| Rol                   | Nombre           |
|-----------------------|------------------|
| Configuration Manager | Castagno Gustavo |
| Software Developer    | Gonzalez Gustavo |
| Software Developer    | Maero Facundo    |
*Tabla 1 - Aprobación necesaria*

Tabla de Contenidos
===================

    1. Introducción
        1.1. Propósito y Alcance
        1.2. Propósito de las Prácticas del Software Configuration Management Plan
        1.3. Acrónimos
        1.4. Herramientas del Configuration Management Plan
    2. Roles de Gestion de Configuraciones
        2.1. Configuration Manager del Proyecto
        2.2. Responsabilidades del plan de CM
    3. Gestión de Cambios
        3.1. Alcance
        3.2. Módulos de clientes y Releases
            3.2.1. CCB (Change Control Board)
                3.2.1.1. Miembros
                3.2.1.2. Frecuencia
                3.2.1.3. Herramienta de Gestión de Cambios
    4. Configuration Identification
    5. Equipos de Desarrollo del Proyecto
    6.	Gestión de la Configuración del Código Fuente
        6.1.	Módulos Centrales
            6.1.1.	Definición de Ramas
            6.1.2.	Definición de Tags
            6.1.3.	Archivos Auxiliares
            6.1.4.	Estrategia de Merging
            6.1.5.	Dirección y forma de acceso a las herramientas utilizadas
    7.	Build Management
    8.	Release Management
    9.	Backup y Recuperación de la Información



## 1. Introducción
---
#### 1.1.	Propósito y Alcance

En el presente documento se abarca el Plan de Gestión de las Configuraciones para el trabajo final de la materia Ingeniería de Software, donde como proyecto original se desarrolló un reproductor mp3 basado en Java. El objetivo de este plan es controlar la configuración de los requerimientos, documentos, software y herramientas usadas en este proyecto.  La gestión de las configuraciones de software (SCM) consiste en las herramientas y métodos utilizados para controlar el software a lo largo de su desarrollo y uso.

#### 1.2. Propósito de Practicas del SMP

* Asegurar la consistencia al poner en práctica las actividades de CM.
* Definir las autoridades que soporten las prácticas del CM.
* Mantener la integridad del producto a lo largo de su ciclo de vida.
* Informar a grupos e individuos interesados sobre el estado del proyecto.
* Crear un historial verificable de los estados actual y anteriores de los productos desarrollados.
* Mejoras en el proceso de desarrollo.

#### 1.3. Acrónimos

| Acrónimo 	| Descripción                                        	|
|----------	|----------------------------------------------------	|
| CM       	| Control de las configuraciones                     	|
| Tag      	| Etiqueta (usadas por ejemplo para releases)        	|
| CCB      	| Junta de control de cambios                        	|
| IDE      	| Entorno de desarrollo                              	|
| SCMP     	| Plan de Gestión de las Configuraciones de Software 	|
| GPMC     	| Manager Global de Configuraciones del Proyecto     	|

*Tabla 2 - Acrónimos utilizados*

#### 1.4. Herramientas del Configuration Management Plan

| Herramienta / Proceso 	| Proposito                                                                       	|
|-----------------------	|---------------------------------------------------------------------------------	|
| Eclipse IDE           	| Entorno de desarrollo principal del proyecto                                    	|
| Notepad++             	| Creación de documentación referida al reporte de versiones y gestión de cambios 	|
| Github                	| Servicio de repositorio y hosting de ítems puestos en control de configuración  	|
| Travis CI             	| Servicio de Integración Continua                                                	|
| Gradle                	| Sistema de automatización de builds                                             	|
| Visual Paradigm Community Edition | Software de modelado UML | 

*Tabla 3 - Herramientas utilizadas*

## 2. Roles de Gestión de Configuraciones
---
#### 2.1 Configuration Manager del Proyecto

Las actividades del manejo de configuración serán coordinadas dentro del proyecto "Reproductor mp3" por el Manager Global de Configuraciones del Proyecto (GPCM), rol que será asignado a una persona. 

El GPCM será responsable por actividades como controlar las ramas principales y las versiones de lanzamiento, determinando cuándo deben ser creadas las ramas y que actividades y características corresponden a cada rama. 

Las actividades de manejo de configuraciones, procesos y sus respectivos procedimientos deben ser seguidas y respetadas por todos los miembros. Es la responsabilidad de cada persona seguir y aplicar el procedimiento de CM apropiado, de acuerdo con sus rol/roles.

#### 2.2 Responsabilidades del plan de CM

| Rol                   	| Responsabilidades                                                                                                                                                                                                                                                                                                                                                                        	|
|-----------------------	|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	|
| Configuration Manager 	| Posee la responsabilidad global de todos los ítems en configuración.Responsable de la creación de ramas y administrar las mismas.Responsable de aplicar etiquetas en los releases.Asegurarse de la integridad y rastreabilidad de los ítems en configuración del proyecto.Coordinar actividades de CM dentro del proyecto.Asistir en actividades de merge y de building.                 	|
| Software Developer    	| Generar nuevo código con nuevas características periódicamente.Corregir bugs encontrados en el código.Ayudar a resolver conflictos en las actividades de merge y de building.Asegurarse de la integridad del producto y su rastreabilidad a lo largo del tiempo.Cumplir los requisitos de calidad en los distintos branches.Seguir las prácticas recomendadas en cada etapa del proyecto 	|

*Tabla 4 - Responsabilidades de los miembros*

## 3. Gestión de Cambios
---
#### 3.1. Alcance

La gestión de cambios es un proceso que ocurre luego de que documentación y código fuente están identificados y aprobados. Los cambios incluyen modificaciones internas al enfoque documentado originalmente, debido a resultado de tests o solicitudes de integración de nuevas funciones.

#### 3.2 Módulos de clientes y Releases

##### 3.2.1 CCB (Change Control Board)

La CCB es un comité que se asegura de que cada modificación es considerada apropiadamente, y autorizada antes de su implementación. La junta es responsable de aprobar, monitorear y controlar las solicitudes de cambios para establecer ramas de desarrollo de ítems de configuración. Las decisiones sobre las solicitudes de cambios son tomadas según el resultado de tests de calidad del producto.  
Los integrantes de la junta se contactaran via email o por teléfono para concertar una reunión extraordinaria, o discutir cambios referidos al proyecto fuera de ellas.

**3.2.1.1 Miembros**

La siguiente tabla muestra los miembros de la junta que asisten a las reuniones de la CCB.

| Rol en la CCB         |
|-----------------------|
| Configuration Manager |
| Software Developer    |
| Software Developer    |


*Tabla 5 - Miembros de la CCB*

**3.2.1.2 Frecuencia**

| Reunión de la CCB     | Frecuencia                            |
|-----------------------|---------------------------------------|
| Reproductor MP3 - CCB | 3 reuniones semanales, o bajo demanda |

*Tabla 6 - Frecuencia de reuniones de la CCB*

**3.2.1.3 Herramienta de Gestion de Cambios**

Se usará para gestionar los cambios en el proyecto la herramienta Issues de GitHub. URL: [Repositorio GitHub](https://github.com/castagno/IngSoft-2016-HelloWorld)

## 4. Configuration Identification

El repositorio de Github será usado para guardar y compartir recursos de diferentes versiones.  
El esquema de directorios a usar será el estándar para proyectos en Java, conformado por la carpeta principal ya mencionada, seguido de la carpeta src, donde se encontrara la carpeta main, con los archivos de código fuente del proyecto. 
Dentro de la carpeta src se encuentra la carpeta resources donde estan todos los recurso estaticos necesarios para el correcto funcionamiento del reproductor(por ejemplo imagenes para los iconos y archivos mp3 que vienen incluidos con el mismo).
Para los archivos de testing también se utilizará el esquema standard, ubicando las clases de Unit Test bajo la carpeta test, dentro del directorio principal del proyecto.
La carpeta principal tambien contiene una carpeta llamada docs donde se encuentra la documentacion del proyecto.

## 5. Equipos de Desarrollo del Proyecto

El alcance de este documento abarca código fuente gestionado por los siguientes equipos:

**Development Team:** a cargo de desarrollar nuevas funcionalidades, subir el código al branch correspondiente y hacer el merge a la rama adecuada según el calendario del proyecto. También pueden trabajar arreglando bugs o testeando características.

**Release Management Team:** está a cargo del testing necesario para que el producto pueda llegar a ser lanzado al consumidor, asegurando su calidad. Está a cargo de la mantención del repositorio y la gestión de los tests automáticos que se realizan luego de cada commit, así como también de crear y mantener la documentación que será entregada a los consumidores.

## 6.  Gestión de la Configuración del Código Fuente

En esta sección se describen diferentes ítems de gestión de código fuente. Abarca aspectos referidos a esquema de ramificado, etiquetado, estrategia de fusión de código y niveles esperados de calidad para la totalidad del producto.

#### 6.1. Módulos Centrales

El gráfico siguiente muestra el esquema de ramificado que será seguido en los módulos centrales.

![alt text][branch strategy]

[branch strategy]: http://s33.postimg.org/k81vmdzxr/branch_strategy.jpg "Branch Strategy"

#### 6.1.1. Definición de Ramas:

Los tipos de ramificaciones utilizadas se describen a continuación:

* **Rama Principal / Master**: es la rama donde se alojan las versiones estables y finales del producto, con funcionalidades testeadas y la menor cantidad de bugs posible. Aquí se integrarán las nuevas funcionalidades una vez pasado un tiempo de testing, siempre que sean necesarias para seguir trabajando en otros branches de release o de desarrollo.Desde esta rama se realizaran los build para release.

* **Development Branch**: aloja desarrollos de nuevas características, que deberán ser testeadas y pulidas adecuadamente para poder llegar a un nivel mínimo de calidad y ser insertadas en el Master. El formato para nombrar estas ramas es: dev-branch-<funcionalidad>  
Ej: dev-branch-singleton, dev-branch-controlador-mp3, etc.

* **Hotfix Branch**: son las ramas que pueden ser utilizadas para corregir errores o bugs detectados en el código, aislando el problema y así pudiendo continuar con el desarrollo en las ramas de desarrollo. Se les dará el nombre de hotfix-branch-feature.  
Por ejemplo: hotfix-branch-singleton.

* **Test Branch**: son las ramas de desarrollo de tests. Se diferencian de las ramas de desarrollo en que se realizan pruebas sobre las clases del proyecto, y se crean clases para utilizar con la herramienta JUnit. Se les dará el nombre de test-branch-feature. Se admite generar varios tests sobre diferentes clases en una misma rama.
Por ejemplo: test-branch-model.

Luego de definir los tipos de ramas, queda claro que la principal motivación al utilizar este esquema es mantener aislado el código inestable y bajo desarrollo del código estable y potencial a ser lanzado como producto final. 

Es posible que se realicen desarrollos de nuevas características en la rama Master, sobre todo en una primera instancia, cuando el producto no tiene una estructura definida y no está cerca de un release inicial.

##### 6.1.2. Definición de Tags: 

* **Development tags**: en las ramas de desarrollo se etiquetara numéricamente la última versión del código, el que se considere apto para un merge hacia ramas más estables y de entrega de producto, para tener una referencia a la hora de realizar el merge y completar la documentación pertinente, siguiendo el esquema v<ID>. Ej: v0.1.0.  
El identificador representa las versiones del código puesto en configuración en esa rama, comenzando por el 0.1.0, luego el 0.2.0. Esta numeración será utilizada en la mayoría de los casos, cuando el nuevo contenido del archivo amerite un cambio de nomenclatura. 

* **Hotfix Tags**: usados en las ramas de hotfix, permiten identificar la versión de la misma previa al merge con la rama de donde se creó, permitiendo saber que el problema está resuelto y que puede continuarse el desarrollo normalmente.  
El identificador usado seguirá la sintaxis: v.0.x.0-hotfix, para dejar claro que no es un tag de desarrollo, sino uno de corrección de errores.

* **Test Tags**: usados en las ramas de testing. Se aplican cuando los archivos creados en la rama alcanzaron un nivel aceptable, y se pueden integrar al master para utilizarlos para testear otras clases del proyecto.
El identificador usado seguirá la sintaxis: v.0.x.0-test.

* **Release Tags**: se utilizan en el MASTER para especificar que los archivos del proyecto alcanzaron un estado entregable. El identificador usado seguirá la sintaxis: v.x.x.x, con l elección de la numeración dependiendo de la magnitud del release.

##### 6.1.3. Archivos Auxiliares:

Se generarán archivos auxiliares para gestionar las distintas versiones del proyecto: archivos de texto que condensen las notas de la versión y las características de los branch que se unieron, así como la fecha y la persona responsable de la operación. 

Ej:

    Notas del Merge.txt 
    09-06-2016
    (<Nombre del desarrollador>)
    Merge del branch <tag> al branch <tag>
    Se agregó la funcionalidad <feature> 
    Se corrigió el error <error>
    Comentarios: <comentario>
---
    Notas de la Versión.txt
    10-06-2016
    (<Nombre del desarrollador>)
    Notas de la versión <version>
    Features presentes: <features>
    Bugs conocidos <bugs>
    Comentarios: <comentario>

##### 6.1.4. Estrategia de merging:

Al realizar merges, es recomendable especificar el End tag (version a ser reintegrada) y el Start tag (version comun). Esto es útil para identificar las versiones afectadas por esta operación, en caso de ser necesario otro merge en esa misma rama, se hará partiendo del merge anterior.

![alt text][merge strategy]

[merge strategy]: http://s33.postimg.org/lz7pa9xmn/merge_strategy.jpg "Merge Strategy"

Por ejemplo, en el gráfico se observa que se quiere hacer el merge entre la rama dev-branch-singleton y el HEAD, partiendo del Start tag dev-branch-singleton, y siendo el End tag dev-branch-singleton 0.1.0.  
Luego, se necesito realizar otro merge, siendo el nuevo Start tag dev-branch-singleton 0.1.0, y el nuevo End tag dev-branch-singleton 0.2.0.  
Aquí se puede apreciar que el nuevo Start tag depende del ultimo End tag utilizado, por lo que se realiza el merge partiendo desde el merge anterior, para evitar conflictos ya resueltos.  
Para registrar el historial de merging, y los Start y End tags, se utilizará el archivo de notas de la versión Notas de Merge.txt, que irá evolucionando a la par del proyecto. 
Como información opcional, puede agregarse la lista de cambios del merge.

##### 6.1.5. Dirección y forma de acceso a las herramientas utilizadas:

Para el control de versiones, se utilizara la herramienta GitHub. Los archivos del proyecto se subirán al repositorio situado en la siguiente [URL GitHub](https://github.com/castagno/IngSoft-2016-HelloWorld).

La herramienta de integración continua utilizada es Travis CI, complementada con Gradle, que verifica y arma los builds, y realiza los chequeos pertinentes, corriendo tests unitarios y demás configuraciones. [URL Travis](https://travis-ci.org/castagno/IngSoft-2016-HelloWorld)

La gestión de defectos se realizará utilizando la herramienta Issues de GitHub, de forma local entre los desarrolladores, o en su defecto utilizando las herramientas de comunicación descritas anteriormente para informar los errores que surgen en el proyecto.

## 7. Build Management

El tipo de Build a utilizar y que será implementado en todas las ramas es:  
**Continuous Integration Builds:**  Son útiles para identificar tan pronto como sea posible errores en el codigo y asi poder arreglarlos antes de hacer algún build formal. Están configurados para no aplicar ningún tag y para ser corridos automáticamente cada vez que alguien realice un commit a cualquiera de las ramas.  
En la pestaña correspondiente de Travis CI se puede encontrar información útil, tal como el resumen de los tests empleados, el autor, la fecha del commit,etc.  
La herramienta a utilizar para manejar el proceso de builds definido será Travis CI, que será debidamente configurada para enviar correos electrónicos a los grupos de trabajo que realicen el commit avisando de alguna falla en el build.

## 8. Release Management

Las Release builds serán realizadas desde el Master luego de finalizado el desarrollo del proyecto. Estos serán enviados a los clientes por medio del repositorio de GitHub dándoles acceso al mismo para que descarguen los archivos necesarios, y puedan probar el proyecto en su IDE de Java de preferencia. Además, se proporcionarán los archivos .jar para su ejecución directa, sin necesidad de inspeccionar o ejecutar el código manualmente.

## 9. Backup y Recuperación de la Información

Se guardarán los archivos y documentos del proyecto en Google Drive como backup en caso de perder la información, pudiendo acceder todos los miembros del proyecto al mismo. Este backup será realizado al final de cada jornada de trabajo, en la siguiente [URL](https://drive.google.com/open?id=0B6PH2ZY6oYH7TmJLOFZwUHdaUjA)
