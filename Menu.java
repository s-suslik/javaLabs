package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.effect.*;

class Menu {
	private GridPane grid;
	private Scene menuScene;
	private  Button buttonPlay;
	private  Button buttonOptions;
	private  Button buttonExit;
	private  Button buttonControl;
	private  Button buttonMusic;
	private  Button buttonCancel;
	private HBox mainMenu;
	private HBox subMenu;
	private DropShadow enteredShadow;
	private FadeTransition appearMenu;
	private FadeTransition disappearMenu;
	private Rectangle fon;
	private MediaPlayer mp;
	private Media someSound;
	
	public Menu(){
		
		enteredShadow = new DropShadow();
		enteredShadow.setOffsetY(4.0f);
		enteredShadow.setOffsetX(4.0f);
		enteredShadow.setColor(Color.YELLOW);
		
		buttonPlay = new Button("Play");
		buttonOptions = new Button("Options");
		buttonExit = new Button("Exit");
		buttonControl = new Button("Control");
		buttonMusic = new Button("Music");
		buttonCancel = new Button("Cancel");
		
		fon = new Rectangle(800,600);
		fon.setFill(Color.BLACK);
				
	    mainMenu = new HBox(50);	    
	    mainMenu.getChildren().addAll(buttonPlay,buttonOptions,buttonExit);
	    
	    subMenu = new HBox(50);	    
	    subMenu.getChildren().addAll(buttonControl,buttonMusic,buttonCancel);
	   	 
	    grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0,0,0,0));
		
	
		menuScene = new Scene(grid,800,600);
		menuScene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
		grid.add(mainMenu,6,43);
		
		appearMenu = new FadeTransition(Duration.millis(500), subMenu);
		appearMenu.setFromValue(0.0);
		appearMenu.setToValue(1.0);
	    
		disappearMenu = new FadeTransition(Duration.millis(500), mainMenu);
		disappearMenu.setFromValue(1.0);
		disappearMenu.setToValue(0.0);
		
		try{
	        someSound = new Media(getClass().getResource("04.mp3").toString());
	        mp = new MediaPlayer(someSound);
	    }catch(Exception ex){System.out.println("error");}
		mp.play();
	}
	
	
	public void setSceneOnStage(Stage primaryStage){
		primaryStage.setScene(menuScene);
		primaryStage.show();
	}
	
	public void update(Stage primaryStage){
			
		buttonPlay.setOnAction(ActionEvent->{
			System.out.println("Play");
			grid.add(fon, 0, 0);
			appearMenu.setNode(fon);
			appearMenu.play();
			mp.play();
			
	
		});
		
		buttonPlay.setOnMouseEntered(MouseEvent->{
			buttonPlay.setEffect(enteredShadow);
			buttonPlay.setScaleX(1.5);
			buttonPlay.setScaleY(1.5);				
		});
		
		buttonPlay.setOnMouseExited(MouseEvent->{
			buttonPlay.setEffect(null);
			buttonPlay.setScaleX(1);
			buttonPlay.setScaleY(1);				
		});	

		buttonOptions.setOnAction(ActionEvent ->{
				System.out.println("Options");
				disappearMenu.setNode(mainMenu);
				appearMenu.setNode(subMenu);
				disappearMenu.play();
				Thread thread = new Thread(new Runnable(){ 
					   public void run() 
					   { 
						   try { 
							   Thread.sleep(500); 
						   } catch (InterruptedException e) {} 
						   Platform.runLater(new Runnable() {
							   @Override
							   public void run() {
								   grid.getChildren().remove(mainMenu);
								   grid.add(subMenu,4,43);
								   appearMenu.play();
							   }
						   });
					   } 
				}); 
				thread.start();
		});
		
		buttonOptions.setOnMouseEntered(MouseEvent ->{
			buttonOptions.setEffect(enteredShadow);
			buttonOptions.setScaleX(1.5);
			buttonOptions.setScaleY(1.5);				
		});
		
		buttonOptions.setOnMouseExited(MouseEvent ->{
			buttonOptions.setEffect(null);
			buttonOptions.setScaleX(1);
			buttonOptions.setScaleY(1);				
		});			
		
		buttonExit.setOnAction(ActionEvent ->{
			System.exit(0);
		});
		
		buttonExit.setOnMouseEntered(MouseEvent ->{
			buttonExit.setEffect(enteredShadow);
			buttonExit.setScaleX(1.5);
			buttonExit.setScaleY(1.5);				
		});
		
		buttonExit.setOnMouseExited(MouseEvent ->{
			buttonExit.setEffect(null);
			buttonExit.setScaleX(1);
			buttonExit.setScaleY(1);				
		});
		
		buttonControl.setOnAction(actionEvent ->{
			System.out.println("Control");
		});
		buttonControl.setOnMouseEntered(MouseEvent ->{
			buttonControl.setEffect(enteredShadow);
			buttonControl.setScaleX(1.5);
			buttonControl.setScaleY(1.5);				
		});
		
		buttonControl.setOnMouseExited(MouseEvent ->{
			buttonControl.setEffect(null);
			buttonControl.setScaleX(1);
			buttonControl.setScaleY(1);				
		});	
		
		buttonMusic.setOnAction(actionEvent ->{
			System.out.println("Music");
		});
		buttonMusic.setOnMouseEntered(MouseEvent ->{
			buttonMusic.setEffect(enteredShadow);
			buttonMusic.setScaleX(1.5);
			buttonMusic.setScaleY(1.5);				
		});
		
		buttonMusic.setOnMouseExited(MouseEvent ->{
			buttonMusic.setEffect(null);
			buttonMusic.setScaleX(1);
			buttonMusic.setScaleY(1);				
		});	
		
		buttonCancel.setOnAction(actionEvent ->{
			System.out.println("Cancel");
			disappearMenu.setNode(subMenu);
			appearMenu.setNode(mainMenu);
			disappearMenu.play();
			Thread thread = new Thread(new Runnable(){ 
				   public void run() 
				   { 
					   try { 
						   Thread.sleep(500); 
					   } catch (InterruptedException e) {} 
					   Platform.runLater(new Runnable() {
						   @Override
						   public void run() {
							   grid.getChildren().remove(subMenu);
							   grid.add(mainMenu,6,43);
							   appearMenu.play();
						   }
					   });
				   } 
			}); 
			thread.start();
		});
		buttonCancel.setOnMouseEntered(MouseEvent ->{
			buttonCancel.setEffect(enteredShadow);
			buttonCancel.setScaleX(1.5);
			buttonCancel.setScaleY(1.5);				
		});
		
		buttonCancel.setOnMouseExited(MouseEvent ->{
			buttonCancel.setEffect(null);
			buttonCancel.setScaleX(1);
			buttonCancel.setScaleY(1);				
		});	
	}
}
