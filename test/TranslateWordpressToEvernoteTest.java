

import static org.junit.Assert.*;

import org.junit.Test;

public class TranslateWordpressToEvernoteTest {

	@Test
	public void testMain() {
		TranslateWordpressToEvernote.main(new String[] { "data/singleWordpressPost.xml",
				"data/twte-output.xml" });

		assertTrue(true);
	}

}
