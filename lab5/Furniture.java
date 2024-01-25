package lab5;

public class Furniture {
    public int quantity;
    public int price = 20;

    public Furniture(int quantity) {
        this.quantity = quantity;
        this.price = quantity * price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Furniture{" +
                "quantity=" + quantity +
                '}';
    }
}
