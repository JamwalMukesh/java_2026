import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo1 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Demo1::createUI);
    }

    private static void createUI() {
        JFrame frame = new JFrame("Cryptocurrency Prices (Swing)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 250);
        frame.setLocationRelativeTo(null);

        AnimatedBackgroundPanel backgroundPanel = new AnimatedBackgroundPanel();
        backgroundPanel.setLayout(new GridBagLayout());

        JPanel grid = createGridPanel();
        backgroundPanel.add(grid);

        frame.setContentPane(backgroundPanel);
        frame.setVisible(true);
    }

    private static JPanel createGridPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setOpaque(false);

        PricesContainer pricesContainer = new PricesContainer();
        Map<String, JLabel> labels = new HashMap<>();

        addCryptoRow(panel, labels, "BTC");
        addCryptoRow(panel, labels, "ETH");
        addCryptoRow(panel, labels, "LTC");
        addCryptoRow(panel, labels, "BCH");
        addCryptoRow(panel, labels, "XRP");

        new PriceUpdater(pricesContainer).start();
        new PriceRefresher(pricesContainer, labels).start();

        return panel;
    }

    private static void addCryptoRow(JPanel panel, Map<String, JLabel> labels, String name) {
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

    // =================== BACKGROUND ANIMATION ===================

    static class AnimatedBackgroundPanel extends JPanel {
        private Color color1 = new Color(144, 238, 144);
        private Color color2 = new Color(173, 216, 230);
        private float ratio = 0.0f;
        private boolean forward = true;

        AnimatedBackgroundPanel() {
            Timer timer = new Timer(40, e -> animate());
            timer.start();
        }

        private void animate() {
            ratio += forward ? 0.01f : -0.01f;
            if (ratio >= 1.0f || ratio <= 0.0f) {
                forward = !forward;
            }
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setPaint(interpolate(color1, color2, ratio));
            g2.fillRect(0, 0, getWidth(), getHeight());
        }

        private Color interpolate(Color c1, Color c2, float t) {
            int r = (int) (c1.getRed() + t * (c2.getRed() - c1.getRed()));
            int g = (int) (c1.getGreen() + t * (c2.getGreen() - c1.getGreen()));
            int b = (int) (c1.getBlue() + t * (c2.getBlue() - c1.getBlue()));
            return new Color(r, g, b);
        }
    }

    // =================== DATA CONTAINER ===================

    static class PricesContainer {
        private final Lock lock = new ReentrantLock();
        double btc, eth, ltc, bch, xrp;

        Lock getLock() {
            return lock;
        }
    }

    // =================== DATA UPDATER THREAD ===================

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
                container.getLock().lock();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored) {}
                try {
                    container.btc = random.nextInt(20000);
                    container.eth = random.nextInt(2000);
                    container.ltc = random.nextInt(500);
                    container.bch = random.nextInt(5000);
                    container.xrp = random.nextDouble();
                } finally {
                    container.getLock().unlock();
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {}
            }
        }
    }

    // =================== UI REFRESH THREAD ===================

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
                container.getLock().lock();
                try {
                	try {
                    Thread.sleep(2000);
                	} catch (InterruptedException ignored) {}
                    SwingUtilities.invokeLater(() -> {
                        labels.get("BTC").setText(String.valueOf(container.btc));
                        labels.get("ETH").setText(String.valueOf(container.eth));
                        labels.get("LTC").setText(String.valueOf(container.ltc));
                        labels.get("BCH").setText(String.valueOf(container.bch));
                        labels.get("XRP").setText(String.format("%.4f", container.xrp));
                    });
                } finally {
                    container.getLock().unlock();
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
            }
        }
    }
}