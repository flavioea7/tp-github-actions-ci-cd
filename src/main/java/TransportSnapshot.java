//DATOS DEL TRANSPORTE
public class TransportSnapshot {    //captura el estado del transporte 

        private String name;
        private double cost;
        private double distance;
        private int eta;    //TIEMPO DE LLEGADA

        public TransportSnapshot(String name, double cost, double distance, int eta) {  //CONSTRUCTOR
            this.name = name;
            this.cost = cost;
            this.distance = distance;
            this.eta = eta;
        }
//GETTERS
        public String getName() {
            return name;
        }

        public double getCost() {
            return cost;
        }

        public double getDistance() {
            return distance;
        }

        public int getEta() {
            return eta;
        }
    }

