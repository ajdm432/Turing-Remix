package Model;

public class State {
    // A single state. A turing machine is made up of many states.
    Integer stateNum;
    Integer nextState0;
    Integer nextState1;
    Integer move0; // 1 is forward, 0 is backwards.
    Integer move1; // 1 is forward, 0 is backwards.
    float writeOdd; // a value between 0.0 and 1.0
    float writeEven; // a value between 0.0 and 1.0


//    public State(Integer name, Integer ns0, Integer ns1, Integer m0, Integer m1, Integer w0, Integer w1) {
//        stateNum = name;
//        nextState0 = ns0;
//        nextState1 = ns1;
//        move0 = m0;
//        move1 = m1;
//        writeOdd = w0;
//        writeEven = w1;
//    }
    // Generates a non-random perscribed state.
    public State(Integer sn, Integer ns0, Integer ns1, Integer m0, Integer m1, float w0, float w1) {
        stateNum = sn;
        nextState0 = ns0;
        nextState1 = ns1;
        move0 = m0;
        move1 = m1;
        writeOdd = w0;
        writeEven = w1;
    }

    // Randomly generates a random state for a specific length of array of states for a turing machine.
    public State(Integer name, Integer numStates) {
        stateNum = name;
        nextState0 = (int)(Math.random() * (numStates));
        nextState1 = (int)(Math.random() * (numStates));
        move0 = randomBinary();
        move1 = randomBinary();
        writeOdd = (float) ((int)(Math.random() * (11))) / 10;
        writeEven = (float) ((int)(Math.random() * (11))) / 10;
    }

    // Randomly generates a 0 or a 1
    private Integer randomBinary() {
        if (Math.random() < 0.5) {
            return 0;
        }
        return 1;
    }

    // Takes the current value at the head and returns the name of the next state which should be switched to.
    public Integer getNextState(int val) {
        if(val % 2 != 0) {
            return  nextState0;
        }
        return nextState1;
    }

    // Takes current val at head and returns direction in which to move.
    public Integer getMove(int val) {
        if(val % 2 != 0) {
            return move0;
        }
        return move1;
    }

    // Takes current val at head and returns the value to replace it with.
    public float getWriteVal(int val) {
        if(val % 2 != 0) {
            return writeOdd;
        }
        return writeEven;
    }

    public static boolean isEven(String s1)
    {
        int l = s1.length();
        char[] s = s1.toCharArray();

        // Loop to traverse number from LSB
        boolean dotSeen = false;
        for (int i = l - 1; i >= 0; i--) {

            // We ignore trailing 0s after dot
            if (s[i] == '0' && dotSeen == false)
                continue;

            // If it is '.' we will check next digit and it
            // means decimal part is traversed.
            if (s[i] == '.') {
                dotSeen = true;
                continue;
            }

            // If digit is divisible by 2
            // means even number.
            if ((s[i] - '0') % 2 == 0)
                return true;

            return false;
        }

        return false;
    }
}
