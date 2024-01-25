package lab6;

public class Temperature {

    public Temperature() {
    }

    public double cold(double u) {
        return 1 / (1 + Math.pow(((u - 10) / 7), 12));
    }

    public double ok(double u) {
        return 1 / (1 + Math.pow(((u - 20) / 3), 6));
    }

    public double hot(double u) {
        return 1 / (1 + Math.pow(((u - 30) / 6), 10));
    }
}
