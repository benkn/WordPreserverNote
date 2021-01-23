package ben.kn.wordpress;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class WordpressExportReaderTest {

	@Test
	public void testRead() throws Exception {
		WordpressExportReader reader = new WordpressExportReader("data/singleWordpressPost.xml");

		List<Post> posts = reader.read();
		assertTrue(posts != null && posts.size() > 0);
	}
}