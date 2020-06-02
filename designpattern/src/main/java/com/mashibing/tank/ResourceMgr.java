package com.mashibing.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceMgr {
    public static BufferedImage tankL=null,tankU=null,tankR=null,tankD=null;
    public static BufferedImage bulletL,bulletR,bulletU,bulletD;

    static{
        try {
            tankL= ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/tankL.gif"));
            tankU= ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/tankU.gif"));
            tankR= ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/tankR.gif"));
            tankD= ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/tankD.gif"));

            bulletL= ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/bulletL.gif"));
            bulletR= ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/bulletR.gif"));
            bulletU= ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/bulletU.gif"));
            bulletD= ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/bulletD.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
