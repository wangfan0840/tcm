package tcm.com.gistone.database.mapper;

import tcm.com.gistone.database.entity.Knowledge;

public interface KnowledgeMapper {
    int deleteByPrimaryKey(Long knowledgeId);

    int insert(Knowledge record);

    int insertSelective(Knowledge record);

    Knowledge selectByPrimaryKey(Long knowledgeId);

    int updateByPrimaryKeySelective(Knowledge record);

    int updateByPrimaryKey(Knowledge record);
    
}