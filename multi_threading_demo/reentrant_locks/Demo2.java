import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo2 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Demo2::createUI);
    }

    private static void createUI() {
        JFrame frame = new JFrame("Crypto Prices (tryLock Demo)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(320, 260);
        frame.setLocationRelativeTo(null);

        AnimatedBackgroundPanel background = new AnimatedBackgroundPanel();
        background.setLayout(new GridBagLayout());

        JPanel grid = createGrid();
        background.add(grid);

        frame.setContentPane(background);
        frame.setVisible(true);
    }

    private static JPanel createGrid() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setOpaque(false);

        PricesContainer container = new PricesContainer();
        Map<String, JLabel> labels = new HashMap<>();

        addRow(panel, labels, "BTC");
        addRow(panel, labels, "ETH");
        addRow(panel, labels, "LTC");
        addRow(panel, labels, "BCH");
        addRow(panel, labels, "XRP");

        new PriceUpdater(container).start();
        new PriceRefresher(container, labels).start();

        return panel;
    }

    private static void addRow(JPanel panel, Map<String, JLabel> labels, String name) {
        JLabel nameLabel = new JLabel(name);
        nameLabel.setForeground(Color.BLUE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));

        nameLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                nameLabel.setForeground(Color.RED);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                nameLabel.setForeground(Color.BLUE);
            }
        });

        JLabel priceLabel = new JLabel("0");
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        panel.add(nameLabel);
        panel.add(priceLabel);

        labels.put(name, priceLabel);
    }

    // ================= BACKGROUND ANIMATION =================

    static class AnimatedBackgroundPanel extends JPanel {
        private final Color c1 = new Color(144, 238, 144);
        private final Color c2 = new Color(173, 216, 230);
        private float ratio = 0f;
        private boolean forward = true;

        AnimatedBackgroundPanel() {
            new Timer(40, e -> animate()).start();
        }

        private void animate() {
            ratio += forward ? 0.01f : -0.01f;
            if (ratio >= 1f || ratio <= 0f) {
                forward = !forward;
            }
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setPaint(interpolate(c1, c2, ratio));
            g2.fillRect(0, 0, getWidth(), getHeight());
        }

        private Color interpolate(Color a, Color b, float t) {
            int r = (int) (a.getRed() + t * (b.getRed() - a.getRed()));
            int g = (int) (a.getGreen() + t * (b.getGreen() - a.getGreen()));
            int bC = (int) (a.getBlue() + t * (b.getBlue() - a.getBlue()));
            return new Color(r, g, bC);
        }
    }

    // ================= DATA CONTAINER =================

    static class PricesContainer {
        private final Lock lock = new ReentrantLock();
        double btc, eth, ltc, bch, xrp;
    }

    // ================= PRODUCER THREAD =================

    static class PriceUpdater extends Thread {
        private final PricesContainer container;
        private final Random random = new Random();

        PriceUpdater(PricesContainer container) {
            this.container = container;
            setDaemon(true);
        }

        @Override
        public void run() {
            while (true) {
                if (container.lock.tryLock()) {
                    try {
                        container.btc = random.nextInt(20000);
                        container.eth = random.nextInt(2000);
                        container.ltc = random.nextInt(500);
                        container.bch = random.nextInt(5000);
                        container.xrp = random.nextDouble();
                    } finally {
                        container.lock.unlock();
                    }
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {}
            }
        }
    }

    // ================= CONSUMER THREAD =================

    static class PriceRefresher extends Thread {
        private final PricesContainer container;
        private final Map<String, JLabel> labels;

        PriceRefresher(PricesContainer container, Map<String, JLabel> labels) {
            this.container = container;
            this.labels = labels;
            setDaemon(true);
        }

        @Override
        public void run() {
            while (true) {
                if (container.lock.tryLock()) {
                    try {
                        SwingUtilities.invokeLater(() -> {
                            labels.get("BTC").setText(String.valueOf(container.btc));
                            labels.get("ETH").setText(String.valueOf(container.eth));
                            labels.get("LTC").setText(String.valueOf(container.ltc));
                            labels.get("BCH").setText(String.valueOf(container.bch));
                            labels.get("XRP").setText(String.format("%.4f", container.xrp));
                        });
                    } finally {
                        container.lock.unlock();
                    }
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
            }
        }
    }
}