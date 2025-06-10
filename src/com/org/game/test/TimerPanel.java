package com.org.game.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerPanel extends JPanel {
    private JLabel blackLabel;
    private JLabel whiteLabel;
    private int blackTime = 20;
    private int whiteTime = 20;
    private Timer timer;
    private FiveChessFrame fiveChessFrame;

    public TimerPanel(FiveChessFrame fiveChessFrame) {
        this.fiveChessFrame = fiveChessFrame;

        setPreferredSize(new Dimension(150, 100)); // 设置右侧区域大小
        setLayout(new GridLayout(2, 1));

        blackLabel = new JLabel("黑棋：20秒", SwingConstants.CENTER);
        whiteLabel = new JLabel("白棋：20秒", SwingConstants.CENTER);

        blackLabel.setFont(new Font("华文楷体", Font.BOLD, 18));
        whiteLabel.setFont(new Font("华文楷体", Font.BOLD, 18));

        add(blackLabel);
        add(whiteLabel);

        // 启动倒计时逻辑，每秒执行一次
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fiveChessFrame.isBlack) {
                    blackTime--;
                    blackLabel.setText("黑棋：" + blackTime + "秒");
                    if (blackTime == 0) {
                        timer.stop();
                        JOptionPane.showMessageDialog(null, "黑棋超时！");
                    }
                } else {
                    whiteTime--;
                    whiteLabel.setText("白棋：" + whiteTime + "秒");
                    if (whiteTime == 0) {
                        timer.stop();
                        JOptionPane.showMessageDialog(null, "白棋超时！");
                    }
                }
            }
        });
        timer.start();
    }

    // 在落子后切换回合时调用
    public void switchTurn() {
        timer.stop();

        System.out.println("当前是黑棋？" + fiveChessFrame.isBlack);

        if (fiveChessFrame.isBlack) {
            blackTime = 20;
            blackLabel.setText("黑棋：" + blackTime + "秒");
            blackLabel.setForeground(Color.RED);
            whiteLabel.setForeground(Color.BLACK);
            blackLabel.repaint();
        } else {
            whiteTime = 20;
            whiteLabel.setText("白棋：" + whiteTime + "秒");
            whiteLabel.setForeground(Color.RED);
            blackLabel.setForeground(Color.BLACK);
            whiteLabel.repaint();
        }
        timer.start();
    }

    // 游戏重置时调用
    public void reset() {
        timer.stop();
        blackTime = 20;
        whiteTime = 20;
        fiveChessFrame.isBlack = true;

        timer.start();// 重置计时器

        blackLabel.setText("黑棋：" + blackTime + "秒");
        whiteLabel.setText("白棋：" + whiteTime + "秒");
        blackLabel.setForeground(Color.RED);
        whiteLabel.setForeground(Color.BLACK);

        whiteLabel.repaint();
        blackLabel.repaint();

    }
}
