package websitesranking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrackingDao {
	public List<Tracking> websiteList(javax.sql.DataSource pool) throws SQLException {
		
		Connection conn = null;
		Statement  stmt = null;
		ResultSet rset = null;
        List<Tracking> trackingList = new ArrayList<Tracking>();

        try {
        	conn = pool.getConnection();	 
        	stmt = conn.createStatement();
        	rset = stmt.executeQuery("SELECT DISTINCT(website) AS website FROM tracking ORDER BY website");
			
            while (rset.next()) {
            	Tracking tracking = new Tracking();
            	tracking.setWebsite(rset.getString("website"));
                trackingList.add(tracking);
            }
        } finally {
            if (rset != null) try { rset.close(); } catch (SQLException ignore) {}
            if (stmt != null) try { stmt.close(); } catch (SQLException ignore) {}
            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
        }

        return trackingList;
    }
	
	public List<Tracking> getListByDate(javax.sql.DataSource pool, String date) throws SQLException {
		
		Connection conn = null;
		Statement  stmt = null;
		ResultSet rset = null;
        List<Tracking> trackingList = new ArrayList<Tracking>();

        try {
        	conn = pool.getConnection();	 
        	stmt = conn.createStatement();
        	rset = stmt.executeQuery("SELECT * FROM tracking WHERE visitdate = '" + date +
        			"' ORDER BY visit DESC LIMIT 5;");
			
            while (rset.next()) {
            	Tracking tracking = new Tracking();
            	tracking.setDate(rset.getString("visitdate"));
            	tracking.setWebsite(rset.getString("website"));
            	tracking.setVisit(rset.getString("visit"));
                trackingList.add(tracking);
            }
        } finally {
            if (rset != null) try { rset.close(); } catch (SQLException ignore) {}
            if (stmt != null) try { stmt.close(); } catch (SQLException ignore) {}
            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
        }

        return trackingList;
    }
	
	public List<Tracking> getListByDateRange(javax.sql.DataSource pool, String date1, String date2) throws SQLException {
		
		Connection conn = null;
		Statement  stmt = null;
		ResultSet rset = null;
        List<Tracking> trackingList = new ArrayList<Tracking>();

        try {
        	conn = pool.getConnection();	 
        	stmt = conn.createStatement();
        	rset = stmt.executeQuery("SELECT * FROM tracking WHERE visitdate BETWEEN '" + date1 +
        			"' AND '" + date2 + "' ORDER BY visit DESC LIMIT 5;");
			
            while (rset.next()) {
            	Tracking tracking = new Tracking();
            	tracking.setDate(rset.getString("visitdate"));
            	tracking.setWebsite(rset.getString("website"));
            	tracking.setVisit(rset.getString("visit"));
                trackingList.add(tracking);
            }
        } finally {
            if (rset != null) try { rset.close(); } catch (SQLException ignore) {}
            if (stmt != null) try { stmt.close(); } catch (SQLException ignore) {}
            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
        }

        return trackingList;
    }
	
	public List<Tracking> getListByWebsite(javax.sql.DataSource pool, String website) throws SQLException {
		
		Connection conn = null;
		Statement  stmt = null;
		ResultSet rset = null;
        List<Tracking> trackingList = new ArrayList<Tracking>();

        try {
        	conn = pool.getConnection();	 
        	stmt = conn.createStatement();
        	rset = stmt.executeQuery("SELECT * FROM tracking WHERE website = '" + website +
        			"' ORDER BY visit DESC LIMIT 5;");
			
            while (rset.next()) {
            	Tracking tracking = new Tracking();
            	tracking.setDate(rset.getString("visitdate"));
            	tracking.setWebsite(rset.getString("website"));
            	tracking.setVisit(rset.getString("visit"));
                trackingList.add(tracking);
            }
        } finally {
            if (rset != null) try { rset.close(); } catch (SQLException ignore) {}
            if (stmt != null) try { stmt.close(); } catch (SQLException ignore) {}
            if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
        }

        return trackingList;
    }

}
