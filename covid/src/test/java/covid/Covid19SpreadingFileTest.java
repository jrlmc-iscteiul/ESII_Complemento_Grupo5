package covid;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class Covid19SpreadingFileTest.
 */
class Covid19SpreadingFileTest {

	/** The covid file. */
	static Covid19SpreadingFile covidFile;

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
		covidFile.setFileName("covidSpreading.rdf");
		assertEquals(covidFile.getFileName(), "covidSpreading.rdf");

		covidFile.setFileTimestamp("13-06-2020");
		assertEquals(covidFile.getFileTimestamp(), "13-06-2020");

		covidFile.setFileTag("NovoSurto2");
		assertEquals(covidFile.getFileTag(), "NovoSurto2");

		covidFile.setTagDescription("Esta � a descri��o da tag");
		assertEquals(covidFile.getTagDescription(), "Esta � a descri��o da tag");

		covidFile.setSpreadVisualizationLink("link");
		assertEquals(covidFile.getSpreadVisualizationLink(), "link");

	}

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		covidFile = new Covid19SpreadingFile("2020-06-13", "covid19SpreadingFile", "NovoSurto",
				"Houve um novo surto de covid 19.",
				"http://visualdataweb.de/webvowl/#iri=https://github.com/vbasto-iscte/ESII1920/raw/NovoSurto/covid19spreading.rdf");
	}

	/**
	 * Test to string.
	 */
	@Test
	void testToString() {
		String string = ("Covid File: Timestamp: " + this.covidFile.getFileTimestamp() + "; Name: "
				+ this.covidFile.getFileName() + "; Tag: " + this.covidFile.getFileTag() + "; Description: "
				+ this.covidFile.getTagDescription() + "; Link: " + this.covidFile.getSpreadVisualizationLink());
		assertEquals(covidFile.toString(), string);
	}
	
	/**
	 * Test getters and setters.
	 */
	@Test
	void testGettersAndSetters() {
		covidFile.setFileName("covidSpreading.rdf");
		assertEquals(covidFile.getFileName(), "covidSpreading.rdf");

		covidFile.setFileTimestamp("13-06-2020");
		assertEquals(covidFile.getFileTimestamp(), "13-06-2020");

		covidFile.setFileTag("NovoSurto2");
		assertEquals(covidFile.getFileTag(), "NovoSurto2");

		covidFile.setTagDescription("Esta � a descri��o da tag");
		assertEquals(covidFile.getTagDescription(), "Esta � a descri��o da tag");

		covidFile.setSpreadVisualizationLink("link");
		assertEquals(covidFile.getSpreadVisualizationLink(), "link");
	}
}
