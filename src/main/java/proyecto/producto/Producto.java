package proyecto.producto;

public class Producto {
    TipoProducto categoria;
    String codigo;
    String nombre;
    double precio;
    int stock;

    public Producto(TipoProducto categoria, String codigo, String nombre, double precio, int stock) {
        this.categoria = categoria;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public Producto () {
        this.categoria = TipoProducto.DEFAULT;
        this.codigo = "0";
        this.nombre = "0";
        this.precio = 0.0;
        this.stock = 0;
    }

    public TipoProducto getCategoria() {
        return categoria;
    }
    public String getCodigo() {
        return codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public double getPrecio() {
        return precio;
    }
    public int getStock() {
        return stock;
    }
    public void setCategoria(TipoProducto categoria) {
        this.categoria = categoria;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
}
