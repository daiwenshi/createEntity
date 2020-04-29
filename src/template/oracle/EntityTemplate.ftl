package ${package}.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * @author zh
 * @className: ${entityName}.java
 * @time: ${now}
 * @tableName_comments ${tableName_comments}
 * hibernate.xml#<mapping class="${package}.entity.${entityName}"/>
 */
@Entity
@Table(name = ${entityName}.tableName)
public class ${entityName} implements java.io.Serializable {
	public static final String tableName="${DB_USER}.${tableName}";
	////生成属性
	${createPropStr}
	
	
	//生成get/set方法
	${createFunctionStr}
}
