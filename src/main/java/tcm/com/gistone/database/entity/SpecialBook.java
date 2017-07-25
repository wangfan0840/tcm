package tcm.com.gistone.database.entity;

public class SpecialBook {
    private Long sBookId;

    private Long bookId;

    private Long specialId;

    public Long getsBookId() {
        return sBookId;
    }

    public void setsBookId(Long sBookId) {
        this.sBookId = sBookId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getSpecialId() {
        return specialId;
    }

    public void setSpecialId(Long specialId) {
        this.specialId = specialId;
    }
}