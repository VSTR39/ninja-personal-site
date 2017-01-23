package entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class articles {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	@NotNull
	@Column(name = "author_id")
	Long author_id;
	@NotNull
	@Column(name = "section_id")
	Long section_id;
	@NotNull
	@Column(name = "timestamp")
	Timestamp timestamp;
	@NotNull
	@Column(name = "title")
	String title;
	@NotNull
	@Column(name = "content")
	String content;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(Long author_id) {
		this.author_id = author_id;
	}

	public Long getSection_id() {
		return section_id;
	}

	public void setSection_id(Long section_id) {
		this.section_id = section_id;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
