import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @title: AbsebcTest
 * @Author eddie
 * @Date: 2021/3/19 18:19
 * @Version 1.0
 */

public class AbsenceTest {
    @Test
    public void test() {
        //LocalDate entryDate, LocalDate startWorkDate, double offWorkYear, LocalDate aDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate currentDate = LocalDate.parse("20210330", formatter);
        LocalDate aDate = LocalDate.parse("20211231", formatter);
        double offWorkYear = 0.0d;

        LocalDate entryDate = LocalDate.parse("20210702", formatter);
        LocalDate startWorkDate = LocalDate.parse("20210702", formatter);
        System.out.println("当前:----------0");
        double d0 = Absence.getAllAnnualDayToADate(startWorkDate, entryDate, offWorkYear, currentDate);
        assert (Math.abs(d0 - 0)  < 1e-4);
        System.out.println("全年:----------2");
        double d1 = Absence.getAllAnnualDayToADate(startWorkDate, entryDate, offWorkYear, aDate);
        assert (Math.abs(d1 - 2)  < 1e-4);

        LocalDate entryDate2 = LocalDate.parse("20200702", formatter);
        LocalDate startWorkDate2 = LocalDate.parse("20200702", formatter);
        System.out.println("当前:----------1");
        double d02 = Absence.getAllAnnualDayToADate(startWorkDate2, entryDate2, offWorkYear, currentDate);
        assert (Math.abs(d02 - 1)  < 1e-4);
        System.out.println("当年:----------5.5");
        double d12 = Absence.getAllAnnualDayToADate(startWorkDate2, entryDate2, offWorkYear, aDate);
        assert (Math.abs(d12 - 5.5)  < 1e-4);

        LocalDate entryDate3 = LocalDate.parse("20210302", formatter);
        LocalDate startWorkDate3 = LocalDate.parse("20200702", formatter);
        System.out.println("当前:----------0");
        double d03 = Absence.getAllAnnualDayToADate(startWorkDate3, entryDate3, offWorkYear, currentDate);
        assert (Math.abs(d03 - 0)  < 1e-4);
        System.out.println("当年:----------4");
        double d13 = Absence.getAllAnnualDayToADate(startWorkDate3, entryDate3, offWorkYear, aDate);
        assert (Math.abs(d13 - 4)  < 1e-4);

        LocalDate entryDate4 = LocalDate.parse("20210101", formatter);
        LocalDate startWorkDate4 = LocalDate.parse("20110702", formatter);
        System.out.println("当前:----------1");
        double d04 = Absence.getAllAnnualDayToADate(startWorkDate4, entryDate4, offWorkYear, currentDate);
        assert (Math.abs(d04 - 1)  < 1e-4);
        System.out.println("当年:----------7");
        double d14 = Absence.getAllAnnualDayToADate(startWorkDate4, entryDate4, offWorkYear, aDate);
        assert (Math.abs(d14 - 7)  < 1e-4);

    }
}
