package junit.org.rapidpm.frp.vaadin.addon.testbench.ng;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

/**
 *
 */
public class ElementQueryUI extends AbstractTestUI {
  @Override
  protected void init(VaadinRequest vaadinRequest) {
    setContent(new HorizontalLayout(new Button()));
  }
}
