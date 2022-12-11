import javax.swing.*;
import java.awt.*;

/**
 * @ClassNAME MainFrame
 * @Description TODO
 * @Author glimmer
 * @Date 2022/12/5 16:09
 * @Version 1.0
 */
public class MainFrame extends JFrame
{
  private static Model model;

  static {
    try {
      model = new Model();
    }
    catch (Exception ex) {
      new ErrorDialog(ex.getMessage());
    }
  }

  private JButton changeButton;
  private JLabel currentAccountLabel;
  private JPanel mainPanel;
  private JPanel headerPanel;
  private JTabbedPane tab;
  private TransactionPanel transactionPanel;
  private WalletPanel walletPanel;


  public MainFrame(){
    changeButton = new JButton("Change Account");
    currentAccountLabel = new JLabel("Current Account: " + model.getAccount().getAddress(), JLabel.CENTER);
    headerPanel = new JPanel();
    tab = new JTabbedPane();
    mainPanel = new JPanel();
    transactionPanel = new TransactionPanel();
    walletPanel = new WalletPanel();

    setSize(640, 430);

    initLayout();
    initListener();

    setTitle("Your own wallet");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setContentPane(mainPanel);

    Model.getWalletWorker().execute();
  }

  public void initLayout(){

    GridBagLayout layout = new GridBagLayout();
    headerPanel.setLayout(layout);

    GridBagConstraints t1 = new GridBagConstraints();
    t1.fill = GridBagConstraints.HORIZONTAL;
    t1.gridx = 0;
    t1.gridy = 0;
    t1.gridwidth = 1;
    changeButton.setPreferredSize(new Dimension(150,25));
    headerPanel.add(changeButton, t1);

    GridBagConstraints t2 = new GridBagConstraints();
    t2.fill = GridBagConstraints.HORIZONTAL;
    t2.gridx = 1;
    t2.gridy = 0;
    currentAccountLabel.setPreferredSize(new Dimension(450,25));
    headerPanel.add(currentAccountLabel, t2);

    mainPanel.add(headerPanel, 0);

    tab.setForeground(Color.BLACK);
    tab.addTab("Wallet", walletPanel);
    tab.addTab("Transaction", transactionPanel);
    mainPanel.add(tab);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screenSize.width - getWidth()) / 2;
    int y = (screenSize.height - getHeight()) / 2;
    setLocation(x,y);
  }

  public void initListener(){
    changeButton.addActionListener(e->{
      new ChangeAccountDialog().setVisible(true);
      updateAddress();
      Model.WalletWorker walletWorker = Model.getWalletWorker();
      walletWorker.execute();
    });
  }

  public static void main(String[] args){
    MainFrame app = new MainFrame();
    app.setVisible(true);
  }

  public static Model getModel(){
    return model;
  }

  public void updateAddress(){
    currentAccountLabel.setText("Current Account: " + model.getAccount().getAddress());
  }

}
