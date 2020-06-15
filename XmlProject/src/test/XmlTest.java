package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import cgi.XmlProject;

// TODO: Auto-generated Javadoc
/**
 * The Class XmlTest.
 */
class XmlTest {

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
		xmlProject = new XmlProject();
	}

	/**
	 * Tear down.
	 *
	 * @throws Exception the exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/** The xml project. */
	XmlProject xmlProject;
	
	
	/**
	 * Test.
	 */
	@Test
	void test() {
	}
	
	/**
	 * Gets the valorteste.
	 *
	 * @return the valorteste
	 */
	@Test
	void getValorteste() {
		
		assertNotEquals("ola", xmlProject.getValor(null, null, null));
	}
	
	/**
	 * Relational op valor teste.
	 */
	@Test
	void relationalOpValorTeste() {
		assertNotEquals(true, xmlProject.relationalOpValor(1, "Maior", 1));
		assertEquals(true, xmlProject.relationalOpValor(1, "Maior ou igual", 1));
	}
	
	
	/**
	 * P teste.
	 */
	void pTeste() {
		xmlProject.p();
	}
	
	/**
	 * Todas regioes que teste.
	 *
	 * @throws XPathExpressionException the x path expression exception
	 */
	void todasRegioesQueTeste() throws XPathExpressionException {
//		assertNotEquals("Algarve", xmlProject.todasRegioesQue("Internamentos", "Maior", "50", null));
		assertNotEquals(true, xmlProject.todasRegioesQue("Internamentos", "Maior", "50", null));
	}
	
	/**
	 * Pull teste.
	 */
	void pullTeste() {
		xmlProject.pull();
	}
	
	/**
	 * Juntar listas teste.
	 */
	void juntarListasTeste() {
		List<String> n = new ArrayList<>();
		List<String> n1 = new ArrayList<>();
		List<String> n2 = new ArrayList<>();
		assertEquals(n2, xmlProject.juntarListas(n, n1));
	}

	/**
	 * Aceder file teste.
	 *
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws SAXException the SAX exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	void acederFileTeste() throws ParserConfigurationException, SAXException, IOException {
		assertNotEquals(null, xmlProject.acederFile());
	}
	
	/**
	 * Test main.
	 *
	 * @throws XPathExpressionException the x path expression exception
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws SAXException the SAX exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	void testMain() throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		xmlProject.main(null);
	}

}
