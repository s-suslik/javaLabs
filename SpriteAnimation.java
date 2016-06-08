package application;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.animation.TranslateTransition;
/**
 * Класс реализующий анимацию
 * @author Alexander
 * @version 1.0
 *
 */
public class SpriteAnimation 
  {
	
	/** Поле отвечающее за отобраежение в окне*/
    public final ImageView imageView;
    
    /** Поле отвечающее за количетво столбцов в переданной  */
    private final int columns;
    
    /**Текущее значение стобца */
    private int currentColumn = 0;
    
    /**Текущее значение строки */
    private int currentLine = 0;
    
    /**Смещение по X */
    private int offsetX;
    
    /**Смещение по Y */
    private int offsetY;
    
    /**Ширина спрайта */
    private int width;
    
    /**Высота спрайта */
    private int height;
    
    
    /** Создает новый объект с заданными значениями
     * @param imageView - отображение
     * @param columns - стобцы
     * @param offsetX - смещение по X
     * @param offsetY - смещение по Y
     * @param width - ширина
     * @param height - высота
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
    
    /** Функция , которая задаёт  значение поля {@link SpriteAnimation#offsetX}
     */
  public void setOffsetX(int x)
    {
      this.offsetX = x;
    }
  
  
  /** Функция , которая задаёт  значение поля {@link SpriteAnimation#offsetY}
   */
  public void setOffsetY(int y)
    {
      this.offsetY = y;
    }
  
  /** Функция , которая задаёт  значение поля {@link SpriteAnimation#width}
   */
  public void setWidth(int width)
  {
    this.width = width;
  }
  
  
  /** Функция , которая задаёт значение поля {@link SpriteAnimation#height}
   */
  public void setHeight(int height)
  {
    this.height = height;
  }
  

  /** Функция , которая задаёт значение поля {@link SpriteAnimation#imageView}
   */
  public void setImage(double line){
      final int y = currentLine*height+offsetY;
	  imageView.setViewport(new Rectangle2D(0, y, width, height));
  }
  
  
  /** Функция реализующая анимацию 
   * @param line - принимает строку, по которой будет идти анимация
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
  
  
  /** Функция для получения значения поля {@link SpriteAnimation#width}
   * @return Возвращает ширину спрайта
   */
  public int getWidth(){
	  return width;
  }
  
  /** Функция для получения значения поля {@link SpriteAnimation#height}
   * @return Возвращает высоту спрайта
   */
  public int getHeight(){
	  return height;
  }
  
  /** Функция для получения значения поля {@link SpriteAnimation#imageView}
   * @return Возвращает отображение 
   */
  public ImageView getImageView(){
	  return imageView;
  }
}
