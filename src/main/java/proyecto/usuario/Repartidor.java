package main.java.proyecto.usuario;

public class Repartidor extends Usuario{
    private String empresa;
    public Repartidor(String codigoUnico, String cedula, String nombres, String apellidos, String userName, String contrasena, String correo, String empresa) {
        super(codigoUnico, cedula, nombres, apellidos, userName, contrasena, correo);
        this.empresa = empresa;
    }


    // Getters y Setters
    public String getEmpresa() {
        return empresa;
    }
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
}
