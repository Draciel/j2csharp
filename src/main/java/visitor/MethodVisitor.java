package visitor;

import data.Statement;
import data.Method;
import data.Parameter;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class MethodVisitor extends Java9BaseVisitor<Method> {

    @Override
    public Method visitMethodDeclaration(Java9Parser.MethodDeclarationContext ctx) {
        final String name = ctx.methodHeader().methodDeclarator().identifier().getText();
        final StatementVisitor statementVisitor = new StatementVisitor();
        final ParameterVisitor parameterVisitor = new ParameterVisitor();

        final List<Parameter> parameters = Collections.emptyList();
        ctx.methodHeader().typeParameters()
                .typeParameterList()
                .typeParameter()
                .stream()
                .map(b -> b.accept(parameterVisitor))
                .collect(Collectors.toList());
        final List<Statement> statements = ctx.methodBody()
                .block()
                .blockStatements()
                .blockStatement()
                .stream()
                .map(b -> b.accept(statementVisitor))
                .collect(Collectors.toList());
        return new Method(name, parameters, statements);
    }
}
