package com.mashibing.tank;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet {

    private static final int SPEED = 8;
    private int x,y;
    private Dir dir;
    public static  int WIDTH=ResourceMgr.bulletD.getWidth(),HEIGHT=ResourceMgr.bulletD.getHeight();

    private boolean living =true;
    TankFrame tf;

    public Bullet(int x, int y, Dir dir,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf=tf;

    }

    public void paint(Graphics g){
        if (!living){
            tf.bullets.remove(this);
            return;
        }

        BufferedImage bm=null;
        switch (dir){
            case DOWN:
                bm=ResourceMgr.bulletD;
                break;
            case UP:
                bm=ResourceMgr.bulletU;
                break;
            case RIGHT:
                bm=ResourceMgr.bulletR;
                break;
            case LEFT:
                bm=ResourceMgr.bulletL;
                break;
        }

        g.drawImage(bm,x+WIDTH/2,y+HEIGHT/2,null);

        move();
    }

    private void move() {
        switch (dir){
            case LEFT:
                x-=SPEED;
                break;
            case UP:
                y-=SPEED;
                break;
            case RIGHT:
                x+=SPEED;
                break;
            case DOWN:
                y+=SPEED;
                break;
        }

        if (x<0 || y<0 || x>TankFrame.GAME_WIDTH || y>TankFrame.GAME_HEIGHT){
            living =false;
        }
    }

    public void collideWith(Tank tank){
        Rectangle rectB=new Rectangle(this.x,this.y,WIDTH,HEIGHT);
        Rectangle rectT=new Rectangle(tank.getX(),tank.getY(),Tank.WIDTH,Tank.HEIGHT);
        if(rectB.intersects(rectT)){
            tank.die();
            this.die();
        }
    }

    public void die(){
        living=false;
    }
}
