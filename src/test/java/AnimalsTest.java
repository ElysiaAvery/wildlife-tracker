import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import org.sql2o.*;
import java.sql.Timestamp;
import java.util.Date;
import java.text.DateFormat;
import java.util.List;
import java.util.ArrayList;


public class AnimalsTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void animals_instantiatesCorrectly_true() {
    Animals testAnimals = new Animals("chickadee", "young");
    assertTrue(testAnimals instanceof Animals);
  }

  @Test
  public void getName_animalsInstantiatesWithName_chickadee() {
    Animals testAnimals = new Animals("chickadee", "young");
    assertEquals("chickadee", testAnimals.getName());
  }

  @Test
  public void getAge_animalsInstantiatesWithAge_String() {
    Animals testAnimals = new Animals("chickadee", "young");
    assertEquals("young", testAnimals.getAge());
  }

  @Test
  public void equals_returnsTrueIfNameAndAgesAreSame_True() {
    Animals firstAnimals = new Animals("chickadee", "young");
    Animals secondAnimals = new Animals("chickadee", "young");
    assertTrue(firstAnimals.equals(secondAnimals));
  }

  @Test
  public void all_returnsAllInstancesOfAnimals_true() {
    Animals firstAnimals = new Animals("chickadee", "young");
    firstAnimals.save();
    Animals secondAnimals = new Animals("chickadee", "young");
    secondAnimals.save();
    assertEquals(true, Animals.all().get(0).equals(firstAnimals));
    assertEquals(true, Animals.all().get(1).equals(secondAnimals));
  }

  @Test
  public void save_assignsIdToObject_true() {
    Animals firstAnimals = new Animals("chickadee", "young");
    firstAnimals.save();
    Animals savedAnimals = Animals.all().get(0);
    assertEquals(firstAnimals.getId(), savedAnimals.getId());
  }

  @Test
  public void find_returnsAnimalsWithSameId_secondAnimals() {
    Animals firstAnimals = new Animals("chickadee", "young");
    firstAnimals.save();
    Animals secondAnimals = new Animals("chickadee", "young");
    secondAnimals.save();
    assertEquals(Animals.find(secondAnimals.getId()), secondAnimals);
  }

  @Test
  public void getSightings_returnsAllSightings_true() {
    Sightings firstSightings = new Sightings("Quadrant A", "Naomi");
    firstSightings.save();
    Sightings secondSightings = new Sightings("Quadrant C", "Peter");
    secondSightings.save();
    Animals firstAnimals = new Animals("chickadee", "young");
    firstAnimals.save();
    firstSightings.addAnimals(firstAnimals);
    secondSightings.addAnimals(firstAnimals);
    List savedSightings = firstAnimals.getSightings();
    assertEquals(2, savedSightings.size());
  }

  @Test
  public void leaveSightings_removesAssociationWithSpecifiedSightings_true() {
    Sightings testSightings = new Sightings("Quadrant A", "Naomi");
    testSightings.save();
    Animals firstAnimals = new Animals("chickadee", "young");
    firstAnimals.save();
    testSightings.addAnimals(firstAnimals);
    firstAnimals.leaveSightings(testSightings);
    List savedSightings = firstAnimals.getSightings();
    assertEquals(0, savedSightings.size());
  }

  @Test
  public void delete_deletesAnimals_true() {
    Animals testAnimals = new Animals("chickadee", "young");
    testAnimals.save();
    testAnimals.delete();
    assertEquals(0, Animals.all().size());
  }

  @Test
  public void delete_deletesAllAnimalssAndSightingsAssociations() {
    Sightings testSightings = new Sightings("Quadrant A", "Naomi");
    testSightings.save();
    Animals testAnimals = new Animals("chickadee", "young");
    testAnimals.save();
    testSightings.addAnimals(testAnimals);
    testAnimals.delete();
    assertEquals(0, testSightings.getAnimals().size());
  }

 }
