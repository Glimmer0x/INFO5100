import WeightGrades.ExtendWeightGrades;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @ClassNAME main
 * @Description TODO
 * @Author Liangxi Liu
 * @Date 2022/9/21 10:10
 * @Version 1.0
 */
public class main
{
  public static void main(String[] args)
  {
    ExtendWeightGrades ewg = new ExtendWeightGrades();
    Double[] tp1 = {20.0, 30.0, 40.0, 50.0, 60.0, 100.0, 200.0, 300.0};
    Double[] ep1 = {15.0,25.0,30.0,40.0,50.0,90.0,180.0,280.0};
    Double[] ptg1 =  {10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 15.0, 25.0};
    ewg.setPointTotal(Arrays.asList(tp1));
    ewg.setAssignmentPercentage(Arrays.asList(ptg1));
    ewg.setEarnedPoints(Arrays.asList(ep1));
    ewg.calculateExtendTotalWeightedGrade();
    System.out.println(ewg.getExtendTotalWeightedGrade());
    System.out.println(ewg.getGradeClass());

  }
}
