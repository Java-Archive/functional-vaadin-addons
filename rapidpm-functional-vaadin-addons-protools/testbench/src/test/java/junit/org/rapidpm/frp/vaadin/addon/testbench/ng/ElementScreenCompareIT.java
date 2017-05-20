package junit.org.rapidpm.frp.vaadin.addon.testbench.ng;

import org.junit.Test;
import org.rapidpm.frp.vaadin.addon.testbench.ng.BaseTest;
import com.vaadin.testbench.By;
import com.vaadin.testbench.TestBenchElement;

public class ElementScreenCompareIT extends BaseTest {

  @Override
  public String getDeploymentPath() {
//        return "/" + ElementQueryUI.class.getSimpleName();
    return null;
  }

  @Test
  public void elementCompareScreen()
      throws Exception {
    openTestURL();
    TestBenchElement button4 = (TestBenchElement) findElements(By.className("v-button")).get(4);

    button4.click();

    //        Assert.assertTrue(button4.compareScreen("button4"));
    //        TestBenchElement layout = (TestBenchElement) button4.findElement(By.xpath("../.."));
    //        Assert.assertTrue(layout.compareScreen("layout"));
  }

}
