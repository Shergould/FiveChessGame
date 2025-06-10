package com.org.game.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class FunctionPanel extends JPanel{;

    private FiveChessFrame fiveChessFrame;
    private TimerPanel timerPanel;

    public FunctionPanel(FiveChessFrame fiveChessFrame, TimerPanel timerPanel) {
        this.fiveChessFrame = fiveChessFrame;
        this.timerPanel = timerPanel;

        setLayout(new GridLayout(2, 1)); // 设置按钮面板的布局为2行1列

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1)); // 设置按钮面板的布局为3行1列

        //绘制重新开始按钮
        JButton restartButton = new JButton("重新开始");
        restartButton.setBounds(840, 120, 150, 50);
        restartButton.setFont(new Font("华文行楷", 10, 30));
        restartButton.setFocusPainted(false); // 去除按钮的焦点边框
        restartButton.setBorderPainted(false); // 去除按钮的边框
        buttonPanel.add(restartButton);

        //绘制悔棋按钮
        JButton regretButton = new JButton("悔棋");
        regretButton.setBounds(840, 200, 150, 50);
        regretButton.setFont(new Font("华文行楷", 10, 30));
        regretButton.setFocusPainted(false); // 去除按钮的焦点边框
        regretButton.setBorderPainted(false); // 去除按钮的边框
        buttonPanel.add(regretButton);

        //绘制认输按钮
        JButton giveUpButton = new JButton("认输");
        giveUpButton.setBounds(840, 280, 150, 50);
        giveUpButton.setFont(new Font("华文行楷", 10, 30));
        giveUpButton.setFocusPainted(false); // 去除按钮的焦点边框
        giveUpButton.setBorderPainted(false); // 去除按钮的边框
        buttonPanel.add(giveUpButton);

        this.add(buttonPanel);
        this.add(timerPanel);

        // 设置监听器
        // 重新开始按钮的监听器
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(fiveChessFrame, "是否重新开始游戏");
                if (result == 0) {
                    // 清空棋盘
                    for (int i = 0; i < 19; i++) {
                        Arrays.fill(fiveChessFrame.allChess[i], 0);
                    }

                    // 重置游戏状态
                    fiveChessFrame.canPlay = true;
                    fiveChessFrame.moveX.clear();
                    fiveChessFrame.moveY.clear();
                    fiveChessFrame.isBlack = true;
                    timerPanel.reset();
                    fiveChessFrame.repaint();
                }
            }
        });

        // 悔棋按钮的监听器
        regretButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(fiveChessFrame, "是否同意悔棋");
                if (result == 0) {
                    if (fiveChessFrame.moveX.isEmpty()) {
                        JOptionPane.showMessageDialog(fiveChessFrame, "没有可悔棋的步骤！");

                    }
                    // 获取最后一步并移除
                    int lastX = fiveChessFrame.moveX.removeLast();
                    int lastY = fiveChessFrame.moveY.removeLast();
                    fiveChessFrame.allChess[lastX][lastY] = 0;

                    // 切换回合
                    fiveChessFrame.isBlack = !fiveChessFrame.isBlack;
                    timerPanel.switchTurn(); // 确保倒计时同步切换
                    fiveChessFrame.repaint();

                }
            }
        });

        // 认输按钮的监听器
        giveUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(fiveChessFrame, "是否确认认输");
                if (result == 0) {
                    if (fiveChessFrame.isBlack) {
                        JOptionPane.showMessageDialog(fiveChessFrame, "白棋获胜！");
                    } else {
                        JOptionPane.showMessageDialog(fiveChessFrame, "黑棋获胜！");
                    }
                    fiveChessFrame.canPlay = false;
                }
            }
        });
    }
}
