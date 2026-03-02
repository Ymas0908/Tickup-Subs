package org.tickup.adapters.ports.driver.apiException;

import java.util.Map;

@lombok.Data
public class ApiErrorResponse {
    private String status;
    private String message;
    private int code;
    private Map<String, String> errors;

    public ApiErrorResponse() {
    }

    public ApiErrorResponse(String status, String message, int code, Map<String, String> errors) {
        this.status = status;
        this.message = message;
        this.code = code;
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "ApiErrorResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", code=" + code +
                ", errors=" + errors +
                '}';
    }
}
