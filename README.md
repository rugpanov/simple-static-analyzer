# Static Analyzer
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
[WARNING] Class has more than 250 lines
```

## Packaging
`mvn package`

## How to use
`java -jar analyzer.jar [path to the project]`

## Heuristics
- `BooleanMethodNamingChecker` - check whether boolean method names conform the rule 'should ask a question in the method names'.
- `ClassLineChecker` - check whether classes have less then 250 lines.
- `VariableNameLengthChecker` - check whether variables have not too long and not loo small names.
- `NamingConventionChecker` - check whether variables conform the 'camelCase' naming rule and classes conform the 'CamelCase' naming rule.
- `IncompatibleDeclarationsCheck` - check whether all declarations of methods are correct.
(For example: private and static methods should not be final /
non-public method should not have public constructor /
method cannot be 'abstract' and also 'private', 'static' )

## Download .jar
[`Release 1.0.0` - analyzer.jar](https://github.com/rugpanov/simple-static-analyzer/releases/download/1.0.0/analyzer.jar)