package covid;

import java.io.IOException;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class HTMLTable.
 *
 * @author Joana Cavalheiro
 * @since 10/06/2020
 */
public class HTMLTable {

	/** The title. */
	public static String title = "<p style=\"text-align:center;font-size:160%;\"><strong>Tags do ficheiro covid19spreading.rdf no GitHub</strong></p>";
	
	/** The style. */
	public static String style = "<style type=\"text/css\">.tg  {border-collapse:collapse;border-spacing:0;}.tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;  overflow:hidden;padding:10px 5px;word-break:normal;}.tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;  font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}.tg .tg-fkac{background-color:#ffce93;font-size:12px;text-align:center;vertical-align:top}.tg .tg-wus8{background-color:#ffce93;border-color:inherit;color:#000000;font-size:12px;font-style:italic;text-align:center;  vertical-align:top}.tg .tg-ltnr{background-color:#ffce93;border-color:inherit;color:#000000;font-size:12px;text-align:center;vertical-align:top}.tg .tg-0lax{text-align:left;vertical-align:top}.tg .tg-0pky{border-color:inherit;text-align:left;vertical-align:top}</style>";
	
	/** The header table. */
	public static String headerTable = "<table class=\"tg\" style=\"undefined;table-layout: fixed; width: 751px\">"
			+ "	<colgroup>" 
			+ "<col style=\"width: 280px\">"
			+ "<col style=\"width: 280px\">"
			+ "<col style=\"width: 280px\">"
			+ "<col style=\"width: 380px\">"
			+ "<col style=\"width: 280px\">"
			+ "</colgroup>"
			+ "	<thead>"
			+ "<tr>"
			+ "<th class=\"tg-fkac\">File Timestamp</th>"
			+ "<th class=\"tg-wus8\">File Name</th>"
			+ "<th class=\"tg-ltnr\">File Tag</th>"
			+ "<th class=\"tg-ltnr\">Tag Description</th>"
			+ "<th class=\"tg-ltnr\">Spread Visualization Link</th></tr>"
			+ "	</thead>"
			+ "<tbody>";
	
	/** The final body. */
	public static String finalBody = "</tbody>";
	
	/** The final table. */
	public static String finalTable = "</table>";
	
	/**
	 * Creates the part corresponding to each row of the html table.
	 *
	 * @param file object of Covid19SpreadingFile
	 * @return the string that corresponds to the html table row
	 */
	public String createLine(Covid19SpreadingFile file) {
		StringBuilder sb = new StringBuilder();

		sb.append("<tr> \n");
		sb.append("<td class=\"tg-0lax\">" + file.getFileTimestamp() + "</td>");
		sb.append("<td class=\"tg-0lax\">" + file.getFileName() + "</td> \n");
		sb.append("<td class=\"tg-0lax\">" + file.getFileTag() + "</td> \n");
		sb.append("<td class=\"tg-0lax\">" + file.getTagDescription() + "</td> \n");
		sb.append("<td class=\"tg-0lax\"><a href=\"" + file.getSpreadVisualizationLink()
				+ "\">Covid19Spreading</a></td> \n");
		sb.append("</tr> \n");

		return sb.toString();
	}

	/**
	 * Update HTML file.
	 *
	 * @param list the list
	 * @return the string
	 */
	public String createHtmlString(List<Covid19SpreadingFile> list){

		StringBuilder sb = new StringBuilder();

		sb.append(title);
		sb.append(style);
		sb.append(headerTable);

		for (Covid19SpreadingFile elementOFList : list) 
			sb.append(createLine(elementOFList));

		sb.append(finalBody);
		sb.append(finalTable);

	return sb.toString();
	}
}

