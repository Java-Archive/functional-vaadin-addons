package junit.org.rapidpm.frp.vaadin.addon.testbench.ng;

import org.junit.Test;
import org.rapidpm.frp.vaadin.addon.testbench.ng.BaseTest;
import com.vaadin.testbench.By;
import com.vaadin.testbench.TestBenchElement;
import com.vaadin.ui.Button;

public class ElementScreenCompareIT extends BaseTest {

  @Override
  public String getDeploymentPath() {
        return "/" + ElementQueryUI.class.getSimpleName();
  }

  @Test
  public void elementCompareScreen()
      throws Exception {
    openTestURL();

    T first = $(Button.class).first();
    button4.click();

    //        Assert.assertTrue(button4.compareScreen("button4"));
    //        TestBenchElement layout = (TestBenchElement) button4.findElement(By.xpath("../.."));
    //        Assert.assertTrue(layout.compareScreen("layout"));
  }

}
