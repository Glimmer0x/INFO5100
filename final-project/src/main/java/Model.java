import org.web3j.protocol.exceptions.TransactionException;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassNAME Model
 * @Description TODO
 * @Author glimmer
 * @Date 2022/12/7 01:49
 * @Version 1.0
 */
public class Model
{
  private static Account account = new Account();
  private static WalletDataBase wDB;
  private static Vector<Vector<String>> transactionTable = new Vector<Vector<String>>();

  private static ArrayList<String> coins = new ArrayList<>(){
    {
      // add("eth");
      // add("0x7ef95a0FEE0Dd31b22626fA2e10Ee6A223F8a684");
      // add("0x8BaBbB98678facC7342735486C851ABD7A0d17Ca");
    }
  };

  private static HashMap<String, Integer> walletTableMap = new HashMap<>();;
  private static Vector<Vector<String>> walletTable = new Vector<Vector<String>>();



  public Model() throws SQLException, ClassNotFoundException
  {

    wDB = new WalletDataBase();
    initTransTable();
    initWalletCoins();
  }

  public static Account getAccount()
  {
    return account;
  }

  public static Vector<Vector<String>> getTransactionTable()
  {
    return transactionTable;
  }

  public static void initWalletCoins() throws SQLException
  {
    List<Coin> cl = wDB.getAllCoins();
    for (Coin c: cl
         ) {
      String coinAddress = c.getAddress();
      Vector<String> coinRow = new Vector<>();
      String coinBalance = "Querying...";
      if(coinAddress.equals("eth")){
        coinRow.add(c.getName());
        coinRow.add(coinBalance);
        coinRow.add("The Gas. No Address");
      }
      else {
        coinRow.add(c.getName());
        coinRow.add(coinBalance);
        coinRow.add(coinAddress);
      }
      coins.add(coinAddress);
      walletTableMap.put(coinAddress, walletTable.size());
      walletTable.add(coinRow);
    }
  }

  public static HashMap<String,Integer> initWalletTableMap()
      throws ExecutionException, InterruptedException, TimeoutException
  {
    HashMap<String, Integer> tableMap = new HashMap<>();

    for (String coinAddress: coins
         ) {
      if(coinAddress.equals("eth")){
        Vector<String> ethRow = new Vector<>();
        ethRow.add("BNB");
        String ethBalance = account.getBalance();
        ethRow.add(ethBalance);
        ethRow.add("The Gas. No Address");
        tableMap.put(coinAddress, walletTable.size());
        walletTable.add(ethRow);
      }
      else {
        Vector<String> coinRow = account.getCoinInfoByAddress(coinAddress);
        tableMap.put(coinAddress, walletTable.size());
        walletTable.add(coinRow);
      }
    }
    return tableMap;
  }

  public static Vector<Vector<String>> getWalletTable(){
    return walletTable;
  }

  public static void addCoinAddress(String s)
  {
    coins.add(s);
  }

  public static void deleteCoin(String s) throws SQLException
  {
    if(s.contains("GAS")) s = "eth";
    Integer idx = walletTableMap.get(s);
    Integer lastIdx = walletTable.size() - 1;
    Vector<String> lastRow = walletTable.get(lastIdx);
    wDB.deleteCoinByAddress(coins.get(idx));

    if(idx==lastIdx){
      walletTable.remove(idx);
      walletTableMap.remove(s);
      coins.remove(idx.intValue());
    }
    else{
      walletTable.set(idx, lastRow);
      walletTable.remove(lastIdx);
      walletTableMap.remove(s);
      String lastAddress = lastRow.get(2);
      lastAddress = lastAddress.contains("Gas") ? "eth" : lastAddress;
      walletTableMap.put(lastAddress, idx);
      coins.remove(lastIdx.intValue());
      coins.set(idx, lastAddress);
    }

  }

  public static void initTransTable() throws SQLException
  {
    List<TransactionRecord> transList = wDB.getAllTransactions();
    for (TransactionRecord transLog: transList
         ) {
      transactionTable.add(transLog.getTransaction());
    }
  }

  public static WalletWorker getWalletWorker(){
    return new WalletWorker();
  }

  static class WalletWorker extends SwingWorker<HashMap<String, Vector<String>>, Object>
  {
    @Override
    public HashMap<String, Vector<String>> doInBackground()
        throws ExecutionException, InterruptedException, TimeoutException
    {
      System.out.println("Start ----- Query all coins");
      HashMap<String, Vector<String>> map = new HashMap<>();
      for (String key: coins
      ) {
        if(key.equals("eth")){
          if(walletTableMap.containsKey(key)){
            Vector<String> ethRow = new Vector<>(walletTable.get(walletTableMap.get(key)));
            String preBalance = ethRow.get(1);
            String newBalance = account.getBalance();
            if(!preBalance.equals(newBalance)) {
              ethRow.set(1 , newBalance);
              map.put(key, ethRow);
            }
          }
          else{
            Vector<String> ethRow = new Vector<>();
            ethRow.add("BNB");
            String ethBalance = account.getBalance();
            ethRow.add(ethBalance);
            ethRow.add("The Gas. No Address");
            map.put(key, ethRow);
          }

        }
        else{
          if(walletTableMap.containsKey(key)){
            Vector<String> coinRow = new Vector<>(walletTable.get(walletTableMap.get(key)));
            String preBalance = coinRow.get(1);
            String newBalance = account.getCoinBalance(key);
            if(!preBalance.equals(newBalance)) {
              coinRow.set(1, newBalance);
              map.put(key, coinRow);
            }
          }
          else {
            Vector<String> coinRow = account.getCoinInfoByAddress(key);
            map.put(key, coinRow);
          }
        }
      }
      System.out.println("End ----- Query all coins");
      return map;
    }

    @Override
    protected void done() {
      try {
        HashMap<String, Vector<String>> newMap = get();
        for (String key: newMap.keySet()
             ) {
          if(walletTableMap.containsKey(key)){
            String newBalance = newMap.get(key).get(1);
            Vector<String> coinRow = walletTable.get(walletTableMap.get(key));
            coinRow.set(1, newBalance);
          }
          else{
            walletTableMap.put(key, walletTable.size());
            walletTable.add(newMap.get(key));
            Coin c = new Coin();
            String name = newMap.get(key).get(0);
            c.setCoin(key, name);
            wDB.addCoin(c);
          }
        }
        WalletPanel.updateTableUI();

      } catch (Exception ex) {
        new ErrorDialog(ex.getMessage()).setVisible(true);
      }
    }
  }

  public static TransactionWorker getTransactionWorker(String _coinAddress, String _receiver, Double _amount){
    return new TransactionWorker(_coinAddress, _receiver, _amount);
  }

  static class TransactionWorker extends SwingWorker<Vector<String>, Object>
  {
    private String coinAddress;
    private String receiver;
    private Double amount;

    public TransactionWorker(String _coinAddress, String _receiver, Double _amount){
      super();
      coinAddress = _coinAddress;
      receiver = _receiver;
      amount = _amount;
    }

    @Override
    public Vector<String> doInBackground()
        throws IOException, ExecutionException, InterruptedException, TimeoutException, TransactionException
    {
      Vector<String> transLog;
      if(coinAddress.equals("eth")){
        transLog = account.transferEth(receiver, amount);
      }
      else {
        transLog = account.transferCoin(coinAddress, receiver, amount);
      }
      return transLog;
    }

    @Override
    protected void done() {
      try {
        Vector<String> aTrans = get();
        transactionTable.add(aTrans);
        TransactionPanel.updateTableUI();
        TransactionRecord t = new TransactionRecord();
        t.setTransactionByVector(aTrans);
        wDB.addTransaction(t);
      } catch (Exception ex) {
        new ErrorDialog(ex.getMessage()).setVisible(true);
      }
    }
  }


}
