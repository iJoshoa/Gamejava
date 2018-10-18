
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.util.Random;

class panelAnimation extends JPanel implements KeyListener, ActionListener {

    int hu, bgx1, bgx2, bgy, xbox, walk, x, up, rdom, speed, way, point, box;
    boolean jump;
    boolean jumpl;
    boolean jumpr;
    boolean down;
    Random rd = new Random();
    URL sound;
    URL sound2;
    Clip clip;
    Clip clip2;
    AudioInputStream audioinput;
    AudioInputStream audioinput2;

    BufferedImage image1;
    BufferedImage image2;
    BufferedImage l1;
    BufferedImage l2;
    BufferedImage l3;
    BufferedImage r1;
    BufferedImage r2;
    BufferedImage r3;
    BufferedImage n0;
    BufferedImage[] n = new BufferedImage[10];
    String[] num = new String[10];
    String po;

    panelAnimation() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        sound = new URL("file:///C:/Users/GELAB/Desktop/Lab6As1/stepstone.wav");
        sound2 = new URL("file:///C:/Users/GELAB/Desktop/Lab6As1/movement.wav");
        clip = AudioSystem.getClip();
        audioinput = AudioSystem.getAudioInputStream(sound);
        clip2 = AudioSystem.getClip();
        audioinput2 = AudioSystem.getAudioInputStream(sound2);
        clip.open(audioinput);
        clip2.open(audioinput2);
        this.image1 = ImageIO.read(new File("C:\\Users\\GELAB\\Desktop\\Lab6As1\\bg1.jpg"));
        this.image2 = ImageIO.read(new File("C:\\Users\\GELAB\\Desktop\\Lab6As1\\box.png"));
        this.l1 = ImageIO.read(new File("C:\\Users\\GELAB\\Desktop\\Lab6As1\\l1.png"));
        this.l2 = ImageIO.read(new File("C:\\Users\\GELAB\\Desktop\\Lab6As1\\l2.png"));
        this.l3 = ImageIO.read(new File("C:\\Users\\GELAB\\Desktop\\Lab6As1\\l3.png"));
        this.r1 = ImageIO.read(new File("C:\\Users\\GELAB\\Desktop\\Lab6As1\\r1.png"));
        this.r2 = ImageIO.read(new File("C:\\Users\\GELAB\\Desktop\\Lab6As1\\r2.png"));
        this.r3 = ImageIO.read(new File("C:\\Users\\GELAB\\Desktop\\Lab6As1\\r3.png"));
        for (int i = 0; i < 10; i++) {
            num[i] = "C:\\Users\\GELAB\\Desktop\\Lab6As1\\" + i + ".png";
            this.n[i] = ImageIO.read(new File(num[i]));
        }

        new Timer(10, this).start();
        hu = 0;
        bgx1 = 0;
        bgx2 = 1600;
        bgy = 0;
        xbox = 0;
        walk = 0;
        x = 350;
        up = 320;
        speed = 5;
        way = x + 10;
        jump = false;
        jumpl = false;
        jumpr = false;
        down = false;
        rdom = rd.nextInt(800 - 500) + 500;

        box = rdom;
        setFocusable(true);
        addKeyListener(this);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        po = String.valueOf(point);
        System.out.println("dox " + rdom + " way " + way + " bgx1 " + bgx1 + " up " + up + " jump " + jump + " jumpl " + jumpl + " jumpr " + jumpr + " down " + down + " point " + point + " bgy " + bgy);

        g.drawImage(this.image1, bgx1, bgy, null);
        g.drawImage(this.image1, bgx2, bgy, null);
        g.drawImage(this.image1, bgx1 + 3200, bgy, null);
        g.drawImage(this.image1, bgx1 + 4800, bgy, null);
        g.drawImage(this.image1, bgx1 + 6400, bgy, null);

        if (point <= 9) {
            g.drawImage(this.n[point], 700, 30, 25, 50, null);
        } else if (point >= 10 && point <= 99) {
            char ch1 = po.charAt(0);
            char ch2 = po.charAt(1);
            int sum1 = ch1 - '0';
            int sum2 = ch2 - '0';
            g.drawImage(this.n[sum2], 700, 30, 25, 50, null);
            g.drawImage(this.n[sum1], 700 - 30, 30, 25, 50, null);
        } else if (point >= 100 && point <= 1000) {
            char ch1 = po.charAt(0);
            char ch2 = po.charAt(1);
            char ch3 = po.charAt(2);
            int sum1 = ch1 - '0';
            int sum2 = ch2 - '0';
            int sum3 = ch3 - '0';
            g.drawImage(this.n[sum3], 700, 30, 25, 50, null);
            g.drawImage(this.n[sum2], 700 - 30, 30, 25, 50, null);
            g.drawImage(this.n[sum1], 700 - 60, 30, 25, 50, null);
        } else if (point >= 1000 && point <= 10000) {
            char ch1 = po.charAt(0);
            char ch2 = po.charAt(1);
            char ch3 = po.charAt(2);
            char ch4 = po.charAt(3);
            int sum1 = ch1 - '0';
            int sum2 = ch2 - '0';
            int sum3 = ch3 - '0';
            int sum4 = ch4 - '0';
            g.drawImage(this.n[sum4], 700, 30, 25, 50, null);
            g.drawImage(this.n[sum3], 700 - 30, 30, 25, 50, null);
            g.drawImage(this.n[sum2], 700 - 60, 30, 25, 50, null);
            g.drawImage(this.n[sum1], 700 - 90, 30, 25, 50, null);

        }
        g.drawImage(this.image2, rdom, bgy + 380, 100, 100, null);
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
        point++;

        int key = ke.getKeyCode();
        if (down == true) {

        } else {
            walk++;
            if (key == KeyEvent.VK_LEFT) {
                hu = 1;
                jumpl = true;
                jumpr = false;

                if (way <= 0) {
                    jumpl = false;
                } else if (way <= 360) {
                    x -= speed;
                    way -= speed;

                } else if (way <= box + 75) {
                    
                } else {
                    if (bgx1 == 0) {

                    } else {
                        bgx1 += speed;
                        bgx2 += speed;
                        rdom += speed;
                    }
                    way -= speed;
                }
                if (way <= box - 75) {
                    down = true;
                }

            }
            if (key == KeyEvent.VK_RIGHT) {
                hu = 2;
                jumpr = true;
                jumpl = false;
                if (way <= 360) {
                    x += speed;
                    way += speed;
                } else {
                    if (way >= box - 75 && up <= 250) {
                        bgx1 -= speed;
                        bgx2 -= speed;
                        way += speed;
                        rdom -= speed;
                    } else if (way >= box - 75) {

                    } else {
                        bgx1 -= speed;
                        bgx2 -= speed;
                        way += speed;
                        rdom -= speed;
                    }
                }
                if (up <= 250 && way >= box + 100) {
                    down = true;
                }
                if (up == 320 && way >= box + 100) {
                    bgx1 -= speed;
                    bgx2 -= speed;
                    way += speed;
                    rdom -= speed;
                }

            }
        }
    }

    @Override
    public void keyTyped(KeyEvent ke
    ) {

    }

    @Override
    public void keyReleased(KeyEvent ke
    ) {
        int key = ke.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            walk = 0;
        }
        if (key == KeyEvent.VK_RIGHT) {
            walk = 0;
        }
        if (key == KeyEvent.VK_UP) {
            if (way <= 0) {
                jump = false;
            } else {
                jump = true;
                walk = 0;
            }
            if (up <= 250) {
                jump = false;
            }

        }

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (jump == true) {
            if (jumpr == true) {
                up -= speed;

                point++;
                if (way <= 350) {
                    x += speed;
                    way += speed;
                } else if (way >= box - 80 && bgy >= 300) {
                    down = true;
                    jump = false;

                } else {
                    bgx1 -= speed;
                    bgx2 -= speed;
                    rdom -= speed;
                    way += speed;
                }
                if (up < 180) {
                    down = true;
                    jump = false;
                    way += speed;
                }
            } else if (jumpl == true) {
                up -= speed;
                way -= speed;
                point++;
                if (way <= 0) {
                    down = true;
                    jump = false;
                }
                if (way <= 350) {
                    x -= speed;
                } else {
                    rdom += speed;
                    bgx1 += speed;
                    bgx2 += speed;
                }
                if (up < 180) {
                    down = true;
                    jump = false;
                }
            } else {

            }

        }
        if (down == true) {

            jump = false;
            if (up >= 320) {
                down = false;
            } else {
                up += speed;
            }
            if (up == 220 && way >= rdom + 75) {
                down = false;
                up += speed;
            } else {

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
