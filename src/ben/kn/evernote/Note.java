package ben.kn.evernote;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * The equivalent of a note in Evernote
 * 
 * @author Ben (bknear@gmail.com)
 */
public class Note {
	private String title;
	private String content;
	private String journal;
	private Date updated;
	private Date created;
	private Set<String> tags;
	private Map<String, String> noteAttributes;

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

	public String getJournal() {
		return journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Map<String, String> getNoteAttributes() {
		return noteAttributes;
	}

	public void setNoteAttributes(Map<String, String> noteAttributes) {
		this.noteAttributes = noteAttributes;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

}
