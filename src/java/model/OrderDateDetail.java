package model;

public class OrderDateDetail {

    private String date;
    private String pName;
    private int quantity;
    private int price;
    private int total;

    public OrderDateDetail() {
    }

    public OrderDateDetail(String date, String pName, int quantity, int price, int total) {
        this.date = date;
        this.pName = pName;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }

    public OrderDateDetail(String pName, int quantity, int price, int total) {
        this.pName = pName;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderDateDetail{" + "date=" + date + ", pName=" + pName + ", quantity=" + quantity + ", price=" + price + ", total=" + total + '}';
    }

}
