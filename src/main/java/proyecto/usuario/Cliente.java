package proyecto.usuario;

/**
 * Representa un cliente en el sistema de delivery.
 * Extiende la clase Usuario agregando información específica como celular y dirección.
 * 
 */
public class Cliente extends Usuario {
    private String celular;
    private String direccion;

    /**
     * Constructor de la clase Cliente.
     * 
     * @param codigoUnico Código único identificador del cliente
     * @param cedula Número de cédula del cliente
     * @param nombres Nombres del cliente
     * @param apellidos Apellidos del cliente
     * @param userName Nombre de usuario para login
     * @param contrasena Contraseña del cliente
     * @param correo Correo electrónico del cliente
     * @param celular Número de celular del cliente
     * @param direccion Dirección de entrega del cliente
     */
    public Cliente(String codigoUnico, String cedula, String nombres, String apellidos, 
                   String userName, String contrasena, String correo, String celular, String direccion) {
        super(codigoUnico, cedula, nombres, apellidos, userName, contrasena, correo);
        setCelular(celular);
        setDireccion(direccion);
    }

    /**
     * Obtiene el número de celular del cliente.
     * 
     * @return El número de celular del cliente
     */
    public String getCelular() {
        return celular;
    }
    
    /**
     * Obtiene la dirección de entrega del cliente.
     * 
     * @return La dirección de entrega del cliente
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece el número de celular del cliente con validación.
     * Si el celular no es válido, se asigna un valor por defecto.
     * 
     * @param celular El nuevo número de celular (debe tener al menos 10 dígitos)
     */
    public void setCelular(String celular) {
        if (celular != null && celular.trim().length() >= 10) {
            this.celular = celular.trim();
        } else {
            System.out.println("Advertencia: El celular debe tener al menos 10 dígitos. Se asignará valor por defecto.");
            this.celular = "0000000000"; // Valor por defecto
        }
    }
    
    /**
     * Establece la dirección de entrega del cliente con validación.
     * Si la dirección está vacía, se asigna un valor por defecto.
     * 
     * @param direccion La nueva dirección de entrega
     */
    public void setDireccion(String direccion) {
        if (direccion != null && !direccion.trim().isEmpty()) {
            this.direccion = direccion.trim();
        } else {
            System.out.println("Advertencia: La dirección no puede estar vacía. Se asignará valor por defecto.");
            this.direccion = ""; // Valor por defecto
        }
    }

    /**
     * Obtiene el nombre completo del cliente.
     * 
     * @return El nombre completo concatenando nombres y apellidos
     */
    public String getNombreCompleto() {
        return getNombres() + " " + getApellidos();
    }
    
    /**
     * Verifica si los datos de contacto del cliente están completos.
     * 
     * @return true si tanto el celular como la dirección están completos, false en caso contrario
     */
    public boolean tieneContactoCompleto() {
        return celular != null && !celular.isEmpty() && 
               direccion != null && !direccion.isEmpty();
    }

    /**
     * Devuelve una representación en cadena del cliente.
     * Incluye todos los datos del usuario padre más celular y dirección.
     * 
     * @return Cadena con todos los datos del cliente separados por |
     */
    @Override
    public String toString() {
        return String.format("%s|%s|%s", super.toString(), celular, direccion);
    }
    
    /**
     * Compara si dos clientes son iguales basándose en los datos del usuario padre y el celular.
     * 
     * @param obj El objeto a comparar
     * @return true si los clientes son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof Cliente)) return false;
        Cliente cliente = (Cliente) obj;
        return celular != null ? celular.equals(cliente.celular) : cliente.celular == null;
    }
    

}


