import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


  /**
   * @ClassName WeightGrades
   * @Description The point total, earned points, and assignment
   *              percentage will be taked in from the user. The
   *              total weighted grade will be calculated. The
   *              idea is to take an assignment and then transform
   *              the raw score to the number of percentage points
   *              earned in the class as a total for the class.
   * @Author Liangxi Liu
   */
  public class WeightGrades extends JFrame
  {
    private JButton calcualteScoreButton;
    private JTextField totalAssignmentPointTextField;
    private JTextField earnedPointsTextField;
    private JTextField percentageOfClassTextField;
    private JLabel totalAssignmentPointsLabel;
    private JLabel earnedPointsLabel;
    private JLabel percentageOfClassLabel;
    private JLabel weightedScoreLabel;
    private JLabel weightedScoreOutput;
    private JPanel panelMain;

    private double pointTotal;
    private double earnedPoints;
    private double assignmentPercentage;
    private double totalWeightedGrade;

    /**
     * @MethodName WeightGrades
     * @Author Liangxi Liu
     * @Description Construction function.
     * @Param _pointTotal: double number
     * @Param _earnedPoints: double number
     * @Param _assignmentPercentage: double number
     */
    public WeightGrades(double _pointTotal,
                         double _earnedPoints,
                         double _assignmentPercentage) {
      pointTotal = _pointTotal;
      earnedPoints = _earnedPoints;
      assignmentPercentage = _assignmentPercentage;
      totalWeightedGrade = calculateTotalWeightedGrade();
    }

    /**
     * @MethodName WeightGrades
     * @Author Liangxi Liu
     * @Description Construction function. Add click event for calcualteScoreButton
     */
    public WeightGrades(){
      calcualteScoreButton.addActionListener(new ActionListener()
      {
        @Override
        public void actionPerformed(ActionEvent e)
        {
          String totalAssignmentPointsInput = totalAssignmentPointTextField.getText();
          String earnedPointsInput = earnedPointsTextField.getText();
          String percentageOfClassInput = percentageOfClassTextField.getText();
          setPointTotal(Double.parseDouble(totalAssignmentPointsInput));
          setEarnedPoints(Double.parseDouble(earnedPointsInput));
          setAssignmentPercentage(Double.parseDouble(percentageOfClassInput));
          double score = calculateTotalWeightedGrade();
          weightedScoreOutput.setText(String.valueOf(score));
        }
      });
    }

    /**
     * @MethodName calculateTotalWeightedGrade
     * @Author Liangxi Liu
     * @Description calculate total weighted grade
     * @return a double number of total weighted grade
     */
    public double calculateTotalWeightedGrade(){
      assert pointTotal!=0;
      return (earnedPoints/pointTotal)*assignmentPercentage*100;
    }

    //-------------------- Setter methods --------------------
    public void setPointTotal(double _pointTotal){
      pointTotal = _pointTotal;
    }

    public void setEarnedPoints(double _earnedPoints){
      earnedPoints = _earnedPoints;
    }

    public void setAssignmentPercentage(double _assignmentPercentage){
      assignmentPercentage = _assignmentPercentage;
    }

    //-------------------- Getter methods --------------------
    public double getPointTotal()
    {
      return pointTotal;
    }

    public double getEarnedPoints()
    {
      return earnedPoints;
    }

    public double getAssignmentPercentage()
    {
      return assignmentPercentage;
    }

    // calculate total weighted grade and then return totalWeightedGrade;
    public double getTotalWeightedGrade()
    {
      totalWeightedGrade = calculateTotalWeightedGrade();
      return totalWeightedGrade;
    }

    public static void main(String[] args){
      WeightGrades w = new WeightGrades();
      w.setContentPane(w.panelMain);
      w.setTitle("Weight Grades");
      w.setSize(500,200);
      w.setVisible(true);
      w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
  }

