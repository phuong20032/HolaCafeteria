package model;

public class User {

    private String username, password, fullname, email, phonenum, address;
    private int role;
    private int id;
    private int numOfOrds;

    public int getNumOfOrds() {
        return numOfOrds;
    }

    public void setNumOfOrds(int numOfOrds) {
        this.numOfOrds = numOfOrds;
    }

    public User() {
    }

    public User(String fullname, String email, String phonenum, String address, int numOfOrds) {
        this.fullname = fullname;
        this.email = email;
        this.phonenum = phonenum;
        this.address = address;
        this.numOfOrds = numOfOrds;
    }

    public User(String username, String password, String fullname, String email, String phonenum, String address) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.phonenum = phonenum;
        this.address = address;
    }

    public User(String username, String password, String fullname, String email, String phonenum) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.phonenum = phonenum;
    }

    public User(String username, String password, String fullname, String email, String phonenum, String address, int role, int id) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.phonenum = phonenum;
        this.address = address;
        this.role = role;
        this.id = id;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, int role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String username, String password, String fullname, int role, int id) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
