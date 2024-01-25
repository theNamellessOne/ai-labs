package lab4;

public class Main {
    public static void main(String[] args) {
        Car car = new Car();
        Motorcycle motorcycle = new Motorcycle();

        System.out.println("Car is Animal? " + car.is("Animal"));
        System.out.println("Car is Transport? " + car.is("Transport"));
        System.out.println("Car has Wheels? " + car.has("Wheels"));
        System.out.println("Car has Engine? " + car.has("Engine"));
        System.out.println("Car has Window? " + car.has("Window"));
        System.out.println("Car has Tail? " + car.has("Tail"));

        System.out.println();

        System.out.println("Motorcycle is Car? " + motorcycle.is("Car"));
        System.out.println("Motorcycle is Motorcycle? " + motorcycle.is("Motorcycle"));
        System.out.println("Motorcycle has Wheels? " + motorcycle.has("Wheels"));
        System.out.println("Motorcycle has Leg? " + motorcycle.has("Leg"));
        System.out.println("Motorcycle has Wings? " + motorcycle.has("Wings"));
    }
}
