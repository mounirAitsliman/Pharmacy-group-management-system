package main;

public class Customer {

    private int cid;
    private String name;
    private String type;
    private float price;
    private long quantity;
    private String date;
    private float total;

    public Customer(int cid, String name, String type, float price, long quantity, float total, String date) {
        this.cid = cid;
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.total=total;
        this.date = date;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
