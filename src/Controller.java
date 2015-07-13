
public class Controller
{
	private StartView startView;
	private GameView gameView;
	private GameModel gameModel;
	
	public void go()
	{
		startView = new StartView();
		startView.setController(this);
	}
	
	public void startGame()
	{
//		startView.finish();
		gameModel = new GameModel();
		gameView = new GameView();
		
		gameModel.setGameView(gameView);
		gameView.setGameModel(gameModel);
		
		gameView.setUp();
		
		gameModel.go();
	}
}
