package proyecto;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import proyecto.leerArchivo.ParseData;
import proyecto.usuario.*;

public class Sistema {
    private ArrayList<Usuario> usuarios = ParseData.parseUsuarios();
    public final static Scanner SC = new Scanner(System.in);

    public Usuario IniciarSesion(){
        Scanner sc = new Scanner(System.in);
        System.out.println("============INICIAR SESIÓN============");
        System.out.println("Usuario: ");
        String usuario= sc.nextLine();
        System.out.println("Contraseña: ");
        String contrasenia= sc.nextLine();

        for (Usuario user : this.usuarios) {
            if(user.getUserName().equals(usuario) && user.getContrasena().equals(contrasenia)){
                return user;
            }
        }
        return null;
    }
    public boolean validarUsuario(Usuario user){
        Scanner sc= new Scanner(System.in);
        if (user==null){
            System.out.println("Error al autenticar usuario");
            return false;
        }else{
            System.out.println("Usuario autenticado exitosamente");
            char rol= user.getCodigoUnico().charAt(0);
            if (rol=='C'){
                Cliente c= (Cliente)user;
                System.out.println("Rol detectado: CLIENTE");
                System.out.println("Bienvenid@ "+user.getNombres()+" "+user.getApellidos());
                System.out.println("Celular registrado: "+c.getCelular());
                System.out.println("¿Este número celular es correcto? (S/N): ");
            }else{
                Repartidor r= (Repartidor)user;
                System.out.println("Rol detectado: REPARTIDOR");
                System.out.println("Bienvenid@ "+user.getNombres()+" "+user.getApellidos());
                System.out.println("Empresa asginada: "+r.getEmpresa());
                System.out.println("¿Esta empresa es correcta? (S/N)");
            }
            String sn= sc.nextLine();
            if(sn.equals("S")){
                System.out.println("Identidad Confirmada");
                return true;
            }else{
                System.out.println("Verificación fallida");
                System.out.println("Por motivos de seguridad se cerrará sesión");
                System.out.println("Saliendo del sistema...");
                return false;
            }

        }
    }
    public void mostrarmenu(Usuario user, boolean verificacion){
        if(user instanceof Cliente){
            Cliente c= (Cliente)user;
            System.out.println("Menú de Cliente");
            System.out.println("1. Comprar producto\n 2. Gestionar Pedido\n 3. Salir\n Seleccione una opción: ");
        }else{
            Repartidor r= (Repartidor)user;
            System.out.println("Menú de Repartidor");
            System.out.println("1. Consultar pedidos\n 2. Gestionar Pedido\n 3. Salir\n Seleccione una opción: ");

        }
    }
    public List<Repartidor> obtenerRepartidores(List<Usuario> usuarios) {
    List<Repartidor> repartidores = new ArrayList<>();

    for (Usuario u : usuarios) {
        if (u instanceof Repartidor) {
            repartidores.add((Repartidor) u); // casteo seguro
        }
    }
    return repartidores;
}
    public List<Cliente> obtenerClientes(List<Usuario> usuarios) {
        List<Cliente> clientes = new ArrayList<>();

        for (Usuario u : usuarios) {
            if (u instanceof Cliente) {
                clientes.add((Cliente) u);
            }
        }
        return clientes;
    }

    
    public static void main(String[] args) {
        Sistema sis = new Sistema();
        Usuario user=sis.IniciarSesion();
        boolean val =sis.validarUsuario(user);
        
        sis.mostrarmenu(user, val);
    }
}