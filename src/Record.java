import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Record
{
	private ArrayList<Player> players;
	
	public void add(Player player)
	{
		for (int i = 0; i < players.size(); i++)
		{
			if (players.get(i).getName().compareTo(player.getName()) == 0)
			{
				if (player.getScore() > players.get(i).getScore())
				{
					players.remove(players.get(i));
					players.add(player);
					Collections.sort(players, new ScoreComparator());
					new SoundPlayer().cheer();
					return;
				}
				else return;
			}
		}
		if (players.size() < 5)
		{
			players.add(player);
			Collections.sort(players, new ScoreComparator());
		}
		else
		{
			for (int i = 0; i < players.size(); i++)
			{
				if (player.getScore() > players.get(i).getScore())
				{
					players.remove(players.get(i));
					players.add(player);
					Collections.sort(players, new ScoreComparator());
					new SoundPlayer().cheer();
					break;
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void load()
	{
		try
		{
			FileInputStream fs = new FileInputStream("Records.ser");
			ObjectInputStream os = new ObjectInputStream(fs);
			
			players = (ArrayList<Player>)os.readObject();
			
			os.close();
		}
		catch(Exception ex)
		{
			players = new ArrayList<Player>();
		}
	}
	
	public void save()
	{
		try
		{
			FileOutputStream fs = new FileOutputStream("Records.ser");
			ObjectOutputStream os = new ObjectOutputStream(fs);
			
			os.writeObject(players);
			
			os.close();
		}
		catch(Exception ex){}
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	class ScoreComparator implements Comparator <Player>
	{

		public int compare(Player one, Player two)
		{
			return two.getScore() - one.getScore();
		}
		
	}
}
