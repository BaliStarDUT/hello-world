package yang.servlet.test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 生成随机图片,用作验证码
 */
@WebServlet(name="/ServletDemo4",urlPatterns={"/demo4"})
public class ServletDemo4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("refresh", "5");//设置refresh响应头控制浏览器每5秒刷新一次
		//1.在内存中创建一个图片
		BufferedImage image = new BufferedImage(80,20,BufferedImage.TYPE_INT_RGB);
		//2.得到图片
		//Graphics g2 =image.getGraphics();
		Graphics2D g = (Graphics2D)image.getGraphics();
		g.setColor(Color.WHITE);//设置图片的背景色
		g.fillRect(0, 0, 80, 20);//填充背景色
		//3.向图片上写数据
		g.setColor(Color.BLUE);//设置图片上的字体的颜色
		g.drawString(makeNum(), 0, 20);
		//4.设置响应头控制浏览器以图片的方式打开
		response.setContentType("image/jpeg");//等同于response.setHeader("Content-Type","image/jpeg");
		//5.设置响应头控制浏览器不缓存图片（发现即使不设，图片还是会自动更新）
		response.setDateHeader("expries", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		//6.将图片写给浏览器
		ImageIO.write(image, "jpg", response.getOutputStream());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	/*
	 * 生成随机数字
	 */
	private String makeNum(){
		Random random = new Random();
		String num = random.nextInt(1000000)+"";
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<7-num.length();i++){
			sb.append("0");
		}
		num=sb.toString()+num;
		return num;
	}
}
