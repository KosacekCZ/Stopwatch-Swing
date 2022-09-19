import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI implements IMGDraw {

    boolean run = false;
    int hrs = 0;
    int min = 0;
    int sec = 0;
    int set = 0;

    BufferedImage img;
    {
        try {
            img = ImageIO.read(new File("images/karbon.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    JFrame frame = new JFrame();
    JLabel l1 = new JLabel(new ImageIcon(img));
    JLabel time = new JLabel();
    JButton start = new JButton("Fungus");

    void init() {
        frame.setSize(300, 300);
        frame.setLayout(null);
        frame.setResizable(false);
    }

    void renderComponents() {
        l1.setBounds(0, 0, 300, 300);
        time.setBounds(50, 10, 200, 50);
        start.setBounds(50, 100, 200, 50);
        start.addActionListener(e -> {run = !run;
            try {
                runtime();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });


        frame.add(time);
        frame.add(start);
        frame.add(l1);

    }

    void repaint() {
        frame.setVisible(true);
        frame.repaint();
    }

    void run() throws InterruptedException {
        Thread.sleep(80);
        set++;

        if (set == 100) {
            set = 0;
            sec++;
        } else if (sec == 60) {
            sec = 0;
            min++;
        } else if (min == 60) {
            min = 0;
            hrs++;
        }

    }

    void runtime() throws InterruptedException {
        while (run) {
            run();
            System.out.println(hrs + " : " + min + " : " + sec + " : " + set);
        }
    }



}