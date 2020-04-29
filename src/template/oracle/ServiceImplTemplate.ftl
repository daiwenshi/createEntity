package ${package}.serviceimpl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import ${package}.dao.${entityName}Dao;
import ${package}.entity.${entityName};
import ${package}.service.${entityName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.common.sys.base.GenericDao;
import com.ga.common.sys.base.GenericServiceImpl;
@Service
public class ${entityName}ServiceImpl extends GenericServiceImpl<${entityName}, ${idType}> implements ${entityName}Service {
	private static final Logger logger = Logger.getLogger(${entityName}ServiceImpl.class);
	 @Autowired
	private ${entityName}Dao ${Name}Dao;
	 
	@Resource(name = "${entityName}Dao")
	public void setGenericDao(GenericDao<${entityName}, ${idType}> genericDao) {
		super.genericDao = genericDao;
	}
}
