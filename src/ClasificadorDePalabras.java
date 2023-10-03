import java.util.*;
import java.util.stream.Collectors;
//para este ejercicio me guie mirando un ejercicio de internet ya que no se me ocurria que aplicar en la parte de agrupar las palabras por longitud :(

public class ClasificadorDePalabras {
    public static void main(String[] args) {
        // Texto de ejemplo:
        String texto = "El reglamento del fútbol, también conocido como las reglas de juego a nivel de la FIFA, " +
                "es el conjunto de reglas que rigen el fútbol o balompié en todo el mundo. " +
                "Los cambios asociados en las mismas están a cargo de la International Football Association Board, " +
                "la cual está conformada por la FIFA y las cuatro asociaciones balompédicas del Reino Unido: " +
                "Asociación Inglesa de Fútbol, Asociación Escocesa de Fútbol, Asociación de Fútbol de Gales y Asociación Irlandesa de Fútbol. " +
                "La última versión del reglamento fue publicada el 1 de junio de 2016 y entró en vigor el 1 de junio de 2016.";

        // Le saco los símbolos de puntuación utilizando expresiones regulares y divido el texto en palabras usando espacios en blanco
        String[] palabras = texto.replaceAll("[,.;]", "").split("\\s+");

        // Uso Stream para procesar las palabras resultantes. Con distinct() elimino las palabras duplicadas
        // y uso Collectors.groupingBy para agrupar las palabras por longitud en un mapa:
        // Me quedaria:
        // 1 -> [a, o, ...]
        // 2 -> [el, de, ...]

        Map<Integer, List<String>> palabrasPorLongitud = Arrays.stream(palabras)
                .distinct()
                .collect(Collectors.groupingBy(String::length, Collectors.toList()));

        // Ordeno el mapa por longitud de palabra
        TreeMap<Integer, List<String>> palabrasOrdenadasPorLongitud = new TreeMap<>(palabrasPorLongitud);

        // Imprimimos las palabras agrupadas por longitud
        palabrasOrdenadasPorLongitud.forEach((longitud, lista) -> {
            System.out.println("Palabras con " +longitud + " letra" + (longitud != 1 ? "s" : "") + " [" + lista.size() + " palabra encontrada]:");
            lista.forEach(System.out::println);
        });
    }
}
