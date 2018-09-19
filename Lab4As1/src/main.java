
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

class panelAnimation extends JPanel implements KeyListener {

    URL sound;
    URL sound2;
    Clip clip;
    Clip clip2;
    AudioInputStream audioinput;
    AudioInputStream audioinput2;
    int bgx1, bgx2, bgx3, way, bgy;
    int hu, sp, walk;
    BufferedImage image1;
    BufferedImage image2;
    BufferedImage l1;
    BufferedImage l2;
    BufferedImage l3;
    BufferedImage r1;
    BufferedImage r2;
    BufferedImage r3;

    panelAnimation() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        sound = new URL("file:///D:/study/Gamejava/Lab4As1/stepstone.wav");
        sound2 = new URL("file:///D:/study/Gamejava/Lab4As1/movement.wav");
        clip = AudioSystem.getClip();
        audioinput = AudioSystem.getAudioInputStream(sound);
        clip2 = AudioSystem.getClip();
        audioinput2 = AudioSystem.getAudioInputStream(sound2);
        clip.open(audioinput);
        clip2.open(audioinput2);
        this.image1 = ImageIO.read(new File("D:/study/Gamejava/Lab4As1/bg1.jpg"));
        this.image2 = ImageIO.read(new File("D:/study/Gamejava/Lab4As1/rock.png"));
        this.l1 = ImageIO.read(new File("D:/study/Gamejava/Lab4As1/l1.png"));
        this.l2 = ImageIO.read(new File("D:/study/Gamejava/Lab4As1/l2.png"));
        this.l3 = ImageIO.read(new File("D:/study/Gamejava/Lab4As1/l3.png"));
        this.r1 = ImageIO.read(new File("D:/study/Gamejava/Lab4As1/r1.png"));
        this.r2 = ImageIO.read(new File("D:/study/Gamejava/Lab4As1/r2.png"));
        this.r3 = ImageIO.read(new File("D:/study/Gamejava/Lab4As1/r3.png"));

        bgx1 = 0;
        bgx2 = 800;
        bgx3 = 800;
        way = 0;
        sp = 5;
        bgy = 0;
        hu = 0;
        walk = 0;
        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(this.image1, bgx1, bgy, null);
        g.drawImage(this.image1, bgx2, bgy, null);
        g.drawImage(this.image2, bgx3 - way, bgy + 20, null);
        if (hu == 1) {

            if ((walk / 3) % 3 == 0) {
                g.drawImage(this.l1, 350, 300, null);
            } else if ((walk / 3) % 3 == 1) {
                g.drawImage(this.l2, 350, 300, null);
            } else if ((walk / 3) % 3 == 2) {
                g.drawImage(this.l3, 350, 300, null);
            }
        } else if (hu == 2) {
            if ((walk / 3) % 3 == 0) {
                g.drawImage(this.r1, 350, 300, null);
            } else if ((walk / 3) % 3 == 1) {
                g.drawImage(this.r2, 350, 300, null);
            } else if ((walk / 3) % 3 == 2) {
                g.drawImage(this.r3, 350, 300, null);
            }
        } else {
            g.drawImage(this.r1, 350, 300, null);
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int key = ke.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
            hu = 1;
            walk++;
            bgx1 += sp;
            way -= sp;
            bgx2 += sp;
            if (bgx1 >= 800) {
                bgx1 = -800;
            }
            if (bgx2 >= 800) {
                bgx2 = -800;
            }
        }
        if (key == KeyEvent.VK_RIGHT) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
            hu = 2;
            walk++;

            if (bgx3 - way == 400) {

                clip2.start();
            } else {
                clip2.setMicrosecondPosition(0);
                clip2.stop();
                way += sp;
                bgx1 -= sp;
                bgx2 -= sp;
                if (bgx1 <= -800) {
                    bgx1 = 800;
                }
                if (bgx2 <= -800) {
                    bgx2 = 800;
                }
            }

        }

    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int key = ke.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            walk = 0;
            clip.stop();
        }
        if (key == KeyEvent.VK_RIGHT) {
            walk = 0;
            clip.stop();
        }
    }
}

class frameAnimation extends JFrame {

    frameAnimation() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        this.add(new panelAnimation());
        this.setTitle("Wunchissanuphong");
    }
}

public class main {

    public static void main(String[] args) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        frameAnimation f = new frameAnimation();
        f.setSize(800, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
