import org.web3j.protocol.exceptions.TransactionException;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

  private static HashMap<String, Integer> walletTableMap;
  private static Vector<Vector<String>> walletTable = new Vector<Vector<String>>();
  private static Vector<Vector<String>> transactionTable = new Vector<>();
  private static ArrayList<String> coins = new ArrayList<>(){
    {
      add("eth");
      add("0x7ef95a0FEE0Dd31b22626fA2e10Ee6A223F8a684");
      // add("0x8BaBbB98678facC7342735486C851ABD7A0d17Ca");
    }
  };

  public Model() throws ExecutionException, InterruptedException, TimeoutException
  {
    walletTableMap = initWalletTableMap();
  }

  public static Account getAccount()
  {
    return account;
  }

//  public static Vector<Vector<String>> getWalletTable(){
//    return walletTable;
//  }

  public static Vector<Vector<String>> getTransactionTable()
  {
    return transactionTable;
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

  public static void updateWalletTableMap() throws ExecutionException, InterruptedException, TimeoutException
  {
    for (String key: coins
         ) {
      if(key.equals("eth")){
        Vector<String> ethRow = walletTable.get(walletTableMap.get(key));
        String preBalance = ethRow.get(1);
        String newBalance = account.getBalance();
        if(!preBalance.equals(newBalance)) ethRow.set(1 , newBalance);
      }
      else{
        if(walletTableMap.containsKey(key)){
          Vector<String> coinRow = walletTable.get(walletTableMap.get(key));
          String preBalance = coinRow.get(1);
          String newBalance = account.getCoinBalance(key);
          if(!preBalance.equals(newBalance)) coinRow.set(1 , newBalance);
        }
        else {
          Vector<String> coinRow = account.getCoinInfoByAddress(key);
          walletTableMap.put(key, walletTable.size());
          walletTable.add(coinRow);
        }
      }
    }
  }

  public static void addCoinAddress(String s){
    coins.add(s);
  }

  public static void deleteCoin(String s){
    if(s.contains("GAS")) s = "eth";
    Integer idx = walletTableMap.get(s);
    coins.remove(idx);
    walletTable.remove(idx);
  }

  public static void transferCoin(String coinAddress, String receiver, Double amount)
      throws IOException, ExecutionException, InterruptedException, TimeoutException
  {
    Vector<String> transLog = account.transferCoin(coinAddress, receiver, amount);
    transactionTable.add(transLog);
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
      HashMap<String, Vector<String>> map = new HashMap<>();
      for (String key: coins
      ) {
        if(key.equals("eth")){
          Vector<String> ethRow = new Vector<>(walletTable.get(walletTableMap.get(key)));
          String preBalance = ethRow.get(1);
          String newBalance = account.getBalance();
          if(!preBalance.equals(newBalance)) {
            ethRow.set(1 , newBalance);
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
          }
        }
        WalletPanel.updateTableUI();

      } catch (Exception ex) {
        new ErrorDialog(ex.getMessage());
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
      } catch (Exception ex) {
        new ErrorDialog(ex.getMessage());
      }
    }
  }


}
