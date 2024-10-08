package LTWebST2.dao;

import java.util.List;
import java.util.Locale.Category;

import LTWebST2.Models.CategoryModel;

public class CategoryServiceImp implements ICategoryService {

	public ICategoryDao cateDao = new CategoryDaoImp();
	@Override
	public void insert(CategoryModel category) {
		cateDao.insert(category);
		
	}

	@Override
	public void update(CategoryModel category) {
		// TODO Auto-generated method stub
		cateDao.update(category);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		cateDao.delete(id);
	}

	@Override
	public List<CategoryModel> findAll() {
		// TODO Auto-generated method stub
		return cateDao.findAll();
	}

	@Override
	public CategoryModel findByID(int id) {
		// TODO Auto-generated method stub
		return cateDao.findbyID(id);
	}

	@Override
	public CategoryModel findName(String keyword) {
		// TODO Auto-generated method stub
		return cateDao.findByName(keyword);	
	}

	@Override
	public List<CategoryModel> searchbyName(String keyword) {
		// TODO Auto-generated method stub
		return cateDao.serachByName(keyword);
	}

	@Override
	public void updateStatus(int id, int status) {
		// TODO Auto-generated method stub
		cateDao.updateStatus(id, status);
	}

	

}