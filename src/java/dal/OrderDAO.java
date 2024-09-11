/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.User;
import model.Item;
import model.Order;
import model.OrderDateDetail;
import model.OrderLine;
import model.OrderTag;

public class OrderDAO {

    public static OrderDAO INSTANCE = new OrderDAO();
    public String status;
    public Connection con;

    private OrderDAO() {
        if (INSTANCE == null) {
            try {
                con = new DBContext().getConnection(); // mo ket noi voi co so du lieu
            } catch (Exception e) {
                status = "Error at Connection " + e.getMessage();
            }
        }
    }

    public List<OrderLine> getOrderLineByUser(int id) {
        String sql = "select o.id, p.name, ol.quantity, ol.price, ol.quantity*ol.price,"
                + " o.date from Order1 o join OrderLine1 ol on o.id = ol.oid"
                + " join Product1 p on ol.pid = p.ID where o.cid = ? order by date desc";
        List<OrderLine> orders = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderLine c = new OrderLine();
                c.setoId(rs.getInt(1));
                c.setpName(rs.getString(2));
                c.setQuantity(rs.getInt(3));
                c.setPrice(rs.getDouble(4));
                c.setTotal(rs.getDouble(5));
                c.setDate(rs.getDate(6));
                c.setcId(id);
                orders.add(c);
            }
        } catch (Exception e) {
        }
        return orders;
    }

    public List<OrderLine> getOrderLineByUserPaging(int id, int index) {
        String sql = "select o.id, p.name, ol.quantity, ol.price, ol.quantity*ol.price,"
                + " o.date from Order1 o join OrderLine1 ol on o.id = ol.oid"
                + " join Product1 p on ol.pid = p.ID where o.cid = ? order by o.id desc";
        sql += " OFFSET ? ROWS FETCH NEXT 6  ROWS ONLY ";
        List<OrderLine> orders = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, (index - 1) * 6);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderLine c = new OrderLine();
                c.setoId(rs.getInt(1));
                c.setpName(rs.getString(2));
                c.setQuantity(rs.getInt(3));
                c.setPrice(rs.getDouble(4));
                c.setTotal(rs.getDouble(5));
                c.setDate(rs.getDate(6));
                c.setcId(id);
                orders.add(c);
            }
        } catch (Exception e) {
        }
        return orders;
    }

    public void addOrder(User u, Cart cart) {
        LocalDate curDate = java.time.LocalDate.now();
        String date = curDate.toString();
        try {
            String sql = "insert into [Order1] values (?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, date);
            ps.setInt(2, u.getId());
            ps.setDouble(3, cart.getTotalMoney());
            ps.executeUpdate();

            String sql1 = "select top 1 id from [Order1] order by id desc";
            PreparedStatement ps1 = con.prepareStatement(sql1);
            ResultSet rs = ps1.executeQuery();
            if (rs.next()) {
                int oid = rs.getInt(1);
                for (Item i : cart.getItems()) {
                    String sql2 = "insert into [OrderLine1] values (?,?,?,?)";
                    PreparedStatement ps2 = con.prepareStatement(sql2);
                    ps2.setInt(1, oid);
                    ps2.setString(2, i.getProduct().getId());
                    ps2.setInt(3, i.getQuantity());
                    ps2.setDouble(4, i.getPrice());
                    ps2.executeUpdate();

                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int getNumOfOrder() {
        ArrayList<Order> ls = new ArrayList<>();
        String sql = "SELECT  *\n"
                + "  FROM Order1\n"
                + "  where totalmoney!=0";

        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Order c = new Order();
                c.setId(rs.getInt("ID"));
                ls.add(c);

            }
        } catch (Exception e) {
        }
        int count = ls.size();
        return count;
    }

    public double getTotalRenevue() {
        ArrayList<Order> ls = new ArrayList<>();
        String sql = "SELECT  [id]\n"
                + "      ,[date]\n"
                + "      ,[cid]\n"
                + "      ,[totalmoney]\n"
                + "  FROM Order1\n"
                + " where totalmoney!=0";

        double sum = 0;
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Order c = new Order();
                c.setTotalmoney(rs.getDouble("totalmoney"));
                ls.add(c);
            }
        } catch (SQLException e) {
        }
        for (int i = 0; i < ls.size(); i++) {
            sum += ls.get(i).getTotalmoney();
        }

        return sum;
    }

    public List<Order> getAll() {
        List<Order> ls = new ArrayList<>();
        String sql = "SELECT  [id]\n"
                + "      ,[date]\n"
                + "      ,[cid]\n"
                + "      ,[totalmoney]\n"
                + "  FROM [dbo].[Order1]\n"
                + "  where totalmoney!=0\n"
                + "  order by [date]";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order c = new Order();
                c.setId(rs.getInt("id"));
                c.setDate(rs.getString("date"));
                c.setCusid(rs.getInt("cid"));
                c.setTotalmoney(rs.getInt("totalmoney"));
                ls.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return ls;
    }

    public List<Order> numProductPerDay() {
        List<Order> ls = new ArrayList<>();
        String sql = "SELECT  count([date]) as'Num'\n"
                + "			,date	\n"
                + "         FROM [dbo].[Order1] \n"
                + "	where totalmoney!=0 \n"
                + "	group by [date]";

//        double sum = 0;
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Order c = new Order();
                c.setDate(rs.getString("date"));
                c.setNum(rs.getInt("Num"));
                ls.add(c);
            }
        } catch (SQLException e) {

        }

        return ls;
    }

    public List<Order> Date() {
        List<Order> ls = new ArrayList<>();
        String sql = "SELECT  [date]\n"
                + "  FROM [dbo].[Order1]\n"
                + "  where totalmoney!=0";

//        double sum = 0;
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Order c = new Order();
                c.setDate(rs.getString("date"));

                ls.add(c);
            }
        } catch (SQLException e) {

        }

        return ls;
    }

    public List<OrderDateDetail> OrderDateDetail(String date) {
        ArrayList<OrderDateDetail> ls = new ArrayList<>();

//        double sum = 0;
        try {
            String sql = "SELECT date\n"
                    + "		,p.[name]\n"
                    + "      ,[quantity]\n"
                    + "      ,p.[price]\n"
                    + "	  , totalmoney\n"
                    + "	FROM [dbo].[Order1] o join OrderLine1 ol\n"
                    + "	on o.id = ol.oid\n"
                    + "	join Product1 p\n"
                    + "	on ol.pid= p.ID\n"
                    + "\n"
                    + "	where totalmoney!=0 and [date]=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, date);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                OrderDateDetail c = new OrderDateDetail();
                c.setDate(rs.getString("date"));
                c.setpName(rs.getString("name"));
                c.setQuantity(rs.getInt("quantity"));
                c.setPrice(rs.getInt("price"));
                c.setTotal(rs.getInt("totalmoney"));
                ls.add(c);
            }
        } catch (SQLException e) {

        }

        return ls;
    }

    public List<Order> mostOrderPerDay() {
        ArrayList<Order> ls = new ArrayList<>();
        String sql = "SELECT top(1)  count([date]) as'Num',date\n"
                + "  FROM [dbo].[Order1] \n"
                + "  where totalmoney!=0 \n"
                + "  group by [date] \n"
                + "  order by 'Num' desc";

        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order c = new Order();
                c.setNum(rs.getInt("Num"));
                c.setDate(rs.getString("date"));
                ls.add(c);

            }
        } catch (SQLException e) {
        }
//        int count = ls.get(0).getNum();
        return ls;
    }

    public List<Order> leastOrderPerDay() {
        ArrayList<Order> ls = new ArrayList<>();
        String sql = "SELECT top(1)  count([date]) as'Num',date\n"
                + "  FROM [dbo].[Order1] \n"
                + "  where totalmoney!=0 \n"
                + "  group by [date] \n"
                + "  order by 'Num' ";

        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order c = new Order();
                c.setNum(rs.getInt("Num"));
                c.setDate(rs.getString("date"));
                ls.add(c);

            }
        } catch (SQLException e) {
        }
//        int count = ls.get(0).getNum();
        return ls;
    }

    public List<Order> AllDate() {
        ArrayList<Order> ls = new ArrayList<>();
        String sql = "SELECT  [date]\n"
                + "  FROM [dbo].[Order1]\n"
                + "  group by date";

        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order c = new Order();
                c.setDate(rs.getString("date"));
                ls.add(c);

            }
        } catch (SQLException e) {
        }
//        int count = ls.get(0).getNum();
        return ls;
    }

    public String currentDate() {

        String sql = "SELECT top(1) [date]\n"
                + "  FROM [dbo].[Order1]\n"
                + "  group by date\n"
                + "  order by date desc";
        String date = null;
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order c = new Order();
                c.setDate(rs.getString("date"));
                date = c.getDate();

            }
        } catch (SQLException e) {
        }
//        int count = ls.get(0).getNum();
        return date;
    }

    public List<OrderDateDetail> OrderDateDetailInXDays(int day) {
        ArrayList<OrderDateDetail> ls = new ArrayList<>();

//        double sum = 0;
        try {
            String sql = "SELECT date\n"
                    + ",p.[name]\n"
                    + ",[quantity]\n"
                    + ",p.[price]\n"
                    + ", totalmoney\n"
                    + "FROM [dbo].[Order1] o join OrderLine1 ol\n"
                    + "on o.id = ol.oid\n"
                    + "join Product1 p\n"
                    + "on ol.pid= p.ID\n"
                    + "where totalmoney!=0 and day([date])<=day(getdate()) and day(date) >=day(getdate()-?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, day);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                OrderDateDetail c = new OrderDateDetail();
                c.setDate(rs.getString("date"));
                c.setpName(rs.getString("name"));
                c.setQuantity(rs.getInt("quantity"));
                c.setPrice(rs.getInt("price"));
                c.setTotal(rs.getInt("totalmoney"));
                ls.add(c);
            }
        } catch (SQLException e) {

        }

        return ls;
    }

    public List<OrderDateDetail> OrderDateDetailInAllDays() {
        ArrayList<OrderDateDetail> ls = new ArrayList<>();

//        double sum = 0;
        try {
            String sql = "SELECT date\n"
                    + ",p.[name]\n"
                    + ",[quantity]\n"
                    + ",p.[price]\n"
                    + ", totalmoney\n"
                    + "FROM [dbo].[Order1] o join OrderLine1 ol\n"
                    + "on o.id = ol.oid\n"
                    + "join Product1 p\n"
                    + "on ol.pid= p.ID\n"
                    + "where totalmoney!=0";
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                OrderDateDetail c = new OrderDateDetail();
                c.setDate(rs.getString("date"));
                c.setpName(rs.getString("name"));
                c.setQuantity(rs.getInt("quantity"));
                c.setPrice(rs.getInt("price"));
                c.setTotal(rs.getInt("totalmoney"));
                ls.add(c);
            }
        } catch (SQLException e) {

        }

        return ls;
    }

    public List<OrderDateDetail> RevenueUp() {
        ArrayList<OrderDateDetail> ls = new ArrayList<>();

//        double sum = 0;
        try {
            String sql = "SELECT date\n"
                    + ",p.[name]\n"
                    + ",[quantity]\n"
                    + ",p.[price]\n"
                    + ", totalmoney\n"
                    + "FROM [dbo].[Order1] o join OrderLine1 ol\n"
                    + "on o.id = ol.oid\n"
                    + "join Product1 p\n"
                    + "on ol.pid= p.ID\n"
                    + "where totalmoney!=0 \n"
                    + "order by totalmoney";
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                OrderDateDetail c = new OrderDateDetail();
                c.setDate(rs.getString("date"));
                c.setpName(rs.getString("name"));
                c.setQuantity(rs.getInt("quantity"));
                c.setPrice(rs.getInt("price"));
                c.setTotal(rs.getInt("totalmoney"));
                ls.add(c);
            }
        } catch (SQLException e) {

        }

        return ls;
    }

    public List<OrderDateDetail> RevenueDown() {
        ArrayList<OrderDateDetail> ls = new ArrayList<>();

//        double sum = 0;
        try {
            String sql = "SELECT date\n"
                    + ",p.[name]\n"
                    + ",[quantity]\n"
                    + ",p.[price]\n"
                    + ", totalmoney\n"
                    + "FROM [dbo].[Order1] o join OrderLine1ol\n"
                    + "on o.id = ol.oid\n"
                    + "join Product1p\n"
                    + "on ol.pid= p.ID\n"
                    + "where totalmoney!=0 \n"
                    + "order by totalmoney desc";
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                OrderDateDetail c = new OrderDateDetail();
                c.setDate(rs.getString("date"));
                c.setpName(rs.getString("name"));
                c.setQuantity(rs.getInt("quantity"));
                c.setPrice(rs.getInt("price"));
                c.setTotal(rs.getInt("totalmoney"));
                ls.add(c);
            }
        } catch (SQLException e) {

        }

        return ls;
    }

    public List<Order> OrderPerDayUp() {
        List<Order> ls = new ArrayList<>();
        String sql = "SELECT  count([date]) as'Num'\n"
                + ",date\n"
                + "FROM [dbo].[Order1]\n"
                + "where totalmoney!=0\n"
                + "group by [date]\n"
                + "order by 'Num'";

//        double sum = 0;
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Order c = new Order();
                c.setDate(rs.getString("date"));
                c.setNum(rs.getInt("Num"));
                ls.add(c);
            }
        } catch (SQLException e) {

        }

        return ls;
    }

    public List<Order> OrderPerDayDown() {
        List<Order> ls = new ArrayList<>();
        String sql = "SELECT  count([date]) as'Num'\n"
                + ",date\n"
                + "FROM [dbo].[Order1]\n"
                + "where totalmoney!=0\n"
                + "group by [date]\n"
                + "order by 'Num' desc";

//        double sum = 0;
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Order c = new Order();
                c.setDate(rs.getString("date"));
                c.setNum(rs.getInt("Num"));
                ls.add(c);
            }
        } catch (SQLException e) {

        }

        return ls;
    }

    public List<OrderDateDetail> leastRevenuePerDay() {
        ArrayList<OrderDateDetail> ls = new ArrayList<>();

//        double sum = 0;
        try {
            String sql = "	SELECT top(1) date\n"
                    + "	,p.[name]\n"
                    + "	,[quantity]\n"
                    + "	,p.[price]\n"
                    + "	, totalmoney\n"
                    + "	FROM [dbo].[Order1] o join OrderLine1 ol\n"
                    + "	on o.id = ol.oid\n"
                    + "	join Product1 p\n"
                    + "	on ol.pid= p.ID\n"
                    + "	where totalmoney!=0 \n"
                    + "	order by totalmoney ";
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                OrderDateDetail c = new OrderDateDetail();
                c.setDate(rs.getString("date"));
                c.setpName(rs.getString("name"));
                c.setQuantity(rs.getInt("quantity"));
                c.setPrice(rs.getInt("price"));
                c.setTotal(rs.getInt("totalmoney"));
                ls.add(c);
            }
        } catch (SQLException e) {

        }

        return ls;
    }

    public List<OrderDateDetail> mostRevenuePerDay() {
        ArrayList<OrderDateDetail> ls = new ArrayList<>();

//        double sum = 0;
        try {
            String sql = "	SELECT top(1) date\n"
                    + "	,p.[name]\n"
                    + "	,[quantity]\n"
                    + "	,p.[price]\n"
                    + "	, totalmoney\n"
                    + "	FROM [dbo].[Order1] o join OrderLine1 ol\n"
                    + "	on o.id = ol.oid\n"
                    + "	join Product1 p\n"
                    + "	on ol.pid= p.ID\n"
                    + "	where totalmoney!=0 \n"
                    + "	order by totalmoney desc";
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                OrderDateDetail c = new OrderDateDetail();
                c.setDate(rs.getString("date"));
                c.setpName(rs.getString("name"));
                c.setQuantity(rs.getInt("quantity"));
                c.setPrice(rs.getInt("price"));
                c.setTotal(rs.getInt("totalmoney"));
                ls.add(c);
            }
        } catch (SQLException e) {

        }

        return ls;
    }

    public List<OrderTag> getAllOrderTag() {
        String sql = "select o.id, u.id, u.fullname, u.phonenum, o.date, o.totalmoney from Order1 o "
                + "join User1 u on o.cid = u.id where totalmoney > 0 order by date desc";
        List<OrderTag> ordertags = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderTag ot = new OrderTag();
                ot.setoId(rs.getInt(1));
                ot.setuId(rs.getInt(2));
                ot.setuName(rs.getString(3));
                ot.setuPhone(rs.getString(4));
                ot.setDate(rs.getDate(5));
                ot.setTotalMoney(rs.getDouble(6));
                ordertags.add(ot);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return ordertags;
    }
    
    public List<OrderTag> getAllOrderTagPaging(int index) {
        String sql = "select o.id, u.id, u.fullname, u.phonenum, o.date, o.totalmoney from Order1 o "
                + "join User1 u on o.cid = u.id where totalmoney > 0 order by date desc";
        sql+= " OFFSET ? ROWS FETCH NEXT 3  ROWS ONLY";
        List<OrderTag> ordertags = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 3);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderTag ot = new OrderTag();
                ot.setoId(rs.getInt(1));
                ot.setuId(rs.getInt(2));
                ot.setuName(rs.getString(3));
                ot.setuPhone(rs.getString(4));
                ot.setDate(rs.getDate(5));
                ot.setTotalMoney(rs.getDouble(6));
                ordertags.add(ot);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return ordertags;
    }
    
    public List<OrderLine> getOrderLineByOId(int oId){
        String sql = "select ol.oid, p.id, p.name, ol.quantity, ol.price, ol.price*ol.quantity from OrderLine1 ol "
                + "join Product1 p on p.ID = ol.pid where ol.oid = ?";
        List<OrderLine> orderLines = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,oId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderLine ol = new OrderLine();
                ol.setoId(rs.getInt(1));
                ol.setpName(rs.getString(3));
                ol.setQuantity(rs.getInt(4));
                ol.setPrice(rs.getDouble(5));
                ol.setTotal(rs.getDouble(6));
                orderLines.add(ol);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return orderLines;
    }
    
    public static void main(String[] args) {
        List<OrderTag> list = INSTANCE.getAllOrderTagPaging(1);
        for(OrderTag o:list){
            System.out.println(o.getoId());
        }
    }
}
