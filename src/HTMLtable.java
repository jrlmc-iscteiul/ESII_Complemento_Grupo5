import java.io.IOException;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;

import pl.edu.icm.cermine.exception.AnalysisException;

public class HTMLtable {
	
	public static void main(String[] args) throws AnalysisException, IOException, InvalidRemoteException, TransportException, GitAPIException {
		ExtractMetadata m = new ExtractMetadata();
		m.useGitRepository();
		//String Titulo = m.extrairTitulo();
		//String Jornar= m.extrairJornal();
		//String Data= m.extrairData();
		//String Autor= m.extrairAutor();
	    
		String l = "<style type=\"text/css\">\r\n" + 
				".tg  {border-collapse:collapse;border-spacing:0;}\r\n" + 
				".tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;\r\n" + 
				"  overflow:hidden;padding:10px 5px;word-break:normal;}\r\n" + 
				".tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;\r\n" + 
				"  font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}\r\n" + 
				".tg .tg-0lax{text-align:left;vertical-align:top}\r\n" + 
				"</style>\r\n" + 
				"<table class=\"tg\">\r\n" + 
				"<thead>\r\n" + 
				"  <tr>\r\n" + 
				"    <th class=\"tg-0lax\">Titulo</th>\r\n" + 
				"    <th class=\"tg-0lax\">Nome do Jornal</th>\r\n" + 
				"    <th class=\"tg-0lax\">Ano de Publicação</th>\r\n" + 
				"    <th class=\"tg-0lax\">Autores</th>\r\n" + 
				"  </tr>\r\n" + 
				"</thead>\r\n" + 
				"<tbody>\r\n" + 
				"  <tr>\r\n" + 
				"    <td class=\"tg-0lax\">"+ m.extrairTitulo() +"</td>\r\n" + 
				"    <td class=\"tg-0lax\">"+ m.extrairJornal() + "</td>\r\n" + 
				"    <td class=\"tg-0lax\">"+ m.extrairData() + "</td>\r\n" + 
				"    <td class=\"tg-0lax\">"+ m.extrairAutor() + "</td>\r\n" + 
				"  </tr>\r\n";
		System.out.println(l);
	}
}   

