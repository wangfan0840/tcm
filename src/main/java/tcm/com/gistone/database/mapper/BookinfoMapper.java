package tcm.com.gistone.database.mapper;

import tcm.com.gistone.database.entity.Bookinfo;

public interface BookinfoMapper {

    int deleteByPrimaryKey(Long bookId);

    int insert(Bookinfo record);

    int insertSelective(Bookinfo record);

    Bookinfo selectByPrimaryKey(Long bookId);

    int updateByPrimaryKeySelective(Bookinfo record);

    int updateByPrimaryKey(Bookinfo record);
}