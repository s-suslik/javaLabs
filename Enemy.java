package application;

import java.util.BitSet;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Enemy {
	private SpriteAnimation enemySprite ;
	private Direction enemyDirection = Direction.Left;
	private double enemyDeltaY = 0.0;
	private double enemyDeltaX = -4.0;
	private double enemyY = 2*64.0;
	private double enemyX = 18*64.0;
	private static final int ENEMY_WIDTH = 64, ENEMY_HEIGHT = 64 ;
	private static final double ENEMY_SPEED = 4.0;
	private static final int ANIMATION_LINE = 0;
	public Enemy(){
		ImageView tileView = new ImageView(new Image(getClass().getResourceAsStream("data/enemy.png")));
	    enemySprite = new SpriteAnimation(tileView,2,0,0,ENEMY_WIDTH,ENEMY_HEIGHT);
	    	    
	    enemySprite.imageView.setLayoutX(enemyX);
	    enemySprite.imageView.setLayoutY(enemyY);
	    
	    
		GameProcess.gameLayer.getChildren().addAll(enemySprite.imageView);
	}
	
	/*
	 * Метод анимации
	 */
	public void animation(){
		enemySprite.animation(ANIMATION_LINE);
	}
	
	/*
	 * Метод обновления кординат
	 * 
	 */
	
	public void update(){
		enemyX = enemySprite.imageView.getLayoutX()+enemyDeltaX;
		enemyY = enemySprite.imageView.getLayoutY()+enemyDeltaY;
		
		enemySprite.imageView.setLayoutX(enemyX);
		enemySprite.imageView.setLayoutY(enemyY);
	}
	
	
	public Direction getDirection(){
		return enemyDirection;
	}
	/*
	 * Джвижение вверх
	 */
	
    public void moveUp(){
		
    	enemyDeltaY = -ENEMY_SPEED;   		
        enemyDeltaX = 0.0;
    	enemyDirection = Direction.Up; 
	}
    
	/*
	 * Джвижение вниз
	 */
	public void moveDown(){
		
		enemyDeltaY = ENEMY_SPEED;
        enemyDeltaX = 0.0;
        enemyDirection = Direction.Down;
	}
	
	/*
	 * Джвижение влево
	 */
	public void moveLeft(){
		
    	enemyDeltaX = -ENEMY_SPEED;	
    	enemyDeltaY = 0.0;
    	enemyDirection = Direction.Left;
	}
	/*
	 * Джвижение вправо
	 */
	public void moveRight(){
		
		enemyDeltaX = ENEMY_SPEED;
        enemyDeltaY = 0.0;
        enemyDirection = Direction.Right;
	}
		
	public double getX(){
		return enemyX;
	}
	
	public double getY(){
		return enemyY;
	}
	
	public double getHeight(){
		return enemySprite.getHeight();
	}
	
	public ImageView getImageView(){
		return enemySprite.getImageView();
	}
	
	public double getWidth(){
		return enemySprite.getWidth();
	}
	
	/*
	 * Взаимодействие с игроком
	 */
	
	public boolean collisionWithPlayer(Player player){
		switch (enemyDirection)
		{
		case Left: {    
					if (player.getX() + 64 >= enemyX &&
						player.getX() <= enemyX && 
						((enemyY <= player.getY() && 
						enemyY + ENEMY_HEIGHT >= player.getY()) || 
						enemyY >= player.getY() && 
						enemyY <= player.getY() + 64 )){
						System.out.println("kek");
						player.death();
						return true;
					}
					break;
		}
		case Right:{        
				   if (player.getX() <= enemyX + ENEMY_WIDTH && 
					   player.getX() >= enemyX && 
					   ((player.getY() <= enemyY && 
					   player.getY() + 64 >= enemyY ) ||
					   player.getY() >= enemyY && 
					   player.getY() <= enemyY + ENEMY_HEIGHT)) {
					   System.out.println("kek");
					   player.death();
					   return true;
				   }
				   break;
		}
		case Up: {      
					if (player.getY() + 64 >= enemyY &&
						player.getY() <= enemyY && 
						((enemyX <= player.getX() && 
						enemyX + ENEMY_WIDTH >= player.getX()) || 
						enemyX >= player.getX() && 
						enemyX <= player.getX() + 64)){
						System.out.println("kek");
						player.death();
						return true;
					}
					break;
		}
		/*case Down: {         
					if (player.getY() <= enemyY + ENEMY_HEIGHT && 
						player.getY() >= enemyY && 
					   (player.getX() <= enemyX && 
						player.getX() + 64 >= enemyX ) ||
						player.getX() >= enemyX && 
						player.getX() <= enemyX + ENEMY_WIDTH){
						System.out.println("kek");
						player.death();
						return true;
					}
					break;
		}*/
		}
	return false;	
}
	/*
	 * Метод взаимодействия с картой
	 */
	
	public void interationWithMap(){
		
		double height = enemySprite.getHeight();
		double width = enemySprite.getWidth();
		
		
		for (int i = (int) (enemyY / 64); i < (enemyY + height) / 64; i++)
		for (int j = (int) (enemyX / 64); j < (enemyX + width) / 64; j++){
			
		
			
		    if (Map.mapMatrix[i][j] == 1 || Map.mapMatrix[i][j] == 2 || Map.mapMatrix[i][j] == 10 ||
				Map.mapMatrix[i][j] == 9 || Map.mapMatrix[i][j] == 3 || Map.mapMatrix[i][j] == 8 ||
				Map.mapMatrix[i][j] == 11 ||
				Map.mapMatrix[i][j] == 12 || Map.mapMatrix[i][j] == 13 || Map.mapMatrix[i][j] == 14){
					
					switch(enemyDirection){
					case Up:{
						
						enemyY = i*64+64;
						enemySprite.imageView.setLayoutY(enemyY);
						
						if(Map.mapMatrix[i + 1][j + 1] == 0  && Map.mapMatrix[i + 1][j - 1] != 0 ){
							moveRight();
							return;

						}
						else if(Map.mapMatrix[i + 1][j + 1] != 0 && Map.mapMatrix[i + 1][j - 1] == 0){
							moveLeft();
							return;
						}
						else if(Map.mapMatrix[i + 1][j + 1] != 0 && Map.mapMatrix[i + 1][j - 1] != 0){
							moveDown();
							return;
						}
						else if(Map.mapMatrix[i + 1][j + 1] == 0 && Map.mapMatrix[i + 1][j - 1] == 0){
							if(GameProcess.player.getX() < enemyX){
								moveLeft();
								return;
							}
							else{
								moveRight();
								return;
							}
						}
						break;
						
					}
					case Down:{
						
						enemyY = i*64-64;
						enemySprite.imageView.setLayoutY(enemyY);
						
						if(Map.mapMatrix[i - 1][j + 1] == 0  && Map.mapMatrix[i - 1][j - 1] != 0 ){
							moveRight();
							return;
						}
						else if(Map.mapMatrix[i - 1][j + 1] != 0 && Map.mapMatrix[i - 1][j - 1] == 0){
							moveLeft();
							return;
						}
						else if(Map.mapMatrix[i - 1][j + 1] != 0 && Map.mapMatrix[i - 1][j - 1] != 0){
							moveUp();
							return;
						}
						else if(Map.mapMatrix[i - 1][j + 1] == 0 && Map.mapMatrix[i - 1][j - 1] == 0){
							if(GameProcess.player.getX() < enemyX){
								moveLeft();
								return;
							}
							else{
								moveRight();
								return;
							}
						}
						break;
					}
					
					case Left:{
						
						enemyX = j*64+64;
						enemySprite.imageView.setLayoutX(enemyX);
						
						if(Map.mapMatrix[i + 1][j + 1] == 0  && Map.mapMatrix[i - 1][j + 1] != 0){
							moveDown();
							return;
							
						}
						else if(Map.mapMatrix[i - 1][j + 1] == 0  && Map.mapMatrix[i + 1][j + 1] != 0){
							moveUp();
							return;
							
						}
						else if(Map.mapMatrix[i - 1][j + 1] != 0  && Map.mapMatrix[i + 1][j + 1] != 0){
							moveRight();
							return;
						}
						else if(Map.mapMatrix[i - 1][j + 1] == 0  && Map.mapMatrix[i + 1][j + 1] == 0){
							if(GameProcess.player.getY() < enemyY){
								moveUp();
								return;
								
							}
							else{
								moveDown();
								return;
								
							}
						}
						break;
					}
					
					case Right:{
						enemyX = j*64-64;
						enemySprite.imageView.setLayoutX(enemyX);
						
						if(Map.mapMatrix[i + 1][j - 1] == 0  && Map.mapMatrix[i - 1][j - 1] != 0){
							moveDown();
							return;

						}
						else if(Map.mapMatrix[i - 1][j - 1] == 0  && Map.mapMatrix[i + 1][j - 1] != 0){
							moveUp();
							
							return;
						}
						else if(Map.mapMatrix[i - 1][j - 1] != 0  && Map.mapMatrix[i + 1][j - 1] != 0){
							moveLeft();
							return;
						}
						else if(Map.mapMatrix[i - 1][j - 1] == 0  && Map.mapMatrix[i + 1][j - 1] == 0){
							
							if(GameProcess.player.getY() < enemyY){
								moveUp();
								return;
								
							}
							else{
								moveDown();
								return;
							}
						}
						break;
					}
					}
			}
		    else {

				switch (enemyDirection) {
				
				case Down:
					if(Map.mapMatrix[i][j - 1] == 0 && enemyY == i*64 && 
					GameProcess.player.getX() < enemyX && 
					Math.abs(GameProcess.player.getY() - enemyY) < 
					Math.abs(GameProcess.player.getX() - enemyX) ){
						moveLeft();
						return;
					}
					else if(Map.mapMatrix[i][j + 1] == 0 && enemyY == i*64 && 
					GameProcess.player.getX() > enemyX && 
					Math.abs(GameProcess.player.getY() - enemyY) <
					Math.abs(GameProcess.player.getX() - enemyX)){
						moveRight();
						return;
					}
					break;
					
				case Up:
					if(Map.mapMatrix[i][j - 1] == 0 && enemyY == i*64 && 
					GameProcess.player.getX() < enemyX && 
					Math.abs(GameProcess.player.getY() - enemyY) < 
					Math.abs(GameProcess.player.getX() - enemyX)){
						moveLeft();
						return;
					}
					else if(Map.mapMatrix[i][j + 1] == 0 && enemyY == i*64 &&
					GameProcess.player.getX() > enemyX && 
					Math.abs(GameProcess.player.getY() - enemyY) <
					Math.abs(GameProcess.player.getX() - enemyX)){
						moveRight();
						return;
					}
					break;
					
				case Left:
					if(Map.mapMatrix[i - 1][j] == 0 && enemyX == j*64 &&
					GameProcess.player.getY() < enemyY &&
					Math.abs(GameProcess.player.getY() - enemyY) > 
					Math.abs(GameProcess.player.getX() - enemyX)){
						moveUp();
						return;
					}
					else if(Map.mapMatrix[i + 1][j] == 0 && enemyX == j*64 &&
					GameProcess.player.getY() > enemyY &&
					Math.abs(GameProcess.player.getY() - enemyY) > 
					Math.abs(GameProcess.player.getX() - enemyX)){
						moveDown();
						return;
					}
					break;
				case Right:
					
					if(Map.mapMatrix[i + 1][j] == 0 && enemyX == j*64 && 
					GameProcess.player.getY() > enemyY &&
					Math.abs(GameProcess.player.getY() - enemyY) > 
					Math.abs(GameProcess.player.getX() - enemyX)){
						moveDown();
						return;
					}
					else if(Map.mapMatrix[i - 1][j] == 0 && enemyX == j*64 &&
					GameProcess.player.getY() < enemyY &&
					Math.abs(GameProcess.player.getY() - enemyY) > 
					Math.abs(GameProcess.player.getX() - enemyX)){
						moveUp();
						
						return;
					}
					break;
				}
		    }
										
		}
	}
}
