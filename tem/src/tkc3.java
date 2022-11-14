import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.jar.JarEntry;

/**
 * @ClassNAME tkc3
 * @Description TODO
 * @Author glimmer
 * @Date 2022/11/4 18:38
 * @Version 1.0
 */
public class tkc3
{
  public static void main(String[] args)
  {
    ArrayList<ArrayList<Integer>> tasks = new ArrayList<>();
    ArrayList<Integer> t1 = new ArrayList<>();
    t1.add(1);
    t1.add(3);
    t1.add(2);
    ArrayList<Integer> t2 = new ArrayList<>();
    t2.add(1);
    t2.add(5);
    t2.add(3);
    ArrayList<Integer> t3 = new ArrayList<>();
    t3.add(5);
    t3.add(6);
    t3.add(2);
    tasks.add(t1);
    tasks.add(t2);
    tasks.add(t3);

    tasks.sort(new Comparator<ArrayList<Integer>>()
    {
      @Override
      public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2)
      {
        return o1.get(1) - o2.get(1);
      }
    });

    HashSet<Integer> timestamps = new HashSet<>();
    for (ArrayList<Integer> task: tasks
         ) {
        int start = task.get(0);
        int end = task.get(1);
        int period = task.get(2);

      for (int j = start; j <=end ; j++) {
        if(timestamps.contains(j)){
          period--;
        }
      }
      int j = end;
      while(period>0 && j >= start){
        if(!timestamps.contains(j)){
          timestamps.add(j);
          j--;
          period--;
        }
      }
    }

    System.out.println(timestamps.size());


  }
}
