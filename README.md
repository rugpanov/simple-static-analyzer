# Static Analyzer
Simple static analyzer for my verification course

## Packaging
`mvn package`

## How to use
`java -jar analyzer.jar [path to the project]`

## Heuristics
- `BooleanMethodNamingChecker` - check whether boolean method names conform the rule 'should ask a question in the method names'.
- `ClassLineChecker` - check whether classes have less then 250 lines.
- `VariableNameLengthChecker` - check whether variables have not too long and not loo small names.
- `NamingConventionChecker` - check whether variables conform the 'camelCase' naming rule and classes conform the 'CamelCase' naming rule.
