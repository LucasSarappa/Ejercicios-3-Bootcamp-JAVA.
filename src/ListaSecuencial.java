import java.util.List;
import java.util.stream.IntStream;

public class ListaSecuencial {
    public void main(String[] args) {
        List<Integer> numerosParesEntre10y20 = IntStream
                .rangeClosed(10, 20) // Creo un stream de números desde 10 hasta 20 (inclusive)
                .filter(n -> n % 2 == 0) // Filtro solo los números pares
                .boxed() // Convierto los números enteros en objetos Integer
                .toList(); // Junto los números en una lista

        System.out.println("Numeros pares entre 10 y 20: " + numerosParesEntre10y20);
    }


}



