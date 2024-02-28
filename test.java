

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class test.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class test
{
    private Person person1;
    private Person person2;
    private Auction auction1;

    /**
     * Default constructor for test class test
     */
    public test()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        person1 = new Person("primero");
        person2 = new Person("segundo");
        auction1 = new Auction();
        auction1.enterLot("1");
        auction1.enterLot("2");
        auction1.enterLot("3");
        auction1.enterLot("4");
        auction1.enterLot("5");
        auction1.showLots();
        auction1.makeABid(1, person1, 5000);
        auction1.makeABid(1, person2, 6000);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
}
