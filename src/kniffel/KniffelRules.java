package kniffel;


public class KniffelRules {
    public static boolean hasXOfKind(int[] dice, int x) {
        for (int i = 1; i <= 6; i++) {
            int count = 0;
            for (int die : dice) {
                if (die == i) {
                    count++;
                }
            }
            if (count >= x) {
                return true;
            }
        }
        return false;
    }

    public static int sumOfNumber(int[] dice, int number) {
        int sum = 0;
        for (int die : dice) {
            if (die == number) {
                sum += die;
            }
        }
        return sum;
    }

    public static boolean isFullHouse(int[] dice) {
        int[] counts = new int[7]; // 1-6, Index 0 ignoriert
        for (int die : dice) {
            counts[die]++;
        }

        boolean hasThree = false;
        boolean hasTwo = false;

        for (int count : counts) {
            if (count == 3) {
                hasThree = true;
            }
            if (count == 2) {
                hasTwo = true;
            }
        }

        return hasThree && hasTwo;
    }

    public static boolean isSmallStraight(int[] dice) {
        boolean[] hasNumber = new boolean[7];
        for (int die : dice) {
            hasNumber[die] = true;
        }

        return (hasNumber[1] && hasNumber[2] && hasNumber[3] && hasNumber[4]) ||
               (hasNumber[2] && hasNumber[3] && hasNumber[4] && hasNumber[5]) ||
               (hasNumber[3] && hasNumber[4] && hasNumber[5] && hasNumber[6]);
    }

    public static boolean isLargeStraight(int[] dice) {
        boolean[] hasNumber = new boolean[7];
        for (int die : dice) {
            hasNumber[die] = true;
        }

        return (hasNumber[1] && hasNumber[2] && hasNumber[3] && hasNumber[4] && hasNumber[5]) ||
               (hasNumber[2] && hasNumber[3] && hasNumber[4] && hasNumber[5] && hasNumber[6]);
    }

    public static boolean isKniffel(int[] dice) {
        int first = dice[0];
        for (int die : dice) {
            if (die != first) {
                return false;
            }
        }
        return true;
    }

    public static int sumAllDigits(int[] dice) {
        int sum = 0;
        for (int die : dice) {
            sum += die;
        }
        return sum;
    }
}