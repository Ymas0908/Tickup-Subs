package org.tickup.adapters.ports.driver.apiException;

@lombok.Data
public class ApiResponseSuccess {
	private String status;
	private String message;
	private int code;
	private Object data;

	public ApiResponseSuccess() {
	}

	public ApiResponseSuccess(String status, String message, int code, Object data) {
		this.status = status;
		this.message = message;
		this.code = code;
		this.data = data;
	}
}
