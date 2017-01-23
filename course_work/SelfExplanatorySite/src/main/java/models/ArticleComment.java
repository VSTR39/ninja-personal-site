package models;

public class ArticleComment {
	String comment;
	long articleID;

	public long getArticleID() {
		return articleID;
	}

	public void setArticleID(long articleID) {
		this.articleID = articleID;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
