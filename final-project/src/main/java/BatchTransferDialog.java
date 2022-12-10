import javax.swing.*;
import java.awt.*;

/**
 * @ClassNAME batchTransferDialog
 * @Description TODO
 * @Author glimmer
 * @Date 2022/12/5 22:02
 * @Version 1.0
 */
public class BatchTransferDialog extends JDialog
{
  private JPanel dialogPanel;
  private JLabel inputLabel;
  private JLabel inputExampleLabel;
  private JTextArea inputArea;
  private JButton cancelButton;
  private JButton confirmButton;

  public BatchTransferDialog(){
    dialogPanel = new JPanel();
    inputLabel = new JLabel("Input addresses and amount:");
    inputExampleLabel = new JLabel("<html>Example: <br> address1, amount1 <br> address2, amount2</html>");
    inputArea = new JTextArea();
    cancelButton = new JButton("Cancel");
    confirmButton = new JButton("Confirm");

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
    inputLabel.setPreferredSize(new Dimension(200,25));
    dialogPanel.add(inputLabel, top);

    GridBagConstraints mid0 = new GridBagConstraints();
    mid0.fill = GridBagConstraints.HORIZONTAL;
    mid0.gridx = 0;
    mid0.gridy = 1;
    mid0.gridwidth = 2;
    mid0.gridheight = 2;
    inputExampleLabel.setPreferredSize(new Dimension(200,75));
    dialogPanel.add(inputExampleLabel, mid0);

    GridBagConstraints mid = new GridBagConstraints();
    mid.fill = GridBagConstraints.HORIZONTAL;
    mid.gridx = 0;
    mid.gridy = 4;
    mid.gridwidth = 2;
    inputArea.setPreferredSize(new Dimension(200,25));
    dialogPanel.add(inputArea, mid);

    GridBagConstraints leftBottom = new GridBagConstraints();
    leftBottom.fill = GridBagConstraints.HORIZONTAL;
    leftBottom.gridx = 0;
    leftBottom.gridy = 5;
    leftBottom.gridwidth = 1;
    cancelButton.setPreferredSize(new Dimension(100,25));
    dialogPanel.add(cancelButton, leftBottom);

    GridBagConstraints rightBottom = new GridBagConstraints();
    rightBottom.fill = GridBagConstraints.HORIZONTAL;
    rightBottom.gridx = 1;
    rightBottom.gridy = 5;
    rightBottom.gridwidth = 1;
    confirmButton.setPreferredSize(new Dimension(100,25));
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


}
