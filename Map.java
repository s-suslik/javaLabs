package application;

import java.util.ArrayList;
import application.GameProcess;
import javafx.scene.Node;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Map
  {
	public static final int  SIZE = 64;
	public static final int  WIDTH = 20;
	public static final int  HEIGHT = 16;
	public static final int A = 10,B = 11, C = 12, D = 13, E = 14;
	public static int level = 0;
	public static ArrayList<Node> tilesMap = new ArrayList<>();
	public static int [][] mapMatrix = new int[HEIGHT][WIDTH];
	public static int [][][] levelMatrix=
		{
		{
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{5, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6},
			{2, 0, C, A, D, 1, 1, 1, B, 1, B, A, 1, D, 1, 1, 0, 0, 0, 9},
			{2, 0, A, A, A, A, B, 1, 1, 1, 1, 1, A, 1, 1, C, 0, D, 1, 9},
			{2, 0, D, C, E, A, 1, 1, C, 1, 1, A, 1, C, 1, 1, 0, 1, C, 9},
			{2, 0, A, A, B, 1, 1, E, 1, 1, A, 1, 1, A, 1, 1, 0, 1, 1, 9},
			{2, 0, A, A, 1, 1, 1, 1, 1, 1, B ,1, 1, 1, 1, 1, 0, 1, 1, 9},
			{2, 0, A, A, 1, 1, E, D, A, A, A, 1, 1, 1, B, 1, 0, 1, E, 9},
			{2, 0, C, 1, E, 1, A, A, 1, 1, 1, 1, 1, A, A, 1, 0, 1, A, 9},
			{2, 0, 0, 0, 0, 0, 1, A, B, B, 1, A, 1, A, A, 1, 0, 1, A, 9},
			{2, 1, C, 1, 1, 0, 1, 1, 1, 1, 1, B, E, D, 1, 1, 0, B, A, 9},
			{2, C, 1, 1, 1, 0, D, 1, A, A, C, A, 1, 1, 1, 1, 0, B, A, 9},
			{2, 1, B, 1, 1, 0, 1, 1, C, A, A, A, 1, 1, E, 1, 0, D, A, 9},
			{2, A, A, 1, B, 0, 1, B, 1, D, 1, C, 1, B, 1, 1, 0, C, A, 9},
			{2, A, A, B, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, A, 9},
			{4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 7}
	    },
	
		   {
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{5, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6},
			{2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9},
			{2, 0, C, 1, 1, 1, 1, 1, 1, 1, 0, C, 1, 1, 1, 1, 1, 1, 0, 9},
			{2, 0, 1, 1, E, 1, 1, 1, 1, 1, 0, 1, A, A, D, C, C, 1, 0, 9},
			{2, 0, B, C, A, A, A, D, 1, 1, 0, B, 1, 1, B, 1, 1, 1, 0, 9},
			{2, 0, B, 1, A, C, A, 1, 1, 1, 0, 1, A, A, 1, A, A, D, 0, 9},
			{2, 0, 1, D, 1, 1, C, 1, B, 1, 0, 1, B, B, 1, E, E, 1, 0, 9},
			{2, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, B, A, A, 1, A, A, 1, 0, 9},
			{2, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, D, 0, 9},
			{2, 0, A, A, 1, 1, 1, 1, A, A, 0, 1, D, 1, E, 1, 1, 1, 0, 9},
			{2, 0, A, A, A, A, A, A, A, A, 0, E, E, E, 1, C, D, E, 0, 9},
			{2, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, D, A, A, C, B, A, A, 0, 9},
			{2, 0, 1, B, 1, B, C, 1, 1, 1, 0, 1, 1, B, 1, C, 1, 1, 0, 9},
			{2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9},
			{4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 7}
	     	},
	    
		   
		};
	
	
	public boolean isChangeMap(){
		int temp = 0;
		boolean changeMap = false;
		for(int i = 0; i < HEIGHT; i++){
			
			for(int j = 0; j < WIDTH; j++){
				
				if(mapMatrix[i][j] == A) {
					temp++;
				}
					
			}
			}
		if(temp == 0)
			changeMap = true;
		return changeMap;
		
	}
	

	public void createMap()
	  {
		int place1, place2;

		
		for(int i = 0; i < HEIGHT; i++)
		  {
			for(int j = 0; j < WIDTH; j++){
				
				mapMatrix[i][j] = levelMatrix[level][i][j];
			}
			}
		
    	
		for(int i = 0; i < HEIGHT; i++)
		  {
			for(int j = 0; j < WIDTH; j++){
				try
				  {
					place1=j*SIZE;
					place2=i*SIZE;
				  }
				catch(ArithmeticException e)
		    	  {
		    		System.out.println("Error");
		    		return;
		    	  }
				
			    if(mapMatrix[i][j] == 1)
					setMyImage( "data/fon.png",false,false,place1,place2);
			    if(mapMatrix[i][j] == 2)
					setMyImage( "data/fon2.png",false,false,place1,place2);    
			    if(mapMatrix[i][j] == 3)
					setMyImage( "data/fon3.png",false,false,place1,place2);
			    if(mapMatrix[i][j] == 4)
					setMyImage( "data/fon4.png",false,false,place1,place2);
			    if(mapMatrix[i][j] == 5)
					setMyImage( "data/fon4.png",false,true,place1,place2);
			    if(mapMatrix[i][j] == 6)
					setMyImage( "data/fon4.png",true,true,place1,place2);
			    if(mapMatrix[i][j] == 7)
					setMyImage( "data/fon4.png",true,false,place1,place2);
			    if(mapMatrix[i][j] == 8)
					setMyImage( "data/fon3.png",false,true,place1,place2);
			    if(mapMatrix[i][j] == 9)
					setMyImage( "data/fon2.png",true,false,place1,place2);
			    if(mapMatrix[i][j] == 10)
					setMyImage( "data/fon5.png",false,false,place1,place2);
			    if(mapMatrix[i][j] == 11)
					setMyImage( "data/fon6.png",false,false,place1,place2);
			    if(mapMatrix[i][j] == 12)
					setMyImage( "data/fon7.png",false,false,place1,place2);
			    if(mapMatrix[i][j] == 13)
					setMyImage( "data/fon8.png",false,false,place1,place2);
			    if(mapMatrix[i][j] == 14)
					setMyImage( "data/fon9.png",false,false,place1,place2);
			    if(mapMatrix[i][j] == 0)
			    	setMyRectangle(place1, place2);
			   }
	       }
       }
	public void setMyImage(String path, boolean reflectionX, boolean reflectionY,int place1, int place2){
		   
    	ImageView tileView = new ImageView(new Image(getClass().getResourceAsStream(path)));
		if(reflectionX == true)
	    	tileView.setScaleX(-1);
		if(reflectionY == true)
	    	tileView.setScaleY(-1);
		tileView.setX(place1);
		tileView.setY(place2);
    	tilesMap.add(tileView);
       	GameProcess.mapLayer.getChildren().addAll(tileView);
	}
	
	public void setMyRectangle(int place1, int place2){
		Rectangle rect = new Rectangle(SIZE,SIZE,Color.BLACK);
    	rect.setX(place1);
    	rect.setY(place2);
    	
    	tilesMap.add(rect);
    	
    	GameProcess.mapLayer.getChildren().addAll(rect);
	}
	
  }