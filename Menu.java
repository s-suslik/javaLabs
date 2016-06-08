package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
//import scalaSort.scalaMergeSort;
//import scalaSort.statistic;
//import scalaSort.Pseudo;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.effect.*;

public class Menu {
	
	
	private Random random = new Random();
	private GridPane grid;
	private Pane replaysList = new Pane();
	private Pane  statMenu = new Pane();
	public  Scene menuScene;
	private  Button buttonPlay, buttonA, buttonB, buttonC, buttonD;
	private  Button buttonOptions;
	private  Button buttonExit;
	private  Button buttonReplay;
	private  Button buttonMusic;
	private  Button buttonStatistic;
	private  Button buttonCancel;
	private HBox mainMenu;
	private HBox subMenu;
	private Button temp = new Button();
	private DropShadow enteredShadow;
	private FadeTransition appearMenu;
	private FadeTransition disappearMenu;
	private Rectangle blackRectangle;
	private ArrayList<Button> filesButton = new ArrayList<Button>();
	
	
	public Menu(Stage primaryStage){		

		enteredShadow = new DropShadow();
		enteredShadow.setOffsetY(4.0f);
		enteredShadow.setOffsetX(4.0f);
		enteredShadow.setColor(Color.YELLOW);
		
		readDirectories();
	
		
		buttonPlay = new Button("Play");
		buttonOptions = new Button("Options");
		buttonExit = new Button("Exit");
		buttonReplay = new Button("Replay");
		buttonMusic = new Button("Bot");
		buttonCancel = new Button("Cancel");
		buttonStatistic = new Button("Statistic");
		
		blackRectangle = new Rectangle(820,620);
		blackRectangle.setFill(Color.BLACK);
		int j = 10;
		int i = 0;
		int k = 0;
		
		for (Button tempButton : filesButton) {
			 tempButton.setLayoutX(i);
			 tempButton.setLayoutY(j);
			 tempButton.setId("file");
			 j += 20;
			 k++;
			 if(k == 5 || k == 10){
				 i+= 260;
				 j = 10;
			 }
			 if(k == 15){
				 i = 0;
			     j = 410;
			 }
			 if(k == 20 || k == 25){
				 i+= 260;
				 j = 410;
			 }
			 if(k == 30)
				 break;
			 
		}
		replaysList.getChildren().addAll(filesButton);	
		
		temp.setLayoutX(250);
		 temp.setLayoutY(250);
		 temp.setText("SORT");
		 
		 buttonStatistic.setLayoutX(500);
		 buttonStatistic.setLayoutY(250);
		 buttonStatistic.setText("STAT");
		 
		 buttonA = new Button();
		 buttonB = new Button();
		 buttonC = new Button();
		 buttonD = new Button();
		 
		 buttonA.setId("file");
		 buttonB.setId("file");
		 buttonC.setId("file");
		 buttonD.setId("file");
		 
		 
		/* buttonA.setLayoutX(30);
		 buttonA.setLayoutY(50);
		 buttonA.setText("Games with player: " + statistic.numGamesWithPlayer());
		 
		 buttonB.setLayoutX(30);
		 buttonB.setLayoutY(150);
		 buttonB.setText("Games with bot: " + statistic.numGamesWithBot());
		 
		 buttonC.setLayoutX(30);
		 buttonC.setLayoutY(250);
		 buttonC.setText("Percent:" + (double)statistic.numDead()/statistic.numShots()*100 + "%");
		 
		 buttonD.setLayoutX(30);
		 buttonD.setLayoutY(350);
		 buttonD.setText("Tiles: " + statistic.numTiles());*/
		
		replaysList.getChildren().addAll(temp);
		replaysList.getChildren().addAll(buttonStatistic);
		 
		 
		 
		statMenu.getChildren().addAll(buttonA,buttonB,buttonC,buttonD);
				
	    mainMenu = new HBox(50);	    
	    mainMenu.getChildren().addAll(buttonPlay,buttonOptions,buttonExit);
	    
	    subMenu = new HBox(50);	    
	    subMenu.getChildren().addAll(buttonReplay,buttonMusic,buttonCancel);
	   	 
	    grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0,0,0,0));
	
		menuScene = new Scene(grid,800,600);
		menuScene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
		grid.add(mainMenu,7,43);
		
		
		appearMenu = new FadeTransition(Duration.millis(500), subMenu);
		appearMenu.setFromValue(0.0);
		appearMenu.setToValue(1.0);
	    
		disappearMenu = new FadeTransition(Duration.millis(500), mainMenu);
		disappearMenu.setFromValue(1.0);
		disappearMenu.setToValue(0.0);
		
		primaryStage.setScene(menuScene);
		primaryStage.show();
	}
	
	public void readDirectories(){
		final File folder = new File("saves/");
		 for (final File fileEntry : folder.listFiles()) {
			 filesButton.add(new Button(fileEntry.getName()));
		 }

	}
	
	
	
	public void update(Stage primaryStage){
			
		buttonPlay.setOnAction(ActionEvent->{
			grid.add(blackRectangle, 0, 0);
			appearMenu.setNode(blackRectangle);
			appearMenu.play();
			
			Thread thread = new Thread(new Runnable(){ 
				   public void run() 
				   { 
					   try { 
						   Thread.sleep(500); 
					   } catch (InterruptedException e) {} 
					   Platform.runLater(new Runnable() {
						   @Override
						   public void run() {
								GameProcess.startGame(primaryStage,1,null);
						   }
					   });
				   } 
			}); 
			thread.start();
			
	
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
		
		
		/*buttonStatistic.setOnAction(ActionEvent ->{
			disappearMenu.setNode(replaysList);
			appearMenu.setNode(statMenu);
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
							   	menuScene.setRoot(statMenu);
							   	appearMenu.play();
						   }
					   });
				   } 
			}); 
			thread.start();
 	   });*/
	

		buttonOptions.setOnAction(ActionEvent ->{
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
		
		/*temp.setOnAction(actionEvent ->{
				int aa = 0;
			 
			 
				final File folder = new File("saves/");
				 for (final File fileEntry : folder.listFiles()) {
					aa++;
					 
				 }
				 MergeSort b = new MergeSort();
					
					 scalaMergeSort c = null ;
					 MyFiles [] a = new MyFiles[aa];
					 for(int i = 0 ;i < aa; i++){
						 a[i] = new MyFiles();
					 }
				 int l = 0;
				 for (final File fileEntry : folder.listFiles()) {
					 a[l].filesName = fileEntry.getName();
					 a[l].fileSize = (int) fileEntry.length();
					 l++;
					 
					 
				 }
			long sortTime = System.nanoTime();
			b.sort(a);
			System.out.println("Java time = " + (System.nanoTime() - sortTime));
			c.sort();
			
		});
		*/
		
		
		
		buttonReplay.setOnAction(actionEvent ->{
			disappearMenu.setNode(subMenu);
			appearMenu.setNode(replaysList);
			
	
			/*ObjectInputStream readStream = null;
			
			try {
				readStream = new ObjectInputStream(new FileInputStream("saves/b 07.06.2016 04 58 27.txt"));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			char ch = '0';
			char sh = '/';
			System.out.println("Ќачало»гры");
			while(ch != '6'){
				try {
					ch = readStream.readChar();
				} catch (Exception e) {
					//e.printStackTrace();
				}
				if(ch == '7')
					System.out.println("убит¬раг");
				if(ch == '5')
					System.out.println("произведен¬ыстрел");
				
				if(ch == '0' && sh != '0'){
					sh = '0';
					System.out.println("игрок—тоит");
				}
				
				if(ch == '1' && sh == '0'){
					sh = '1';
					System.out.println("игрокЌачалƒвижение¬право");
				}
				if(ch == '2' && sh == '0'){
					sh = '2';
					System.out.println("игрокЌачалƒвижение¬лево");
				}
				if(ch == '3' && sh == '0'){
					sh = '3';
					System.out.println("игрокЌачалƒвижение¬низ");
				}
				if(ch == '4' && sh == '0'){
					sh = '4';
					System.out.println("игрокЌачалƒвижение¬верх");
				}
				if(ch == '1' && sh != '1'){
					sh = '1';
					System.out.println("игрокѕовернулЌаправо");
				}
				if(ch == '2' && sh != '2'){
					sh = '2';
					System.out.println("игрокѕовернулЌалево");
				}
				if(ch == '3' && sh != '3'){
					sh = '3';
					System.out.println("игрокѕовернул¬низ");
				}
				if(ch == '4' && sh != '4'){
					sh = '4';
					System.out.println("игрокѕовернул¬верх");
				}
				
				if(ch == '8' && sh != '8'){
					sh = '8';
					System.out.println("игрок—ъелѕеченьку");
				}
				
			}
			System.out.println(" онец»гры");
			try {
				readStream.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}*/
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
							   	menuScene.setRoot(replaysList);
							   	appearMenu.play();
						   }
					   });
				   } 
			}); 
			thread.start();
			
		});
		
		
		buttonReplay.setOnMouseEntered(MouseEvent ->{
			buttonReplay.setEffect(enteredShadow);
			buttonReplay.setScaleX(1.5);
			buttonReplay.setScaleY(1.5);
		});
		
		buttonReplay.setOnMouseExited(MouseEvent ->{
			buttonReplay.setEffect(null);
			buttonReplay.setScaleX(1);
			buttonReplay.setScaleY(1);				
		});	
		
		buttonMusic.setOnAction(actionEvent ->{
			Thread thread = new Thread(new Runnable(){ 
				   public void run() 
				   { 
					   try { 
						   Thread.sleep(500); 
					   } catch (InterruptedException e) {} 
					   Platform.runLater(new Runnable() {
						   @Override
						   public void run() {
							   
								GameProcess.startGame(primaryStage,3,null);
						   }
					   });
				   } 
			}); 
			thread.start();
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
		
		
		
		for (Button tempButton : filesButton) {
			
			tempButton.setOnAction(MouseEvent ->{
				GameProcess.startGame(primaryStage, 2,tempButton.getText());
						
			}); 
			
			tempButton.setOnMouseEntered(MouseEvent ->{
				tempButton.setEffect(enteredShadow);
						
			});
			
			tempButton.setOnMouseExited(MouseEvent ->{
				tempButton.setEffect(null);
						
			});
			
			
		}

	}
}
