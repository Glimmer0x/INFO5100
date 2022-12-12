import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @ClassNAME ErrorDiglog
 * @Description TODO
 * @Author glimmer
 * @Date 2022/12/8 23:26
 * @Version 1.0
 */
public class ErrorDialog extends JDialog
{
  private JPanel dialogPanel;
  private JLabel labelText;
  private JButton cancelButton;
  private JButton confirmButton;

  public ErrorDialog(String hint){
    dialogPanel = new JPanel();
    labelText = new JLabel(hint, JLabel.CENTER);
    cancelButton = new JButton("Close");
    confirmButton = new JButton("Confirm");

    setSize(200, 100);

    initLayout();
    initListener();

    setTitle("Error Dialog");
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
    labelText.setPreferredSize(new Dimension(200,25));
    dialogPanel.add(labelText, top);

    GridBagConstraints rightBottom = new GridBagConstraints();
    rightBottom.fill = GridBagConstraints.HORIZONTAL;
    rightBottom.gridx = 0;
    rightBottom.gridy = 1;
    rightBottom.gridwidth = 2;
    confirmButton.setPreferredSize(new Dimension(200,25));
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
      setVisible(false);
      dispose();
    });
  }
}
