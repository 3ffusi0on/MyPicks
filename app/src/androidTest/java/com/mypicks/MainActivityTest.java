package com.mypicks;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.mypicks.views.main.MainActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
/**
 * Created by Pierre-Alain SIMON on 10/03/2016.
 * Vivoka
 * simon_p@vivoka.com
 */
public class MainActivityTest {
  @Rule
  public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
      MainActivity.class);

  @Test
  public void validateAllCycle() {
    onView(withId(R.id.imageMap)).check(matches(ViewMatchers.isDisplayed()));
    onView(withId(R.id.imageMap)).perform(click());
    onView(withId(R.id.new_champ_bt)).check(matches(ViewMatchers.isDisplayed()));
    onView(withId(R.id.menu_app_home)).check(matches(ViewMatchers.isDisplayed()));
    onView(withId(R.id.new_champ_bt)).perform(click());
    onView(withId(R.id.new_champ_list_bt)).check(matches(ViewMatchers.isDisplayed()));
    onView(withId(R.id.menu_app_home)).check(matches(ViewMatchers.isDisplayed()));
    onView(withId(R.id.menu_app_home)).perform(click());
    onView(withId(R.id.imageMap)).check(matches(ViewMatchers.isDisplayed()));
  }


}
