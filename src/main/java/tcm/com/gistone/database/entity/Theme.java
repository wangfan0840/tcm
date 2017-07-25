package tcm.com.gistone.database.entity;

public class Theme {
    private Long themeId;

    private String themeName;

    private String themeShort;

    private Long specialId;

    public Long getThemeId() {
        return themeId;
    }

    public void setThemeId(Long themeId) {
        this.themeId = themeId;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName == null ? null : themeName.trim();
    }

    public String getThemeShort() {
        return themeShort;
    }

    public void setThemeShort(String themeShort) {
        this.themeShort = themeShort == null ? null : themeShort.trim();
    }

    public Long getSpecialId() {
        return specialId;
    }

    public void setSpecialId(Long specialId) {
        this.specialId = specialId;
    }
}