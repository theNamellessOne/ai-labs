package lab5;

public class Main {
    public static void main(String[] args) {
        Wall walls = new Wall(300, 2500);
        Door door = new Door(200, 200);
        Window windows = new Window(100, 100);
        Furniture furniture = new Furniture(30);

        House house = new House(walls, door, windows, furniture);

        System.out.println(house);
        house.countSum();
    }
}
