
public class Controller
{
	private StartView startView;
	private GameView gameView;
	private GameModel gameModel;
	private GameOverView gameOverView;
	
	public void go()
	{
		startView = new StartView();
		startView.setController(this);
	}
	
	public void startGame()
	{
		startView.finish();
		
		gameModel = new GameModel();
		gameView = new GameView();
		gameModel.setController(this);
		
		gameModel.setGameView(gameView);
		gameView.setGameModel(gameModel);
		
		gameView.setUp();
		
		gameModel.go();
	}
	
	public void gameOver()
	{
		gameView.finish();
		
		gameOverView = new GameOverView();
		gameOverView.setUp();
	}
}
