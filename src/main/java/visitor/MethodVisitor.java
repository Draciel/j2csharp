package visitor;

import data.Annotation;
import data.Method;
import data.Modifier;
import data.Parameter;
import data.Statement;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;
import utility.Nonnull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

final class MethodVisitor extends Java9BaseVisitor<Method> {

    private MethodVisitor() {
    }

    public static MethodVisitor instance() {
        return HOLDER.INSTANCE;
    }

    @Override
    public Method visitInterfaceMethodDeclaration(final Java9Parser.InterfaceMethodDeclarationContext ctx) {
        final String name = ctx.methodHeader().methodDeclarator().identifier().getText();
        final StatementWithoutTrailingSubstatementVisitor statementVisitor =
                StatementWithoutTrailingSubstatementVisitor.instance();
        final ParameterVisitor parameterVisitor = ParameterVisitor.instance();
        final AnnotationVisitor annotationVisitor = AnnotationVisitor.instance();

        final List<Parameter> parameters = new ArrayList<>();

        final Java9Parser.FormalParameterListContext formalParameterList = ctx.methodHeader()
                .methodDeclarator().formalParameterList();

        // no params case
        if (formalParameterList != null) {

            // two or more params case
            if (formalParameterList.formalParameters() != null) {
                parameters.addAll(formalParameterList.formalParameters().formalParameter()
                        .stream()
                        .map(fpc -> fpc.accept(parameterVisitor))
                        .collect(Collectors.toList()));
            }

            // add last parameter as it is not added by method above (or just one if method has only one parameter)
            parameters.add(formalParameterList
                    .lastFormalParameter()
                    .formalParameter()
                    .accept(parameterVisitor));
        }

        final List<Statement> statements;
        final boolean isDeclaration;
        if (ctx.methodBody().block() == null) {
            statements = Collections.singletonList(Statement.EmptyStatement.instance());
            isDeclaration = true;
        } else if (ctx.methodBody().block().blockStatements() == null) {
            statements = Collections.singletonList(Statement.EmptyStatement.instance());
            isDeclaration = false;
        } else {
            isDeclaration = false;
            statements = ctx.methodBody()
                    .block()
                    .blockStatements()
                    .blockStatement()
                    .stream()
                    .map(b -> b.accept(statementVisitor))
                    .collect(Collectors.toList());
        }

        // fixme we can optimize modifiers flow a bit
        final List<Annotation> annotations = ctx.interfaceMethodModifier().stream()
                .filter(MethodVisitor::isAnnotation)
                .map(cm -> cm.annotation().accept(annotationVisitor))
                .collect(Collectors.toList());

        final List<Modifier> modifiers = ctx.interfaceMethodModifier().stream()
                .filter(fm -> !isAnnotation(fm))
                .map(cm -> Modifier.of(cm.getText()))
                .collect(Collectors.toList());

        // check if class does have package private
        final Optional<Modifier> accessModifier = modifiers.stream()
                .filter(Modifier::isAccessModifier)
                .findFirst();

        if (!accessModifier.isPresent()) {
            modifiers.add(Modifier.PACKAGE);
        }

        final String result = ctx.methodHeader().result().getText();

        return new Method(name, parameters, statements, modifiers, annotations, result, isDeclaration);
    }

    @Override
    public Method visitMethodDeclaration(Java9Parser.MethodDeclarationContext ctx) {
        final String name = ctx.methodHeader().methodDeclarator().identifier().getText();
        final StatementWithoutTrailingSubstatementVisitor statementVisitor =
                StatementWithoutTrailingSubstatementVisitor.instance();
        final ParameterVisitor parameterVisitor = ParameterVisitor.instance();
        final AnnotationVisitor annotationVisitor = AnnotationVisitor.instance();

        final List<Parameter> parameters = new ArrayList<>();

        final Java9Parser.FormalParameterListContext formalParameterList = ctx.methodHeader()
                .methodDeclarator().formalParameterList();

        // no params case
        if (formalParameterList != null) {

            // two or more params case
            if (formalParameterList.formalParameters() != null) {
                parameters.addAll(formalParameterList.formalParameters().formalParameter()
                        .stream()
                        .map(fpc -> fpc.accept(parameterVisitor))
                        .collect(Collectors.toList()));
            }

            // add last parameter as it is not added by method above (or just one if method has only one parameter)
            parameters.add(formalParameterList
                    .lastFormalParameter()
                    .formalParameter()
                    .accept(parameterVisitor));
        }

        final List<Statement> statements;
        final boolean isDeclaration;
        if (ctx.methodBody().block() == null) {
            statements = Collections.singletonList(Statement.EmptyStatement.instance());
            isDeclaration = true;
        } else if (ctx.methodBody().block().blockStatements() == null) {
            statements = Collections.singletonList(Statement.EmptyStatement.instance());
            isDeclaration = false;
        } else {
            statements = ctx.methodBody()
                    .block()
                    .blockStatements()
                    .blockStatement()
                    .stream()
                    .map(b -> b.accept(statementVisitor))
                    .collect(Collectors.toList());
            isDeclaration = false;
        }

        // fixme we can optimize modifiers flow a bit
        final List<Annotation> annotations = ctx.methodModifier().stream()
                .filter(MethodVisitor::isAnnotation)
                .map(cm -> cm.annotation().accept(annotationVisitor))
                .collect(Collectors.toList());

        final List<Modifier> modifiers = ctx.methodModifier().stream()
                .filter(fm -> !isAnnotation(fm))
                .map(cm -> Modifier.of(cm.getText()))
                .collect(Collectors.toList());

        // check if class does have package private
        final Optional<Modifier> accessModifier = modifiers.stream()
                .filter(Modifier::isAccessModifier)
                .findFirst();

        if (!accessModifier.isPresent()) {
            modifiers.add(Modifier.PACKAGE);
        }

        final String result = ctx.methodHeader().result().getText();

        return new Method(name, parameters, statements, modifiers, annotations, result, isDeclaration);
    }

    private static boolean isAnnotation(@Nonnull final Java9Parser.MethodModifierContext ctx) {
        return ctx.annotation() != null;
    }

    private static boolean isAnnotation(@Nonnull final Java9Parser.InterfaceMethodModifierContext ctx) {
        return ctx.annotation() != null;
    }

    private static final class HOLDER {
        private static final MethodVisitor INSTANCE = new MethodVisitor();
    }
}
