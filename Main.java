package application;
	
import javafx.application.Application;
import javafx.stage.Stage;



public class Main extends Application {

	public static boolean isGame;
	public static int gameMode = 0;
	@Override
	public void start(Stage primaryStage) {
		isGame = false;
		primaryStage.setTitle("DIGGER");
		primaryStage.setX(0);
		primaryStage.setY(0);
		primaryStage.setResizable(false);
		Menu startMenu = new Menu(primaryStage);
		startMenu.update(primaryStage);
	
			
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
