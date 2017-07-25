package tcm.com.gistone.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import tcm.com.gistone.database.entity.Theme;

public interface ThemeMapper {
    int deleteByPrimaryKey(Long themeId);

    int insert(Theme record);

    int insertSelective(Theme record);

    Theme selectByPrimaryKey(Long themeId);

    int updateByPrimaryKeySelective(Theme record);

    int updateByPrimaryKey(Theme record);
    
    @Select("select * from tb_theme")
	@Results({
		@Result(property = "themeId",  column = "theme_id"),
		@Result(property = "themeName",  column = "theme_name"),
		@Result(property = "themeShort",  column = "theme_short"),
		@Result(property = "specialId",  column = "special_id")
	})
    List<Theme> selectAll();
    
    @Select("select theme_id from tb_theme where theme_short = #{themeShort}")
    @Results({
            @Result(property = "themeId",  column = "theme_id"),
    })
    Long selectIdByShort(String themeShort);
}