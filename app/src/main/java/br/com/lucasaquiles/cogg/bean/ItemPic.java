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
}
