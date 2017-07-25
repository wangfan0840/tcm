package tcm.com.gistone.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import tcm.com.gistone.database.entity.Section;

public interface SectionMapper {
    int deleteByPrimaryKey(Long sectionId);

    int insert(Section record);

    int insertSelective(Section record);

    Section selectByPrimaryKey(Long sectionId);

    int updateByPrimaryKeySelective(Section record);

    int updateByPrimaryKey(Section record);
    
    @Select("select * from tb_section where book_id = #{bookId}")
    @Results({
		    @Result(property = "sectionContent",  column = "section_content"),
		    @Result(property = "sectionId",  column = "section_id"),
            @Result(property = "imageUrl",  column = "image_Url")
	})
    List<Section> selectByBookId(long bookId);
}