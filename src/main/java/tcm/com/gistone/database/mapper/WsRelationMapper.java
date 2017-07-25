package tcm.com.gistone.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import tcm.com.gistone.database.entity.WsRelation;

public interface WsRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WsRelation record);

    int insertSelective(WsRelation record);

    WsRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WsRelation record);

    int updateByPrimaryKey(WsRelation record);
    
    
    @Select("SELECT * FROM tb_ws_relation WHERE word_id = #{wordId}")
    @Results({
		@Result(property = "wordId",  column = "word_id"),
		@Result(property = "sectionId",  column = "section_id")
		
	})
    List<WsRelation> selectByWId(Long wordId);
    
    @Select("SELECT * FROM tb_ws_relation WHERE section_id = #{sectionId}")
    @Results({
            @Result(property = "wordId",  column = "word_id"),
            @Result(property = "bookId",  column = "book_id"),
            @Result(property = "sectionId",  column = "section_id"),
            @Result(property = "wordType",  column = "word_type"),
            @Result(property = "wordNum",  column = "word_num")

    })
    List<WsRelation> selectBySId(Long sectionId);
   /* @Select("select a.numa,b.numb from (select section_id,word_num as numa from tb_ws_relation where book_id=#{bookId} and word_id =#{word1} )a join (select section_id,word_num as numb from tb_ws_relation where book_id =#{bookId} and word_id = #{word2})b on a.section_id =b.section_id ")
    List<Map> selectTowWordsInSameSection(@Param("bookId")long bookId,@Param("word1")long word1, @Param("word2")long word2);*/

}