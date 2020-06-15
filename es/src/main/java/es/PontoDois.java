package es;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * The Class PontoDois.
 * 
 * @author Beatriz Ragageles
 * @since 2020-06-10
 */
public class PontoDois {

	/** The driver. */
	public static WebDriver driver;

	/** The site. */
	private static String site = "http://192.168.99.100";

	/** The titulo site. */
	private static String tituloSite = "COVID-19 – ES II (Engenharia de Software II, 2º Semestre 2019/2020, Licenciatura em IGE)";

	/** The site funciona. */
	private static boolean siteFunciona;

	/** The wpcms. */
	private static String wpcms="not tested";

	/** The webpages. */
	private static String webpages="not tested";  

	/** The login. */
	private static String login="not tested"; 

	/** The repositorios. */
	private static String repositorios="not tested"; 

	/** The registos. */
	private static String registos="not tested";   

	/** The email. */
	private static String email="unvailable"; 

	/** The date. */
	private static String date ="not tested"; 

	/** The global. */
	private static String global ="available";

	/** The enviou. */
	private static boolean enviou=false;


	//	private static int counterWpcms=0;
	//	private static int counterWebpages=0;  
	//	private static int counterLogin=0; 
	//	private static int counterRepositorios=0; 
	//	private static int counterRegistos=0;   
	//	private static int counterEmail=0; // FALTA 
	//	private static int counterGlobal=0; 


	/** The user. */
	private static String user = "bearaga"; //colocar pass do cliente no wordpress (admin)

	/** The pass. */
	private static String pass = "teste"; //colocar pass do cliente no wordpress (admin)

	/** The email admin. */
	private static String emailAdmin = "beatriz.isabel41@gmail.com"; //colocar email do cliente para o qual quer receber avisos

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws InterruptedException the interrupted exception
	 * @throws EmailException the email exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws InterruptedException, EmailException, IOException{

		while(true) {

			System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
			driver = new ChromeDriver();
			//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); 
			//cada x q nao encontrares o elemento que se procura nas outras funcoes, espera sempre 15seg ate encontrar antes de mandar o erro

			veSeFunciona();
			if(siteFunciona) 
				login();
			if(siteFunciona)
				verificaUtilizadores();
			if(siteFunciona)
				fazRegisto();
			if(siteFunciona) {
				verificaUtilizadores();
				driver.findElement(By.linkText("COVID-19")).click();
				//Thread.sleep(2000);
			}
			if(siteFunciona)
				verificaPaginas();
			if(siteFunciona)
				verificaRepositorios();
			dataAtual();
			html();
			driver.close();
			Thread.sleep(120000); //2minutos p testar
			//Thread.sleep(7200000);//verificar de 2 em 2 horas 
		}
	}


	/**
	 * Ve se funciona.
	 *
	 * @throws EmailException the email exception
	 */
	private static void veSeFunciona() throws EmailException {
		try {
			driver.navigate().to(site);
			//driver.manage().window().maximize();
			String title = driver.getTitle();
			System.out.println(title);
			if(title.equals(tituloSite)) {
				System.out.println("site funciona");
				siteFunciona = true;
				wpcms="available";
				//	counterWpcms++;


			} else {
				erro("site não funciona");
				siteFunciona = false;
				wpcms="unvailable";

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("site não funciona");
			wpcms="unvailable";
			global="unvailable";
			erro("site não funciona");

		}
	}


	/**
	 * Erro.
	 *
	 * @param error the error
	 * @throws EmailException the email exception
	 */
	private static void erro(String error) throws EmailException {
		Email mail = new SimpleEmail();
		mail.setHostName("smtp.gmail.com"); //p definir mail q envia
		mail.setSmtpPort(465);
		mail.setAuthenticator(new DefaultAuthenticator("grupo5testeES@gmail.com", "ESII1920")); //dar log in no mail
		mail.setSSLOnConnect(true); 
		mail.setFrom("grupo5testeES@gmail.com");
		mail.setSubject("ERROR FOUND"); //assunto do mail
		mail.setMsg("Caro administrador, detetámos um erro do tipo "+error+".\n"+"Recomendamos a verificação do problema assim que possível");
		mail.addTo(emailAdmin);
		mail.send();
		email="available"; // fica available quando 
	}

	/**
	 * Faz registo.
	 *
	 * @throws EmailException the email exception
	 */
	public static void fazRegisto() throws EmailException { //para os formularios do join us --simulacao por falta de ponto 1 na altura
		try {
			((JavascriptExecutor)driver).executeScript("window.open()");
			ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			driver.get("https://generator.email/");
			Thread.sleep(5000); //poe dormir p esperar q pag carregue
			String mail = driver.findElement(By.id("email_ch_text")).getText(); //linha do email
			String[] aux = mail.split("@"); //p dividir pelo @ p criar o user
			String username = aux[0];
			System.out.println(username); 
			driver.close();
			driver.switchTo().window(tabs.get(0)); //p mudar de janela p voltar a inicial
			driver.findElement(By.linkText("Add New")).click(); //pq n tem id - clicar no botao
			Thread.sleep(1000);
			driver.findElement(By.id("user_login")).sendKeys(username);
			driver.findElement(By.id("email")).sendKeys(mail); //preencher registo
			//
			driver.findElement(By.id("createusersub")).click(); //add new user - clicar botao
			registos="available";
			//counterRegistos++;
		} catch (Exception e) {
			//	e.printStackTrace();
			siteFunciona = false;
			System.out.println("Erro de registo");
			erro("Erro de registo");
			registos="unvailable";
			global="unvailable";
		}
	}


	/**
	 * Verifica utilizadores.
	 *
	 * @throws EmailException the email exception
	 */
	public static void verificaUtilizadores() throws EmailException {
		try {
			driver.findElement(By.linkText("Users")).click();
			WebElement forms = driver.findElement(By.id("the-list")); //vai buscar todos 
			String c = forms.getText(); //passa p texto
			System.out.println("Os utilizadores registados são os seguintes \n"+c); //escreve no ecrã
			Thread.sleep(3000);
			registos="available";
		} catch (Exception e) {
			//	e.printStackTrace();
			siteFunciona = false;
			erro("Erro de verificação de utilizadores");
			System.out.println("Erro de verificação de utilizadores");
			registos="unvailable";
			global="unvailable";
		}
	}


	/**
	 * Login.
	 *
	 * @throws EmailException the email exception
	 */
	public static void login() throws EmailException {
		try {
			Actions act = new Actions(driver);
			WebElement element = driver.findElement(By.linkText("Log in")); 
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element); //p dar scroll
			Thread.sleep(1000);
			act.moveToElement(driver.findElement(By.linkText("Log in"))).click().perform(); //clica no log in 
			Thread.sleep(1000);
			WebElement username = driver.findElement(By.id("user_login"));
			username.sendKeys(user); //na caixinha poe o q defini
			WebElement password = driver.findElement(By.id("user_pass"));
			password.sendKeys(pass);
			driver.findElement(By.id("wp-submit")).click(); //p carregar no botao de log in
			Thread.sleep(1000);
			login="available";
			//counterLogin++;
		} catch (Exception e) {
			//	e.printStackTrace();
			erro("Log in não é possível");
			siteFunciona = false;
			System.out.println("Log in não é possivel");
			login="unvailable";
			global="unvailable";
		}
	}

	/**
	 * Verifica paginas.
	 *
	 * @throws EmailException the email exception
	 */
	public static void verificaPaginas() throws EmailException {
		try {
			driver.findElement(By.linkText("Home"));
			driver.findElement(By.linkText("Covid Scientific Discoveries"));
			driver.findElement(By.linkText("Covid Spread"));
			driver.findElement(By.linkText("Covid Queries"));
			driver.findElement(By.linkText("Covid Wiki"));
			driver.findElement(By.linkText("Covid Evolution"));
			driver.findElement(By.linkText("FAQ"));
			driver.findElement(By.linkText("Contact Us"));
			driver.findElement(By.linkText("Join Us"));
			driver.findElement(By.linkText("About Us"));
			driver.findElement(By.linkText("Covid Scientific Discoveries Repository"));
			driver.findElement(By.linkText("Web Site Analytics"));
			webpages="available";
			//counterWebpages++;
		}catch (Exception e) {
			erro("Páginas Web não disponíveis");
			siteFunciona=false;
			System.out.println("web pages não disponíveis");
			webpages="unavailable";
			global="unvailable";
		}
	}

	/**
	 * Verifica repositorios.
	 *
	 * @throws EmailException the email exception
	 */
	public static void verificaRepositorios() throws EmailException {
		try {
			Actions act = new Actions(driver);
			//	WebElement element = driver.findElement(By.linkText("Covid Scientific Discoveries Repository"));  a testar usamos mas pode ser q dê sem isso
			//	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element); //p dar scroll
			//	Thread.sleep(1000);
			act.moveToElement(driver.findElement(By.linkText("Covid Scientific Discoveries Repository"))).click().perform(); //clica  
			Thread.sleep(1000);
			driver.findElement(By.linkText("1-s2.0-S1755436517301135-main"));
			driver.findElement(By.linkText("biology-09-00094"));
			driver.findElement(By.linkText("178-1-53"));
			driver.findElement(By.linkText("biology-09-00097"));
			repositorios="available";
			//counterRepositorios++;
		}catch (Exception e) {
			erro("repositorios não disponíveis");
			siteFunciona=false;
			System.out.println("repositorios não disponíveis");
			repositorios="unavailable";
			global="unvailable";
		}
	}

	/**
	 * Data atual.
	 */
	public static void dataAtual() {

		Date data = new Date(System.currentTimeMillis());
		System.out.println(data);
		date=data.toString();
	}


	/**
	 * Html.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	public static void html() throws IOException, InterruptedException {

		BufferedWriter bw2 = new BufferedWriter(new FileWriter("ponto2.html"));
		bw2.write("<html>\r\n" + 
				"<head>\r\n" + 
				"<title>monitorização da disponibilidade</title>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"<table width=\"500px\" border=\"1px\">\r\n" + 
				"<caption> Monitorização da disponibilidade </caption>\r\n" + 
				"	<tr align=\"left\" bgcolor=\"lightblue\">\r\n" + 
				"	<tr>\r\n" + 
				"		<th>Site</th>\r\n" + 
				"		<th>Web Pages</th>\r\n" + 
				"		<th>Login</th>\r\n" + 
				"		<th>repositories</th>\r\n" + 
				"		<th>Forms</th>\r\n" + 
				"		<th>Email</th>\r\n" + 
				"		<th>Date</th>\r\n" + 
				"		<th>Global</th>\r\n" + 
				"</tr>\r\n" + 
				"<tr>\r\n" + 
				"		<td> "+ wpcms +"  </td>\n" + 
				"		<td>" + webpages + "</td>\r\n" + 
				"		<td>" + login + "</td>\r\n" + 
				"		<td>" + repositorios + "</td>\r\n" + 
				"		<td>" + registos + "</td>\r\n" + 
				"		<td>" + email + "</td>\r\n" + 
				"		<td>" + date + "</td>\r\n" + 
				"		<td>" + global + "</td>\r\n" + 
				"</tr>\r\n" + 
				"</table>\r\n" + 
				"</body>\r\n" + 
				"</html>");
		bw2.close();	


		((JavascriptExecutor)driver).executeScript("window.open()");
		ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs1.get(1));
		driver.get("C:/Users/BeatrizRagageles/Documents/wordpress/cgi-bin/ponto2.html");
		Thread.sleep(10000);


		System.out.println("Content-type: text/html\n");	

		System.out.println("<html>\r\n" + 
				"<head>\r\n" + 
				"<title>monitorização da disponibilidade</title>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"<table width=\"500px\" border=\"1px\">\r\n" + 
				"<caption> Monitorização da disponibilidade </caption>\r\n" + 
				"	<tr align=\"left\" bgcolor=\"lightblue\">\r\n" + 
				"	<tr>\r\n" + 
				"		<th>Site</th>\r\n" + 
				"		<th>Web Pages</th>\r\n" + 
				"		<th>Login</th>\r\n" + 
				"		<th>repositories</th>\r\n" + 
				"		<th>Forms</th>\r\n" + 
				"		<th>Email</th>\r\n" + 
				"		<th>Date</th>\r\n" + 
				"		<th>Global</th>\r\n" + 
				"</tr>\r\n" + 
				"<tr>\r\n" + 
				"		<td> "+ wpcms +"  </td>\n" + 
				"		<td>" + webpages + "</td>\r\n" + 
				"		<td>" + login + "</td>\r\n" + 
				"		<td>" + repositorios + "</td>\r\n" + 
				"		<td>" + registos + "</td>\r\n" + 
				"		<td>" + email + "</td>\r\n" + 
				"		<td>" + date + "</td>\r\n" + 
				"		<td>" + global + "</td>\r\n" + 
				"</tr>\r\n" + 
				"</table>\r\n" + 
				"</body>\r\n" + 
				"</html>");
	}



}

