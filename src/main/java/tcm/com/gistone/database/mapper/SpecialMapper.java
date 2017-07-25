package tcm.com.gistone.database.mapper;

import tcm.com.gistone.database.entity.Special;

public interface SpecialMapper {
    int deleteByPrimaryKey(Long specialId);

    int insert(Special record);

    int insertSelective(Special record);

    Special selectByPrimaryKey(Long specialId);

    int updateByPrimaryKeySelective(Special record);

    int updateByPrimaryKey(Special record);
}