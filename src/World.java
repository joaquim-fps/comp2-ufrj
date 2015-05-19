import java.awt.Color;

public class World {
	private int width, height;
	private Color background;
	
	public World(int width, int height, Color background) {
		this.width = width;
		this.height = height;
		this.background = background;
	}

	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public Color getBackground() {
		return background;
	}
	
	public void setBackground(Color background) {
		this.background = background;
	}
}
