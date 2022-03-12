package bubble.test.ex12;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bubble extends JLabel implements Moveable {

	// ������ ��������
	private BubbleFrame mContext;
	private Player player;
	private BackgroundBubbleService backgroundBubbleService;

	// ��ġ ����
	private int x;
	private int y;

	// ������ ����
	private boolean left;
	private boolean right;
	private boolean up;

	// ������ ���� ����
	private int state; // 0(�����), 1(���� ���� �����)

	private ImageIcon bubble; // �����
	private ImageIcon bubbled; // ���� ���� �����
	private ImageIcon bomb; // ������� ���� ����

	public Bubble(BubbleFrame mContext) {
		this.mContext = mContext;
		this.player = mContext.getPlayer();
		initObject();
		initSetting();
		initThread();
	}

	private void initObject() {
		bubble = new ImageIcon("image/bubble.png");
		bubbled = new ImageIcon("image/bubbled.png");
		bomb = new ImageIcon("image/bomb.png");

		backgroundBubbleService = new BackgroundBubbleService(this);
	}

	private void initSetting() {
		left = false;
		right = false;
		up = false;

		x = player.getX();
		y = player.getY();

		setIcon(bubble);
		setSize(50, 50);

		state = 0;
	}

	private void initThread() {
		// ������ �����尡 �ϳ��� �ʿ��ϴ�.
		new Thread(() -> {
			if (player.getPlayerWay() == PlayerWay.LEFT) {
				left();
			} else {
				right();
			}
		}).start();
	}

	@Override
	public void left() {
		left = true;
		for (int i = 0; i < 400; i++) {
			x--;
			setLocation(x, y);

			if (backgroundBubbleService.leftWall()) {
				left=false;
				break;
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		up();
	}

	@Override
	public void right() {
		right = true;
		for (int i = 0; i < 400; i++) {
			x++;
			setLocation(x, y);

			if (backgroundBubbleService.rightWall()) {
				right = false;
				break;
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		up();
	}

	@Override
	public void up() {
		up = true;
		while (up) {
			y--;
			setLocation(x, y);

			if (backgroundBubbleService.topWall()) {
				up=false;
				break;
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		clearBubble();//õ�忡 ������ �����ϰ� ���� 3�� �Ŀ� �޸𸮿��� �Ҹ�
		
	}
	//���簡 �տ� ���� ����
	private void clearBubble() {
		try {
			Thread.sleep(3000);
			setIcon(bomb);
			Thread.sleep(500);
			mContext.remove(this);//BubbleFrame�� bubble�� �޸𸮿��� �Ҹ�ȴ�.
			mContext.repaint(); //BubbleFrame�� ��ü�� �ٽ� �׸���. (�޸𸮿��� ���� �� �׸��� ����)
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}