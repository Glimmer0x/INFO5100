import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassNAME addCoinDialog
 * @Description TODO
 * @Author glimmer
 * @Date 2022/12/5 22:01
 * @Version 1.0
 */
public class AddCoinDialog extends AddDialog
{
  public AddCoinDialog(){
    super("Input a new coin address:");
    super.setConfirmListener(e -> {
        String coinAddress = super.getInput();
      try {
        Model.addCoinAddress(coinAddress);
        Model.WalletWorker walletWorker = Model.getWalletWorker();
        walletWorker.execute();
      }
      catch (Exception ex) {
        new ErrorDialog(ex.getMessage());
      }
      dispose();
    });
  }
}
