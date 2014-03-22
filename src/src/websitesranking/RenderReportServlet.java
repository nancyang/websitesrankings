package websitesranking;

import java.io.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

import org.json.simple.*;
import org.json.simple.parser.*;

/**
 * Servlet implementation class RenderReportServlet
 */
@WebServlet("/RenderReportServlet")
public class RenderReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DataSource pool;  // Database connection pool
    TrackingDao trackingDao = new TrackingDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RenderReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init( ) throws ServletException {
        try {
           // Create a JNDI Initial context to be able to lookup the DataSource
           InitialContext ctx = new InitialContext();
           // Lookup the DataSource, which will be backed by a pool
           //   that the application server provides.
           pool = (DataSource)ctx.lookup("java:comp/env/jdbc/trackingDB");
           if (pool == null)
              throw new ServletException("Unknown DataSource 'jdbc/trackingDB'");
        } catch (NamingException ex) {
           ex.printStackTrace();
        }
     }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "nocache");
        response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		StringWriter result = new StringWriter();
		
		// get received JSON data from request
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String postData = "";
        if (br != null) {
        	postData = br.readLine();
        }
        
		try {			
			JSONObject json = (JSONObject)new JSONParser().parse(postData);
			JSONObject resultObj = new JSONObject();
			JSONArray list = new JSONArray();
			List<Tracking> trackingList = new ArrayList<Tracking>();
			
			// get the website list
			if (json.get("type").equals("websiteslist")) {
				trackingList = trackingDao.websiteList(pool);				
				for (Tracking item : trackingList) {
					list.add(item.getWebsite());
				}
			}
			// render report
			else if (json.get("type").equals("submit")) {
				if (json.get("criteria").equals("date")) {
					// render repoty by date
					trackingList = trackingDao.getListByDate(pool, json.get("date").toString());
				}
				else if (json.get("criteria").equals("daterange")) {
					// render repoty by date range
					trackingList = trackingDao.getListByDateRange(pool, json.get("fromdate").toString(), json.get("todate").toString());
				}
				else if (json.get("criteria").equals("website")) {
					// render repoty by website
					String website = (json.get("website") == null ? "" : json.get("website").toString());
					trackingList = trackingDao.getListByWebsite(pool, website);
				}
				
				for (Tracking item : trackingList) {
					JSONObject trackingObj = new JSONObject();
					trackingObj.put("date", item.getDate());
					trackingObj.put("website", item.getWebsite());
					trackingObj.put("visit", item.getVisit());
					list.add(trackingObj);
				}
			}
			resultObj.put("result", list);
			resultObj.writeJSONString(result);
			// finally output the json string		
			out.print(result.toString());
		} catch (ParseException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
