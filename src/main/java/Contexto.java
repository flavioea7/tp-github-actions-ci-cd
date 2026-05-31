public class Contexto
{
    //GUARDO LA ESTRATEGIA COMO ATRIBUTO
    private TransporteStrategy estrategiaActual;


    //CONSTRUCTOR PARA ARRANCAR CON UNA DE ARRANQUE
    public Contexto (TransporteStrategy estrategia)
    {
        this.estrategiaActual = estrategia;
    }

    //CREO UN SETTER PARA PODER MODIFICAR LA ESTRATEGIA DURANTE EJECUCION

    public void setEstrategia(TransporteStrategy nuevaEstrategia)
    {
        this.estrategiaActual = nuevaEstrategia;
    }

    //ACA YA LLAMO LAS FUNCIONES DE CADA UNA PARA QUE EL CONTEXTO RESPONDA SEGUN TOQUE

    public String getNombre()
    {
        return estrategiaActual.getNombre();
    }

    public double getPrecio()
    {
        return estrategiaActual.getPrecio();
    }

    public double getDistancia()
    {
        return estrategiaActual.getDistancia();
    }

    public int getETA()
    {
        return estrategiaActual.getETA();
    }

}
