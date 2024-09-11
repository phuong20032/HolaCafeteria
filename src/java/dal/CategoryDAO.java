/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import static dal.DAO.INSTANCE;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Category;

/**
 *
 * @author ngoba
 */
public class CategoryDAO {

    public static CategoryDAO INSTANCE = new CategoryDAO();

    private Connection con;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private CategoryDAO() {
        if (INSTANCE == null) {
            try {
                con = new DBContext().getConnection(); // mo ket noi voi co so du lieu
            } catch (Exception e) {
                status = "Error at Connection " + e.getMessage();
            }
        }
    }

    public boolean isExist(String name) {
        List<Category> categoriesList = CategoryDAO.INSTANCE.getAll();
        for (Category category : categoriesList) {
            if (category.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public List<Category> getAll() {
        List<Category> ls = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "  FROM [dbo].[Category1]";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));

                ls.add(c);
            }
        } catch (SQLException e) {
            status = "Error at read Category " + e.getMessage();
        }
        return ls;
    }

    public Category getCategoryById(int id) {
        String sql = "select [id]\n"
                + "           ,[name]\n"
                + "   FROM [dbo].[Category1] where id=?";
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
        } catch (SQLException e) {
            status = "Error at read Category " + e.getMessage();
        }
        return null;
    }

    public void delete(int id) {
        String sql = "Delete from [dbo].[Category1]\n"
                + "     where id =? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            status = "Error at delete Category " + e.getMessage();
        }
    }

    public void insert(Category c) {
        String sql = "INSERT INTO [dbo].[Category1]\n"
                + "           ([name])\n"
                + "     VALUES\n"
                + "           (?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, c.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            status = "Error at insert Category " + e.getMessage();
        }
    }

    // update 
    public void update(Category c) {
        String sql = "UPDATE [dbo].[Category1]\n"
                + "   SET [name] = ?\n"
                + " WHERE id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getName());

            ps.setInt(2, c.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            status = "Error at update Category " + e.getMessage();
        }
    }
}
