package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import model.Category;
import model.Product;

public class DAO {

    public static DAO INSTANCE = new DAO();

    private Connection con;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private DAO() {
        if (INSTANCE == null) {
            try {
                con = new DBContext().getConnection(); // mo ket noi voi co so du lieu
            } catch (Exception e) {
                status = "Error at Connection " + e.getMessage();
            }
        }
    }

    public List<Category> getAllCategories() {
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "  FROM [dbo].[Category1]";

        List<Category> categories = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Category c = new Category();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                categories.add(c);
            }
        } catch (Exception e) {
            status = "Error at read Category " + e.getMessage();
        }
        return categories;
    }

    public List<Product> getProductsByCid(int cid) {
        String sql = "SELECT [ID]\n"
                + "      ,[name]\n"
                + "      ,[price]\n"
                + "      ,[cid]\n"
                + "      ,[image]\n"
                + "      ,[describe]\n"
                + "  FROM [dbo].[Product1] Where cid=?";

        List<Product> products = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getString("id"));
                p.setName(rs.getString("name"));

                p.setPrice(rs.getInt("price"));

                p.setDescribe(rs.getString("describe"));
                p.setImage(rs.getString("image"));
                Category c = getCategoryById(rs.getInt("cid"));
                p.setCategory(c);
                products.add(p);
            }
        } catch (Exception e) {
            status = "Error at read Product " + e.getMessage();
        }

        return products;
    }

    public Category getCategoryById(int id) {
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "  FROM [dbo].[Category1] Where id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Category c = new Category();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));

                return c;
            }
        } catch (Exception e) {
            status = "Error at read Category " + e.getMessage();
        }
        return null;
    }
}
