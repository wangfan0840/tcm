package tcm.com.gistone.database.mapper;

import tcm.com.gistone.database.entity.SectionType;

public interface SecTypeMapper {
    int deleteByPrimaryKey(Long sTypeId);

    int insert(SectionType record);

    int insertSelective(SectionType record);

    SectionType selectByPrimaryKey(Long sTypeId);

    int updateByPrimaryKeySelective(SectionType record);

    int updateByPrimaryKey(SectionType record);
}