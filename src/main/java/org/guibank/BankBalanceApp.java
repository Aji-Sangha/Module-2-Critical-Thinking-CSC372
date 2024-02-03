package org.guibank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankBalanceApp extends JFrame {
    private double balance = 0.0;

    private JLabel balanceLabel;
    private JTextField depositField;
    private JTextField withdrawField;

    public BankBalanceApp() {
        setTitle("Bank Balance Application");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton showBalanceButton = new JButton("Show Balance");

        balanceLabel = new JLabel("Balance: $" + balance);
        depositField = new JTextField();
        withdrawField = new JTextField();

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double depositAmount = Double.parseDouble(depositField.getText());
                deposit(depositAmount);
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double withdrawAmount = Double.parseDouble(withdrawField.getText());
                withdraw(withdrawAmount);
            }
        });

        showBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBalance();
            }
        });

        panel.add(new JLabel("Deposit Amount:"));
        panel.add(depositField);
        panel.add(new JLabel("Withdraw Amount:"));
        panel.add(withdrawField);
        panel.add(depositButton);
        panel.add(withdrawButton);
        panel.add(showBalanceButton);
        panel.add(balanceLabel);

        add(panel);
    }

    private void deposit(double amount) {
        balance += amount;
        updateBalanceLabel();
    }

    private void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            updateBalanceLabel();
        } else {
            JOptionPane.showMessageDialog(this, "Insufficient funds!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showBalance() {
        JOptionPane.showMessageDialog(this, "Current Balance: $" + balance, "Balance", JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Balance: $" + balance);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BankBalanceApp().setVisible(true);
            }
        });
    }
}
