package tpv.bros;

public class Const {
	public static final String SYSTEM_CODE = "BR";
	public static final String PATH_P001_HOME = "/home";
	public static final String PATH_P002_REGISTER = "/register";
	public static final String PATH_P003_LOGIN = "/login";
	public static final String PATH_P004_RESET_PASSWORD = "/reset-password";
	public static final String PATH_P005_CONFIRM_PASSWORD = "/confirm-password";
	public static final String PATH_P006_PRICE_LIST = "/price-list";

	public static final String PATH_DEFAULT_SUCCESS_URL = "/login-success";
	public static final String PATH_FAILURE_URL = PATH_P003_LOGIN;
	public static final String PATH_LOGOUT_URL = "/logout";
	public static final String PATH_LOGOUT_SUCCESS_URL = PATH_P001_HOME;

	public static final String[] PUBLIC_URL = { 
			PATH_P001_HOME, "/", "/index" // for HOME
			, PATH_P002_REGISTER          // for register page
			, PATH_P003_LOGIN             // for login page
			, PATH_LOGOUT_URL             // for logout page
			, PATH_P004_RESET_PASSWORD    // for reset password page
			, PATH_P005_CONFIRM_PASSWORD  // for confirm password page
			, PATH_P006_PRICE_LIST
			, "/all"
			, "/rs-user*"
			, "/render"
	};
}
