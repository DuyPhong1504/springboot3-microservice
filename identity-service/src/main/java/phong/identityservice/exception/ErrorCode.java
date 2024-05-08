package phong.identityservice.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(1,"Uncategorized error"),

    INVALID_KEY(2,"Uncategorized error"),

    USER_EXISTED(3,"User existed"),

    USERNAME_INVALID(4,"Username must be at least 3 characters"),

    INVALID_PASSWORD(5,"Password must be at least 8 characters")
            ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
