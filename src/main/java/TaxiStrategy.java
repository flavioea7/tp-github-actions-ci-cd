import java.util.Random; //ACA IMPORTO PA PODER USAR RANDOM

public class TaxiStrategy implements TransporteStrategy
{
    private final Random random = new Random();

    private static final double PRECIO_MIN = 3500.0;
    private static final double PRECIO_MAX = 10000.0;

    private static final double DISTANCIA_MIN = 1.0;
    private static final double DISTANCIA_MAX = 35.0;

    private static final int ETA_MIN = 5;
    private static final int ETA_MAX = 90;

    @Override
    public String getNombre()
    {
        return "Taxi";
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
