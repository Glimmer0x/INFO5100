import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @ClassNAME AddDialog
 * @Description TODO
 * @Author glimmer
 * @Date 2022/12/6 13:05
 * @Version 1.0
 */
public class AddDialog extends JDialog
{

  private JPanel dialogPanel;
  private JLabel labelText;
  private JTextField inputField;
  private JButton cancelButton;
  private JButton confirmButton;

  public AddDialog(String hint){
    dialogPanel = new JPanel();
    labelText = new JLabel(hint, JLabel.CENTER);
    inputField = new JTextField();
    cancelButton = new JButton("Cancel");
    confirmButton = new JButton("Confirm");

    setSize(200, 100);

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
    labelText.setPreferredSize(new Dimension(250,25));
    dialogPanel.add(labelText, top);

    GridBagConstraints mid = new GridBagConstraints();
    mid.fill = GridBagConstraints.HORIZONTAL;
    mid.gridx = 0;
    mid.gridy = 1;
    mid.gridwidth = 2;
    inputField.setPreferredSize(new Dimension(250,25));
    dialogPanel.add(inputField, mid);

    GridBagConstraints leftBottom = new GridBagConstraints();
    leftBottom.fill = GridBagConstraints.HORIZONTAL;
    leftBottom.gridx = 0;
    leftBottom.gridy = 2;
    leftBottom.gridwidth = 1;
    cancelButton.setPreferredSize(new Dimension(125,25));
    dialogPanel.add(cancelButton, leftBottom);

    GridBagConstraints rightBottom = new GridBagConstraints();
    rightBottom.fill = GridBagConstraints.HORIZONTAL;
    rightBottom.gridx = 1;
    rightBottom.gridy = 2;
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
  }

  public void setConfirmListener(ActionListener l){
    confirmButton.addActionListener(l);
  }

  public String getInput(){
    return inputField.getText();
  }
}
