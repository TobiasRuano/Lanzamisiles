# Lanzamisiles

## Trabajo Práctico Obligatorio - Introducción a la orientación a objetos
> El equipo de analistas ha finalizado la determinación de los requerimientos de un juego de lanzamisiles.
> Se solicita al equipo de diseñadores que diseñe e implemente el sistema relevado. A continuación, se detalla los requerimientos relevados por el equipo como así también algunos datos obtenidos por parte de los usuarios.
* El juego consiste en disparar desde un cañón, misiles a una serie de barcos que pasan por nuestro horizonte, para ellos utilizaremos las teclas de izquierda / derecha para indicar hacia donde disparamos, la tecla suma para aumentar o disminuir la potencia del disparo y la barra espaciadora para iniciar el disparo. 
* Una vez efectuado el disparo, no puede volver a dispararse el cañón hasta que pasen un número determinado de segundos (dicho número debe ser configurable y mayor a 1). 
* El juego posee un determinado número de niveles que se diferencian en el aumento de la velocidad de los barcos (dicho número debe ser configurable pero siempre mayor a 5). 
* Los barcos circulan de izquierda a derecha o de derecha a izquierda de forma aleatoria. Los mismos se agrupan de a 10 formando una serie. Una vez que un barco recorre nuestro horizonte y no fue hundido no vuelve a pasar. 
* Cada vez que pasan los 10 barcos de una serie se cambia de nivel. Si en una serie no se hunden al menos 5 barcos se pierde una vida. Al reiniciar se continua en el mismo nivel en el que estaba. 
* Por cada barco que se hunde se reciben 20 puntos y por cada cambio de nivel se reciben 100. Por cada trescientos puntos obtenidos se recibe una vida extra. No hay límite para el numero de vidas que pueden acumularse. 
* El juego comienza con la selección de la dificultad con la cual se desea jugar, una vez determinada la misma se registra y permanece sin cambios hasta que se decida cambiarla (el número de dificultades debe ser configurable). 
* Cada vez que se inicia el juego comienza en la dificultad menor y el jugador posee 3 vidas. No se puede cambiar la dificultad mientras se está jugando. 
* Cuando un jugador termina el juego (ya sea porque llego al final de las series, perdió todas sus vidas o simplemente deja de jugar) ingresará su nombre para que quede en el ranking de jugadores. El mencionado ranking almacenara los nombres de los últimos 10 jugadores y sus puntajes ordenados de mayor a menor. 
* En la pantalla de juego debe figurar la cantidad de barcos que faltan para completar la serie, la cantidad de barcos hundidos de esa serie, los puntos totales hasta el momento, los puntos obtenidos para la próxima vida, el número de la serie que se está jugando y el nivel de dificultad. 
* En la pantalla principal, un menú nos permitirá configurar los parámetros del juego, jugar o salir. Cada una de estas opciones nos enviará a una nueva pantalla donde desarrollaremos la actividad seleccionada o bien finaliza la ejecución. 

## Pautas para la entrega:
* Todas las entregas serán digitales conteniendo al menos un archivo con el número de grupo y los integrantes. 
* Se identificarán con el número de grupo y numero de fase. 
* Se considerará como fecha de entrega a la correspondiente a la última versión subida a WebCampus sección grupos. 
* Las entregas deben realizarse en un único archivo comprimido (zip o rar). 
* Si se les solicitaron correcciones a las entregas anteriores, las mismas deben incluirse en la nueva entrega. 
* Respetar las consignas y los objetivos. 
* La aprobación del TPO es individual ya que en la entrega final se le realizara una evaluación individual a cada integrante del grupo sobre cualquiera de las partes y/o etapas del TPO.

## Pautas para la aprobación del Trabajo Practico Cuatrimestral
* Cumplir con todas las entregas definidas en tiempo y forma. 
* Aprobar todas las entregas y/o correcciones. 
* Aprobar la evaluación final sobre TPO.

## Fases de Entregas
* Fase A - 16/05/2019 [x]: Definición de los requerimientos y diagrama de clases. 
* Fase B - 30/05/2019 [x]: Diagramas de secuencia (todos los requerimientos). 
* Fase C - 13/06/2019 [x]: Código del negocio funcionando con controlador y test. 
* Final  - 04/07/2019 []: Interfaz gráfica que utilice el negocio entregado en la Fase C.
