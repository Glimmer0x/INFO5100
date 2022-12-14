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
      String newKey = super.getInput();
      if(newKey.equals("")){
        new ErrorDialog("Please input a private key!").setVisible(true);
        return;
      }
      MainFrame.getModel().getAccount().setKey(newKey);
      setVisible(false);
      dispose();
    });
  }

}
