package visitor;

import data.Generic;
import data.Type;
import generated.Java9BaseVisitor;
import generated.Java9Parser;

import java.util.Collections;
import java.util.List;

final class TypeParameterVisitor extends Java9BaseVisitor<Generic> {

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
                return new Generic(new Type(typeName), new Type(ctx.typeBound().typeVariable().getText()),
                        ctx.typeBound().EXTENDS().getText(), false, Collections.emptyList());
            }
        } else {
            generic = Collections.emptyList();
            boundType = "";
        }
        return new Generic(new Type(typeName), null, boundType, false, generic);
    }

    private static class Holder {
        private static final TypeParameterVisitor INSTANCE = new TypeParameterVisitor();
    }
}
