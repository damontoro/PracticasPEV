# Proyecto:

	El objetivo era hacer un algoritmo genético usando Gramaticas Evolutivas y Árboles para hacer una regresión simbólica 
	y poder aproximar la función solo con un dataset de 100 puntos usando una interfaz gráfica.

## Instalación:

	Simplemente clona el repo en tu IDE favorito o VSCode y debería funcionar si se ejecuta desde el main.
	En caso de problemas, en la raíz del repo está el .jar para ejecutar el programa directamente.

## Características

	Se proporciona una interfaz para poder modificar los parametros del algoritmo, como las probabilidades de mutación, cruce, etc

	Para el control del bloating de los arboles también se han implementado distintos metodos antibloating.

	La gramática usada para la regresión simbólica es la siguiente
	`* <I> : <exp> <op> <exp> //Lo hacemos así para que la expresión siempre tenga una operacion
 	 * <exp> : <exp> <op> <exp> | <var> | <sign> <exp>
	 * <op> : + | - | *
	 * <sign> : + | -
 	 * <var> : x | 1 | 2`

	### Selecciones: 
		- Estocástica
		- Ranking
		- Restos
		- Ruleta
		- Torneo Determinista/Probabilistico
		- Truncamiento
	### Cruces: 
		- Intercambio
		- Monopunto
		- Uniforme
	### Mutaciones:
		- Basica
		- Contracción
		- Expansión
		- Funcional
		- Hoist
		- Incremento
		- Permutación
		- SubArbol
		- Terminal

<div align='center'>
  <img src="https://user-images.githubusercontent.com/57947618/269212390-a8e2bb8a-18b3-4596-9b6b-3ac5cada423f.png"/> 
</div>
	
