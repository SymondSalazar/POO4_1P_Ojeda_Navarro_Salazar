package proyecto;
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

    //Setters
    public void setCantPedido(int cantPedido) {
        this.cantPedido = cantPedido;
    }
    public void setCodigoPedido(String codigoPedido) {
        this.codigoPedido = codigoPedido;
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
        this.valorPagado = valorPagado;
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
}
