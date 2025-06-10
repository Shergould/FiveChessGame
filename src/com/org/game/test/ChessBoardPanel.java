package com.org.game.test;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class ChessBoardPanel extends JPanel {

    private BufferedImage bgImage;
    private FiveChessFrame fiveChessFrame;
    private TimerPanel timerPanel;

    // 接受主框架引用
    public ChessBoardPanel(FiveChessFrame fiveChessFrame) {
        this.fiveChessFrame = fiveChessFrame;
    }

    //绘制棋盘
    public void paint(Graphics g2) {

        //双缓冲技术防止屏幕闪烁
        BufferedImage bi = new BufferedImage(830,880,BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics();

        //绘制背景图
        g2.drawImage(fiveChessFrame.bgImage, 0, 30, this);

        //画线、线间隔44像素、横线19条、竖线19条
        for (int a = 0; a < 19; a++) {
            g2.drawLine(15, 31 + 44 * a, 807, 31 + 44 * a);
            g2.drawLine(15 + 44 * a, 31, 15 + 44 * a, 823);
        }

        // 画几个特殊定位点
        g2.fillOval(144,159,6,6);
        g2.fillOval(672,159,6,6);
        g2.fillOval(144,688,6,6);
        g2.fillOval(672,688,6,6);
        g2.fillOval(407,424,6,6);
        g2.fillOval(407,159,6,6);
        g2.fillOval(144,424,6,6);
        g2.fillOval(407,688,6,6);
        g2.fillOval(672,424,6,6);

        // 画棋子
        for (int i=0;i<19;i++) {
            for (int j = 0; j < 19; j++) {
                if (fiveChessFrame.allChess[i][j]==1){
                    int tempX=i*44+15;
                    int tempY=j*44+31;
                    g2.fillOval(tempX-10,tempY-10,20,20);
                }
                if (fiveChessFrame.allChess[i][j]==2){
                    int tempX=i*44+15;
                    int tempY=j*44+31;
                    g2.setColor(Color.WHITE);
                    g2.fillOval(tempX-10,tempY-10,20,20);
                    g2.setColor(Color.BLACK);
                    g2.drawOval(tempX-10,tempY-10,20,20);
                }
            }
        }
    }
}