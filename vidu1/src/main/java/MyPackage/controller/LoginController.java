package MyPackage.controller;


import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import MyPackage.config.Constants;
import MyPackage.model.UsersModel;
import MyPackage.Service.UserService;
import MyPackage.Service.impl.UserServiceImpl;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
	
	// Khởi tạo service
	UserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);

		// 1. Kiểm tra session "account" (Đã đăng nhập)
		if (session != null && session.getAttribute(Constants.SESSION_ACCOUNT) != null) {
			resp.sendRedirect(req.getContextPath() + "/waiting/Home"); // Chuyển hướng đến trang chủ
			return;
		}

		// 2. Kiểm tra cookie "remember me"
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				// Sử dụng hằng số từ lớp Constants
				if (cookie.getName().equals(Constants.COOKIE_REMEMBER)) { 
					
					String usernameFromCookie = cookie.getValue();
					// LẤY LẠI THÔNG TIN USER TỪ DB
					UsersModel user = userService.get(usernameFromCookie); 
					
					if (user != null) {
						// Tạo session và set ĐỐI TƯỢNG USER (không phải string)
						session = req.getSession(true);
						session.setAttribute(Constants.SESSION_ACCOUNT, user);
						resp.sendRedirect(req.getContextPath() + "/waiting/Home"); // Chuyển hướng đến trang chủ
						return;
					}
				}
			}
		}
		
		// 3. Nếu không có session và không có cookie hợp lệ, hiển thị trang login
		req.getRequestDispatcher("views/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String remember = req.getParameter("remember");
		boolean isRememberMe = "on".equals(remember); // Gọn hơn

		String alertMsg = "";
		
		// Kiểm tra rỗng
		if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
			alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
			return;
		}
		
		// Gọi service
		UsersModel user = userService.login(username, password);
		
		if (user != null) {
			// Đăng nhập thành công
			HttpSession session = req.getSession(true);
			session.setAttribute(Constants.SESSION_ACCOUNT, user); // Sử dụng hằng số
			
			if (isRememberMe) {
				saveRemeberMe(resp, username);
			}
			
			resp.sendRedirect(req.getContextPath() + "/waiting/Home"); // Chuyển hướng
		} else {
			// Đăng nhập thất bại
			alertMsg = "Tài khoản hoặc mật khẩu không đúng";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
		}
	}

	private void saveRemeberMe(HttpServletResponse response, String username) {
		// Sử dụng hằng số
		Cookie cookie = new Cookie(Constants.COOKIE_REMEMBER, username);
		cookie.setMaxAge(Constants.COOKIE_MAX_AGE_SECONDS); // Lấy từ Constants
		cookie.setPath("/"); // Thêm path để cookie hợp lệ toàn site
		response.addCookie(cookie);
	}

	// XÓA BỎ các hằng số định nghĩa ở đây vì đã dùng lớp Constants
	// public static final String SESSION_USERNAME = "username";
	// public static final String COOKIE_REMEMBER = "username";
}