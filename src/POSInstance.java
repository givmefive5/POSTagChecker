
public class POSInstance {

	String pos;
	String word;
	String filename;
	int sentenceNumber;
	int tokenNumber;
	
	public POSInstance(String pos, String word, String filename, int sentenceNumber, int tokenNumber) {
		super();
		this.pos = pos;
		this.word = word;
		this.filename = filename;
		this.sentenceNumber = sentenceNumber;
		this.tokenNumber = tokenNumber;
	}
	public String getPos() {
		return pos;
	}
	public void setPos(String pos) {
		this.pos = pos;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getSentenceNumber() {
		return sentenceNumber;
	}
	public void setSentenceNumber(int sentenceNumber) {
		this.sentenceNumber = sentenceNumber;
	}
	public int getTokenNumber() {
		return tokenNumber;
	}
	public void setTokenNumber(int tokenNumber) {
		this.tokenNumber = tokenNumber;
	}
	
	
}
