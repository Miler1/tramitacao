package br.ufla.lemaf.querybrowser.repository.impl;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import br.ufla.lemaf.querybrowser.repository.QueryBrowserRepository;

@Repository
public class QueryBrowserRepositoryImpl implements QueryBrowserRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public Collection<Object> executeSelect(String sql) {
		
		Session s = (Session) entityManager.getDelegate();
		Query query = s.createSQLQuery(sql);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		
		return query.list();
	}
	
	
	public Integer executeDML(String sql) {
		
		return this.entityManager.createNativeQuery(sql).executeUpdate();
	}
	
	
//	@SuppressWarnings("unchecked")
//	public QueryBrowserTO executeSelectHibernate(String query) {
//		
//		List<String> columns = this.getColumnNames(query);
//				
//		Collection result = this.entityManager.createNativeQuery(query).getResultList();
//		
//		return new QueryBrowserTO(result, columns, "");
//	}
//	
//	public List<String> getColumnNames(String query) {
//		
//		List<String> columnsNames = new ArrayList<String>();
//		
//		Connection connection = null;
//	    Statement stmt = null;
//	    ResultSet rs = null;
//	    
//		try {
//			
//			connection = QueryBrowserRepositoryImpl.getOracleConnection();
//			
//		    // Create a result set
//		    stmt = connection.createStatement();
//		    rs = stmt.executeQuery(query);
//
//		    // Get result set meta data
//		    ResultSetMetaData rsmd = rs.getMetaData();
//		    int numColumns = rsmd.getColumnCount();
//
//		    // Get the column names; column indices start from 1
//		    for (int i=1; i<numColumns+1; i++)
//		    	columnsNames.add(rsmd.getColumnName(i));
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//
//			if (connection != null) {
//		        try {
//		        	connection.close();
//		        } catch (SQLException sqlEx) { sqlEx.printStackTrace(); }
//
//		        connection = null;
//		    }
//
//		    if (rs != null) {
//		        try {
//		            rs.close();
//		        } catch (SQLException sqlEx) { sqlEx.printStackTrace(); }
//
//		        rs = null;
//		    }
//
//		    if (stmt != null) {
//		        try {
//		            stmt.close();
//		        } catch (SQLException sqlEx) { sqlEx.printStackTrace(); }
//
//		        stmt = null;
//		    }
//		}
//		
//		return columnsNames;
//	}
//
//	
//	public QueryBrowserTO executeSelectStatement(String query) {
//		
//		List<String> columnsNames = new ArrayList<String>();
//		List<Object> result = new ArrayList<Object>();
//		
//		Connection connection = null;
//	    Statement stmt = null;
//	    ResultSet rs = null;
//	    
//		try {
//			
//			connection = QueryBrowserRepositoryImpl.getOracleConnection();
//			
//		    // Create a result set
//		    stmt = connection.createStatement();
//		    rs = stmt.executeQuery(query);
//
//		    // Get result set meta data
//		    ResultSetMetaData rsmd = rs.getMetaData();
//		    int numColumns = rsmd.getColumnCount();
//
//		    // Get the column names; column indices start from 1
//		    for (int i=1; i<numColumns+1; i++)
//		    	columnsNames.add(rsmd.getColumnName(i));
//
//		    
//		    while (rs.next()) {
//		    	
//		    	List<String> object = new ArrayList<String>();
//		    	
//		    	for (int i=1; i<numColumns+1; i++)
//		    		object.add(rs.getString(i));
//		    	
//		    	result.add(object);
//		    }
//		    	
//		    
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//
//			if (connection != null) {
//		        try {
//		        	connection.close();
//		        } catch (SQLException sqlEx) { sqlEx.printStackTrace(); }
//
//		        connection = null;
//		    }
//
//		    if (rs != null) {
//		        try {
//		            rs.close();
//		        } catch (SQLException sqlEx) { sqlEx.printStackTrace(); }
//
//		        rs = null;
//		    }
//
//		    if (stmt != null) {
//		        try {
//		            stmt.close();
//		        } catch (SQLException sqlEx) { sqlEx.printStackTrace(); }
//
//		        stmt = null;
//		    }
//		}
//		
//		return new QueryBrowserTO(result, columnsNames, "");
//	}
//	
//	private static Connection getOracleConnection() throws Exception {
//
//	    String driver = "oracle.jdbc.driver.OracleDriver";
//	    String url = "jdbc:oracle:thin:@200.131.95.25:1521:desenvol";
//	    String username = "mbwladm";
//	    String password = "mbwladm";
//
//	    Class.forName(driver); // load Oracle driver
//	    Connection conn = DriverManager.getConnection(url, username, password);
//	    return conn;
//	 }
}
