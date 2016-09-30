import java.util.List;
import org.sql2o.*;
import java.sql.Timestamp;

public class EndangeredAnimals implements GeneralAnimal extends DatabaseManager {
  private String health;
  private String age;
  private int amount;
  public static final String DATABASE_TYPE = "endangered";
  public static final String HEALTHY = "healthy";
  public static final String ILL = "ill";
  public static final String OKAY = "okay";
  public static final String NEWBORN = "newborn";
  public static final String YOUNG = "young";
  public static final String ADULT = "adult";
  public EndangeredAnimals(String health, String age, int amount) {
    this.health = health;
    this.age = age;
    this.amount = amount;
  }

  public String getHealth() {
    return health;
  }

  public String getAge() {
    return age;
  }

  public int getAmount() {
    return amount;
  }

  public boolean
}
