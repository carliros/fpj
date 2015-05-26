module Mapping where
import Data.Char

main :: IO ()
main = putStrLn "hi there"

-- Queremos convertir todas las letras de una cadena
-- a mayusculas/minuscular
aMayuscula :: [Char] -> [Char]
aMayuscula [] = []
aMayuscula (c:cs)
    = (toUpper c) : aMayuscula cs

aMinuscula :: [Char] -> [Char]
aMinuscula [] = []
aMinuscula (c: cs)
    = (toLower c) : aMinuscula cs

-- abstraendo la repetición
mapeo1 :: (Char -> Char) -> [Char] -> [Char]
mapeo1 f [] = []
mapeo1 f (c:cs) = (f c) : mapeo1 f cs

-- probando mapeo1
aMayuscula1 = mapeo1 toUpper "Carlos"
aMinuscula2 = mapeo1 toLower "Carlos"

-- Ahora queremos convertir un listado de numeros a un listado descriptivo
-- de pares (P) e impares (I)
 
parImpar :: [Int] -> [Char]
parImpar [] = []
parImpar (n:ns)
    = if (even n) then 'P'   : parImpar ns
                  else 'I' : parImpar ns

-- pero queremos reutilizar la función mapeo de repetición, pero no podemos
-- porque la función mapeo1 recibe un parametro Char y devuelve otro Char,
-- y la función que necesitamos debe recibir un parametro Int y devolver un
-- Char.  Entonces, lo que necesitamos hacer es parametrizar la función de
-- repetición para cualquier tipo.
mapeo :: (a -> b) -> [a] -> [b]
mapeo f [] = []
mapeo f (e:es) = (f e) : mapeo f es

-- probando mapeo
aMayuscula2 = mapeo toUpper "Carlos"
aMiniscula2 = mapeo toLower "Carlos"

determinarTipoNumero :: Int -> Char
determinarTipoNumero n = if (even n) then 'P'
                                     else 'I'
parImpar2 = mapeo determinarTipoNumero [4,3,2,1]

-- La función 'mapeo' no es necesario crearlo ya que es una función
-- estandar de Haskell, y se llama 'map'.
-- La función 'map', al igual que 'mapeo', abstrae la repetición y
-- trabaja con tipos genericos.
--
-- De entrada, este caso es trivial, veamos una caso más practico.
--
-- El día del trabajador, el presidente declaro un 8.5% de incremento en el
-- sueldo, y pues tenemos un listado de empleados que tienen un suelfo
-- fijo, a quienes debemos incrementarles el 8.5% a su sueldo.

data Empleado
    = Empleado {nombre :: String, sueldo :: Float}
    deriving Show

listaEmpleados :: [Empleado]
listaEmpleados
    = [Empleado "Pedro" 500, Empleado "Juan" 600, Empleado "Jacobo" 700]

incremento :: Float
incremento = 8.5

incrementarSueldo :: Empleado -> Empleado
incrementarSueldo empleado
    = empleado {sueldo = nuevoSueldo}
    where nuevoSueldo = let sueldo' = sueldo empleado
                            inc     = (incremento / 100.0) * sueldo'
                        in sueldo' + inc
                        
aplicarIncremento = map incrementarSueldo listaEmpleados


