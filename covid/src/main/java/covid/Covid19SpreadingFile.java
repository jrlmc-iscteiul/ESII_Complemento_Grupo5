package covid;

import java.security.Timestamp;
import java.util.Date;

import org.eclipse.jgit.lib.ObjectLoader;

// TODO: Auto-generated Javadoc
/**
 * The Class Covid19SpreadingFile.
 */
public class Covid19SpreadingFile {

	/** The tm. */
	private Date tm;
	
	/** The ol. */
	private ObjectLoader ol;
	
	/** The texto. */
	private String texto;

	/**
	 * Instantiates a new covid 19 spreading file.
	 *
	 * @param tm the tm
	 * @param ol the ol
	 */
	public Covid19SpreadingFile(Date tm, ObjectLoader ol) {
		this.ol = ol;
		this.tm = tm;
		this.texto = texto;
	}



	/**
	 * Gets the texto.
	 *
	 * @return the texto
	 */
	public String getTexto() {
		return texto;
	}


	

	/**
	 * Sets the texto.
	 *
	 * @param texto the new texto
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}



	/**
	 * Gets the tm.
	 *
	 * @return the tm
	 */
	public Date getTm() {
		return tm;
	}



	/**
	 * Sets the tm.
	 *
	 * @param tm the new tm
	 */
	public void setTm(Date tm) {
		this.tm = tm;
	}



	/**
	 * Gets the ol.
	 *
	 * @return the ol
	 */
	public ObjectLoader getOl() {
		return ol;
	}



	/**
	 * Sets the ol.
	 *
	 * @param ol the new ol
	 */
	public void setOl(ObjectLoader ol) {
		this.ol = ol;
	}



	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Covid File: Timestamp: " + this.tm + "; Texto: " + this.ol);

		return sb.toString();
	}
}
