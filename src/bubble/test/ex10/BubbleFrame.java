package bubble.test.ex10;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BubbleFrame extends JFrame {
	
	private JLabel backgroundMap;
	private Player player;

	public BubbleFrame() {
		initObject();
		initSetting();
		initListener();
		setVisible(true);
	}
	
	private void initObject() {
		backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));
		setContentPane(backgroundMap);
		
		player = new Player();
		add(player);

	}
	
	private void initSetting() {
		setSize(1000,640);
		setLayout(null);
		setLocationRelativeTo(null);//�����ġ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���� jvm���̲���
	}
	
	private void initListener() {
		addKeyListener(new KeyAdapter() {
			
			@Override//Ű���� Ŭ�� �̺�Ʈ �ڵ鷯
			public void keyPressed(KeyEvent e) {
//				System.out.println(e.getKeyCode());
				switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					if(!player.isLeft()&&!player.isLeftWallCrash()) {
						player.left();
					}
					break;
				case KeyEvent.VK_RIGHT:
					if(!player.isRight()&&!player.isRightWallCrash()) {
						player.right();
					}
					break;
				case KeyEvent.VK_UP:
					if(!player.isUp()&&!player.isDown()) {
						player.up();
					}
					break;
				case KeyEvent.VK_SPACE:
					Bubble bubble = new Bubble(player);
					add(bubble);
					break;
				}
			}
			
			//Ű���� ���� �̺�Ʈ �ڵ鷯
			@Override
			public void keyReleased(KeyEvent e) {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					player.setLeft(false);
					break;
				case KeyEvent.VK_RIGHT:
					player.setRight(false);
					break;
				
				}
			}
		});
	}
	
	public static void main(String[] args) {
		new BubbleFrame();
	}
}
