import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @ClassNAME Coin
 * @Description TODO
 * @Author glimmer
 * @Date 2022/12/10 15:37
 * @Version 1.0
 */

@Entity(name = "coins")
public class Coin
{

  @Id
  private String address;

  @Column(nullable = false)
  private String name;

  public Coin(){
  }

  public void setCoin(String _address, String _name){
    address = _address;
    name = _name;
  }

  public void setAddress(String s){
    address = s;
  }

  public void setName(String s){
    name = s;
  }

  public String getAddress(){
    return address;
  }

  public String getName(){
    return name;
  }
}
