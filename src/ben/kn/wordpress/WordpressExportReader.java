package ben.kn.wordpress;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WordpressExportReader {
	private String filename;
	// 2011-02-11 23:04:14
	private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");

	public WordpressExportReader(String filename) {
		this.filename = filename;
	}

	public List<Post> read() throws Exception {
		List<Post> posts = new ArrayList<Post>();

		File file = new File(filename);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(file);
		doc.getDocumentElement().normalize();

		NodeList nodeLst = doc.getElementsByTagName("item");

		System.out.println("Found " + nodeLst.getLength() + " items.");

		for ( int s = 0; s < nodeLst.getLength(); s++ ) {

			System.out.println("Working on item " + s);
			Node fstNode = nodeLst.item(s);

			Post post = new Post();

			// with this element, get the attributes we want to populate the
			// object
			if ( fstNode.getNodeType() == Node.ELEMENT_NODE ) {
				// String values
				post.setTitle(getValue((Element) fstNode, "title"));
				post.setLink(getValue((Element) fstNode, "link"));
				post.setCreator(getValue((Element) fstNode, "dc:creator"));
				post.setDescription(getValue((Element) fstNode, "description"));
				post.setContent(getValue((Element) fstNode, "content:encoded"));

				// Date values
				post.setPubDate(dateFormat.parse((getValue((Element) fstNode, "wp:post_date"))));

				post.setTags(getValues((Element) fstNode, "category", "domain", "post_tag"));
				post.setCategories(getValues((Element) fstNode, "category", "domain", "category"));
			}

			posts.add(post);
		}

		return posts;
	}

	/**
	 * Get the value of the given tag for the Element
	 * 
	 * @param fstElmnt Element
	 * @param tagName String
	 * @return String of the value
	 */
	private String getValue(Element fstElmnt, String tagName) {
		// should be just zero
		NodeList matchingElements = fstElmnt.getElementsByTagName(tagName);

		if ( matchingElements.getLength() > 0 ) {
			Element fstNmElmnt = (Element) matchingElements.item(0);
			NodeList fstNm = fstNmElmnt.getChildNodes();

			if ( fstNm.getLength() > 0 ) {

				// System.out.println("Found " + ((Node)
				// fstNm.item(0)).getNodeValue() + " for "
				// + tagName);
				return ((Node) fstNm.item(0)).getNodeValue();
			}
		}
		return null;
	}

	private Set<String> getValues(Element element, String tagName, String discriminator,
			String discrimValue) {
		Set<String> values = new HashSet<String>();

		NodeList matchingElements = element.getElementsByTagName(tagName);
		for ( int i = 0; i < matchingElements.getLength(); i++ ) {
			Element matchingElement = (Element) matchingElements.item(i);

			if ( discrimValue.equals(matchingElement.getAttribute(discriminator)) ) {

				NodeList fstNm = matchingElement.getChildNodes();
				if ( fstNm.getLength() > 0 ) {
					// System.out.println("Found " + ((Node)
					// fstNm.item(0)).getNodeValue() + " for "
					// + tagName + " with " + discrimValue + " for its " +
					// discriminator);
					values.add(((Node) fstNm.item(0)).getNodeValue());
				}
			}
		}
		return values;
	}
}
