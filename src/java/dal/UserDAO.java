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
import model.User;

/**
 *
 * @author ngoba
 */
public class UserDAO {

    public static UserDAO INSTANCE = new UserDAO();

    private Connection con;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private UserDAO() {
        if (INSTANCE == null) {
            try {
                con = new DBContext().getConnection(); // mo ket noi voi co so du lieu
            } catch (Exception e) {
                status = "Error at Connection " + e.getMessage();
            }
        }
    }

    public User check(String username, String password) {
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[fullname]\n"
                + "      ,[email]\n"
                + "      ,[phonenum]\n"
                + "      ,[role]\n"
                + "      ,[address]\n"
                + "      ,[password]\n"
                + "  FROM [dbo].[User1] where name = ? and password = ?";

        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("name"), rs.getString("password"), rs.getString("fullname"), rs.getString("email"), rs.getString("phonenum"), rs.getString("address"), rs.getInt("role"), rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public User checkUserEmail(String username, String email) {
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[fullname]\n"
                + "      ,[email]\n"
                + "      ,[phonenum]\n"
                + "      ,[role]\n"
                + "      ,[address]\n"
                + "      ,[password]\n"
                + "  FROM [dbo].[User1] where name = ? and email = ?";

        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, email);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("name"), rs.getString("password"), rs.getString("fullname"), rs.getString("email"), rs.getString("phonenum"), rs.getString("address"), rs.getInt("role"), rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }


    public User getCustomerBuyMost() {
        String sql = "select top(1) count(cid) as'NumOfOrds'\n"
                + "	  , cid\n"
                + "      ,[fullname]\n"
                + "      ,[email]\n"
                + "      ,[phonenum]\n"
                + "      ,[address]\n"
                + "  from [Order1] o join [User1] u\n"
                + "  on u.id = o.cid\n"
                + "  where o.totalmoney!=0\n"
                + "  group by o.cid, fullname, email, phonenum, [address]\n"
                + "\n"
                + "  order by COUNT(cid) desc";
        try {
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User c = new User();
                c.setId(rs.getInt("cid"));
                c.setAddress(rs.getString("address"));
                c.setPhonenum(rs.getString("phonenum"));
                c.setEmail(rs.getString("email"));
                c.setFullname(rs.getString("fullname"));
                c.setNumOfOrds(rs.getInt("NumOfOrds"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT [name]\n"
                + "      ,[passWord]\n"
                + "  FROM [dbo].[User1]";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User c = new User();
                c.setUsername(rs.getString("name"));
                c.setPassword(rs.getString("password"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void change(User a) {
        String sql = "UPDATE [dbo].[User1]\n"
                + "   SET [password] = ?\n"
                + " WHERE [name]=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, a.getPassword());
            st.setString(2, a.getUsername());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void changePro(User a) {
        String sql = "UPDATE [dbo].[User1]\n"
                + "   SET [fullname] = ?\n"
                + "      ,[email] = ?\n"
                + "      ,[phonenum] = ?\n"
                + "      ,[address] = ?\n"
                + " WHERE [name]=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, a.getFullname());
            st.setString(2, a.getEmail());
            st.setString(3, a.getPhonenum());
            st.setString(4, a.getAddress());
            st.setString(5, a.getUsername());

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        User u = INSTANCE.check("hqViet", "123");
        if (u == null) {
            System.out.println("null");
        } else {
            INSTANCE.changePro(new User(u.getUsername(),u.getPassword(),u.getFullname(),u.getEmail(),u.getPhonenum(),"JQK_123"));
        }
    }
    
    public boolean existedUs(String username) {
        String sql = "SELECT [name]\n"
                + "      ,[fullname]\n"
                + "      ,[email]\n"
                + "      ,[phonenum]\n"
                + "      ,[password]\n"
                + "  FROM [dbo].[User1] where name=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public boolean existedEmail(String email) {
        String sql = "SELECT [name]\n"
                + "      ,[fullname]\n"
                + "      ,[email]\n"
                + "      ,[phonenum]\n"
                + "      ,[password]\n"
                + "  FROM [dbo].[User1] where email=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public boolean existedPhoneNum(String phonenum) {
        String sql = "SELECT [name]\n"
                + "      ,[fullname]\n"
                + "      ,[email]\n"
                + "      ,[phonenum]\n"
                + "      ,[password]\n"
                + "  FROM [dbo].[User1] where phonenum=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, phonenum);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    // dang ki
    public void register(User a) {
        String sql = "INSERT INTO [dbo].[User1]\n"
                + "           ([name]\n"
                + "           ,[password]\n"
                + "           ,[fullname]\n"
                + "           ,[email]\n"
                + "           ,[phonenum])\n"
                + "             VALUES\n"
                + "           (?,?,?,?,?)";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, a.getUsername());
            st.setString(2, a.getPassword());
            st.setString(3, a.getFullname());
            st.setString(4, a.getEmail());
            st.setString(5, a.getPhonenum());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
