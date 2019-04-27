package visitor;

import data.Statement;
import data.Method;
import data.Parameter;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class MethodVisitor extends Java9BaseVisitor<Method> {

    private MethodVisitor() {
    }

    public static MethodVisitor instance() {
        return HOLDER.INSTANCE;
    }

    @Override
    public Method visitMethodDeclaration(Java9Parser.MethodDeclarationContext ctx) {
        final String name = ctx.methodHeader().methodDeclarator().identifier().getText();
        final StatementVisitor statementVisitor = StatementVisitor.instance();
        final ParameterVisitor parameterVisitor = ParameterVisitor.instance();

        final List<Parameter> parameters = new ArrayList<>();
        parameters.add(ctx.methodHeader()
                .methodDeclarator()
                .formalParameterList()
                .lastFormalParameter()
                .formalParameter()
                .accept(parameterVisitor));
//        final ctx.methodHeader().methodDeclarator()
//                .formalParameterList()
//                .formalParameters()
//                .formalParameter()
//                .stream()
//                .map(b -> b.accept(parameterVisitor))
//                .collect(Collectors.toList());
        final List<Statement> statements = ctx.methodBody()
                .block()
                .blockStatements()
                .blockStatement()
                .stream()
                .map(b -> b.accept(statementVisitor))
                .collect(Collectors.toList());
        return new Method(name, parameters, statements);
    }

    private static final class HOLDER {
        private static final MethodVisitor INSTANCE = new MethodVisitor();
    }
}
