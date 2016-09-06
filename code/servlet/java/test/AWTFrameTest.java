package yang.java.test;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;

/**
 *
 * @date 2015年10月8日 下午3:01:27
 * @author James Yang
 * @version 1.0
 * @since
 */
public class AWTFrameTest extends Frame {

	public static void main(String[] args) {
		AWTFrameTest frame = new AWTFrameTest();
		frame.setTitle("AWT");
		frame.setSize(300,100);
		frame.setLayout(new FlowLayout());
		Label label = new Label("Name");
		frame.add(label);
		frame.setVisible(true);
	}

}
