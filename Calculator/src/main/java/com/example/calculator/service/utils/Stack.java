package com.example.calculator.service.utils;

import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class Stack<T> extends ArrayList<T> {
  public void push(T element) {
    super.add(element);
  }

  public void pop() {
    if (!super.isEmpty()) {
      super.remove(super.size() - 1);
    }
    throw new ArrayIndexOutOfBoundsException();
  }

  public void popUntil(T element) {
    if (super.isEmpty()) throw new ArrayIndexOutOfBoundsException();

    for (int i = super.size() - 1; i > 0; i--) {
      if (!super.get(i).equals(element)) super.remove(super.size() - 1);
    }
  }
}
