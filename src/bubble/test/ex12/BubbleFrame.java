package bubble.test.ex12;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BubbleFrame extends JFrame {
	
	private BubbleFrame mContext = this;
	private JLabel backgroundMap;
	private Player player;
	private Enemy enemy;

	public BubbleFrame() {
		initObject();
		initSetting();
		initListener();
		setVisible(true);
	}

	private void initObject() {
		backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));
		setContentPane(backgroundMap);
		player = new Player(mContext);
		add(player);
		enemy = new Enemy(mContext);
		add(enemy);
	}

	private void initSetting() {
		setSize(1000, 640);
		setLayout(null); // absoulte ���̾ƿ� (�����Ӱ� �׸��� �׸� �� �ִ�)
		setLocationRelativeTo(null); // JFrame ��� ��ġ�ϱ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x��ư���� â�� �� �� JVM ���� �����ϱ�
	}

	private void initListener() {
		addKeyListener(new KeyAdapter() {

			// Ű���� Ŭ�� �̺�Ʈ �ڵ鷯
			@Override
			public void keyPressed(KeyEvent e) {
				// System.out.println(e.getKeyCode());

				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					if (!player.isLeft() && !player.isLeftWallCrash()) {
						player.left(); // 5��
					}
					break;
				case KeyEvent.VK_RIGHT:
					if (!player.isRight() && !player.isRightWallCrash()) {
						player.right(); // 3��
					}

					break;
				case KeyEvent.VK_UP:
					if (!player.isUp() && !player.isDown()) {
						player.up();
					}
					break;
				case KeyEvent.VK_SPACE:
					player.attack();
					break;
				}
			}

			// Ű���� ���� �̺�Ʈ �ڵ鷯
			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
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
