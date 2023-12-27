package main;

public class Medicine {

    private int mid;
    private String name;
    private String type;
    private float price;
    private long quantity;

    public Medicine(int mid, String name, String type, float price, long quantity){

        this.mid = mid;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.type = type;
    }
    public Medicine(){}

    public int getMid() {
        return mid;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public float getPrice() {
        return price;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
