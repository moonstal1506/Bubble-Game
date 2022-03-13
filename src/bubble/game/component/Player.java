package bubble.game.component;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import bubble.game.BubbleFrame;
import bubble.game.Moveable;
import bubble.game.service.BackgroundPlayerService;
import bubble.game.state.PlayerWay;
import lombok.Getter;
import lombok.Setter;

// class Player -> new ������ �ֵ�!! ���ӿ� ������ �� ����. (�߻�޼��带 ���� �� ����.)
@Getter
@Setter
public class Player extends JLabel implements Moveable {

	private BubbleFrame mContext;
	private List<Bubble> bubbleList;

	// ��ġ ����
	private int x;
	private int y;

	// �÷��̾��� ����
	private PlayerWay playerWay;

	// ������ ����
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;

	// ���� �浹�� ����
	private boolean leftWallCrash;
	private boolean rightWallCrash;

	// �÷��̾� �ӵ� ����
	private final int SPEED = 4;
	private final int JUMPSPEED = 2; // up, down

	private ImageIcon playerR, playerL;

	public Player(BubbleFrame mContext) {
		this.mContext = mContext;
		initObject();
		initSetting();
		initBackgroundPlayerService();
	}

	private void initObject() {
		playerR = new ImageIcon("image/playerR.png");
		playerL = new ImageIcon("image/playerL.png");
		bubbleList = new ArrayList<>();
	}

	private void initSetting() {
		x = 80;
		y = 535;

		left = false;
		right = false;
		up = false;
		down = false;

		leftWallCrash = false;
		rightWallCrash = false;

		playerWay = PlayerWay.RIGHT;

		setIcon(playerR);
		setSize(50, 50);
		setLocation(x, y);
	}

	private void initBackgroundPlayerService() {
		new Thread(new BackgroundPlayerService(this)).start();
	}

	@Override
	public void attack() {
		new Thread(() -> {
			Bubble bubble = new Bubble(mContext);
			mContext.add(bubble);
			bubbleList.add(bubble);
			if (playerWay == PlayerWay.LEFT) {
				bubble.left();
			} else {
				bubble.right();
			}
		}).start();
	}

	// �̺�Ʈ �ڵ鷯
	@Override
	public void left() {
		// System.out.println("left");
		playerWay = PlayerWay.LEFT;
		left = true;
		new Thread(() -> {
			while (left) {
				setIcon(playerL);
				x = x - SPEED;
				setLocation(x, y);
				try {
					Thread.sleep(10); // 0.01��
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();

	}

	@Override
	public void right() {
		// System.out.println("right");
		playerWay = PlayerWay.RIGHT;
		right = true;
		new Thread(() -> {
			while (right) {
				setIcon(playerR);
				x = x + SPEED;
				setLocation(x, y);
				try {
					Thread.sleep(10); // 0.01��
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();

	}

	// left + up, right + up
	@Override
	public void up() {
		// System.out.println("up");
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
		// System.out.println("down");
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