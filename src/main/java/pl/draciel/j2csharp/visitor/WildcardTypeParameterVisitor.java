package pl.draciel.j2csharp.visitor;

import pl.draciel.j2csharp.data.Generic;
import pl.draciel.j2csharp.data.Type;
import generated.Java9BaseVisitor;
import generated.Java9Parser;
import pl.draciel.j2csharp.utility.Nonnull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class WildcardTypeParameterVisitor extends Java9BaseVisitor<List<Generic>> {

    private WildcardTypeParameterVisitor() {
        //no instance
    }

    public static WildcardTypeParameterVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public List<Generic> visitClassOrInterfaceType(final Java9Parser.ClassOrInterfaceTypeContext ctx) {
        final String type = ctx.classType_lfno_classOrInterfaceType().identifier().getText();

        final Java9Parser.TypeArgumentListContext typeArgumentListContext = ctx.classType_lfno_classOrInterfaceType()
                .typeArguments().typeArgumentList();

        final List<Generic> generics = new ArrayList<>();

        for (int i = 0; i < typeArgumentListContext.typeArgument().size(); i++) {
            final Java9Parser.WildcardContext wildcardContext =
                    typeArgumentListContext.typeArgument().get(i).wildcard();
            if (wildcardContext != null) {
                final String typeParameter = wildcardContext.QUESTION().getText();
                final String bound = getBound(wildcardContext.wildcardBounds());
                final List<Generic> innerGenerics =
                        wildcardContext.wildcardBounds().referenceType().classOrInterfaceType().accept(this);
                generics.add(new Generic(new Type(typeParameter), new Type(type), bound, true, innerGenerics));
            } else {
                final String typeParameter = typeArgumentListContext.typeArgument().get(i).referenceType().getText();
                final String bound = "";
                generics.add(new Generic(new Type(typeParameter), new Type(type), bound, false,
                        Collections.emptyList()));
            }
        }

        return generics;
    }

    private static String getBound(@Nonnull final Java9Parser.WildcardBoundsContext context) {
        if (context.EXTENDS() != null) {
            return context.EXTENDS().getText();
        }
        if (context.SUPER() != null) {
            return context.SUPER().getText();
        }
        return "";
    }

    private static class Holder {
        private static final WildcardTypeParameterVisitor INSTANCE = new WildcardTypeParameterVisitor();
    }
}
