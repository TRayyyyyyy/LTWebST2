package LTWebST2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import LTWebST2.Models.UserModel;
import LTWebST2.Models.DBConnection;

public class UserDaoImp implements IUserDao {

	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	@Override
	public UserModel findByUsername(String username) {
		String sql = "select * from users where username = ?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			UserModel user = new UserModel();
			while(rs.next()) {
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setFullname(rs.getString("fullname"));
				user.setPassword(rs.getString("password"));
				user.setImage(rs.getString("image"));
				user.setRoleid(rs.getInt("roleid"));
				user.setPhone(rs.getString("phone"));
			}
			return user;
		} 
		catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	

	@Override

	public void insert(UserModel user) {
	    String sql = "insert into users (fullname, email, username, password, roleid) values (?, ?, ?, ?, ?)";
	    try {
	        conn = new DBConnection().getConnection();
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, user.getFullname());  
	        ps.setString(2, user.getEmail());   
	        ps.setString(3, user.getUsername());  
	        ps.setString(4, user.getPassword()); 
	        ps.setInt(5, 3); 
	        
	        ps.executeUpdate();
	    } catch(Exception ex) {
	        ex.printStackTrace();
	    }
	}
	@Override
	public List<UserModel> findAll() {
		List<UserModel> users = new ArrayList<>();
        String sql = "Select * from [users] w";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                UserModel user = new UserModel();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setFullname(rs.getString("fullname"));
                user.setPassword(rs.getString("password"));
                user.setRoleid(rs.getInt("roleid"));
                user.setPhone(rs.getString("phone"));
                user.setCreateDate(rs.getDate("createdDate"));
                users.add(user);
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}

	@Override
	public boolean checkExsitEmail(String email) {
		boolean check = false;
		String sql = "Select * from users where email = ?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if(rs.next()) {
				check=true;
			}
			ps.close();
			conn.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return check;
	}

	@Override
	public boolean checkExistUserName(String username) {
		boolean check = false;
		String sql = "Select * from users where username = ?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if(rs.next()) {
				check=true;
			}
			ps.close();
			conn.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return check;
	}

	@Override
	public UserModel findByUsernameAndEmail(String username, String email) {
		String sql = "SELECT * FROM users WHERE username = ? AND email = ?";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        UserModel user = null;

        try {
            con = new DBConnection().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, email);
            rs = ps.executeQuery();

            if (rs.next()) {
                user = new UserModel();
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setFullname(rs.getString("fullname"));
                user.setPassword(rs.getString("password"));
                user.setRoleid(rs.getInt("roleid"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return user;
	}

	@Override
	public boolean updatePassword(String username, String password) {
		String sql = "UPDATE users SET password = ? WHERE username = ?";
        Connection con = null;
        PreparedStatement ps = null;
        boolean isUpdated = false;

        try {
            con = new DBConnection().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, password);
            ps.setString(2, username);
            int rowsAffected = ps.executeUpdate();

       
            if (rowsAffected > 0) {
                isUpdated = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return isUpdated;
	}



}
