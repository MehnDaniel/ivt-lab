package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private TorpedoStore ts1;
  private TorpedoStore ts2;
  private GT4500 ship;

  @BeforeEach
  public void init(){
    ts1 = mock(TorpedoStore.class);
    ts2 = mock(TorpedoStore.class);
    this.ship = new GT4500(ts1, ts2);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(ts1.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    verify(ts1, times(1)).fire(1);
    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(ts1.fire(1)).thenReturn(true);
    when(ts2.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    verify(ts1, times(1)).fire(1);
    verify(ts2, times(1)).fire(1);
    // Assert
    assertEquals(true, result);
  }

  @Test
  void unitTestNo1()
  {
    when(ts1.fire(1)).thenReturn(true);

    boolean result1 = ship.fireTorpedo(FiringMode.SINGLE);

    verify(ts1, times(1)).fire(1);
    
    assertEquals(true, result1);
  }

  @Test
  void unitTestNo2()
  {
    when(ts1.fire(1)).thenReturn(true);

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    verify(ts1, times(1)).fire(1);
    
    assertEquals(0, ts1.getTorpedoCount());
  }

  @Test
  void unitTestNo3()
  {
    when(ts1.fire(1)).thenReturn(true);
    when(ts2.fire(1)).thenReturn(true);

    boolean result1 = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    verify(ts1, times(1)).fire(1);
    verify(ts2, times(1)).fire(1);
    
    assertEquals(true, result1);
    assertEquals(true, result2);
  }

  @Test
  void unitTestNo4()
  {
    when(ts1.fire(1)).thenReturn(false);
    when(ts2.fire(1)).thenReturn(false);

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    result = ship.fireTorpedo(FiringMode.SINGLE);
    result = ship.fireTorpedo(FiringMode.SINGLE);

    verify(ts1, times(2)).fire(1);
    verify(ts2, times(1)).fire(1);
    
    assertEquals(false, result);
  }

  @Test
  void unitTestNo5()
  {
    when(ts1.fire(1)).thenReturn(true);
    when(ts2.fire(1)).thenReturn(false);

    boolean result = ship.fireTorpedo(FiringMode.ALL);

    verify(ts1, times(1)).fire(1);
    verify(ts2, times(1)).fire(1);
    
    assertEquals(false, result);
  }

  @Test
  void torpedoBayEmpties()
  {
    when(ts1.fire(1)).thenReturn(true);

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    verify(ts1, times(1)).fire(1);

    assertEquals(0, ts1.getTorpedoCount());
  }
}
