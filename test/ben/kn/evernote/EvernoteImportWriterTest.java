package ben.kn.evernote;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class EvernoteImportWriterTest {

	@Test
	public void testWrite() throws IOException {
		EvernoteImportWriter writer = new EvernoteImportWriter("data/test-evernote-output.xml");

		Note note = new Note();
		note.setTitle("Test title");
		note.setContent("Long string containing content of various nature. <p>This would be a paragraph</p>");
		note.setCreated(new Date(System.currentTimeMillis() - 86400000));
		note.setUpdated(new Date());
		Set<String> tags = new HashSet<String>();
		tags.add("test tag1");
		tags.add("tag2 test");
		note.setTags(tags);

		Set<Note> notes = new HashSet<Note>();
		notes.add(note);

		writer.write(notes);
	}

}
