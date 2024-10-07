package LTWebST2.Controller;

import java.io.IOException;
import java.util.List;

import LTWebST2.Models.CategoryModel;
import LTWebST2.dao.CategoryServiceImp;
import LTWebST2.dao.ICategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/admin/categories", "/admin/category/add", "/admin/category/insert", "/admin/category/edit", "/admin/category/update", "/admin/category/delete", "/admin/category/search"})
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ICategoryService catService = new CategoryServiceImp();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String url = req.getRequestURI();
		if (url.contains("categories")) {
			List<CategoryModel> lst = catService.findAll();
			req.setAttribute("listcat", lst);
			req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
		}

		else if (url.contains("add")) {
			req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
		}
		
		else if(url.contains("edit")) {
            int id = Integer.parseInt(req.getParameter("id"));
            CategoryModel categoryModel = catService.findByID(id);
            req.setAttribute("cate", categoryModel);
            req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
            
           
        }
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		String url = req.getRequestURI();
		if(url.contains("insert")) {
			String categoryname = req.getParameter("categoryname");
			String status = req.getParameter("status");
			int statuss = Integer.parseInt(status);
			String images = "https://cdn.tgdd.vn/Files/2021/10/15/1390676/oppo-reno6-3_1280x853-800-resize.jpg";
			
			CategoryModel category = new CategoryModel();
			category.setcategoryname(categoryname);
			category.setImages(images);
			category.setStatus(statuss);
			
			catService.insert(category);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
			
	
		}
		
			
	}

}
