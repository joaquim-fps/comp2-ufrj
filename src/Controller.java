public class Controller
{
	private StartView startView;
	private GameView gameView;
	private GameModel gameModel;
	private GameOverView gameOverView;
	private Controller controller = this;
	
	public void go()
	{
		startView = new StartView();
		startView.setController(controller);
	}
	
	public void startGame()
	{
		startView.finish();
		
		gameModel = new GameModel();
		gameView = new GameView();
		gameModel.setController(controller);
		
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

	public StartView getStartView() {
		return startView;
	}

	public void setStartView(StartView startView) {
		this.startView = startView;
	}

	public GameView getGameView() {
		return gameView;
	}

	public void setGameView(GameView gameView) {
		this.gameView = gameView;
	}

	public GameModel getGameModel() {
		return gameModel;
	}

	public void setGameModel(GameModel gameModel) {
		this.gameModel = gameModel;
	}

	public GameOverView getGameOverView() {
		return gameOverView;
	}

	public void setGameOverView(GameOverView gameOverView) {
		this.gameOverView = gameOverView;
	}
}
