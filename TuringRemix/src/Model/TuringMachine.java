package Model;

import java.util.ArrayList;

public class TuringMachine {
    // This class handles the setup of a turing machine so it can be run by MachineRunner.

    ArrayList<State> states;
    Integer numStates;

    //creates a blank turing machine. Used for testing.
    public TuringMachine() {
        numStates = 0;
        states = new ArrayList<>();
    }

    // Constructs a new turing machine with stateN randomized states.
    public TuringMachine(Integer stateN) {
        numStates = stateN;
        states = new ArrayList();
        for (Integer i = 0; i < numStates; i++) {
            State state = new State(i, numStates);
            states.add(state);
        }
    }

    private State getState(Integer i) {
        return states.get(i);
    }

    public void addState(State s) {
        states.add(s);
    }

    public int[] run(int[] tape) {
        int next = 0;
        int count = 0;
        State state = states.get(0);
        while ((next < tape.length) && (next > -1) && (count <= (tape.length^2))) {
            int head = tape[next];
            tape[next] = (int) (head * state.getWriteVal(head));
            if (state.getMove(head) == 1) {
                next += 1;
            } else {
                next -= 1;
            }
            state = getState(state.getNextState(head));
            count += 1;
        }

        return tape;
    }
}
