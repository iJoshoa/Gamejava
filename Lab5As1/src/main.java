
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;

class panelAnimation extends JPanel implements KeyListener, ActionListener {

    URL sound;
    URL sound2;
    Clip clip;
    Clip clip2;
    AudioInputStream audioinput;
    AudioInputStream audioinput2;
    int bgx1, bgx2, bgx3, way, bgy;
    int hu, sp, walk, x, up;
    BufferedImage image1;
    BufferedImage image2;
    BufferedImage l1;
    BufferedImage l2;
    BufferedImage l3;
    BufferedImage r1;
    BufferedImage r2;
    BufferedImage r3;
    Boolean jump;
    Boolean jumpl;
    Boolean jumpr;
    Boolean down;
    Boolean downn;
    Boolean l;
    Boolean r;
    Boolean u;

    panelAnimation() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        sound = new URL("file:///D:/study/Gamejava/Lab4As1/stepstone.wav");
        sound2 = new URL("file:///D:/study/Gamejava/Lab4As1/movement.wav");
        clip = AudioSystem.getClip();
        audioinput = AudioSystem.getAudioInputStream(sound);
        clip2 = AudioSystem.getClip();
        audioinput2 = AudioSystem.getAudioInputStream(sound2);
        clip.open(audioinput);
        clip2.open(audioinput2);
        this.image1 = ImageIO.read(new File("D:/study/Gamejava/Lab5As1/bg1.jpg"));
        this.image2 = ImageIO.read(new File("D:/study/Gamejava/Lab5As1/box.png"));
        this.l1 = ImageIO.read(new File("D:/study/Gamejava/Lab5As1/l1.png"));
        this.l2 = ImageIO.read(new File("D:/study/Gamejava/Lab5As1/l2.png"));
        this.l3 = ImageIO.read(new File("D:/study/Gamejava/Lab5As1/l3.png"));
        this.r1 = ImageIO.read(new File("D:/study/Gamejava/Lab5As1/r1.png"));
        this.r2 = ImageIO.read(new File("D:/study/Gamejava/Lab5As1/r2.png"));
        this.r3 = ImageIO.read(new File("D:/study/Gamejava/Lab5As1/r3.png"));
        new Timer(10, this).start();
        bgx1 = 0;
        bgx2 = 1600;
        bgx3 = 800;
        way = 0;
        sp = 5;
        bgy = 0;
        hu = 0;
        walk = 0;
        x = 350;
        up = 300;
        jump = false;
        jumpl = false;
        jumpr = false;
        down = false;
        downn = false;
        l = false;
        u = false;
        setFocusable(true);
        addKeyListener(this);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(this.image1, bgx1, bgy, null);
        g.drawImage(this.image1, bgx2, bgy, null);

        g.drawImage(this.image2, bgx3 - way, bgy + 380, 100, 100, null);

        if (hu == 1) {

            if ((walk / 3) % 3 == 0) {
                g.drawImage(this.l1, x, up, null);
            } else if ((walk / 3) % 3 == 1) {
                g.drawImage(this.l2, x, up, null);
            } else if ((walk / 3) % 3 == 2) {
                g.drawImage(this.l3, x, up, null);
            }
        } else if (hu == 2) {
            if ((walk / 3) % 3 == 0) {
                g.drawImage(this.r1, x, up, null);
            } else if ((walk / 3) % 3 == 1) {
                g.drawImage(this.r2, x, up, null);
            } else if ((walk / 3) % 3 == 2) {
                g.drawImage(this.r3, x, up, null);
            }
        } else {
            g.drawImage(this.r1, x, up, null);
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int key = ke.getKeyCode();

        if (down == true) {

        } else {

            if (key == KeyEvent.VK_LEFT) {
                l = true;
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
                hu = 1;
                walk++;

                if (way + x + 80 == 960) {
                    System.out.println(way + x + 80);

                    clip2.start();
                } else {
                    down=true;
                    System.out.println(way + x + 80);
                    clip2.setMicrosecondPosition(0);
                    clip2.stop();
                    way -= sp;
                    bgx1 += sp;
                    bgx2 += sp;
                    if (bgx1 >= 1600) {
                        bgx1 = -1600;
                    }
                    if (bgx2 >= 1600) {
                        bgx2 = -1600;
                    }
                }
            }
            if (key == KeyEvent.VK_RIGHT) {
                r = true;
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
                hu = 2;
                walk++;

                if (way + x + 80 == 800) {

                    clip2.start();
                } else {
                    down=true;
                    System.out.println(way + x + 80);
                    clip2.setMicrosecondPosition(0);
                    clip2.stop();
                    
                    way += sp;
                    bgx1 -= sp;
                    bgx2 -= sp;
                    if (bgx1 <= -1600) {
                        bgx1 = 1600;
                    }
                    if (bgx2 <= -1600) {
                        bgx2 = 1600;
                    }
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
        if (down == true) {

        } else {
            if (key == KeyEvent.VK_UP) {
                u = true;
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (hu == 2 && u == true) {
            jumpr = true;
        }
        if (hu == 1 && u == true) {
            jumpl = true;
        }

        if (jumpr == true) {
            //System.out.println(way + x + 80);
            if (up <= 150) {
                jumpr = false;
                u = false;
                down = true;
            }
            if (way + x + 80 >= bgx3 && bgy >= 300) {
                jumpr = false;
                u = false;
                down = true;
            } else {
                up -= 5;

                bgx1 -= 5;
                bgx2 -= 5;
                way += 5;
            }
        }
        if (jumpl == true) {
            if (up <= 150) {
                jumpl = false;
                u = false;
                down = true;
            } else {
                up -= 5;
                System.out.println(up);
                bgx1 += 5;
                bgx2 += 5;
                way -= 5;
            }
        }
       
        if (down == true) {
            if (up >= 300) {

                down = false;
                l = false;
                r = false;

            } else {
                if (up == 220 && (way + x + 80 >= 800&&way + x + 80 <= 960)) {
                    down = false;
                    l = false;
                    r = false;
                } else if (way + x + 80 == 800||way + x + 80 == 960) {
                    up += 5;
                    System.out.println("ooooo");
                } else {
                    System.out.println("down " + up);
                    up += 5;
                }

            }
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
