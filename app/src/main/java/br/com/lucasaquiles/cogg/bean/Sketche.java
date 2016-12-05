package br.com.lucasaquiles.cogg.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lucasaquiles on 12/11/16.
 */


@DatabaseTable
public class Sketche implements Serializable {

    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField(canBeNull = true)
    private String pathImageBase;
    @DatabaseField(canBeNull = true)
    private String pathToAvatar;
    @DatabaseField(canBeNull = true)
    private Date data;

    @DatabaseField(canBeNull = true)
    private int noiseResourceId;
    @DatabaseField(canBeNull = true)
    private int headResourceId;
    @DatabaseField(canBeNull = true)
    private int eyeResourceId;
    @DatabaseField(canBeNull = true)
    private int eyebrowResourceId;
    @DatabaseField(canBeNull = true)
    private int mouthResourceId;
    @DatabaseField(canBeNull = true)
    private int hairResourceId;

    @DatabaseField(foreign=true, foreignAutoRefresh=true, columnName="pic_id")
    private Pic pic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPathImageBase() {
        return pathImageBase;
    }

    public void setPathImageBase(String pathImageBase) {
        this.pathImageBase = pathImageBase;
    }

    public String getPathToAvatar() {
        return pathToAvatar;
    }

    public void setPathToAvatar(String pathToAvatar) {
        this.pathToAvatar = pathToAvatar;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Pic getPic() {
        return pic;
    }

    public void setPic(Pic pic) {
        this.pic = pic;
    }


    public int getNoiseResourceId() {
        return noiseResourceId;
    }

    public void setNoiseResourceId(int noiseResourceId) {
        this.noiseResourceId = noiseResourceId;
    }

    public int getHeadResourceId() {
        return headResourceId;
    }

    public void setHeadResourceId(int headResourceId) {
        this.headResourceId = headResourceId;
    }

    public int getEyeResourceId() {
        return eyeResourceId;
    }

    public void setEyeResourceId(int eyeResourceId) {
        this.eyeResourceId = eyeResourceId;
    }

    public int getEyebrowResourceId() {
        return eyebrowResourceId;
    }

    public void setEyebrowResourceId(int eyebrowResourceId) {
        this.eyebrowResourceId = eyebrowResourceId;
    }

    public int getMouthResourceId() {
        return mouthResourceId;
    }

    public void setMouthResourceId(int mouthResourceId) {
        this.mouthResourceId = mouthResourceId;
    }

    public int getHairResourceId() {
        return hairResourceId;
    }

    public void setHairResourceId(int hairResourceId) {
        this.hairResourceId = hairResourceId;
    }


}
