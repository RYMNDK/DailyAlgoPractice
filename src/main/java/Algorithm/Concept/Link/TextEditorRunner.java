package Algorithm.Concept.Link;

public class TextEditorRunner {

    public static void main(String[] args) {
        System.out.println("=== TextEditor Functionality Tests ===\n");

        // Test 1: Initialize with empty string
        System.out.println("Test 1: Initialize with empty string");
        TextEditor editor1 = new TextEditor("");
        System.out.println("Editor content: [" + editor1 + "]");

        // Test 2: Initialize with some text
        System.out.println("\nTest 2: Initialize with text");
        TextEditor editor2 = new TextEditor("Hello");
        System.out.println("Editor content: [" + editor2 + "]");

        // Test 3: Add characters
        System.out.println("\nTest 3: Add characters");
        TextEditor editor3 = new TextEditor("");
        System.out.println("Initial: [" + editor3 + "]");
        editor3.addChar('A');
        System.out.println("After adding 'A': [" + editor3 + "]");
        editor3.addChar('B');
        System.out.println("After adding 'B': [" + editor3 + "]");
        editor3.addChar('C');
        System.out.println("After adding 'C': [" + editor3 + "]");

        // Test 4: Move cursor
        System.out.println("\nTest 4: Move cursor");
        TextEditor editor4 = new TextEditor("ABCDEF");
        System.out.println("Initial: [" + editor4 + "]");
        editor4.moveBack();
        System.out.println("After moveBack(): [" + editor4 + "]");
        editor4.moveBack();
        System.out.println("After moveBack(): [" + editor4 + "]");
        editor4.moveNext();
        System.out.println("After moveNext(): [" + editor4 + "]");
        editor4.moveEnd();
        System.out.println("After moveEnd(): [" + editor4 + "]");

        // Test 5: Edge case - Move cursor before start
        System.out.println("\nTest 5: Edge case - Move cursor before start");
        TextEditor editor5 = new TextEditor("AB");
        System.out.println("Initial: [" + editor5 + "]");
        editor5.moveBack();
        System.out.println("After moveBack(): [" + editor5 + "]");
        editor5.moveBack();
        System.out.println("After moveBack(): [" + editor5 + "]");
        editor5.moveBack(); // Should not move past start
        System.out.println("After moveBack() (should not move): [" + editor5 + "]");

        // Test 6: Edge case - Move cursor after end
        System.out.println("\nTest 6: Edge case - Move cursor after end");
        TextEditor editor6 = new TextEditor("AB");
        System.out.println("Initial: [" + editor6 + "]");
        editor6.moveNext(); // Should not move past end
        System.out.println("After moveNext() (should not move): [" + editor6 + "]");

        // Test 7: Backspace
        System.out.println("\nTest 7: Backspace");
        TextEditor editor7 = new TextEditor("ABCDEF");
        System.out.println("Initial: [" + editor7 + "]");
        editor7.backSpace();
        System.out.println("After backSpace(): [" + editor7 + "]");
        editor7.moveBack();
        editor7.moveBack();
        System.out.println("After moving cursor: [" + editor7 + "]");
        editor7.backSpace();
        System.out.println("After backSpace(): [" + editor7 + "]");

        // Test 8: Edge case - Backspace at start
        System.out.println("\nTest 8: Edge case - Backspace at start");
        TextEditor editor8 = new TextEditor("ABC");
        System.out.println("Initial: [" + editor8 + "]");
        editor8.moveBack();
        editor8.moveBack();
        editor8.moveBack();
        System.out.println("After moving to start: [" + editor8 + "]");
        editor8.backSpace(); // Should do nothing
        System.out.println("After backSpace() (should do nothing): [" + editor8 + "]");

        // Test 9: Delete
        System.out.println("\nTest 9: Delete");
        TextEditor editor9 = new TextEditor("ABCDEF");
        System.out.println("Initial: [" + editor9 + "]");
        editor9.moveBack();
        editor9.moveBack();
        System.out.println("After moving cursor: [" + editor9 + "]");
        editor9.delete();
        System.out.println("After delete(): [" + editor9 + "]");

        // Test 10: Edge case - Delete at end
        System.out.println("\nTest 10: Edge case - Delete at end");
        TextEditor editor10 = new TextEditor("ABC");
        System.out.println("Initial: [" + editor10 + "]");
        editor10.delete(); // Should do nothing
        System.out.println("After delete() (should do nothing): [" + editor10 + "]");

        // Test 11: Complex sequence of operations
        System.out.println("\nTest 11: Complex sequence of operations");
        TextEditor editor11 = new TextEditor("Hello");
        System.out.println("Initial: [" + editor11 + "]");
        editor11.moveBack();
        editor11.moveBack();
        System.out.println("After moving cursor: [" + editor11 + "]");
        editor11.addChar('!');
        System.out.println("After adding '!': [" + editor11 + "]");
        editor11.moveBack();
        editor11.backSpace();
        System.out.println("After backSpace(): [" + editor11 + "]");
        editor11.moveEnd();
        editor11.addChar('.');
        System.out.println("After adding '.': [" + editor11 + "]");

        // Test 12: Test toStringReverse()
        System.out.println("\nTest 12: Test toStringReverse()");
        TextEditor editor12 = new TextEditor("ABCDEF");
        System.out.println("Forward: [" + editor12 + "]");
        System.out.println("Reverse: [" + editor12.toStringReverse() + "]");

        // Test 13: Empty editor operations
        System.out.println("\nTest 13: Empty editor operations");
        TextEditor editor13 = new TextEditor("");
        System.out.println("Initial empty: [" + editor13 + "]");
        editor13.backSpace(); // Should do nothing
        System.out.println("After backSpace(): [" + editor13 + "]");
        editor13.addChar('X');
        System.out.println("After adding 'X': [" + editor13 + "]");
        editor13.backSpace();
        System.out.println("After backSpace(): [" + editor13 + "]");

        // Test 14: Cursor display in toString
        System.out.println("\nTest 14: Cursor display in toString");
        TextEditor editor14 = new TextEditor("ABCDE");
        editor14.moveBack();
        editor14.moveBack();
        editor14.moveBack();
        System.out.println("Cursor at position 2: [" + editor14 + "]");

    }
}

class TextEditor {
    public InputCharacter cursor;

    public InputCharacter start;
    public InputCharacter end;

    static class InputCharacter {
        public char letter;

        public InputCharacter next;
        public InputCharacter prev;

        public InputCharacter(char letter, InputCharacter next, InputCharacter prev) {
            this.letter = letter;

            this.next = next;
            this.prev = prev;
        }

        @Override
        public String toString() {
            return "" + this.letter;
        }
    }

    // initialText can be empty NOT null
    public TextEditor(String initialText) {
        this.start = new InputCharacter((char) 0, null, null);
        this.end = new InputCharacter((char) 1, null, null);
        this.start.next = this.end;

        // build the chain
        this.cursor = this.start;
        for (int i = 0; i < initialText.length(); i++) {
            this.cursor.next = new InputCharacter(initialText.charAt(i), this.end, this.cursor);
            this.cursor = cursor.next;
        }

        // add the end node
        this.cursor.next = this.end;
        this.end.prev = this.cursor;

        // move the cursor to the end
        this.cursor = this.end;
    }

    // delete the char before cursor
    public void backSpace() {
        if (cursor.prev == start) {
            return;
        }
        InputCharacter temp = this.cursor.prev.prev;
        temp.next = this.cursor;
        this.cursor.prev = temp;
    }

    // delete the char at cursor
    public void delete() {
        if (cursor == this.end) {
            return;
        }
        InputCharacter temp = this.cursor.prev;
        this.cursor = this.cursor.next;
        temp.next = this.cursor;
        this.cursor.prev = temp;
    }

    // insert char before cursor
    public void addChar(char letter) {
        InputCharacter temp = new InputCharacter(letter, cursor, cursor.prev);
        temp.next.prev = temp;
        temp.prev.next = temp;

        this.cursor = temp.next;
    }

    public void moveBack() {
        if (this.cursor != start) {
            this.cursor = this.cursor.prev;
        }
    }

    public void moveNext() {
        if (this.cursor != end) {
            this.cursor = this.cursor.next;
        }
    }

    public void moveEnd() {
        this.cursor = this.end;
    }

    public String toStringReverse() {
        StringBuilder sb = new StringBuilder();
        InputCharacter current = this.end.prev;
        while(current != start) {
            sb.append(current.letter);
            current = current.prev;
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        InputCharacter current = this.start.next;
        while(current != end) {
            if (current == this.cursor) {
                sb.append("\033[4m").append(current.letter).append("\033[0m");
            } else {
                sb.append(current.letter);
            }

            current = current.next;
        }
        return sb.toString();
    }

}

