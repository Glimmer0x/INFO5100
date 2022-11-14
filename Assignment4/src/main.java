import WeightGrades.ExtendWeightGrades;

import java.util.ArrayList;
import java.util.Arrays;
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

    Scanner inputs = new Scanner(System.in);

    System.out.println("Enter the number of total assignments:");
    int num = Integer.parseInt(inputs.nextLine());
    Double[] tp = new Double[num];
    Double[] ep = new Double[num];
    Double[] ptg =  new Double[num];

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

    ewg.setTotalAssignments(num);
    ewg.setPointTotal(Arrays.asList(tp));
    ewg.setAssignmentPercentage(Arrays.asList(ptg));
    ewg.setEarnedPoints(Arrays.asList(ep));
    ewg.calculateExtendTotalWeightedGrade();
    System.out.println("Extend Total Weighted Grade: "+ewg.getExtendTotalWeightedGrade());
    System.out.println("Grade Class : "+ewg.getGradeClass());

  }
}
