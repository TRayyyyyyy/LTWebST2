package LTWebST2.dao;

import java.util.List;

import LTWebST2.Models.CategoryModel;

public interface ICategoryDao {
	List<CategoryModel> findAll();
	CategoryModel findbyID(int id);
	CategoryModel findByName(String keyword);
	List<CategoryModel> serachByName(String keyword);	

	void insert(CategoryModel category);
	void update(CategoryModel category);
	void delete(int id);
	void updateStatus(int id, int status);
}
