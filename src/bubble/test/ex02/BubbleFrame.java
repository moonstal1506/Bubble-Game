package bubble.test.ex02;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BubbleFrame extends JFrame {
	
	private JLabel backgroundMap;
	private Player player;

	public BubbleFrame() {
		initObject();
		initSetting();
		setVisible(true);
	}
	
	private void initObject() {
		backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));
		setContentPane(backgroundMap);
		
		player = new Player();
		add(player);
//		backgroundMap.setSize(100,100);
//		backgroundMap.setLocation(300,300);
//		backgroundMap.setSize(1000,600);
//		add(backgroundMap);//JFrame�� JLabel�� �׷�����.
	}
	
	private void initSetting() {
		setSize(1000,640);
		setLayout(null);
		setLocationRelativeTo(null);//�����ġ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���� jvm���̲���
	}
	
	public static void main(String[] args) {
		new BubbleFrame();
	}
}
