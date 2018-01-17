import com.airport.baggagerouting.Main;
import junit.framework.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class RoutingTest extends TestCase {
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    public void testRoutingSystem(){
        System.setOut(new PrintStream(outputStream));
        Main.main(null);
        String baggageDetails = outputStream.toString();
        assertEquals("0001 Concourse_A_Ticketing A5 A1 : 11\r\n" +
                "0002 A5 A1 A2 A3 A4 : 9\r\n" +
                "0003 A2 A1 : 1\r\n" +
                "0004 A8 A9 A10 A5 : 6\r\n" +
                "0005 A7 A8 A9 A10 A5 BaggageClaim : 12\r\n",baggageDetails);

    }
}
