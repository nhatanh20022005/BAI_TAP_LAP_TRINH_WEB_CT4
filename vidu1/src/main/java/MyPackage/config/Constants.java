package MyPackage.config;


public final class Constants {

    // Ngăn chặn tạo đối tượng (Constants chỉ nên là static)
    private Constants() {} 

    // Session keys
    public static final String SESSION_ACCOUNT = "account";
    public static final String SESSION_USERNAME = "username";

    // Cookie keys
    public static final String COOKIE_REMEMBER = "username"; // Tên cookie
    public static final int COOKIE_MAX_AGE_SECONDS = 30 * 60; // Thời gian sống (30 phút)
    
    // Các hằng số khác (ví dụ: đường dẫn)
    // public static final String UPLOAD_PATH = "/uploads/";
}
