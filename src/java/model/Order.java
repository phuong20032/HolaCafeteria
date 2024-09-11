package model;

public class Order {

    private int id;
    private String date;
    private int cusid;
    private double totalmoney;
    private int num;

    public Order(String date, int num) {
        this.date = date;
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Order(String date) {
        this.date = date;
    }

    public Order() {
    }

    public Order(int id, String date, int cusid, double totalmoney) {
        this.id = id;
        this.date = date;
        this.cusid = cusid;
        this.totalmoney = totalmoney;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", date=" + date + ", cusid=" + cusid + ", totalmoney=" + totalmoney + ", num=" + num + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCusid() {
        return cusid;
    }

    public void setCusid(int cusid) {
        this.cusid = cusid;
    }

    public double getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(double totalmoney) {
        this.totalmoney = totalmoney;
    }

}
