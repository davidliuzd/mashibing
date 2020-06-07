package com.mashibing.tank;

import com.mashibing.tank.base.BaseTank;
import com.mashibing.tank.base.FireStg;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 封装一个坦克类
 */
public class Tank extends BaseTank {
    public static int WIDTH = ResourceMgr.goodTankU.getWidth(), HEIGHT = ResourceMgr.goodTankU.getHeight();
    private static int SPEED = 5;
    Rectangle rect = new Rectangle();
    private int x, y;
    private Dir dir = Dir.DOWN;
    private boolean moving = true;
    private boolean living = true;
    private Random random = new Random();
    private TankFrame tf;
    private Group group = Group.BAD;

    public Tank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tankFrame;
        this.group = group;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void paint(Graphics g) {

        if (!living) tf.tanks.remove(this);
        //画一个矩形
        Color c = g.getColor();
        BufferedImage bm = null;
        switch (dir) {
            case DOWN:
                bm = this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD;
                break;
            case UP:
                bm = this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU;
                break;
            case RIGHT:
                bm = this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR;
                break;
            case LEFT:
                bm = this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL;
                ;
                break;
        }

        g.drawImage(bm, x, y, null);
        g.setColor(c);
        move();
    }

    public void fire() {
        int x1 = this.x + WIDTH / 2 - Bullet.WIDTH / 2;
        int y1 = this.y + HEIGHT / 2 - Bullet.HEIGHT / 2;
        //tf.bullets.add(new Bullet(x, y, this.dir, this.group, tf));
        FireStg fireStg = DefaultFireStg.getInstance(); //改成单例,省的创建太多的实例
        fireStg.fire(tf,dir,getGroup(),x1,y1);
    }

    private void move() {
        if (!moving) return;

        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }

        if (this.group == Group.BAD && random.nextInt(100) > 95) this.fire();

        if (this.group == Group.BAD && random.nextInt(10) > 8)
            randomDir();

        //用于防止坦克出界
        boundsCheck();

        //更新rect的值
        rect.x = this.x;
        rect.y = this.y;
    }

    private void boundsCheck() {
        if (this.x < 0) x = 0;
        if (this.y < 30) y = 30;
        if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH) x = TankFrame.GAME_WIDTH - Tank.WIDTH;
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT) y = TankFrame.GAME_HEIGHT - Tank.HEIGHT;
    }

    private void randomDir() {
        dir = Dir.values()[random.nextInt(4)];
    }


    public void die() {
        living = false;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
