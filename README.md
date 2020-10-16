# logic
Logic expression evaluator

example output:
```
 p | q | r | ((((p v q) & (q => r)) & p) => q)
------------------
 0 | 0 | 0 | 1
 0 | 0 | 1 | 1
 0 | 1 | 0 | 1
 0 | 1 | 1 | 1
 1 | 0 | 0 | 0
 1 | 0 | 1 | 0
 1 | 1 | 0 | 1
 1 | 1 | 1 | 1
```
