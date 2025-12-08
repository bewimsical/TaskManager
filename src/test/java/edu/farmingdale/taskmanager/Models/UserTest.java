package edu.farmingdale.taskmanager.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void createUser() {
        String testID = "AbC123xYz789KlmNOPqr";
        List<String> friends = new ArrayList<>();
        friends.add("Chomper");
        friends.add("HazelTheNut");
        friends.add("HoliCannoli");
        List<String> parties = new ArrayList<>();
        parties.add("ChoreCrew");
        parties.add("DoggoneClean");
        parties.add("DirtyDogs");
        Map<String, Boolean> weekStreak = new HashMap<>();
        weekStreak.put("Monday", false);
        weekStreak.put("Tuesday", false);
        weekStreak.put("Wednesday", false);
        weekStreak.put("Thursday", false);
        weekStreak.put("Friday", false);
        weekStreak.put("Saturday", false);
        weekStreak.put("Sunday", false);

        User user = new User.UserBuilder()
                .id(testID)
                .username("Jaxson")
                .password("iluvtreats")
                .email("jax819@woof.com")
                .age(49)
                .adult(true)
                .streak(0)
                .xpBonus(550)
                .friends(friends)
                .parties(parties)
                .vanquishedBossCount(0)
                .completedQuestCount(0)
                .profileUrl("www.woof-woof.com")
                .level(1)
                .xp(1350)
                .weekStreak(weekStreak)
                .weekStart("Monday")
                .lastRitualComplete("Sunday")
                .weekStartDate("Tuesday")
                .parentalControls(false)
                .build();

        assertEquals("AbC123xYz789KlmNOPqr", user.getId());
        assertEquals("Jaxson", user.getUsername());
        assertEquals("iluvtreats", user.getPassword());
        assertEquals("jax819@woof.com", user.getEmail());
        assertEquals(49, user.getAge());
        assertTrue(user.isAdult());
        assertEquals(0, user.getStreak());
        assertEquals(550, user.getXpBonus());
        assertEquals(friends, user.getFriends());
        assertEquals(parties, user.getParties());
        assertEquals(0,  user.getVanquishedBossCount());
        assertEquals(0,  user.getCompletedQuestCount());
        assertEquals("www.woof-woof.com",  user.getProfileUrl());
        assertEquals(1,  user.getLevel());
        assertEquals(1350, user.getXp());
        assertEquals(weekStreak, user.getWeekStreak());
        assertEquals("Monday", user.getWeekStart());
        assertEquals("Sunday", user.getLastRitualComplete());
        assertFalse(user.isParentalControls());
        assertEquals("Tuesday", user.getWeekStartDate());
    }
}