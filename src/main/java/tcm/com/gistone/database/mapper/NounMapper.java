package tcm.com.gistone.database.mapper;

import tcm.com.gistone.database.entity.Noun;

public interface NounMapper {
    int deleteByPrimaryKey(Long nounId);

    int insert(Noun record);

    int insertSelective(Noun record);

    Noun selectByPrimaryKey(Long nounId);

    int updateByPrimaryKeySelective(Noun record);

    int updateByPrimaryKey(Noun record);
}