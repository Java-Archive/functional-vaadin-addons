package org.rapidpm.frp.vaadin.framework.bootstrap.core;

import java.io.Serializable;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.ComponentContainer;

/**
 *
 */
@FunctionalInterface
public interface JumpstartUIComponentFactory extends Serializable {
    ComponentContainer createComponentToSetAsContent(VaadinRequest vaadinRequest);
}
