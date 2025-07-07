package proyecto.usuario;

/**
 * Representa un repartidor en el sistema de delivery.
 * Extiende la clase Usuario agregando información específica como la empresa a la que pertenece.
 */
public class Repartidor extends Usuario{
    private String empresa;
    
    /**
     * Constructor de la clase Repartidor.
     * 
     * @param codigoUnico Código único identificador del repartidor
     * @param cedula Número de cédula del repartidor
     * @param nombres Nombres del repartidor
     * @param apellidos Apellidos del repartidor
     * @param userName Nombre de usuario para login
     * @param contrasena Contraseña del repartidor
     * @param correo Correo electrónico del repartidor
     * @param empresa Empresa de delivery a la que pertenece el repartidor
     */
    public Repartidor(String codigoUnico, String cedula, String nombres, String apellidos, String userName, String contrasena, String correo, String empresa) {
        super(codigoUnico, cedula, nombres, apellidos, userName, contrasena, correo);
        this.empresa = empresa;
    }

    /**
     * Obtiene la empresa de delivery a la que pertenece el repartidor.
     * 
     * @return El nombre de la empresa del repartidor
     */
    public String getEmpresa() {
        return empresa;
    }
    
    /**
     * Establece la empresa de delivery del repartidor con validación.
     * 
     * @param empresa El nombre de la nueva empresa (no puede estar vacía)
     */
    public void setEmpresa(String empresa) {
        if (empresa != null && !empresa.trim().isEmpty()) {
            this.empresa = empresa.trim();
        }
    }

    /**
     * Devuelve una representación en cadena del repartidor.
     * Incluye todos los datos del usuario padre más la empresa.
     * 
     * @return Cadena con todos los datos del repartidor separados por |
     */
    @Override
    public String toString() {
        return new StringBuilder(super.toString())
                .append("|").append(this.empresa)
                .toString();
    }
}
