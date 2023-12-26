package com.example.cnc;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.example.cnc.db.CncDao;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

  private CncDao dao;
  private Context context;

  @BeforeEach
  public void setup() {
    context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    dao = Statics.initDatabase(context);
  }

  @Test
  public void testUserCreate() {
    assertEquals(0, dao.getAllUsers().size());
  }

  @Test
  public void testIntent() {
    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

    System.out.println(appContext);
  }
}