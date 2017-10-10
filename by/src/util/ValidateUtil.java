package util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

public class ValidateUtil {
	private static final String CODE_SOURCE="23456789ABCDEFGHIJKLMNPQRSTXYZ";//字符源
	private static Random random=new Random();//随机数
	
	/**
	 * 使用默认字符源生成验证码
	 * @param codeSize 验证码长度
	 * @return 验证码
	 */
	public static String valiCode(int codeSize){
		Random random=new Random(System.currentTimeMillis());
		StringBuilder builder=new StringBuilder(codeSize);
		for (int i = 0; i < codeSize; i++) {
			int value = random.nextInt(CODE_SOURCE.length());
			builder.append(CODE_SOURCE.charAt(value));
		}
		return builder.toString();
	}
	/**
	 * 使用其他字符源生成验证码
	 * @param codeSize 生成验证码长度
	 * @param sourceCode 验证码字符源源
	 * @return 验证码
	 */
	public static String valiSourceCode(int codeSize,String sourceCode){
		if (sourceCode==null && sourceCode.length()<1) {//判断
			sourceCode=CODE_SOURCE;
		}
		Random random=new Random(System.currentTimeMillis());
		StringBuilder builder=new StringBuilder(codeSize);
		for (int i = 0; i < codeSize; i++) {
			int value = random.nextInt(sourceCode.length());
			builder.append(sourceCode.charAt(value));
		}
		return builder.toString();
	}
	/**
	 * 生成验证码并向前台输出
	 * @param width 图片的宽度
	 * @param height 图片的高度
	 * @param out 输出对象
	 * @param valiCode 验证码
	 * @throws IOException 
	 */
	public static void outValiCodeImage(int width,int height,OutputStream out,String valiCode) throws IOException{
		BufferedImage image=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.BLACK);//设置边框颜色
		g.fillRect(0, 0, width, height);//绘制矩形
		g.setColor(getRangeColor(200, 250));//设置背景色
		g.fillRect(1, 1, width-2, height-2);//绘制背景色预留1px为边框
		g.setColor(getRangeColor(160, 200));//设置线条颜色
		for (int i = 0; i < 30; i++) {//绘制干扰线
			int x=random.nextInt(width);
			int y=random.nextInt(height);
			int x2=random.nextInt(6)+1;
			int y2=random.nextInt(12)+1;
			g.drawLine(x, y, x+x2+40, y+y2+20);
		}
		float yawpRate=0.05f;//噪点比率
		int yawp=(int) (width*height*yawpRate);//获取噪点数
		for (int i = 0; i < yawp; i++) {
			int intRGB = getIntRGB();
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			image.setRGB(x, y, intRGB);
		}
		
		int fontSize=height-4;
		g.setColor(getRangeColor(100, 160));
		//设置字体
		Font font=new Font("Algerian", Font.ITALIC, fontSize);
		g.setFont(font);
		int length = valiCode.length();
		char[] data = valiCode.toCharArray();
		for (int i = 0; i < length; i++) {//将字体旋转
			AffineTransform affine=new AffineTransform();
			//参数1:旋转角度 ，公式 (Math.PI / 4为45度乘以不到1的随机double乘以1为顺时针旋转-1为逆时针旋转)
			double theta=Math.PI/4*random.nextDouble()*(random.nextBoolean()?1:-1);
			//参数2:X轴，公式:(图片宽度/字体个数)*i+fontSize/2
			double anchorx=width/length*i+fontSize/2;
			//参数3:Y轴，公式:(图片高度/2)
			double anchory=height/2;
			affine.setToRotation(theta, anchorx, anchory);//设置字体旋转
			g.setTransform(affine);
			//参数4:X轴，公式:(图片宽度-10)/字体个数*i+5两边个留5px像素
            //参数5:Y轴，公式:(图片高度/2)+(字体大小/2)-4
			g.drawChars(data, i, 1, ((width-10) / length) * i + 5, height/2 + fontSize/2-4);
		}
		g.dispose();
		ImageIO.write(image, "jpg", out);
		out.flush();out.close();
	}
	/**
	 * 随机获取颜色
	 * @return
	 */
	private static Color getRandomColor(){
		return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
	}
	/**
	 * 根据范围获取颜色
	 * @param bc rgb生成最小值
	 * @param ec rgb生成最大值-1
	 * @return Color
	 */
	private static Color getRangeColor(int bc,int ec){
		if (bc>255) {
			bc=255;
		}
		if (ec>255) {
			ec=255;
		}
		int r=bc+random.nextInt(ec-bc);
		int g=bc+random.nextInt(ec-bc);
		int b=bc+random.nextInt(ec-bc);
		return new Color(r,g,b);
	}
	/**
	 * 获取rgb的int值
	 * @return
	 */
	private static int getIntRGB(){
		int rgb=0;
		for (int i = 0; i < 3; i++) {
			rgb=rgb<<8;
			rgb=rgb|random.nextInt(255);
		}
		return rgb;
	}
}
