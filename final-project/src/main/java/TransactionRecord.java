import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Vector;

/**
 * @ClassNAME Transcation
 * @Description TODO
 * @Author glimmer
 * @Date 2022/12/10 17:15
 * @Version 1.0
 */
@Entity(name = "transactions")
public class TransactionRecord
{
  public static final String FROM_FIELD_NAME = "from";

  @Id
  private String hash;

  @Column(nullable = false, name = FROM_FIELD_NAME)
  private String from;

  @Column(nullable = false)
  private String to;

  @Column(nullable = false)
  private String coin;

  @Column(nullable = false)
  private String out;

  @Column(nullable = false)
  private String status;

  public void setTransaction(String _status, String _from, String _to, String _coin, String _out, String _hash){
    status = _status;
    from = _from;
    to = _to;
    coin = _coin;
    out = _out;
    hash = _hash;
  }

  public void setTransactionByVector(Vector<String> transLog){
    status = transLog.get(0);
    from = transLog.get(1);
    to = transLog.get(2);
    coin = transLog.get(3);
    out = transLog.get(4);
    hash = transLog.get(5);
  }

  public Vector<String> getTransaction(){
    return new Vector<>(){{
      add(status);
      add(from);
      add(to);
      add(coin);
      add(out);
      add(hash);
    }};
  }

  public String getFrom(){
    return from;
  }

  public String getHash(){
    return hash;
  }
}
