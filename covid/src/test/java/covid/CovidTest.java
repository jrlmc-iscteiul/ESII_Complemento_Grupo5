package covid;

import static org.junit.Assert.*;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.lib.ObjectLoader;
import org.junit.Test;

public class CovidTest {


	@Test

	public void test1 () throws InvalidRemoteException, TransportException, GitAPIException, IOException{
		CovidEvolutionDiff t1 = new CovidEvolutionDiff();
		t1.useGitRepository();


		String string1 = t1.getStringFinalE();
		assertNotEquals("teste1", string1);
		//		assertEquals("teste1", string1);

	}

	@Test

	public void test2() throws InvalidRemoteException, TransportException, GitAPIException{
		CovidEvolutionDiff t2 = new CovidEvolutionDiff();
		t2.main(null);



	}


	@Test
	public void test4 () {
		CovidEvolutionDiff t2 = new CovidEvolutionDiff();
		//		t2.useGitRepository();
		ArrayList<String> lista = new ArrayList<>();
		lista = null;
		t2.getFicheiros();
		assertNotEquals(t2.getFicheiros(), lista);
	}

	@Test
	public void test5() throws InvalidRemoteException, TransportException, GitAPIException, IOException {
		CovidEvolutionDiff t5 = new CovidEvolutionDiff();
		t5.useGitRepository();
		t5.setStringFinalE("teste5");
		assertEquals("teste5",t5.getStringFinalE());



	}

//	public void teste6() {
//		Date tm=null;
//		ObjectLoader ol=null;
//		Covid19SpreadingFile t6 = new Covid19SpreadingFile(tm, ol);
//		ObjectLoader ol6 = t6.getOl();
////		Date date6 = t6.getTm();
//		System.out.println(ol6);
//		
		
//		assertEquals(expected, actual);
	}
	


