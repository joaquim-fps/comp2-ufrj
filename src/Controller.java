public class Controller
{
	private StartView startView;
	private GameController gameController;
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
		
		gameController = new GameController();
		gameController.setController(this);
		gameController.setUpGame();
		gameController.playGame();
	}
	
	public void gameOver()
	{
		gameOverView = new GameOverView();
		gameOverView.setController(this);
		gameOverView.setUp();
	}
	
	public void playAgain()
	{
		gameOverView.finish();
		go();
	}

	public StartView getStartView() {
		return startView;
	}

	public void setStartView(StartView startView) {
		this.startView = startView;
	}

	public GameController getGameController() {
		return gameController;
	}

	public void setGameController(GameController gameController) {
		this.gameController = gameController;
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
