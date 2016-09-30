import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import org.sql2o.*;
import java.sql.Timestamp;
import java.util.Date;
import java.text.DateFormat;
import java.util.List;
import java.util.ArrayList;


public class EndangeredAnimalsTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void animals_instantiatesCorrectly_true() {
    EndangeredAnimals testEndangeredAnimals = new EndangeredAnimals("grey wolf","healthy", "young", 3);
    assertTrue(testEndangeredAnimals instanceof EndangeredAnimals);
  }

  @Test
  public void getName_animalsInstantiatesWithName_chickadee() {
    EndangeredAnimals testEndangeredAnimals = new EndangeredAnimals("grey wolf","healthy", "young", 3);
    assertEquals("grey wolf", testEndangeredAnimals.getName());
  }

  @Test
  public void getHealth_animalsInstantiatesWithHealth_chickadee() {
    EndangeredAnimals testEndangeredAnimals = new EndangeredAnimals("grey wolf","healthy", "young", 3);
    assertEquals("healthy", testEndangeredAnimals.getHealth());
  }

  @Test
  public void getAge_animalsInstantiatesWithAge_String() {
    EndangeredAnimals testEndangeredAnimals = new EndangeredAnimals("grey wolf","healthy", "young", 3);
    assertEquals("young", testEndangeredAnimals.getAge());
  }

  @Test
  public void getAmount_animalsInstantiatesWithAmount_int() {
    EndangeredAnimals testEndangeredAnimals = new EndangeredAnimals("grey wolf","healthy", "young", 3);
    assertEquals(3, testEndangeredAnimals.getAmount());
  }

  @Test
  public void equals_returnsTrueIfNameAndAgesAreSame_True() {
    EndangeredAnimals firstEndangeredAnimals = new EndangeredAnimals("grey wolf","healthy", "young", 3);
    EndangeredAnimals secondEndangeredAnimals = new EndangeredAnimals("grey wolf","healthy", "young", 3);
    assertTrue(firstEndangeredAnimals.equals(secondEndangeredAnimals));
  }

  @Test
  public void all_returnsAllInstancesOfEndangeredAnimals_true() {
    EndangeredAnimals firstEndangeredAnimals = new EndangeredAnimals("grey wolf","healthy", "young", 3);
    firstEndangeredAnimals.save();
    EndangeredAnimals secondEndangeredAnimals = new EndangeredAnimals("grey wolf","healthy", "young", 3);
    secondEndangeredAnimals.save();
    assertEquals(true, EndangeredAnimals.all().get(0).equals(firstEndangeredAnimals));
    assertEquals(true, EndangeredAnimals.all().get(1).equals(secondEndangeredAnimals));
  }

  @Test
  public void save_assignsIdToObject_true() {
    EndangeredAnimals firstEndangeredAnimals = new EndangeredAnimals("grey wolf","healthy", "young", 3);
    firstEndangeredAnimals.save();
    EndangeredAnimals savedEndangeredAnimals = EndangeredAnimals.all().get(0);
    assertEquals(firstEndangeredAnimals.getId(), savedEndangeredAnimals.getId());
  }

  @Test
  public void find_returnsEndangeredAnimalsWithSameId_secondEndangeredAnimals() {
    EndangeredAnimals firstEndangeredAnimals = new EndangeredAnimals("grey wolf","healthy", "young", 3);
    firstEndangeredAnimals.save();
    EndangeredAnimals secondEndangeredAnimals = new EndangeredAnimals("grey wolf","healthy", "young", 3);
    secondEndangeredAnimals.save();
    assertEquals(EndangeredAnimals.find(secondEndangeredAnimals.getId()), secondEndangeredAnimals);
  }

  @Test
  public void getSightings_returnsAllSightings_true() {
    Sightings firstSightings = new Sightings("Quadrant A", "Naomi");
    firstSightings.save();
    Sightings secondSightings = new Sightings("Quadrant C", "Peter");
    secondSightings.save();
    EndangeredAnimals firstEndangeredAnimals = new EndangeredAnimals("grey wolf","healthy", "young", 3);
    firstEndangeredAnimals.save();
    firstSightings.addEndangeredAnimals(firstEndangeredAnimals);
    secondSightings.addEndangeredAnimals(firstEndangeredAnimals);
    List savedSightings = firstEndangeredAnimals.getSightings();
    assertEquals(2, savedSightings.size());
  }

  @Test
  public void leaveSightings_removesAssociationWithSpecifiedSightings_true() {
    Sightings testSightings = new Sightings("Quadrant A", "Naomi");
    testSightings.save();
    EndangeredAnimals firstEndangeredAnimals = new EndangeredAnimals("grey wolf","healthy", "young", 3);
    firstEndangeredAnimals.save();
    testSightings.addEndangeredAnimals(firstEndangeredAnimals);
    firstEndangeredAnimals.leaveSightings(testSightings);
    List savedSightings = firstEndangeredAnimals.getSightings();
    assertEquals(0, savedSightings.size());
  }

  @Test
  public void delete_deletesEndangeredAnimals_true() {
    EndangeredAnimals testEndangeredAnimals = new EndangeredAnimals("grey wolf","healthy", "young", 3);
    testEndangeredAnimals.save();
    testEndangeredAnimals.delete();
    assertEquals(0, EndangeredAnimals.all().size());
  }

  @Test
  public void delete_deletesAllEndangeredAnimalssAndSightingsAssociations() {
    Sightings testSightings = new Sightings("Quadrant A", "Naomi");
    testSightings.save();
    EndangeredAnimals testEndangeredAnimals = new EndangeredAnimals("grey wolf","healthy", "young", 3);
    testEndangeredAnimals.save();
    testSightings.addEndangeredAnimals(testEndangeredAnimals);
    testEndangeredAnimals.delete();
    assertEquals(0, testSightings.getEndangeredAnimals().size());
  }

 }
