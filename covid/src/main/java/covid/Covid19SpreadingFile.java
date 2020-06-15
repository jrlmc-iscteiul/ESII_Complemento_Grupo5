package covid;

// TODO: Auto-generated Javadoc
/**
 * The Class Covid19SpreadingFile.
 * @author Joana Cavalheiro
 * @since 10/06/2020
 * 
 */
public class Covid19SpreadingFile {
	
	/** The file timestamp. */
	private String fileTimestamp;
	
	/** The file name. */
	private String fileName;
	
	/** The file tag. */
	private String fileTag;
	
	/** The tag description. */
	private String tagDescription;
	
	/** The spread visualization link. */
	private String spreadVisualizationLink;
	
	/**
	 * Instantiates a new covid 19 spreading file.
	 *
	 * @param fileTimestamp the file timestamp
	 * @param fileName the file name
	 * @param fileTag the file tag
	 * @param tagDescription the tag description
	 * @param spreadVisualizationLink the spread visualization link
	 */
	public Covid19SpreadingFile(String fileTimestamp, String fileName, String fileTag, String tagDescription,
			String spreadVisualizationLink) {
		this.fileTimestamp = fileTimestamp;
		this.fileName = fileName;
		this.fileTag = fileTag;
		this.tagDescription = tagDescription;
		this.spreadVisualizationLink = spreadVisualizationLink;
	}
		
	/**
	 * Gets the file timestamp.
	 *
	 * @return the file timestamp
	 */
	public String getFileTimestamp() {
		return fileTimestamp;
	}
	
	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Gets the file tag.
	 *
	 * @return the file tag
	 */
	public String getFileTag() {
		return fileTag;
	}

	/**
	 * Gets the tag description.
	 *
	 * @return the tag description
	 */
	public String getTagDescription() {
		return tagDescription;
	}

	/**
	 * Gets the spread visualization link.
	 *
	 * @return the spread visualization link
	 */
	public String getSpreadVisualizationLink() {
		return spreadVisualizationLink;
	}

	/**
	 * Sets the file timestamp.
	 *
	 * @param fileTimestamp the new file timestamp
	 */
	public void setFileTimestamp(String fileTimestamp) {
		this.fileTimestamp = fileTimestamp;
	}

	/**
	 * Sets the file name.
	 *
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Sets the file tag.
	 *
	 * @param fileTag the new file tag
	 */
	public void setFileTag(String fileTag) {
		this.fileTag = fileTag;
	}

	/**
	 * Sets the tag description.
	 *
	 * @param tagDescription the new tag description
	 */
	public void setTagDescription(String tagDescription) {
		this.tagDescription = tagDescription;
	}

	/**
	 * Sets the spread visualization link.
	 *
	 * @param spreadVisualizationLink the new spread visualization link
	 */
	public void setSpreadVisualizationLink(String spreadVisualizationLink) {
		this.spreadVisualizationLink = spreadVisualizationLink;
	}

	/**
	 * To string.
	 *
	 * @return the string of the class
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Covid File: Timestamp: " + this.fileTimestamp + "; Name: " + this.fileName + "; Tag: " + this.fileTag + "; Description: " + this.tagDescription + "; Link: " + this.spreadVisualizationLink);

		return sb.toString();
	}
}
