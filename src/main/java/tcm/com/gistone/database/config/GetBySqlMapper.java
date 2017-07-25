package tcm.com.gistone.database.config;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import tcm.com.gistone.database.entity.TutorDynaSqlProvider;

public interface GetBySqlMapper {
		
	@SelectProvider(type=TutorDynaSqlProvider.class, method="findTutorByIdSql")
	List<Map> findRecords(String SQLAdapter);
	
	@SelectProvider(type=TutorDynaSqlProvider.class, method="findTutorByIdSql")
	int findrows(String SQLAdapter);
	
	@InsertProvider(type=TutorDynaSqlProvider.class, method="findTutorByIdSql")
	int insert(String SQLAdapter);
	
	@UpdateProvider(type=TutorDynaSqlProvider.class, method="findTutorByIdSql")
	int update(String SQLAdapter);
	
	@DeleteProvider(type=TutorDynaSqlProvider.class, method="findTutorByIdSql")
	int delete(String SQLAdapter);
	
}