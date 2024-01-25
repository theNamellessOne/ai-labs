package lab5;

public class Wall {
    public int height;
    public int width;
    public double price = 1000;

    public Wall(int height, int width) {
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
        return "Wall{" +
                "height=" + height +
                ", width=" + width +
                '}';
    }
}
