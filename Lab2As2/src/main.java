
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

class Framenaja extends JPanel implements ActionListener {

    int x;
    int y, le_x, le_y, bgx1, bgx2, bgy,ey;
    BufferedImage image1;
    boolean l1;
    boolean l2,e1;

    Framenaja() throws IOException {
        this.image1 = ImageIO.read(new File("c:/Users/~iJoshoa~/Desktop/bg1.jpg"));

        x = 350;
        y = 300;
        le_x = 420;
        le_y = 350;
        bgx1 = 0;
        bgx2 = 800;
        bgy = 0;
        ey=35;
        l1 = true;
        l2 = true;
        new Timer(10, this).start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(this.image1, bgx1, bgy, null);
        g.drawImage(this.image1, bgx2, bgy, null);
        g.setColor(Color.yellow);
        g.fillArc(x, y - 50, 200, 200, 315, 50);//ไฟ
        g.setColor(Color.black);
        g.fillOval(x, y - 40, 45, 45);

        g.setColor(Color.red);
        g.fillRect(x + 90, y + 40, 20, 20);//โคมไฟ
        g.setColor(Color.black);
        g.fillOval(x + 90, y + 20, 20, 20);//แขนไฟ
        g.drawLine(x + 20, y + 30, 450, 330);
        g.drawLine(x + 20, y + 100, le_x, 450);//ขา1
        g.drawLine(x + 20, y + 100, le_y, 450);//ขา2
        g.drawLine(x + 20, y + 30, le_y, 400);//แขน
        
        g.fillOval(le_y-10, y+140, 20, 20); //เท้า 1
        g.fillOval(le_x-10, y+140, 20, 20); //เท้า 2
        
        
        g.drawLine(x + 20, y, x + 20, y + 100); //ตัว
        
        g.setColor(Color.white);
        g.fillOval(x + 30, y - 30, 15, 15);//ตา
        g.drawLine(le_y-40, y+100, le_y + 190, y + 100);// หอก
         g.setColor(Color.pink);
        g.fillArc(le_y+170, y +75, 80, 50, 150, 60);//ปลายหอก
        
        g.setColor(Color.black);
        g.fillOval(x + ey, y - 25, 5, 5);//ลูกตา
        g.fillOval(le_y-10, y+90, 20, 20);//มือ
        g.setColor(Color.orange);
        g.fillRect(x, y - 45, 50, 10);//หมวก
        g.fillOval(x, y - 55, 10, 20);//ประดับ
        g.fillOval(x+10, y - 55, 10, 20);//ประดับ
        g.fillOval(x+20, y - 55, 10, 20);//ประดับ
        g.fillOval(x+30, y - 55, 10, 20);//ประดับ
        g.fillOval(x+40, y - 55, 10, 20);//ประดับ
    }

    public void actionPerformed(ActionEvent e) {
         if (e1 == true) {
            if (ey+x >= 390) {
                e1 = false;
            }
            ey++;
        } else {
            if (ey+x <= 370) {
                e1 = true;
            }
            ey--;
        }
        
        if (l1 == true) {
            if (le_x >= 420) {
                l1 = false;
            }
            le_x++;
        } else {
            if (le_x <= 350) {
                l1 = true;
            }
            le_x--;
        }
        if (l2 == true) {
            if (le_y >= 420) {
                l2 = false;
            }
            le_y++;
        } else {
            if (le_y <= 350) {
                l2 = true;
            }
            le_y--;
        }
        bgx1--;
        bgx2--;
        if (bgx1 <= -800) {
            bgx1 = 800;
        }
        if (bgx2 <= -800) {
            bgx2 = 800;
        }
        repaint();
    }
}

class frameAn extends JFrame {

    frameAn() throws IOException {
        this.add(new Framenaja());
        this.setTitle("Lab2as2");
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
