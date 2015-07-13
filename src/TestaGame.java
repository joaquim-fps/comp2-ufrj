
public class TestaGame {
	public static void main(String[] args) {
		
		/*
		 * para iniciar pela tela inicial
		 * comente a linha new Controller().startGame()
		 * descomente a linha new Controller().go()
		 * e descomente a linha startView.finish() na classe Controller
		 * 
		 * para inciar direto pela tela do jogo,
		 * comente a linha new Controller().go()
		 * descomente a linha new Controller().startGame()
		 * e comente a linha startView.finish() na classe Controller
		 * 
		 * bugs:
		 * se o jogo for inciado pela tela inicial, o paintComponent não funciona
		 */
		
		new Controller().go(); //inicia pela tela inicial
//		new Controller().startGame(); //inicia pela tela do jogo
	}
}
