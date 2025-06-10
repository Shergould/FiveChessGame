package com.org.game.test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FiveChessFrame extends JFrame implements MouseListener {
    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    // 保存棋盘上棋子的信息
    int[][] allChess = new int[19][19];
    public BufferedImage bgImage;

    java.util.List<Integer> moveX = new java.util.ArrayList<>();
    java.util.List<Integer> moveY = new java.util.ArrayList<>();

    private TimerPanel timerPanel;
    private ChessBoardPanel chessBoardPanel;
    private FunctionPanel functionPanel;

    // 保存棋子的坐标
    int x = 0;
    int y = 0;

    // 判断当前是黑棋还是白棋
    boolean isBlack = true;

    // 是否可以下棋
    boolean canPlay = true;

    // 创建棋盘
    public FiveChessFrame() {

        // 创建面板
        timerPanel = new TimerPanel(this);
        chessBoardPanel = new ChessBoardPanel(this);
        functionPanel = new FunctionPanel(this, timerPanel);

        // 设置窗口属性
        this.setTitle("五子棋"); // 标题
        this.setSize(1180, 880); // 窗口大小
        this.setLocation((width - 799) / 2, (height - 838) / 2); // 窗口位置
        this.setResizable(false); // 窗口大小不可改变
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 点击关闭按钮退出程序
        this.addMouseListener(this); // 监听鼠标事件
        this.setVisible(true); // 窗口可见

        chessBoardPanel.addMouseListener(this);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(chessBoardPanel, BorderLayout.CENTER);
        contentPanel.add(functionPanel, BorderLayout.EAST);

        this.setContentPane(contentPanel);
        this.setVisible(true);

        //加载背景图片
        try {
            bgImage = ImageIO.read(new File("fig/FiveChessFrame.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }


    @Override
    public void mousePressed(MouseEvent e) {
        if (canPlay == true) {

            // 获取鼠标点击的坐标
            x = e.getX();
            y = e.getY();

            // 判断鼠标点击的坐标是否在棋盘范围内
            if (x >= 15 && x <= 807 && y >= 31 && y <= 823) {
                x = x / 44;
                y = y / 44;
                if (allChess[x][y] == 0) {
                    if (isBlack == true) {
                        allChess[x][y] = 1;
                        isBlack = false;
                    } else {
                        allChess[x][y] = 2;
                        isBlack = true;
                    }

                    // 记录当前棋子的位置
                    moveX.add(x);
                    moveY.add(y);


                    boolean winFlag = this.checkWin();
                    if (winFlag == true) {
                        JOptionPane.showMessageDialog(this, "游戏结束" + (allChess[x][y] == 1 ? "黑" : "白") + "获胜");
                        canPlay = false;
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "当前位置有棋子，请重新落子");
                }

                System.out.println("落子后，当前轮到：" + (isBlack ? "黑棋" : "白棋"));

                // 切换倒计时回合
                timerPanel.switchTurn();

                this.repaint();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    //判断是否胜利
    private boolean checkWin() {
        boolean flag = false;
        int count = 1;

        //判断横向是否有五子连珠
        int color = allChess[x][y];
        int i = 1;
        while (color == allChess[x + i][y]) {
            count++;
            i++;
        }
        i = 1;
        while (color == allChess[x - i][y]) {
            count++;
            i++;
        }
        if (count >= 5) {
            flag = true;
        }

        //判断纵向是否有五子连珠
        int i2 = 1;
        int count2 = 1;
        while (color == allChess[x][y + i2]) {
            count2++;
            i2++;
        }
        i2 = 1;
        while (color == allChess[x][y - i2]) {
            count2++;
            i2++;
        }
        if (count2 >= 5) {
            flag = true;
        }

        //判断斜向（左下右上）是否有五子连珠
        int i3 = 1;
        int count3 = 1;
        while (color == allChess[x + i3][y + i3]) {
            count3++;
            i3++;
        }
        i3 = 1;
        while (color == allChess[x - i3][y - i3]) {
            count3++;
            i3++;
        }
        if (count3 >= 5) {
            flag = true;
        }

        //判断斜向（左上右下）是否有五子连珠
        int i4 = 1;
        int count4 = 1;
        while (color == allChess[x - i4][y + i4]) {
            count4++;
            i4++;
        }
        i4 = 1;
        while (color == allChess[x + i4][y - i4]) {
            count4++;
            i4++;
        }
        if (count4 >= 5) {
            flag = true;
        }

        return flag;
    }
}