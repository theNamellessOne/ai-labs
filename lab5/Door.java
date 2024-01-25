package lab5;

public class Door {
    public int height;
    public int width;
    public double price = 300;

    public Door(int height, int width) {
        this.height = height;
        this.width = width;

        this.price = price + (1 * (height * 0.1) * (width * 0.1));
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "Door{" +
                "height=" + height +
                ", width=" + width +
                '}';
    }
}
