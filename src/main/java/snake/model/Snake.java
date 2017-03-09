package snake.model;

import java.util.LinkedList;
import java.util.List;

/**
 * @author afeher
 */
public class Snake {

    private List<BodyPart> body;
    private int append;

    public Snake() {
        this(new LinkedList<BodyPart>(), 0);
        body.add(new BodyPart(0, 0));
    }
    
    private Snake(List<BodyPart> body, int append) {
        this.body = body;
        this.append = append;
    }

    public void move(Direction direction) {
        BodyPart tail = body.get(body.size() - 1);
        BodyPart head = getHead();
        BodyPart headAtNewPosition = head.copy();
        headAtNewPosition.move(direction);
        body.add(0, headAtNewPosition);
        keepTailIfAppendingNeeded(tail);
    }

    private void keepTailIfAppendingNeeded(BodyPart tail) {
        if (append == 0) {
            body.remove(tail);
        } else {
            append--;
        }
    }

    public void append(int units) {
        append += units;
    }

    public BodyPart getHead() {
        return body.get(0);
    }

    public List<BodyPart> getBody() {
        return body;
    }

    public Snake copy() {
        return new Snake(new LinkedList(body), append);
    }
    
}
