package edu.farmingdale.taskmanager.factories;

import edu.farmingdale.taskmanager.Models.User;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserFactory {

    private UserFactory(){};

    public static User generate(String userId, String username, String password, String email, int age, String profileImage, String weekStart){

        LocalDate weekStartDay = computeWeekStart(DayOfWeek.valueOf(weekStart));

        User user = new User.UserBuilder()
                .id(userId)
                .username(username)
                .password(password)
                .email(email)
                .age(age)
                .adult(checkAge(age))
                .vanquishedBossCount(0)
                .completedQuestCount(0)
                .profileUrl(profileImage)
                .level(1)
                .xp(0)
                .weekStreak(setupStreak(weekStartDay))
                .streak(0)
                .xpBonus(1)
                .weekStart(weekStart)
                .weekStartDate(weekStartDay.toString())
                .lastRitualComplete(LocalDate.now().minusDays(1).toString())
                .parties(new ArrayList<>())
                .friends(new ArrayList<>())
                .build();

        return user;
    }

    private static boolean checkAge(int age){
        return age >= 18;
    }

    public static LocalDate computeWeekStart(DayOfWeek weekStart) {
        LocalDate today = LocalDate.now();
        DayOfWeek current = today.getDayOfWeek();
        int diff = current.getValue() - weekStart.getValue();
        if (diff < 0) diff += 7;
        return today.minusDays(diff);
    }

    public static Map<String, Boolean> setupStreak(LocalDate weekStartDate){
        LocalDate today = LocalDate.now();
        Map<String, Boolean> ritualCompletion = new HashMap<>();
        for (int i = 0; i < 7; i++) {
            LocalDate day = weekStartDate.plusDays(i);
            DayOfWeek dow = day.getDayOfWeek();
            String dayVal = String.valueOf(dow.getValue());
            // Mark all past days including today as complete
            if (day.isBefore(today) || day.isEqual(today)) {

                ritualCompletion.put(dayVal, true);
            } else {
                ritualCompletion.put(dayVal, false);
            }
        }
        return ritualCompletion;
    }
}
