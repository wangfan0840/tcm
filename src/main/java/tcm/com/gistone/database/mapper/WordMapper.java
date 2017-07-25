package tcm.com.gistone.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import tcm.com.gistone.database.entity.Word;

@Mapper
public interface WordMapper {
    int deleteByPrimaryKey(Long wordId);

    int insert(Word record);

    int insertSelective(Word record);

    Word selectByPrimaryKey(Long wordId);

    int updateByPrimaryKeySelective(Word record);

    int updateByPrimaryKey(Word record);
    
    @Select("select * from tb_word")
    List<Word> selectAll();
    
    @Select("select * from tb_word where word = #{word}")
    @Results({
            @Result(property = "wordId",  column = "word_id"),
            @Result(property = "themeType",  column = "theme_type"),
            @Result(property = "word",  column = "word"),
            @Result(property = "parentId",  column = "parent_id")
    })
    Word selectByWord(String word);
    
    @Select("select * from tb_word where theme_type = #{type}")
	@Results({
		@Result(property = "wordId",  column = "word_id")
	})
    List<Word> selectByType(String type);
    @Select("select MAX(word_id) from tb_word")
    Long selectMaxId();
    @Select("select theme_type from tb_word where word_id = #{id}")
    String selectTypeById(Long id);
}