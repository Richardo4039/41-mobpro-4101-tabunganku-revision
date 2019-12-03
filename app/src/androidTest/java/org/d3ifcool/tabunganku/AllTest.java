package org.d3ifcool.tabunganku;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AllTest {

//    @Rule
//    public ActivityTestRule<AllTest> activityRule =
//            new ActivityTestRule(MyClass.class);

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    String inputJenisTrx = "Makan";
    String inputNominal = "10000";
    String inputKeterangan = "Makan Siang";

    String inputPemasukkan = "200000";
    String inputDescPem = "Gaji Pertama";

    @Test
    public void tambahPengeluaran() throws Exception{
//        onView(withId(R.id.fab_menu)).perform(click());

        onView(withId(R.id.fabPengeluaran)).perform(click());
        onView(withId(R.id.fabPengeluaran)).perform(click());
        onView(withId(R.id.edit_text_phone)).perform(replaceText(inputJenisTrx));
        onView(withId(R.id.edit_text_amount)).perform(replaceText(inputNominal));
        onView(withId(R.id.edit_text_description)).perform(replaceText(inputKeterangan));
        onView(withId(R.id.button_d3)).perform(click());
        onView(withId(R.id.mdtp_ok)).perform(click());

        onView(withId(R.id.action_save)).perform(click());
    }

    @Test
    public void tambahPemasukkan() throws Exception{
//        onView(withId(R.id.fab_menu)).perform(click());

        onView(withId(R.id.fabPemasukkan)).perform(click());
        onView(withId(R.id.fabPemasukkan)).perform(click());
        onView(withId(R.id.edit_pemasukkan)).perform(replaceText(inputPemasukkan));
        onView(withId(R.id.edit_desc_pemasukkan)).perform(replaceText(inputDescPem));
        onView(withId(R.id.button_d3)).perform(click());
        onView(withId(R.id.mdtp_ok)).perform(click());

        onView(withId(R.id.action_save)).perform(click());
    }

    @Test
    public void lihatDetailPengeluaran() throws Exception{
//        onView(withId(R.id.list_view_item)).perform(click());
//        onData(anything()).inAdapterView(withId(R.id.list_view_item)).atPosition(1).perform(click());

//        onView(withId(R.id.list_view_item)).perform(RecyclerViewAction.<VH>actionOnItemPosition(0,click()));
//        onData(withId(R.id.list_view_item)).atPosition(1).perform(click());
        ViewInteraction constraintLayout = onView(
                childAtPosition(
                        allOf(withId(R.id.list_view_item),
                                childAtPosition(
                                        withId(R.id.scrollView2),
                                        0)),
                        0));
        constraintLayout.perform(scrollTo(), click());
    }

    @Test
    public void lihatDetailPemasukkan() throws Exception{
//        onView(withId(R.id.list_view_item1)).perform(click());

//        onData(anything()).inAdapterView(withId(R.id.list_view_item1)).atPosition(2).perform(click());
        ViewInteraction constraintLayout = onView(
                childAtPosition(
                        allOf(withId(R.id.list_view_item1),
                                childAtPosition(
                                        withId(R.id.scView),
                                        0)),
                        0));
        constraintLayout.perform(scrollTo(), click());
    }

    @Test
    public void deletePengeluaran() throws Exception{
        ViewInteraction constraintLayout = onView(
                childAtPosition(
                        allOf(withId(R.id.list_view_item),
                                childAtPosition(
                                        withId(R.id.scrollView2),
                                        0)),
                        0));
        constraintLayout.perform(scrollTo(), click());

        ViewInteraction actionMenuItemView2 = onView(
                allOf(withId(R.id.action_delete), withContentDescription("Hapus"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        2),
                                0),
                        isDisplayed()));
        actionMenuItemView2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(android.R.id.button1), withText("Ya"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton3.perform(scrollTo(), click());
    }

    @Test
    public void deletePemasukkan() throws Exception{
        ViewInteraction constraintLayout = onView(
                childAtPosition(
                        allOf(withId(R.id.list_view_item1),
                                childAtPosition(
                                        withId(R.id.scView),
                                        0)),
                        0));
        constraintLayout.perform(scrollTo(), click());

        ViewInteraction actionMenuItemView2 = onView(
                allOf(withId(R.id.action_delete), withContentDescription("Hapus"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        2),
                                0),
                        isDisplayed()));
        actionMenuItemView2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(android.R.id.button1), withText("Ya"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton3.perform(scrollTo(), click());
    }

    @Test

    public void testAllFunction() throws Exception{
        onView(withId(R.id.fabPengeluaran)).perform(click());
        onView(withId(R.id.fabPengeluaran)).perform(click());
        onView(withId(R.id.edit_text_phone)).perform(replaceText(inputJenisTrx));
        onView(withId(R.id.edit_text_amount)).perform(replaceText(inputNominal));
        onView(withId(R.id.edit_text_description)).perform(replaceText(inputKeterangan));
        onView(withId(R.id.button_d3)).perform(click());
        onView(withId(R.id.mdtp_ok)).perform(click());

        onView(withId(R.id.action_save)).perform(click());

        onView(withId(R.id.fabPemasukkan)).perform(click());
        onView(withId(R.id.fabPemasukkan)).perform(click());
        onView(withId(R.id.edit_pemasukkan)).perform(replaceText(inputPemasukkan));
        onView(withId(R.id.edit_desc_pemasukkan)).perform(replaceText(inputDescPem));
        onView(withId(R.id.button_d3)).perform(click());
        onView(withId(R.id.mdtp_ok)).perform(click());

        onView(withId(R.id.action_save)).perform(click());

        ViewInteraction constraintLayout = onView(
                childAtPosition(
                        allOf(withId(R.id.list_view_item),
                                childAtPosition(
                                        withId(R.id.scrollView2),
                                        0)),
                        0));
        constraintLayout.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction constraintLayout1 = onView(
                childAtPosition(
                        allOf(withId(R.id.list_view_item1),
                                childAtPosition(
                                        withId(R.id.scView),
                                        0)),
                        0));
        constraintLayout1.perform(scrollTo(), click());

        appCompatImageButton.perform(click());

        constraintLayout.perform(scrollTo(), click());

        ViewInteraction actionMenuItemView2 = onView(
                allOf(withId(R.id.action_delete), withContentDescription("Hapus"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        2),
                                0),
                        isDisplayed()));
        actionMenuItemView2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(android.R.id.button1), withText("Ya"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton3.perform(scrollTo(), click());

        constraintLayout1.perform(scrollTo(), click());

        ViewInteraction actionMenuItemView3 = onView(
                allOf(withId(R.id.action_delete), withContentDescription("Hapus"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        2),
                                0),
                        isDisplayed()));
        actionMenuItemView3.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(android.R.id.button1), withText("Ya"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton4.perform(scrollTo(), click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

//    @Test
//    public void clickMenuJurnal_IsiJurnal() throws Exception {
//
//        onView(withId(R.id.floatingActionButton)).perform(click());
//
//        onView(withId(R.id.edit_text_name)).perform(replaceText(inputNama));
//        onView(withId(R.id.edit_text_phone)).perform(replaceText(inputJenisTrx));
//        onView(withId(R.id.edit_text_amount)).perform(replaceText(inputNominal));
//        onView(withId(R.id.edit_text_description)).perform(replaceText(inputKeterangan));
//    }
//
//    @Test
//    public void clickMenuJurnal_AturTanggal() throws Exception {
//
//        onView(withId(R.id.floatingActionButton)).perform(click());
//
//        onView(withId(R.id.button_d3)).perform(click());
//    }
//
//    @Test
//    public void clickMenuJurnal_AturJam() throws Exception {
//        onView(withId(R.id.floatingActionButton)).perform(click());
//
//        onView(withId(R.id.button_d3)).perform(click());
//        onView(withId(R.id.mdtp_ok)).perform(click());
//        onView(withId(R.id.mdtp_ok)).perform(click());
//    }
//
//    @Test
//    public void taskFullFungsi() throws Exception {
//
//        onView(withId(R.id.floatingActionButton)).perform(click());
//
//        onView(withId(R.id.edit_text_name)).perform(replaceText(inputNama));
//        onView(withId(R.id.edit_text_phone)).perform(replaceText(inputJenisTrx));
//        onView(withId(R.id.edit_text_amount)).perform(replaceText(inputNominal));
//        onView(withId(R.id.edit_text_description)).perform(replaceText(inputKeterangan));
//
//        onView(withId(R.id.button_d3)).perform(click());
//        onView(withId(R.id.mdtp_ok)).perform(click());
//        onView(withId(R.id.mdtp_ok)).perform(click());
//
//        onView(withId(R.id.action_save)).perform(click());
//
//    }
}
