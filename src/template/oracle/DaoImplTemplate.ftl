package ${package}.daoimpl;

import ${package}.dao.${entityName}Dao;
import ${package}.entity.${entityName};
import org.springframework.stereotype.Repository;

import com.ga.common.sys.base.GenericDaoImpl;

@SuppressWarnings("unchecked")
@Repository("${entityName}Dao")
public class ${entityName}DaoImpl extends GenericDaoImpl<${entityName},${idType}> implements ${entityName}Dao {

	public ${entityName}DaoImpl() {
		super(${entityName}.class);
	}

	



}