package visitor;

import data.*;
import generated.Java9BaseVisitor;
import generated.Java9Parser;
import utility.Nonnull;

import java.util.ArrayList;
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
        final BlockVisitor blockVisitor = BlockVisitor.instance();
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

        final Statement block;
        final boolean isDeclaration;
        if (ctx.methodBody().block() == null) {
            block = Statement.EmptyStatement.instance();
            isDeclaration = true;
        } else if (ctx.methodBody().block().blockStatements() == null) {
            block = Statement.EmptyStatement.instance();
            isDeclaration = false;
        } else {
            isDeclaration = false;
            block = ctx.methodBody()
                    .block()
                    .accept(blockVisitor);
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

        final Type resultType = new Type(ctx.methodHeader().result().getText());

        return new Method(name, parameters, block, modifiers, annotations, resultType, isDeclaration);
    }

    @Override
    public Method visitMethodDeclaration(Java9Parser.MethodDeclarationContext ctx) {
        final String name = ctx.methodHeader().methodDeclarator().identifier().getText();
        final BlockVisitor blockVisitor = BlockVisitor.instance();
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
            // and is not var arg
            if (formalParameterList.lastFormalParameter().formalParameter() != null) {
                parameters.add(formalParameterList.lastFormalParameter()
                        .formalParameter()
                        .accept(parameterVisitor));
            } else {
                parameters.add(formalParameterList.lastFormalParameter()
                        .accept(parameterVisitor));
            }
        }

        final Statement block;
        final boolean isDeclaration;
        if (ctx.methodBody().block() == null) {
            block = Statement.EmptyStatement.instance();
            isDeclaration = true;
        } else if (ctx.methodBody().block().blockStatements() == null) {
            block = Statement.EmptyStatement.instance();
            isDeclaration = false;
        } else {
            block = ctx.methodBody()
                    .block()
                    .accept(blockVisitor);
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

        final Type resultType = new Type(ctx.methodHeader().result().getText());

        return new Method(name, parameters, block, modifiers, annotations, resultType, isDeclaration);
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
