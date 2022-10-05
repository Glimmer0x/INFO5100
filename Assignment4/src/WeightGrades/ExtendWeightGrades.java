package WeightGrades;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassNAME ExtendWeightGrades
 * @Description Extend the weighted grades class to
 *              include a method that generates the
 *              final grade. Students will need to add
 *              attributes to store the grades earned in
 *              the class. Additional attributes will be
 *              to support the percentage that the
 *              assignments / exams are for the class.
 * @Author Liangxi Liu
 * @Date 2022/9/28 09:52
 * @Version 1.0
 */
public class ExtendWeightGrades extends WeightGrades
{
  private int totalAssignments;
  private ArrayList<Double> extendPointTotal;
  private ArrayList<Double> extendEarnedPoints;
  private ArrayList<Double> extendAssignmentPercentage;
  private double extendTotalWeightedGrade;
  private String gradeClass;

  /**
   * @MethodName calculateExtendTotalWeightedGrade
   * @Author Liangxi Liu
   * @Description calculate the extend total weighted grade
   * @return double extendTotalWeightedGrade
   */
  public double calculateExtendTotalWeightedGrade()
  {
    assert totalAssignments>0 : "the total assignments should be larger than zero";
    assert this.extendEarnedPoints.size()==totalAssignments : "the length of extend earned points should equal to the total assignments";
    assert this.extendAssignmentPercentage.size()==this.totalAssignments : "the length of extend assignment percentage should equal to the total assignments";
    assert this.extendPointTotal.size()==this.totalAssignments : "the length of extend total points should equal to the total assignments";
    double points = 0;
    double totalP = 0;
    for (int i = 0; i < this.extendEarnedPoints.size(); i++){
      assert this.extendPointTotal.get(i)>=this.extendEarnedPoints.get(i);
      points += this.extendEarnedPoints.get(i) * this.extendAssignmentPercentage.get(i);
      totalP += this.extendPointTotal.get(i) * this.extendAssignmentPercentage.get(i);
    }
    points = points/100;
    totalP = totalP/100;
    this.extendTotalWeightedGrade = (points/totalP)*100;
    this.matchGradeClass();
    return points;
  }

  /**
   * @MethodName matchGradeClass
   * @Author Liangxi Liu
   * @Description match the extend total weighted grade to
   *              different class: "A","B", "C", "D", "F".
   *              A is 90 – 100,
   *              B is 80 – < 90,
   *              C is 70 – < 80,
   *              D is 60 – < 70,
   *              then F is anything < 60.
   */
  public void matchGradeClass(){
    assert this.extendTotalWeightedGrade<=100;
    assert this.extendTotalWeightedGrade>=0;
    if(this.extendTotalWeightedGrade>=90){
      this.gradeClass = "A";
    }else if(this.extendTotalWeightedGrade>=80){
      this.gradeClass = "B";
    }else if(this.extendTotalWeightedGrade>=70){
      this.gradeClass = "C";
    }else if(this.extendTotalWeightedGrade>=60){
      this.gradeClass = "D";
    }else{
      this.gradeClass = "F";
    }
  }


  //-------------------- Setter methods --------------------
  public void setEarnedPoints(List<Double> _earnedPoints)
  {

    assert _earnedPoints.size()==totalAssignments: "the length of inputs should equal to the total assignments";
    this.extendEarnedPoints = new ArrayList<>();
    for(double p: _earnedPoints){
      this.extendEarnedPoints.add(p);
    }
  }

  public void setAssignmentPercentage(List<Double> _assignmentPercentage)
  {
    assert _assignmentPercentage.size()==totalAssignments: "the length of inputs should equal to the total assignments";
    double totalPercentage = 0.0;
    for(double p: _assignmentPercentage){
      totalPercentage=totalPercentage+p;
    }
    assert totalPercentage==100.0:"total percentage should equal 100.0";

    this.extendAssignmentPercentage = new ArrayList<>();
    for(double p: _assignmentPercentage){
      this.extendAssignmentPercentage.add(p);
    }
  }

  public void setPointTotal(List<Double> _pointTotal)
  {
    assert _pointTotal.size()==totalAssignments: "the length of inputs should equal to the total assignments";
    this.extendPointTotal = new ArrayList<>();
    for(double p: _pointTotal){
      this.extendPointTotal.add(p);
    }
  }

  public void setTotalAssignments(int _totalAssignments){
    this.totalAssignments = _totalAssignments;
  }

  //-------------------- Getter methods --------------------
  public ArrayList<Double> getExtendAssignmentPercentage()
  {
    return this.extendAssignmentPercentage;
  }

  public ArrayList<Double> getExtendPointTotal(){
    return this.extendPointTotal;
  }

  public ArrayList<Double> getExtendEarnedPoints(){
    return this.extendEarnedPoints;
}

  public double getExtendTotalWeightedGrade()
  {
    return this.extendTotalWeightedGrade;
  }

  public String getGradeClass()
  {
    return this.gradeClass;
  }

  public int getTotalAssignments(){
    return this.totalAssignments;
  }
}
