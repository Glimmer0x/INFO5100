/**
 * @ClassNAME testAccount
 * @Description TODO
 * @Author glimmer
 * @Date 2022/12/8 22:39
 * @Version 1.0
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAccount
{
  private final Account account = new Account();
  private String key = "32becf791e88596313c1cbfc1ed09b5acf5bd1d88590d86cd6370ff3e31b8a61";
  private String address = "0x2248Ff179A34EC73a26308d3300647bFC0a4c3fF";

  @Test
  void calculateAddress(){
    assertEquals(address, Account.getAddressByKey(key));
  }
}
