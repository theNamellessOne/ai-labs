package lab4;

public class Car {
    public String typeOfTransport = "Car";
    public Boolean hasConditioner = true;
    public Transport transport;

    public Car() {
        this.transport = new Transport();
    }

    public Boolean is(String string) {
        return string.equals("Transport") || string.equals("Car");
    }

    public Boolean has(String string) {
        return transport.has(string) || string.equals("Conditioner") || string.equals("Window");
    }
}
