package br.com.lucasaquiles.cogg.bean;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by lucasaquiles on 2/11/16.
 */

@DatabaseTable
public class Pic implements Serializable {

    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField
    private String filePath;

    @DatabaseField
    private String avatarPath;

    @DatabaseField
    private String title;

    @DatabaseField
    private String emotion;

    public ForeignCollection<Sketche> getSketches() {
        return sketches;
    }

    public void setSketches(ForeignCollection<Sketche> sketches) {
        this.sketches = sketches;
    }

    @ForeignCollectionField
    private ForeignCollection<Sketche> sketches;

    @ForeignCollectionField
    private ForeignCollection<ItemPic> itensPic;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }


    public Pic(){}
    public Pic(String filePath, String title, String emotion){
        this.filePath = filePath;
        this.title = title;
        this.emotion = emotion;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ForeignCollection<ItemPic> getItensPic() {
        return itensPic;
    }

    public void setItensPic(ForeignCollection<ItemPic> itensPic) {
        this.itensPic = itensPic;
    }
}
