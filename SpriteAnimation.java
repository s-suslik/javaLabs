package application;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.animation.TranslateTransition;
/**
 * ����� ����������� ��������
 * @author Alexander
 * @version 1.0
 *
 */
public class SpriteAnimation 
  {
	
	/** ���� ���������� �� ������������ � ����*/
    public final ImageView imageView;
    
    /** ���� ���������� �� ��������� �������� � ����������  */
    private final int columns;
    
    /**������� �������� ������ */
    private int currentColumn = 0;
    
    /**������� �������� ������ */
    private int currentLine = 0;
    
    /**�������� �� X */
    private int offsetX;
    
    /**�������� �� Y */
    private int offsetY;
    
    /**������ ������� */
    private int width;
    
    /**������ ������� */
    private int height;
    
    
    /** ������� ����� ������ � ��������� ����������
     * @param imageView - �����������
     * @param columns - ������
     * @param offsetX - �������� �� X
     * @param offsetY - �������� �� Y
     * @param width - ������
     * @param height - ������
     * @see SpriteAnimation#SpriteAnimation()
    */
    public SpriteAnimation(
            ImageView imageView,
            int columns,
            int offsetX, int offsetY,
            int width, int height
    )
      {
        this.imageView = imageView;
        this.columns = columns;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;

        this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
      }
    
    /** ������� , ������� �����  �������� ���� {@link SpriteAnimation#offsetX}
     */
  public void setOffsetX(int x)
    {
      this.offsetX = x;
    }
  
  
  /** ������� , ������� �����  �������� ���� {@link SpriteAnimation#offsetY}
   */
  public void setOffsetY(int y)
    {
      this.offsetY = y;
    }
  
  /** ������� , ������� �����  �������� ���� {@link SpriteAnimation#width}
   */
  public void setWidth(int width)
  {
    this.width = width;
  }
  
  
  /** ������� , ������� ����� �������� ���� {@link SpriteAnimation#height}
   */
  public void setHeight(int height)
  {
    this.height = height;
  }
  

  /** ������� , ������� ����� �������� ���� {@link SpriteAnimation#imageView}
   */
  public void setImage(double line){
      final int y = currentLine*height+offsetY;
	  imageView.setViewport(new Rectangle2D(0, y, width, height));
  }
  
  
  /** ������� ����������� �������� 
   * @param line - ��������� ������, �� ������� ����� ���� ��������
   */
  public void animation(double line)
    {
	  this.currentLine = (int)line;
      final int x = currentColumn*width+offsetX;
      final int y = currentLine*height+offsetY;
      currentColumn++;
      imageView.setViewport(new Rectangle2D(x, y, width, height));
      if(currentColumn == columns)
    	  currentColumn = 0;
    	  
    }
  
  
  /** ������� ��� ��������� �������� ���� {@link SpriteAnimation#width}
   * @return ���������� ������ �������
   */
  public int getWidth(){
	  return width;
  }
  
  /** ������� ��� ��������� �������� ���� {@link SpriteAnimation#height}
   * @return ���������� ������ �������
   */
  public int getHeight(){
	  return height;
  }
  
  /** ������� ��� ��������� �������� ���� {@link SpriteAnimation#imageView}
   * @return ���������� ����������� 
   */
  public ImageView getImageView(){
	  return imageView;
  }
}
