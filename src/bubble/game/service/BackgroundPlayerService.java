package bubble.game.service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import bubble.game.component.Bubble;
import bubble.game.component.Player;

// ���ν����� �ٻ� - Ű���� �̺�Ʈ�� ó���ϱ� �ٻ�.
// ��׶��忡�� ��� ����
public class BackgroundPlayerService implements Runnable {

	private BufferedImage image;
	private Player player;
	private List<Bubble> bubbleList;

	public BackgroundPlayerService(Player player) {
		this.player = player;
		this.bubbleList = player.getBubbleList();
		try {
			image = ImageIO.read(new File("image/backgroundMapService.png"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void run() {

		while (true) {

			// 1. ���� �浹 üũ
			for (int i = 0; i < bubbleList.size(); i++) {
				Bubble bubble = bubbleList.get(i);
				if (bubble.getState() == 1) {
					if ((Math.abs(player.getX() - bubble.getX()) < 10) && Math.abs(player.getY() - bubble.getY()) > 0
							&& Math.abs(player.getY() - bubble.getY()) < 50) {
						System.out.println("���� ��� �Ϸ�");
						bubble.clearBubbled(); 
						break;
					}
				}
			}

			// 2. �� �浹 üũ
			// ���� Ȯ��
			Color leftColor = new Color(image.getRGB(player.getX() - 10, player.getY() + 25));
			Color rightColor = new Color(image.getRGB(player.getX() + 50 + 15, player.getY() + 25));
			// -2�� ���´ٴ� ���� �ٴڿ� ������ ���� ���
			int bottomColor = image.getRGB(player.getX() + 10, player.getY() + 50 + 5) // -1
					+ image.getRGB(player.getX() + 50 - 10, player.getY() + 50 + 5); // -1

			// �ٴ� �浹 Ȯ��
			if (bottomColor != -2) {
				// System.out.println("���� Į�� : "+bottomColor);
				// System.out.println("�ٴڿ� �浹��");
				player.setDown(false);
			} else { // -2 �϶� ����� => �� �ٴڻ����� �Ͼ���̶�� ��
				if (!player.isUp() && !player.isDown()) {
					// System.out.println("�ٿ� �����");
					player.down();
				}
			}

			// �ܺ� �浹 Ȯ��
			if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
				// System.out.println("���� ���� �浹��");
				player.setLeftWallCrash(true);
				player.setLeft(false);
			} else if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
				// System.out.println("������ ���� �浹��");
				player.setRightWallCrash(true);
				player.setRight(false);
			} else {
				player.setLeftWallCrash(false);
				player.setRightWallCrash(false);
			}

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}