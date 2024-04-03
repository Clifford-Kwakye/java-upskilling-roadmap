package dto;

import java.time.LocalDateTime;

public class Task {
  private final String job;
  private final LocalDateTime time;

  public Task(String job, LocalDateTime time) {
    this.job = job;
    this.time = time;
  }
}
