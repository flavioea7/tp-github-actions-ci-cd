import java.util.Random; //ACA IMPORTO PA PODER USAR RANDOM

public class ColectivoStrategy implements TransporteStrategy
{
    private final Random random = new Random();

    private static final int PRECIO_MIN = 1500;
    private static final int PRECIO_MAX = 10000;

    private static final double DISTANCIA_MIN = 1.0;
    private static final double DISTANCIA_MAX = 200.0;

    private static final int ETA_MIN = 15;
    private static final int ETA_MAX = 240;

    @Override
    public String getNombre()
    {
        return "Colectivo";
    }

    @Override
    public double getPrecio()
    {
        return PRECIO_MIN + random.nextInt(PRECIO_MAX - PRECIO_MIN + 1);
    }

    @Override
    public double getDistancia()
    {
        return DISTANCIA_MIN + random.nextDouble() * (DISTANCIA_MAX - DISTANCIA_MIN);
    }

    @Override
    public int getETA()
    {
        return ETA_MIN + random.nextInt(ETA_MAX - ETA_MIN + 1 );
    }
}
