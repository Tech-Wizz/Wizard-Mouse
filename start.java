import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Start extends JFrame implements KeyListener {
    private boolean exitFlag = false;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Start start = new Start();
            start.setupUI();
            new Thread(start::jiggleMouse).start(); // Run jiggleMouse in a separate thread
        });
    }

    private void setupUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea("Press 'M' to exit.");
        textArea.setEditable(false);
        add(textArea);

        addKeyListener(this);

        setVisible(true);
    }

    public void jiggleMouse() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        int radius = 20;
        double speed = 0.1;
        int centerX = screenWidth / 2;
        int centerY = screenHeight / 2;

        while (!exitFlag) {
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

        System.out.println("Exiting the program.");
        System.exit(0);
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
            exitFlag = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Unused, but required for KeyListener interface
    }
}