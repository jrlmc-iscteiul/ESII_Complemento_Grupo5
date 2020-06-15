package covid;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;

// TODO: Auto-generated Javadoc
/**
 * The Class GetDataFromGit.
 *
 * @author Joana Cavalheiro
 * @since 13/06/2020
 */
public class GetDataFromGit {

	/** The rdf files. */
	List<String> rdfFiles = new ArrayList<>();
	
	/** The table. */
	HTMLTable table = new HTMLTable();

	/**
	 * 
	 * Is used to make a clone of the GitHub repository and access rdf files that have associated 
	 * tags and their commits to remove the information needed to create the table.
	 *
	 * @return the string that have the html table
	 * @throws InvalidRemoteException the invalid remote exception
	 * @throws TransportException the transport exception
	 * @throws GitAPIException the git API exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * 
	 */
	public String useGitRepository() throws InvalidRemoteException, TransportException, GitAPIException, IOException {

		Git git;
		File file = new File("cloneESII");
	 
		String tableString = null;

		if (file.isDirectory() && file.list().length == 0) {
			git = Git.cloneRepository().setURI("https://github.com/vbasto-iscte/ESII1920.git").setDirectory(file).call();
		} else {
			git = Git.open(file);
			git.pull();
			git.checkout();
		}

		Repository repository = git.getRepository();

		List<Ref> listTags = git.tagList().call();
		List<Covid19SpreadingFile> listCovid19SpreadingFile = new ArrayList<Covid19SpreadingFile>();
		

		for (Ref ref : listTags) {
		//	System.out.println("Tag: " + ref.getName());
			ObjectId tagFile = repository.resolve(ref.getName());

			try (RevWalk revWalk = new RevWalk(repository)) {
				
				RevCommit commit = revWalk.parseCommit(tagFile);
				RevTree tree = commit.getTree();
				

				try (TreeWalk treeWalk = new TreeWalk(repository)) {
					treeWalk.addTree(tree);
					treeWalk.setRecursive(true);
					treeWalk.setFilter(PathFilter.create("covid19spreading.rdf"));

					if (!treeWalk.next())
						throw new IllegalStateException("Did not find expected file 'covid19spreading.rdf'");

					String tag = new String(ref.getName().replace("refs/tags/", ""));
					String url = new String(
							"http://visualdataweb.de/webvowl/#iri=https://github.com/vbasto-iscte/ESII1920/raw/" + tag
									+ "/covid19spreading.rdf");

					listCovid19SpreadingFile.add(new Covid19SpreadingFile(commit.getAuthorIdent().getWhen().toLocaleString().toString(),
									"covid19spreading.rdf", tag, commit.getFullMessage(), url));

					tableString = table.createHtmlString(listCovid19SpreadingFile);
				}
			}
		}
		 return tableString;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		GetDataFromGit gdfg = new GetDataFromGit();
		String table = null;

		System.out.println(cgi_lib.Header());
		
		try {
			table = gdfg.useGitRepository();
		} catch (GitAPIException | IOException e) {
			System.out.println("Nao foi possivel ligar ao repositorio: " + e);
		}
		
		System.out.println(cgi_lib.HtmlTop("Covid Tags"));
		System.out.println(table);
		System.out.println(cgi_lib.HtmlBot());
	}
}
