package proyecto.utils;

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Utilidad para manejo centralizado de entrada de datos
 * Evita problemas con múltiples instancias de Scanner
 */
public class InputUtils {
    private static Scanner scanner = new Scanner(System.in);
    
    // Constructor privado para evitar instanciacion
    private InputUtils() {}
    
    /**
     * Lee una línea de texto del usuario
     */
    public static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }
    
    /**
     * Lee un número entero del usuario con validación
     */
    public static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                int valor = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número entero válido.");
                scanner.nextLine(); // Limpiar el buffer
            }
        }
    }
    
    /**
     * Lee un número entero positivo del usuario
     */
    public static int leerEnteroPositivo(String mensaje) {
        int valor;
        do {
            valor = leerEntero(mensaje);
            if (valor <= 0) {
                System.out.println("Error: El valor debe ser mayor que 0.");
            }
        } while (valor <= 0);
        return valor;
    }
    
    /**
     * Lee un número decimal del usuario con validación
     */
    public static double leerDecimal(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                double valor = scanner.nextDouble();
                scanner.nextLine(); // Consumir el salto de línea
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número válido.");
                scanner.nextLine(); // Limpiar el buffer
            }
        }
    }
    
    /**
     * Lee una confirmación S/N del usuario
     */
    public static boolean leerConfirmacion(String mensaje) {
        String respuesta;
        do {
            respuesta = leerTexto(mensaje + " (S/N): ").toUpperCase();
            if (!respuesta.equals("S") && !respuesta.equals("N")) {
                System.out.println("Error: Debe responder S o N.");
            }
        } while (!respuesta.equals("S") && !respuesta.equals("N"));
        return respuesta.equals("S");
    }
    
    /**
     * Valida que un número de tarjeta contenga solo dígitos
     */
    public static String leerNumeroTarjeta(String mensaje) {
        String numeroTarjeta;
        do {
            numeroTarjeta = leerTexto(mensaje);
            if (!numeroTarjeta.matches("\\d{13,19}")) {
                System.out.println("Error: El número de tarjeta debe contener entre 13 y 19 dígitos.");
            }
        } while (!numeroTarjeta.matches("\\d{13,19}"));
        return numeroTarjeta;
    }
    
    /**
     * Lee un texto no vacío del usuario
     */
    public static String leerTextoNoVacio(String mensaje) {
        String texto;
        do {
            texto = leerTexto(mensaje);
            if (texto.isEmpty()) {
                System.out.println("Error: El texto no puede estar vacío.");
            }
        } while (texto.isEmpty());
        return texto;
    }
    
    /**
     * Muestra un menú y lee la opción seleccionada
     */
    public static int leerOpcionMenu(String[] opciones) {
        System.out.println("\n--- MENÚ ---");
        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i + 1) + ". " + opciones[i]);
        }
        
        int opcion;
        do {
            opcion = leerEntero("Seleccione una opción (1-" + opciones.length + "): ");
            if (opcion < 1 || opcion > opciones.length) {
                System.out.println("Error: Opción inválida. Intente nuevamente.");
            }
        } while (opcion < 1 || opcion > opciones.length);
        
        return opcion;
    }
    
    /**
     * Pausa la ejecución hasta que el usuario presione Enter
     */
    public static void pausar() {
        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
    
    /**
     * Cierra el scanner (solo debe llamarse al final del programa)
     */
    public static void cerrar() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
