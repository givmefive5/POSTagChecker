
public class POSInstance {

	String pos;
	String word;
	String lemma;
	String filename;
	int sentenceNumber;
	int tokenNumber;

	public POSInstance(String pos, String word, String lemma, String filename, int sentenceNumber, int tokenNumber) {
		super();
		this.pos = pos;
		this.word = word;
		this.lemma = lemma;
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

	public String getLemma() {
		return lemma;
	}

	public void setLemma(String lemma) {
		this.lemma = lemma;
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

	@Override
	public int hashCode() {
		return word.hashCode();
	}
	//
	// @Override
	// public boolean equals(Object pi){
	// String w = ((POSInstance) pi).getWord();
	// if(this.word.equals(w))
	// return true;
	// else
	// return false;
	//
	// }

}
