
public class Player
{
	private String name;
	private int score;
	
	public Player (String name)
	{
		this.name = name;
		score = 0;
	}
	
	public void score(int points)
	{
		score += points;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
}