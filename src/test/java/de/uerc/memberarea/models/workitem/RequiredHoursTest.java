package de.uerc.memberarea.models.workitem;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RequiredHoursTest {

    @Test
    public void validForAllYearsOfBirth() {
        RequiredHours rh = new RequiredHours(2000);
        rh.setFromYearOfBirth(null);
        rh.setToYearOfBirth(null);

        assertTrue(rh.isValidForYearOfBirth(0));
        assertTrue(rh.isValidForYearOfBirth(1900));
        assertTrue(rh.isValidForYearOfBirth(2000));
        assertTrue(rh.isValidForYearOfBirth(2100));
    }

    @Test
    public void validForYearsLowerThan2000() {
        RequiredHours rh = new RequiredHours(2000);
        rh.setFromYearOfBirth(null);
        rh.setToYearOfBirth(2000);

        assertTrue(rh.isValidForYearOfBirth(0));
        assertTrue(rh.isValidForYearOfBirth(1900));
        assertTrue(rh.isValidForYearOfBirth(1999));
        assertFalse(rh.isValidForYearOfBirth(2000));
        assertFalse(rh.isValidForYearOfBirth(2100));
    }

    @Test
    public void validForYearsBetween1900And2000() {
        RequiredHours rh = new RequiredHours(2000);
        rh.setFromYearOfBirth(1900);
        rh.setToYearOfBirth(2000);

        assertFalse(rh.isValidForYearOfBirth(0));
        assertTrue(rh.isValidForYearOfBirth(1900));
        assertTrue(rh.isValidForYearOfBirth(1999));
        assertFalse(rh.isValidForYearOfBirth(2000));
        assertFalse(rh.isValidForYearOfBirth(2100));
    }

    @Test
    public void validForYearsHigherThan2000() {
        RequiredHours rh = new RequiredHours(2000);
        rh.setFromYearOfBirth(2001);
        rh.setToYearOfBirth(null);

        assertFalse(rh.isValidForYearOfBirth(0));
        assertFalse(rh.isValidForYearOfBirth(1900));
        assertFalse(rh.isValidForYearOfBirth(1999));
        assertFalse(rh.isValidForYearOfBirth(2000));
        assertTrue(rh.isValidForYearOfBirth(2001));
        assertTrue(rh.isValidForYearOfBirth(2100));
    }

    @Test
    public void noOverlapForDifferentYears() {
        RequiredHours rh1 = new RequiredHours(2000);
        rh1.setFromYearOfBirth(2001);
        rh1.setToYearOfBirth(null);
        RequiredHours rh2 = new RequiredHours(2001);
        rh2.setFromYearOfBirth(2001);
        rh2.setToYearOfBirth(null);
        assertFalse(rh1.overlapsWith(rh2));
    }

    @Test
    public void overlapForOpenTop() {
        RequiredHours rh1 = new RequiredHours(2000);
        rh1.setFromYearOfBirth(2001);
        rh1.setToYearOfBirth(null);
        RequiredHours rh2 = new RequiredHours(2000);
        rh2.setFromYearOfBirth(2001);
        rh2.setToYearOfBirth(null);
        assertTrue(rh1.overlapsWith(rh2));
    }

    @Test
    public void overlapForOpenBottom() {
        RequiredHours rh1 = new RequiredHours(2000);
        rh1.setFromYearOfBirth(null);
        rh1.setToYearOfBirth(2001);
        RequiredHours rh2 = new RequiredHours(2000);
        rh2.setFromYearOfBirth(null);
        rh2.setToYearOfBirth(2001);
        assertTrue(rh1.overlapsWith(rh2));
    }

    @Test
    public void overlapForOpenBottomAndTop() {
        RequiredHours rh1 = new RequiredHours(2000);
        rh1.setFromYearOfBirth(null);
        rh1.setToYearOfBirth(null);
        RequiredHours rh2 = new RequiredHours(2000);
        rh2.setFromYearOfBirth(null);
        rh2.setToYearOfBirth(null);
        assertTrue(rh1.overlapsWith(rh2));
    }

    
    public void overlapForRanges() {
        RequiredHours rh1 = new RequiredHours(2000);
        rh1.setFromYearOfBirth(2000);
        rh1.setToYearOfBirth(2002);
        RequiredHours rh2 = new RequiredHours(2000);
        rh2.setFromYearOfBirth(2002);
        rh2.setToYearOfBirth(null);
        assertTrue(rh1.overlapsWith(rh2));
    }

    
    public void noOverlapForRanges() {
        RequiredHours rh1 = new RequiredHours(2000);
        rh1.setFromYearOfBirth(null);
        rh1.setToYearOfBirth(2000);
        RequiredHours rh2 = new RequiredHours(2000);
        rh2.setFromYearOfBirth(2001);
        rh2.setToYearOfBirth(null);
        assertFalse(rh1.overlapsWith(rh2));
    }
}
