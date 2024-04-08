package CustomQueue;

import java.util.ArrayList;
import java.util.List;

public class CustomQueue<T> extends ArrayList<T> {
  private static CustomQueue<Object> INSTANCE;

  private CustomQueue() {}

  public static synchronized CustomQueue getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new CustomQueue();
    }

    return INSTANCE;
  }

  public void offer(T element) {
    super.add(element);
  }

  public T poll() {
    if (super.isEmpty()) return null;

    T element = super.getFirst();
    super.removeFirst();

    return element;
  }

  public T peek() {
    if (super.isEmpty()) return null;

    return super.getFirst();
  }

  public List<T> peekAll() {
    return super.stream().toList();
  }
}
