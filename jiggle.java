import java.awt.*;
import java.awt.event.KeyEvent;

public class MouseJiggler {
    public static void main(String[] args) {
        jiggleMouse();
    }

    public static void jiggleMouse() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        int radius = 2;
        double speed = 0.1; // Decreased sleep time for more responsiveness
        int centerX = screenWidth / 2;
        int centerY = screenHeight / 2;

        System.out.println("Press 'Esc' to exit.");

        while (true) {
            for (int angle = 0; angle < 360; angle += 10) {
                int x = centerX + (int) (radius * Math.cos(Math.toRadians(angle)));
                int y = centerY + (int) (radius * Math.sin(Math.toRadians(angle)));

                moveMouse(x, y);

                // Check if the 'Esc' key is pressed
                if (isKeyPressed(KeyEvent.VK_ESCAPE)) {
                    System.out.println("Exiting the program.");
                    return;
                }

                try {
                    Thread.sleep(10); // Short sleep time for responsiveness
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void moveMouse(int x, int y) {
        try {
            Robot robot = new Robot();
            robot.mouseMove(x, y);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static boolean isKeyPressed(int keyCode) {
        return Toolkit.getDefaultToolkit().getLockingKeyState(keyCode);
    }
}