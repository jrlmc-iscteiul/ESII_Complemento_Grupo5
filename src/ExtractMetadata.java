import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.lib.Repository;

import com.itextpdf.text.Element;

import pl.edu.icm.cermine.ContentExtractor;
import pl.edu.icm.cermine.exception.AnalysisException;
import pl.edu.icm.cermine.metadata.model.DateType;
import pl.edu.icm.cermine.metadata.model.DocumentAuthor;
import pl.edu.icm.cermine.metadata.model.DocumentDate;

public class ExtractMetadata {
	
	private static final DateType DateType = null;
	static File directory;
	

//	public static void cloneGit() {
//		try {
//			String URI= "https://github.com/hnpbo-iscteiul/Covid-19-Scientific-Articles.git";
//			directory = new File(System.getProperty("user.dir") + "/clonegit");
//			
//			//deleteDir(directory);
//
//			Git git;
//			git = Git.cloneRepository()
//					.setURI(URI)
//					.setDirectory(directory)
//					.call();
//			Repository repository = git.getRepository();
//		} catch (GitAPIException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	public void useGitRepository() throws InvalidRemoteException, TransportException, GitAPIException, IOException {
		Git git;
		File file = new File("covidS");
		
		String tableString = null;
		
		if(file.isDirectory() && file.list().length ==0) {
			git = Git.cloneRepository().setURI("https://github.com/vbasto-iscte/ESII1920.git").setDirectory(file).call();
		}else {
			git = Git.open(file);
			git.pull();
			git.checkout();
		}
	}
	
	
	public String extrairTitulo() throws AnalysisException, IOException {
		
		ContentExtractor extractor = new ContentExtractor();
		InputStream inputStream = new FileInputStream("covidS");
		extractor.setPDF(inputStream);
		Element result = (Element) extractor.getContentAsNLM();
		
		String l = extractor.getMetadata().getTitle();
		
		return l;
	}
	
	
	public String extrairJornal() throws AnalysisException, IOException {
		ContentExtractor extractor = new ContentExtractor();
		InputStream inputStream = new FileInputStream("C:\\Users\\Henrique\\eclipse-workspace\\covid-sci-discoveries\\CovidScientificDiscoveriesRepository");
		extractor.setPDF(inputStream);
		Element result = (Element) extractor.getContentAsNLM();
		
		String l = extractor.getMetadata().getJournal();
		return l;
	}
	
	
	public DocumentDate extrairData() throws AnalysisException, IOException {
		ContentExtractor extractor = new ContentExtractor();
		InputStream inputStream = new FileInputStream("C:\\Users\\Henrique\\eclipse-workspace\\covid-sci-discoveries\\CovidScientificDiscoveriesRepository");
		extractor.setPDF(inputStream);
		Element result = (Element) extractor.getContentAsNLM();
		
		DocumentDate l = extractor.getMetadata().getDate(DateType);
		return l;
	}
	
	
	public List extrairAutor() throws AnalysisException, IOException {
		ContentExtractor extractor = new ContentExtractor();
		InputStream inputStream = new FileInputStream("C:\\Users\\Henrique\\eclipse-workspace\\covid-sci-discoveries\\CovidScientificDiscoveriesRepository");
		extractor.setPDF(inputStream);
		Element result = (Element) extractor.getContentAsNLM();
		
		List<DocumentAuthor> l = extractor.getMetadata().getAuthors();
		return l;
	}
}

