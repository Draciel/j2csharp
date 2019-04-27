package visitor;

import data.Field;
import data.Modifier;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

import java.util.List;
import java.util.stream.Collectors;

class FieldVisitor extends Java9BaseVisitor<Field> {

    private FieldVisitor() {
    }

    public static FieldVisitor instance() {
        return HOLDER.INSTANCE;
    }

    @Override
    public Field visitFieldDeclaration(Java9Parser.FieldDeclarationContext ctx) {
        final List<Modifier> modifiers = ctx.fieldModifier().stream()
                .map(fmc -> Modifier.of(fmc.getText()))
                .collect(Collectors.toList());
        // fixme investigate if this is the only one variable declarator
        final String name = ctx.variableDeclaratorList().variableDeclarator().get(0).variableDeclaratorId()
                .identifier().getText();
        final String type = ctx.unannType().getText();
        return Field.of(name, type, modifiers);
    }

    private static final class HOLDER {
        private static final FieldVisitor INSTANCE = new FieldVisitor();
    }
}
