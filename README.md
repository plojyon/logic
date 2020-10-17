# logic
Logical expression evaluator
`javac Logic.java && java logic.java`

example output:
```
Enter logical expression to evaluate: ((((p v q) & (q => r)) & p) => q)
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
