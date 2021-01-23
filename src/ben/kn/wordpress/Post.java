package ben.kn.wordpress;

import java.util.Date;
import java.util.Set;

/**
 * The equivalent of a post in Wordpress.
 * 
 * @author Ben (bknear@gmail.com)
 */
public class Post {
	private String title; // title
	private String link; // link
	private String creator; // dc:creator
	private String description; // description
	private String content; // content:encoded
	private Date pubDate; // pubDate
	private Set<String> categories; // category domain="category"
	private Set<String> tags; // category domain="post_tag"

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set<String> getCategories() {
		return categories;
	}

	public void setCategories(Set<String> categories) {
		this.categories = categories;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}
}
