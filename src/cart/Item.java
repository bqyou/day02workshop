package cart;

public class Item {

    private String name;
    private Float price = 0f;
    private Integer quantity = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public float totalCost(Float price, Integer quantity) {
        return this.price * this.quantity;
    }

    public Item(String name, Float price, Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Item [name=" + name + ", price=" + price + ", quantity=" + quantity + "]";
    }

}
