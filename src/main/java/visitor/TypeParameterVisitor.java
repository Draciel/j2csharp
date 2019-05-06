package visitor;

import data.Generic;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

import java.util.Collections;
import java.util.List;

class TypeParameterVisitor extends Java9BaseVisitor<Generic> {

    private TypeParameterVisitor() {
        //no instance
    }

    public static TypeParameterVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public Generic visitTypeParameter(final Java9Parser.TypeParameterContext ctx) {
        final WildcardTypeParameterVisitor wildcardVisitor = WildcardTypeParameterVisitor.instance();

        final String typeName = ctx.identifier().getText();
        final List<Generic> generic;
        final String boundType;
        if (ctx.typeBound() != null) {
            boundType = ctx.typeBound().EXTENDS().getText();
            if (ctx.typeBound().classOrInterfaceType() != null) {
                generic = ctx.typeBound().classOrInterfaceType().accept(wildcardVisitor);
            } else {
                return new Generic(typeName, ctx.typeBound().typeVariable().getText(), ctx.typeBound().EXTENDS().getText(), false, Collections.emptyList());
            }
        } else {
            generic = Collections.emptyList();
            boundType = "";
        }
        return new Generic(typeName, null, boundType, false, generic);
    }

    private static class Holder {
        private static final TypeParameterVisitor INSTANCE = new TypeParameterVisitor();
    }
}
