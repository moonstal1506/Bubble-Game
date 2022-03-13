package bubble.game.component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import bubble.game.BubbleFrame;
import bubble.game.Moveable;
import bubble.game.service.BackgroundBubbleService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bubble extends JLabel implements Moveable {

	// ������ ��������
	private BubbleFrame mContext;
	private Player player;
	private Enemy enemy;
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
		this.enemy= mContext.getEnemy();
		initObject();
		initSetting();
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
			//40�� 60�� ���� ���밪
			if((Math.abs(x-enemy.getX())<10)&&
					(0<Math.abs(y-enemy.getY())&&Math.abs(y-enemy.getY())<50)){
				System.out.println("������� ������ �浹�Ͽ����ϴ�.");
				if(enemy.getState()==0) {
					attack();
					break;
				}
				
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
			
			//������ �Ÿ�10���� ����
			if((Math.abs(x-enemy.getX())<10)&&
					(0<Math.abs(y-enemy.getY())&&Math.abs(y-enemy.getY())<50)){
				System.out.println("������� ������ �浹�Ͽ����ϴ�.");
				if(enemy.getState()==0) {
					attack();
					break;
				}
				
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
				if(state==0) {//�⺻ �����
					Thread.sleep(1);
				}else {// ���� ���� �����
					Thread.sleep(10);
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(state==0) clearBubble();//õ�忡 ������ �����ϰ� ���� 3�� �Ŀ� �޸𸮿��� �Ҹ�
		
	}
	@Override
	public void attack() {
		state=1;
		enemy.setState(1);
		setIcon(bubbled);
		mContext.remove(enemy);//�޸𸮿��� ������� �Ѵ�.(������ �÷���->��� �ߵ����� ����.)
		mContext.repaint();
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
	public void clearBubbled() {
		new Thread(()->{
			try {
				up= false;
				setIcon(bomb);
				Thread.sleep(1000);
				mContext.remove(this);
				mContext.repaint();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
		
	}
}