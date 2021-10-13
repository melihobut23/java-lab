import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import javax.swing.*;

/**
 * BalloonGamePanel class  create panel and component on it
 * 
 * @author melihobut
 * @date 07.04.2020
 */

public class BalloonsGamePanel extends JPanel {
   static final int WIDTH = 800;
   static final int HEIGHT = 600;
   static final int MAX_RADIUS = 100;
   static final int INIT_BALLOONS = 25;
   static final int MIN_BALLOONS = 15;
   
   ShapeContainer balloons;
   Timer timer;
   JLabel pointsLabel;
   JLabel timesLabel;
   int points;
   int elapsedTime;
   MouseListener pin;
   
   //constructor
   
   public BalloonsGamePanel() {
      this.pointsLabel = new JLabel();
      this.timesLabel = new JLabel();
      add(this.pointsLabel);
      add(this.timesLabel);
      setBackground(Color.WHITE);
      setPreferredSize(new Dimension(WIDTH, HEIGHT));
      this.pin = new Pin();
      this.timer = new Timer(MAX_RADIUS, new GrowListener());
      initGame();
   }
   
   /* 
    * initGame() initilize the methods and variables
    */
   private void initGame() {
      this.elapsedTime = 0;
      this.points = 0;
      this.pointsLabel.setText("Points: " + this.points);
      this.balloons = new ShapeContainer();
      addRandomBalloons(INIT_BALLOONS, WIDTH, HEIGHT);
      addMouseListener(this.pin);
      this.timer.start();
   }
   
   /* addRandomBalloons() add balloons randomly
    * @param number, width,height
    *
    */
   private void addRandomBalloons(int number, int width, int height) {
      for (int i = 0; i < number; i++) {
         int x = (int)(Math.random() * width);
         int y = (int)(Math.random() * height);
         Balloon b = new Balloon(x, y);

         this.balloons.add((Shape)b);
      } 
   }
   
   /* paintComponent() 
    * @param Graphics g
    * @return draw
    */
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Iterator<Shape> it = this.balloons.iterator();
      while (it.hasNext())
         ((Drawable)it.next()).draw(g);
         
   }
   
   //inner class
   private class Pin extends MouseAdapter {
      //constructor
      private Pin() 
      {
      }
      
      /* mousePressed() method burst the ballons in selected x and y
       * @param MouseEvent e
       */
      public void mousePressed(MouseEvent e) {
         int burstCount = BalloonsGamePanel.this.balloons.selectAllAt(e.getX(), e.getY());
         if (burstCount >= 2) {
            BalloonsGamePanel.this.points += burstCount;
            BalloonsGamePanel.this.pointsLabel.setText("Points: " + BalloonsGamePanel.this.points);
            
         } 
      }
   }
   
   private class GrowListener implements ActionListener {
      private GrowListener() 
      {
      }
      /* actionPerformed() removing shapes and adding ballons as time past 
       * @param ActionEvent e
       */
      public void actionPerformed(ActionEvent e) {
         Iterator<Shape> it = BalloonsGamePanel.this.balloons.iterator();
         while (it.hasNext())
         {
            ((Balloon)it.next()).grow(); 
         }
         BalloonsGamePanel.this.balloons.removeSelectedShapes();
         if (BalloonsGamePanel.this.balloons.size() < 15)
         { 
            BalloonsGamePanel.this.addRandomBalloons(1, BalloonsGamePanel.this.getWidth(), BalloonsGamePanel.this.getHeight()); 
            
         } 
         BalloonsGamePanel.this.repaint();
         BalloonsGamePanel.this.elapsedTime++;
         BalloonsGamePanel.this.timesLabel.setText("Time: " + BalloonsGamePanel.this.elapsedTime);
         if (BalloonsGamePanel.this.elapsedTime >= 250) 
         {
            BalloonsGamePanel.this.timer.stop();
            BalloonsGamePanel.this.removeMouseListener(BalloonsGamePanel.this.pin);
            int again = JOptionPane.showConfirmDialog(BalloonsGamePanel.this, "Play again? ", "Game over", 0);
            if (again == 0)
               BalloonsGamePanel.this.initGame(); 
         } 
      }
   }
}
