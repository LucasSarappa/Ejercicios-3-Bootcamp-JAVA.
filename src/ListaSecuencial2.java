import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Arrays;
import java.util.function.IntPredicate;


public class ListaSecuencial2 {
    public static void main(String[] args) {

//---List<Integer> obtenerListaSecuencialCondicionada(Integer desde, Integer hasta, Predicate<Integer> condicion)
        List<Predicate<Integer>> condicion = new ArrayList<>();
        condicion.add(n -> n % 2 == 0);

        List<Integer> listaSecuencial = obtenerListaSecuencialCon1Condicion(10, 20, condicion);

        System.out.println("Lista secuencial con 1 condicional (numeros pares entre 10 y 20): " + listaSecuencial);

//----List<Integer> obtenerListaSecuencialCondicionadaMultiple(Integer desde, Integer hasta, List<Predicate<Integer>> condiciones) -----//

        List<Predicate<Integer>> condiciones2 = new ArrayList<>();
        condiciones2.add(n -> n % 2 == 0);
        condiciones2.add(n -> n % 3 == 0);

        List<Integer> listaSecuencial2 = obtenerListaSecuencialCondicionadaMultiple1(10, 20, condiciones2);

        System.out.println("Lista secuencial con multiples condicionales (numeros pares y divisores de 3 entre 10 y 20) usando ist<Predicate<Integer>> condiciones): " + listaSecuencial2);

//----List<Integer> obtenerListaSecuencialCondicionadaMultiple(Integer desde, Integer hasta, Predicate<Integer>[] condiciones) --------//

        Predicate<Integer> esPar = numero -> numero % 2 == 0;
        Predicate<Integer> esMultiploDe3 = numero -> numero % 3 == 0;

        Predicate[] condiciones = new Predicate[]{esPar, esMultiploDe3};

        List listaSecuencial4 = obtenerListaSecuencialCondicionadaMultiple2(10, 20, condiciones);

        System.out.println("Lista secuencial con multiples condicionales (numeros pares y divisores de 3 entre 10 y 20) usando Predicate<Integer>[] condiciones: " + listaSecuencial4);

//List<Integer> obtenerListaSecuencialCondicionadaMultiple(Integer desde, Integer hasta, Predicate<Integer> ... condicion)

        IntPredicate condicionPar = n -> n % 2 == 0;
        IntPredicate condicionDivisiblePor3 = n -> n % 3 == 0;

        List<Integer> listaSecuencial3 = obtenerListaSecuencialCondicionadaMultiple3(10, 20, condicionPar, condicionDivisiblePor3);

        System.out.println("Lista secuencial con multiples condicionales (numeros pares y divisores de 3 entre 10 y 20) usando Predicate<Integer> ... condicion: " + listaSecuencial3);


//List<Integer> obtenerListaSecuencialCondicionadaMultiple(Integer desde, Integer hasta, condicion)
        /* En el caso de que alguno de los 3 parametros (desde, hasta o condicion) fuesen null, se generaria un problema en la ejecucion del metodo.
        Para evitarlo, se podria lanzar una excepcion
        */

    }




    public static List<Integer> obtenerListaSecuencialCon1Condicion(Integer desde, Integer hasta, List<Predicate<Integer>> condicion) {
        return IntStream.rangeClosed(desde, hasta)
                .filter(n -> condicion.stream().allMatch(cond -> cond.test(n)))
                .boxed()
                .collect(Collectors.toList());
    }


    public static List<Integer> obtenerListaSecuencialCondicionadaMultiple1(Integer desde, Integer hasta, List<Predicate<Integer>> condiciones3) {
        return IntStream.rangeClosed(desde, hasta)
                .filter(n -> {
                    for (Predicate<Integer> condicion : condiciones3) {
                        if (!condicion.test(n)) {
                            return false;
                        }
                    }
                    return true;
                })
                .boxed()
                .collect(Collectors.toList());
    }

    public static List<Integer> obtenerListaSecuencialCondicionadaMultiple2(Integer desde, Integer hasta, Predicate<Integer>[] condiciones) {
        List<Integer> resultado = new ArrayList<>();

        for (int i = desde; i <= hasta; i++) {
            boolean cumpleTodasLasCondiciones = true;

            for (Predicate<Integer> condicion : condiciones) {
                if (!condicion.test(i)) {
                    cumpleTodasLasCondiciones = false;
                    break;
                }
            }

            if (cumpleTodasLasCondiciones) {
                resultado.add(i);
            }
        }

        return resultado;
    }

    public static List<Integer> obtenerListaSecuencialCondicionadaMultiple3(int desde, int hasta, IntPredicate... condiciones) {
        IntPredicate condicionFinal = Arrays.stream(condiciones)
                .reduce(IntPredicate::and)
                .orElse(n -> true);

        return IntStream.rangeClosed(desde, hasta)
                .filter(condicionFinal)
                .boxed()
                .collect(Collectors.toList());
    }
}