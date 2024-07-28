package main;

import object.OBJ_Boots;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Uranium;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {

        this.gp = gp;
    }

    public void setObject() {

        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 39 * gp.tileSize;
        gp.obj[0].worldY = 22 * gp.tileSize;

        gp.obj[1] = new OBJ_Key();
        gp.obj[1].worldX = 27 * gp.tileSize;
        gp.obj[1].worldY = 39 * gp.tileSize;

        gp.obj[2] = new OBJ_Key();
        gp.obj[2].worldX = 11 * gp.tileSize;
        gp.obj[2].worldY = 10 * gp.tileSize;

        gp.obj[3] = new OBJ_Key();
        gp.obj[3].worldX = 26 * gp.tileSize;
        gp.obj[3].worldY = 10 * gp.tileSize;

        gp.obj[4] = new OBJ_Door();
        gp.obj[4].worldX = 17 * gp.tileSize;
        gp.obj[4].worldY = 15 * gp.tileSize;

        gp.obj[5] = new OBJ_Door();
        gp.obj[5].worldX = 36 * gp.tileSize;
        gp.obj[5].worldY = 13 * gp.tileSize;

        gp.obj[6] = new OBJ_Door();
        gp.obj[6].worldX = 31 * gp.tileSize;
        gp.obj[6].worldY = 33 * gp.tileSize;

        gp.obj[7] = new OBJ_Door();
        gp.obj[7].worldX = 17 * gp.tileSize;
        gp.obj[7].worldY = 33 * gp.tileSize;

        gp.obj[8] = new OBJ_Uranium();
        gp.obj[8].worldX = 13 * gp.tileSize;
        gp.obj[8].worldY = 15 * gp.tileSize;

        gp.obj[9] = new OBJ_Uranium();
        gp.obj[9].worldX = 36 * gp.tileSize;
        gp.obj[9].worldY = 10 * gp.tileSize;

        gp.obj[10] = new OBJ_Uranium();
        gp.obj[10].worldX = 31 * gp.tileSize;
        gp.obj[10].worldY = 37 * gp.tileSize;

        gp.obj[11] = new OBJ_Uranium();
        gp.obj[11].worldX = 12 * gp.tileSize;
        gp.obj[11].worldY = 33 * gp.tileSize;

        gp.obj[12] = new OBJ_Boots();
        gp.obj[12].worldX = 14 * gp.tileSize;
        gp.obj[12].worldY = 30 * gp.tileSize;
    }
}
