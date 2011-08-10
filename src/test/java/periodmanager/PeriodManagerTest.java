package periodmanager;

import static org.fest.assertions.Assertions.assertThat;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

public class PeriodManagerTest {

    private PeriodManager periodManager;

    @Before
    public void initPeriods() throws ParseException {
        periodManager = new PeriodManager(
                PeriodManager.DATE_FORMAT.parse("01/01/2000"),
                PeriodManager.DATE_FORMAT.parse("01/02/2000"),
                PeriodManager.DATE_FORMAT.parse("01/03/2000"),
                PeriodManager.DATE_FORMAT.parse("01/04/2000"),
                PeriodManager.DATE_FORMAT.parse("01/05/2000"));
    }
    
    @Test
    public final void testNominal() {
        assertThat(periodManager.toString()).isEqualTo(
                "0. 01/01/2000 - 01/02/2000\n" +
                "1. 01/02/2000 - 01/03/2000\n" +
                "2. 01/03/2000 - 01/04/2000\n" +
                "3. 01/04/2000 - 01/05/2000\n" +
                "4. 01/05/2000\n");
    }

    @Test
    public final void testFirst() throws ParseException {
        periodManager.move(2, PeriodManager.DATE_FORMAT.parse("15/12/1999"));
        assertThat(periodManager.toString()).isEqualTo(
                "2. 15/12/1999 - 01/01/2000\n" +
                "0. 01/01/2000 - 01/02/2000\n" +
                "1. 01/02/2000 - 01/04/2000\n" +
                "3. 01/04/2000 - 01/05/2000\n" +
                "4. 01/05/2000\n");
    }
    
    @Test
    public final void testBeforePrevious() throws ParseException {
        periodManager.move(2, PeriodManager.DATE_FORMAT.parse("15/01/2000"));
        assertThat(periodManager.toString()).isEqualTo(
                "0. 01/01/2000 - 15/01/2000\n" +
                "2. 15/01/2000 - 01/02/2000\n" +
                "1. 01/02/2000 - 01/04/2000\n" +
                "3. 01/04/2000 - 01/05/2000\n" +
                "4. 01/05/2000\n");
    }

    @Test
    public final void testPrevious() throws ParseException {
        periodManager.move(2, PeriodManager.DATE_FORMAT.parse("15/02/2000"));
        assertThat(periodManager.toString()).isEqualTo(
                "0. 01/01/2000 - 01/02/2000\n" +
                "1. 01/02/2000 - 15/02/2000\n" +
                "2. 15/02/2000 - 01/04/2000\n" +
                "3. 01/04/2000 - 01/05/2000\n" +
                "4. 01/05/2000\n");
    }

    @Test
    public final void testOnItself() throws ParseException {
        periodManager.move(2, PeriodManager.DATE_FORMAT.parse("15/03/2000"));
        assertThat(periodManager.toString()).isEqualTo(
                "0. 01/01/2000 - 01/02/2000\n" +
                "1. 01/02/2000 - 15/03/2000\n" +
                "2. 15/03/2000 - 01/04/2000\n" +
                "3. 01/04/2000 - 01/05/2000\n" +
                "4. 01/05/2000\n");
    }

    @Test
    public final void testNext() throws ParseException {
        periodManager.move(2, PeriodManager.DATE_FORMAT.parse("15/04/2000"));
        assertThat(periodManager.toString()).isEqualTo(
                "0. 01/01/2000 - 01/02/2000\n" +
                "1. 01/02/2000 - 01/04/2000\n" +
                "3. 01/04/2000 - 15/04/2000\n" +
                "2. 15/04/2000 - 01/05/2000\n" +
                "4. 01/05/2000\n");
    }

    @Test
    public final void testLast() throws ParseException {
        periodManager.move(2, PeriodManager.DATE_FORMAT.parse("15/05/2000"));
        assertThat(periodManager.toString()).isEqualTo(
                "0. 01/01/2000 - 01/02/2000\n" +
                "1. 01/02/2000 - 01/04/2000\n" +
                "3. 01/04/2000 - 01/05/2000\n" +
                "4. 01/05/2000 - 15/05/2000\n" +
                "2. 15/05/2000\n");
    }

}
