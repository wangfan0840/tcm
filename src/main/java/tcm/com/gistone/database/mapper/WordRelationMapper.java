package tcm.com.gistone.database.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import tcm.com.gistone.database.entity.WordRelation;

import java.util.List;
import java.util.Map;


public interface WordRelationMapper {
	
	@Insert("INSERT INTO tb_a#{a}_b#{b}(word_id,ano_word_id,section_id,num) VALUES(#{wr.wordId}, #{wr.anoWordId}, #{wr.sectionId}, #{wr.num})")
	void insert(@Param("a")int a,@Param("b")int b,@Param("wr")WordRelation wr);
	
	@Select("Select * from tb_a#{a}_b#{b} where word_id = #{word1} and ano_word_id = #{word2}")
	@Results({
		@Result(property = "id",  column = "id"),
		@Result(property = "wordId",  column = "word_id"),
		@Result(property = "anoWordId",  column = "ano_word_id"),
		@Result(property = "num",  column = "num")
	})
	WordRelation getByWords(@Param("a")long a,@Param("b")long b,@Param("word1")long word1,@Param("word2")long word2);

	@Select("SELECT count(*) FROM tb_a#{a}_b#{b} where word_id = #{wordId}")
	public int selectCountByWord(@Param("a")long a,@Param("b")long b,@Param("wordId") long wordId);

	
	@Select("SELECT  ano_word_id as wordId,num FROM tb_a#{a}_b#{b} where word_id = #{wordId}")
	List<Map> selectByWord(@Param("a")int a, @Param("b")int b, @Param("wordId") long wordId);

	@Select("SELECT word_id as wordId,num FROM tb_a#{a}_b#{b} where ano_word_id = #{wordId}")
	List<Map> selectByAnoWord(@Param("a")int a, @Param("b")int b, @Param("wordId") long wordId);
	
	@Update("update tb_a#{a}_b#{b} set num = #{wr.num} where id = #{wr.id}")  
	public int update(@Param("a")long a,@Param("b")long b,@Param("wr")WordRelation wr); 
	
}
