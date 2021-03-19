import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * @title: Absence
 * @Author eddie
 * @Date: 2021/3/19 11:01
 * @Version 1.0
 */
public class Absence {
    public static void main(String[] args) {
        Integer currentYear = 2021;// 当前年份
        String beginWorkDate = "20200701"; // 开始工作日期
        String entryDate = "20200701"; // 入职日期
        String nowYearBegain = currentYear + "0101";
        String endD = "20211231"; // 年末 ｜｜ 离职日
        String endD2 = "20210328"; // 当前时间
        double earlyWorkYear = 0; // 脱产工作年份

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate startDate = LocalDate.parse(beginWorkDate, formatter); // 开始工作
        LocalDate startDate2 = LocalDate.parse(entryDate, formatter); // 入司
        LocalDate startDate3 = LocalDate.parse(nowYearBegain, formatter); // 当前年份开始时间

        LocalDate endDate = LocalDate.parse(endD, formatter); // 到年末 ｜｜ 离职日
        LocalDate endDate2 = LocalDate.parse(endD2, formatter); // 当前时间天数
        double totalAbsenceDay = Math.min(15.0, getAnnualAbsenceDaysBeforeADate(startDate, startDate2, startDate3, endDate, earlyWorkYear));
        // 本年度到当前日期拥有的年假天数
        double currentAbsenceDay = Math.min(15.0, getAnnualAbsenceDaysBeforeADate(startDate, startDate2, startDate3, endDate2, earlyWorkYear));
        double workingYear = getYearsBetween2Date(startDate, endDate2);
        System.out.println("workingYear: " + workingYear);
        System.out.println("totalAbsenceDay : " + totalAbsenceDay);
        System.out.println("currentAbsenceDay: " + currentAbsenceDay);
    }

    private static double getAnnualAbsenceDaysBeforeADate(LocalDate startDate, LocalDate startDate2, LocalDate startDate3, LocalDate endDate, Double earlyWorkYear) {
        long startwork_today_diff = ChronoUnit.DAYS.between(startDate, endDate); // 计算两个日期间的天 开始工作日期到（年末｜｜离职日）间隔时间
        long entrywork_today_diff = ChronoUnit.DAYS.between(startDate2, endDate); // 计算两个日期间的天 入司迄今间隔
        long nowYearWorkDays = ChronoUnit.DAYS.between(startDate3, endDate); // 当年在司天数
        int totalWorkDay = (int) (startwork_today_diff + Math.floor(earlyWorkYear * 365));
        int workYear = totalWorkDay / 365; // 工作整年份计算
        int workRestDay = totalWorkDay % 365; // 余天数计算 （用于当年10，20年的年份的假期分段计算）
        int entryYear = (int) entrywork_today_diff / 365; // 入司年份 用于计算福利年假 福利年假 = 入司年份 * 2
        int entryRestDay = (int) entrywork_today_diff % 365; // 入司天数 用于不满一年计算年假
        Double totalAbsenceDay = 0.0;
        // 年假分水岭 ~10, 10 ,20
        if (workYear < 10) {
            // 入司年份 <1
            if (entryYear < 1) {
                totalAbsenceDay = (double) (entryRestDay / 365d * 5);
            } else {
                totalAbsenceDay = (double) (nowYearWorkDays / 365d * 5 + (entryYear - 1) * 2 + entryRestDay / 365d * 2); // 福利年假+法定年假
            }

        } else if (workYear == 10) {
            if (entryYear < 1) {
                totalAbsenceDay = (double) ((workRestDay / 365d * 10 + (nowYearWorkDays - workRestDay) / 365d * 5));
            } else {
                totalAbsenceDay = (double) ((workRestDay / 365d * 10 + (nowYearWorkDays - workRestDay) / 365d * 5 + (entryYear - 1) * 2 + entryRestDay / 365d * 2)
                ); // 福利年假+法定年假
            }

        } else if (workYear > 10 && workYear < 20) {
            if (entryYear < 1) {
                totalAbsenceDay = (double) (entryRestDay / 365d * 10);
            } else {
                totalAbsenceDay = (double) ((nowYearWorkDays / 365d * 10 + (entryYear - 1) * 2 + entryRestDay / 365d * 2)); // 福利年假+法定年假
            }

        } else if (workYear == 20) {
            if (entryYear < 1) {
                totalAbsenceDay = (double) ((workRestDay / 365d * 15 + (nowYearWorkDays - workRestDay) / 365d * 10));
            } else {
                totalAbsenceDay = (double) ((workRestDay / 365d * 15 + (nowYearWorkDays - workRestDay) / 365d * 10 + (entryYear - 1) * 2 + entryRestDay / 365d * 2)
                ); // 福利年假+法定年假
            }

        } else if (workYear > 20) {
            if (entryYear < 1) {
                totalAbsenceDay = (double) (entryRestDay / 365d * 15);
            } else {
                totalAbsenceDay = (double) ((nowYearWorkDays / 365d * 15 + (entryYear - 1) * 2 + entryRestDay / 365d * 2)); // 福利年假+法定年假
            }
        }

        int restd = totalAbsenceDay.intValue();

        if (totalAbsenceDay - 0.5 >= restd) {
            totalAbsenceDay = restd + 0.5;
        } else {
            totalAbsenceDay = (double) restd;
        }
        return totalAbsenceDay;

    }


    public static double getAllAnnualDayToADate(LocalDate entryDate, LocalDate startWorkDate, double offWorkYear, LocalDate aDate) {
        double benefitAnnualDay = getBenefitAnnualDayOfADate(entryDate, aDate);
        double lawAnnualDayOfADate = getLawAnnualDayOfADate(startWorkDate, offWorkYear, aDate);
        double modifyBenefitAnnualDay = (Math.floor(benefitAnnualDay) + Math.round(benefitAnnualDay)) / 2;
        double modifyLawAnnualDayOfADate = (Math.floor(lawAnnualDayOfADate) + Math.round(lawAnnualDayOfADate)) / 2;
        System.out.println("modifyLawAnnualDayOfADate:" + modifyLawAnnualDayOfADate);
        double modifySum = modifyBenefitAnnualDay + modifyLawAnnualDayOfADate;
        System.out.println("福利年假: " + benefitAnnualDay + "修整:" + modifyBenefitAnnualDay);
        System.out.println("法定年假:" + lawAnnualDayOfADate + "修整:" + modifyLawAnnualDayOfADate);
        return Math.min(modifySum, 15.0);
    }

    /**
     * 计算到某一天的福利年假 从进公司开始 0-1年 0天 1-2年 2天
     */
    private static double getBenefitAnnualDayOfADate(LocalDate entryDate, LocalDate aDate) {
        // 获取 aDate 同年的第一天
        LocalDate firstDayWithSameYearOfaDate = aDate.with(TemporalAdjusters.firstDayOfYear());
        double entryYear2FirstDayWithSameYearOfaDate = getYearsBetween2Date(entryDate, firstDayWithSameYearOfaDate);
        double entryYear2aDate = getYearsBetween2Date(entryDate, aDate);
        //小于一年是0天
        if (entryYear2aDate < 1.0d + 1e-4) {
            return 0.0d;
        }
        if (entryYear2FirstDayWithSameYearOfaDate < 1) {
            return 2 * (entryYear2aDate - 1.0d);
        }
        return 2 * (entryYear2aDate - entryYear2FirstDayWithSameYearOfaDate);
    }


    /**
     * 计算到某一天的法定年假天数
     */
    private static double getLawAnnualDayOfADate(LocalDate startWorkDate, double offWorkYear, LocalDate aDate) {
        double ANNUAL_DAYS_ONE_TO_TEN_YEARS = 5.0d;
        double ANNUAL_DAYS_TEN_TO_TWENTY_YEARS = 10.0d;
        double ANNUAL_DAYS_OVER_TWENTY_YEARS = 15.d;
        // 获取 aDate 同年的第一天
        LocalDate firstDayWithSameYearOfaDate = aDate.with(TemporalAdjusters.firstDayOfYear());
        double workingYear2FirstDayWithSameYearOfaDate = getYearsBetween2Date(startWorkDate, firstDayWithSameYearOfaDate) - offWorkYear;
        double workingYear2aDate = getYearsBetween2Date(startWorkDate, aDate) - offWorkYear;
        // 跨10年
        if (workingYear2FirstDayWithSameYearOfaDate < 10 && workingYear2aDate > 10) {
            return (10 - workingYear2aDate) * ANNUAL_DAYS_ONE_TO_TEN_YEARS + (workingYear2aDate - 10) * ANNUAL_DAYS_TEN_TO_TWENTY_YEARS;
        }
        // 跨20年
        if (workingYear2FirstDayWithSameYearOfaDate < 20 && workingYear2aDate > 20) {
            return (20 - workingYear2aDate) * ANNUAL_DAYS_TEN_TO_TWENTY_YEARS + (workingYear2aDate - 20) * ANNUAL_DAYS_OVER_TWENTY_YEARS;
        }
        // 不跨的情况
        double years = Math.max((workingYear2aDate - workingYear2FirstDayWithSameYearOfaDate), 0);
        if (workingYear2aDate < 10.0 + 1e-4) {
            return ANNUAL_DAYS_ONE_TO_TEN_YEARS * years;
        }
        if (workingYear2aDate > 10.0 && workingYear2aDate < 20.0) {
            return ANNUAL_DAYS_TEN_TO_TWENTY_YEARS * years;
        }
        if (workingYear2aDate > 20.0 + 1e-4) {
            return ANNUAL_DAYS_OVER_TWENTY_YEARS * years;
        }
        return 0d;
    }

    /**
     * 计算两个日期之间的年份，转成小数
     */
    private static double getYearsBetween2Date(LocalDate startDate, LocalDate endDate) {
        // 计算天数
        int daysOfEndDateInItsYear = (int) ChronoUnit.DAYS.between(startDate, endDate);
        if (daysOfEndDateInItsYear < 0) {
            return 0;
        }
        return (daysOfEndDateInItsYear + 0.0d) / 365;
    }

}
