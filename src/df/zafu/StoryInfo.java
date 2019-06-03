package df.zafu;

public class StoryInfo {
	  private int storyid;
	  private String storytitle;
	  private String storyplace;
	  private String storycontent;
	  private String storyimg;
	  private int authorid;
	  private String authorimg;
	  private String authorname;
	public StoryInfo(int storyid, String storytitle, String storyplace, String storycontent, String storyimg,
			int authorid, String authorimg, String authorname) {
		super();
		this.storyid = storyid;
		this.storytitle = storytitle;
		this.storyplace = storyplace;
		this.storycontent = storycontent;
		this.storyimg = storyimg;
		this.authorid = authorid;
		this.authorimg = authorimg;
		this.authorname = authorname;
	}
	public int getStoryid() {
		return storyid;
	}
	public void setStoryid(int storyid) {
		this.storyid = storyid;
	}
	public String getStorytitle() {
		return storytitle;
	}
	public void setStorytitle(String storytitle) {
		this.storytitle = storytitle;
	}
	public String getStoryplace() {
		return storyplace;
	}
	public void setStoryplace(String storyplace) {
		this.storyplace = storyplace;
	}
	public String getStorycontent() {
		return storycontent;
	}
	public void setStorycontent(String storycontent) {
		this.storycontent = storycontent;
	}
	public String getStoryimg() {
		return storyimg;
	}
	public void setStoryimg(String storyimg) {
		this.storyimg = storyimg;
	}
	public int getAuthorid() {
		return authorid;
	}
	public void setAuthorid(int authorid) {
		this.authorid = authorid;
	}
	public String getAuthorimg() {
		return authorimg;
	}
	public void setAuthorimg(String authorimg) {
		this.authorimg = authorimg;
	}
	public String getAuthorname() {
		return authorname;
	}
	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}
	

}
