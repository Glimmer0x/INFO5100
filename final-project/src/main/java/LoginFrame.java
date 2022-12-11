import javax.swing.*;
import java.awt.*;

/**
 * @ClassNAME Login
 * @Description TODO
 * @Author glimmer
 * @Date 2022/12/5 10:10
 * @Version 1.0
 */
public class LoginFrame extends JFrame
{
  private JPanel mainPanel;
  private JLabel inputLabel;
  private JTextField inputField;
  private JButton confirmButton;
  private JButton cancelButton;

  public LoginFrame(){
    mainPanel = new JPanel();

    inputLabel = new JLabel("Input a private key here:", JLabel.CENTER);
    inputField = new JTextField();
    confirmButton = new JButton("Confirm");
    cancelButton = new JButton("Cancel");
    cancelButton.setForeground(Color.red);
    cancelButton.setOpaque(true);

    setSize(200, 100);
    initLayout();
    setTitle("Import Private Key");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setContentPane(mainPanel);
  }

  public void initLayout(){
    GridBagLayout layout = new GridBagLayout();
    mainPanel.setLayout(layout);

    GridBagConstraints top = new GridBagConstraints();
    top.fill = GridBagConstraints.HORIZONTAL;
    top.gridx = 0;
    top.gridy = 0;
    top.gridwidth = 2;
    mainPanel.add(inputLabel, top);

    GridBagConstraints second = new GridBagConstraints();
    second.fill = GridBagConstraints.HORIZONTAL;
    second.gridx = 0;
    second.gridy = 1;
    second.gridwidth = 2;
    mainPanel.add(inputField, second);

    GridBagConstraints leftBottom = new GridBagConstraints();
    leftBottom.fill = GridBagConstraints.HORIZONTAL;
    leftBottom.gridx = 0;
    leftBottom.gridy = 3;
    leftBottom.gridwidth = 1;
    mainPanel.add(cancelButton, leftBottom);

    GridBagConstraints rightBottom = new GridBagConstraints();
    rightBottom.fill = GridBagConstraints.HORIZONTAL;
    rightBottom.gridx = 1;
    rightBottom.gridy = 3;
    rightBottom.gridwidth = 1;
    mainPanel.add(confirmButton, rightBottom);

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
