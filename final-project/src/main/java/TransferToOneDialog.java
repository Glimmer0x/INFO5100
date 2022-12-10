import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassNAME transferToOneDialog
 * @Description TODO
 * @Author glimmer
 * @Date 2022/12/5 22:02
 * @Version 1.0
 */
public class TransferToOneDialog extends JDialog
{
  private JPanel dialogPanel;
  private JLabel addressLabel;
  private JTextField addressInput;
  private JLabel amountLabel;
  private JTextField amountInput;
  private JButton cancelButton;
  private JButton confirmButton;
  private String coinAddress;

  public TransferToOneDialog(String _coinAddress){
    dialogPanel = new JPanel();
    addressLabel = new JLabel("Input receiver's address:");
    addressInput = new JTextField();
    amountLabel = new JLabel("Input transfer amount:");
    amountInput = new JTextField();
    cancelButton = new JButton("Cancel");
    confirmButton = new JButton("Confirm");
    coinAddress = _coinAddress;

    setSize(200, 150);

    initLayout();
    initListener();

    setTitle("Dialog");
    setModal(true);
  }

  public void initLayout(){
    GridBagLayout layout = new GridBagLayout();
    dialogPanel.setLayout(layout);

    GridBagConstraints top = new GridBagConstraints();
    top.fill = GridBagConstraints.HORIZONTAL;
    top.gridx = 0;
    top.gridy = 0;
    top.gridwidth = 2;
    addressLabel.setPreferredSize(new Dimension(250,25));
    dialogPanel.add(addressLabel, top);

    GridBagConstraints mid = new GridBagConstraints();
    mid.fill = GridBagConstraints.HORIZONTAL;
    mid.gridx = 0;
    mid.gridy = 1;
    mid.gridwidth = 2;
    addressInput.setPreferredSize(new Dimension(250,25));
    dialogPanel.add(addressInput, mid);

    GridBagConstraints mid2 = new GridBagConstraints();
    mid2.fill = GridBagConstraints.HORIZONTAL;
    mid2.gridx = 0;
    mid2.gridy = 2;
    mid2.gridwidth = 2;
    amountLabel.setPreferredSize(new Dimension(250,25));
    dialogPanel.add(amountLabel, mid2);

    GridBagConstraints mid3 = new GridBagConstraints();
    mid3.fill = GridBagConstraints.HORIZONTAL;
    mid3.gridx = 0;
    mid3.gridy = 3;
    mid3.gridwidth = 2;
    amountInput.setPreferredSize(new Dimension(250,25));
    dialogPanel.add(amountInput, mid3);

    GridBagConstraints leftBottom = new GridBagConstraints();
    leftBottom.fill = GridBagConstraints.HORIZONTAL;
    leftBottom.gridx = 0;
    leftBottom.gridy = 4;
    leftBottom.gridwidth = 1;
    cancelButton.setPreferredSize(new Dimension(125,25));
    dialogPanel.add(cancelButton, leftBottom);

    GridBagConstraints rightBottom = new GridBagConstraints();
    rightBottom.fill = GridBagConstraints.HORIZONTAL;
    rightBottom.gridx = 1;
    rightBottom.gridy = 4;
    rightBottom.gridwidth = 1;
    confirmButton.setPreferredSize(new Dimension(125,25));
    dialogPanel.add(confirmButton, rightBottom);

    add(dialogPanel);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screenSize.width - getWidth()) / 2;
    int y = (screenSize.height - getHeight()) / 2;
    setLocation(x,y);
  }

  public void initListener(){
    cancelButton.addActionListener(e -> {
      setVisible(false);
      dispose();
    });

    confirmButton.addActionListener(e -> {
      String receiver = addressInput.getText();

      try {
        Double amount = Double.parseDouble(amountInput.getText());
        Model.transferCoin(coinAddress,receiver, amount);
        TransactionPanel.updateTableUI();
      }
      catch (Exception ex) {
        new ErrorDialog(ex.getMessage());
      }
      dispose();
    });
  }

}
