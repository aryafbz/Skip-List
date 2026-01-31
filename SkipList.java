import java.util.Random;

class SkipListNode{
    int value;
    SkipListNode[] forward; // be node badi eshare mi konad
    SkipListNode(int value , int level){
        this.value = value;
        this.forward = new SkipListNode[level + 1];
    }
}

public class SkipList {
    private static final int MAX_LEVEL = 16;
    private static final double p = 0.5; // ehtemal raftan be laye bala tar
    private final SkipListNode header; // (sentinel) shoro va payan ra moshakhas mikonad
    private int level;
    private final Random random;

    public SkipList(){
        this.level = 0;
        this.header = new SkipListNode(Integer.MIN_VALUE , MAX_LEVEL);
        this.random = new Random();
    }

    private int randomLevel(){
        int lvl = 0;
        while(random.nextDouble() < p && lvl < MAX_LEVEL){
            lvl++;
        }

        return lvl;
    }

    public boolean search(int value){
        SkipListNode curr = header;
        for(int i = level ; i >= 0 ; i--){
            while(curr.forward[i] != null && curr.forward[i].value < value){
                curr = curr.forward[i];
            }
        }
        curr = curr.forward[0];
        return curr != null && curr.value == value;
    }

    public boolean insert(int value){
        SkipListNode[] update = new SkipListNode[MAX_LEVEL + 1];
        SkipListNode curr = header;

        for(int i = level ; i >= 0 ; i--){
            while(curr.forward[i] != null && curr.forward[i].value < value){
                curr = curr.forward[i];
            }
            update[i] = curr;

        }
        curr = curr.forward[0];
        if(curr != null && curr.value == value)
            return false;

        int lvl = randomLevel();
        if(lvl > level){
            for(int i = level + 1 ; i <= lvl ; i++){
                update[i] = header;
            }
            level = lvl;
        }

        SkipListNode newNode = new SkipListNode(value , lvl);
        for(int i = 0 ; i <= lvl ; i++){
            newNode.forward[i] = update[i].forward[i];
            update[i].forward[i] = newNode;
        }

        return true;
    }

    public boolean delete(int value){
        SkipListNode[] update = new SkipListNode[MAX_LEVEL + 1];
        SkipListNode curr = header;

        for(int i = level ; i >= 0 ; i--){
            while(curr.forward[i] != null && curr.forward[i].value < value){
                curr = curr.forward[i];
            }
            update[i] = curr;
        }
        curr = curr.forward[0];

        if(curr == null || curr.value != value)
            return false;

        for(int i = 0 ; i <= level ; i++){
            if(update[i].forward[i] != curr)
                break;

            update[i].forward[i] = curr.forward[i];
        }

        while(level > 0 && header.forward[level] == null){
            level--;
        }

        return true;
    }
}









