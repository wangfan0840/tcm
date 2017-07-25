package tcm.com.gistone.database.mapper;

import tcm.com.gistone.database.entity.UserBook;


public interface UserBookMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserBook record);

    int insertSelective(UserBook record);

    UserBook selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserBook record);

    int updateByPrimaryKey(UserBook record);
}