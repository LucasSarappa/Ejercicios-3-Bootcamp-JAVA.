public class Ejercicio2Thread {
    public static void main(String[] args) {

        // Creo un objeto Runnable que muestra los números pares del 1 al 10
        Runnable hiloPares = () -> {

            int contPares = 0;
            for (int i = 1; i <= 10; i++) {
                if(i%2==0){
                    System.out.println("Numero par: " + i);
                    contPares += i;
                }
                try {
                    Thread.sleep(2000);
                }catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Suma total de pares: " + contPares);
        };

        Runnable hiloImpares = () -> {
            int contImpares = 0;
            for (int i = 1; i <= 10; i++) {
                if(i%2!=0){
                    System.out.println("Numero impar: " + i);
                    contImpares += i;
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Suma total de impares: " + contImpares);
        };

        // Creo 2 Threads y ejecuto los Runnable de c/u
        Thread thread1 = new Thread(hiloImpares);
        Thread thread2 = new Thread(hiloPares);
        thread1.start();
        thread2.start();
    }
}