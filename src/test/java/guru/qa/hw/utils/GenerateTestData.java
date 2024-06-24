package guru.qa.hw.utils;

import java.util.Random;

public class GenerateTestData {

    private enum Months {
        JANUARY("January", 31),
        FEBRUARY("February", 28),
        MARCH("March", 31),
        APRIL("April", 30),
        MAY("May", 31),
        JUNE("June", 30),
        JULY("July", 31),
        AUGUST("August", 31),
        SEPTEMBER("September", 30),
        OCTOBER("October", 31),
        NOVEMBER("November", 30),
        DECEMBER("December", 31);

        private final String month;
        private final Integer maxDays;

        Months(String month, int maxDays) {
            this.month = month;
            this.maxDays = maxDays;
        }

        public static Months getRandomMonth() {
            return values()[new Random().nextInt(12)];
        }
    }

    public enum States {
        NCR("NCR", new String[]{"Delhi", "Gurgaon", "Noida"}),
        UTTAR_PRADESH("Uttar Pradesh", new String[]{"Agra", "Lucknow", "Merrut"}),
        HARYANA("Haryana", new String[]{"Karnal", "Panipat"}),
        RAJASTHAN("Rajasthan", new String[]{"Jaipur", "Jaiselmer"});

        public final String state;
        private final String[] city;

        States(String state, String[] city) {
            this.state = state;
            this.city = city;
        }
    }

    public static class Birthday {
        public String year;
        public String month;
        public String day;
        public String dayWithZero;

        Birthday(Integer year, String month, Integer day) {
            this.year = Integer.toString(year);
            this.month = month;
            this.day = Integer.toString(day);
            this.dayWithZero = day < 10 ? String.format("0%d", day) : Integer.toString(day);
        }
    }

    public static Birthday generateBirthday() {
        var minYear = 1900;
        var maxYear = 2024;

        int year = new Random().nextInt(maxYear - minYear) + minYear;
        Months month = Months.getRandomMonth();
        int day = new Random().nextInt(month.maxDays);

        return new Birthday(year, month.month, day);
    }

    public static String generateGender() {
        String[] gender = new String[]{"Male", "Female", "Other"};
        return gender[new Random().nextInt(0, gender.length)];
    }

    public static String generateSubject() {
        String[] subject = new String[]{"Math", "Computer Science", "Art"};
        return subject[new Random().nextInt(0, subject.length)];
    }

    public static String generateHobbies() {
        String[] subject = new String[]{"Music", "Sports", "Reading"};
        return subject[new Random().nextInt(0, subject.length)];
    }

    public static States generateState() {
        return States.values()[new Random().nextInt(0, States.values().length)];
    }

    public static String generateCity(States state) {
        return state.city[new Random().nextInt(0, state.city.length)];
    }
}
