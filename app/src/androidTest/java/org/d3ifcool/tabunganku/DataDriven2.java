package org.d3ifcool.tabunganku;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class DataDriven2 {
    List<String[]> rows = new ArrayList<>();
    @Rule
    public ActivityTestRule<AddPemasukkanActivity> activityActivityTestRule = new ActivityTestRule<>(AddPemasukkanActivity.class);

    @Test
    public void dataDriven() throws IOException, InterruptedException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openFile("test2.csv")));

        String line = " ";

        while ((line = bufferedReader.readLine())!= null){
            String[] str = line.split(",");
            rows.add(str);
        }

        for (int i = 0; i <= rows.size()-1; i++){
            onView(withId(R.id.edit_pemasukkan)).perform(typeText(rows.get(i)[0]));
            Thread.sleep(1000);
            onView(withId(R.id.edit_desc_pemasukkan)).perform(typeText(rows.get(i)[1]));
            Thread.sleep(1000);
            onView(withId(R.id.edit_pemasukkan)).perform(clearText());
            onView(withId(R.id.edit_desc_pemasukkan)).perform(clearText());

        }
    }

    private InputStream openFile(String filename) throws IOException{
        return getClass().getClassLoader().getResourceAsStream(filename);
    }
}
