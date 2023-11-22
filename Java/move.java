import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class move extends JFrame implements KeyListener {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            move move = new move();
            move.setupUI();
            new Thread(move::jiggleMouse).start();
        });
    }

    private void setupUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        // Set custom icon
        ImageIcon icon = new ImageIcon("/image/Mouse_Wizard.png");
        setIconImage(icon.getImage());

        // Set image instead of text
        ImageIcon image = new ImageIcon("/image/Mouse_Wizard.png");
        JLabel imageLabel = new JLabel(image);
        add(imageLabel);

        addKeyListener(this);

        setVisible(true);
    }

    public void jiggleMouse() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        int radius = 20;
        int centerX = screenWidth / 2;
        int centerY = screenHeight / 2;

        while (true) {
            for (int angle = 0; angle < 360; angle += 10) {
                int x = centerX + (int) (radius * Math.cos(Math.toRadians(angle)));
                int y = centerY + (int) (radius * Math.sin(Math.toRadians(angle)));

                moveMouse(x, y);

                try {
                    Thread.sleep(9000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void moveMouse(int x, int y) {
        try {
            Robot robot = new Robot();
            robot.mouseMove(x, y);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Unused, but required for KeyListener interface
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'M') {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Unused, but required for KeyListener interface
    }
}