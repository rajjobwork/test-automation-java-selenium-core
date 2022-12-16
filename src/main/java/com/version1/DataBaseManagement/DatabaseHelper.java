package com.version1.DataBaseManagement;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.version1.webdriver.configuration.TestConfigHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DatabaseHelper {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseHelper.class);
    private static final String jdbcUrl;
    private static final String jdbcDriver;
    private static final String jdbcUser;
    private static final String jdbcPwd;
    protected static Connection conn = null;
    public static Map<String, Object> DBConfig = new HashMap<>();
    
    static {
    	Iterator<Map.Entry<String, JsonNode>> templateConfigs = TestConfigHelper.get()
                .getTemplateConfigs("Cucumber")
                .fields();
           while (templateConfigs.hasNext()) {
            	HashMap.Entry<String, JsonNode> entry = templateConfigs.next();
            	DBConfig.put(entry.getKey(), entry.getValue().asText());
           }
        jdbcUrl = DBConfig.get("jdbcUrl").toString();
        jdbcDriver = DBConfig.get("jdbcDriver").toString();
        jdbcUser = DBConfig.get("jdbcUser").toString();
        jdbcPwd = DBConfig.get("jdbcPwd").toString();
        conn=setUpConnection();
    }

    protected static Connection setUpConnection() {
        try {
            DbUtils.loadDriver(jdbcDriver);
            return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPwd);
        } catch (SQLException se) {
            LOG.info(se.getMessage());
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return conn;
    }

    public List executeQuery(String sqlQuery) throws SQLException {
        return getQueryRunner().query(conn, sqlQuery, new MapListHandler());
    }

    protected QueryRunner getQueryRunner() {
        return new QueryRunner();
    }
}