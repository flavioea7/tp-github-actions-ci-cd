import java.util.Random; //ACA IMPORTO PA PODER USAR RANDOM

public class BicicletaStrategy implements TransporteStrategy
{
    private final Random random = new Random();

    private static final double PRECIO_MIN = 50.0;
    private static final double PRECIO_MAX = 300.0;

    private static final double DISTANCIA_MIN = 0.1;
    private static final double DISTANCIA_MAX = 15.0;

    private static final int ETA_MIN = 1;
    private static final int ETA_MAX = 60;

    @Override
    public String getNombre()
    {
        return "Bicicleta";
    }

    @Override
    public double getPrecio()
    {
        return PRECIO_MIN + random.nextDouble() * (PRECIO_MAX - PRECIO_MIN);
    }

    @Override
    public double getDistancia()
    {
        return DISTANCIA_MIN + random.nextDouble() * (DISTANCIA_MAX - DISTANCIA_MIN);
    }

    @Override
    public int getETA()
    {
        return ETA_MIN + random.nextInt(ETA_MAX - ETA_MIN + 1);
    }
}