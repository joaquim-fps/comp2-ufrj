public class Controller
{
	private StartView startView;
	private GameController gameController;
	private GameView gameView;
	private GameModel gameModel;
	private GameOverView gameOverView;
	private Player player;
	private ConfigModel config;
	private Record record;
	
	public void go()
	{
		startView = new StartView();
		startView.setController(this);
	}
	
	public void createPlayer(String name)
	{
		player = new Player(name);
	}
	
	public void startGame()
	{
		startView.finish();
		
		gameController = new GameController();
		gameController.setController(this);
		gameController.setPlayer(player);
		gameController.setUpGame();
		gameController.playGame();
	}
	
	public void gameOver()
	{
		record = new Record();
		record.load();
		record.add(player);
		record.save();
		
		gameOverView = new GameOverView();
		gameOverView.setController(this);
		gameOverView.setRecord(record);
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

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public ConfigModel getConfig() {
		return config;
	}

	public void setConfig(ConfigModel config) {
		this.config = config;
	}

	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}
}
