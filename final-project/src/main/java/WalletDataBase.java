import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.jdbc.db.SqliteDatabaseType;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.table.TableUtils;
import org.web3j.protocol.core.methods.request.Transaction;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassNAME DataBase
 * @Description TODO
 * @Author glimmer
 * @Date 2022/12/10 15:37
 * @Version 1.0
 */
public class WalletDataBase
{
  private static String dataBaseUrl = "jdbc:sqlite:./db/walletDB" ;
  private JdbcPooledConnectionSource connection;
  private Dao<Coin, String> coinDao;
  private Dao<TransactionRecord, String> transDao;

  public WalletDataBase() throws ClassNotFoundException, SQLException
  {
    Class.forName("org.sqlite.JDBC");
    createDataBase(dataBaseUrl);
    connection = new JdbcPooledConnectionSource(dataBaseUrl);

    TableUtils.createTableIfNotExists(connection, Coin.class);
    TableUtils.createTableIfNotExists(connection, TransactionRecord.class);

    coinDao = DaoManager.createDao(connection, Coin.class);
    transDao = DaoManager.createDao(connection, TransactionRecord.class);
  }

  public void cleanAllCoins() throws SQLException
  {
    coinDao.delete(coinDao.queryForAll());
  }

  public void cleanAllTrans() throws SQLException
  {
    transDao.delete(transDao.queryForAll());
  }

  public List<Coin> getAllCoins() throws SQLException
  {
    return coinDao.queryForAll();
  }


  public void addCoin(Coin c) throws SQLException
  {
    if(!coinDao.idExists(c.getAddress())){
      coinDao.create(c);
    }
  }

  public void deleteCoinByAddress(String s) throws SQLException
  {
    if(coinDao.idExists(s)){
      Coin c = coinDao.queryForId(s);
      coinDao.delete(c);
    }
  }

  public void deleteCoin(Coin c) throws SQLException
  {
    if(coinDao.idExists(c.getAddress())){
      coinDao.delete(c);
    }
  }

  public List<TransactionRecord> getAllTransactions() throws SQLException
  {
    return transDao.queryForAll();
  }

  public void addTransaction(TransactionRecord t) throws SQLException
  {
    if(!transDao.idExists(t.getHash())){
      transDao.create(t);
    }
  }

  public List<TransactionRecord> getTransactionsByFrom(String from) throws SQLException
  {
    QueryBuilder<TransactionRecord, String> queryBuilder = transDao.queryBuilder();
    queryBuilder.where().eq(TransactionRecord.FROM_FIELD_NAME, from);
    PreparedQuery<TransactionRecord> preparedQuery = queryBuilder.prepare();
    return transDao.query(preparedQuery);
  }

  public void close() throws Exception
  {
    connection.close();
  }

  public static void createDataBase(String url){
    try (Connection conn = DriverManager.getConnection(url)) {
      if (conn != null) {
        DatabaseMetaData meta = conn.getMetaData();
        System.out.println("The driver name is " + meta.getDriverName());
        System.out.println("The database has been created.");
      }

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public static void main(String[] args) throws Exception
  {
    WalletDataBase w = new WalletDataBase();
    w.cleanAllCoins();
    w.cleanAllTrans();
  }


}
