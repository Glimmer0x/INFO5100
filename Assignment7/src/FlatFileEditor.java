import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @ClassNAME FlatFileEditor
 * @Description A graphic interface
 * A text box that can be edited but defaults to the file name you are reading from
 * A button to read from the file
 * A text box to load the five lines of the file read
 * A button to write to the file
 * A text box for the user to input the new file name
 * A text box to show the first five lines that is written to the file so the user knows what data will be in the file
 *
 * @Author Liangxi Liu
 * @Date 2022/10/26 10:08
 * @Version 1.0
 */
public class FlatFileEditor extends JFrame
{
  private JTextField newFileName;
  private JButton readFileBtn;
  private JButton writeFileBtn;
  private JTextArea newFileLines;
  private JLabel fileName;
  private JTextArea fileLines;
  private JPanel panelMain;
  private String filePath = "annual.csv";
  private ArrayList<String> lines;
  private ArrayList<String> newLines;

  public static void main(String[] args)
  {
    // creat graphic interface and set its frame
    FlatFileEditor e = new FlatFileEditor();
    e.setContentPane(e.panelMain);
    e.setTitle("Flat File Editor");
    e.setSize(600, 500);
    e.setVisible(true);
    e.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  // filter content and only keep the first three column
  private String catchFirstThreeFields(String line){
    String[] splitFields = line.split(",");
    String newline = "";
    for (int i = 0; i < 3; i++) {
      String dot = ",";
      if(i==2){
        dot = "";
      }
      newline = newline + splitFields[i] + dot ;
    }
    return newline;
  }

  // initialize texts and events
  public FlatFileEditor()
  {
    fileName.setText(filePath);
    newFileName.setText(filePath);

    // add click event of readFileBtn to read file and creat new content
    readFileBtn.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        lines = new ArrayList<>();
        newLines = new ArrayList<>();
        try {
          File csvFile = new File("./src/"+filePath);
          Scanner reader = new Scanner(csvFile);
          while (reader.hasNextLine()){
            String line = reader.nextLine();
            lines.add(line+"\n");
            newLines.add(catchFirstThreeFields(line)+"\n");
          }
        }
        catch (FileNotFoundException ex) {
          ex.printStackTrace();
        }
        String shows = "";
        String newShows = "";
        for (int i = 0; i < 5; i++) {
          shows = shows + lines.get(i);
          newShows = newShows + newLines.get(i);
        }
        fileLines.setEditable(true);
        fileLines.setText(shows);
        fileLines.setEditable(false);
        newFileLines.setEditable(true);
        newFileLines.setText(newShows);
        newFileLines.setEditable(false);

      }
    });

    // add click event of writeFileBtn to save new file
    writeFileBtn.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        String newFilePath = newFileName.getText();
        String newFileContent = "";
        for (String newLine: newLines) {
          newFileContent = newFileContent + newLine;
        }
        try {
          FileWriter writer = new FileWriter(newFilePath);
          writer.write(newFileContent);
          writer.close();
        }
        catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    });
  }
}
