package ec.edu.utpl.cc.poo.semana15.controller;

import ec.edu.utpl.cc.poo.semana15.conecciones.DBConfig;
import ec.edu.utpl.cc.poo.semana15.conecciones.JDBIDaoImpl;
import ec.edu.utpl.cc.poo.semana15.model.Estudiante;

import java.util.List;
import java.util.Scanner;

public class RegistroMain { public static void main(String[] args) {
    Scanner lector = new Scanner(System.in);
    char opc;
    String menu = """
                C. Registrar estudiante.
                R. Mostrar estudiante.
                U. Actualizar estudiante.
                D. Borrar estudiante.
                A. Mostrar todos.
                S. Salir.
                """;
    DBConfig dbConfig = new DBConfig(" jdbc:h2:C:\\Users\\SALA A\\h2BBD", "sa", "");
    JDBIDaoImpl jdbiDao = new JDBIDaoImpl(dbConfig);
    do {
        System.out.print(menu);
        System.out.print("Elección: ");
        opc = lector.next().toUpperCase().charAt(0);
        switch (opc) {
            case 'C' : crearEstudiante(jdbiDao, lector);
                break;
            case 'R' :
                leerEstudiante(jdbiDao, lector);
                break;
            case 'U':
                actualizarEstudiante(jdbiDao, lector);
                break;
            case 'D':
                System.out.println();
                break;
            case 'A':
                System.out.println("Listado de estudiantes");
                listarTodos(jdbiDao, lector);
                break;
            case 'S':
                System.out.println("Adiós!!!\n");
                break;
            default:
                System.out.println("Selección no válida\n");
        }
    } while (opc != 'S');
    lector.close();
}
    private static void crearEstudiante(JDBIDaoImpl jdbiDao, Scanner lector) {
        System.out.println("Registro de estudiante");
        Estudiante estudiante = new Estudiante();
        System.out.print("Cédula: ");
        estudiante.setCedula(lector.next());
        System.out.print("Apellido: ");
        estudiante.setApellido(lector.next());
        System.out.print("Nombre: ");
        estudiante.setNombre(lector.next());
        System.out.print("Email: ");
        estudiante.setEmail(lector.next());
        System.out.print("Edad: ");
        estudiante.setEdad(lector.nextInt());
        System.out.print("Peso: ");
        estudiante.setPeso(lector.nextDouble());

        jdbiDao.create(estudiante);
    }

    private static void leerEstudiante(JDBIDaoImpl jdbiDao, Scanner lector) {
        String outputFrmt = """
                Cédula: %s
                Apellido: %s
                Nombre: %s
                Email: %s
                Edad: %d
                Peso: %.2f
                """;
        String cedulaABuscar;
        System.out.print("Ingrese # cédula: ");
        cedulaABuscar = lector.next();
        Estudiante encontrado = jdbiDao.read(cedulaABuscar);
        System.out.printf(outputFrmt,
                encontrado.getCedula(),
                encontrado.getApellido(),
                encontrado.getNombre(),
                encontrado.getEmail(),
                encontrado.getEdad(),
                encontrado.getPeso());
    }

    private static void actualizarEstudiante(JDBIDaoImpl jdbiDao, Scanner lector) {
        System.out.println("Seleccione un estudiante");
        int id;
        listarTodos(jdbiDao, lector);
        System.out.print("Id del estudiante seleccionado: ");
        id = lector.nextInt();
        Estudiante estudiante = new Estudiante();
        System.out.print("Cédula: ");
        estudiante.setCedula(lector.next());
        System.out.print("Apellido: ");
        estudiante.setApellido(lector.next());
        System.out.print("Nombre: ");
        estudiante.setNombre(lector.next());
        System.out.print("Email: ");
        estudiante.setEmail(lector.next());
        System.out.print("Edad: ");
        estudiante.setEdad(lector.nextInt());
        System.out.print("Peso: ");
        estudiante.setPeso(lector.nextDouble());
        estudiante.setId(id);
        jdbiDao.update(estudiante);
    }

    private static void borrarEstudiante(JDBIDaoImpl jdbiDao, Scanner lector) {
        System.out.println("Seleccione un estudiante");
        int id;
        listarTodos(jdbiDao, lector);
        System.out.print("Id del estudiante seleccionado: ");
        id = lector.nextInt();
        jdbiDao.delete(id);
    }

    private static void listarTodos(JDBIDaoImpl jdbiDao, Scanner lector) {
        List<Estudiante> estudiantes = jdbiDao.readAllData();
        for (int i = 0; i < estudiantes.size(); i ++ ) {
            Estudiante estudiante = estudiantes.get(i);
            System.out.printf("%d\t%s\t%s\t%s\t%s\t%d\t%f\n",
                    i + 1,
                    estudiante.getCedula(),
                    estudiante.getApellido(),
                    estudiante.getNombre(),
                    estudiante.getEmail(),
                    estudiante.getEdad(),
                    estudiante.getPeso());
        }
    }
}
