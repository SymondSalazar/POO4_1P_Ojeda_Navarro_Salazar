package proyecto.pedido;
import proyecto.usuario.Repartidor;


public class Pedido {
    private String codigoPedido;
    private String fechaPedido;
    private int cantPedido;
    private double valorPagado;
    private EstadoPedido estado;
    private Repartidor repartidor;

    //Contructor de Clase
    public Pedido(String codigoPedido, String fechaPedido, int cantPedido, double valorPagado, EstadoPedido estado, Repartidor repartidor){
        this.codigoPedido=codigoPedido;
        this.fechaPedido=fechaPedido;
        this.cantPedido=cantPedido;
        this.valorPagado=valorPagado;
        this.estado=estado;
        this.repartidor=repartidor;
    }

    //Setters con validaciones
    public void setCantPedido(int cantPedido) {
        if (cantPedido > 0) {
            this.cantPedido = cantPedido;
        }
    }
    public void setCodigoPedido(String codigoPedido) {
        if (codigoPedido != null && !codigoPedido.trim().isEmpty()) {
            this.codigoPedido = codigoPedido.trim();
        }
    }
    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }
    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }
    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
    }
    public void setValorPagado(double valorPagado) {
        if (valorPagado >= 0) {
            this.valorPagado = valorPagado;
        }
    }

    //Getters
    public int getCantPedido() {
        return cantPedido;
    }
    public String getCodigoPedido() {
        return codigoPedido;
    }
    public EstadoPedido getEstado() {
        return estado;
    }
    public String getFechaPedido() {
        return fechaPedido;
    }
    public Repartidor getRepartidor() {
        return repartidor;
    }
    public double getValorPagado() {
        return valorPagado;
    }
    
    // Métodos de utilidad
    public boolean estaEntregado() {
        return this.estado == EstadoPedido.Entregado;
    }
    
    public boolean estaEnRuta() {
        return this.estado == EstadoPedido.EnRuta;
    }
    
    public boolean estaEnPreparacion() {
        return this.estado == EstadoPedido.EnPreparacion;
    }
}
