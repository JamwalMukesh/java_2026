import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo3 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Demo3::createUI);
    }

    private static void createUI() {
        JFrame frame = new JFrame("lock() UI Freeze Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 320);
        frame.setLocationRelativeTo(null);

        AnimatedBackgroundPanel background = new AnimatedBackgroundPanel();
        background.setLayout(new BorderLayout());

        JPanel grid = createGrid();
        background.add(grid, BorderLayout.CENTER);

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

        // BAD: UI update happens via EDT but waits on lock()
        Timer uiTimer = new Timer(200, e -> {
            container.lock.lock(); // 🚨 BLOCKING CALL ON UI THREAD
            try {
                labels.get("BTC").setText(String.valueOf(container.btc));
                labels.get("ETH").setText(String.valueOf(container.eth));
                labels.get("LTC").setText(String.valueOf(container.ltc));
                labels.get("BCH").setText(String.valueOf(container.bch));
                labels.get("XRP").setText(String.format("%.4f", container.xrp));
            } finally {
                container.lock.unlock();
            }
        });
        uiTimer.start();

        new PriceUpdater(container).start();

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
        private final Color c1 = new Color(255, 230, 230);
        private final Color c2 = new Color(230, 230, 255);
        private float t = 0;
        private boolean forward = true;

        AnimatedBackgroundPanel() {
            new Timer(40, e -> animate()).start();
        }

        private void animate() {
            t += forward ? 0.01f : -0.01f;
            if (t >= 1 || t <= 0) forward = !forward;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(interpolate(c1, c2, t));
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        private Color interpolate(Color a, Color b, float t) {
            int r = (int) (a.getRed() + t * (b.getRed() - a.getRed()));
            int g = (int) (a.getGreen() + t * (b.getGreen() - a.getGreen()));
            int bl = (int) (a.getBlue() + t * (b.getBlue() - a.getBlue()));
            return new Color(r, g, bl);
        }
    }

    // ================= DATA =================

    static class PricesContainer {
        final Lock lock = new ReentrantLock();
        double btc, eth, ltc, bch, xrp;
    }

    // ================= PRODUCER (HOLDS LOCK LONG) =================

    static class PriceUpdater extends Thread {
        private final PricesContainer c;
        private final Random r = new Random();

        PriceUpdater(PricesContainer c) {
            this.c = c;
            setDaemon(true);
        }

        @Override
        public void run() {
            while (true) {
                c.lock.lock();
                try {
                    // 🚨 Simulate heavy computation
                    Thread.sleep(2000);

                    c.btc = r.nextInt(20000);
                    c.eth = r.nextInt(2000);
                    c.ltc = r.nextInt(500);
                    c.bch = r.nextInt(5000);
                    c.xrp = r.nextDouble();
                } catch (InterruptedException ignored) {
                } finally {
                    c.lock.unlock();
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {}
            }
        }
    }
}