import java.util.List;
import org.sql2o.*;
import java.sql.Timestamp;

public class EndangeredAnimals implements GeneralAnimal extends DatabaseManager {
  private String health;
  private String age;
  public static final String DATABASE_TYPE = "endangered";
  public static final String DATABASE_HEALTHY = "healthy";
  public static final String DATABASE_ILL = "ill";
  public static final String DATABASE_OKAY = "okay";
  public static final String DATABASE_NEWBORN = "newborn";
  public static final String DATABASE_YOUNG = "young";
  public static final String DATABASE_AADULT = "adult";
}
