package es;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.apache.commons.mail.EmailException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

class PontoDoisTest {
	
	
	PontoDois p; 
	

	@BeforeEach
	void setUp() throws Exception {
		p=new PontoDois();
		
	}
	
	@Test
	public void testPontoDois() {
		assertTrue(true);
	}
	
	@Test
	void testMain() throws EmailException, IOException, InterruptedException {
		p=new PontoDois();
		
		p.main(null);
		p.login();
		p.verificaUtilizadores();
		p.fazRegisto();
		p.verificaPaginas();
		p.verificaRepositorios();
	//	p.html();
		
	}
	
	@Test
	public void testWebPage() throws EmailException {
		p.verificaPaginas();
	}
	
//	@Test
//	public void testWebPage() throws EmailException {
//		p.veSeFunciona();
//	}
	
	@Test
	public void testLogin() throws EmailException{
		p.login();
	}
	
	@Test
	public void testUtilizadores() throws EmailException {
		p.verificaUtilizadores();
	}
	
	@Test
	public void testRepositories() throws EmailException{
		p.verificaRepositorios();
	}
//	
//	@Test
//	public void testErros() throws EmailException{
//		p.erro(null);
//	}
	
//	@Test
//	public void testTabelaHtml() throws EmailException, IOException, InterruptedException{
//		p.html();
//	}

}
