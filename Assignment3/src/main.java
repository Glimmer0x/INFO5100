import WeightGrades.ExtendWeightGrades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
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
//    Double[] tp1 = {20.0, 30.0, 40.0, 50.0, 60.0, 100.0, 200.0, 300.0};
//    Double[] ep1 = {15.0,25.0,30.0,40.0,50.0,90.0,180.0,250.0};
//    Double[] ptg1 =  {10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 15.0, 25.0};
//    ewg.setPointTotal(Arrays.asList(tp1));
//    ewg.setAssignmentPercentage(Arrays.asList(ptg1));
//    ewg.setEarnedPoints(Arrays.asList(ep1));
//    ewg.calculateExtendTotalWeightedGrade();
//    System.out.println(ewg.getGradeClass());

    Double[] tp = new Double[8];
    Double[] ep = new Double[8];
    Double[] ptg =  new Double[8];
    Scanner inputs = new Scanner(System.in);
    System.out.println("Enter total points:");
    String[] tpInputs = inputs.nextLine().split(",");
    for(int i=0; i<tpInputs.length;i++){
      tp[i]=Double.parseDouble(tpInputs[i]);
    }

    System.out.println("Enter earned points:");
    String[] epInputs = inputs.nextLine().split(",");
    for(int i=0; i<epInputs.length;i++){
      ep[i]=Double.parseDouble(epInputs[i]);
    }
    System.out.println("Enter percentages of points:");
    String[] ptInputs = inputs.nextLine().split(",");
    for(int i=0; i<ptInputs.length;i++){
      ptg[i]=Double.parseDouble(ptInputs[i]);
    }
    ewg.setPointTotal(Arrays.asList(tp));
    ewg.setAssignmentPercentage(Arrays.asList(ptg));
    ewg.setEarnedPoints(Arrays.asList(ep));
    ewg.calculateExtendTotalWeightedGrade();
    System.out.println(ewg.getGradeClass());

  }
}
