package org.example;

import java.util.Stack;

public class CargoHistoryStack {
    private Stack<Cargo> stack;
    private static final int MAX_SIZE = 5;

    public CargoHistoryStack() {
        stack = new Stack<>();
    }

    public void push(Cargo cargo) {
        if (stack.size() >= MAX_SIZE) {
            stack.remove(0);  // Stack full, remove oldest
        }
        stack.push(cargo);
    }

    public void printHistory() {
        if (stack.isEmpty()) {
            System.out.println("No cargo history available.");
        } else {
            for (Cargo cargo : stack) {
                System.out.println(cargo);
            }
        }
    }
}
