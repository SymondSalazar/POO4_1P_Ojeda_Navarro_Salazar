package proyecto.servicio;

import proyecto.producto.Producto;
import proyecto.TipoProducto;
import proyecto.utils.InputUtils;
import java.util.List;
import java.util.ArrayList;

/**
 * Servicio para manejo de productos y catálogo
 */
public class ProductoService {
    
    /**
     * Muestra las categorías disponibles
     */
    public void mostrarCategorias() {
        System.out.println("\n--- CATEGORÍAS DISPONIBLES ---");
        for (TipoProducto tipo : TipoProducto.values()) {
            System.out.println("- " + tipo.getDescripcion());
        }
    }
    
    /**
     * Solicita al usuario seleccionar una categoría
     */
    public TipoProducto seleccionarCategoria() {
        mostrarCategorias();
        
        while (true) {
            String entrada = InputUtils.leerTextoNoVacio("Ingrese la categoría que desea consultar: ");
            
            try {
                // Intentar buscar por nombre exacto primero
                for (TipoProducto tipo : TipoProducto.values()) {
                    if (tipo.name().equalsIgnoreCase(entrada) || 
                        tipo.getDescripcion().equalsIgnoreCase(entrada)) {
                        return tipo;
                    }
                }
                
                // Mapeo especial para "tecnologia" sin tilde
                if (entrada.equalsIgnoreCase("tecnologia")) {
                    return TipoProducto.Tecnología;
                }
                
                // Si no encuentra por nombre, intentar por enum
                return TipoProducto.valueOf(entrada);
                
            } catch (IllegalArgumentException e) {
                System.out.println("Categoría inválida. Intente nuevamente.");
                mostrarCategorias();
            }
        }
    }
    
    /**
     * Filtra y muestra productos por categoría
     */
    public List<Producto> mostrarProductosPorCategoria(List<Producto> todosLosProductos, TipoProducto categoria) {
        System.out.println("\n--- PRODUCTOS EN CATEGORÍA: " + categoria.getDescripcion() + " ---");
        
        List<Producto> productosFiltrados = new ArrayList<>();
        
        for (Producto producto : todosLosProductos) {
            if (producto.getCategoria() == categoria && producto.hayStock()) {
                productosFiltrados.add(producto);
            }
        }
        
        if (productosFiltrados.isEmpty()) {
            System.out.println("No hay productos disponibles en esta categoría.");
        } else {
            System.out.printf("%-10s %-20s %-15s %-10s%n", "Código", "Nombre", "Precio", "Stock");
            System.out.println("-".repeat(60));
            for (Producto producto : productosFiltrados) {
                System.out.printf("%-10s %-20s $%-14.2f %-10d%n", 
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getPrecio(),
                    producto.getStock());
            }
        }
        
        return productosFiltrados;
    }
    
    /**
     * Permite al usuario seleccionar un producto específico
     */
    public Producto seleccionarProducto(List<Producto> productosDisponibles) {
        if (productosDisponibles.isEmpty()) {
            System.out.println("No hay productos disponibles para seleccionar.");
            return null;
        }
        
        while (true) {
            String entrada = InputUtils.leerTextoNoVacio("Ingrese el nombre o código del producto que desea comprar: ");
            
            for (Producto producto : productosDisponibles) {
                if (producto.getNombre().equalsIgnoreCase(entrada) || 
                    producto.getCodigo().equalsIgnoreCase(entrada)) {
                    return producto;
                }
            }
            
            System.out.println("Producto no encontrado. Intente nuevamente.");
        }
    }
    
    /**
     * Solicita y valida la cantidad a comprar
     */
    public int solicitarCantidad(Producto producto) {
        System.out.println("\nProducto seleccionado: " + producto.getNombre());
        System.out.println("Precio unitario: $" + String.format("%.2f", producto.getPrecio()));
        System.out.println("Stock disponible: " + producto.getStock());
        
        int cantidad;
        do {
            cantidad = InputUtils.leerEnteroPositivo("Ingrese la cantidad que desea comprar: ");
            
            if (!producto.hayStock(cantidad)) {
                System.out.println("Error: No hay suficiente stock. Stock disponible: " + producto.getStock());
            }
        } while (!producto.hayStock(cantidad));
        
        return cantidad;
    }
    
    /**
     * Muestra el resumen de la compra
     */
    public void mostrarResumenCompra(Producto producto, int cantidad) {
        double total = producto.getPrecio() * cantidad;
        
        System.out.println("\n--- RESUMEN DE COMPRA ---");
        System.out.println("Producto: " + producto.getNombre());
        System.out.println("Precio unitario: $" + String.format("%.2f", producto.getPrecio()));
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Total a pagar: $" + String.format("%.2f", total));
    }
    
    /**
     * Actualiza el stock del producto después de una compra
     */
    public boolean procesarCompra(Producto producto, int cantidad) {
        if (producto.hayStock(cantidad)) {
            producto.reducirStock(cantidad);
            return true;
        }
        return false;
    }
}
