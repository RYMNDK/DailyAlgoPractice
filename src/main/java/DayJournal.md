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

### Java DS (1)
java.util.LinkedList 可以插入 null
java.util.PriorityQueue 不能插入 null 