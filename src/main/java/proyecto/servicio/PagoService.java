package proyecto.servicio;

import proyecto.utils.InputUtils;

/**
 * Servicio simple para procesar pagos
 */
public class PagoService {
    
    /**
     * Procesa el pago de una compra de forma simplificada
     */
    public boolean procesarPago(double monto) {
        System.out.println("\n--- PROCESAMIENTO DE PAGO ---");
        System.out.println("Monto a pagar: $" + String.format("%.2f", monto));
        
        // Solicitar número de tarjeta (validación básica)
        String numeroTarjeta = InputUtils.leerTexto("Ingrese su número de tarjeta: ");
        
        // Validación simple: solo verificar que tenga números
        if (esNumeroTarjetaValido(numeroTarjeta)) {
            System.out.println("Procesando pago...");
            System.out.println("✓ Pago procesado exitosamente");
            return true;
        } else {
            System.out.println("✗ Número de tarjeta inválido");
            return false;
        }
    }
    
    /**
     * Validación simple del número de tarjeta
     */
    private boolean esNumeroTarjetaValido(String numeroTarjeta) {
        // Solo verificar que tenga entre 13 y 19 dígitos
        return numeroTarjeta != null && 
               numeroTarjeta.matches("\\d{13,19}");
    }
    
    /**
     * Solicita confirmación del pago al usuario
     */
    public boolean confirmarPago(double monto) {
        System.out.println("\nMonto total: $" + String.format("%.2f", monto));
        return InputUtils.leerConfirmacion("¿Confirmar el pago?");
    }
    
    /**
     * Muestra el resumen del pago realizado
     */
    public void mostrarResumenPago(double monto) {
        System.out.println("\n--- RESUMEN DEL PAGO ---");
        System.out.println("Monto pagado: $" + String.format("%.2f", monto));
        System.out.println("Estado: APROBADO");
        System.out.println("Gracias por su compra!");
    }
}
