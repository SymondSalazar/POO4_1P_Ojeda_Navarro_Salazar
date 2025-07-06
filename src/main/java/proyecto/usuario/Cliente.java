package proyecto.usuario;

public class Cliente extends Usuario {
    private String celular;
    private String direccion;

    public Cliente(String codigoUnico, String cedula, String nombres, String apellidos, String userName, String contrasena, String correo, String celular, String direccion) {
        super(codigoUnico, cedula, nombres, apellidos, userName, contrasena, correo);
        this.celular = celular;
        this.direccion = direccion;
    }

    // Getters y Setters
    public String getCelular() {
        return celular;
    }
    public void setCelular(String celular) {
        // Validación básica de formato de teléfono
        if (celular != null && celular.trim().length() >= 10) {
            this.celular = celular.trim();
        }
    }
    
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        if (direccion != null && !direccion.trim().isEmpty()) {
            this.direccion = direccion.trim();
        }
    }

    @Override
    public String toString() {
        return new StringBuilder(super.toString())
                .append("|").append(this.celular)
                .append("|").append(this.direccion)
                .toString();
    }

}
