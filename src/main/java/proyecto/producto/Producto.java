package proyecto.producto;

import proyecto.TipoProducto;

/**
 * Representa un producto en el sistema de delivery.
 * Contiene información sobre categoría, código, nombre, precio y stock del producto.
 */
public class Producto {
    private TipoProducto categoria;
    private String codigo;
    private String nombre;
    private double precio;
    private int stock;

    /**
     * Constructor principal de la clase Producto.
     * 
     * @param categoria Categoría del producto (tipo de producto)
     * @param codigo Código único identificador del producto
     * @param nombre Nombre descriptivo del producto
     * @param precio Precio unitario del producto (mínimo 1.0)
     * @param stock Cantidad disponible en inventario (mínimo 1)
     */
    public Producto(TipoProducto categoria, String codigo, String nombre, double precio, int stock) {
        this.categoria = categoria;
        this.codigo = codigo != null ? codigo.trim() : "";
        this.nombre = nombre != null ? nombre.trim() : "";
        this.precio = Math.max(1.0, precio);
        this.stock = Math.max(1, stock);
    }

    /**
     * Constructor por defecto de la clase Producto.
     * Crea un producto con valores predeterminados.
     */
    public Producto() {
        this(TipoProducto.Ropa, "", "Sin nombre", 0.0, 0);
    }

    /**
     * Obtiene la categoría del producto.
     * 
     * @return La categoría del producto
     */
    public TipoProducto getCategoria() {
        return categoria;
    }
    
    /**
     * Obtiene el código único del producto.
     * 
     * @return El código del producto
     */
    public String getCodigo() {
        return codigo;
    }
    
    /**
     * Obtiene el nombre del producto.
     * 
     * @return El nombre del producto
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Obtiene el precio unitario del producto.
     * 
     * @return El precio del producto
     */
    public double getPrecio() {
        return precio;
    }
    
    /**
     * Obtiene la cantidad disponible en stock.
     * 
     * @return El stock actual del producto
     */
    public int getStock() {
        return stock;
    }

    /**
     * Establece la categoría del producto.
     * 
     * @param categoria La nueva categoría del producto (no puede ser null)
     */
    public void setCategoria(TipoProducto categoria) {
        if (categoria != null) {
            this.categoria = categoria;
        }
    }
    
    /**
     * Establece el código único del producto.
     * 
     * @param codigo El nuevo código del producto (no puede estar vacío)
     */
    public void setCodigo(String codigo) {
        if (codigo != null && !codigo.trim().isEmpty()) {
            this.codigo = codigo.trim();
        }
    }
    
    /**
     * Establece el nombre del producto.
     * 
     * @param nombre El nuevo nombre del producto (no puede estar vacío)
     */
    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre.trim();
        }
    }
    
    /**
     * Establece el precio del producto.
     * 
     * @param precio El nuevo precio del producto (debe ser mayor o igual a 0)
     */
    public void setPrecio(double precio) {
        if (precio >= 0) {
            this.precio = precio;
        }
    }
    
    /**
     * Establece la cantidad de stock del producto.
     * 
     * @param stock La nueva cantidad de stock (debe ser mayor o igual a 0)
     */
    public void setStock(int stock) {
        if (stock >= 0) {
            this.stock = stock;
        }
    }

    /**
     * Verifica si hay stock disponible del producto.
     * 
     * @return true si hay stock disponible, false en caso contrario
     */
    public boolean hayStock() {
        return stock > 0;
    }
    
    /**
     * Verifica si hay suficiente stock para una cantidad específica.
     * 
     * @param cantidad La cantidad requerida
     * @return true si hay suficiente stock, false en caso contrario
     */
    public boolean hayStock(int cantidad) {
        return stock >= cantidad;
    }
    
    /**
     * Reduce el stock del producto en la cantidad especificada.
     * 
     * @param cantidad La cantidad a reducir del stock
     */
    public void reducirStock(int cantidad) {
        if (cantidad > 0 && cantidad <= stock) {
            this.stock -= cantidad;
        }
    }
    
    /**
     * Aumenta el stock del producto en la cantidad especificada.
     * 
     * @param cantidad La cantidad a agregar al stock
     */
    public void aumentarStock(int cantidad) {
        if (cantidad > 0) {
            this.stock += cantidad;
        }
    }

    /**
     * Devuelve una representación en cadena del producto.
     * 
     * @return Cadena con todos los datos del producto separados por |
     */
    @Override
    public String toString() {
        return new StringBuilder()
                .append(this.codigo).append("|")
                .append(this.categoria).append("|")
                .append(this.nombre).append("|")
                .append(this.precio).append("|")
                .append(this.stock)
                .toString();
    }
        
    /**
     * Compara si dos productos son iguales basándose en su código.
     * 
     * @param obj El objeto a comparar
     * @return true si los productos son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Producto producto = (Producto) obj;
        return codigo != null ? codigo.equals(producto.codigo) : producto.codigo == null;
    }
}
