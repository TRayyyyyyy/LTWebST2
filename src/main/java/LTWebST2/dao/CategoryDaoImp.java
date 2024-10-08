package LTWebST2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import LTWebST2.Models.CategoryModel;
import LTWebST2.Models.DBConnection;

public class CategoryDaoImp implements ICategoryDao {

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	public List<CategoryModel> findAll() {
		String sql = "Select * from categories";
		List<CategoryModel> list = new ArrayList<>();
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				CategoryModel category = new CategoryModel();
				category.setCategoryid(rs.getInt("categoryid"));
				category.setCategoryname(rs.getString("categoryname"));
				category.setImages(rs.getString("images"));
				category.setStatus(rs.getInt("status"));
				list.add(category);
			}
			

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return list;

	}

	@Override
	public void insert(CategoryModel category) {
		String query = "INSERT INTO categories (categoryname, images, status) VALUES (?, ?, ?)";

		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, category.getCategoryname());
			ps.setString(2, category.getImages());
			ps.setInt(3, category.getStatus());
			
			ps.executeUpdate();
			/*
			 * conn.close(); ps.close(); rs.close();
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(CategoryModel category) {
		// TODO Auto-generated method stub
		String query = "UPDATE categories SET categoryname = ?, images = ?, status = ? WHERE categoryid = ?";

		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, category.getCategoryname());
			ps.setString(2, category.getImages());
			ps.setInt(3, category.getStatus());
			ps.setInt(4, category.getCategoryid());
			ps.executeUpdate();
			/*
			 * conn.close(); ps.close(); rs.close();
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		String query = "DELETE categories WHERE categoryid = ?";

		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
			/*
			 * conn.close(); ps.close(); rs.close();
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public CategoryModel findByName(String keyword) {
		String query = "SELECT * FROM categories WHERE categoryname = ?";

		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, keyword);
			rs = ps.executeQuery();

			CategoryModel category = new CategoryModel();
			while (rs.next()) {
				category.setCategoryid(rs.getInt("categoryid"));
				category.setCategoryname(rs.getString("categoryname"));
				category.setImages(rs.getString("images"));
				category.setStatus(rs.getInt("status"));
			}
			conn.close();
			ps.close();
			rs.close();
			return category;
		} catch (Exception exc) {
			exc.printStackTrace();
		}

		return null;
	}

	@Override
	public void updateStatus(int id, int status) {
		String query = "UPDATE categories SET status = ? WHERE categoryid = ?";

		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, status);
			ps.setInt(2, id);
			ps.executeUpdate();
			conn.close();
			ps.close();
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<CategoryModel> serachByName(String keyword) {
		String sql = "Select * from categories where categoryname like ?";
		List<CategoryModel> list = new ArrayList<>();
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + keyword + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				CategoryModel category = new CategoryModel();
				category.setCategoryid(rs.getInt("categoryid"));
				category.setCategoryname(rs.getString("categoryname"));
				category.setImages(rs.getString("images"));
				category.setStatus(rs.getInt("status"));
				list.add(category);
			}
			conn.close();
			ps.close();
			rs.close();
			return list;

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	@Override
	public CategoryModel findbyID(int id) {
		String query = "SELECT * FROM categories WHERE categoryid = ?";

		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			CategoryModel category = new CategoryModel();
			while (rs.next()) {
				category.setCategoryid(rs.getInt("categoryid"));
				category.setCategoryname(rs.getString("categoryname"));
				category.setImages(rs.getString("images"));
				category.setStatus(rs.getInt("status"));
			}
			conn.close();
			ps.close();
			rs.close();
			return category;
		} catch (Exception exc) {
			exc.printStackTrace();
		}

		return null;
	}


}