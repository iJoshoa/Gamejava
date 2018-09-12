
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

class Panelnaja extends JPanel implements ActionListener {

    JButton l;
    JButton r;
    JButton s;
    int x, y;
    int sp;
    int hu;
    int bgx1, bgx2, bgx3, bgy;
    URL sound;
    URL sound2;
    Clip clip;
    Clip clip2;
    AudioInputStream audioinput;
    AudioInputStream audioinput2;
    BufferedImage image1;
    BufferedImage l1;
    BufferedImage l2;
    BufferedImage l3;
    BufferedImage r1;
    BufferedImage r2;
    BufferedImage r3;
    boolean walk;

    Panelnaja() throws MalformedURLException, LineUnavailableException, UnsupportedAudioFileException, IOException {
        this.image1 = ImageIO.read(new File("D:/study/Gamejava/Lab3as1/bg1.jpg"));
        this.l1 = ImageIO.read(new File("D:/study/Gamejava/Lab3as1/l1.png"));
        this.l2 = ImageIO.read(new File("D:/study/Gamejava/Lab3as1/l2.png"));
        this.l3 = ImageIO.read(new File("D:/study/Gamejava/Lab3as1/l3.png"));
        this.r1 = ImageIO.read(new File("D:/study/Gamejava/Lab3as1/r1.png"));
        this.r2 = ImageIO.read(new File("D:/study/Gamejava/Lab3as1/r2.png"));
        this.r3 = ImageIO.read(new File("D:/study/Gamejava/Lab3as1/r3.png"));
        sound = new URL("file:///D:/study/Gamejava/Lab3as1/sound.wav");
        sound2 = new URL("file:///D:/study/Gamejava/Lab3as1/click.wav");
        clip = AudioSystem.getClip();
        clip2 = AudioSystem.getClip();
        audioinput = AudioSystem.getAudioInputStream(sound);
        audioinput2 = AudioSystem.getAudioInputStream(sound2);
        clip.open(audioinput);
        clip2.open(audioinput2);

        l = new JButton("<==");
        r = new JButton("==>");
        s = new JButton("Sound");
        this.setLayout(new FlowLayout());
        l.addActionListener(this);
        s.addActionListener(this);
        r.addActionListener(this);
        
        x = 100;
        y = 100;
        sp = 0;
        bgx1 = 0;
        bgx2 = 800;
        bgx3 = -800;
        bgy = 0;
        walk = true;
        hu=0;
        new Timer(10, this).start();
       
    }

    @Override
    public void paint(Graphics g) {
        
        super.paint(g);
        g.drawImage(this.image1, bgx1, bgy, null);
        g.drawImage(this.image1, bgx2, bgy, null);
        g.drawImage(this.image1, bgx3, bgy, null);
        if(walk==true){
            if((hu/10)%3==0){
                g.drawImage(this.r1, 350, 300, null);
            }
            else if((hu/10)%3==1){
                g.drawImage(this.r2, 350, 300, null);
            }
            else if((hu/10)%3==2){
                g.drawImage(this.r3, 350, 300, null);
            } 
        }
        else{
            if((hu/10)%3==0){
                g.drawImage(this.l1, 350, 300, null);
            }
            else if((hu/10)%3==1){
                g.drawImage(this.l2, 350, 300, null);
            }
            else if((hu/10)%3==2){
                g.drawImage(this.l3, 350, 300, null);
            }
        }
        this.add(l);
        this.add(r);
        this.add(s);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == s) {
            sp++;
            try {
                if (sp % 2 == 0) {
                    soundoff();
                } else {
                    soundon();
                }
            } catch (LineUnavailableException | IOException ex) {
                Logger.getLogger(Panelnaja.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == l) {
            walk = false;
            hu=0;
            clip2.setMicrosecondPosition(0);
            clip2.start();
        }
        if (e.getSource() == r) {
            walk = true;
            hu=0;
            clip2.setMicrosecondPosition(0);
            clip2.start();
        }

        if (walk == true) {
            bgx1--;
            bgx2--;
            hu++;
   
            if (bgx1 <= -800) {
                bgx1 = 800;
            }
            if (bgx2 <= -800) {
                bgx2 = 800;
            }
        } else {
            bgx1++;
            bgx2++;
             hu++;
            if (bgx1 >= 800) {
                bgx1 = -800;
            }
            if (bgx2 >= 800) {
                bgx2 = -800;
            }
        }

        repaint();
         
    }

    public void soundon() throws LineUnavailableException, IOException {
        s.setText("Sound On");
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void soundoff() {
        s.setText("Sound Off");
        clip.stop();
    }

}

class frameAn extends JFrame {

    frameAn() throws IOException, MalformedURLException, LineUnavailableException, UnsupportedAudioFileException {
        this.add(new Panelnaja());
        this.setTitle("Lab3as1");

    }
}

public class main {

    public static void main(String[] args) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        frameAn f = new frameAn();
        f.setSize(800, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

    }
}
