package visitor;

import data.Constructor;
import data.Modifier;
import data.Parameter;
import data.Statement;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class ConstructorVisitor extends Java9BaseVisitor<Constructor> {

    private ConstructorVisitor() {
    }

    public static ConstructorVisitor instance() {
        return HOLDER.INSTANCE;
    }

    @Override
    public Constructor visitConstructorDeclaration(Java9Parser.ConstructorDeclarationContext ctx) {
        final ParameterVisitor parameterVisitor = ParameterVisitor.instance();
        final StatementVisitor statementVisitor = StatementVisitor.instance();
        final List<Parameter> parameters = new ArrayList<>();
        parameters.add(ctx.constructorDeclarator()
                .formalParameterList()
                .lastFormalParameter()
                .formalParameter()
                .accept(parameterVisitor));

        final List<Statement> statements = ctx.constructorBody()
                .blockStatements()
                .blockStatement()
                .stream()
                .map(b -> b.accept(statementVisitor))
                .collect(Collectors.toList());

        final List<Modifier> modifiers = ctx.constructorModifier()
                .stream()
                .map(cm -> Modifier.of(cm.getText()))
                .collect(Collectors.toList());

        return new Constructor(parameters, statements, modifiers);
    }

    private static final class HOLDER {
        private static final ConstructorVisitor INSTANCE = new ConstructorVisitor();
    }
}
