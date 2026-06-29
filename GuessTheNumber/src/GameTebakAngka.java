import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameTebakAngka extends JFrame {
    private int targetNumber;
    private JTextField inputField;
    private JLabel statusLabel;
    private JButton guessButton;
    private Random random;

    public GameTebakAngka() {
        random = new Random();
        generateNewNumber();
        setupUI();
    }

    private void generateNewNumber() {
        targetNumber = random.nextInt(100) + 1;
    }

    private void setupUI() {
        setTitle("Game Tebak Angka");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 20, 15, 20));

        JLabel titleLabel = new JLabel("Tebak angka dari 1 - 100");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        inputField = new JTextField();
        inputField.setMaximumSize(new Dimension(200, 30));
        inputField.setAlignmentX(Component.CENTER_ALIGNMENT);

        guessButton = new JButton("Tebak");
        guessButton.setBackground(Color.gray);
        guessButton.setForeground(Color.WHITE);
        guessButton.setFocusPainted(false);
        guessButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        guessButton.setMaximumSize(new Dimension(150, 30));

        statusLabel = new JLabel("Selamat mencoba!");
        statusLabel.setForeground(Color.darkGray);
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(inputField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(guessButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(statusLabel);

        add(panel);

        guessButton.addActionListener(e -> checkGuess());
        inputField.addActionListener(e -> checkGuess());
    }

    private void checkGuess() {
        String input = inputField.getText().trim();

        if (input.isEmpty()) {
            statusLabel.setText("Masukkan angka dulu!");
            statusLabel.setForeground(Color.red);
            return;
        }

        int guess;
        try {
            guess = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            statusLabel.setText("Input harus berupa angka!");
            statusLabel.setForeground(Color.red);
            return;
        }

        if (guess < 1 || guess > 100) {
            statusLabel.setText("Angka harus antara 1 - 100!");
            statusLabel.setForeground(Color.red);
            return;
        }

        if (guess > targetNumber) {
            statusLabel.setText("Ups, terlalu Tinggi!");
            statusLabel.setForeground(Color.orange);
        } else if (guess < targetNumber) {
            statusLabel.setText("Masih terlalu Rendah!");
            statusLabel.setForeground(Color.orange);
        } else {
            statusLabel.setText("Tepat sekali!");
            statusLabel.setForeground(Color.green);

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Selamat! tebakanmu benar!\nMau main lagi?",
                    "Menang!",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                generateNewNumber();
                inputField.setText("");
                statusLabel.setText("Silakan mulai menebak!");
                statusLabel.setForeground(Color.GRAY);
            } else {
                System.exit(0);
            }
        }

        inputField.selectAll();
        inputField.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GameTebakAngka().setVisible(true);
        });
    }
}