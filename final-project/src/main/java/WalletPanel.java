import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassNAME walletPanel
 * @Description TODO
 * @Author glimmer
 * @Date 2022/12/5 15:38
 * @Version 1.0
 */
public class WalletPanel extends JPanel
{

  public static final String[] COLUMN_NAMES = {
      "Name", "Balance", "Address"};
  private JButton updateButton;
  private JButton addButton;
  private JButton deleteButton;
  private JButton transferToOneButton;
  // private JButton transferToBatch;
  private JScrollPane walletWrap;
  private static JTable walletTable;
  private DefaultTableModel tableModel;

  public WalletPanel(){
    updateButton = new JButton("Update");
    addButton = new JButton("Add");
    deleteButton = new JButton("Delete");
    transferToOneButton = new JButton("Transfer");
    // transferToBatch = new JButton("Batch Transfer");
    walletTable = new JTable(){
      @Override
      public Dimension getPreferredScrollableViewportSize()
      {
        return new Dimension(600, 280);
      }
    };

    tableModel = new DefaultTableModel( Model.getWalletTable() ,  new Vector<String>(Arrays.asList(COLUMN_NAMES)));
    walletTable.setModel(tableModel);
    walletTable.setFont(new Font("Times New Roman", Font.PLAIN, 14));
    walletTable.getTableHeader().setPreferredSize(new Dimension(200, 20));
    walletTable.getTableHeader().setFont(new Font("Times New Roman", Font.PLAIN, 16));


    walletWrap = new JScrollPane(walletTable);
    walletWrap.createVerticalScrollBar();

    initLayout();
    initListener();
  }

  public void initLayout(){
    GridBagLayout layout = new GridBagLayout();
    setLayout(layout);

    GridBagConstraints b0 = new GridBagConstraints();
    b0.fill = GridBagConstraints.HORIZONTAL;
    b0.gridx = 0;
    b0.gridy = 0;
    b0.gridwidth = 1;
    updateButton.setPreferredSize(new Dimension(150,25));
    add(updateButton, b0);

    GridBagConstraints b1 = new GridBagConstraints();
    b1.fill = GridBagConstraints.HORIZONTAL;
    b1.gridx = 1;
    b1.gridy = 0;
    b1.gridwidth = 1;
    addButton.setPreferredSize(new Dimension(150,25));
    add(addButton, b1);

    GridBagConstraints b2 = new GridBagConstraints();
    b2.fill = GridBagConstraints.HORIZONTAL;
    b2.gridx = 2;
    b2.gridy = 0;
    b2.gridwidth = 1;
    deleteButton.setPreferredSize(new Dimension(150,25));
    add(deleteButton, b2);

    GridBagConstraints b3 = new GridBagConstraints();
    b3.fill = GridBagConstraints.HORIZONTAL;
    b3.gridx = 3;
    b3.gridy = 0;
    b3.gridwidth = 1;
    transferToOneButton.setPreferredSize(new Dimension(150,25));
    add(transferToOneButton, b3);

    /*
    GridBagConstraints b4 = new GridBagConstraints();
    b4.fill = GridBagConstraints.HORIZONTAL;
    b4.gridx = 4;
    b4.gridy = 0;
    b4.gridwidth = 1;
    transferToBatch.setPreferredSize(new Dimension(125,25));
    add(transferToBatch, b4);
    */

    GridBagConstraints main = new GridBagConstraints();
    main.fill = GridBagConstraints.HORIZONTAL;
    main.gridx = 0;
    main.gridy = 1;
    main.gridwidth = 4;
    add(walletWrap, main);
  }

  public void initListener(){
    updateButton.addActionListener(e -> {
      try {
        Model.WalletWorker walletWorker = Model.getWalletWorker();
        walletWorker.execute();
      }
      catch (Exception ex) {
        new ErrorDialog(ex.getMessage());
      }

    });
    addButton.addActionListener(e -> {
      new AddCoinDialog().setVisible(true);
    });
    deleteButton.addActionListener(e -> {
      Integer row= walletTable.getSelectedRow();
      if(row != null){
        String s = (String) tableModel.getValueAt(row, 2);
        try {
          Model.deleteCoin(s);
          tableModel.removeRow(row);
          walletTable.updateUI();
        }
        catch (Exception ex) {
          new ErrorDialog(ex.getMessage());
        }
      }
      else {
        new ErrorDialog("Please select a coin!");
      }
    });
    transferToOneButton.addActionListener(e -> {
      Integer row= walletTable.getSelectedRow();
      if(row != null){
        String s = (String) tableModel.getValueAt(row, 2);
        new TransferToOneDialog(s).setVisible(true);
      }
      else {
        new ErrorDialog("Please select a coin!");
      }
    });
    // transferToBatch.addActionListener(e -> new BatchTransferDialog().setVisible(true));
  }

  public static void updateTableUI(){
    walletTable.updateUI();
  }
}
