# Generador y solucionador de laberintos

Este es un proyecto personal desarrollado durante mi segundo año en la carrera de Ingenieria Informatica en la UCM, se trata de un generador y solucionador de laberintos
creado en Java.

## Como descargarlo y ejecutarlo

1. Descargar el zip dentro de la carpeta Launcher del repositorio
2. Descomprimir el zip 
3. Abrir la terminal (Linux, Mac) o CMD (Windows)
4. ir al directorio que acabamos de descargar
5. Ejecutar ***java -jar MazeGS.jar*** (en Windows puede abrirse con doble click)

## Menú principal

La aplicación cuenta con un menú principal, dicho menú es lo primero que veremos nada más iniciarla y nos permite seleccionar la altura y anchura del laberinto.

![MainMenu](https://user-images.githubusercontent.com/55555187/99090864-6b161780-25cf-11eb-8b97-3b95cac294f1.png)

## La aplicación

Una vez que hemos seleccionado los parametros de nuestro primer laberinto y hemos pulsado en el botón "generate" se nos redirigirá a la aplicación como tal.

![MainWindow](https://user-images.githubusercontent.com/55555187/99091072-ad3f5900-25cf-11eb-9337-07fd53cab1a9.png)

Como podemos ver se nos mostrará una zona con opciones e información y otra zona con el laberinto generado, cabe destacar que esta aplicación soporta laberintos cuadrados
y rectangulares de un tamaño máximo de 30 x 30 celdas.

![mazes](https://user-images.githubusercontent.com/55555187/99095983-11fdb200-25d6-11eb-9345-ddbfb3f5c7ce.png)

### Panel de generación

Si queremos generar un nuevo laberinto no es necesario que nos salgamos de la aplicación y volvamos al menú principal, podemos generar un nuevo laberinto de la misma forma 
utilizando el primero de los paneles que nos aparecen a la izquierda.

![GeneratePanel](https://user-images.githubusercontent.com/55555187/99091820-ad8c2400-25d0-11eb-86ca-47d7737e896d.png)

#### ¿Como se generan los laberintos?

Los laberintos se generan recursivamente utilizando una técnica llamada "Vuelta atrás". Una vez que se termina de generar, quitamos una serie de
muros de forma aleatoria para hacer que el laberinto sea más interesante y tenga más rutas.

Los puntos de comienzo y final tambien se eligen aleatoriamente y siempre habrá como mínimo un camino que los conecten.

### Panel de datos

Justo debajo del panel de generación tenemos el panel de datos, este panel ofrece información interesante sobre la efectividad del algoritmo empleado para encontrar
la solución del laberinto.

![DataPanel](https://user-images.githubusercontent.com/55555187/99092359-533f9300-25d1-11eb-9863-befb47cd9de9.png)

![SolveImage](https://user-images.githubusercontent.com/55555187/99092658-b7625700-25d1-11eb-9d3d-880e307b0899.png)


#### ¿Como se soluciona el laberinto?

Actualmente se soluciona empleando un algoritmo recursivo que utiliza la técnica "Divide y vencerás", hay formas mucho más eficientes de resolver un laberinto (como por ejemplo
aplicando teoría de grafos y algoritmos como el algoritmo de Dijkstra) pero teniendo en cuenta que como máximo se pueden generar laberintos de 30 x 30 decidí optar por este tipo
de solución.

A pesar de esto, el código está diseñado para permitir la adición de nuevas soluciones de forma comoda y sencilla, esto se debe a la gran modularidad e independencia de todas las secciones
que conforman la aplicación.

### Panel de opciones

Por último, la aplicación cuenta con una serie de opciones que podemos encontrar en el ultimo de los paneles.

![OptionsPanel](https://user-images.githubusercontent.com/55555187/99092937-22139280-25d2-11eb-929d-a3ffe7266939.png)


#### Solucionar el laberinto 

Es la primera opción que nos encontramos y, como su nombre indica, permite mostrar graficamente la solución del laberinto.

![OptionsPanel](https://user-images.githubusercontent.com/55555187/99093210-77e83a80-25d2-11eb-88ee-cbb92725988d.png)

![Solve](https://user-images.githubusercontent.com/55555187/99094265-e1b51400-25d3-11eb-8782-ba2c337ecc21.png)

### Guardar el laberinto

Con esta opción podemos guardar el laberinto actual dandole un nombre distintivo, se guardará en formato JSON en la carpeta Saves que genera la aplicación

![OptionsPanel2](https://user-images.githubusercontent.com/55555187/99093465-cac1f200-25d2-11eb-9388-da2d08f2d75a.png)

![Save1](https://user-images.githubusercontent.com/55555187/99108130-331ace80-25e7-11eb-86df-f693adcd7195.png)

![Save2](https://user-images.githubusercontent.com/55555187/99106967-678d8b00-25e5-11eb-9a1c-f9570e30b95a.png)


### Cargar el laberinto

La última de las opciones disponibles actualmente nos permite cargar uno de los laberintos que hayamos guardado anteriormente.

![OptionsPanel3](https://user-images.githubusercontent.com/55555187/99093590-f8a73680-25d2-11eb-8f7b-efeac266205f.png)

![LoadDialog](https://user-images.githubusercontent.com/55555187/99093623-05c42580-25d3-11eb-9aba-cf803326d639.png)


#### ¿Como funciona el guardado y la carga de laberintos?

El guardado y la carga de laberintos estan hechos aplicando los patrones Memento, Transfer Object y el patrón DAO, con el patrón DAO se consigue una mayor independiencia y modularidad del código,
con el uso del patrón memento y el patrón transfer object conseguimos que cada objecto sea responsable de su propia información y conseguimos que el diseño final quede más limpio.


