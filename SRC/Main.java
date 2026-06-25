import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    Player player;
    Enemy enemy;

    JLabel playerHP;
    JLabel enemyHP;

    JTextArea log;

    JButton attackBtn;
    JButton blockBtn;
    JButton specialBtn;

    public Main() {

        player = new Player(20, 5, 2, 10, 10);
        enemy = new Fighter(15, 4, 2, 5, 5);

        setTitle("RPG Game");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // HP display
        JPanel top = new JPanel(new GridLayout(2, 1));

        playerHP = new JLabel();
        enemyHP = new JLabel();

        top.add(playerHP);
        top.add(enemyHP);

        add(top, BorderLayout.NORTH);

        // Log
        log = new JTextArea();
        log.setEditable(false);
        add(new JScrollPane(log), BorderLayout.CENTER);

        // Buttons
        JPanel bottom = new JPanel();

        attackBtn = new JButton("Attack");
        blockBtn = new JButton("Block");
        specialBtn = new JButton("Special");

        bottom.add(attackBtn);
        bottom.add(blockBtn);
        bottom.add(specialBtn);

        add(bottom, BorderLayout.SOUTH);

        // Actions
        attackBtn.addActionListener(e -> {
            player.attack(enemy);
            log.append("You attacked!\n");
            enemyTurn();
            updateUI();
        });

        blockBtn.addActionListener(e -> {
            player.block();
            log.append("You blocked!\n");
            enemyTurn();
            updateUI();
        });

        specialBtn.addActionListener(e -> {
            player.special(enemy);
            log.append("You used SPECIAL!\n");
            enemyTurn();
            updateUI();
        });

        updateUI();
        setVisible(true);
    }

    private void enemyTurn() {

        if (enemy.isAlive()) {
            enemy.takeTurn(player);
            log.append("Enemy acted!\n");
        }

        player.endTurnReset();
        player.reduceCooldown();

        checkGame();
    }

    private void updateUI() {

        playerHP.setText("Player HP: " + player.currentHP + "/" + player.maxHP);
        enemyHP.setText("Enemy HP: " + enemy.currentHP + "/" + enemy.maxHP);
    }

    private void checkGame() {

        if (!enemy.isAlive()) {
            log.append("Enemy defeated!\n");

            enemy = new Rogue(12, 5, 1, 15, 10); // next enemy
        }

        if (!player.isAlive()) {
            log.append("GAME OVER\n");

            attackBtn.setEnabled(false);
            blockBtn.setEnabled(false);
            specialBtn.setEnabled(false);
        }

        updateUI();
    }

    public static void main(String[] args) {
        new Main();
    }
}