/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author ngoba
 */
public class OrderTag {
    private int oId;
    private int uId;
    private String uName;
    private String uPhone;
    private Date date;
    private double totalMoney;

    public OrderTag() {
    }

    public OrderTag(int oId, int uId, String uName, String uPhone, Date date, double totalMoney) {
        this.oId = oId;
        this.uId = uId;
        this.uName = uName;
        this.uPhone = uPhone;
        this.date = date;
        this.totalMoney = totalMoney;
    }

    public int getoId() {
        return oId;
    }

    public void setoId(int oId) {
        this.oId = oId;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }
    
}
