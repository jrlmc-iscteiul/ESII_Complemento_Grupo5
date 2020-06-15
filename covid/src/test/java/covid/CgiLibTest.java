package covid;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class CgiLibTest.
 */
class CgiLibTest {
	
	cgi_lib cl;

	/**
	 * Sets the up before class.
	 *
	 * @throws Exception the exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Tear down after class.
	 *
	 * @throws Exception the exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		cl = new cgi_lib();
	}
	
	/**
	 * Test url decode.
	 */
	@Test
	void testUrlDecode() { 
		assertEquals("ola ola", cgi_lib.urlDecode("ola+ola"));	
	}

	/**
	 * Test header.
	 */
	@Test
	void testHeader() {
		assertEquals("Content-type: text/html\n\n", cgi_lib.Header());
	}
	
	/**
	 * Test html top.
	 */
	@Test
	void testHtmlTop() {
		String expected = new String("<html>\n" + 
				"<head>\n" + 
				"<title>\n" + 
				"Hello\n" + 
				"</title>\n" + 
				"</head>\n" + 
				"<body>\n");
		assertEquals(expected, cgi_lib.HtmlTop("Hello"));
	}
	
	/**
	 * Test html bot.
	 */
	@Test
	void testHtmlBot() {
		assertEquals("</body>\n</html>\n", cgi_lib.HtmlBot());
	}
	
	/**
	 * Test meth get.
	 */
	@Test
	void testMethGet() {
		assertEquals(false, cgi_lib.MethGet());
	}
	
	/**
	 * Test meth post.
	 */
	@Test
	void testMethPost() {
		assertEquals(false, cgi_lib.MethPost());
	}
	
	/**
	 * Test environment.
	 */
	@Test
	void testEnvironment() {
		String expected = new String("<dl compact>\n" + 
				"<dt><b>CONTENT_TYPE</b> <dd>:<i>null</i>:<br>\n" + 
				"<dt><b>CONTENT_LENGTH</b> <dd>:<i>null</i>:<br>\n" + 
				"<dt><b>REQUEST_METHOD</b> <dd>:<i>null</i>:<br>\n" + 
				"<dt><b>QUERY_STRING</b> <dd>:<i>null</i>:<br>\n" + 
				"<dt><b>SERVER_NAME</b> <dd>:<i>null</i>:<br>\n" + 
				"<dt><b>SERVER_PORT</b> <dd>:<i>null</i>:<br>\n" + 
				"<dt><b>SCRIPT_NAME</b> <dd>:<i>null</i>:<br>\n" + 
				"<dt><b>PATH_INFO</b> <dd>:<i>null</i>:<br>\n" + 
				"</dl>\n");
		assertEquals(expected, cgi_lib.Environment());
	}
}
