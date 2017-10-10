package util;
/**
 * ajax请求时返回的结果对象
 * @author hasee
 *
 */
public class ReturnResult {
	private Integer status=1;//返回状态信息成功返回1，失败返回2
	private Object data;//返回的数据
	private String message;//返回的信息
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Result [status=" + status + ", data=" + data + ", message="
				+ message + "]";
	}
	/**
	 * 成功返回的构造方法
	 * @param data 数据
	 */
	public ReturnResult(Object data){
		this.data=data;
		this.status=1;
	}
	/**
	 * 成功返回并添加成功提示的构造方法
	 * @param data
	 * @param message
	 */
	public ReturnResult(Object data,String message){
		this.status=1;
		this.data=data;
		this.message=message;
		
	}
	/**
	 * 返回失败的构造方法
	 * @param message
	 */
	public ReturnResult(String message){
		this.status=2;
		this.message=message;
	}
	/**
	 * 自定义的构造方法
	 * @param status
	 * @param data
	 * @param message
	 */
	public ReturnResult(Integer status, Object data, String message) {
		this.status = status;
		this.data = data;
		this.message = message;
	}
	
	
	
}
