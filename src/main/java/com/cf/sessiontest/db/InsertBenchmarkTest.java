package com.cf.sessiontest.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Formatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InsertBenchmarkTest {
    private static Logger logger = Logger.getLogger(InsertBenchmarkTest.class.getName());

    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://10.10.1.166:3306/db_test?useUnicode=true&amp;characterEncoding=UTF-8";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "sofmit";

    private static Random random = new Random(10000);

    private static final String TABLE_NAME = "uac_user";

    private CountDownLatch concurrencyLacth;
    private CountDownLatch endLatch;
    private int concurrencySize;

    public InsertBenchmarkTest() {
    }

    public InsertBenchmarkTest(int concurrencySize) {
        this.concurrencyLacth = new CountDownLatch(concurrencySize);
        this.endLatch = new CountDownLatch(concurrencySize);
        this.concurrencySize = concurrencySize;
    }

    private static void printHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(new Formatter().format("%15s|%15s|%15s|%15s", "CONCURRENT", "AVG (r/s)", "MIN (r/s)", "MAX (r/s)"));
        System.out.println(sb.toString());
    }

    public void run(boolean printResult) throws Exception {
        final List<Long> results = Collections.synchronizedList(new ArrayList<Long>());

        for (int idxConcurrent = 0; idxConcurrent < concurrencySize; idxConcurrent++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
//                        long time = execute(concurrencyLacth);
                        long time = executeQuery(concurrencyLacth);
                        results.add(time);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        endLatch.countDown();
                    }
                }
            }).start();
            concurrencyLacth.countDown();
        }
        endLatch.await();

        Collections.sort(results);

        if (printResult) {
            printResult(results);
        }
    }

    public long executeQuery(CountDownLatch countDownLatch) throws Exception {
        Connection conn = getConnection();
//        Map<String, Integer> columns = queryTableColumns(conn);
        PreparedStatement ps = conn.prepareStatement("select * from " + TABLE_NAME);
        try {
            countDownLatch.await();
            long start = System.currentTimeMillis();
            ps.execute();
            long stop = System.currentTimeMillis();
            return stop - start;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
            conn.rollback();
            conn.close();
            throw ex;
        } finally {
            conn.close();
        }
    }

    public long execute(CountDownLatch countDownLatch) throws Exception {
        Connection conn = getConnection();
        Map<String, Integer> columns = queryTableColumns(conn);
        String insertSQL = generateInsertSQL(columns);
        PreparedStatement ps = conn.prepareStatement(insertSQL);
        try {
            countDownLatch.await();
            long start = System.currentTimeMillis();
            execute(conn, ps, columns);
            long stop = System.currentTimeMillis();
            return stop - start;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
            conn.rollback();
            conn.close();
            throw ex;
        } finally {
            conn.close();
        }
    }

    public void execute(Connection conn, PreparedStatement ps, Map<String, Integer> columns) throws Exception {
        try {
            int idxColumn = 1;
            for (String column : columns.keySet()) {
                ps.setObject(idxColumn, generateColumnValue(columns.get(column)));
                idxColumn++;
            }

            ps.execute();
            conn.commit();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
            if (null != ex.getMessage()) {
                logger.log(Level.SEVERE, null, ex.getMessage());
            }
            conn.rollback();
            throw ex;
        }
    }

    private String generateInsertSQL(Map<String, Integer> columns) throws SQLException {
        StringBuilder sb = new StringBuilder();
        StringBuffer sbColumns = new StringBuffer();
        StringBuffer sbValues = new StringBuffer();

        sb.append("INSERT INTO ").append(TABLE_NAME);

        for (String column : columns.keySet()) {
            if (sbColumns.length() > 0) {
                sbColumns.append(",");
                sbValues.append(",");
            }
            sbColumns.append(column);
            sbValues.append("?");
        }
        sb.append("(").append(sbColumns).append(")");
        sb.append("VALUES");
        sb.append("(").append(sbValues).append(")");
        return sb.toString();
    }

    private Map<String, Integer> queryTableColumns(Connection conn) throws Exception {
        Map<String, Integer> columns = new LinkedHashMap<String, Integer>();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE 1=0";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ResultSetMetaData rsmd = rs.getMetaData();
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            if (rsmd.getColumnName(i).compareToIgnoreCase("ID") != 0) {
                columns.put(rsmd.getColumnName(i), rsmd.getColumnType(i));
            }
        }
        return columns;
    }

    private Object generateColumnValue(int type) {
        Object obj = null;
        switch (type) {
            case Types.DECIMAL:
            case Types.NUMERIC:
            case Types.DOUBLE:
            case Types.FLOAT:
            case Types.REAL:
            case Types.BIGINT:
            case Types.TINYINT:
            case Types.SMALLINT:
            case Types.INTEGER:
                obj = random.nextInt(10000);
                break;
            case Types.DATE:
                obj = Calendar.getInstance().getTime();
                break;
            case Types.TIMESTAMP:
                obj = new Timestamp(System.currentTimeMillis());
                break;
            default:
                obj = String.valueOf(random.nextInt(10000));
                break;
        }
        return obj;
    }

    private Connection getConnection() throws Exception {
        Class.forName(DB_DRIVER);
        Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        conn.setAutoCommit(false);
        return conn;
    }

    private void printResult(List<Long> results) {
        Double total = Double.valueOf(0);
        for (Long result : results) {
            total += result;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(new Formatter().format("%15s|%15s|%15s|%15s", concurrencySize, (total / (results.size() * 1000 * 1d)), results.get(0) / (1000 * 1d), results.get(results.size() - 1) / (1000 * 1d)));
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws Exception {
        printHeader();
        int[] concurrentList = new int[]{2000};
        for (int concurrencySize : concurrentList) {
            new InsertBenchmarkTest(concurrencySize).run(true);
        }
    }

}
