package br.com.lucasaquiles.cogg.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by lucasaquiles on 04/12/16.
 */

@DatabaseTable
public class ItemPic implements Serializable{

    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField
    private int resourceId;

    @DatabaseField
    private boolean head;
    @DatabaseField
    private boolean eye;
    @DatabaseField
    private boolean mouth;
    @DatabaseField
    private boolean eyebrow;
    @DatabaseField
    private boolean noise;
    @DatabaseField
    private boolean hair;

    public ItemPic(){}

    public ItemPic(int resourceId, Pic pic){
        this.resourceId = resourceId;
        this.pic = pic;
    }

    @DatabaseField(foreign=true, foreignAutoRefresh=true, columnName="pic_id", canBeNull = true)
    private Pic pic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public Pic getPic() {
        return pic;
    }

    public void setPic(Pic pic) {
        this.pic = pic;
    }


    public boolean isHead() {
        return head;
    }

    public void setHead(boolean head) {
        this.head = head;
    }

    public boolean isEye() {
        return eye;
    }

    public void setEye(boolean eye) {
        this.eye = eye;
    }

    public boolean isMouth() {
        return mouth;
    }

    public void setMouth(boolean mouth) {
        this.mouth = mouth;
    }

    public boolean isEyebrow() {
        return eyebrow;
    }

    public void setEyebrow(boolean eyebrow) {
        this.eyebrow = eyebrow;
    }

    public boolean isNoise() {
        return noise;
    }

    public void setNoise(boolean noise) {
        this.noise = noise;
    }

    public boolean isHair() {
        return hair;
    }

    public void setHair(boolean hair) {
        this.hair = hair;
    }
}
