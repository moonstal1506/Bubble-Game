package bubble.test.ex11;

public interface Moveable {
	public abstract void left();
	public abstract void right();
	public abstract void up();
	default public void down(){};
	//default를 사용하면 인터페이스도 메서드 구현가능(다중상속문제해결)
}
