package yang.java.test;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 *
 * @date 2015年10月8日 下午3:07:34
 * @author James Yang
 * @version 1.0
 * @since
 */
public class JFrameTest1 {
	private static void constructGUI(){
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame();
		frame.setTitle("Swing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel label = new JLabel("Welcome!");
		frame.add(label);
		frame.pack();
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				constructGUI();
			}
		});
	}

}
