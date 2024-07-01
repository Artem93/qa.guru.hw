package guru.qa.hw.utils;

import com.github.javafaker.Faker;

import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class GenerateTestData {

    public enum States {
        NCR("NCR", new String[]{"Delhi", "Gurgaon", "Noida"}),
        UTTAR_PRADESH("Uttar Pradesh", new String[]{"Agra", "Lucknow", "Merrut"}),
        HARYANA("Haryana", new String[]{"Karnal", "Panipat"}),
        RAJASTHAN("Rajasthan", new String[]{"Jaipur", "Jaiselmer"});

        public final String state;
        public final String[] cities;

        States(String state, String[] city) {
            this.state = state;
            this.cities = city;
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
        var birthday = Faker.instance().date().birthday(0, 100);
        var calendar = Calendar.getInstance();
        calendar.setTime(birthday);

        var year = calendar.get(Calendar.YEAR);
        var month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
        var day = calendar.get(Calendar.DAY_OF_MONTH);

        return new Birthday(year, month, day);
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
        return state.cities[new Random().nextInt(0, state.cities.length)];
    }
}