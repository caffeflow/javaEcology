为了ReduceJoin操作带给reduce端的压力，在关联表是小表的情况下，可以在mapper端来完成join操作。
思路是：将小表一次性缓存在内存中

![img.png](img.png)