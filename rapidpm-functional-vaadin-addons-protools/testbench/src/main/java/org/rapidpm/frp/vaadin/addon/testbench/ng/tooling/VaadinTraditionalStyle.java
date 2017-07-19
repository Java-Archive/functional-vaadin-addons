package org.rapidpm.frp.vaadin.addon.testbench.ng.tooling;

import org.rapidpm.frp.vaadin.addon.testbench.ng.BaseTest;
import com.vaadin.server.LegacyApplication;
import com.vaadin.server.UIProvider;
import com.vaadin.ui.UI;

/**
 *
 */
public interface VaadinTraditionalStyle {

  /**
   * Returns the path for the given UI class when deployed on the test server.
   * The path contains the full path (appended to hostname+port) and must
   * start with a slash.
   * <p>
   * This method takes into account {@link BaseTest#isPush()} isPush()} and {@link #isDebug()}
   * when the path is generated.
   *
   * @param uiClass to get the part of the url from
   * @return The path to the given UI class
   */
  default String getDeploymentPath(Class<?> uiClass) {
    String runPath = "";
    if (UI.class.isAssignableFrom(uiClass)) {
      return runPath + "/" + uiClass.getSimpleName()
          + (isDebug() ? "?debug" : "");
    } else if (LegacyApplication.class.isAssignableFrom(uiClass)) {
      return runPath + "/" + uiClass.getSimpleName()
          + "?restartApplication" + (isDebug() ? "&debug" : "");
    } else {
      throw new IllegalArgumentException(
          "Unable to determine path for enclosing class "
              + uiClass.getCanonicalName());
    }
  }

  boolean isDebug();

  /**
   * Returns the path that should be used for the test. The path contains the
   * full path (appended to hostname+port) and must start with a slash.
   *
   * @return The URL path to the UI class to test
   */
  default String getDeploymentPath() {
    Class<?> uiClass = getUIClass();
    if (uiClass != null) {
      return getDeploymentPath(uiClass);
    } else
      throw new IllegalArgumentException("Unable to determine path for "
          + getClass().getCanonicalName());
  }

  /**
   * Returns the UI class the current test is connected to (or in special
   * cases UIProvider or LegacyApplication). Uses the enclosing class if the
   * test class is a static inner class to a UI class.
   * <p>
   * Test which are not enclosed by a UI class must implement this method and
   * return the UI class they want to test.
   * <p>
   * Note that this method will update the test name to the enclosing class to
   * be compatible with TB2 screenshot naming
   *
   * @return the UI class the current test is connected to
   */
  default Class<?> getUIClass() {
    try {
      // Convention: SomeUI UI class is used by SomeUITest
      // or SomeUIIT (IT-integration test)
      String testUIpackage = "com.vaadin.testUI";
      String uiClassName = "";
      if (getClass().getSimpleName().endsWith("Test")) {
        uiClassName = getClass().getSimpleName().replaceFirst("Test$",
            "");
      } else if (getClass().getSimpleName().endsWith("IT")) {
        uiClassName = getClass().getSimpleName()
            .replaceFirst("IT$", "");
      }
      Class<?> cls = Class.forName(testUIpackage + "." + uiClassName);
      if (isSupportedRunnerClass(cls)) {
        return cls;
      }
    } catch (Exception e) {
    }
    throw new RuntimeException(
        "Could not determine UI class. Ensure the test is named UIClassTest and is in the same package as the UIClass");
  }

  /**
   * @param cls
   * @return true if the given class is supported by ApplicationServletRunner
   */
  @SuppressWarnings("deprecation")
  default boolean isSupportedRunnerClass(Class<?> cls) {
    return UI.class.isAssignableFrom(cls)
        || UIProvider.class.isAssignableFrom(cls)
        || LegacyApplication.class.isAssignableFrom(cls);
  }

}
