package proyecto;

/**
 * Enumeración que define las categorías de productos disponibles en el sistema de delivery.
 * Cada categoría tiene una descripción asociada para mostrar al usuario.
 * 
 */
public enum TipoProducto {
    /** Categoría de productos de vestimenta y accesorios */
    Ropa("Ropa"),
    /** Categoría de productos tecnológicos y electrónicos */
    Tecnología("Tecnología"),
    /** Categoría de productos deportivos y equipamiento atlético */
    Deportes("Deportes"),
    /** Categoría de productos para el hogar y decoración */
    Hogar("Hogar");

    private final String descripcion;
    
    /**
     * Constructor del enum TipoProducto.
     * 
     * @param descripcion Descripción legible de la categoría
     */
    TipoProducto(String descripcion) {
        this.descripcion = descripcion;
    }
    
    /**
     * Obtiene la descripción de la categoría.
     * 
     * @return La descripción de la categoría
     */
    public String getDescripcion() {
        return descripcion;
    }
    
    @Override
    public String toString() {
        return descripcion;
    }
}
