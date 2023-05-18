package org.examplegame;

public class TeamMemberCount {
    private int value;

    public TeamMemberCount(){
        this.value = 0;
    }

    /**
     * increment teamMemberCount by 1
     */
    public void increment(){
        this.value++;
    }

    /**
     * decrement teamMemberCount by 1
     */
    public void decrement(){
        this.value--;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
