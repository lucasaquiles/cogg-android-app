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

    @DatabaseField(foreign=true, foreignAutoRefresh=true, columnName="pic_id")
    private Pic pic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
