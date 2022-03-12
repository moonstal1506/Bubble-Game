package bubble.test.ex12;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

//class Player-> new������ �ֵ� ���ӿ� ����(�߻�޼��� ���� �� ����.)
@Getter
@Setter
public class Enemy extends JLabel implements Moveable {
	
	private BubbleFrame mContext;
	
	// ��ġ����
	private int x;
	private int y;

	// ������ ����
	private EnemyWay enemyWay;

	// ������ ����
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	private int state; // 0(����ִ� ����), 1(����￡ ���� ����)

	// ���� �浹�� ����
	private boolean leftWallCrash;
	private boolean rightWallCrash;

	// �÷��̾� �ӵ� ����
	private final int SPEED = 3;
	private final int JUMPSPEED = 1;

	private ImageIcon enemyR, enemyL;

	public Enemy(BubbleFrame mContext) {
		this.mContext = mContext;
		initObject();
		initSetting();
		initBackgroundEnemyService();
	}

	private void initObject() {
		enemyR = new ImageIcon("image/enemyR.png");
		enemyL = new ImageIcon("image/enemyL.png");
	}

	private void initSetting() {
		x = 480;
		y = 178;

		left = false;
		right = false;
		up = false;
		down = false;
		
		state=0;

		enemyWay = EnemyWay.RIGHT;
		setIcon(enemyR);
		setSize(50, 50);
		setLocation(x, y);
	}

	private void initBackgroundEnemyService() {
//		new Thread(new BackgroundEnemyService(this)).start();
	}

	
	@Override
	public void left() {
//		System.out.println("l");
		enemyWay = EnemyWay.LEFT;
		left = true;
		new Thread(() -> {
			while (left) {
				setIcon(enemyL);
				x = x - SPEED;
				setLocation(x, y);
				try {
					Thread.sleep(10);// 0.01��
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();

	}

	@Override
	public void right() {
//		System.out.println("r");
		enemyWay = EnemyWay.RIGHT;
		right = true;
		new Thread(() -> {
			while (right) {
				setIcon(enemyR);
				x = x + SPEED;
				setLocation(x, y);
				try {
					Thread.sleep(10);// 0.01��
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();

	}

	// left+up,right+up
	@Override
	public void up() {
//		System.out.println("up");
		up = true;
		new Thread(() -> {
			for (int i = 0; i < 130 / JUMPSPEED; i++) {
				y = y - JUMPSPEED;
				setLocation(x, y);
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			up = false;
			down();
		}).start();
	}

	@Override
	public void down() {
//		System.out.println("down");
		down = true;
		new Thread(() -> {
			while (down) {
				y = y + JUMPSPEED;
				setLocation(x, y);
				try {
					Thread.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			down = false;
		}).start();
	}
}
