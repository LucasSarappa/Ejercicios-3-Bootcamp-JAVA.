// 1) Mostrar los numeros del 1 al 20 con un retardo de 3 segundos entre cada numero

public class Ejercicio1Thread {
    public static void main(String[] args) {

        // Creo un objeto Runnable que muestra los números del 1 al 20
        Runnable mostrarNumeros = () -> {
            for (int i = 1; i <= 20; i++) {
                System.out.println(i);
                try {
                    Thread.sleep(3000); // Retardo de 3 segundos (3000 milisegundos)
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        };

        // Creo un Thread y ejecuto el Runnable
        Thread thread = new Thread(mostrarNumeros);
        thread.start();
    }
}
