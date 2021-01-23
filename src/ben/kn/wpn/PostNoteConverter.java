package ben.kn.wpn;

import java.util.Date;
import java.util.HashSet;

import ben.kn.evernote.Note;
import ben.kn.wordpress.Post;

public class PostNoteConverter {

	public static Note convertPostToNote(Post post, boolean includeCategoriesAsTags) {
		Note note = new Note();

		note.setTitle(post.getTitle());
		note.setContent(post.getContent());
		note.setCreated(post.getPubDate());
		note.setUpdated(new Date());
		note.setTags(post.getTags());

		if ( includeCategoriesAsTags )
			note.getTags().addAll(post.getCategories());

		return note;
	}

	public static Post convertNoteToPost(Note note) {
		Post post = new Post();

		post.setTitle(note.getTitle());
		post.setContent(note.getContent());
		post.setTags(note.getTags());
		post.setCategories(new HashSet<String>());
		post.getCategories().add(note.getJournal());
		post.setPubDate(note.getCreated());

		return post;
	}
}
