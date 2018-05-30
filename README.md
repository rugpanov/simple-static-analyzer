# Static Analyzer ![release v1.0.0](https://img.shields.io/github/release/rugpanov/simple-static-analyzer.svg)
Simple static analyzer for the verification course
```
In the project there are 6 warnings!
In the project there are 51 info problems!

CodeUtils class has 3 warnings
[WARNING] Method CodeUtils:checkWhetherCyclicMatrix is not start with a question word.
[WARNING] Method CodeUtils:checkOtherParameters is not start with a question word.
[WARNING] Non-public class "CodeUtils" has public constructors.

Code class has 3 warnings
[WARNING] Method Code:equals is not start with a question word.
[WARNING] Method Code:ZERO_CODE not in 'camelCase'
[WARNING] Class Code has more than 250 lines
```

## Packaging
`mvn package`

## How to use
`java -jar analyzer.jar [path to the project]`

## Heuristics
- `BooleanMethodNamingChecker` - check whether boolean method names conform the rule 'should ask a question in the method names'.
- `ClassLineChecker` - check whether classes have less then 250 lines.
- `VariableNameLengthChecker` - check whether variables have not too long and not too small names.
- `NamingConventionChecker` - check whether variables conform the 'camelCase' naming rule and classes conform the 'CamelCase' naming rule.
- `IncompatibleDeclarationsCheck` - check whether all declarations of methods are correct.
(For example: private and static methods should not be final /
non-public method should not have public constructor /
method cannot be 'abstract' and also 'private', 'static').
- `ConstantConditionChecker` - check whether 'if/do/while' statement doesn't have meaningless constant conditions.
- `MaxNestingChecker` - check whether block nesting in methods not exceeds 3.
- `SameIfBranchesChecker` - check whether 'if' statement have different branches.
- `UnusedVariablesChecker` - check whether all method parameters appear in the body.

## Download .jar
[`Release 1.0.0` - analyzer.jar](https://github.com/rugpanov/simple-static-analyzer/releases/download/1.0.0/analyzer.jar)

\
![](assert/ifmo.svg)
