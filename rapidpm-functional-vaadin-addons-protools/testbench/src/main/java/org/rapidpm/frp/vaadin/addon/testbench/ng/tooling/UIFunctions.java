package org.rapidpm.frp.vaadin.addon.testbench.ng.tooling;

import static java.lang.String.valueOf;

import java.lang.reflect.Constructor;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.rapidpm.frp.functions.CheckedBiFunction;
import org.rapidpm.frp.model.Result;
import com.vaadin.ui.Component;

/**
 *
 */
public interface UIFunctions {

  static CheckedBiFunction<Class<? extends Component>, String, Component> newInstance() {
    return (aClass, caption) -> {
      Constructor<? extends Component> constructor = aClass.getConstructor(String.class);
      constructor.setAccessible(true);
      return constructor.newInstance(caption);
    };
  }

  static CheckedBiFunction<Class<? extends Component>, Integer, Component[]> newInstanceArray() {
    return (clazz, amount) -> IntStream
        .range(0, amount)
        .mapToObj(i -> newInstance().apply(clazz, valueOf(i)))
        .filter(Result::isPresent)
        .map(Result::get)
        .toArray(Component[]::new);
  }

  static CheckedBiFunction<Class<? extends Component>, Integer, Stream<Component>> newInstanceStream() {
    return (clazz, amount) -> IntStream
        .range(0, amount)
        .mapToObj(i -> newInstance().apply(clazz, valueOf(i)))
        .filter(Result::isPresent)
        .map(Result::get);
  }


}
