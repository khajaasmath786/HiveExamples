package HiveJDBCClient;

import java.io.PrintStream;
import java.sql.*;

//Code: http://saurzcode.in/2015/01/connect-hiveserver2-service-jdbc-client/
public class HiveJdbcClient
{

    public HiveJdbcClient()
    {
    }

    public static void main(String args[])
        throws SQLException
    {
        try
        {
            Class.forName(driverName);
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        //hive --service hiveserver -p 10000 &  --> Run this command before running this prog. It will take time to start
        Connection con = DriverManager.getConnection("jdbc:hive://localhost:10000/default", "", "");
        Statement stmt = con.createStatement();
        String tableName = "testHiveDriverTable";
        stmt.executeQuery((new StringBuilder("drop table ")).append(tableName).toString());
        ResultSet res = stmt.executeQuery((new StringBuilder("create table ")).append(tableName).append(" (key int, value string)").toString());
        String sql = (new StringBuilder("show tables '")).append(tableName).append("'").toString();
        System.out.println((new StringBuilder("Running: ")).append(sql).toString());
        res = stmt.executeQuery(sql);
        if(res.next())
            System.out.println(res.getString(1));
        sql = (new StringBuilder("describe ")).append(tableName).toString();
        System.out.println((new StringBuilder("Running: ")).append(sql).toString());
        for(res = stmt.executeQuery(sql); res.next(); System.out.println((new StringBuilder(String.valueOf(res.getString(1)))).append("\t").append(res.getString(2)).toString()));
        String filepath = "/tmp/a.txt";
        sql = (new StringBuilder("load data local inpath '")).append(filepath).append("' into table ").append(tableName).toString();
        System.out.println((new StringBuilder("Running: ")).append(sql).toString());
        res = stmt.executeQuery(sql);
        sql = (new StringBuilder("select * from ")).append(tableName).toString();
        System.out.println((new StringBuilder("Running: ")).append(sql).toString());
        for(res = stmt.executeQuery(sql); res.next(); System.out.println((new StringBuilder(String.valueOf(String.valueOf(res.getInt(1))))).append("\t").append(res.getString(2)).toString()));
        sql = (new StringBuilder("select count(1) from ")).append(tableName).toString();
        System.out.println((new StringBuilder("Running: ")).append(sql).toString());
        for(res = stmt.executeQuery(sql); res.next(); System.out.println(res.getString(1)));
    }

    private static String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver";

}