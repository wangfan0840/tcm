package tcm.com.gistone.database.entity;

public class WordRelation {
	private long id;
  
	private long wordId;
	
	private long anoWordId;
	
	private int num;
	
	private long sectionId;

	public long getWordId() {
		return wordId;
	}

	public void setWordId(long wordId) {
		this.wordId = wordId;
	}

	public long getAnoWordId() {
		return anoWordId;
	}

	public void setAnoWordId(long anoWordId) {
		this.anoWordId = anoWordId;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public long getSectionId() {
		return sectionId;
	}

	public void setSectionId(long sectionId) {
		this.sectionId = sectionId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

}
