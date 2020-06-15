package cgi;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import javax.xml.parsers.DocumentBuilder;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jgit.api.Git;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XmlProject {
	
	static String result;
	
	private static String getValor(String regiao, String campo, Document doc) {
		try {
			XPathFactory xpathFactory = XPathFactory.newInstance();
			XPath xpath = xpathFactory.newXPath();
			String query = "//*[contains(@about,'" + regiao + "')]/" + campo + "/text()";
			XPathExpression expr = xpath.compile(query);
			result = (String) expr.evaluate(doc, XPathConstants.STRING);
		} catch (Exception e) {

		}
		return result;
	}
	
	private static boolean relationalOpValor(int valor, Object relationalOp, int val) {
		if(relationalOp.equals("Igual")) {
			return valor == val;
		} else if(relationalOp.equals("Diferente")) {
			return valor != val;
		} else if(relationalOp.equals("Menor")) {
			return valor < val;
		} else if(relationalOp.equals("Maior")) {
			return valor > val;
		} else if(relationalOp.equals("Menor ou igual")) {
			return valor <= val;
		} else if(relationalOp.equals("Maior ou igual")) {
			return valor >= val;
		}
		return false;
	}
	
	//Saber o valor de todas as regioes onde ...
	private static List<String> todasRegioesQue(Object campo, Object relationalOp, Object val, Document doc) throws XPathExpressionException {
		List<String> regs = new ArrayList<>();
		String query = "/RDF/NamedIndividual/@*";
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		XPathExpression expr = xpath.compile(query);
		NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		for (int i = 0; i < nl.getLength(); i++) {
			String regiao = StringUtils.substringAfter(nl.item(i).getNodeValue(), "#");
			String v1 = getValor(regiao, (String) campo, doc);
			int v2 = Integer.parseInt((String) val);
			if(!v1.contentEquals("")) {
				if(relationalOpValor(Integer.parseInt(v1), relationalOp, v2)) {
					regs.add(regiao);
				}  
			}	
		}
		return regs;
	}
	
	private static int sumRegioes(Object campo, Document doc) throws XPathExpressionException {
		int count = 0;
		String query = "/RDF/NamedIndividual/@*";
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		XPathExpression expr = xpath.compile(query);
		NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		for (int i = 0; i < nl.getLength(); i++) {
			String regiao = StringUtils.substringAfter(nl.item(i).getNodeValue(), "#");
			String v1 = getValor(regiao, (String) campo, doc);
			if(!v1.contentEquals("")) {
				int j = Integer.parseInt(v1);
				count = count + j;
			}
		}
		return count;
	}
	
	private static void regioesPorCampo(Object campo, Document doc) throws XPathExpressionException {
		String query = "/RDF/NamedIndividual/@*";
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		XPathExpression expr = xpath.compile(query);
		NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		for (int i = 0; i < nl.getLength(); i++) {
			String regiao = StringUtils.substringAfter(nl.item(i).getNodeValue(), "#");
			System.out.println(regiao + "(" + getValor(regiao, (String) campo, doc) + ") ");
		}
	}
	
	private static Document acederFile() throws ParserConfigurationException, SAXException, IOException {
		File inputFile = new File("covid19spreading.rdf");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(inputFile);
		doc.getDocumentElement().normalize();
		return doc;
	}
	
	private static void pull() {
		try {
			Git git;
			File file = new File("cloneESII");
			if (file.isDirectory() && file.list().length == 0) {
				git = Git.cloneRepository().setURI("https://github.com/vbasto-iscte/ESII1920.git").setDirectory(file).call();
			} else {
				git = Git.open(file);
				git.pull();
				git.checkout();
			}
		} catch (Exception e) {

		}
	}
	
	private static List<String> juntarListas(List<String> l1, List<String> l2) {
		List<String> conj = new ArrayList<>();
		for(String s : l1) {
			conj.add(s);
		}
		for (String s : l2) {
			conj.add(s);
		}
		return conj;
	}
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {

		System.out.println(cgi_lib.Header());
		
		pull();

		Hashtable form_data = cgi_lib.ReadParse(System.in);

		String campo1 = (String) form_data.get("campo1");
		String regiao1 = (String) form_data.get("regiao1");
		String relationalOp1 = (String) form_data.get("relationalOp1");
		String val1 = (String) form_data.get("val1");

		String campo2 = (String) form_data.get("campo2");
		String regiao2 = (String) form_data.get("regiao2");
		String relationalOp2 = (String) form_data.get("relationalOp2");
		String val2 = (String) form_data.get("val2");

		// Caso1
		if (!form_data.get("ops").equals("-") && form_data.get("campo1").equals(form_data.get("campo2")) && form_data.get("regiao1").equals(form_data.get("regiao2"))) {

		}
		// Caso2
		if (!form_data.get("ops").equals("-") && form_data.get("regiao1").equals(form_data.get("regiao2")) && form_data.get("regiao1").equals("Todas")) {
			if(form_data.get("ops").equals("and")) {
				List<String> q2 = todasRegioesQue(campo2, relationalOp2, val2, acederFile());
				List<String> q1 = todasRegioesQue(campo1, relationalOp1, val1, acederFile());
				System.out.println("Regioes que correspondem a query: ");
				for (String s : juntarListas(q1, q2)) {
					System.out.println(s);
				}
			} else if (form_data.get("ops").equals("or")) {
				
			} else if (form_data.get("ops").equals("not")) {
				
			}
		}

		if (form_data.get("q1")!= null && form_data.get("ops").equals("-")) {
			// Query 1
			// Tipo1
			if (val1.equals("") && regiao1.equals("Todas")) {
				System.out.println(campo1 + " por regiao: ");
				regioesPorCampo(campo1, acederFile());
			}
			// Tipo2
			if (!val1.equals("") && regiao1.equals("Todas")) {
				List<String> regs = todasRegioesQue(campo1, relationalOp1, val1, acederFile());
				System.out.println("Regioes onde o campo: " + campo1 + " corresponde a query: ");
				for (String s : regs) {
					System.out.println(s);
				}
			}
			// Tipo3
			if (val1.equals("") && !regiao1.equals("Todas")) {
				System.out.println("Numero de " + form_data.get("campo1") + " para a regiao " + form_data.get("regiao1") + ": " + getValor((String) form_data.get("regiao1"), (String) form_data.get("campo1"), acederFile()));
			}
			// Tipo4
			if (!val1.equals("") && !regiao1.equals("Todas")) {
				if (relationalOpValor(Integer.parseInt(getValor(regiao1, campo1, acederFile())), relationalOp1, Integer.parseInt(val1))) {
					System.out.println("A query e verdadeira");
				} else {
					System.out.println("A query e falsa");
				}
			}
		}
		System.out.println("<p>");

		if (form_data.get("q2")!=null) {
			// Query 2
			// Tipo1
			if (val2.equals("") && regiao2.equals("Todas")) {
				System.out.println(campo2 + " por regiao: ");
				regioesPorCampo(campo2, acederFile());
			}
			// Tipo2
			if (!val2.equals("") && regiao2.equals("Todas")) {
				List<String> regs = todasRegioesQue(campo2, relationalOp2, val2, acederFile());
				System.out.println("Regioes onde o campo: " + campo2 + " corresponde a query: ");
				for (String s : regs) {
					System.out.println(s);
				}
			}
			// Tipo3
			if (val2.equals("") && !regiao2.equals("Todas")) {
				System.out.println("Numero de " + form_data.get("campo2") + " para a regiao " + form_data.get("regiao2") + ": " + getValor((String) form_data.get("regiao2"), (String) form_data.get("campo2"), acederFile()));
			}
			// Tipo4
			if (!val2.equals("") && !regiao2.equals("Todas")) {
				if (relationalOpValor(Integer.parseInt(getValor(regiao2, campo2, acederFile())), relationalOp2, Integer.parseInt(val2))) {
					System.out.println("A query e verdadeira");
				} else {
					System.out.println("A query e falsa");
				}
			}
		}
		
		System.out.println("<p>");

		//SUM query
		if (form_data.get("q3")!=null) {
			System.out.println("Soma dos " + form_data.get("campo3") + ": " + sumRegioes(form_data.get("campo3"), acederFile()));
		}
		
		if (!(form_data.get("q1")!=null) && !(form_data.get("q2")!=null) && !(form_data.get("q3")!=null)) {
			System.out.println("<p style=\"text-align:center;font-size:160%;\"><strong></p>Nenhuma query selecionada</strong></p>");
		}


//		System.out.println("</BODY>");
//		System.out.println("</HTML>");

		System.out.println(cgi_lib.HtmlBot());
	}
   
}
