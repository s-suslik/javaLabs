package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Bullet {
	private ImageView bulletSprite;
	private Direction bulletDirection;
	private double bulletDeltaY = 0.0;
	private double bulletDeltaX = 0.0;
	private double bulletY = 0.0;
	private double bulletX = 0.0;
	private double bulletWidth = 24.0;
	private double bulletHeight = 24.0;

	private boolean mustDestroyed = false;
	private static final double BULLET_SPEED = 7.0;
	
	public Bullet(double startX, double startY, Direction startDirection){
		bulletSprite = new ImageView(new Image(getClass().getResourceAsStream("data/bullet.png")));
		
		bulletX = startX;
		bulletY = startY;
		bulletSprite.setLayoutX(startX+10);
		bulletSprite.setLayoutY(startY+10);
		
		switch (startDirection) {
		
		case Up:
			bulletDeltaX = 0.0;
			bulletDeltaY = -BULLET_SPEED;
			bulletDirection = Direction.Up;
			break;
		case Down:
			bulletDeltaX = 0.0;
			bulletDeltaY = BULLET_SPEED;
			bulletDirection = Direction.Down;
			break;
		case Left:
			bulletDeltaX = -BULLET_SPEED;
			bulletDeltaY = 0.0;		
			bulletDirection = Direction.Left;
			break;
		case Right:
			bulletDeltaX = BULLET_SPEED;
			bulletDeltaY = 0.0;		
			bulletDirection = Direction.Right;
			break;
		}
		
		GameProcess.gameLayer.getChildren().addAll(bulletSprite);
		
	}
	
	public void update(){
		bulletX = bulletSprite.getLayoutX()+bulletDeltaX;
		bulletY = bulletSprite.getLayoutY()+bulletDeltaY;
		
		bulletSprite.setLayoutX(bulletX);
		bulletSprite.setLayoutY(bulletY);
	}
	
	/*
	 * Взаимодействие с картой
	 */
	public void interationWithMap(){
		
		for (int i = (int) (bulletY / 64); i < (bulletY + 24) / 64; i++)
		for (int j = (int) (bulletX / 64); j < (bulletX + 24) / 64; j++){
				
				
				if (Map.mapMatrix[i][j] == 9 || Map.mapMatrix[i][j] == 2 || Map.mapMatrix[i][j] == 10 ||
				    Map.mapMatrix[i][j] == 8 || Map.mapMatrix[i][j] == 3 || Map.mapMatrix[i][j] == 1 || 
				    Map.mapMatrix[i][j] == 11 ||
					Map.mapMatrix[i][j] == 12 || Map.mapMatrix[i][j] == 13 || Map.mapMatrix[i][j] == 14){
					mustDestroyed = true;
				}
					
	   }
  }
	
	public boolean getMustDestroyed(){
		return mustDestroyed;
	}
	
	public void setMustDestroyed(){
		mustDestroyed = true;
	}
	
	public ImageView getImageView(){
		return bulletSprite;
	}
	
	public double getX(){
		return bulletX;
	}
	
	public double getY(){
		return bulletY;
	}
	
	public double getHeight(){
		return bulletHeight;
	}
	
	public double getWidth(){
		return bulletWidth;
	}
	/*
	 * Используется для вычисления столкновения пули с врагом
	 */
	public boolean collisionWithEnemy(Enemy enemy){
			switch (bulletDirection)
			{
			case Left: {    
						if (enemy.getX() + 64 >= bulletX &&
							enemy.getX() <= bulletX && 
							((bulletY <= enemy.getY() && 
							bulletY + bulletHeight >= enemy.getY()) || 
							bulletY >= enemy.getY() && 
							bulletY <= enemy.getY() + 64 )){
							System.out.println("kek");
							mustDestroyed = true;
							return true;
						}
						break;
			}
			case Right:{        
					   if (enemy.getX() <= bulletX + bulletWidth && 
						   enemy.getX() >= bulletX && 
						   ((enemy.getY() <= bulletY && 
						   enemy.getY() + 64 >= bulletY ) ||
						   enemy.getY() >= bulletY && 
						   enemy.getY() <= bulletY + bulletHeight)) {
						   System.out.println("kek");
						   mustDestroyed = true;
						   return true;
					   }
					   break;
			}
			case Up: {      
						if (enemy.getY() + 64 >= bulletY &&
							enemy.getY() <= bulletY && 
							((bulletX <= enemy.getX() && 
							bulletX + bulletWidth >= enemy.getX()) || 
							bulletX >= enemy.getX() && 
							bulletX <= enemy.getX() + 64)){
							System.out.println("kek");
							mustDestroyed = true;
							return true;
						}
						break;
			}
			case Down: {         
						if (enemy.getY() <= bulletY + bulletHeight && 
							enemy.getY() >= bulletY && 
						   (enemy.getX() <= bulletX && 
							enemy.getX() + 64 >= bulletX ) ||
							enemy.getX() >= bulletX && 
							enemy.getX() <= bulletX + bulletWidth){
							System.out.println("kek");
							mustDestroyed = true;
							return true;
						}
						break;
			}
			}
		return false;	
	}
	
	
}
