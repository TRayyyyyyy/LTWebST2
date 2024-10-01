package LTWebST2.Models;

public class CategoryModel {
	private int categoryid;
	private String categoryname;
	private String images;
	private int status;
	
	public CategoryModel() {
		super();
	}
	public int getcategoryid() {
		return categoryid;
	}
	public void setcategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	public String getcategoryname() {
		return categoryname;
	}
	public void setcategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

}
