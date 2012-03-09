package models;

public class JsonResponse {

	private String message;

	private Object result;

	public JsonResponse(String message) {
		this.message = message;
	}

	public JsonResponse(Object result) {
		this.result = result;
	}

	public JsonResponse(String message, Object result) {
		this.message = message;
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}