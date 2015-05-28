% Importancia de la Programación Funcional en Java
% Carlos Gómez
% 28 de Mayo del 2015

<!--
pandoc -t dzslides --template template.html -s slide.md -o index.html
-->

# Propósito de la presentación

> No pretendo ser exhaustivo en explicar el porque la programación funcional
> es importante en Java, pero si, almenos, despertar interés en este estudio
> y sentar algunas bases para continuar con la investigación.

![](images/disclaimer.gif)


# Contenido de la presentación

#. Un poco de mi
#. Un poco de Haskell
#.


# Un poco de mi

-------------------------

## Lic. en Informática

-------------------------

## Trabajando en un proyecto usando Java

![](images/illustration_back-end-front-end.png)

-------------------------

## Pero amante de Haskell

![](images/she-is-Haskell.jpg)

-------------------------

# Un poco de Haskell

Según [Wikipedia](http://en.wikipedia.org/wiki/Colleen_Haskell)

* Es actriz
* Es productora de televisión
* etc.

Y según yo

* Es linda
* Una ves que la conoces, es defícil hacerla a un lado

-------------------------

## En términos técnicos

> Haskell es un **Lenguaje** de **Programación Funcional** *puro*, con
> *evaluación perezosa* y de propósito general. Peyton Jones

-------------------------

Haskell provee:

* funciones de alto orden
* semántica no estricta
* tipado polimórfico estático
* tipos de datos algebraicos definidos por el usuario
* listas por comprensión
* sistema modular
* sistema monadico I/O
* tipos de datos primitivos para listas, arrays, números, ...
* etc.

# Cosas lindas de Haskell

Veamos algunas cosas lindas de Haskell.

------------------------------

## Queremos convertir todas las letras de una cadena a mayusculas/minusculas

~~~~{.haskell}
aMayuscula :: [Char] -> [Char]
aMayuscula [] = []
aMayuscula (c:cs)
    = (toUpper c) : aMayuscula cs
~~~~


~~~~{.haskell}
aMinuscula :: [Char] -> [Char]
aMinuscula [] = []
aMinuscula (c: cs)
    = (toLower c) : aMinuscula cs
~~~~

---------------------

## Podemos abstraer la repetición

~~~~{.haskell}
mapeo1 :: (Char -> Char) -> [Char] -> [Char]
mapeo1 f [] = []
mapeo1 f (c:cs) = (f c) : mapeo1 f cs
~~~~~

Y nuestras nuevas funciones serian

~~~~{.haskell}
aMayuscula1 = mapeo1 toUpper "Carlos"
aMinuscula2 = mapeo1 toLower "Carlos"
~~~~

--------------------------

## Pero si ahora queremos convertir un listado de números a un listado descriptivo de pares (P) e impares (I)

~~~~{.haskell}
parImpar :: [Int] -> [Char]
parImpar [] = []
parImpar (n:ns)
    = if (even n) then 'P' : parImpar ns
                  else 'I' : parImpar ns
~~~~

------------------------

## Pero queremos reutilizar la función mapeo de repetición. Entonces, necesitamos parametrizar el tipo.

~~~~{.haskell}
mapeo :: (a -> b) -> [a] -> [b]
mapeo f [] = []
mapeo f (e:es) = (f e) : mapeo f es
~~~~

-----------------------

Nuestras funciones ahora se reescribirian así:

~~~~{.haskell}
aMayuscula2 = mapeo toUpper "Carlos"
aMiniscula2 = mapeo toLower "Carlos"

determinarTipoNumero :: Int -> Char
determinarTipoNumero n = if (even n) then 'P'
                                     else 'I'
parImpar2 = mapeo determinarTipoNumero [4,3,2,1]
~~~~

---------------------

## La función **map** de Haskell

> Lo *lindo* de **Haskel** es que la función **mapeo** es una función estandar de
> **Haskell**.  Es llamada "map" y abstrae la repetición y la parametrización
> del tipo.

~~~~{.haskell}
map :: (a -> b) -> [a] -> [b]
~~~~

Las ventajas son:

* Se abstrae la repetición
* Es más simple de usarlo
* Nos permite centrarnos en lo importante, la función que mapea de un valor a otro

--------------------------

## Un ejemplo no tan trivial como el de cadenas y números

Tenemos una lista de empleados:

~~~~{.haskell}
data Empleado
    = Empleado {nombre :: String, sueldo :: Float}
    deriving Show

listaEmpleados :: [Empleado]
listaEmpleados
    = [ Empleado "Pedro" 500
      , Empleado "Juan" 600
      , Empleado "Jacobo" 700]
~~~~

------------------------

## Queremos realizar el incremento del 8.5%

~~~~{.haskell}
incremento :: Float
incremento = 8.5

incrementarSueldo :: Empleado -> Empleado
incrementarSueldo empleado
  = empleado {sueldo = nuevoSueldo}
  where nuevoSueldo
    = let sueldo' = sueldo empleado
          inc     = (incremento / 100.0) * sueldo'
      in sueldo' + inc

aplicarIncremento
  = map incrementarSueldo listaEmpleados
~~~~

--------------------------------------

## La función **filter**

Al igual que la función **map** tenemos la función **filter** de Haskell.

~~~~{.haskell}
filter :: (a -> Bool) -> [a] -> [a]
~~~~

La función **filter** abstrae la

* Se abstrae la repetición
* Construye un nuevo listado basado en la función predicado

-----------------------------

## Filtrar empleados

Continuando con el ejemplo anterior, queremos filtrar los empleados que
ganaban más de 500.

~~~~{.haskell}
mayorA500 :: Empleado -> Bool
mayorA500 empleado = sueldo empleado > 500.0

aplicarFiltro = filter mayorA500 listaEmpleados
~~~~

--------------------------------------

## Funciones de combinación binaria

Otra de las funciones poderosas de Haskell son las funciones de combinación
binaria:

- **foldr**: combinación de un listado comenzando por la derecha (right)

    ~~~~{.haskell}
    foldr :: (a -> b -> b) -> b -> [a] -> b
    foldr f e [] = e
    foldr f e (x:xs) = f x (foldr f e xs)
    ~~~~

- **foldl**: combinación de un listado comenzando por la izquierda (left)

    ~~~~{.haskell}
    foldl :: (b -> a -> b) -> b -> [a] -> b
    foldl f e [] = e
    foldl f e (x:xs) = foldl f (f e x) xs
    ~~~~

-----------------------------

## Obtener el gasto de sueldos

Con el objetivo de usar la función **foldr** podemos hacer una función
que sume los sueldos de los empleados y asi obtener el gasto de sueldos.

~~~~{.haskell}
sumarSueldos :: Empleado -> Float -> Float
sumarSueldos (Empleado _ sueldo1) sueldo2
    = sueldo1 + sueldo2

gastoSueldos = foldr sumarSueldos 0.0 listaEmpleados
~~~~
