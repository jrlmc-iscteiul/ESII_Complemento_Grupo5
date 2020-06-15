package covid;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.URL;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

//import javax.xml.ath.ath;
//import javax.xml.ath.athEressionException;
//import javax.xml.ath.athFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.diff.CommandVisitor;
import org.apache.commons.text.diff.StringsComparator;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.errors.AmbiguousObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.lib.AnyObjectId;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class CovidEvolutionDiff {

//	List<String> rdfFiles = new ArrayList<>();
	private static  String StringFinalE;
	private List<Covid19SpreadingFile> ficheiros = new ArrayList<>(); // Lista para guardar a informação dos ficherios
	
	/**
	 * Use git repository.
	 *
	 * @throws InvalidRemoteException the invalid remote exception
	 * @throws TransportException the transport exception
	 * @throws GitAPIException the git API exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void useGitRepository() throws InvalidRemoteException, TransportException, GitAPIException, IOException {
		//		String URI = "https://github.com/vbasto-iscte/ESII1920";
		File directory = new File("directory");
		Git git;
		//-------------------------------------

		if (directory.isDirectory() && directory.list().length == 0) {	//se diretorio estiver vazio(?) --> vai clona lo no link dado
			git = Git.cloneRepository().setURI("https://github.com/vbasto-iscte/ESII1920.git").setDirectory(directory).call();
		} else {	//se nao vai abri lo
			git = Git.open(directory);
			git.pull();
			git.checkout();
		}

		//deleteDir(directory);


		Repository repository = git.getRepository();

		List<Ref> listTags = git.tagList().call(); // listar as tags que ha no repositorio

		for (Ref ref : listTags) { // percorre a lista dos tags

			//	System.out.println("Tag: " + ref.getName());
			ObjectId tagFile = repository.resolve(ref.getName());

			// o RevWalk permite andar em commits baseado numa filtragem qlq definida
			try (RevWalk revWalk = new RevWalk(repository)) {

				RevCommit commit = revWalk.parseCommit(tagFile);
				RevTree tree = commit.getTree();

				// try para encontrar um file especifico
				try (TreeWalk treeWalk = new TreeWalk(repository)) {
					treeWalk.addTree(tree);
					treeWalk.setRecursive(true);
					treeWalk.setFilter(PathFilter.create("covid19spreading.rdf"));

					if (!treeWalk.next()) {
						throw new IllegalStateException("Did not find eected file 'covid19spreading.rdf'");

					}

					ObjectId objectId = treeWalk.getObjectId(0);
					ObjectLoader loader = repository.open(objectId);
					// Covid19SpreadingFile covid = new Covid19SpreadingFile(commit.getAuthorIdent().getWhen(), loader);

					ficheiros.add(new Covid19SpreadingFile(commit.getAuthorIdent().getWhen(), loader));

					// (e depois o loader pode ler o ficheiro (permite me ler o file de um commit))
					// loader.copyTo(System.out);

				}

			} catch (IOException e) {
				e.printStackTrace();

			}
		}

		//System.out.println(ficheiros.size());
		//System.out.println(ficheiros);

		compareDates(ficheiros);

	}
	
	

	public  String getStringFinalE() {
		return StringFinalE;
	}



	public  String setStringFinalE(String stringFinalE) {
		return StringFinalE = stringFinalE;
	}



	public List<Covid19SpreadingFile> getFicheiros() {
		return ficheiros;
	}



	public void setFicheiros(List<Covid19SpreadingFile> ficheiros) {
		this.ficheiros = ficheiros;
	}



	public List<Covid19SpreadingFile> compareDates(List<Covid19SpreadingFile> ficheiros) throws MissingObjectException, IOException {

		List<Covid19SpreadingFile> listaFinal = new ArrayList<>();
		// listaFinal.add(ficheiros.get(0));
		Covid19SpreadingFile covid2 = ficheiros.get(0);
		Integer index = 0;

		for (Covid19SpreadingFile c : ficheiros) {
			if (c.getTm().after(covid2.getTm())) {
				covid2 = c;
				index = ficheiros.indexOf(c);
			}
		}

		ficheiros.remove(ficheiros.get(index));
		Covid19SpreadingFile covid3 = ficheiros.get(1);

		for (Covid19SpreadingFile c : ficheiros) {
			if (c.getTm().after(covid3.getTm())) {
				covid3 = c;
			}
		}
		listaFinal.add(covid2);
		listaFinal.add(covid3);

		//colocar ObjectLoaders numa String (2 commits mais recentes) 
		String s0 = new String(listaFinal.get(0).getOl().getBytes()).trim();
		String s1 = new String(listaFinal.get(1).getOl().getBytes()).trim();

		//chamar a função createFiles com strings acima (files que aparecem do lado esq com commits)

		//createFiles (s0, s1);




		//-------------------Para comparar diferenças:

		// Create a diff comparator with two inputs strings.
		StringsComparator comparator = new StringsComparator(s0, s1);


		// Initialize custom visitor and visit char by char.
		MyCommandsVisitor myCommandsVisitor = new MyCommandsVisitor();
		comparator.getScript().visit(myCommandsVisitor);

		//String com a comparação final
		StringFinalE = ("FINAL DIFF = 1ªSTRING"  + myCommandsVisitor.left + " ----------------2ªSTRING------------------------- " + myCommandsVisitor.right );
		
		//System.out.println(StringFinalE);

		return listaFinal;	//devolve lista de commits finais


	}


	public boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i=0; i<children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		return dir.delete();
	}




	//--------------------------Class para Comparara duas strings 

	class MyCommandsVisitor implements CommandVisitor<Character> {

		String left = "";
		String right = "";
		

		@Override
		public void visitKeepCommand(Character c) {
			// Character is present in both files.
			left = left + c;
			right = right + c;
		}

		@Override
		public void visitInsertCommand(Character c) {
			/*
			 * Character is present in right file but not in left. Method name
			 * 'InsertCommand' means, c need to insert it into left to match right.
			 */
			right = right + "(" + c + ")";
		}

		@Override
		public void visitDeleteCommand(Character c) {
			/*
			 * Character is present in left file but not right. Method name 'DeleteCommand'
			 * means, c need to be deleted from left to match right.
			 */
			left = left + "{" + c + "}";
		}



	}


//	public static void main(String[] args) {
//		GetDataFromGit gd = new GetDataFromGit();
//		try {
//			gd.useGitRepository();
//		} catch (InvalidRemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (TransportException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (GitAPIException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.println(gd.getStringFinalE());
//	}


	



	/**
 * The main method.
 *
 * @param args the arguments
 */
public static void main(String[] args) {

		//System.out.println("Content-type: text/html\n\n");
		System.out.println("Content-type: text/html\n\n");


		CovidEvolutionDiff gdfg = new CovidEvolutionDiff();
		//		String table = "FINAL DIFF = xml version=\"1.0" ;


//		System.out.println(cgi_lib.Header());

		try {
			gdfg.useGitRepository();

		} catch (GitAPIException | IOException e) {
			System.out.println("Não foi possivel ligar ao repositorio" + e);
		}

		//System.out.println(cgi_lib.HtmlTop("Diferencaaas"));	//isto e o titulo

		
		String Nota = "(Nota: Na 1ª String os caracteres diferentes entre este commit e o seguinte encontram-se entre chavetas ({}), os que são iguais, estão representados normalmente.\n"
				+ "O mesmo acontece na 2ª String, no entanto os caracteres diferentes encontram-se especificados entre parenteses ) ";

		System.out.println( Nota + " \n" + "<xmp>" + StringFinalE + "</xmp>");



//		System.out.println(cgi_lib.HtmlBot());
	}
}



