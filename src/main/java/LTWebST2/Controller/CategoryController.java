package LTWebST2.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import LTWebST2.Models.CategoryModel;
import LTWebST2.dao.CategoryServiceImp;
import LTWebST2.dao.ICategoryService;
import LTWebST2.utils.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/categories", "/admin/category/add", "/admin/category/insert", "/admin/category/edit", "/admin/category/update", "/admin/category/delete", "/admin/category/search"})
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ICategoryService catService = new CategoryServiceImp();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (url.contains("categories")) {
			List<CategoryModel> lst = catService.findAll();
			for (CategoryModel categoryModel : lst) {
				System.out.println(categoryModel.getImages());
			}
			req.setAttribute("listcate", lst);
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
		
		else if(url.contains("delete")) {
			String id = req.getParameter("id");
			catService.delete(Integer.parseInt(id));
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
			
		}
			
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
	    req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
	        
	    if (url.contains("update")) {
	        int categoryid = Integer.parseInt(req.getParameter("categoryid"));
	        String categoryname = req.getParameter("categoryname");
	        String status = req.getParameter("status");
	        int statuss = Integer.parseInt(status);	        
	        CategoryModel category = new CategoryModel();
	        category.setCategoryid(categoryid);  
	        category.setCategoryname(categoryname);
	        category.setStatus(statuss);
	        
	        // luu hinh cu
	        CategoryModel cateold = catService.findByID(categoryid);
	        String fileold = cateold.getImages();
	        
	        // xu ly image
	        String fname = "";
	        String uploadPath = Constant.UPLOAD_DIRECTORY;
	        File uploadDir = new File(uploadPath);
	        if(!uploadDir.exists()) {
	        	uploadDir.mkdir();
	        }
	        
	        try {
	        	Part part = req.getPart("images");
	        	if(part.getSize()>0) {
	        		String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
	        		// đổi tên file
	        		int index = filename.lastIndexOf(".");
	        		String ext =  filename.substring(index+1);
	        		fname = System.currentTimeMillis() + "." + ext;
	        		// upload file
	        		part.write(uploadPath + "/" + fname);
	        		//ghi ten vao data
	        		category.setImages(fname);
	        	}
	        	else {
	        		category.setImages(fileold);
	        	}
	        	
	        }catch(Exception ex){
	        	ex.printStackTrace();
	        }
	        
	        catService.update(category);
	        resp.sendRedirect(req.getContextPath() + "/admin/categories");
	    }
	    
	    else if(url.contains("insert")) {
	        String categoryname = req.getParameter("categoryname");
	        String status = req.getParameter("status");
	        int statuss = Integer.parseInt(status); 
	        
	        CategoryModel category = new CategoryModel();
	        category.setCategoryname(categoryname); 
	        category.setStatus(statuss);
	        
		    String fname = "";
	        String uploadPath = Constant.UPLOAD_DIRECTORY;
	        File uploadDir = new File(uploadPath);
	        if(!uploadDir.exists()) {
	        	uploadDir.mkdir();
	        }
	        
	        try {
	        	Part part = req.getPart("images");
	        	if(part.getSize()>0) {
	        		String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
	        		// đổi tên file
	        		int index = filename.lastIndexOf(".");
	        		String ext =  filename.substring(index+1);
	        		fname = System.currentTimeMillis() + "." + ext;
	        		// upload file
	        		part.write(uploadPath + "/" + fname);
	        		//ghi ten vao data
	        		category.setImages(fname);
	        	}
	        	else {
	        		category.setImages("avarta.png");
	        	}
	        	
	        }catch(Exception ex){
	        	ex.printStackTrace();
	        }
	        
	        catService.insert(category);
	        resp.sendRedirect(req.getContextPath() + "/admin/categories");
	    }
	}
}
