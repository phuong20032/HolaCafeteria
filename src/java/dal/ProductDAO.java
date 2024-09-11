package dal;

import static dal.DAO.INSTANCE;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Product;

public class ProductDAO {

    public static ProductDAO INSTANCE = new ProductDAO();

    private Connection con;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private ProductDAO() {
        if (INSTANCE == null) {
            try {
                con = new DBContext().getConnection(); // mo ket noi voi co so du lieu
            } catch (Exception e) {
                status = "Error at Connection " + e.getMessage();
            }
        }
    }

    public List<Product> getAllProducts() {
        String sql = "SELECT [ID]\n"
                + "      ,[name]\n"
                + "      ,[price]\n"
                + "      ,[cid]\n"
                + "      ,[image]\n"
                + "      ,[describe]\n"
                + "  FROM [dbo].[Product1]";

        List<Product> products = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product c = new Product();
                c.setId(rs.getString("ID"));
                c.setName(rs.getString("name"));
                c.setPrice(rs.getInt("price"));
                c.setDescribe(rs.getString("describe"));
                c.setImage(rs.getString("image"));
                c.setCid(rs.getInt("cid"));
                products.add(c);
            }
        } catch (Exception e) {
            status = "Error at read Product " + e.getMessage();
        }

        return products;
    }

    public Product getProductById(String id) {
        String sql = "SELECT [ID]\n"
                + "      ,[name]\n"
                + "      ,[price]\n"
                + "      ,[cid]\n"
                + "      ,[image]\n"
                + "      ,[describe]\n"
                + "  FROM [dbo].[Product1] Where id=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Product p = new Product();
                p.setId(rs.getString("ID"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getInt("price"));
                p.setDescribe(rs.getString("describe"));
                p.setImage(rs.getString("image"));
                p.setCid(rs.getInt("cid"));
                return p;
            }
        } catch (Exception e) {
            status = "Error at read Product " + e.getMessage();
        }

        return null;
    }

    public void delete(String id) {
        String sql = "Delete from [dbo].[Product1]\n"
                + "     where ID =? ";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            status = "Error at delete product " + e.getMessage();
        }
    }

    public void insert(Product c) {
        String sql = "INSERT INTO [dbo].[Product1]\n"
                + "           ([name]\n"
                + "           ,[price]\n"
                + "           ,[cid]\n"
                + "           ,[describe]\n"
                + "           ,[image])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getName());
            ps.setInt(2, c.getPrice());
            ps.setInt(3, c.getCid());
            ps.setString(4, c.getDescribe());
            ps.setString(5, c.getImage());

            ps.executeUpdate();
        } catch (SQLException e) {
            status = "Error at insert product " + e.getMessage();
        }
    }

    public void update(Product c) {
        String sql = "UPDATE [dbo].[Product1]\n"
                + "   SET [name] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[describe] = ?\n"
                + "      ,[image] = ?\n"
                + " WHERE ID =?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getName());
            ps.setInt(2, c.getPrice());
            ps.setString(3, c.getDescribe());
            ps.setString(4, c.getImage());
            ps.setString(5, c.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            status = "Error at update product " + e.getMessage();
        }
    }

    public List<Product> randomRelative(String id, String cid) throws SQLException {
        ArrayList<Product> ls = new ArrayList<>();
        try {
            String sql = "SELECT top (3) [ID]\n"
                    + "      ,[name]\n"
                    + "      ,[price]\n"
                    + "      ,[cid]\n"
                    + "      ,[image]\n"
                    + "      ,[describe]\n"
                    + "  FROM [dbo].[Product1]\n"
                    + "  where id!= ? and cid = ? \n"
                    + "  order by NEWID()";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, id);
            st.setString(2, cid);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Product c = new Product();
                c.setId(rs.getString("ID"));
                c.setName(rs.getString("name"));
                c.setPrice(rs.getInt("price"));
                c.setCid(rs.getInt("cid"));
                c.setDescribe(rs.getString("describe"));
                c.setImage(rs.getString("image"));
                ls.add(c);

            }

        } catch (SQLException e) {
        }
        return ls;
    }

    //so luong nhieu nhat ngay hnay
    // ngay hnay last =0, 3 ngay qua: last=2, 7 ngay qua: last =6
    public List<Product> mostSoldInXDay(int last) throws SQLException {
        List<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT TOP (3)[date]\n"
                    + "	  ,p.cid\n"
                    + "	  ,p.ID\n"
                    + "	  ,p.name\n"
                    + "	  ,p.price\n"
                    + "	  ,p.image\n"
                    + "	  ,p.describe\n"
                    + "      ,ol.quantity\n"
                    + "      ,[totalmoney]\n"
                    + "  FROM [dbo].[Order1] o join OrderLine1 ol\n"
                    + "  on o.id = ol.oid\n"
                    + "  join Product1 p\n"
                    + "  on p.ID = ol.pid\n"
                    + "  where day(date) between day(GETDATE()-?) and  day(GETDATE()) and totalmoney!=0\n"
                    + "  order by ol.quantity desc";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, last);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product c = new Product();
                c.setId(rs.getString("ID"));
                c.setName(rs.getString("name"));
                c.setPrice(rs.getInt("price"));
                c.setCid(rs.getInt("cid"));
                c.setDescribe(rs.getString("describe"));
                c.setImage(rs.getString("image"));
                c.setDate(rs.getString("date"));
                c.setQuantity(rs.getInt("quantity"));
                c.setTotal(rs.getInt("totalmoney"));
                products.add(c);
            }

        } catch (SQLException e) {
        }
        return products;
    }

    public List<Product> mostRevenueInXDay(int last) throws SQLException {
        List<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT TOP (3)[date]\n"
                    + "		  ,p.cid\n"
                    + "		  ,p.ID\n"
                    + "		  ,p.name\n"
                    + "		  ,p.price\n"
                    + "		  ,p.image\n"
                    + "		  ,p.describe\n"
                    + "		  ,ol.quantity\n"
                    + "		  ,[totalmoney]\n"
                    + "	  FROM [dbo].[Order1] o join OrderLine1 ol\n"
                    + "	  on o.id = ol.oid\n"
                    + "	  join Product1 p\n"
                    + "	  on p.ID = ol.pid\n"
                    + "	  where day(date) between day(GETDATE()-?) and  day(GETDATE()) and totalmoney!=0\n"
                    + "	  order by totalmoney desc";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, last);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product c = new Product();
                c.setId(rs.getString("ID"));
                c.setName(rs.getString("name"));
                c.setPrice(rs.getInt("price"));
                c.setCid(rs.getInt("cid"));
                c.setDescribe(rs.getString("describe"));
                c.setImage(rs.getString("image"));
                c.setDate(rs.getString("date"));
                c.setQuantity(rs.getInt("quantity"));
                c.setTotal(rs.getInt("totalmoney"));
                products.add(c);
            }

        } catch (SQLException e) {
        }
        return products;
    }

    public List<Product> mostSold() throws SQLException {
        List<Product> products = new ArrayList<>();
        try {
            String sql = "select top(3) [pid]\n"
                    + "	  ,[name]\n"
                    + "      ,p.[price]\n"
                    + "      ,[cid]\n"
                    + "      ,[image]\n"
                    + "      ,[describe]\n"
                    + "  FROM OrderLine1 ol join product1 p\n"
                    + "  on ol.pid = p.ID\n"
                    + "  order by quantity desc";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product c = new Product();
                c.setId(rs.getString("pid"));
                c.setName(rs.getString("name"));
                c.setPrice(rs.getInt("price"));
                c.setCid(rs.getInt("cid"));
                c.setDescribe(rs.getString("describe"));
                c.setImage(rs.getString("image"));
                products.add(c);
            }

        } catch (SQLException e) {
        }
        return products;
    }

    public List<Product> topFeature() throws SQLException {
        List<Product> products = new ArrayList<>();
        try {
            String sql = "select top(3) [pid]\n"
                    + "	  ,[name]\n"
                    + "      ,p.[price]\n"
                    + "      ,p.[cid]\n"
                    + "      ,[image]\n"
                    + "      ,[describe]\n"
                    + "  FROM OrderLine1 ol join product1 p\n"
                    + "  on ol.pid = p.ID\n"
                    + "  join [Order1] o \n"
                    + "  on o.id = ol.oid\n"
                    + "  order by o.totalmoney desc";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product c = new Product();
                c.setId(rs.getString("pid"));
                c.setName(rs.getString("name"));
                c.setPrice(rs.getInt("price"));
                c.setCid(rs.getInt("cid"));
                c.setDescribe(rs.getString("describe"));
                c.setImage(rs.getString("image"));
                products.add(c);
            }

        } catch (SQLException e) {
        }
        return products;
    }

    public List<Product> topNew() throws SQLException {
        List<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT TOP (3) [ID]\n"
                    + "      ,[name]\n"
                    + "      ,[price]\n"
                    + "      ,[cid]\n"
                    + "      ,[image]\n"
                    + "      ,[describe]\n"
                    + "  FROM [dbo].[1]\n"
                    + "  order by id desc";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product c = new Product();
                c.setId(rs.getString("ID"));
                c.setName(rs.getString("name"));
                c.setPrice(rs.getInt("price"));
                c.setCid(rs.getInt("cid"));
                c.setDescribe(rs.getString("describe"));
                c.setImage(rs.getString("image"));
                products.add(c);
            }

        } catch (SQLException e) {
        }
        return products;
    }

    public List<Product> searchPaging(int cid, Integer price, String keyword, String sortby, int index) {
        String sql;
        if (sortby == null || sortby.equals("") || sortby.equals("feature")) {
            sql = "select p.* from Product1 p LEFT JOIN OrderLine1 ol ON p.ID = ol.pid\n"
                    + "WHERE 1=1 ";

            if (cid != 0) {
                sql += " and p.cid = " + cid;
            }
            if (keyword != null && !keyword.equals("")) {
                sql += " and p.name like '%" + keyword + "%' ";
            }
            if (price != null) {
                switch (price) {
                    case 1:
                        sql += " and p.price >= " + 10000;
                        sql += " and p.price < " + 30000;
                        break;
                    case 2:
                        sql += " and p.price >= " + 30000;
                        sql += " and p.price < " + 50000;
                        break;
                    case 3:
                        sql += " and p.price >= " + 50000;
                        break;
                }
            }
            sql += " GROUP BY p.ID, p.price, p.name, p.cid, p.describe, p.image\n"
                    + "ORDER BY SUM(ol.quantity) desc";
        } else {

            sql = "Select * from productHE171956 where 1=1";

            if (cid != 0) {
                sql += " and cid = " + cid;
            }
            if (keyword != null && !keyword.equals("")) {
                sql += " and name like '%" + keyword + "%' ";
            }
            if (price != null) {
                switch (price) {
                    case 1:
                        sql += " and price >= " + 10000;
                        sql += " and price < " + 30000;
                        break;
                    case 2:
                        sql += " and price >= " + 30000;
                        sql += " and price < " + 50000;
                        break;
                    case 3:
                        sql += " and price >= " + 50000;
                        break;
                }
            }
            switch (sortby) {
                case "new":
                    sql += "order by id desc";
                    break;
                case "pHL":
                    sql += "order by price desc";
                    break;
                case "pLH":
                    sql += "order by price asc";
                    break;
            }
        }
        sql += " OFFSET ? ROWS FETCH NEXT 6  ROWS ONLY ";
        List<Product> products = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 6);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product c = new Product();
                c.setId(rs.getString("ID"));
                c.setName(rs.getString("name"));
                c.setPrice(rs.getInt("price"));
                c.setDescribe(rs.getString("describe"));
                c.setImage(rs.getString("image"));
                c.setCid(rs.getInt("cid"));
                products.add(c);
            }
        } catch (Exception e) {
            status = "Error at read Product " + e.getMessage();
        }

        return products;
    }

    public List<Product> search(int cid, Integer price, String keyword, String sortby) {
        String sql;
        if (sortby == null || sortby.equals("") || sortby.equals("feature")) {
            sql = "select p.* from Product1 p LEFT JOIN OrderLine1 ol ON p.ID = ol.pid\n"
                    + "WHERE 1=1 ";

            if (cid != 0) {
                sql += " and p.cid = " + cid;
            }
            if (keyword != null && !keyword.equals("")) {
                sql += " and p.name like '%" + keyword + "%' ";
            }
            if (price != null) {
                switch (price) {
                    case 1:
                        sql += " and p.price >= " + 10000;
                        sql += " and p.price < " + 30000;
                        break;
                    case 2:
                        sql += " and p.price >= " + 30000;
                        sql += " and p.price < " + 50000;
                        break;
                    case 3:
                        sql += " and p.price >= " + 50000;
                        break;
                }
            }
            sql += " GROUP BY p.ID, p.price, p.name, p.cid, p.describe, p.image\n"
                    + "ORDER BY SUM(ol.quantity) desc";
        } else {

            sql = "Select * from productHE171956 where 1=1";

            if (cid != 0) {
                sql += " and cid = " + cid;
            }
            if (keyword != null && !keyword.equals("")) {
                sql += " and name like '%" + keyword + "%' ";
            }
            if (price != null) {
                switch (price) {
                    case 1:
                        sql += " and price >= " + 10000;
                        sql += " and price < " + 30000;
                        break;
                    case 2:
                        sql += " and price >= " + 30000;
                        sql += " and price < " + 50000;
                        break;
                    case 3:
                        sql += " and price >= " + 50000;
                        break;
                }
            }
            switch (sortby) {
                case "new":
                    sql += "order by id desc";
                    break;
                case "pHL":
                    sql += "order by price desc";
                    break;
                case "pLH":
                    sql += "order by price asc";
                    break;
            }
        }
        List<Product> products = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product c = new Product();
                c.setId(rs.getString("ID"));
                c.setName(rs.getString("name"));
                c.setPrice(rs.getInt("price"));
                c.setDescribe(rs.getString("describe"));
                c.setImage(rs.getString("image"));
                c.setCid(rs.getInt("cid"));
                products.add(c);
            }
        } catch (Exception e) {
            status = "Error at read Product " + e.getMessage();
        }

        return products;
    }

    public Product getHighestEarningProduct() {
        String sql = "select top(1) [pid]\n"
                + "	  ,[name]\n"
                + "      ,p.[price]\n"
                + "      ,p.[cid]\n"
                + "      ,[image]\n"
                + "      ,[describe]\n"
                + "	  ,o.totalmoney\n"
                + "  FROM OrderLine1  ol join product1 p\n"
                + "  on ol.pid = p.ID\n"
                + "  join [Order1 ] o \n"
                + "  on o.id = ol.oid\n"
                + "  order by o.totalmoney desc";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Product c = new Product();
                c.setId(rs.getString("pid"));
                c.setName(rs.getString("name"));
                c.setPrice(rs.getInt("price"));
                c.setDescribe(rs.getString("describe"));
                c.setImage(rs.getString("image"));
                c.setCid(rs.getInt("cid"));
                c.setTotal(rs.getInt("totalmoney"));

                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Product getLowestEarningProduct() {
        String sql = "select top(1) [pid]\n"
                + "	  ,[name]\n"
                + "      ,p.[price]\n"
                + "      ,p.[cid]\n"
                + "      ,[image]\n"
                + "      ,[describe]\n"
                + "	  ,o.totalmoney\n"
                + "  FROM OrderLine1 ol join product1 p\n"
                + "  on ol.pid = p.ID\n"
                + "  join [Order1] o \n"
                + "  on o.id = ol.oid\n"
                + "  order by o.totalmoney  ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Product c = new Product();
                c.setId(rs.getString("pid"));
                c.setName(rs.getString("name"));
                c.setPrice(rs.getInt("price"));
                c.setDescribe(rs.getString("describe"));
                c.setImage(rs.getString("image"));
                c.setCid(rs.getInt("cid"));
                c.setTotal(rs.getInt("totalmoney"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Product getMinPrice() {
        String sql = "SELECT top (1)[ID]\n"
                + "      ,[name]\n"
                + "      ,[price]\n"
                + "      ,[cid]\n"
                + "      ,[image]\n"
                + "      ,[describe]\n"
                + "  FROM [dbo].[Product1]\n"
                + "  order by price";
        Product c = new Product();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                c.setId(rs.getString("ID"));
                c.setName(rs.getString("name"));
                c.setPrice(rs.getInt("price"));
                c.setCid(rs.getInt("cid"));
                c.setDescribe(rs.getString("describe"));
                c.setImage(rs.getString("image"));
            }
        } catch (Exception e) {
        }
        return c;
    }

    public Product getMaxPrice() {
        String sql = "SELECT top (1)[ID]\n"
                + "      ,[name]\n"
                + "      ,[price]\n"
                + "      ,[cid]\n"
                + "      ,[image]\n"
                + "      ,[describe]\n"
                + "  FROM [dbo].[Product1]\n"
                + "  order by price desc";
        Product c = new Product();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                c.setId(rs.getString("ID"));
                c.setName(rs.getString("name"));
                c.setPrice(rs.getInt("price"));
                c.setCid(rs.getInt("cid"));
                c.setDescribe(rs.getString("describe"));
                c.setImage(rs.getString("image"));
            }
        } catch (Exception e) {
        }
        return c;
    }
}
