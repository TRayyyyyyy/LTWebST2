package LTWebST2.Controller;

import java.io.IOException;

import LTWebST2.Models.UserModel;
import LTWebST2.dao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/forgotpassword")
public class ForgotController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUserService userService = new UserServiceImp();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("views/forgotpassword.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String password = req.getParameter("newpassword");
		UserModel user = userService.findByUsernameAndEmail(username, email);
		String msg = "";
		if (user == null) {
			msg = "Tên người dùng hoặc mật khẩu không đúng.";
			req.setAttribute("alert", msg);
			req.getRequestDispatcher("views/forgotpassword.jsp").forward(req, resp);
		} else {
			if (userService.updatePassword(username, password)) {
				req.setAttribute("alert", msg);
				req.getRequestDispatcher("views/login.jsp").forward(req, resp);
			}
			else
				msg = "Đổi mật khẩu thất bại";
				req.setAttribute("alert", msg);
				req.getRequestDispatcher("views/forgotpassword.jsp").forward(req, resp);

		}

		
	}
}
