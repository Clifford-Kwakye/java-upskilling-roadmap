package org.example.labTwo;

class ShopItems {
  int totalCount;

  class NonVegan {
    int iceCreamCount;
    int shakeCount;
    int totalCount;

    void increaseCount(int type, int count) {
      if (type == 1) {
        Nonvvegan.this.iceCreamCount += count;
      }

      if (type == 2) {
        NonVegan.this.shakeCount += count;
      }

      NonVegan.this.totalCount += count;
    }

    class Vegan {
      int smoothieCount;
      int slushieCount;
      int totalCount;

      void increaseCount(int type, int count) {
        if (type == 3) {
          Nonvvegan.this.smoothieCount += count;
        }

        if (type == 4) {
          NonVegan.this.slushieCount += count;
        }

        NonVegan.this.totalCount += count;
      }
    }
  }
}
