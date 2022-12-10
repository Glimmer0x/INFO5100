/**
 * @ClassNAME Account
 * @Description web3j + key + address + transfer
 * @Author glimmer
 * @Date 2022/12/6 19:13
 * @Version 1.0
 */

import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Account
{
  private Credentials credentials =Credentials.create(getKeyPair("32becf791e88596313c1cbfc1ed09b5acf5bd1d88590d86cd6370ff3e31b8a61"));;
  private String address = Keys.toChecksumAddress(credentials.getAddress());
  private String rpcURL = "https://data-seed-prebsc-1-s1.binance.org:8545";
  private Web3j web3j = Web3j.build(new HttpService(rpcURL));

  public void setKey(String s){
    try {
      ECKeyPair keyPair = getKeyPair(s);
      credentials = Credentials.create(keyPair);
      address = Keys.toChecksumAddress(Keys.getAddress(keyPair));
    } catch (Exception e){
      new ErrorDialog(e.getMessage());
    }
  }

  public static ECKeyPair getKeyPair(String privateKeyInHex){
    BigInteger privateKeyInBT = new BigInteger(privateKeyInHex, 16);
    ECKeyPair keyPair = ECKeyPair.create(privateKeyInBT);
    return keyPair;
  }

  public static String getAddressByKey(String privateKeyInHex) {
    ECKeyPair keyPair = getKeyPair(privateKeyInHex);
    return Keys.toChecksumAddress(Keys.getAddress(keyPair));
  }

  public String getAddress(){
    return address;
  }

  public String getBalance(){
    String ethBalance = "0";
    try{
      ethBalance = getEthBalance(web3j, address).toString();
    } catch (Exception e){
      new ErrorDialog(e.getMessage());
    }

    return ethBalance;
  }
  public String getCoinBalance(String coinAddress) throws ExecutionException, InterruptedException, TimeoutException
  {
    ERC20 coin = ERC20.load(coinAddress, web3j, credentials, new DefaultGasProvider());
    BigInteger decimal = coin.decimals().sendAsync().get(5, TimeUnit.SECONDS);
    BigInteger tokenBalance = coin.balanceOf(address).sendAsync().get(5, TimeUnit.SECONDS);
    BigDecimal balance = new BigDecimal(tokenBalance).divide(new BigDecimal(Math.pow(10, decimal.intValue())));
    return balance.toString();
  }

  public static BigDecimal getEthBalance(Web3j web3j, String walletAddress)
      throws ExecutionException, InterruptedException, TimeoutException
  {
    EthGetBalance ethGetBalance = web3j.ethGetBalance(walletAddress, DefaultBlockParameterName.LATEST).sendAsync().get(5, TimeUnit.SECONDS);
    BigInteger wei = ethGetBalance.getBalance();
    return Convert.fromWei(new BigDecimal(wei), Convert.Unit.ETHER);
  }

  public Vector<String> getCoinInfoByAddress(String coinAddress) throws ExecutionException, InterruptedException, TimeoutException
  {
    ERC20 coin = ERC20.load(coinAddress, web3j, credentials, new DefaultGasProvider());
    String symbol = coin.symbol().sendAsync().get(5, TimeUnit.SECONDS);
    BigInteger decimal = coin.decimals().sendAsync().get(5, TimeUnit.SECONDS);
    BigInteger tokenBalance = coin.balanceOf(address).sendAsync().get(5, TimeUnit.SECONDS);
    BigDecimal balance = new BigDecimal(tokenBalance).divide(new BigDecimal(Math.pow(10, decimal.intValue())));

    Vector<String> coinInfo = new Vector<>();
    coinInfo.add(symbol);
    coinInfo.add(balance.toString());
    coinInfo.add(coinAddress);
    return coinInfo;
  }

  public static void main(String[] args) throws Exception
  {
  }

}
