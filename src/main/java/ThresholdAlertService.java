public class ThresholdAlertService implements AlertService
{

    private double maxCost; //TRAIGO LOS UMBRALES QUE ESTABAN EN ALERTOBSERVER ACA
    private int maxETA;

    public ThresholdAlertService(double maxCost, int maxETA) { //PASO LOS VALORES POR CONSTRUCTOR PARA PODER HACERLO DESDE MAIN
        this.maxCost = maxCost;
        this.maxETA = maxETA;
    }

    @Override
    public boolean shouldAlertCost(double cost) { // Y YA ACA IMPLEMENTO LAS FUNCIONES
        return cost > maxCost;
    }

    @Override
    public boolean shouldAlertETA(int eta) {
        return eta > maxETA;
    }
}