package ben.kn.evernote;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * Write a batch of Notes to a file in the expected Evernote format.
 * 
 * @author Ben (bknear@gmail.com)
 */
public class EvernoteImportWriter {

	private String filename;
	// 20120709T205136Z
	private final DateFormat DF = new SimpleDateFormat("yyyyMMdd'T'kmmss'Z'");
	private final String TOP_OF_FILE = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
			+ "<!DOCTYPE en-export SYSTEM \"http://xml.evernote.com/pub/evernote-export2.dtd\">\n"
			+ "<en-export export-date=\"%s\" application=\"Evernote\" version=\"Evernote Mac 3.1.2 (255119)\">\n";
	private final String END_OF_FILE = "\n</en-export>";

	public EvernoteImportWriter(String filename) {
		this.filename = filename;
	}

	public void write(Collection<Note> notes) throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format(TOP_OF_FILE, DF.format(new Date())));

		for ( Note note : notes ) {
			populateStringBuilderWithNoteXML(note, sb);
		}

		sb.append(END_OF_FILE);

		// write out to file
		FileWriter writer = new FileWriter(filename);
		writer.write(sb.toString());
		writer.flush();
		writer.close();
	}

	private void populateStringBuilderWithNoteXML(Note note, StringBuilder sb) {
		String title;
		if ( note.getTitle() != null )
			title = note.getTitle().replaceAll("&", " and ");
		else
			title = "";
		sb.append("\n<note><title>").append(title).append("</title>");

		String content = note.getContent();
		content = content.replaceAll("\n", "<br/>\n");
		content = content.replaceAll("Ê", ""); // remove this weird hidden
												// object (it's not a space!)
		sb.append("<content><![CDATA[<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("\n<!DOCTYPE en-note SYSTEM \"http://xml.evernote.com/pub/enml2.dtd\">");
		sb.append("\n<en-note>").append(content).append("</en-note>]]></content>");

		sb.append("<created>").append(DF.format(note.getCreated())).append("</created>");
		sb.append("<updated>").append(DF.format(note.getUpdated())).append("</updated>");
		if ( note.getTags() != null ) {
			for ( String tag : note.getTags() ) {
				sb.append("<tag>").append(tag).append("</tag>");
			}
		}
		Map<String, String> attributes = note.getNoteAttributes();
		if ( attributes != null && attributes.size() > 0 ) {
			sb.append("<note-attributes>");
			for ( String key : attributes.keySet() ) {
				sb.append("<").append(key).append(">").append(attributes.get(key));
				sb.append("</").append(key).append(">");
			}
			sb.append("</note-attributes>");
		}
		sb.append("<note-attributes><source>mobile.iphone</source></note-attributes></note>");
	}
}
