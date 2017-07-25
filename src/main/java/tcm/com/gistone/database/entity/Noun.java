package tcm.com.gistone.database.entity;

public class Noun {
    private Long nounId;

    private String noun;

    private String nounImp;

    public Long getNounId() {
        return nounId;
    }

    public void setNounId(Long nounId) {
        this.nounId = nounId;
    }

    public String getNoun() {
        return noun;
    }

    public void setNoun(String noun) {
        this.noun = noun == null ? null : noun.trim();
    }

    public String getNounImp() {
        return nounImp;
    }

    public void setNounImp(String nounImp) {
        this.nounImp = nounImp == null ? null : nounImp.trim();
    }
}