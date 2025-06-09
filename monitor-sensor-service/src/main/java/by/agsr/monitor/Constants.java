package by.agsr.monitor;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {

    public static final String ACCESS_TOKEN_ID = "a-key";
    public static final String ROLE_CLAIM_KEY = "role";
    public static final String REFRESH_TOKEN_ID = "r-id";

    public static final String BEARER_PREFIX = "Bearer ";

    public static final String PASSWORD_REGEX = "^(?=.*[~!?@#$%^&*_\\-+()\\[\\]{}><\\/\\\\|\"'. ,:;])(?=.*\\d)(?=.*[A-Z])[A-Za-z0-9~!?@#$%^&*_\\-+()\\[\\]{}><\\/\\\\|\"'. ,:;]{8,}$";

}
