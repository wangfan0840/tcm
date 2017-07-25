package tcm.com.gistone.database.mapper;

import tcm.com.gistone.database.entity.SpecialBook;

public interface SpecialBookMapper {
    int deleteByPrimaryKey(Long sBookId);

    int insert(SpecialBook record);

    int insertSelective(SpecialBook record);

    SpecialBook selectByPrimaryKey(Long sBookId);

    int updateByPrimaryKeySelective(SpecialBook record);

    int updateByPrimaryKey(SpecialBook record);
}