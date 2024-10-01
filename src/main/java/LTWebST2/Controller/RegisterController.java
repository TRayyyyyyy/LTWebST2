package LTWebST2.Controller;

import java.io.IOException;

import LTWebST2.dao.IUserService;
import LTWebST2.dao.UserServiceImp;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/register"})

public class RegisterController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
        	resp.sendRedirect(req.getContextPath() + "/admin");
            return;
        }
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    session = req.getSession(true);
                    session.setAttribute("username", cookie.getValue());
                    resp.sendRedirect(req.getContextPath() + "/admin");
                    return;
                }
            }
        }
        req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String username = req.getParameter("username");
        String fullname = req.getParameter("fullname");
    	IUserService userService = new UserServiceImp();
        String alertMsg = "";
        if (userService.checkExistEmail(email)) {
            alertMsg = "Email đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }
        if (userService.checkExistUsername(username)) {
            alertMsg =
                    "Tài khoản đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }
        boolean isSuccess = userService.register(fullname, email, username, password);
        if (isSuccess) {
            alertMsg = "Đăng kí thành công !";
            req.setAttribute("alert", alertMsg);
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            alertMsg = "System error!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
        }
	}
	
	public class Constant {
        public static final String COOKIE_REMEMBER = "username";
        public static final String SESSION_USERNAME = "username";
    }
    public static final String REGISTER = "";
}