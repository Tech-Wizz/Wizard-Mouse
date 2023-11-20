import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MouseJiggler implements KeyListener {
    private boolean exitFlag = false;

    public static void main(String[] args) {
        MouseJiggler mouseJiggler = new MouseJiggler();
        mouseJiggler.jiggleMouse();
    }

    public void jiggleMouse() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        int radius = 2;
        double speed = 0.1; // Decreased sleep time for more responsiveness
        int centerX = screenWidth / 2;
        int centerY = screenHeight / 2;

        System.out.println("Press 'M' to exit.");

        // Set up key listener
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                if (e.getKeyChar() == 'M') {
                    exitFlag = true;
                }
            }
            return false;
        });

        while (!exitFlag) {
            for (int angle = 0; angle < 360; angle += 10) {
                int x = centerX + (int) (radius * Math.cos(Math.toRadians(angle)));
                int y = centerY + (int) (radius * Math.sin(Math.toRadians(angle)));

                moveMouse(x, y);

                try {
                    Thread.sleep(10); // Short sleep time for responsiveness
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Exiting the program.");
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
        // Unused, but required for KeyListener interface
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Unused, but required for KeyListener interface
    }
}