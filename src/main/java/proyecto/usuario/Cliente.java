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
        this.celular = celular;
    }
    
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return super.toString() + "|" + this.celular + "|" + this.direccion;
    }

}
