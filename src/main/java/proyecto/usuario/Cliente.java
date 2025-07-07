package proyecto.usuario;
import java.util.Scanner;
import java.util.List;

import proyecto.Sistema;
import proyecto.producto.Producto;
import java.util.ArrayList;
import java.util.InputMismatchException;
import proyecto.producto.TipoProducto;


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

    public void mostrarCategorias() {
        for(TipoProducto tp : TipoProducto.values()) System.out.println(tp);
    }

    public TipoProducto guardarCategoria() {
    TipoProducto categoria = null;

    while (categoria == null || categoria == TipoProducto.DEFAULT) {
        System.out.println("Categorías disponibles:");
        for (TipoProducto tipo : TipoProducto.values()) {
            if (tipo != TipoProducto.DEFAULT) {
                System.out.println("- " + tipo);
            }
        }

        System.out.print("Ingrese categoría que desea consultar: ");
        String entrada = Sistema.SC.nextLine().toUpperCase();

        try {
            categoria = TipoProducto.valueOf(entrada);
            if (categoria == TipoProducto.DEFAULT) {
                System.out.println("No puede elegir la opción 'DEFAULT'.");
                categoria = null;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Categoría inválida. Intente nuevamente.");
        }
    }
    return categoria;
}

    public List<Producto> mostrarProductosDisponibles(List<Producto> listaProductos, TipoProducto categoria) {
    System.out.println("Productos en la categoría: " + categoria);
    
    List<Producto> listaProdCat = new ArrayList<>();

    for (Producto p : listaProductos) {
        if (p.getCategoria() == categoria) {
            listaProdCat.add(p);
        }
    }

    if (listaProdCat.isEmpty()) {
        System.out.println("No hay productos disponibles en esta categoría.");
    } else {
        for (Producto p : listaProdCat) {
            System.out.println(p);
        }
    }

    return listaProdCat;
}

    public Producto guardarProducto(List<Producto> listaProdCat) {
    Producto productoSeleccionado = null;

    while (productoSeleccionado == null) {
        System.out.println("Ingrese el nombre del producto que desea comprar:");
        String entrada = Sistema.SC.nextLine();

        for (Producto p : listaProdCat) {
            if (p.getNombre().equalsIgnoreCase(entrada)) {
                productoSeleccionado = p;
                break;
            }
        }

        if (productoSeleccionado == null) {
            System.out.println("El producto no está en la lista. Intente nuevamente.");
        }
    }
    sc.close();
    return productoSeleccionado;
}

    public int guardarCantidadProducto(Producto p) {
        Scanner sc = new Scanner(System.in);

        int cantidad = 0;

        while (cantidad == 0) {
            try {
            System.out.println("Ingrese la cantidad que desea comprar del producto: ");
            cantidad = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("No ingraste un número entero.");
            }
        } 
        sc.close();
        return cantidad;
    }

    public void mostrarTotalPago(Producto producto, int cantidad) {
        System.out.println("Total a pagar: " + producto.getPrecio()*cantidad);
    }

    public String guardarNumeroTarjeta() {
        Scanner sc = new Scanner(System.in);
        boolean value = true;

        System.out.println("Ingrese su número de tarjeta: ");
        String numeroTarjeta = sc.nextLine();

        while(value) {
            if (numeroTarjeta.matches("[0-9]*")) {
                value = false;
            } System.out.println("Ingresaste un número inválido");
        }
        sc.close();
        return numeroTarjeta;
    }

    public void mostrarMensajePago() {
        System.out.println("El pago se hizo efectivo");
    }

}


