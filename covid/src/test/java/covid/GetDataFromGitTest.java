package covid;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class GetDataFromGitTest.
 */
class GetDataFromGitTest {
	
	/** The get data from git. */
	GetDataFromGit getDataFromGit;
	/*Covid19SpreadingFile covidFile;
	HTMLTable table;
	File file;
	List<Covid19SpreadingFile> listCovids;*/

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
		getDataFromGit = new GetDataFromGit();
	}

	/**
	 * Test use git rpository.
	 */
	@Test
	void testUseGitRpository() {
		String actual = null;
		
		String expected = new String("<p style=\"text-align:center;font-size:160%;\"><strong>Tags do ficheiro covid19spreading.rdf no GitHub</strong></p>" +
				"<style type=\"text/css\">.tg  {border-collapse:collapse;border-spacing:0;}.tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;  overflow:hidden;padding:10px 5px;word-break:normal;}.tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;  font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}.tg .tg-fkac{background-color:#ffce93;font-size:12px;text-align:center;vertical-align:top}.tg .tg-wus8{background-color:#ffce93;border-color:inherit;color:#000000;font-size:12px;font-style:italic;text-align:center;  vertical-align:top}.tg .tg-ltnr{background-color:#ffce93;border-color:inherit;color:#000000;font-size:12px;text-align:center;vertical-align:top}.tg .tg-0lax{text-align:left;vertical-align:top}.tg .tg-0pky{border-color:inherit;text-align:left;vertical-align:top}</style><table class=\"tg\" style=\"undefined;table-layout: fixed; width: 751px\">	<colgroup><col style=\"width: 280px\"><col style=\"width: 280px\"><col style=\"width: 280px\"><col style=\"width: 380px\"><col style=\"width: 280px\"></colgroup>	<thead><tr><th class=\"tg-fkac\">File Timestamp</th><th class=\"tg-wus8\">File Name</th><th class=\"tg-ltnr\">File Tag</th><th class=\"tg-ltnr\">Tag Description</th><th class=\"tg-ltnr\">Spread Visualization Link</th></tr>	</thead><tbody><tr> \n" + 
				"<td class=\"tg-0lax\">26/mai/2020 12:52:32</td><td class=\"tg-0lax\">covid19spreading.rdf</td> \n" + 
				"<td class=\"tg-0lax\">NovoSurto</td> \n" + 
				"<td class=\"tg-0lax\">Detetado novo surto</td> \n" + 
				"<td class=\"tg-0lax\"><a href=\"http://visualdataweb.de/webvowl/#iri=https://github.com/vbasto-iscte/ESII1920/raw/NovoSurto/covid19spreading.rdf\">Covid19Spreading</a></td> \n" + 
				"</tr> \n" + 
				"<tr> \n" + 
				"<td class=\"tg-0lax\">26/mai/2020 12:15:47</td><td class=\"tg-0lax\">covid19spreading.rdf</td> \n" + 
				"<td class=\"tg-0lax\">TestesGeracaoII</td> \n" + 
				"<td class=\"tg-0lax\">Introdução dos novos testes de geração II\n" + 
				"\n" + 
				"Introdução dos novos testes de geração II - Resultados em 5 horas.</td> \n" + 
				"<td class=\"tg-0lax\"><a href=\"http://visualdataweb.de/webvowl/#iri=https://github.com/vbasto-iscte/ESII1920/raw/TestesGeracaoII/covid19spreading.rdf\">Covid19Spreading</a></td> \n" + 
				"</tr> \n" + 
				"<tr> \n" + 
				"<td class=\"tg-0lax\">26/mai/2020 12:58:03</td><td class=\"tg-0lax\">covid19spreading.rdf</td> \n" + 
				"<td class=\"tg-0lax\">TestesGeracaoIII</td> \n" + 
				"<td class=\"tg-0lax\">Update covid19spreading.rdf</td> \n" + 
				"<td class=\"tg-0lax\"><a href=\"http://visualdataweb.de/webvowl/#iri=https://github.com/vbasto-iscte/ESII1920/raw/TestesGeracaoIII/covid19spreading.rdf\">Covid19Spreading</a></td> \n" + 
				"</tr> \n" + 
				"</tbody></table>");
		
		try {
			actual = getDataFromGit.useGitRepository();
			System.out.println(actual);
		} catch (GitAPIException | IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(expected, actual);
	}
	
	/**
	 * Test main.
	 */
	@Test
	void testMain() {
		getDataFromGit.main(null);		
	}

}
