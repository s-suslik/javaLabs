package application;

import java.util.ArrayList;
import java.util.BitSet;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/** Класс игрока
 * @author Сусликов Александр
 * @version 1.0
*/
public class Player {
	
	
	private Random random = new Random();
	
	private SpriteAnimation playerSprite ;
	private Direction playerDirection = Direction.Right;
	private double playerDeltaY = 0.0;
	private double playerDeltaX = 0.0;
	private Integer playerScore = 0;
	private double playerY = 896.0;
	private long botTime = 0;
	private long moveTime = 0;
	private boolean shotBot = false;
	private double playerX = 640.0;
	private long bulletTime = 0;
	private boolean isOldMove = false;
	private boolean isBullet = true;
	private boolean life = true;
	private int currentTileX, currentTileY;
	private static final double PLAYER_SPEED = 3.0;
	private static final int PLAYER_WIDTH = 56;
	private static final int PLAYER_HEIGHT = 42;
	private static final int LEFT_LINE = 1;
	private static final int RIGHT_LINE = 0;
	private static final int UP_LINE = 0;
	private static final int DOWN_LINE = 3;
	private static final int DEATH_LINE = 4;	
	private static final int VERTICAL_OFFSET = 218;
	private int tmp;
	private BitSet keyboardBitSet;
	private int currentLine;
	public char moveCode;
	public ObjectOutputStream writeStream; 
	public ObjectInputStream readStream; 
	public ArrayList<Integer> list  = new ArrayList<Integer>();
	
	/** Создает новый объект игрока
	* 
	* @see Player#Player()
	*/
	public Player(){
		ImageView tileView = new ImageView(new Image(getClass().getResourceAsStream("data/hero.png")));
	    playerSprite = new SpriteAnimation(tileView,2,0,0,PLAYER_WIDTH,PLAYER_HEIGHT);
	    for (int i = 0; i < 3; i++){
	    	
	    	ImageView lifeImage = new ImageView(new Image(getClass().getResourceAsStream("data/life.png")));
	    
	    	lifeImage.setLayoutX(i*64);
	    	lifeImage.setLayoutY(0);
	    	GameProcess.gameLayer.getChildren().addAll(lifeImage);
	    	
	    	
	    }
	    
	    keyboardBitSet = new BitSet();
	    	 

	    playerSprite.imageView.setLayoutX(playerX);
	    playerSprite.imageView.setLayoutY(playerY);
	    
	    
	    
		GameProcess.gameLayer.getChildren().addAll(playerSprite.imageView);
	}
	
	public void setObjectOutputStream(int mode) throws IOException{
		Date d = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy hh mm ss");
        if (mode == 1)
        	writeStream = new ObjectOutputStream(new FileOutputStream("saves/" + "p " + format1.format(d) + ".txt"));
        else if(mode == 3)
        	writeStream = new ObjectOutputStream(new FileOutputStream("saves/" + "b " + format1.format(d) + ".txt"));
	}
	
	
	
	public void setObjectInputStream(String fileName) throws IOException{
		readStream = new ObjectInputStream(new FileInputStream("saves/" + fileName));
	}
	
	public long getBulletTime(){
		return bulletTime;
	}
	/** Функция для получения значения поля {@link Player#playerX}
     * @return Возвращает кординату игрока по X 
     */
	public double getX(){
		return playerX;
	}
	
	public double getY(){
		return playerY;
	}
	
	public String getScore(){
		return playerScore.toString();
	}
	
	public void setBullet(){
		isBullet = true;
		
		switch (playerDirection) {
		case Up:
			currentLine = UP_LINE;
			break;
		case Down:
			currentLine = DOWN_LINE;
			break;
		case Left:
			currentLine = LEFT_LINE;
			break;
		case Right:
			currentLine = RIGHT_LINE;
			break;
		}
	}
	
	public void addToScore(int value) {
		playerScore += value;
	}

	public void addInputControls(){
	
	    GameProcess.gameScene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
	        keyboardBitSet.set(keyEvent.getCode().ordinal(), true);
	    });
	
	    GameProcess.gameScene.addEventFilter(KeyEvent.KEY_RELEASED, keyEvent -> {
	        keyboardBitSet.set(keyEvent.getCode().ordinal(), false);
	    });
	}

	public boolean isAlive(){
		return life;
	}

	public void killEnemy(){
		try {
			writeStream.writeChar('7');
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void levelUp(){
		try {
			writeStream.writeChar('/');
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean control(long currentTime, int mode) throws IOException {

	    boolean isUpPressed = false;
	    boolean isLeftPressed = false;
	    boolean isDownPressed = false;
	    boolean isRightPressed = false;
	    boolean isSpacePressed = false;
	    boolean isEscPressed = false;
	    
	    if(mode == 1){
		    isUpPressed = keyboardBitSet.get(KeyCode.UP.ordinal());
		    isLeftPressed = keyboardBitSet.get(KeyCode.LEFT.ordinal());
		    isDownPressed = keyboardBitSet.get(KeyCode.DOWN.ordinal());
		    isRightPressed = keyboardBitSet.get(KeyCode.RIGHT.ordinal());
		    isSpacePressed = keyboardBitSet.get(KeyCode.SPACE.ordinal());
		    isEscPressed = keyboardBitSet.get(KeyCode.ESCAPE.ordinal());
	    }
	    else if(mode == 3){

	    	switch (tmp) {
			case 1:
				isRightPressed = true;
				break;
			case 2:
				isLeftPressed = true;
				break;
			case 3:
				isDownPressed = true;
				break;
			case 4:
				isUpPressed = true;
				break;
			}
	    	if(shotBot == true){
	    		isSpacePressed = true;
	    		shotBot = false;
	    	}
	    	
	    	
	    }
	    else if(mode == 2){
			
			moveCode = readStream.readChar();
			while(true){
				if(moveCode == '7' || moveCode == '/' || moveCode == '8')
					moveCode = readStream.readChar();
				else 
					break;
			}
			switch (moveCode) {
			case '1':
				isRightPressed = true;
				break;
			case '2':
				isLeftPressed = true;
				break;
			case '3':
				isDownPressed = true;
				break;
			case '4':
				isUpPressed = true;
				break;
			case '5':
				isSpacePressed = true;
				break;
			case '6':
				isEscPressed = true;
				break;
			}
	    }
	    
		
		if(isOldMove == true){
			currentTileX = (int)(playerX/64);
			currentTileY = (int)(playerY/64);
			isOldMove = false;
		}
		
		
		if(isEscPressed){
			if(mode == 1 || mode == 3)
				moveCode = '6';
		}
		else if (isSpacePressed && isBullet ){
			
			if(mode == 1 || mode == 3)
				moveCode = '5';	
			
			shot(currentTime);
			switch (playerDirection) {
			case Up:
				currentLine = UP_LINE + 1;
				break;
			case Down:
				currentLine = DOWN_LINE - 1;
				break;
			case Left:
				currentLine = LEFT_LINE + 2;
				break;
			case Right:
				currentLine = RIGHT_LINE + 2;
				break;
			}
		}
	    
		else if (isUpPressed) {
			
			if(mode == 1 || mode == 3)
				moveCode = '4';		
	
	    	if(playerDeltaX > 0 && !isOldMove && (currentTileX*64+64) - playerX < 3){
	
	            	playerX = currentTileX*64+64;
	        		playerSprite.imageView.setLayoutX(playerX);
	        		moveUp();
	        }
	    	
	    	if(playerDeltaX < 0 && !isOldMove && playerX - (currentTileX*64) < 3){
	            	
	            	playerX = currentTileX*64;
	        		playerSprite.imageView.setLayoutX(playerX);
	        		moveUp();
	
	        }
	        if(playerDeltaY < 0 || playerDeltaY > 0 || (playerDeltaY == 0.0 && playerDeltaX == 0.0)) {
	        	
	        		moveUp();
	        }
	        
	        
	        
	    } else if (isDownPressed ) {
	    	
	    	if(mode == 1 || mode == 3)
				moveCode = '3';		

	    	
	    	if(playerDeltaX > 0 && !isOldMove && (currentTileX*64+64) - playerX < 3){
	            		
		            	playerX = currentTileX*64+64;
		        		playerSprite.imageView.setLayoutX(playerX);
		        		moveDown();
	        }
	    	
	        if(playerDeltaX < 0 && !isOldMove && playerX - (currentTileX*64) < 3){
	
		            	playerX = currentTileX*64;
		        		playerSprite.imageView.setLayoutX(playerX);
	            		moveDown();
	        }
	    	
	    	if(playerDeltaY < 0 || playerDeltaY > 0 || (playerDeltaY == 0.0 && playerDeltaX == 0.0)) {
	    	
	            	moveDown();
	    	}
	    	 
	        
	    } else if (isLeftPressed){
	    	
	    	if(mode == 1 || mode == 3)
				moveCode = '2';	
			
	    	
	    	 if(playerDeltaY >  0 && !isOldMove && (currentTileY*64+64) - playerY < 3 ){
	            		
		            	playerY = currentTileY*64+64;
		        		playerSprite.imageView.setLayoutY(playerY);
		        		moveLeft();
	            	
	        }
	    	
	    	if(playerDeltaY < 0 && !isOldMove && playerY - (currentTileY*64) < 3 ){
	            		
	            		playerY = currentTileY*64;
	            		playerSprite.imageView.setLayoutY(playerY);
	    	        	moveLeft();
	        }
	    	
	    	if(playerDeltaX < 0 || playerDeltaX > 0 || (playerDeltaY == 0.0 && playerDeltaX == 0.0)) {
	        	
	        		moveLeft();
	    	}
	    	
	    } else if (isRightPressed) {
	    	
	    	if(mode == 1 || mode == 3)
				moveCode = '1';	
			
	    	
	        if(playerDeltaY >  0 && !isOldMove && (currentTileY*64+64) - playerY < 3 ){
	
	            	playerY = currentTileY*64+64;
	        		playerSprite.imageView.setLayoutY(playerY);
	            	moveRight();
	
	        }
	    	   	
	        if(playerDeltaY < 0 && !isOldMove && playerY - (currentTileY*64) < 3 ){
	        		
	            	playerY = currentTileY*64;
	        		playerSprite.imageView.setLayoutY(playerY);
	            	moveRight();
	        }
	    	
	    	if(playerDeltaX < 0 || playerDeltaX > 0 || (playerDeltaY == 0.0 && playerDeltaX == 0.0)) {
	            	
	            	moveRight();
	    	}
	        
	    }
	    
	    else {
	    	
	    	if(mode == 1 || mode == 3)
				moveCode = '0';		
			
	    	
	        if(playerDeltaX > 0 && !isOldMove){
	            if((currentTileX*64+64) - playerX < 3){
	            	playerX = currentTileX*64+64;
	        		playerSprite.imageView.setLayoutX(playerX);
	            	playerDeltaX = 0.0;
	            	isOldMove = true;
	            }
	        }
	        
	        if(playerDeltaX < 0 && !isOldMove){
	            if(playerX - (currentTileX*64) < 3){
	            	playerX = currentTileX*64;
	        		playerSprite.imageView.setLayoutX(playerX);
	            	playerDeltaX = 0.0;
	            	isOldMove = true;
	            }
	        }
	        
	        if(playerDeltaY > 0 && !isOldMove){
	            if((currentTileY*64+64) - playerY < 3){
	            	playerY = currentTileY*64+64;
	        		playerSprite.imageView.setLayoutY(playerY);
	            	playerDeltaY = 0.0;
	            	isOldMove = true;
	            }
	        }
	        
	        if(playerDeltaY < 0 && !isOldMove){
	            if(playerY - (currentTileY*64) < 3){
	            	playerY = currentTileY*64;
	        		playerSprite.imageView.setLayoutY(playerY);
	            	playerDeltaY = 0.0;
	            	isOldMove = true;
	            }
	        }
	        
	    }
		
		if(mode == 3 && (currentTime - botTime) >= moveTime){
	    	botTime = currentTime;
	    	if(tmp == 0){
	    		tmp = random.nextInt(4);
	    		tmp++;
	    	}
	    	else if(tmp == 1){
	    		do {
	    			tmp = random.nextInt(4);
	    			tmp++;
	    		}while(tmp == 2);
	    	}
	    	else if(tmp == 2){
	    		do {
	    			tmp = random.nextInt(4);
	    			tmp++;
	    		}while(tmp == 1);
	    	}
	    	else if(tmp == 3){
	    		do {
	    			tmp = random.nextInt(4);
	    			tmp++;
	    		}while(tmp == 4);
	    	}
	    	else if(tmp == 4){
	    		do {
	    			tmp = random.nextInt(4);
	    			tmp++;
	    		}while(tmp == 3);
	    	}
	    	moveTime =  random.nextInt(5)*600;

		}

		if(mode == 1 || mode == 3){
			writeStream.writeChar(moveCode);
			writeStream.flush();
			
			if(moveCode == '6'){
				writeStream.close();
				return false;
			}
			
		}
		if(isEscPressed){
			return false;

			
		}
		return true;
	
	}
	
	public void end(){
		
		try {
			writeStream.writeChar('6');
			writeStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void shot(long currentTime){
		bulletTime = currentTime;
		isBullet = false;
		Bullet bullet = new Bullet(playerX, playerY,playerDirection);
		GameProcess.bulletList.add(0, bullet);; 
	}
	
	public void update(){
		playerX = playerSprite.imageView.getLayoutX()+playerDeltaX;
		playerY = playerSprite.imageView.getLayoutY()+playerDeltaY;
		
		playerSprite.imageView.setLayoutX(playerX);
		playerSprite.imageView.setLayoutY(playerY);
	}
	
	public void moveUp(){
		
    	playerDeltaY = -PLAYER_SPEED;
    	playerSprite.setWidth(42);
    	playerSprite.setHeight(55);
    	playerSprite.setOffsetY(VERTICAL_OFFSET);
    	if(!isBullet)
    		currentLine = UP_LINE + 1; 
    	else
    		currentLine = UP_LINE;    		
        playerDeltaX = 0.0;
    	isOldMove = true;
    	playerDirection = Direction.Up; 
	}
	
	public void moveDown(){
		
		playerDeltaY = PLAYER_SPEED;
		playerSprite.setWidth(42);
		playerSprite.setHeight(56);
		playerSprite.setOffsetY(VERTICAL_OFFSET+1);
		playerSprite.setOffsetX(1);
		if(!isBullet)
    		currentLine = DOWN_LINE - 1; 
    	else
    		currentLine = DOWN_LINE; 
        playerDeltaX = 0.0;
        isOldMove = true;
        playerDirection = Direction.Down;
	}
	
	public void moveLeft(){
		
    	playerDeltaX = -PLAYER_SPEED;
		playerSprite.setWidth(56);
		playerSprite.setHeight(42);
		playerSprite.setOffsetY(0);
		if(!isBullet)
    		currentLine = LEFT_LINE + 2; 
    	else
    		currentLine = LEFT_LINE; 
    	playerDeltaY = 0.0;
    	isOldMove = true;
    	playerDirection = Direction.Left;
	}
	
	public void moveRight(){
		
		playerDeltaX = PLAYER_SPEED;
    	playerSprite.setWidth(56);
    	playerSprite.setHeight(42);
    	playerSprite.setOffsetY(0);
    	if(!isBullet)
    		currentLine = RIGHT_LINE + 2; 
    	else
    		currentLine = RIGHT_LINE;
        playerDeltaY = 0.0;
        isOldMove = true;
        playerDirection = Direction.Right;
	}
	
	public void shotAnalyze(Enemy enemy,long Time){
		if(Math.abs(enemy.getX() - playerX) < 10 && (playerDirection == Direction.Up 
				|| playerDirection == Direction.Down)){
			shotBot = true;
		}
		else if(Math.abs(enemy.getY() - playerY) < 10 && (playerDirection == Direction.Left 
				|| playerDirection == Direction.Right)){
			shotBot = true;
		}
	}
	
	public void death(){
		life = false;	
					
	}
	
	public void deathAnimation(){
		playerSprite.setWidth(58);
    	playerSprite.setHeight(42);
		playerSprite.setOffsetX(1);
		playerSprite.setOffsetY(0);
		playerSprite.animation(DEATH_LINE);
	}
	
	public void animation(){
		playerSprite.animation(currentLine);
	}
	
	public void reset(){
		life = true; 
		isBullet = true;
		bulletTime = 0;
		playerDirection = Direction.Right;
		
		playerY = 896.0;
		playerX = 640.0;
		
		playerDeltaX = 0.0;
		playerDeltaY = 0.0;
		
	
		playerSprite.setOffsetX(0);

		
	    playerSprite.imageView.setLayoutX(playerX);
	    playerSprite.imageView.setLayoutY(playerY);
		
	}
	


	public void interationWithMap(long currentTime,int mode){
		double height = playerSprite.getHeight();
		double width = playerSprite.getWidth();
		
		
		for (int i = (int) (playerY / 64); i < (playerY + height) / 64; i++)
		for (int j = (int) (playerX / 64); j < (playerX + width) / 64; j++){
			

			if (Map.mapMatrix[i][j] == 9 ){	
				if (playerDeltaX > 0){
					playerDeltaX = 0.0;	
					botTime = currentTime;
					while(tmp == 1){
						tmp = random.nextInt(4);
						tmp++;
					}
			    	moveTime = random.nextInt(3)*1000;
				}
			}
			else if (Map.mapMatrix[i][j] == 2 ){	
				if (playerDeltaX < 0){
					playerDeltaX = 0.0;
					playerX = j*64 + 64;
					playerSprite.imageView.setLayoutX(playerX);
					botTime = currentTime;
					while(tmp == 2){
						tmp = random.nextInt(4);
						tmp++;
					}
			    	moveTime = random.nextInt(3)*1000;
				}
			}
			else if (Map.mapMatrix[i][j] == 8 ){	
				if (playerDeltaY < 0){
					playerDeltaY = 0.0;
					playerY = i*64 + 64;
					playerSprite.imageView.setLayoutY(playerY);
					botTime = currentTime;
					while(tmp == 4){
						tmp = random.nextInt(4);
						tmp++;
					}
			    	moveTime = random.nextInt(3)*1000;
				}
			}
			else if (Map.mapMatrix[i][j] == 3 ){
				
				if (playerDeltaY > 0){
					playerDeltaY = 0.0;		
					botTime = currentTime;
					while(tmp == 3){
						tmp = random.nextInt(4);
						tmp++;
					}
			    	moveTime = random.nextInt(3)*1000;
				}
			}
			else if(Map.mapMatrix[i][j] == 1 || Map.mapMatrix[i][j] == 10 || Map.mapMatrix[i][j] == 11 ||
				Map.mapMatrix[i][j] == 12 || Map.mapMatrix[i][j] == 13 || Map.mapMatrix[i][j] == 14){
				if (playerDeltaX > 0 || playerDeltaX < 0 || playerDeltaY > 0 || playerDeltaY < 0){
					
					Rectangle rect = new Rectangle(64,64,Color.BLACK);
			    	rect.setX(j*64);
			    	rect.setY(i*64);
			    	
			    	
					GameProcess.mapLayer.getChildren().remove(Map.tilesMap.get(i*20+j));
					Map.tilesMap.remove(Map.tilesMap.get(i*20+j));
					Map.tilesMap.add(i*20+j, rect);
					GameProcess.mapLayer.getChildren().addAll(rect);		
					if(Map.mapMatrix[i][j] == 10){
						playerScore+=50;
						if(mode == 1 || mode == 3){
							try {
								writeStream.writeChar('8');
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						
					}
					Map.mapMatrix[i][j] = 0;
					
				}
			}
			
		}

	}
}
