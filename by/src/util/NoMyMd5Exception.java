package util;
/**
 * MD5异常类
 * @author hasee
 *
 */
public class NoMyMd5Exception extends RuntimeException {
	public NoMyMd5Exception(String error){
		super(error);
	}
}
