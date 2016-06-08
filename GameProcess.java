package application;
//import scalaSort.*;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import application.Map;

enum Direction {
	Up,Down,Left,Right
}

public class GameProcess {
	public static  Scene gameScene;
	public static  Group root;
	public static Pane mapLayer;
	public static Pane gameLayer;
	public static Map map;
	public static Label gameScore;
	public static final int GAME_X = 1280,GAME_Y = 1024;
	public static final int CURRENT_MAX_ENEMY = 3;
	public static final int ROUND_MAX_ENEMY = 9;
	public static AnimationTimer gameLoop;
	public static long previousTime = 0;
	public static long oldTime = -1;
	public static long bulletTime = 0;
	public static ImageView playerCarImage;
	public static Player player;
	public static Enemy tempEnemy;
	public static int countForDeath = 3;
	public static boolean flagAnimationEnemy = false;
	public static boolean deleteEnemy = false;
	public static ArrayList<Enemy> enemyList = new ArrayList<>();
	public static ArrayList<MyFiles> xu = new ArrayList<>();
	public static int counterForEnemy = 0;
	public static int counterAfterDeath = 0;
	public static ArrayList<Bullet> bulletList = new ArrayList<>();
	

	public static void startGame(Stage primaryStage,int mode, String fileName){
		
		root =  new Group();
		mapLayer = new Pane();
		gameLayer = new Pane();
		player = new Player();
		Enemy firstEnemy = new Enemy();
		enemyList.add(firstEnemy);
		root.getChildren().addAll(mapLayer,gameLayer);
		
		
		gameScene = new Scene(root, GAME_X, GAME_Y);
		primaryStage.setScene(gameScene);
		gameScene.getStylesheets().add(GameProcess.class.getResource("application.css").toExternalForm());
		
		map = new Map();
		map.createMap();
		
		Label gameScore = new Label("SCORE ");
		gameScore.setId("scoreString");
		gameScore.setLayoutX(4*64);
		gameScore.setLayoutY(-20);
		
		Label scoreNumber = new Label("");
		scoreNumber.setId("scoreNumber");
		scoreNumber.setLayoutX(6*64+20);
		scoreNumber.setLayoutY(5);
		
		mapLayer.getChildren().add(gameScore);
		mapLayer.getChildren().add(scoreNumber);
				
		if(mode == 1 || mode == 3){
			player.addInputControls();
			try {
				player.setObjectOutputStream(mode);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if(mode == 2){
			
			try {
				player.setObjectInputStream(fileName);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		
	
		 
		oldTime = System.currentTimeMillis();
		gameLoop = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
		        long animationTime = System.currentTimeMillis();
		        long enemyTime = System.currentTimeMillis();
		        long Time = System.currentTimeMillis();
		        
		    
		       if(map.isChangeMap()){
		    	   mapLayer.getChildren().clear();
		    	   oldTime = enemyTime;
		    	   if(Map.level == 0){
		    	    Map.level++;
		    	    player.levelUp();
		    	   }
		    	   else 
		    		   Map.level = 0; 
		    	   map.createMap();
		    	   player.reset();
		    	   counterForEnemy = 0;
		    	   for (Enemy enemyes : enemyList)
	        			gameLayer.getChildren().remove(enemyes.getImageView());
		    	   enemyList.clear();
		   		Enemy firstEnemy = new Enemy();
				enemyList.add(firstEnemy);
				mapLayer.getChildren().add(gameScore);
				mapLayer.getChildren().add(scoreNumber);
		    	   
		       }
		      
		        if(player.isAlive()){
		        	
		        	try {
						if(player.control(Time,mode) == false){
							stop();
							int i=0;
							
							primaryStage.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
		        	
		        	player.interationWithMap(Time,mode);
		        	player.update();
		        	if(mode == 3){
			        	for(Enemy enemyes : enemyList){
			        		player.shotAnalyze(enemyes, Time);
			        	}
		        	}
		        }
		        
		        
			    /*
			     * Если есть пуля выполняем методы пули
			     */
		        if (!bulletList.isEmpty()){
		        	Bullet bullet = bulletList.get(0);
		        	bullet.interationWithMap();
		        	bullet.update();
		        	
		      	  	if(bullet.getMustDestroyed()){
		    
						gameLayer.getChildren().remove(bullet.getImageView());
						bulletList.clear();
		      	  	} 

		        	
		        }    
		
		        
		       
		        for (Enemy enemyes : enemyList) {
		        	enemyes.interationWithMap();
		        	enemyes.update();
		        	enemyes.collisionWithPlayer(player);
		        	
		       		        	
		        	if (flagAnimationEnemy == true){
		        		flagAnimationEnemy = false;
		        		for (Enemy enemyes2 : enemyList)
		        			enemyes2.animation();
		        	}
		        	if (!bulletList.isEmpty()){
		        		Bullet bullet = bulletList.get(0);
		        		if (bullet.collisionWithEnemy(enemyes)){
		        			deleteEnemy = true;
		        			player.addToScore(250);
		        			tempEnemy = enemyes;
		        			
		        		}
		        	}
		        	
                }

		        
		        if(deleteEnemy){
		        	deleteEnemy = false;
		        	if(mode == 1 || mode == 3)
	      	  			player.killEnemy();
		        	gameLayer.getChildren().remove(tempEnemy.getImageView());
        			enemyList.remove(tempEnemy);
		        }

		        if (Time - player.getBulletTime() >= 8000) {
		        	player.setBullet();       		
	
		        }
		        
		        
		        if(enemyTime - oldTime >= 6000 && enemyList.size() < CURRENT_MAX_ENEMY && counterForEnemy < ROUND_MAX_ENEMY){
		        	oldTime = enemyTime; 
		        	Enemy newEnemy = new Enemy();
    				enemyList.add(newEnemy);	
    				counterForEnemy++;
    		 	}
		        


		        if (animationTime - previousTime >= 150) {
		        	previousTime = animationTime;	
		        	flagAnimationEnemy = true;
		        	if(player.isAlive()){
		        		player.animation();
		        		scoreNumber.setText(player.getScore());
		        	}
		        	else {
		        		player.deathAnimation();
		        		counterAfterDeath++;
		        		if(counterAfterDeath == 20){
		        			counterAfterDeath = 0;
		        			countForDeath--;
		        			if(countForDeath == 0){
		        				player.end();
		        				System.exit(0);
		        			}
		        			gameLayer.getChildren().remove(0);
		        			player.reset();
		        			for (Enemy enemyes : enemyList)
			        			gameLayer.getChildren().remove(enemyes.getImageView());
		        			enemyList.clear();
		        			oldTime = enemyTime;
		        			Enemy newEnemy = new Enemy();
		    				enemyList.add(newEnemy);
		        		}
		        		
		        	}
		        }
		    
			}
		};
		gameLoop.start();
	}
}
