package Tests;

import Encounters.Date;
import Exceptions.InvalidDate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    void getHours() throws InvalidDate {
        Date date = new Date(12,12,12);
        assertEquals(12, date.getHours());
    }

    @Test
    void compareDates() throws InvalidDate {
        Date dateTest = new Date(1,1,1); //769
        Date dateTest2 = new Date(1,1,1); //769
        assertEquals(0, dateTest.compareDates(dateTest2));
    }

}