import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class DirectDrawDemo extends JPanel {

    private BufferedImage canvas;
    private int xpos,ypos;
    static int width = 1024;
    static int height = 1024;
    static DirectDrawDemo panel = new DirectDrawDemo(width, height);
    
    public DirectDrawDemo(int width, int height) {
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        int[] size = {canvas.getWidth()/8,canvas.getWidth()/8};
        fillCanvas(Color.BLUE);
        drawRect(Color.RED, 0, 0, width/2, height/2);
        Color grey = new Color(200,200,200);
        Color black = new Color(0,0,0);
        Color player = new Color(77, 153, 91);
        Color color;
        int res = 128/2;
        boolean[][] map = CaveGen.Generate(size,3,4,15,0.45);
        while(true){
        try{
            Thread.sleep(10);
        } catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
        for (double y = 0; y < canvas.getHeight()/8; y++) {
            for (double x = 0; x < canvas.getWidth()/8; x++) {
                if(map[(int)y][(int)x]){
                    color = black;
                }else if(x == xpos && y == ypos){
                    color = player;
                }else{
                    color = grey;
                }
                drawRect(color,(int)y*8,(int)x*8,8,8);
            }
        }
        repaint();
        }
        
    }
    //Beginning
    public void keyPressed(KeyEvent k){
        int code = k.getKeyCode();
        switch(code){
            case KeyEvent.VK_UP:
                ypos --;
                break;
            case KeyEvent.VK_DOWN:
                ypos ++;
                break;
            case KeyEvent.VK_LEFT:
                xpos --;
                break;
            case KeyEvent.VK_RIGHT:
                xpos++;
                break;
        }
    }
    //end
    public Dimension getPreferredSize() {
        return new Dimension(canvas.getWidth(), canvas.getHeight());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(canvas, null, null);
    }


    public void fillCanvas(Color c) {
        int color = c.getRGB();
        for (int x = 0; x < canvas.getWidth(); x++) {
            for (int y = 0; y < canvas.getHeight(); y++) {
                canvas.setRGB(x, y, color);
            }
        }
        repaint();
    }


    public void drawLine(Color c, int x1, int y1, int x2, int y2) {
        // Implement line drawing
        repaint();
    }

    public void drawRect(Color c, int x1, int y1, int width, int height) {
        int color = c.getRGB();
        // Implement rectangle drawing
        for (int x = x1; x < x1 + width; x++) {
            for (int y = y1; y < y1 + height; y++) {
                canvas.setRGB(x, y, color);
            }
        }
        repaint();
    }

    public void drawOval(Color c, int x1, int y1, int width, int height) {
        // Implement oval drawing
        repaint();
    }



    public static void main(String[] args) {
        
        JFrame frame = new JFrame("Direct draw demo");

        

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}