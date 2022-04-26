package tpv.bros;

public class Const {
	public static final String PATH_HOME = "/home";
	public static final String PATH_LOGIN = "/login";
	public static final String PATH_DEFAULT_SUCCESS_URL = "/login-success";
	public static final String PATH_FAILURE_URL = PATH_LOGIN;
	public static final String PATH_LOGOUT_URL = "/logout";
	public static final String PATH_LOGOUT_SUCCESS_URL = PATH_HOME;

	public static final String[] PUBLIC_URL = { 
			PATH_HOME, "/", "/index" // for HOME
			, "/register"            // for register page
			, PATH_LOGIN             // for login page
			, "/reset-password"      // for reset password page
			, "/confirm-password"    // for confirm password page
	};
}
