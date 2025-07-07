package proyecto.usuario;

/**
 * Clase abstracta que representa un usuario del sistema de delivery.
 * Contiene la información básica común a todos los tipos de usuarios.
 */
public abstract class Usuario {
    protected String codigoUnico;
    protected String cedula;
    protected String nombres;
    protected String apellidos;
    protected String userName;
    protected String contrasena;
    protected String correo;

    /**
     * Constructor de la clase Usuario.
     * 
     * @param codigoUnico Código único identificador del usuario
     * @param cedula Número de cédula del usuario
     * @param nombres Nombres del usuario
     * @param apellidos Apellidos del usuario
     * @param userName Nombre de usuario para login
     * @param contrasena Contraseña del usuario
     * @param correo Correo electrónico del usuario
     */
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
    /**
     * Obtiene el código único del usuario.
     * 
     * @return El código único del usuario
     */
    public String getCodigoUnico() {
        return codigoUnico;
    }
    
    /**
     * Establece el código único del usuario.
     * 
     * @param codigoUnico El nuevo código único del usuario
     */
    public void setCodigoUnico(String codigoUnico) {
        if (codigoUnico != null && !codigoUnico.trim().isEmpty()) {
            this.codigoUnico = codigoUnico.trim();
        }
    }
    
    /**
     * Obtiene la cédula del usuario.
     * 
     * @return La cédula del usuario
     */
    public String getCedula() {
        return cedula;
    }
    
    /**
     * Establece la cédula del usuario.
     * 
     * @param cedula La nueva cédula del usuario
     */
    public void setCedula(String cedula) {
        if (cedula != null && !cedula.trim().isEmpty()) {
            this.cedula = cedula.trim();
        }
    }
    
    /**
     * Obtiene los nombres del usuario.
     * 
     * @return Los nombres del usuario
     */
    public String getNombres() {
        return nombres;
    }
    
    /**
     * Establece los nombres del usuario.
     * 
     * @param nombres Los nuevos nombres del usuario
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    
    /**
     * Obtiene los apellidos del usuario.
     * 
     * @return Los apellidos del usuario
     */
    public String getApellidos() {
        return apellidos;
    }
    
    /**
     * Establece los apellidos del usuario.
     * 
     * @param apellidos Los nuevos apellidos del usuario
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    /**
     * Obtiene el nombre de usuario para login.
     * 
     * @return El nombre de usuario
     */
    public String getUserName() {
        return userName;
    }
    
    /**
     * Establece el nombre de usuario para login.
     * 
     * @param userName El nuevo nombre de usuario
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    /**
     * Obtiene la contraseña del usuario.
     * 
     * @return La contraseña del usuario
     */
    public String getContrasena() {
        return contrasena;
    }
    
    /**
     * Establece la contraseña del usuario.
     * 
     * @param contrasena La nueva contraseña del usuario
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    /**
     * Obtiene el correo electrónico del usuario.
     * 
     * @return El correo electrónico del usuario
     */
    public String getCorreo() {
        return correo;
    }
    
    /**
     * Establece el correo electrónico del usuario.
     * 
     * @param correo El nuevo correo electrónico del usuario
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Devuelve una representación en cadena del usuario.
     * 
     * @return Cadena con todos los datos del usuario separados por |
     */
    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s|%s|%s|%s", 
                codigoUnico, cedula, nombres, apellidos, userName, contrasena, correo);
    }
<<<<<<< HEAD
=======
    
    /**
     * Compara si dos usuarios son iguales basándose en su código único.
     * 
     * @param obj El objeto a comparar
     * @return true si los usuarios son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Usuario usuario = (Usuario) obj;
        return codigoUnico != null ? codigoUnico.equals(usuario.codigoUnico) : usuario.codigoUnico == null;
    }
    

    
    /**
     * Obtiene el nombre completo del usuario.
     * 
     * @return El nombre completo concatenando nombres y apellidos
     */
    public String getNombreCompleto() {
        return nombres + " " + apellidos;
    }
    
    /**
     * Valida si los datos básicos del usuario están completos.
     * 
     * @return true si todos los campos obligatorios están completos, false en caso contrario
     */
    public boolean datosCompletos() {
        return codigoUnico != null && !codigoUnico.isEmpty() &&
               cedula != null && !cedula.isEmpty() &&
               nombres != null && !nombres.isEmpty() &&
               apellidos != null && !apellidos.isEmpty() &&
               userName != null && !userName.isEmpty() &&
               contrasena != null && !contrasena.isEmpty() &&
               correo != null && !correo.isEmpty();
    }
>>>>>>> 99659700cddf95e82a7b1217313e68afdafe0297

}
