package bubble.test.ex01;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;

//윈도우창이 됨
//윈도우 창은 내부에 패널 하나 가짐
public class BubbleFrame extends JFrame{

	public BubbleFrame() {
		setSize(1000,640);
		getContentPane().setLayout(null);
		setVisible(true);//그림을 그려라
	}
	public static void main(String[] args) {
		new BubbleFrame();
	}

}
