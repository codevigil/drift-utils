Thoughts on Making a Regular Expression Map:

What is a Regular Expression Map? A map which supports storing keys as regular expressions, and values as any generic object which wil be provided by user.

* How to query the map ? Same as a regular HashMap is supposed to be queried, but with the following features :
```java

regMap.put('abc*',0.94f);
regMap.put('*xyz',0.67f);

regMap.get('abcd'); //returns 0.94d
regMap.get('ab');   //returns null

```
* What if multiple regex match an input query ? Regex will be matched in the insertion order.

* How is it implemented internally ? My initial thoughts are to use a LinkedList to store the regular expressions and iterate over it to match the rules in insertion order, the big issue with this would be the slowness, esp. while dealing with repeated queries, we may speed it up with an inbuilt cache which plainly stores the last-n queries and their results.

* The map will be O(no. of patterns stored in the map).

* Where is a similar implementation discussed ?
  https://dzone.com/articles/regular-expression-hashmap
  












