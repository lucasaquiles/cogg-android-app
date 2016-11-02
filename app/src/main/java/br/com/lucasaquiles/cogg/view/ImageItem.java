package br.com.lucasaquiles.cogg.view;

import android.graphics.Bitmap;

import br.com.lucasaquiles.cogg.bean.Pic;

public class ImageItem {
	private Bitmap image;
	private String title;
	private Pic pic;

	private int resourceId;
	private String filePath;

	public ImageItem(Bitmap image, String title, String filePath, Pic pic){
		this.image = image;
		this.title = title;
		this.filePath = filePath;
		this.pic = pic;
	}

	public ImageItem(Bitmap image, String title, String filePath){
		this.image = image;
		this.title = title;
		this.filePath = filePath;
	}

	public ImageItem(Bitmap image, String title, int resourceId  ) {
		super();
		this.image = image;
		this.title = title;
		this.resourceId = resourceId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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