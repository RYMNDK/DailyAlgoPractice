# Daily notes
## 1/1/2025
### LFU Cache (1)
O(1)重点就是不能按顺序查找。
从LRU开始，我有head， tail 和每一个节点的地址（HashMap）和一个双向链表。
然后用一个HashMap放书签，书签2的前一个就是新插入的地址。
还有第三个HashMap放每一个节点和出现次数。
#### 5个功能
- GET不存在key -> return -1。
- GET已存在key以后可以把这个节点插到下一个书签的前一个。
- PUT更新节点以后要和GET已存key。
- PUT插入不大于最大可以存在书签2的前一个位置。
- PUT插入大于最大可以删掉tail的后一个节点（LF+LRU）然后PUT。

## 1/2/2025
### Java DS (1)
java.util.LinkedList 可以插入 null
java.util.PriorityQueue 不能插入 null 

## 1/3/2025
### CRDT (1)
原文在[这里](https://plus.excalidraw.com/blog/building-excalidraw-p2p-collaboration-feature)

Excalidraw 选的CP用的socketio然后用解决冲突的算法（CRDT）确保大家同时看到同样的内容。
这里四个可能性：
- A 画了C，B上没有：B把A更新到B上
- A 删了C，B 上有： B不更新C (C上有一个isDeleted=true)
- A 更新了C同时B更新了D： 每个C和D上有个版本号，版本号最高的获胜。
- A 和B 同时 更新了C：AC和BC上都有本地的随机数字（nounce），数字小的获胜。

假如我把一行黑字改成红色，我可能会看到那段字变成红色，也有可能看到那段字变成绿色，因为其他人也更新了这段字。

## 1/5/2025 (补签)
### Java DS (2)
HashMap vs TreeMap：
- 线程不安全！！
- HashMap and TreeMap 不保证插入顺序，可以用LinkedHashMap 保证插入顺序
- HashMap 的 key 没有排序，TreeMap 保证所有 key 有排序
- Hashmap 内部用的hashtable，Treemap 内部用的红黑树。
- HashMap允许一个 null key，TreeMap 一般不能插入null key
- HashMap 时间复杂度 是 O（1）， Treemap O（LogN）
- TreeMap 使用比空间比 Hashmap 要大，可以做 range 和 navigable 操作 
- 大部分DSA用HashMap足够，得当的TreeMap可以优化答案。

(待续）

## 1/6/2025
### Java DS (3)
之前聊的hashmap 提到了LinkedHashMap， 刚好补点笔记
LinkedHashmap：
- 这个也是线程不安全！
- 双向链表 + HashMap，保证插入顺序: 
- 和HashMap一样，允许一个null key
- 储存比HashMap 要高 （双向链表）
- 操作比HashMap 要慢 20-30% 
- 这个和 TreeMap 一样，关键时刻可以给面试官来一手

```
    // Leetcode 146. LRU Cache
    public LRUCacheUsingLinkedHashMap(int capacity){
        super(capacity, 0.75f, true); // true for access-order
        this.capacity = capacity;
    }
    
    public int get(int key){
        return super.getOrDefault(key, -1);
    }
    
    public void put(int key, int value){
        super.put(key, value);
    }
    
    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest){
        return this.size() > capacity;
    }
```

挖个坑以后看其他的map。
