package LTWebST2.dao;

import java.util.List;

import LTWebST2.Models.CategoryModel;

public interface ICategoryService {
	List<CategoryModel>findAll();
	CategoryModel findByID(int id);
	CategoryModel findName(String keyword);
	List<CategoryModel>searchbyName(String keyword);
	void insert(CategoryModel category);
	void update(CategoryModel category);
	void delete(int id);
	void updateStatus(int id,int status);
	

}
