package tcm.com.gistone.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import tcm.com.gistone.database.entity.Directory;

@Mapper
public interface DirectoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Directory record);

    int insertSelective(Directory record);

    Directory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Directory record);

    int updateByPrimaryKey(Directory record);
    
    @Select("select * from tb_directory where book_id = #{bookId}")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "bookId",  column = "book_id"),
            @Result(property = "parentId",  column = "parent_id"),
            @Result(property = "sectionId",  column = "section_id"),
            @Result(property = "name",  column = "name"),
            @Result(property = "level",  column = "level"),
            @Result(property = "parentName",  column = "parent_name"),
    })
    List<Directory> selectByBookId(Long bookId);
    
    @Select("select * from tb_directory where book_id = #{bookId} and name = #{name}")
    List<Long> selectByName(@Param("name")String name,@Param("bookId")long bookId);
}