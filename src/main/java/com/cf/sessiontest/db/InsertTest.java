//package com.cf.sessiontest.db;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.sql.Timestamp;
//import java.sql.Types;
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Collections;
//import java.util.Formatter;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Random;
//import java.util.UUID;
//import java.util.concurrent.CountDownLatch;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class InsertTest {
//    private static Logger logger = Logger.getLogger(InsertTest.class.getName());
//
//    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
//    private static final String DB_URL = "jdbc:mysql://10.10.1.79:3306/db_test?useUnicode=true&amp;characterEncoding=UTF-8";
//    private static final String DB_USERNAME = "root";
//    private static final String DB_PASSWORD = "root";
//
//    private static Random random = new Random(10000);
//
//    private static final String TABLE_NAME = "uac_user";
//
//    private int batchSize;
//
//    private int concurrent;
//
//    private int sampling;
//
//    public static void main(String[] args) throws Exception {
//        printHeader();
//
//        int[] concurrentList = new int[]{1000};
//        int[] batchSizeList = new int[]{1};
//
//        for (int batchSize : batchSizeList) {
//            for (int concurrent : concurrentList) {
//                new InsertTest(batchSize, concurrent).run(true);
//            }
////            Thread.sleep(10000);
//        }
//    }
//
//    public InsertTest(final int batchSize, final int concurrent) throws Exception {
//        this.batchSize = batchSize;
//        this.concurrent = concurrent;
//        this.sampling = 1;
//    }
//
//    public InsertTest(final int batchSize, final int concurrent, final int sampling) throws Exception {
//        this.batchSize = batchSize;
//        this.concurrent = concurrent;
//        this.sampling = sampling;
//    }
//
//    public void run(boolean printResult) throws Exception {
//        final List<String> results = Collections.synchronizedList(new ArrayList<String>());
//        final CountDownLatch startGate = new CountDownLatch(concurrent);
//        final CountDownLatch endGate = new CountDownLatch(concurrent);
//
//        for (int idxConcurrent = 0; idxConcurrent < concurrent; idxConcurrent++) {
//            new Thread(new Runnable() {
//                public void run() {
//                    try {
//                        long time = execute(startGate);
//                        double avg = time / (batchSize * sampling * 1000 * 1d);
//                        DecimalFormat df = new DecimalFormat("0.4f");
//                        String t = String.format("%.4f", avg);
//                        results.add(t);
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    } finally {
//                        endGate.countDown();
//                    }
//                }
//            }).start();
//            startGate.countDown();
//        }
//        endGate.await();
//
//        Collections.sort(results);
//
//        if (printResult) {
//            printResult(batchSize, concurrent, results);
//        }
//    }
//
//    public long execute(CountDownLatch countDownLatch) throws Exception {
//        Connection conn = getConnection();
//        Map<String, Integer> columns = queryTableColumns(conn);
//        String insertSQL = generateInsertSQL(columns);
//        PreparedStatement ps = conn.prepareStatement(insertSQL);
//        try {
//            countDownLatch.await();
//            long start = System.currentTimeMillis();
//            execute(conn, ps, columns);
//            long stop = System.currentTimeMillis();
////            System.out.println("outer user time:" + (stop - start));
//            return stop - start;
//        } catch (Exception ex) {
//            logger.log(Level.SEVERE, null, ex);
//            conn.rollback();
//            conn.close();
//            throw ex;
//        } finally {
//            conn.close();
//        }
//    }
//
//    public void execute(Connection conn, PreparedStatement ps, Map<String, Integer> columns) throws Exception {
//        try {
//            int idxColumn = 1;
//            for (String column : columns.keySet()) {
//                ps.setObject(idxColumn, generateColumnValue(columns.get(column)));
//                idxColumn++;
//            }
//
//            long start = System.currentTimeMillis();
//            ps.execute();
//            conn.commit();
//            long stop = System.currentTimeMillis();
////            System.out.println("use time:" + (stop - start));
//        } catch (Exception ex) {
//            logger.log(Level.SEVERE, null, ex);
//            if (null != ex.getMessage()) {
//                logger.log(Level.SEVERE, null, ex.getMessage());
//            }
//            conn.rollback();
//            throw ex;
//        }
//    }
//
//    private String generateInsertSQL(Map<String, Integer> columns) throws SQLException {
//        StringBuilder sb = new StringBuilder();
//        StringBuffer sbColumns = new StringBuffer();
//        StringBuffer sbValues = new StringBuffer();
//
//        sb.append("INSERT INTO ").append(TABLE_NAME);
//
//        for (String column : columns.keySet()) {
//            if (sbColumns.length() > 0) {
//                sbColumns.append(",");
//                sbValues.append(",");
//            }
//            sbColumns.append(column);
//            sbValues.append("?");
//        }
//        sb.append("(").append(sbColumns).append(")");
//        sb.append("VALUES");
//        sb.append("(").append(sbValues).append(")");
//        return sb.toString();
//    }
//
//    private Map<String, Integer> queryTableColumns(Connection conn) throws Exception {
//        Map<String, Integer> columns = new LinkedHashMap<String, Integer>();
//        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE 1=0";
//        Statement stmt = conn.createStatement();
//        ResultSet rs = stmt.executeQuery(sql);
//        ResultSetMetaData rsmd = rs.getMetaData();
//        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//            if (rsmd.getColumnName(i).compareToIgnoreCase("ID") != 0) {
//                columns.put(rsmd.getColumnName(i), rsmd.getColumnType(i));
//            }
//        }
//        return columns;
//    }
//
//    private Object generateColumnValue(int type) {
//        Object obj = null;
//        switch (type) {
//            case Types.DECIMAL:
//            case Types.NUMERIC:
//            case Types.DOUBLE:
//            case Types.FLOAT:
//            case Types.REAL:
//            case Types.BIGINT:
//            case Types.TINYINT:
//            case Types.SMALLINT:
//            case Types.INTEGER:
//                obj = random.nextInt(10000);
//                break;
//            case Types.DATE:
//                obj = Calendar.getInstance().getTime();
//                break;
//            case Types.TIMESTAMP:
//                obj = new Timestamp(System.currentTimeMillis());
//                break;
//            default:
//                obj = String.valueOf(random.nextInt(10000));
//                break;
//        }
//        return obj;
//    }
//
//    private Connection getConnection() throws Exception {
//        Class.forName(DB_DRIVER);
//        Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
//        conn.setAutoCommit(false);
//        return conn;
//    }
//
//    private static void printHeader() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("\n");
//        sb.append(new Formatter().format("%15s|%15s|%15s|%15s|%15s", "BATCH_SIZE", "CONCURRENT", "AVG (r/s)", "MIN (r/s)", "MAX (r/s)"));
//        System.out.println(sb.toString());
//    }
//
//    private static void printResult(int batch, int concurrent, List<String> results) {
//        Double total = Double.valueOf(0);
//        for (String result : results) {
//            total += Double.valueOf(result);
//        }
//        StringBuilder sb = new StringBuilder();
//        sb.append(new Formatter().format("%15s|%15s|%15s|%15s|%15s", batch, concurrent, (total / results.size()), results.get(0), results.get(results.size() - 1)));
//        System.out.println(sb.toString());
//    }
//}
