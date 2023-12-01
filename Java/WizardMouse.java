import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WizardMouse extends JFrame implements KeyListener {
    private boolean isPaused = false;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WizardMouse wizardMouse = new WizardMouse();
            wizardMouse.setupUI();
            new Thread(wizardMouse::jiggleMouse).start();
        });
    }

    private void setupUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 10);
        setLocationRelativeTo(null);
        setTitle("Wizard Mouse");

        JButton pauseButton = new JButton("Pause");
        JButton playButton = new JButton("Play");

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isPaused = true;
            }
        });

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isPaused = false;
            }
        });

        setLayout(new FlowLayout());
        add(pauseButton);
        add(playButton);

        addKeyListener(this);
        setVisible(true);
    }

    // ... (unchanged)

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'M') {
            System.exit(0);
        }
    }

    // ... (remaining methods unchanged)
}