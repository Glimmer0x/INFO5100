/**
 * @ClassNAME accountDialog
 * @Description TODO
 * @Author glimmer
 * @Date 2022/12/5 22:01
 * @Version 1.0
 */
public class ChangeAccountDialog extends AddDialog
{
  public ChangeAccountDialog(){
    super("Import a new private key:");
    super.setConfirmListener(e -> {
      MainFrame.getModel().getAccount().setKey(super.getInput());
      setVisible(false);
      dispose();
    });
  }

}
