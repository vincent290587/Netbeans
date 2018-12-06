/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ls027simulator;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 *
 * @author Vincent
 */
public class LS027simulator extends Canvas implements Runnable {

    private static final int WIDTH = 240;
    private static final int HEIGHT = 400;

    private static final int HW_WIDTH = HEIGHT;
    private static final int HW_HEIGHT = WIDTH;

    private static boolean take_s_shot;

    private static int[] ls027_canvas;

    public static int[] rgb_neo;
    
    static int nb_photo;
    
    public InputStream in_buffer;
    public OutputStream out_buffer;

    public LS027simulator() {
        ls027_canvas = new int[WIDTH * HEIGHT / 8];
        rgb_neo = new int[3];
        take_s_shot = false;
        nb_photo = 0;
    }

    public void takeScreenShot() {
        take_s_shot = true;
    }

    void read_pipe() {

        try {
            // Read response from buffer
            int nb_read = 0;

            if (in_buffer.available() > 0) {

                System.out.println(".");
                //in_buffer.read(ls027_canvas);
                
                while (nb_read < WIDTH * HEIGHT / 8) {
                    int byte_read = in_buffer.read();
                    if (byte_read >= 0) {
                        ls027_canvas[nb_read++] = byte_read;
                    }
                }
                
                int flag = in_buffer.read();
                nb_read += 1;

                // neopixel color
                rgb_neo[0] = in_buffer.read();
                rgb_neo[1] = in_buffer.read();
                rgb_neo[2] = in_buffer.read();
                
                nb_read += 3;
                
            }
            
            // force sync
            while (in_buffer.available() > 0) {
                nb_read++;
                in_buffer.read();
            }

            if (nb_read > 0) {
                System.out.println("Pipe updated, read bytes: " + nb_read);
                this.repaint();
            }

        } catch (IOException e) {
            // do something
            System.out.print(".");
        }
    }

    @Override
    public void run() {

        while (true) {
            try {
                this.read_pipe();
                sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(LS027simulator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *
     * @param n
     * @param k Kth bit, from 1 to 8
     * @return
     */
    private static boolean isKthBitSet(int n,
            int k) {

        if ((n & (1 << (k - 1))) >= 1) {
            return true;
        }

        return false;
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);

        BufferedImage img = new BufferedImage(WIDTH + 40, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D    graphics = img.createGraphics();

        graphics.setPaint ( Color.BLACK );
        graphics.fillRect ( 0, 0, img.getWidth(), img.getHeight() );
        graphics.setPaint ( Color.LIGHT_GRAY );
        graphics.drawLine(WIDTH, 0, WIDTH, HEIGHT);

        for (int i = 0; i < HW_HEIGHT; i++) {
            for (int j = 0; j < HW_WIDTH / 8; j++) {

                int pixel_grp = ls027_canvas[i * HW_WIDTH / 8 + j];

                for (int k = 0; k < 8; k++) {

                    if (!isKthBitSet(pixel_grp, k + 1)) {
                        img.setRGB(HW_HEIGHT - i - 1, j * 8 + k, Color.WHITE.getRGB());
                    } else {
                        img.setRGB(HW_HEIGHT - i - 1, j * 8 + k, Color.BLACK.getRGB());
                    }
                }

            }
        }
        
        int pixel_rgb = 0;
        pixel_rgb = ((rgb_neo[0]<<3) & 0xFF);
        pixel_rgb = (pixel_rgb << 8) + ((rgb_neo[1]<<3) & 0xFF);
        pixel_rgb = (pixel_rgb << 8) + ((rgb_neo[2]<<3) & 0xFF);
        //pixel_rgb |= Color.LIGHT_GRAY.getRGB();

         if (pixel_rgb != 0)
             System.out.println("WS2812 RGB: " + rgb_neo[0]);
        
        for (int i = -20; i < 20; i++) {
            for (int j = -20; j < 20; j++) {

                if (pixel_rgb != 0 &&
                        (i*i + j*j <= 15 * 15)) {
                    img.setRGB(WIDTH + 20 + i, 20 + j, pixel_rgb);
                }

            }
        }

        if (take_s_shot) {
            take_s_shot = false;
            
            try {
//                img = ImageIO.read(new File("image.png"));
                ImageIO.write(img, "png", new File("image" + nb_photo + ".png"));
                nb_photo += 1;
                System.out.println("Screenshot taken");
            } catch (IOException e) {
                System.out.println("Some exception occured " + e);
            }
        }
        
        // draw
        g.drawImage(img, 0, 0, this);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        LS027simulator sim = new LS027simulator();
        sim.setFocusable(false);

        JFrame frame = new JFrame();

        frame.setVisible(false);
        frame.setSize(WIDTH + 60, HEIGHT + 45);
        frame.add(sim);

        frame.setVisible(true);
        KeyListener klisten = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case 100:
                    case 101:
                    case 102: {
                        System.out.println("Sent key: " + e.getKeyCode());
                        System.out.flush();
                        try {
                            sim.out_buffer.write(e.getKeyCode());
                        } catch (IOException ex) {
                            Logger.getLogger(LS027simulator.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                    case 105:
                        sim.takeScreenShot();
                        break;
                    default:
                        System.out.println("Key rel.: " + e.getKeyCode());
                        break;
                }
            }
        };
        WindowListener wlisten = new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("Hello LS027");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Bye LS027");
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        };
        frame.setVisible(true);
        frame.addWindowListener(wlisten);
        frame.addKeyListener(klisten);

        try {
            String hostName = "localhost";
            int portNumber = 8080;

            Socket echoSocket = new Socket(hostName, portNumber);
            sim.in_buffer = echoSocket.getInputStream();
            sim.out_buffer = echoSocket.getOutputStream();

            Thread t1 = new Thread(sim, "t1");
            t1.start();

        } catch (IOException ex) {
            Logger.getLogger(LS027simulator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
