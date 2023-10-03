import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Scanner;

public class Alumno {
    private int id;
    private String dni;
    private String nombre;
    private String apellido;
    private String curso;
    private double nota;
    private int edad;

    public Alumno(int id, String dni, String nombre, String apellido, String curso, double nota, int edad) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.curso = curso;
        this.nota = nota;
        this.edad = edad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", curso='" + curso + '\'' +
                ", nota=" + nota +
                ", edad=" + edad +
                '}';
    }


    public static void main(String[] args) {
        List<Alumno> alumnos = List.of(
                new Alumno(1, "12345678A", "Lionel", "Messi", "Argentina", 8, 36),
                new Alumno(2, "98765432B", "Carlos", "Tevez", "DT", 7, 39),
                new Alumno(3, "56789012C", "Diego", "Maradona", "Argentina", 9, 63),
                new Alumno(4, "23456789D", "Juan Román", "Riquelme", "Boca", 6, 45),
                new Alumno(5, "34567890E", "Sergio", "Aguero", "Argentina", 8, 35),
                new Alumno(6, "45678901F", "Gabriel", "Batistuta", "Argentina", 7, 54),
                new Alumno(7, "56789012G", "Diego", "Simeone", "DT", 8, 17),
                new Alumno(8, "67890123H", "Pablo", "Aimar", "River", 9, 43),
                new Alumno(9, "78901234I", "Esteban", "Cambiasso", "Argentina", 6, 43),
                new Alumno(10, "95012345J", "Javier", "Zanetti", "Argentina", 7, 50)
        );

        Scanner scanner = new Scanner(System.in);

        while (true) {
            mostrarMenu();
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    mostrarTodosLosAlumnos(alumnos);
                    break;
                case 2:
                    mostrarAlumnosOrdenadosPorNota(alumnos);
                    break;
                case 3:
                    mostrarAlumnosPorCaracter(alumnos);
                    break;
                case 4:
                    edadTotalAlumnos(alumnos);
                    break;
                case 5:
                    obtenerMapaNotasAlumnos(alumnos);
                    break;
                case 6:
                    System.out.println("Saliendo del programa.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    public static void mostrarMenu() {
        System.out.println("                                                         ");
        System.out.println("---------------Menú:----------------");
        System.out.println("                                                         ");
        System.out.println("1. Mostrar todos los alumnos");
        System.out.println("2. Ordenar por nota de menor a mayor");
        System.out.println("3. Mostrar todos los alumnos cuyo nombre empieza con un caracter");
        System.out.println("4. Mostrar la edad total de los alumnos");
        System.out.println("5. Mostrar listado de notas con los alumnos que las tienen");
        System.out.print("Elija una opción: ");
    }

    public static void mostrarTodosLosAlumnos(List<Alumno> alumnos) {
        System.out.println("Todos los alumnos:");
        alumnos.forEach(System.out::println);
    }

    public static void mostrarAlumnosOrdenadosPorNota(List<Alumno> alumnos) {
        List<Alumno> alumnosOrdenados = alumnos.stream()
                .sorted(Comparator.comparing(Alumno::getNota))
                .toList();
        System.out.println("Alumnos ordenados por nota de menor a mayor:");
        alumnosOrdenados.forEach(System.out::println);
    }

    public static void mostrarAlumnosPorCaracter (List<Alumno> alumnos){
        System.out.print("Ingrese el caracter inicial del nombre: ");
        Scanner scanner = new Scanner(System.in);
        char caracter = scanner.next().charAt(0);

        List <Alumno> alumnosFiltrados = alumnos.stream()
                .filter(alumno -> alumno.getNombre().charAt(0) == caracter)
                .toList();

        System.out.println("Alumnos cuyo nombre empieza con " + caracter + ":");
        alumnosFiltrados.forEach(System.out::println);
    }

    public static void edadTotalAlumnos (List <Alumno> alumnos){

        int sumaEdad = alumnos.stream()
                .mapToInt(Alumno::getEdad)
                .sum();

        System.out.println("Suma de edades de todos los alumnos: " + sumaEdad);
    }

    public static void obtenerMapaNotasAlumnos(List<Alumno> alumnos) {

        Map<Double, List<Alumno>> mapaNotasAlumnos = alumnos.stream()
                .collect(Collectors.groupingBy(Alumno::getNota));

        System.out.println("---Mapa de notas y alumnos:---");
        System.out.println("                               ");

        mapaNotasAlumnos.forEach((nota, listaAlumnos) -> {
            System.out.println("-->Nota: " + nota);
            listaAlumnos.forEach(alumno -> System.out.println("   " + alumno));
        });
    }
}





