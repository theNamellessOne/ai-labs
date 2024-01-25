package lab5;

public class House {
    public Wall walls;
    public Door doors;
    public Window windows;
    public Furniture furniture;

    public House(Wall walls, Door doors, Window windows, Furniture furniture) {
        this.walls = walls;
        this.doors = doors;
        this.windows = windows;
        this.furniture = furniture;
    }

    public void countSum() {
        double total = walls.price + windows.price + doors.price + furniture.price;
        System.out.println("Money needed for planned house: " + total);
    }

    public Wall getWalls() {
        return walls;
    }

    public void setWalls(Wall walls) {
        this.walls = walls;
    }

    public Door getDoors() {
        return doors;
    }

    public void setDoors(Door doors) {
        this.doors = doors;
    }

    public Window getWindows() {
        return windows;
    }

    public void setWindows(Window windows) {
        this.windows = windows;
    }

    public Furniture getFurniture() {
        return furniture;
    }

    public void setFurniture(Furniture furniture) {
        this.furniture = furniture;
    }

    @Override
    public String toString() {
        return "House{" +
                "walls=" + walls +
                ", doors=" + doors +
                ", windows=" + windows +
                ", furniture=" + furniture +
                '}';
    }
}
