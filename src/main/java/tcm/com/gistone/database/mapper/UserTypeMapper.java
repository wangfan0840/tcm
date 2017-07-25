package tcm.com.gistone.database.mapper;

import tcm.com.gistone.database.entity.UserType;

public interface UserTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserType record);

    int insertSelective(UserType record);

    UserType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserType record);

    int updateByPrimaryKey(UserType record);
}