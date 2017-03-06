package snake.model;

import java.util.LinkedList;
import java.util.List;

/**
 * @author afeher
 */
public class Snake {

    private List<BodyPart> body = new LinkedList<>();
    private int append = 0;

    public Snake() {
        body.add(new BodyPart(0, 0));
    }

    public void move(Direction direction) throws SelfBiteException {
        BodyPart tail = body.get(body.size() - 1);
        BodyPart head = getHead();
        BodyPart headAtNewPosition = head.copy();
        headAtNewPosition.move(direction);
        if (isSelfBiting(headAtNewPosition))
            throw new SelfBiteException();
        body.add(0, headAtNewPosition);
        keepTailIfAppendingNeeded(tail);
    }
    
    private boolean isSelfBiting(BodyPart headAtNewPosition) {
        for (BodyPart bodyPart : body) {
            if (bodyPart.getX() == headAtNewPosition.getX() 
                    && bodyPart.getY() == headAtNewPosition.getY()) {
                return true;
            }
        }
        return false;
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
    
}
