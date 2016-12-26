package com.gom.de.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gom.de.common.ConnectionProvider;
import com.gom.de.model.ExternalDependencyModel;
import com.gom.de.model.ProjectDependencyModel;
import com.mysql.jdbc.Statement;

public class ProjectDependencyDao extends AbstractDao<ProjectDependencyModel> {

	@Override
	public void insert(ProjectDependencyModel value) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();

		String query = " insert into projects (count_external, count_internal, count_total_line, project_name)"
				+ " values (?, ?, ?, ?)";

		PreparedStatement preparedStmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		preparedStmt.setInt(1, value.getExternalCount());
		preparedStmt.setInt(2, value.getInternalCount());
		preparedStmt.setInt(3, value.getTotalLineCount());
		preparedStmt.setString(4, value.getProjectName());

		preparedStmt.executeUpdate();
		
		try (ResultSet generatedKeys = preparedStmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
            	int project_id = generatedKeys.getInt(1);
            	
            	for(ExternalDependencyModel model : value.getExternalMap().values())
            	{
            		insertExternal(project_id, model);
            	}
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
	}
	
	void insertExternal(int project_id, ExternalDependencyModel value) throws SQLException
	{
		Connection connection = ConnectionProvider.getConnection();
		
		String query = " insert into project_external (project_uid, count, related_line_count, package_name)"
				+ " values (?, ?, ?, ?)";

		PreparedStatement preparedStmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		preparedStmt.setInt(1, project_id);
		preparedStmt.setInt(2, value.getCount());
		preparedStmt.setInt(3, value.getRelatedLineCount());
		preparedStmt.setString(4, value.getFullName());
		
		preparedStmt.executeUpdate();
	}
}
