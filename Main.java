package application;
	
import javafx.application.Application;
import javafx.stage.Stage;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("DIGGER");
		Menu startMenu = new Menu();
		startMenu.update(primaryStage);
		startMenu.setSceneOnStage(primaryStage);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
