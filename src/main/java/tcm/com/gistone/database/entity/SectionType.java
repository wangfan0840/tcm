package tcm.com.gistone.database.entity;

public class SectionType {
    private Long sTypeId;

    private String sTypeName;

    private String sTypeShort;

    private Long themeId;

    public Long getsTypeId() {
        return sTypeId;
    }

    public void setsTypeId(Long sTypeId) {
        this.sTypeId = sTypeId;
    }

    public String getsTypeName() {
        return sTypeName;
    }

    public void setsTypeName(String sTypeName) {
        this.sTypeName = sTypeName == null ? null : sTypeName.trim();
    }

    public String getsTypeShort() {
        return sTypeShort;
    }

    public void setsTypeShort(String sTypeShort) {
        this.sTypeShort = sTypeShort == null ? null : sTypeShort.trim();
    }

    public Long getThemeId() {
        return themeId;
    }

    public void setThemeId(Long themeId) {
        this.themeId = themeId;
    }
}