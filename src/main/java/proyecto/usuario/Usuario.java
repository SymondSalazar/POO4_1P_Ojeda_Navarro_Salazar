package proyecto.usuario;

public abstract class Usuario {
    protected String codigoUnico;
    protected String cedula;
    protected String nombres;
    protected String apellidos;
    protected String userName;
    protected String contrasena;
    protected String correo;

    public Usuario(String codigoUnico, String cedula, String nombres, String apellidos, String userName, String contrasena, String correo) {
        this.codigoUnico = codigoUnico;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.userName = userName;
        this.contrasena = contrasena;
        this.correo = correo;
    }

    //Getters y Setters
    public String getCodigoUnico() {
        return codigoUnico;
    }
    public void setCodigoUnico(String codigoUnico) {
        if (codigoUnico != null && !codigoUnico.trim().isEmpty()) {
            this.codigoUnico = codigoUnico.trim();
        }
    }
    public String getCedula() {
        return cedula;
    }
    public void setCedula(String cedula) {
        if (cedula != null && !cedula.trim().isEmpty()) {
            this.cedula = cedula.trim();
        }
    }
    public String getNombres() {
        return nombres;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.codigoUnico).append("|")
          .append(this.cedula).append("|")
          .append(this.nombres).append("|")
          .append(this.apellidos).append("|")
          .append(this.userName).append("|")
          .append(this.contrasena).append("|")
          .append(this.correo);
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Usuario usuario = (Usuario) obj;
        return codigoUnico != null ? codigoUnico.equals(usuario.codigoUnico) : usuario.codigoUnico == null;
    }

}
