package dmit2015.config;

import javax.annotation.sql.DataSourceDefinition;
import javax.enterprise.context.ApplicationScoped;


@DataSourceDefinition(
	name="java:app/datasources/dmit2015courseproject/ProjectDS",
	className="com.microsoft.sqlserver.jdbc.SQLServerDataSource",
	url="jdbc:sqlserver://localhost:1433;databaseName=Dmit2015ProjectDB",
	user="dmit2015",
	password="Password2015")
@ApplicationScoped
public class DataSourceDefinitionConfig {


}


