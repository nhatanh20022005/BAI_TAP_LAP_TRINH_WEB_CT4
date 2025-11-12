package MyPackage.controller;


import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
// Map URL đích sau khi đăng nhập thành công
// Đảm bảo URL này khớp với URL chuyển hướng trong LoginController
@WebServlet(urlPatterns = "/waiting/Home") 
public class HomeController extends HttpServlet {

    // Khai báo hằng số cho Session Key (hoặc lấy từ vn.iotstar.common.Constants)
    private static final String SESSION_ACCOUNT_KEY = "account";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy Session hiện tại, không tạo nếu chưa tồn tại (false)
        HttpSession session = req.getSession(false); 

        // 1. Kiểm tra Session: Đảm bảo người dùng đã đăng nhập
        if (session != null && session.getAttribute(SESSION_ACCOUNT_KEY) != null) {
            
            // Nếu session tồn tại và có thông tin tài khoản, chuyển hướng đến trang Home
            // Đường dẫn đến file JSP của bạn
            req.getRequestDispatcher("/views/home.jsp").forward(req, resp);
        
        } else {
            // 2. Nếu không có session hợp lệ, chuyển hướng về trang đăng nhập
            // Sử dụng URL mapping của LoginController, không phải file JSP trực tiếp
            resp.sendRedirect(req.getContextPath() + "/login"); 
        }
    }
    
    // Yêu cầu POST không cần thiết cho trang hiển thị, nhưng có thể thêm nếu cần logic đặc biệt
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
