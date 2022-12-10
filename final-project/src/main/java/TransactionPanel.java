import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * @ClassNAME TranscationPanel
 * @Description TODO
 * @Author glimmer
 * @Date 2022/12/5 21:32
 * @Version 1.0
 */
public class TransactionPanel extends JPanel
{

  public static final String[] COLUMN_NAMES = {
      "Status", "From", "To", "Coin", "Out", "Hash"};

  private JScrollPane transactionWrap;
  private JTable transactionTable;
  private DefaultTableModel tableModel;

  public TransactionPanel(){
    transactionTable = new JTable(){
      @Override
      public Dimension getPreferredScrollableViewportSize()
      {
        return new Dimension(600, 280);
      }
    };

    tableModel = new DefaultTableModel( new String[][]{ COLUMN_NAMES }, COLUMN_NAMES);
    transactionTable.setModel(tableModel);
    transactionTable.setFont(new Font("Times New Roman", Font.PLAIN, 14));
    transactionTable.getTableHeader().setPreferredSize(new Dimension(100, 20));
    transactionTable.getTableHeader().setFont(new Font("Times New Roman", Font.PLAIN, 16));

    transactionWrap = new JScrollPane(transactionTable);
    transactionWrap.createVerticalScrollBar();
    add(transactionWrap);
  }

}
