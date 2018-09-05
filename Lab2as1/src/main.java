import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

class Framenaja extends JPanel implements ActionListener {

    int x;
    int y;
    BufferedImage image1;
    BufferedImage image2;
    BufferedImage image3;

    Framenaja() throws IOException {
        this.image1 = ImageIO.read(new File("c:/Users/~iJoshoa~/Desktop/p1.png"));
        this.image2 = ImageIO.read(new File("c:/Users/~iJoshoa~/Desktop/p2.png"));
        this.image3 = ImageIO.read(new File("c:/Users/~iJoshoa~/Desktop/p3.png"));
        x = 0;
        y = 0;
        new Timer(10, this).start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        if(x>=250&&x<500){
            g.drawImage(this.image2, x, 200, null);
        }
        else if(x>=500){
            g.drawImage(this.image3, x, 200, null);
        }
        else{
            g.drawImage(this.image1, x, 200, null);
        }
    }

    public void actionPerformed(ActionEvent e) {
        x += 3;
        if (x >= 800) {
            x = -100;
        }
        repaint();
    }
}
class frameAn extends JFrame {
    frameAn() throws IOException{
        this.add(new Framenaja());
        this.setTitle("Lab2as1");
    }
}
public class main {
    public static void main(String[] args) throws IOException {
        frameAn f = new frameAn();
        f.setSize(800, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
