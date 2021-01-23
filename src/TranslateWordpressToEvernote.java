import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ben.kn.evernote.EvernoteImportWriter;
import ben.kn.evernote.Note;
import ben.kn.wordpress.Post;
import ben.kn.wordpress.WordpressExportReader;
import ben.kn.wpn.PostNoteConverter;

public class TranslateWordpressToEvernote {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if ( args == null || args.length != 2 ) {
			System.out.println("Incorrect parameters provided. Usage: "
					+ TranslateWordpressToEvernote.class.getName()
					+ " <wordpress export file> <evernote filename ending in .enex>");
			System.out.println("You only provided " + args.length);
			System.exit(1);
		}

		WordpressExportReader reader = new WordpressExportReader(args[0]);
		EvernoteImportWriter writer = new EvernoteImportWriter(args[1]);

		List<Post> posts = null;
		try {
			posts = reader.read();
		} catch (Exception e) {
			System.out.println("Failed to retrieve the posts from the given wordpress file: "
					+ e.getLocalizedMessage());
			System.exit(1);
		}

		List<Note> notes = new ArrayList<Note>();
		for ( Post post : posts ) {
			Note note = PostNoteConverter.convertPostToNote(post, true);
			notes.add(note);
		}

		System.out.println("Writing " + notes.size() + " notes.");

		try {
			writer.write(notes);
		} catch (IOException e) {
			System.out.println("Failed to write the notes to the evernote archive: "
					+ e.getLocalizedMessage());
			System.exit(1);
		}

		System.out.println("Finished!");

		System.exit(0);
	}
}