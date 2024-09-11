package model;

public class Product {

    private String id, name;
    private int quantity;
    private int price;
    private String releaseDate, describe, image, date;
    private Category category;
    private int cid;
    private int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Product(String id, String name, int quantity, int price, String describe, String image, String date, int cid, int total) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.describe = describe;
        this.image = image;
        this.date = date;
        this.cid = cid;
        this.total = total;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", quantity=" + quantity + ", price=" + price + ", releaseDate=" + releaseDate + ", describe=" + describe + ", image=" + image + ", date=" + date + ", category=" + category + ", cid=" + cid + ", total=" + total + '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public Product(String id, String name, int price, String describe, String image, int cid, int total) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.describe = describe;
        this.image = image;
        this.cid = cid;
        this.total = total;
    }

    public Product() {
    }

    public Product(String name, int price, String describe, String image, int cid) {
        this.name = name;

        this.price = price;
        this.describe = describe;
        this.image = image;
        this.cid = cid;
    }

    public Product(String id, String name, int price, String describe, String image, int cid) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.describe = describe;
        this.image = image;
        this.cid = cid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public Product(String id, String name, int price, String describe, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.describe = describe;
        this.image = image;

    }

    public Product(Category category) {
        this.category = category;
    }

    public Product(String id, String name, int quantity,
            int price, String releaseDate, String describe,
            String image, Category category) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.releaseDate = releaseDate;
        this.describe = describe;
        this.image = image;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
