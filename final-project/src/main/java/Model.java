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

  private static HashMap<String, Vector<String>> walletTableMap;;
  private static Vector<Vector<String>> walletTable;
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

//  private static Vector<Vector<String>> walletTable = new Vector<>();
  private static Vector<Vector<String>> transactionTable = new Vector<>();

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

  public void updateWalletTable(){

  }

  public void updateTransactionTable(){

  }

  public static HashMap<String,Vector<String>> initWalletTableMap()
      throws ExecutionException, InterruptedException, TimeoutException
  {
    HashMap<String, Vector<String>> tableMap = new HashMap<>();
    for (String coinAddress: coins
         ) {
      if(coinAddress.equals("eth")){
        Vector<String> ethRow = new Vector<>();
        ethRow.add("BNB");
        String ethBalance = account.getBalance();
        ethRow.add(ethBalance);
        ethRow.add("The Gas. No Address");
        tableMap.put(coinAddress, ethRow);
      }
      else {
        Vector<String> coinRow = account.getCoinInfoByAddress(coinAddress);
        tableMap.put(coinAddress, coinRow);
      }
    }
    walletTable = new Vector<Vector<String>>(tableMap.values());
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
        Vector<String> ethRow = walletTableMap.get(key);
        String preBalance = ethRow.get(1);
        String newBalance = account.getBalance();
        if(!preBalance.equals(newBalance)) ethRow.set(1 , newBalance);
      }
      else{
        if(walletTableMap.containsKey(key)){
          Vector<String> coinRow = walletTableMap.get(key);
          String preBalance = coinRow.get(1);
          String newBalance = account.getCoinBalance(key);
          if(!preBalance.equals(newBalance)) coinRow.set(1 , newBalance);
        }
        else {
          Vector<String> coinRow = account.getCoinInfoByAddress(key);
          walletTableMap.put(key, coinRow);
          walletTable.add(coinRow);
        }
      }
    }
  }

  public static void addCoinAddress(String s){
    coins.add(s);
  }


}
