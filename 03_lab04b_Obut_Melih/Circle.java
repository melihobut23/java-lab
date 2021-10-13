
/**
 * Circle Class
 * @author melihobut
 * @date 10.03.2020
 */
public class Circle extends Shape implements Selectable
{
   private int radius;
   boolean selected;
   //constructor
   public Circle() {
      this.radius = 0;
   }
   //constructor
   public Circle(int pRadius) {
      this.radius = pRadius;
   }
   
   //methods
   
   /* getArea() method calculates area of the circle with given radius
    * @return area 
    */
   @Override
   public double getArea()
   {
      return Math.PI * Math.pow(radius, 2);
   }
   
   /* toString() method shows radius of the circle
    * @return String 
    */
   public String toString()
   {
      return "Circle: " + (this.selected ? "* " : "  ") + super
         .toString() + " " + this.radius;
   }
   
   /* setLocation() method
    *@
    * @return
    */
   @Override
   public void setLocation( int x, int y)
   {
      this.x = x;
      this.y = y;
   }
   
   /* isSelected() method controls if shape is selected or not
    * @return boolean selected
    */
   public boolean isSelected()
   {
      return selected;
   }
   
   /* setSelected() method sets shape selected
    * @return selected
    */
   
   public void setSelected(boolean selected) {
      this.selected = selected;
   }
   
   
   /* contains() method return the first shape that includes the point
    * @param x, y
    * @return this
    */
   public Shape contains( int x, int y)
   {
      int x1 = (x - getX()) * (x - getX());
      int y1 = (y - getY()) * (y - getY());
      
      if (x1 + y1 <= this.radius * this.radius)
      {
         return this; 
      }
      return null;
   }
   
   /* setRadius() method set the radius
    * @param radius
    * @return
    */
   public void setRadius(int radius)
   {
      this.radius = radius;
   }
   /* getRadius() method get the radius
    * @param radius
    * @return radius
    */
   public int getRadius()
   {
      return this.radius;
   }
   
}